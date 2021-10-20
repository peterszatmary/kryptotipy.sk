package sk.kryptotipy.web.data.jdbc;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import sk.kryptotipy.web.data.Label;
import sk.kryptotipy.web.data.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class PostMapper implements RowMapper<Post> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Post mapRow(ResultSet rs, int i) throws SQLException {

        Post post = new Post();

        post.setId(rs.getLong("p.id"));
        post.setUrl(rs.getString("p.url"));
        post.setTitle(rs.getString("p.title"));
        post.setI18n(rs.getString("p.i18n"));
        this.setRenderPostTitle(post);

        post.setSource(rs.getString("p.source"));
        post.setSourceRender(rs.getString("p.source_render"));
        post.setImgSrc(rs.getString("p.img_src"));
        post.setViews(rs.getInt("p.clicks"));
        post.setLikes(rs.getInt("p.likes"));
        post.setBirthday(LocalDate.parse(rs.getString("p.birthday"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        post.setLabels(selectLabels(rs.getLong("p.id")));

        return post;
    }


    private List<Label> selectLabels(Long id) {
        List<Label> labels = new ArrayList<>();

        try {
            //SqlParameterSource namedParameters = new MapSqlParameterSource("label_name", label);
            labels  = jdbcTemplate.query(QueryFactory.getLabelsByPostId(), new LabelMapper(), id);
            //labels.stream().forEach(System.out::println);

        } catch(Exception e){
            e.printStackTrace();
        }

        return labels;
    }

    /**
     * render title attribute is calculated each time from title. is not stored in DB
     * @param post
     * @return
     */
    private void setRenderPostTitle(Post post) {
        int MAX_LENGTH = 68;
        if (post.getTitle() == null) {
            post.setTitleRender("N/A");
            return;
        }

        int titleLength = post.getTitle().length();

        if (titleLength < MAX_LENGTH) {
            StringBuilder str = new StringBuilder(post.getTitle());
            for (int i = 0; i < MAX_LENGTH - titleLength; i++) {
                str.append("&#160;");//todo
            }
            post.setTitleRender(str.toString());
            return;
        }

        if (titleLength == MAX_LENGTH) {
            post.setTitleRender(post.getTitle());
            return;
        }

        if (titleLength > MAX_LENGTH) {
            String part = post.getTitle().substring(0, MAX_LENGTH);
            StringBuilder title = new StringBuilder().append(part).append("...");
            post.setTitleRender(title.toString());
            return;
        }
    }
}
package sk.kryptotipy.web.data.jdbc;

import org.springframework.jdbc.core.RowMapper;
import sk.kryptotipy.web.data.Label;
import sk.kryptotipy.web.data.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PostMapper implements RowMapper<Post> {

    @Override
    public Post mapRow(ResultSet rs, int i) throws SQLException {

        Post post = new Post();
        Label label = new Label();
        label.setName(rs.getString("l.name"));

        post.setUrl(rs.getString("p.url"));
        post.setTitle(rs.getString("p.title"));
        this.setRenderPostTitle(post);

        post.setSource(rs.getString("p.source"));
        post.setSourceRender(rs.getString("p.source_render"));
        post.setImgSrc(rs.getString("p.img_src"));
        post.setBirthday(LocalDate.parse(rs.getString("p.birthday"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        post.setLabels(new HashSet<>(Arrays.asList(label)));

        return post;
    }


    private List<Label> selectLabels() {
        
    }

    /**
     * render title atribute is calculated each time from title. is not stored in DB
     * @param post
     * @return
     */
    private void setRenderPostTitle(Post post) {
        int MAX_LENGTH = 45;
        if (post.getTitle() == null) {
            post.setTitleRender("N/A");
            return;
        }

        int titleLength = post.getTitle().length();

        if (titleLength < MAX_LENGTH) {
            StringBuilder str = new StringBuilder(post.getTitle());
            for (int i = 0; i < MAX_LENGTH - titleLength; i++) {
                str.append(" ");
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
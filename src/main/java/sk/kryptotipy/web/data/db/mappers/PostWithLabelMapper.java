package sk.kryptotipy.web.data.db.mappers;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import sk.kryptotipy.web.data.db.PostWithLabel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
public class PostWithLabelMapper implements RowMapper<PostWithLabel> {

    /**
     * @param rs
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public PostWithLabel mapRow(ResultSet rs, int i) throws SQLException {
        var post = new PostWithLabel();

        post.setId(rs.getLong("p.id"));
        post.setPostId(rs.getLong("p.post_id"));
        post.setUrl(rs.getString("p.post_url"));
        post.setTitle(rs.getString("p.post_title"));
        post.setTitleRender(rs.getString("p.post_title_render"));
        post.setLang(rs.getString("p.post_lang"));
        post.setImgSrcOrigin(rs.getString("p.post_original_image"));

        post.setSource(rs.getString("p.post_source"));
        post.setSourceRender(rs.getString("p.post_source_render"));
        post.setImgSrc(rs.getString("p.post_source_image"));
        post.setViews(rs.getInt("p.post_clicks"));
        post.setLikes(rs.getInt("p.post_likes"));
        post.setBirthday(LocalDate.parse(rs.getString("p.post_birthday"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")));

        var labelName1 = rs.getString("p.label_name_1");
        if (labelName1 != null) {
            post.setLabelName1(labelName1);
        }
        var labelRelevance1 = (Integer) rs.getObject("p.label_relevance_1");
        if (labelRelevance1 != null) {
            post.setLabelRelevance1(labelRelevance1);
        }

        var labelName2 = rs.getString("p.label_name_2");
        if (labelName2 != null) {
            post.setLabelName2(labelName2);
        }
        var labelRelevance2 = (Integer) rs.getObject("p.label_relevance_2");
        if (labelRelevance2 != null) {
            post.setLabelRelevance2(labelRelevance2);
        }

        var labelName3 = rs.getString("p.label_name_3");
        if (labelName3 != null) {
            post.setLabelName3(labelName3);
        }
        var labelRelevance3 = (Integer) rs.getObject("p.label_relevance_3");
        if (labelRelevance3 != null) {
            post.setLabelRelevance3(labelRelevance3);
        }

        var labelName4 = rs.getString("p.label_name_4");
        if (labelName4 != null) {
            post.setLabelName4(labelName4);
        }

        var labelRelevance4 = (Integer) rs.getObject("p.label_relevance_4");
        if (labelRelevance4 != null) {
            post.setLabelRelevance4(labelRelevance4);
        }

        return post;
    }
}

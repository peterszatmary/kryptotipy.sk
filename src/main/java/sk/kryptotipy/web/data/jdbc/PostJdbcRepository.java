package sk.kryptotipy.web.data.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Post;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class PostJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Post> getNewestPosts() {
        List<Post> posts = new ArrayList<>();

        try {
            posts  = jdbcTemplate.query(QueryFactory.newestPosts(), new Object[] {}, new PostMapper());

        } catch(Exception e){
            e.printStackTrace();
        }
        return posts;
    }

   public List<Post> getPostsByLabel(String label) {
        List<Post> posts = new ArrayList<>();

        try {
            //SqlParameterSource namedParameters = new MapSqlParameterSource("label_name", label);
            posts  = jdbcTemplate.query(QueryFactory.postsByLabel(), new PostMapper(), label);

        } catch(Exception e){
            e.printStackTrace();
        }

        return posts;
    }

}

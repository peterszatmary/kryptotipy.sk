package sk.kryptotipy.web.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Label;
import sk.kryptotipy.web.data.Post;
import sk.kryptotipy.web.data.jdbc.PostMapper;
import sk.kryptotipy.web.data.jdbc.QueryFactory;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class PostJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Post> getNewestPosts() {
        List<Post> posts = new ArrayList<>();

        try {
            posts  = jdbcTemplate.query(QueryFactory.newestPosts(), new Object[] {}, new PostMapper(jdbcTemplate));

        } catch(Exception e){
            e.printStackTrace();
        }
        return posts;
    }

   public List<Post> getPostsByLabel(String label, Long from, Long to) {
        List<Post> posts = new ArrayList<>();

        try {
            posts  = jdbcTemplate.query(QueryFactory.postsByLabel(), new PostMapper(jdbcTemplate), label, from, to);

        } catch(Exception e){
            e.printStackTrace();
        }

        return posts;
    }

    public List<Post> getPostsByI18n(String i18n, Long from, Long to) {
        List<Post> posts = new ArrayList<>();

        try {
            posts  = jdbcTemplate.query(QueryFactory.postsByI18n(), new PostMapper(jdbcTemplate), i18n, from, to);

        } catch(Exception e){
            e.printStackTrace();
        }

        return posts;
    }

    public List<Post> getPostsByLabelAll(Long from, Long to) {
        List<Post> posts = new ArrayList<>();

        try {
            posts = jdbcTemplate.query(QueryFactory.postsByLabelAll(), new PostMapper(jdbcTemplate), from, to);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;

    }

    public List<Post> getPostsByLabelsAndLang(List<String> labels, List<String> langs, Long from, Long to) {
        List<Post> posts = new ArrayList<>();

        try {

            List<Object> o = new ArrayList<>();
            o.addAll(labels);
            o.addAll(langs);
            o.add(from);
            o.add(to);

            posts  = jdbcTemplate.query(
                    QueryFactory.postsByLabelsAndLangs(labels.size(), langs.size()),
                    new PostMapper(jdbcTemplate),
                    o.toArray()
            );

        } catch(Exception e){
            e.printStackTrace();
        }
        return posts;
    }
}

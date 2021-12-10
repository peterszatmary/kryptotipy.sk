package sk.kryptotipy.web.repository.db;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.db.Post;
import sk.kryptotipy.web.data.db.mappers.PostMapper;
import sk.kryptotipy.web.data.db.mappers.QueryFactory;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@AllArgsConstructor
@Repository
public class PostJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Post> getNewestPosts(List<String> langs) {
       return p(langs, 8);
    }

    public List<Post> getNewsAll(List<String> langs) {
        return p(langs, 60);
    }

    private List<Post> p(List<String> langs, Integer limit) {
        List<Post> posts = new ArrayList<>();

        try {
            List<Object> o = new ArrayList<>();
            o.addAll(langs);
            //o.add(limit);

            posts  = jdbcTemplate.query(QueryFactory.newestPosts(langs.size(), limit), new PostMapper(jdbcTemplate), o.toArray());

        } catch(Exception e){
            e.printStackTrace();
        }
        return posts;
    }


    public boolean updatePostClicks(String url, Integer clicks) {
        return jdbcTemplate.update("update post set clicks = ? where url like ?",
                clicks, url + "%") > 0;
    }

    public Post getPostById(Long id) {
        List<Post> posts = new ArrayList<>();
        try {
            posts  = jdbcTemplate.query(QueryFactory.postById(), new PostMapper(jdbcTemplate), id);

        } catch(Exception e){
            e.printStackTrace();
        }

        return posts.isEmpty() ? null : posts.get(0);
    }

    public List<Post> getNews(List<String> labels, List<String> langs, Long fromLimit, Long toLimit) {
        var result = new ArrayList<Post>();

        try {
            var o = new ArrayList<>();
            o.addAll(langs);
            o.addAll(labels);
            o.add(fromLimit);
            o.add(toLimit);

            var posts  = jdbcTemplate.query(
                    QueryFactory.getNews(labels.size(), langs.size()),
                    new PostMapper(jdbcTemplate),
                    o.toArray()
            );

            var resultWithoutDuplicities = new LinkedHashSet<Post>();
            posts.stream().forEach(post -> resultWithoutDuplicities.add(post));
            result = new ArrayList<>(resultWithoutDuplicities);

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

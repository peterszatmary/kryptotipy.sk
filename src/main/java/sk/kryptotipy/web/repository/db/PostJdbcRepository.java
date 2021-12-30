package sk.kryptotipy.web.repository.db;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.db.Post;
import sk.kryptotipy.web.data.db.PostWithLabel;
import sk.kryptotipy.web.data.db.mappers.PostWithLabelMapper;
import sk.kryptotipy.web.data.db.mappers.QueryFactory;

import java.util.*;

@AllArgsConstructor
@Repository
public class PostJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<PostWithLabel> getNewestPosts(List<String> langs) {
       return p(langs, 8);
    }

    public List<PostWithLabel> getNewsAll(List<String> langs) {
        return p(langs, 60);
    }

    private List<PostWithLabel> p(List<String> langs, Integer limit) {
        List<PostWithLabel> results = new ArrayList<>();

        try {
            List<Object> o = new ArrayList<>();
            o.addAll(langs);
            //o.add(limit);

            results  = jdbcTemplate.query(QueryFactory.newestPosts(langs.size(), limit * 4), new PostWithLabelMapper(), o.toArray());
        } catch(Exception e){
            e.printStackTrace();
        }
        return results;
    }


    public boolean updatePostClicks(String url, Integer clicks) {
        return jdbcTemplate.update("update post set clicks = ? where url like ?",
                clicks, url + "%") > 0;
    }

    public PostWithLabel getPostById(Long id) {
        List<PostWithLabel> posts = new ArrayList<>();
        try {
            posts  = jdbcTemplate.query(QueryFactory.postById(), new PostWithLabelMapper(), id);

        } catch(Exception e){
            e.printStackTrace();
        }

        return posts.isEmpty() ? null : posts.get(0);
    }

    public List<PostWithLabel> getNews(List<String> labels, List<String> langs, Long fromLimit, Long toLimit) {
        List<PostWithLabel> result = new ArrayList<>();

        try {
            var o = new ArrayList<>();
            o.addAll(langs);
            o.addAll(labels);
            o.add(fromLimit);
            o.add(toLimit);

            result  = jdbcTemplate.query(
                    QueryFactory.getNews(labels.size(), langs.size()),
                    new PostWithLabelMapper(),
                    o.toArray()
            );

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

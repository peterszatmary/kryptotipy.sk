package sk.kryptotipy.web.repository.db;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.db.Post;
import sk.kryptotipy.web.data.db.PostWithLabel;
import sk.kryptotipy.web.data.db.mappers.PostMapper;
import sk.kryptotipy.web.data.db.mappers.PostWithLabelMapper;
import sk.kryptotipy.web.data.db.mappers.QueryFactory;
import sk.kryptotipy.web.service.UtilityService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class PostJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UtilityService utilityService;

    public List<PostWithLabel> getNewestPosts(List<String> langs) {
       return p(langs, 12);
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

            results  = jdbcTemplate.query(QueryFactory.newestPosts(langs.size(), limit), new PostWithLabelMapper(utilityService), o.toArray());
        } catch(Exception e){
            e.printStackTrace();
        }
        return results;
    }


    //old one, maybe one day i will use it again
    public boolean updatePostClicks(String url, Integer clicks) {
        return jdbcTemplate.update("update post set clicks = ? where url like ?",
                clicks, url + "%") > 0;
    }

    public boolean updatePostClicksInViewTable(String url, Integer clicks) {
        return jdbcTemplate.update("update post_with_label set post_clicks = ? where post_url like ?",
                clicks, url + "%") > 0;
    }

    public Post getPostById(Long id) {
        List<Post> posts = new ArrayList<>();
        try {
            posts  = jdbcTemplate.query(QueryFactory.postById(), new PostMapper(jdbcTemplate, utilityService), id);

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
                    new PostWithLabelMapper(utilityService),
                    o.toArray()
            );

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

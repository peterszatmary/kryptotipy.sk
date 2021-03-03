package sk.kryptotipy.web.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Label;
import sk.kryptotipy.web.data.Post;
import sk.kryptotipy.web.data.jdbc.PostMapper;
import sk.kryptotipy.web.data.jdbc.QueryFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Repository
public class PostJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Post> getNewestPosts(List<String> langs) {
        List<Post> posts = new ArrayList<>();

        try {
            List<Object> o = new ArrayList<>();
            o.addAll(langs);

            posts  = jdbcTemplate.query(QueryFactory.newestPosts(langs.size()), new PostMapper(jdbcTemplate), o.toArray());

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

    public Post findByUrl(String url) {
        List<Post> posts = new ArrayList<>();
        try {
            posts  = jdbcTemplate.query(QueryFactory.postByUrl(), new PostMapper(jdbcTemplate), url + "%");

        } catch(Exception e){
            e.printStackTrace();
        }
        return posts.get(0); //TODO what if two ? than it means problem in data consistency !
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
        List<Post> result = new ArrayList<>();

        try {

            List<Object> o = new ArrayList<>();
            o.addAll(labels);
            o.addAll(langs);
            o.add(from);
            o.add(to(to));

            List<Post> posts  = jdbcTemplate.query(
                    QueryFactory.postsByLabelsAndLangs(labels.size(), langs.size()),
                    new PostMapper(jdbcTemplate),
                    o.toArray()
            );

            //System.out.println(">>>>>" + posts.size());

            Set<Post> resultWithoutDuplicities = new HashSet<>();
            posts.stream().forEach(post -> resultWithoutDuplicities.add(post));
            result = new ArrayList<>(resultWithoutDuplicities);


            //System.out.println(">>>>>" + resultWithoutDuplicities.size());

        } catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean updatePostClicks(String url, Integer clicks) {
        return jdbcTemplate.update("update post set clicks = ? where url like ?",
                clicks, url + "%") > 0;
    }

    private Long to(Long to) {
        final Integer NUM_OF_LABELS = 5;
        return to * NUM_OF_LABELS;
    }
}

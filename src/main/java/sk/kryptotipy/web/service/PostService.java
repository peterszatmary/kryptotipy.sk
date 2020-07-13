package sk.kryptotipy.web.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.data.Label;
import sk.kryptotipy.web.data.Post;
import sk.kryptotipy.web.data.jdbc.PostJdbcRepository;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class PostService implements PostServiceApi {

    private final PostJdbcRepository postLabelRepository;


    public List<Post> getPostsByLabel(String label, Long from, Long to) {

        List<Post> posts = postLabelRepository
                .getPostsByLabel(label, from, to)
                .stream()
                .collect(Collectors.toList());

        //posts.stream().forEach(System.out::println);
        //return groupSimilar(posts).stream().limit(20).collect(Collectors.toList());
        return posts;
    }

    @Override
    public List<Post> getPostsByLabelAll(Long from, Long to) {
        List<Post> posts = postLabelRepository
                .getPostsByLabelAll(from, to)
                .stream()
                .collect(Collectors.toList());
        return posts;
    }

    //TODO
    public List<Post> getNewestPosts() {

        List<Post> posts = postLabelRepository
                .getNewestPosts()
                .stream()
                .limit(100)
                .collect(Collectors.toList());

        //return groupSimilar(posts).stream().limit(20).collect(Collectors.toList());
        return posts;
    }

    //TODO sort by birthday ? fix
//    public static List<Post> groupSimilar(final List<Post> posts) {
//        Map<String, Post> postsByUrl = new HashMap<>();
//
//        for (Post post: posts) {
//            String url = post.getUrl();
//            if (!postsByUrl.containsKey(url)) {
//                postsByUrl.put(url, post);
//            } else {
//                Post p = postsByUrl.get(url);
//                Set<Label> existingLabels = p.getLabels();
//                existingLabels.add(post.getLabels().iterator().next());
//                p.setLabels(existingLabels);
//                postsByUrl.put(p.getUrl(), p);
//            }
//        }
//        return postsByUrl.values().stream().collect(Collectors.toList());
//    }

    //TODO
    public List<Post> getPosts() {
        return null;
    }

}

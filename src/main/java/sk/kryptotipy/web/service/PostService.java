package sk.kryptotipy.web.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
                .collect(Collectors.toList());
        return posts;
    }

    //TODO
    public List<Post> getPosts() {
        return null;
    }

}

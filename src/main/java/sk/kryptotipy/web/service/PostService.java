package sk.kryptotipy.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.data.Label;
import sk.kryptotipy.web.data.Post;
import sk.kryptotipy.web.data.PostRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostService implements PostServiceApi {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //TODO


    //TODO
    public List<Post> getPosts() {
        //return postRepository.findAll().stream().limit(100).collect(Collectors.toList());
        return postRepository.findAllByOrderByBirthdayDesc().stream().limit(20).collect(Collectors.toList());
        //return postRepository.findByLabel("bitcoin");
    }

    //TODO
    public Page<Post> getNewestPosts() {
        return postRepository.findAll(PageRequest.of(0, 12));
    }
}

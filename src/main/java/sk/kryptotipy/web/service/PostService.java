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
        return postRepository.findAllByOrderByBirthdayDesc()
                .stream()
                .limit(20)
                .map(post -> { post.setTitleRender(this.renderPostTitle(post));return post;})
                .collect(Collectors.toList());
        //return postRepository.findByLabel("bitcoin");
    }

    //TODO
    public Page<Post> getNewestPosts() {
        return postRepository.findAll(PageRequest.of(0, 12));
    }

    public String renderPostTitle(Post post) {
        int MAX_LENGTH = 66;
        if (post.getTitle() == null) {
            return "N/A";
        }
        String raw  = post.getTitle().substring(0, MAX_LENGTH - 1);
        if (raw.length() <= MAX_LENGTH - 3) {
            return raw;
        } else {
            String part = raw.substring(0, MAX_LENGTH - 3);
            StringBuilder title = new StringBuilder().append(part).append("...");
            return title.toString();
        }
    }
}

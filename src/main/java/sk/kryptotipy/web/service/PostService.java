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
        List<Post> posts = postRepository.findAllByOrderByBirthdayDesc()
                .stream()
                .limit(20)
                .map(post -> { this.setRenderPostTitle(post); return post; })
                .collect(Collectors.toList());

        //return postRepository.findByLabel("bitcoin");

        return posts;
    }

    //TODO
    public Page<Post> getNewestPosts() {
        return postRepository.findAll(PageRequest.of(0, 12));
    }

    /**
     * render title atribute is calculated each time from title. is not stored in DB
     * @param post
     * @return
     */
    public void setRenderPostTitle(Post post) {
        int MAX_LENGTH = 45;
        if (post.getTitle() == null) {
            post.setTitleRender("N/A");
            return;
        }

        int titleLength = post.getTitle().length();

        if (titleLength < MAX_LENGTH) {
            StringBuilder str = new StringBuilder(post.getTitle());
            for (int i = 0; i < MAX_LENGTH - titleLength; i++) {
                str.append(" ");
            }
            post.setTitleRender(str.toString());
            return;
        }

        if (titleLength == MAX_LENGTH) {
            post.setTitleRender(post.getTitle());
            return;
        }

        if (titleLength > MAX_LENGTH) {
            String part = post.getTitle().substring(0, MAX_LENGTH);
            StringBuilder title = new StringBuilder().append(part).append("...");
            post.setTitleRender(title.toString());
            return;
        }
    }
}

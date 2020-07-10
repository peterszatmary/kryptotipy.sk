package sk.kryptotipy.web.service;

import org.springframework.data.domain.Page;
import sk.kryptotipy.web.data.Post;

import java.util.List;

public interface PostServiceApi {
    Page<Post> getNewestPosts();
    List<Post> getPosts();
}

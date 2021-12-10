package sk.kryptotipy.web.api;

import sk.kryptotipy.web.data.Donate;
import sk.kryptotipy.web.data.db.Post;

import java.util.List;

public interface PostServiceApi {
    List<Post> getNewestPosts(List<String> langs);
    Post getPostsById(Long id);
    boolean updatePostViews(String url, Integer views);
    List<Donate> getAllDonates();
}
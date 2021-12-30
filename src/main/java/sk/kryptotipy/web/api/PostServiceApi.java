package sk.kryptotipy.web.api;

import sk.kryptotipy.web.data.Donate;
import sk.kryptotipy.web.data.db.Post;
import sk.kryptotipy.web.data.db.PostWithLabel;

import java.util.List;

public interface PostServiceApi {
    List<PostWithLabel> getNewestPosts(List<String> langs);
    PostWithLabel getPostsById(Long id);
    boolean updatePostViews(String url, Integer views);
    List<Donate> getAllDonates();
}
package sk.kryptotipy.web.service;

import sk.kryptotipy.web.data.db.Post;
import sk.kryptotipy.web.data.db.PostWithLabel;

import java.util.List;

public sealed interface PostServiceApi permits PostService {
    List<PostWithLabel> getNewestPosts(List<String> langs);
    Post getPostsById(Long id);
    boolean updatePostViews(String url, Integer views);
}
package sk.kryptotipy.web.service;

import sk.kryptotipy.web.data.Post;

import java.util.List;

public interface PostServiceApi {
    List<Post> getNewestPosts();
    List<Post> getPosts();
    List<Post> getPostsByLabel(String label, Long from, Long to);
    List<Post> getPostsByLabelAll(Long from, Long to);
}

package sk.kryptotipy.web.api;

import sk.kryptotipy.web.data.Post;

import java.util.List;

public interface PostServiceApi {
    List<Post> getNewestPosts();
    List<Post> getPostsByLabel(String label, Long from, Long to);
    List<Post> getPostsByLabelAll(Long from, Long to);
    List<Post> getPostsByI18n(String i18n, Long from, Long to);
    List<Post> getPostsByLabelsAndLangs(List<String> labels, List<String>langs, Long from, Long to);

}

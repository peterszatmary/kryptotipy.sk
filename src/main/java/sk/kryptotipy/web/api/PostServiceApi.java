package sk.kryptotipy.web.api;

import sk.kryptotipy.web.data.Donate;
import sk.kryptotipy.web.data.Post;
import sk.kryptotipy.web.data.Source;

import java.util.List;

public interface PostServiceApi {
    List<Post> getNewestPosts(List<String> langs);
    List<Post> getPostsByLabel(String label, Long from, Long to);
    List<Post> getPostsByLabelAll(Long from, Long to);
    Post getPostsById(Long id);
    List<Post> getPostsByI18n(String i18n, Long from, Long to);
    List<Post> getPostsByLabelsAndLangs(List<String> labels, List<String>langs, Long from, Long to);
    Post getPostByUrl(String url);
    Post getPostByUrlPartAndSourceRendered(String urlPart, String sourceRendered);
    boolean updatePostViews(String url, Integer views);
    List<Source> getAllSources();
    List<Donate> getAllDonates();
}
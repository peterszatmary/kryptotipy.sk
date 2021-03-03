package sk.kryptotipy.web.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.api.PostServiceApi;
import sk.kryptotipy.web.data.Post;
import sk.kryptotipy.web.repository.PostJdbcRepository;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class PostService implements PostServiceApi {

    private final PostJdbcRepository postLabelRepository;

    public List<Post> getPostsByLabel(String label, Long from, Long to) {
        List<Post> posts = postLabelRepository
                .getPostsByLabel(label, from, to);
        return posts;
    }

    @Override
    public List<Post> getPostsByLabelAll(Long from, Long to) {
        List<Post> posts = postLabelRepository
                .getPostsByLabelAll(from, to);
        return posts;
    }

    @Override
    public List<Post> getPostsByI18n(String i18n, Long from, Long to) {
        return postLabelRepository.getPostsByI18n(i18n, from, to);
    }

    @Override
    public List<Post> getPostsByLabelsAndLangs(List<String> labels, List<String> langs, Long from, Long to) {
        return postLabelRepository.getPostsByLabelsAndLang(labels, langs, from, to);
    }

    @Override
    public Post getPostByUrl(String url) {
        return postLabelRepository.findByUrl(url);
    }

    @Override
    public boolean updatePostViews(String url, Integer views) {
        return postLabelRepository.updatePostClicks(url, views);
    }

    @Override
    public List<Post> getNewestPosts(List<String> langs) {
        List<Post> posts = postLabelRepository
                .getNewestPosts(langs);
        return posts;
    }
}

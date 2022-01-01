package sk.kryptotipy.web.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.api.PostServiceApi;
import sk.kryptotipy.web.data.Donate;
import sk.kryptotipy.web.data.db.Post;
import sk.kryptotipy.web.data.db.PostWithLabel;
import sk.kryptotipy.web.repository.DonateRepository;
import sk.kryptotipy.web.repository.db.PostJdbcRepository;
import sk.kryptotipy.web.repository.db.PostWithLabelRepository;


import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class PostService implements PostServiceApi {

    private final PostJdbcRepository postLabelRepository;
    private final DonateRepository donateRepository;

    @Override
    public Post getPostsById(Long id) {
        return postLabelRepository.getPostById(id);
    }


    @Override
    public boolean updatePostViews(String url, Integer views) {
        var postTable = postLabelRepository.updatePostClicksInViewTable(url, views);
        var view = postLabelRepository.updatePostClicks(url, views);
        return postTable && view;
    }

    @Override
    public List<PostWithLabel> getNewestPosts(List<String> langs) {
        return postLabelRepository.getNewestPosts(langs);
    }

    @Deprecated
    @Override
    public List<Donate> getAllDonates() {
        return donateRepository.getDonates();
    }
}

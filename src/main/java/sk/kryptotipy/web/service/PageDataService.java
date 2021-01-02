package sk.kryptotipy.web.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.api.PageDataServiceApi;

import java.util.HashMap;
import java.util.Map;

@Service
@Data
@Slf4j
public class PageDataService implements PageDataServiceApi {

    @Value("${page.title}")
    private String pageTitle;

    @Value("${page.description}")
    private String pageDescription;

    @Value("${page.description.utf8}")
    private String pageDescriptionUtf8;

    @Value("${page.author}")
    private String pageAuthor;

    @Value("${base.url}")
    private String baseUrl;

    @Value("${project.version}")
    private String projectVersion;

    @Value("${page.size}")
    private Integer pageSize;

    @Value("${project.post.count}")
    private String postsCount;

    @Value("${post.title.btc-is-not-the-first}")
    private String btcIsNotFirstTitle;

    @Value("${post.description.btc-is-not-the-first}")
    private String btcIsNotFirstDescription;

    // info about if pagination should go to view
    private Boolean paginationOn = Boolean.TRUE;

    private Map<String, String> postsMeta = new HashMap<>();


    public void set(Map<String, Object> model, String pageTitleCustom) {
        model.put("page_title", pageTitle + " " + pageTitleCustom);
        model.put("page_description", pageDescription);
        model.put("page_description_utf8", pageDescriptionUtf8);
        model.put("page_author", pageAuthor);
        model.put("base_url", baseUrl);
        model.put("title", pageTitle);
        model.put("posts_count", postsCount);
        model.put("project_version", projectVersion);

        addPostsMeta();
    }


    private void addPostsMeta() {
        postsMeta.put("post.title.btc-is-not-the-first", "Bitcoin nebol prvou kryptomenou");
        postsMeta.put("post.description.btc-is-not-the-first","KRYPTOTIPY.SK - Pred Bitcoinom tu naozaj bola iná kryptomena.");
    }

    public String getPostTitle(String key) {
        return postsMeta.get("post.title." + key);
    }

    public String getPostDescription(String key) {
        return postsMeta.get("post.description." + key);
    }

}
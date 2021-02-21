package sk.kryptotipy.web.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.AppProperties;
import sk.kryptotipy.web.api.PageDataServiceApi;
import sk.kryptotipy.web.component.ArticleComponent;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@Data
@Slf4j
@AllArgsConstructor
public class PageDataService implements PageDataServiceApi {

    private final AppProperties appProperties;

    private final ArticleComponent articleComponent;


    public void set(Map<String, Object> model, String pageTitleCustom) {
        model.put("page_title", pageTitleCustom + " | " + appProperties.getPageTitle());
        model.put("page_author", appProperties.getPageAuthor());
        model.put("base_url", appProperties.getBaseUrl());
        model.put("base_url_without_port", appProperties.getBaseUrlWithoutPort());
        model.put("title", appProperties.getPageTitle());
        model.put("posts_count", appProperties.getPostsCount());
        model.put("project_version", appProperties.getProjectVersion());
        model.put("page_image", appProperties.getPageImagePath());
        model.put("page_img_width", "85");
        model.put("page_img_height", "85");
        model.put("page_date", LocalDateTime.now());
        model.put("page_description", pageTitleCustom + " zo všetkých podporovaných štátov európy. Najčerstvejšie krypto novinky z celej európy na jednom mieste. ");

        articleComponent.addPostsAndArticlesMeta();
    }

    public String getPageDescription() {
        return this.appProperties.getPageDescription();
    }

    public String getPostTitle(String key) {
        return this.articleComponent.getPostTitle(key);
    }

    public String getPostDescription(String key) {
        return this.articleComponent.getPostDescription(key);
    }

    public String getPostImage(String key) {
        return this.articleComponent.getPostImage(key);
    }

    public String getPostDate(String key) {
        return this.articleComponent.getPostDate(key);
    }

    public String getPostImageWidth(String key) {
        return this.articleComponent.getPostImageWidth(key);
    }

    public String getPostImageHeight(String key) {
        return this.articleComponent.getPostImageHeight(key);
    }



}
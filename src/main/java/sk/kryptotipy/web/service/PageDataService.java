package sk.kryptotipy.web.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.AppProperties;
import sk.kryptotipy.web.api.PageDataServiceApi;
import sk.kryptotipy.web.component.ArticleComponent;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.ResourceBundle;

@Service
@Data
@Slf4j
@AllArgsConstructor
public class PageDataService implements PageDataServiceApi {

    private final AppProperties appProperties;
    private final ArticleComponent articleComponent;
    private final UtilityService utilityService;

    public void set(Map<String, Object> model, String pageTitleCustom, String pageDescription, HttpServletRequest request) {
        model.put("page_title", pageTitleCustom + " | " + appProperties.getPageTitle());
        model.put("page_author", appProperties.getPageAuthor());
        model.put("base_url", appProperties.getBaseUrl());
        model.put("base_url_without_port", appProperties.getBaseUrlWithoutPort());
        model.put("base_url_without_port_en", appProperties.getBaseUrlWithoutPortEn());
        model.put("base_url_without_port_de", appProperties.getBaseUrlWithoutPortDe());
        model.put("base_url_without_port_sk", appProperties.getBaseUrlWithoutPortSk());
        model.put("base_url_without_port_hu", appProperties.getBaseUrlWithoutPortHu());
        model.put("base_url_without_port_pl", appProperties.getBaseUrlWithoutPortPl());
        model.put("base_url_without_port_nl", appProperties.getBaseUrlWithoutPortNl());
        model.put("title", appProperties.getPageTitle());
        model.put("posts_count", appProperties.getPostsCount());
        model.put("project_version", appProperties.getProjectVersion());
        model.put("page_image", appProperties.getPageImagePath());
        model.put("page_img_width", "85");
        model.put("page_img_height", "85");
        model.put("page_date", LocalDateTime.now());
        model.put("page_description", pageTitleCustom + pageDescription);
        model.put("canonical_url", utilityService.getCanonicalUrl(request));

        articleComponent.addPostsAndArticlesMeta();
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
package sk.kryptotipy.web.service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

public sealed interface PageDataServiceApi permits PageDataService {
    void set(Map<String, Object> model, String resolvedLocale, String pageTitleCustom, String pageDescription, HttpServletRequest request);
    String getPostTitle(String key);
    String getPostDescription(String key);
    String getPostImage(String key);
    String getPostDate(String key);
    String getPostImageWidth(String key);
    String getPostImageHeight(String key);
}

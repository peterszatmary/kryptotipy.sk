package sk.kryptotipy.web.api;

import java.util.Map;

public interface PageDataServiceApi {
    void set(Map<String, Object> model, String pageTitleCustom, String pageDescription);
    String getPostTitle(String key);
    String getPostDescription(String key);
    String getPostImage(String key);
    String getPostDate(String key);
    String getPostImageWidth(String key);
    String getPostImageHeight(String key);
}

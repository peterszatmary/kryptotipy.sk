package sk.kryptotipy.web.api;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface PageDataServiceApi {
    void set(Map<String, Object> model, String pageTitleCustom, String pageDescription, HttpServletRequest request);
    String getPostTitle(String key);
    String getPostDescription(String key);
    String getPostImage(String key);
    String getPostDate(String key);
    String getPostImageWidth(String key);
    String getPostImageHeight(String key);
}

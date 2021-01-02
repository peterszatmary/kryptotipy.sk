package sk.kryptotipy.web.api;

import java.util.Map;

public interface PageDataServiceApi {
    void set(Map<String, Object> model, String pageTitleCustom);
    String getPostTitle(String key);
    String getPostDescription(String key);
}

package sk.kryptotipy.web.service;

import java.util.Map;

public interface PageDataServiceApi {
    void set(Map<String, Object> model, String pageTitleCustom);
}

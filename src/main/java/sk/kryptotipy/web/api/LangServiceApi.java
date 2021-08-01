package sk.kryptotipy.web.api;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LangServiceApi {
    String getLang(HttpServletRequest request);
    String getLocaleFromUrl(String url);
    List<String> getLangsByUserCountry(String computedLang);
}

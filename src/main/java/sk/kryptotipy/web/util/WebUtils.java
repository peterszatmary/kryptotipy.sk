package sk.kryptotipy.web.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import sk.kryptotipy.web.config.MixedLocaleResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class WebUtils {

    public static void addCookies(Map<String, Object> model, HttpServletRequest request, MixedLocaleResolver mixedLocaleResolver) {
        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var cookies = resolvedLocale.getLanguage().toLowerCase();
        model.put("cookies_lang", cookies);
        log.debug("cookies '{}'", cookies);
    }

    public static void makeLinkActive(Map<String, Object> model, String activeLink) {
        var links = new ArrayList<>(Arrays.asList(
                "index", "posts", "dictionary", "external_services", "did_you_know", "krypto_learning", "cooperation"
        ));
        if (!links.contains(activeLink)) {
            activeLink = "index";
        }

        model.put("active_" + activeLink, "active");
        links.remove(activeLink);
        links.stream().forEach((var x) -> model.put("active_" + x, ""));
    }
}

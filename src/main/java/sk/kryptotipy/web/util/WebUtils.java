package sk.kryptotipy.web.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import sk.kryptotipy.web.config.MixedLocaleResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@Slf4j
public class WebUtils {

    public static String getClientIpAddress(HttpServletRequest request) {
        var ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("HTTP_VIA");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

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
        links.forEach((var x) -> model.put("active_" + x, ""));
    }
}

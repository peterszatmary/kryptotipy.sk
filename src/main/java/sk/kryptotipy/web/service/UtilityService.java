package sk.kryptotipy.web.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import org.springframework.web.servlet.LocaleResolver;
import sk.kryptotipy.web.AppProperties;
import sk.kryptotipy.web.data.Label;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class UtilityService {

    private final AppProperties appProperties;

    //TODO good for deletion
//    public ResourceBundle getResourceBundle(HttpServletRequest request) {
//        ResourceBundle rb = ResourceBundle.getBundle(
//                "messages", new Locale(consumeAcceptLang(request))
//        );
//        return rb;
//    }

    //TODO
    public ResourceBundle getResourceBundleSk() {
        ResourceBundle rb = ResourceBundle.getBundle(
                "messages", new Locale("sk")
        );
        return rb;
    }

    public ResourceBundle getResourceBundle(String langLower) {
        if (langLower.contains("sk")) {//TODO news -> is null

            return ResourceBundle.getBundle("messages", new Locale("sk"));
        } else if (langLower.contains("cs")) {
            return ResourceBundle.getBundle("messages", new Locale("sk"));
        } else if (langLower.contains("de")) {
            return ResourceBundle.getBundle("messages", new Locale("de"));
        } else if (langLower.contains("pl")) {
            return ResourceBundle.getBundle("messages", new Locale("pl"));
        } else if (langLower.contains("hu")) {
            return ResourceBundle.getBundle("messages", new Locale("hu"));
        } else if (langLower.contains("en")) {
            //LocaleContextHolder.setLocale(new Locale("hu"));//TODO
            //LocaleContextHolder.setLocale(new Locale("en_US"));
            log.info("Returning en resource bundle");
            return ResourceBundle.getBundle("messages", new Locale("en"));
        } else if (langLower.contains("us")) {
            return ResourceBundle.getBundle("messages", new Locale("en"));
        } else if (langLower.contains("nl")) {
            return ResourceBundle.getBundle("messages", new Locale("nl"));
        } else {
            return ResourceBundle.getBundle("messages", new Locale("sk"));
        }
    }

    public String buildSiteUrl(List<String> labels, List<String> langs) {
        StringBuilder siteUrl = new StringBuilder("news?");
        labels.stream().forEach(label -> siteUrl.append("label=").append(label).append("&"));
        langs.stream().forEach(lang -> siteUrl.append("lang=").append(lang).append("&"));
        siteUrl.delete(siteUrl.length() - 1, siteUrl.length());
        return siteUrl.toString();
    }

    public String mapSiteUrl(String url, List<String> labels, List<String> langs) {

        if (labels.size() == 1) {
            return "posts/" + labels.get(0);
        }
        if (langs.size() == 1) {
            return "posts-by-i18n/" + langs.get(0).toUpperCase();
        }
        if ("news".equals(url)) {
            return "posts/all";
        }
        return "news";
    }


    //TODO translate
    public String mapLabelForRendering(String label) {
        String result = label;
        if ("wallets".equals(label)) {
            result = "peňaženky";
        }

        else if ("not-categorized".equals(label)) {
            result = "ostatné";
        }

        else if ("cryptocurrency".equals(label)) {
            result = "kryptomeny";
        }

        else if ("exchange".equals(label)) {
            result = "burzy";
        }
        return result;
    }

    public List<String> checkAndInitCheckBoxInput(
            String inputKey,
            Map<String, String[]> parameterMap,
            List<String> initValues
    ) {
        //TODO from DB
        return (null == parameterMap.get(inputKey)) ?
                initValues : new ArrayList<>(Arrays.asList(parameterMap.get(inputKey)));
    }



    public String buildUrlWithLang(String lang) {
        if (StringUtils.isEmpty(lang)) {
            return "https://www.kryptotipy.sk";
        } else {
            return "https://" + lang + ".kryptotipy.sk";
        }
    }


    public Long decodeKryptotipyHash(String hashedId) {
        String s = hashedId.substring(1, hashedId.length() - 2);
        return Long.valueOf(s) - 217L;
    }
}

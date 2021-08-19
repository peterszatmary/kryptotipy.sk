package sk.kryptotipy.web.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class UtilityService {

    //TODO
    public ResourceBundle getResourceBundleSk() {
        ResourceBundle rb = ResourceBundle.getBundle(
                "messages", new Locale("sk")
        );
        return rb;
    }

    public ResourceBundle getResourceBundle(Locale locale) {
        ResourceBundle result = ResourceBundle.getBundle("messages", locale);
        log.info("ResourceBundle with locale was found = {}", result.getLocale());
        return result;
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

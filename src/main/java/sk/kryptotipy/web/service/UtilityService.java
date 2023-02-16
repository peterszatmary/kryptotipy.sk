package sk.kryptotipy.web.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.component.UrlComponent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class UtilityService {

    private final UrlComponent urlComponent;

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
        labels.stream().forEach((var label) -> siteUrl.append("label=").append(label).append("&"));
        langs.stream().forEach((var lang) -> siteUrl.append("lang=").append(lang).append("&"));
        siteUrl.delete(siteUrl.length() - 1, siteUrl.length());
        return siteUrl.toString();
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


    public Long decodeKryptotipyHash(String hashedId) {
        String s = hashedId.substring(1, hashedId.length() - 2);
        return Long.valueOf(s) - 217L;
    }

    public String getCanonicalUrl(HttpServletRequest request) {
        String fullUrl;
        if (request.getQueryString() == null) {
            fullUrl = request.getRequestURL().toString();
        } else {
            fullUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
        }
        return urlComponent.getCanonical(fullUrl);
    }

    public LocalDate parseDate(String rawDate, String postUrl) {
        var birthDay = LocalDate.now();
        try {
            birthDay = LocalDate.parse(rawDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException ex) {
            log.warn("Date not parsed correctly for [{}], post url = [{}]", rawDate, postUrl);
        }
        return birthDay;
    }
}

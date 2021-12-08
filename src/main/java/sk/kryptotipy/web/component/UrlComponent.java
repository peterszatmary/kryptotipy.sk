package sk.kryptotipy.web.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UrlComponent {

    private String getBaseUrl(String url) {
        var p = Pattern.compile("(https://.*)/.*/.*");
        var m = p.matcher(url);
        if (m.matches()) {
            return m.group(1);
        }

        p = Pattern.compile("(https://.*)/.*");
        m = p.matcher(url);
        if (m.matches()) {
            return m.group(1);
        }
        return url;
    }


    // canonical should be same as actual url
    public String getCanonical(String url) {

        url = comToSK(url);
        var urlWithoutPort = url;
        if (url.contains(":443")) {
            urlWithoutPort = url.replace(":443", "");
        } else if (url.contains(":8443")){
            urlWithoutPort = url.replace(":8443", "");
        }

        if (urlWithoutPort.contains("news") || urlWithoutPort.contains("posts")) {
            return this.mapSiteUrlForCanonical(
                    urlWithoutPort,
                    getLabelsFromUrl(urlWithoutPort),
                    getLangsFromUrl(urlWithoutPort)
            );
        } else {
            return urlWithoutPort;
        }
    }

    private String comToSK(String url) {
        return url.contains(".com") ? url.replace(".com", ".sk") : url;
    }



    private List<String> getLabelsFromUrl(String url) {
        return Arrays.asList(url.split("label=")).stream()
                .skip(1)
                .map(x -> x.split("&")[0])
                .collect(Collectors.toList());
    }

    private List<String> getLangsFromUrl(String url) {
        return Arrays.asList(url.split("lang=")).stream()
                .skip(1)
                .map(x -> x.split("&")[0])
                .collect(Collectors.toList());
    }

    /**
     * just for news
      */

    public String mapSiteUrlForCanonical(String url, List<String> labels, List<String> langs) {

        if (labels.size() == 1) {
            return getBaseUrl(url) + "/posts/" + labels.get(0);
        }
        if (langs.size() == 1) {
            return getBaseUrl(url) + "/posts-by-i18n/" + langs.get(0).toUpperCase();
        }


        if (labels.isEmpty() && !langs.isEmpty() && url.contains("posts")) {
            return url; //TODO maybe return getBaseUrl(url) + "/posts/all";
        }

        if (labels.isEmpty() && langs.isEmpty() && !url.contains("posts") && url.contains("news")) {
            return getBaseUrl(url) + "/posts/all";
        }

        if (labels.isEmpty() && langs.isEmpty() && url.contains("posts")) {
            return url; //TODO maybe return getBaseUrl(url) + "/posts/all";
        }

        if (labels.size() > 1) {
            return getBaseUrl(url) + "/posts/all";
        }

        if (langs.size() > 1) {
            return getBaseUrl(url) + "/posts/all";
        }

        if (url.contains("news") || url.contains("posts")) {
            return getBaseUrl(url) + url.split("news")[0] + "/posts/all";
        }

        return url;
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

}

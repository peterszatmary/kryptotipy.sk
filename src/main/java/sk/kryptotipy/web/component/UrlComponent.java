package sk.kryptotipy.web.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

        String o = "posts";
        if (urlWithoutPort.contains("news") || urlWithoutPort.contains("posts")) {
            return this.mapSiteUrlForCanonical(urlWithoutPort);
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
                .map(x -> x.toLowerCase())
                .map(x -> x.split("&")[0])
                .collect(Collectors.toList());
    }

    private Long getLabelsFromPostsUrl(String url) {

       if (url.contains("_l_")) {
           return (long) url.split("_l_")[0].split("_").length;
       } else {
           return (long) url.split("_").length;
       }
    }

    private Long getLangsFromPostsUrl(String url) {
        if (url.contains("_l_")) {
            return (long) url.split("_l_")[1].split("_").length;
        } else {
            return 0L;
        }
    }

    /**
     * just for news
      */

    public String mapSiteUrlForCanonical(String url) {

        var result = "";

        if (url.endsWith("news") || url.endsWith("news/")) {
            result = new StringBuilder(getBaseUrl(url)).append("/posts/all").toString();
        }

        else if (url.contains("news")) {
            var labels = getLabelsFromUrl(url);
            var langs  = getLangsFromUrl(url);

            var str = new StringBuilder(getBaseUrl(url)).append("/posts/");
            labels.stream().forEach(x ->
                    str.append(x).append("_")
            );
            if (str.toString().contains("_")) {
                str.deleteCharAt(str.length() - 1);
            }
            if (!langs.isEmpty()) {
                str.append("_l_");
            }
            langs.stream().forEach(x ->
                    str.append(x).append("_")
            );
            if (str.toString().contains("_")) {
                str.deleteCharAt(str.length() - 1);
            }
            result = str.toString();

        } else if (url.contains("posts")) {
            var labelsCount = getLabelsFromPostsUrl(url);
            //var langsCount  = getLangsFromPostsUrl(url);

            if (labelsCount > 55) {
                result = getBaseUrl(url) + "/posts/all";
            } else {
                result = url;
            }
        }
        return result;
    }










    //TODO toto je co?
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

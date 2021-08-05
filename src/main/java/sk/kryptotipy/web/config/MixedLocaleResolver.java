package sk.kryptotipy.web.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.AbstractLocaleResolver;
import sk.kryptotipy.web.AppProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * locale consumed from URL subdomain first and than from Accepted Lang header with fallback
 */
@AllArgsConstructor
@Component
@Slf4j
public class MixedLocaleResolver extends AbstractLocaleResolver {

    private final AppProperties appProperties;

    @Override
    public Locale resolveLocale(HttpServletRequest request) {

        String url = request.getRequestURL().toString();
        String langFromUrl = this.getLocaleFromUrl(url);
        String result = "";
        if (langFromUrl != null) {
            result = langFromUrl;
        } else {
            String acceptHeader = consumeAcceptLang(request);
            if (acceptHeader != null) {

                String localeString = getJustOneLocale(acceptHeader).toUpperCase();
                if ("CZ".equalsIgnoreCase(localeString)) {
                    result = "SK";
                }
                localeString = localeString.toLowerCase();
                if (!appProperties.getSupportedLangs().contains(localeString)) {
                    result = "US";
                }
            } else {
                // default
                result = "SK";
            }
        }

        // en-gb,en-us
        log.debug("Locale computed = {}", result);
        return new Locale(result.toLowerCase());
    }

    /**
     * Locale can contain more locales as 'en-gb,en-us' for example we need just one
     * @return
     */
    private String getJustOneLocale(String locale) {
        String result = "";
        //sk,cs -> sk
        Matcher matcher = Pattern.compile("(.{0,3}),(.{0,3})").matcher(locale);
        if (matcher.matches()) {
            result = matcher.group(1);
            return result;
        }

        //en-gb,en-us -> us
        matcher = Pattern.compile("(.{0,3})-(.{0,3}),(.{0,3})-(.{0,3})").matcher(locale);
        if (matcher.matches()) {
            result = matcher.group(4);
            return result;
        }

        // xxx-xxx
        matcher = Pattern.compile("(.{0,3})-(.{0,3})").matcher(locale);
        if (matcher.matches()) {
            result = matcher.group(1);
            return result;
        }

        // cs-CZ,cs -> CZ
        matcher = Pattern.compile("(.{0,3})-(.{0,3}),(.{0,3})").matcher(locale);
        if (matcher.matches()) {
            result = matcher.group(2);
            return result;
        }

        return locale;
    }

    private String getLocaleFromUrl(String url) {

        List<String> supportedLangs = appProperties.getSupportedLangs();

        String result = null;

        String[] urlParts = url.split("\\.");
        String langPart = "";
        if (null != urlParts[0]) {
            langPart = urlParts[0].toLowerCase();
        }

        langPart = langPart.replace("https://", "");

        if (supportedLangs.contains(langPart)) {
            result = langPart;
        }
//        else if ("www".equalsIgnoreCase(langPart)) {
//            result = "sk";
//        }
        return result;
    }

    private String consumeAcceptLang(HttpServletRequest request) {
        String userLocale = "";
        String clientLocal = request.getHeader("Accept-Language");
        if (!StringUtils.isEmpty(clientLocal)) {
            userLocale = clientLocal.split(";")[0];
        }
        log.info("Trying to read client locale = {} userLocale = {}", clientLocal, userLocale);
        return userLocale.toLowerCase();
    }

    /**
     *
     * @param locale result from MixedLocaleResolver.resolveLocale(HttpServletRequest)
     * @return
     */
    public List<String> getLangsByUserCountry(Locale locale) {
        List<String> langs = new ArrayList<>();
        String lang = locale.getLanguage();

        switch (lang) {
            case "de": {
                langs.add("DE");
                langs.add("CH");
                break;
            }
            case "pl": {
                langs.add("PL");
                break;
            }
            case "nl": {
                langs.add("NL");
                break;
            }
            case "hu": {
                langs.add("HU");
                break;
            }
            case "en": {
                langs.add("CA");
                langs.add("GB");
                langs.add("US");
                break;
            }
            case "us": {
                langs.add("CA");
                langs.add("GB");
                langs.add("US");
                break;
            }
            case "sk": {
                langs.add("SK");
                langs.add("CZ");
                break;
            }
            case "cs": {
                langs.add("SK");
                langs.add("CZ");
                break;
            }
            default: {
                langs.add("SK");
                langs.add("CZ");
                break;
            }
        }
        log.info("set [{}] by [{}]", langs, lang);
        return langs;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        throw new UnsupportedOperationException("Cannot change sub-domain locale - use a different locale resolution strategy");
    }
}
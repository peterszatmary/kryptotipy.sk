package sk.kryptotipy.web.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.AbstractLocaleResolver;
import sk.kryptotipy.web.AppProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
        var localeResolved = isGoogleBot(request) ?
                resolveLocaleForGoogleBot(request) : resolveLocaleForUser(request);
        log.info("Locale resolved = [{}]", localeResolved);
        return localeResolved;
    }

    private Locale resolveLocaleForGoogleBot(HttpServletRequest request) {
        var url = request.getRequestURL().toString();
        var locale = getLocaleFromUrl(url);
        if (locale == null && url.contains("www")) {
            return new Locale("sk");
        } else if (url.contains("com")) {
            return new Locale("us");
        } else if (locale != null) {
            return new Locale(locale.toLowerCase());
        } else {
           return new Locale("sk");
        }
    }

    private boolean isGoogleBot(HttpServletRequest request) {

        var userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        if (userAgent.contains("Googlebot") || userAgent.contains("www.google.com/bot.html")) {
            log.info("Google bot is here! on site {}", request.getRequestURL().toString());
            return true;
        }
        return false;
    }

    private Locale resolveLocaleForUser(HttpServletRequest request) {
        var url = request.getRequestURL().toString();
        var langFromUrl = this.getLocaleFromUrl(url);
        var result = "SK";
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
        log.debug("Locale computed = [{}]", result);
        return new Locale(result.toLowerCase());
    }


    /**
     * Locale can contain more locales as 'en-gb,en-us' for example we need just one
     * @return
     */
    private String getJustOneLocale(String locale) {
        var result = "";
        //sk,cs -> sk
        var matcher = Pattern.compile("(.{0,3}),(.{0,3})").matcher(locale);
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

        var supportedLangs = appProperties.getSupportedLangs();

        String result = null;

        var urlParts = url.split("\\.");
        var langPart = "";
        if (null != urlParts[0]) {
            langPart = urlParts[0].toLowerCase();
        }

        langPart = langPart.replace("https://", "");

        if (supportedLangs.contains(langPart)) {
            result = langPart;
        }

        return result;
    }


    private String consumeAcceptLang(HttpServletRequest request) {
        var userLocale = "";
        var clientLocal = request.getHeader("Accept-Language");
        if (!StringUtils.isEmpty(clientLocal)) {
            userLocale = clientLocal.split(";")[0];
        }
        log.debug("Trying to read client locale = {} userLocale = {}", clientLocal, userLocale);
        return userLocale.toLowerCase();
    }

    /**
     *
     * @param locale result from MixedLocaleResolver.resolveLocale(HttpServletRequest)
     * @return
     */
    public List<String> getLangsByUserCountry(Locale locale) {
        var langs = new ArrayList<String>();
        var lang = locale.getLanguage();

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
            case "en", "us": {
                langs.add("CA");
                langs.add("GB");
                langs.add("US");
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
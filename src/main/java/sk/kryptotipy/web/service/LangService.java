package sk.kryptotipy.web.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sk.kryptotipy.web.AppProperties;
import sk.kryptotipy.web.api.LangServiceApi;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
@AllArgsConstructor
@Slf4j
@Service
public class LangService implements LangServiceApi {

    private final AppProperties appProperties;

    @Override
    public String getLang(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String langFromUrl = this.getLocaleFromUrl(url);
        String result = "";
        if (langFromUrl != null) {
            result = langFromUrl;
        } else {
            String acceptHeader = consumeAcceptLang(request);
            if (acceptHeader != null) {
                result = acceptHeader;
            } else {
                // default
                result = "sk";
            }
        }
        return result; // en-gb,en-us
    }

    public String getLocaleFromUrl(String url) {

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
     * @param langLower result from getLang(HttpServletRequest)
     * @return
     */
    public List<String> getLangsByUserCountry(String langLower) {
        List<String> langs = new ArrayList<>();
        if (langLower.contains("sk")) {
            langs.add("SK");
            langs.add("CZ");
            log.info("set SK and CZ by sk");
        } else if (langLower.contains("cs")) {
            langs.add("SK");
            langs.add("CZ");
            log.info("set SK and CZ by cs");
        } else if (langLower.contains("de")) {
            langs.add("DE");
            langs.add("CH");
            log.info("set DE and CH by de");
        } else if (langLower.contains("pl")) {
            langs.add("PL");
            log.info("set PL by pl");
        } else if (langLower.contains("hu")) {
            langs.add("HU");
            log.info("set HU by hu");
        } else if (langLower.contains("en")) {
            langs.add("CA");
            langs.add("GB");
            langs.add("US");
            log.info("set CA,GB,US by en");
        } else if (langLower.contains("nl")) {
            langs.add("NL");
            log.info("set NL by nl");
        } else {
            langs.add("SK");
            langs.add("CZ");
            log.info("set SK and CZ by N/A");
        }
        return langs;
    }


    // accept lang has priority over everything
    // for page translation has priority resource bundle

    // if url i18n is set than it has priority
    // if not than accept lang has priority
}

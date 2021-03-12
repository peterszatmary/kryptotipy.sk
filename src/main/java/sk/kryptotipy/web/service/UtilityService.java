package sk.kryptotipy.web.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sk.kryptotipy.web.AppProperties;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class UtilityService {

    private final AppProperties appProperties;

    public String consumeAcceptLang(HttpServletRequest request) {
        String userLocale = "SK";
        String clientLocal = request.getHeader("Accept-Language");
        if (!StringUtils.isEmpty(clientLocal)) {
            userLocale = clientLocal.split(";")[0];
        }
        log.info("Trying to read client locale = {} userLocale = {}", clientLocal, userLocale);
        return userLocale.toLowerCase();
    }

    public ResourceBundle getResourceBundle(HttpServletRequest request) {
        ResourceBundle rb = ResourceBundle.getBundle(
                "messages", new Locale(consumeAcceptLang(request))
        );
        return rb;
    }

    public ResourceBundle getResourceBundle(String lang) {
        //LocaleContextHolder.setLocale(new Locale("hu"));
        ResourceBundle rb = ResourceBundle.getBundle(
                "messages", new Locale(lang == null ? "sk" : lang)
        );
        return rb;
    }

    public String buildSiteUrl(List<String> labels, List<String> langs) {
        StringBuilder siteUrl = new StringBuilder("news?");
        labels.stream().forEach(label -> siteUrl.append("label=").append(label).append("&"));
        langs.stream().forEach(lang -> siteUrl.append("lang=").append(lang).append("&"));
        siteUrl.delete(siteUrl.length() - 1, siteUrl.length());
        return siteUrl.toString();
    }

    public String mapSiteUrl(String url) {

        switch (url) {
            case "news" :
                return "posts/all";
            case "news?label=cardano&lang=SK&lang=CZ" :
                return "posts/cardano";
            case "news?label=tezos&lang=SK&lang=CZ" :
                return "posts/tezos";
            case "news?label=bitcoin&lang=SK&lang=CZ" :
                return "posts/bitcoin";
            case "news?label=ethereum&lang=SK&lang=CZ" :
                return "posts/ethereum";
            case "news?label=exchange&lang=SK&lang=CZ" :
                return "posts/exchange";
            case "news?label=litecoin&lang=SK&lang=CZ" :
                return "posts/litecoin";
            case "news?label=polkadot&lang=SK&lang=CZ" :
                return "posts/polkadot";
            case "news?label=fed&lang=SK&lang=CZ" :
                return "posts/fed";
            case "news?label=ecb&lang=SK&lang=CZ" :
                return "posts/ecb";
            case "news?label=nbs&lang=SK&lang=CZ" :
                return "posts/nbs";
            case "news?label=neo&lang=SK&lang=CZ" :
                return "posts/neo";
            case "news?label=altcoin&lang=SK&lang=CZ" :
                return "posts/altcoin";
            case "news?label=monero&lang=SK&lang=CZ" :
                return "posts/monero";
            case "news?label=dash&lang=SK&lang=CZ" :
                return "posts/dash";
            case "news?label=vechain&lang=SK&lang=CZ" :
                return "posts/vechain";
            case "news?label=algorand&lang=SK&lang=CZ" :
                return "posts/algorand";
            case "news?label=ontology&lang=SK&lang=CZ" :
                return "posts/ontology";
            case "news?label=cosmos&lang=SK&lang=CZ" :
                return "posts/cosmos";
            case "news?label=binance&lang=SK&lang=CZ" :
                return "posts/binance";
            case "news?label=dag&lang=SK&lang=CZ" :
                return "posts/dag";
            case "news?label=exodus&lang=SK&lang=CZ" :
                return "posts/exodus";
            case "news?label=fork&lang=SK&lang=CZ" :
                return "posts/fork";
            case "news?label=ico&lang=SK&lang=CZ" :
                return "posts/ico";
            case "news?label=kraken&lang=SK&lang=CZ" :
                return "posts/kraken";
            case "news?label=coinbase&lang=SK&lang=CZ" :
                return "posts/coinbase";
            case "news?label=halving&lang=SK&lang=CZ" :
                return "posts/halving";
            case "news?label=bitfinex&lang=SK&lang=CZ" :
                return "posts/bitfinex";
            case "news?label=bittrex&lang=SK&lang=CZ" :
                return "posts/bittrex";
            case "news?label=ripple&lang=SK&lang=CZ" :
                return "posts/ripple";
            case "news?label=wallets&lang=SK&lang=CZ" :
                return "posts/wallets";
            case "news?label=coinmarketcap&lang=SK&lang=CZ" :
                return "posts/coinmarketcap";
            case "news?label=not-categorized&lang=SK&lang=CZ" :
                return "posts/not-categorized";
        }

        if (isLangUrl(url, "SK")) {
            return "posts-by-i18n/SK";
        }

        if (isLangUrl(url, "CZ")) {
            return "posts-by-i18n/CZ";
        }
        return url;
    }



    private boolean isLangUrl(String newUrl, String lang) {
        return (newUrl.contains("lang=" + lang) &&
                (
                        newUrl.contains("bitcoin") &&
                                newUrl.contains("cryptocurrency") &&
                                newUrl.contains("altcoin")
                ))
                || (newUrl.contains("lang=" + lang) && (newUrl.split("label").length == 1));
    }


    //TODO
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

    public String getLocaleFromUrl(String url) {

        List<String> supportedLangs = appProperties.getSupportedLangs();
        String DEFAULT_LANG = "sk";

        String result = DEFAULT_LANG;

        String[] urlParts = url.split("\\.");
        String langPart = DEFAULT_LANG;
        if (null != urlParts[0]) {
            langPart = urlParts[0].toLowerCase();
        }

        langPart = langPart.replace("https://", "");

        if (supportedLangs.contains(langPart)) {
            result = langPart;
        }
        LocaleContextHolder.setLocale(new Locale(result)); //TODO not sure maybe delete it
        return result;
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

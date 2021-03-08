package sk.kryptotipy.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sk.kryptotipy.web.api.PageDataServiceApi;
import sk.kryptotipy.web.api.PostServiceApi;
import sk.kryptotipy.web.repository.ArticleRepository;
import sk.kryptotipy.web.data.Post;
import sk.kryptotipy.web.repository.LabelRepository;
import sk.kryptotipy.web.repository.ServiceRepository;
import sk.kryptotipy.web.service.BtcPriceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Controller
public class WebController {

    private final PageDataServiceApi pageData;
    private final PostServiceApi postService;
    private final AppProperties appProperties;
    private final BtcPriceService btcPriceService;
    private final ServiceRepository serviceRepo;
    private final ArticleRepository articleRepo;
    private final LabelRepository labelRepo;
    private static final String MESSAGE_404_NOT_FOUND = "404 Stránka nenájdená";

    @RequestMapping("/")
    public String index(Map<String, Object> model, HttpServletRequest request) {

        try {
            model.put("active_index", "active");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("active_krypto_learning", "");
            model.put("site", "");
            List<String> langs = new ArrayList<>();
            setLangsByUserCountry(langs, request);
            model.put("posts", this.postService.getNewestPosts(langs));
            model.put("articles", articleRepo.getKryptotipyContentView());
            model.put("external_services", serviceRepo.getExternalServicesViews());
            log.info("index.html");
            pageData.set(model, "Agregátor najčerstvejších článkov o kryptomenách z celého sveta.");
            model.put("btc_price", getBtcPrice());
            model.put("page_description", pageData.getPageDescription());
            return "index";

        } catch (Exception ex) {
            log.warn("index : " + ex.getMessage());
            pageData.set(model, MESSAGE_404_NOT_FOUND);
            return "redirect:/error.html";
        }
    }

    @RequestMapping("/posts-by-i18n/{lang}")
    public String postsByI18n(Map<String, Object> model,
                              @PathVariable("lang") String lang, HttpServletRequest request) {
       return news(null, lang, model, request);
    }


    @RequestMapping("/posts/{label}")
    public String posts(Map<String, Object> model,
                        @PathVariable("label") String label, HttpServletRequest request) {
        return news(label, null,  model, request);
    }


    @RequestMapping(value="/news", method = RequestMethod.GET)
    public String news(String labell, String lang, Map<String, Object> model,
                       HttpServletRequest request) {

        List<String> langs = new ArrayList<>();


        List<String> allLabels = labelRepo.getAllLabels()
                .stream()
                .map(label -> label.getName())
                .collect(Collectors.toList());

        final Integer ALL_LABELS = allLabels.size();
        final Integer ALL_LANGS  = 9; //TODO from DB


        List<String> labels = new ArrayList<>();

        if (null != labell) {
            labels =
                    labell != null && !labell.contains(",") && labels.contains(labell) ?
                            Arrays.asList(labell) : Arrays.asList(labell.split(","));
        }

        if (lang != null && !lang.contains(",") && !langs.contains(lang)) {
            langs.add(lang);
        }

        Map<String, String[]> parameterMap = request.getParameterMap();

        if (langs.isEmpty() && null == parameterMap.get("lang")) {
            setLangsByUserCountry(langs, request);
        } else {
            langs = new ArrayList<>(Arrays.asList(parameterMap.get("lang")));
        }

        if (labels.isEmpty()) {
            labels =
                    checkAndInitCheckBoxInput("label", parameterMap,  allLabels);
        }

        final Long from = 0L;
        final Long to = appProperties.getPageSize();

        if (labels.contains("all")) {
            labels.remove("all");
        }

        List<Post> posts;
        if (labels.size() == 1 && langs.size() == ALL_LANGS) {
            posts = this.postService.getPostsByLabel(labels.get(0), from, to);
        } else if (labels.size() == ALL_LABELS && langs.size() == 1) {
            posts = this.postService.getPostsByI18n(langs.get(0), from, to);
        } else if (labels.size() == ALL_LABELS && langs.size() == ALL_LANGS) {
            posts = this.postService.getPostsByLabelAll(from, to);
        }
        else {
            posts = this.postService.getPostsByLabelsAndLangs(labels, langs, from, to);//TODO az to opravis staci ti jeden SQL QUERY
        }

        setNewsRenderingTexts(labels, langs, model, ALL_LABELS);

        log.info("Posts count rendered = {}", posts.size());
        model.put("posts", posts);
        model.put("content_path", "fragments/content/posts");
        model.put("checked_labels", labels);
        model.put("checked_langs", langs);
        model.put("active_index", "");
        model.put("active_posts", "active");
        model.put("active_dictionary", "");
        model.put("active_external_services", "");
        model.put("active_did_you_know", "");
        model.put("active_krypto_learning", "");

        log.info("Posts filtered by labels count = {} labels = {} langs count = {} and langs = {}",
                labels.size(), labels, langs.size(), langs);
        return "non-index";
    }

    private void setLangsByUserCountry(List<String> langs, HttpServletRequest request) {

        String userLocale = "SK";
        String clientLocal = request.getHeader("Accept-Language");
        if (!StringUtils.isEmpty(clientLocal)) {
            userLocale = clientLocal.split(";")[0];
        }
        log.info("Trying to read client locale = {} userLocale = {}", clientLocal, userLocale);

        String langLower = userLocale.toLowerCase();

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
        } else {
            langs.add("SK");
            langs.add("CZ");
            log.info("set SK and CZ by N/A");
        }
    }


    private void setNewsRenderingTexts(List<String> labels, List<String> langs,
                                       Map<String, Object> model,
                                       Integer allLabelCount) {
        String label_render;
        String customPageTitle;
        if (langs.size() == 1) {
            customPageTitle = getI18nText(langs.get(0));
            label_render = customPageTitle.split(" ")[0];
        } else if (labels.size() == 1) {
            label_render = mapLabelForRendering(labels.get(0));
            customPageTitle = "Krypto novinky na tému " + StringUtils.capitalize(label_render);
        } else if (labels.size() == allLabelCount) { //TODO trasparency for all checkbox later

            customPageTitle = "Všetky krypto novinky";
            label_render = "Všetky";
        } else {
            String pageTitle = "Krypto novinky na témy ";

            StringBuilder labelsStr = new StringBuilder();
            labelsStr.append("[ ");
            labels.stream().forEach(label -> labelsStr.append(StringUtils.capitalize(label)).append(", "));
            label_render = labelsStr
                    .deleteCharAt(labelsStr.length() - 1)
                    .deleteCharAt(labelsStr.length() - 1)
                    .append(" ]")
                    .toString();
            customPageTitle = pageTitle + label_render;//"Vybrané";
        }

        pageData.set(model, customPageTitle);
        model.put("site", mapSiteUrl(buildSiteUrl(labels, langs)));
        model.put("label_render", customPageTitle);
        model.put("label_render_path", StringUtils.capitalize(label_render));
    }

    @RequestMapping("/external-services")
    public String externalServices(Map<String, Object> model) {

        try {

            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "active");
            model.put("active_did_you_know", "");
            model.put("active_krypto_learning", "");
            model.put("site", "external-services");
            model.put("content_path","fragments/content/external_services");
            model.put("services", serviceRepo.getExternalServices());
            model.put("link_is_not_internal", true);

            log.info("external_services.html");
            pageData.set(model, "Externé krypto služby");
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 19, 0, 0) + "");
            model.put("page_description", "Služby, ktoré približujú používateľovi kryptomeny a krypto svet vznikajú po celom svete a je ich stále viac. Na stránkach kryptotipy.sk nájdete súhrn tých najzaujímavejších.");
            return "non-index";

        } catch (Exception ex) {
            log.warn("external-services : " + ex.getMessage());
            pageData.set(model, MESSAGE_404_NOT_FOUND);
            return "redirect:/error";
        }
    }

    @RequestMapping("/cooperation")
    public String cooperation(Map<String, Object> model) {

        try {

            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("active_krypto_learning", "");
            model.put("site", "cooperation");
            model.put("content_path","fragments/content/cooperation");
            model.put("services", serviceRepo.getCooperationServices());

            log.info("cooperation.html");
            pageData.set(model, "Spolupráca");
            model.put("page_description", "Spolupráca s portálom kryptotipy.sk. " +
                    "Výmena spätných odkazov (Backlink building), zobrazenie odkazov na " +
                    "vaše články na kryptotipy.sk, článok o kryptotipy.sk, alebo iný druh " +
                    "spolupráce.");
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 16, 0, 0) + "");
            return "non-index";

        } catch (Exception ex) {
            log.warn("cooperation : " + ex.getMessage());
            pageData.set(model, MESSAGE_404_NOT_FOUND);
            return "redirect:/error";
        }
    }


    @RequestMapping("/krypto-learning")
    public String cryptoLearning(Map<String, Object> model) {

        try {

            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("active_krypto_learning", "active");
            model.put("site", "krypto-learning");
            model.put("content_path","fragments/content/krypto_learning");

            model.put("articles", articleRepo.getKryptotipyContent());
            model.put("link_is_not_internal", false);

            log.info("krypto_learning.html");
            pageData.set(model, "Krypto learning");
            model.put("page_description", "Kryptotipy.sk nielenže agregujú zaujímavý " +
                    "krypto obsah z celého sveta, ale vytvára aj vlastný obsah. Čítajte " +
                    "krypto články z pera kryptotipy.sk");
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 17, 0, 0) + "");
            return "non-index";

        } catch (Exception ex) {
            log.warn("krypto-learning : " + ex.getMessage());
            pageData.set(model, MESSAGE_404_NOT_FOUND);
            return "redirect:/error";
        }
    }

    @RequestMapping("/dictionary")
    public String dictionary(Map<String, Object> model) {

        try {

            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "active");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("site", "dictionary");
            model.put("active_krypto_learning", "");
            model.put("content_path", "fragments/content/dictionary");

            log.info("dictionary.html");
            pageData.set(model, "Krypto slovník");
            model.put("page_description",
                    "Krypto slovník pomáha zorientovať sa v novom krypto svete a jeho krypto pojmoch. " +
                            "Vysvetľuje najbežnejšie / najdôležitejšie krypto pojmy tvoriace doménu kryptomien.");
            model.put("page_date", LocalDateTime.of(2020, Month.DECEMBER, 22, 0, 0 ) + "");
            return "non-index";

        } catch (Exception ex) {
            log.warn("dictionary : " + ex.getMessage());
            pageData.set(model, MESSAGE_404_NOT_FOUND);
            return "redirect:/error";
        }
    }

    @RequestMapping("/robots.txt")
    public String robotsTxt(Map<String, Object> model) {
        log.info("robots.txt");
        return "robots.txt";
    }


    @RequestMapping("/article/{postTitle}")
    public String post(Map<String, Object> model,
                       @PathVariable("postTitle") String postTitle) {
        try {
            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("active_krypto_learning", "active");
            model.put("site", "article/" + postTitle);

            model.put("content_path","fragments/content/" + postTitle);

            log.info("article = {}", postTitle);
            pageData.set(model, pageData.getPostTitle(postTitle));

            model.put("page_title", pageData.getPostTitle(postTitle));
            model.put("page_description", pageData.getPostDescription(postTitle));
            model.put("page_image", pageData.getPostImage(postTitle));
            model.put("page_image_width", pageData.getPostImageWidth(postTitle));
            model.put("page_image_height", pageData.getPostImageHeight(postTitle));
            model.put("page_date", pageData.getPostDate(postTitle));
            return "non-index";

        } catch (Exception ex) {
            log.warn("{} : {}", postTitle, ex.getMessage());
            pageData.set(model, MESSAGE_404_NOT_FOUND);
            return "redirect:/error";
        }
    }

    @RequestMapping("/did-you-know")
    public String didYouKnow(Map<String, Object> model) {

        try {

            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "active");
            model.put("active_krypto_learning", "");
            model.put("site", "did-you-know");
            model.put("content_path","fragments/content/did_you_know");
            pageData.set(model, "Vedeli ste, že");
            log.info("did_you_know.html");
            model.put("page_description", "Krypto sekcia, ktorá prináša krypto zaujímavosti " +
                    "o rôznych kryptomenách z rôznych časových období, nielen aktuality.");
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 22, 0, 0) + "");
            return "non-index";

        } catch (Exception ex) {
            log.warn("dictionary : " + ex.getMessage());
            pageData.set(model, MESSAGE_404_NOT_FOUND);
            return "redirect:/error";
        }
    }

    @RequestMapping("/one-post-frame/{protocol}/{www}/{source_rendered}/{url_path}")
    public String onePostFrame(Map<String, Object> model,
            @PathVariable(value = "source_rendered", required = false) String sourceRendered,
            @PathVariable(value = "url_path", required = false) String urlPath,
            @PathVariable(value = "protocol", required = false) String protocol,
                               @PathVariable(value = "www", required = false) String www,
                               HttpServletResponse response
                               ) {
        try {
            model.put("active_index", "");
            model.put("active_posts", "active");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("active_krypto_learning", "");
            model.put("site", "one-post-frame");

            String wwwStr = "www".equals(www) ? "www." : "";
            String protocolStr = "hs".equals(protocol) ? "https" : "http";
            String additional =
                    "krypto.sk".equals(sourceRendered) ||
                            "krypto-meny.sk".equals(sourceRendered) ? "/l" : "";
            String postUrl = protocolStr + "://" + wwwStr + sourceRendered + additional + "/" + urlPath;


            Post post = this.postService.getPostByUrl(postUrl);
            System.out.println("labels=" + post.getLabels());

            post.setViews(post.getViews() + 1);
            postService.updatePostViews(post.getUrl(), post.getViews());
            log.info("New view for post = {}, views = {}", post.getUrl(), post.getViews());

            model.put("one_post", post);

            //TODO kryptotnovinky
            //response.setHeader("X-Frame-Options", "https://www.kryptonovinky.sk");
            //response.setHeader("Content-Security-Policy", "connect-src https://www.kryptonovinky.sk;connect-src https://google-analytics.com");

            pageData.set(model, urlPath.replace("-"," ") + " - " + sourceRendered);

            if (post.useRedirectToSourceSite()) {
                response.setHeader("Location", post.getUrl());
                response.setStatus(302);

            }
            return "one-post";

        } catch (Exception ex) {
            log.warn("one page frame : sourceRendered={}, urlPath={}, protocol={}, www={}",
                    sourceRendered,
                    urlPath,
                    protocol,
                    www,
                    ex.getMessage()
            );
            log.warn(ex.getCause() + "" + ex.getStackTrace());
            pageData.set(model, "404 Stránka nenájdená");
            return "redirect:/error";
        }
    }


    private String getBtcPrice() {
        String btcPrice = String.valueOf(btcPriceService.getLatestPrice());
        return btcPrice.substring(0, btcPrice.length() - 2);
    }

    private String getI18nText(String i18n) {
        switch(i18n) {
            case "SK": return "Slovenské krypto novinky";
            case "CZ": return "České krypto novinky";
            case "CH": return "Švajčiarské krypto novinky";
            case "DE": return "Nemecké krypto novinky";
            case "PL": return "Poľské krypto novinky";
            case "HU": return "Maďarské krypto novinky";
            case "GB": return "Britské krypto novinky";
            case "CA": return "Kanadské krypto novinky";
            case "US": return "Americké krypto novinky";
            default : return "Nezaradené krypto novinky";
        }
    }

    private String buildSiteUrl(List<String> labels, List<String> langs) {
        StringBuilder siteUrl = new StringBuilder("news?");
        labels.stream().forEach(label -> siteUrl.append("label=").append(label).append("&"));
        langs.stream().forEach(lang -> siteUrl.append("lang=").append(lang).append("&"));
        siteUrl.delete(siteUrl.length() - 1, siteUrl.length());
        return siteUrl.toString();
    }

    private String mapSiteUrl(String url) {

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

    private String mapLabelForRendering(String label) {
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

    private List<String> checkAndInitCheckBoxInput(
            String inputKey,
            Map<String, String[]> parameterMap,
            List<String> initValues
    ) {
        //TODO from DB
        return (null == parameterMap.get(inputKey)) ?
                initValues : new ArrayList<>(Arrays.asList(parameterMap.get(inputKey)));
    }
}

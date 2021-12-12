package sk.kryptotipy.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sk.kryptotipy.web.api.PageDataServiceApi;
import sk.kryptotipy.web.api.PostServiceApi;
import sk.kryptotipy.web.component.UrlComponent;
import sk.kryptotipy.web.config.MixedLocaleResolver;
import sk.kryptotipy.web.data.db.Post;
import sk.kryptotipy.web.repository.ArticleRepository;
import sk.kryptotipy.web.repository.db.LabelRepository;
import sk.kryptotipy.web.repository.db.PostJdbcRepository;
import sk.kryptotipy.web.repository.ServiceRepository;
import sk.kryptotipy.web.service.PriceService;
import sk.kryptotipy.web.service.SitemapService;
import sk.kryptotipy.web.service.UtilityService;

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
    private final UtilityService utilityService;
    private final PriceService priceService;
    private final ServiceRepository serviceRepo;
    private final ArticleRepository articleRepo;
    private final LabelRepository labelRepo;
    private final MixedLocaleResolver mixedLocaleResolver;
    private final PostJdbcRepository postJdbcRepository;
    private final UrlComponent urlComponent;
    private final SitemapService sitemapService;


    @GetMapping("/")
    public String index(Map<String, Object> model,
                        HttpServletRequest request) {

        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(resolvedLocale);

        try {
            addCookies(model, request);
            makeLinkActive(model, "index");

            var langs = mixedLocaleResolver.getLangsByUserCountry(resolvedLocale);
            log.info("index.html");

            model.put("site", "");
            model.put("posts", postService.getNewestPosts(langs));
            model.put("articles", articleRepo.getKryptotipyContentView(rbFromUrl));
            model.put("external_services", serviceRepo.getExternalServicesViews(rbFromUrl));
            pageData.set(model,
                    resolvedLocale.getLanguage(),
                    rbFromUrl.getString("page.index.description"),
                    rbFromUrl.getString("default.descr"),
                    request);
            model.put("btc_price", getBtcPrice());
            model.put("page_description", rbFromUrl.getString("page.index.description.long"));
            return "index";

        } catch (Exception ex) {
            log.warn("index : " + ex.getMessage());
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"), request);
            return "redirect:/error.html";
        }
    }

    @GetMapping("/posts-by-i18n/{lang}")
    public String postsByI18n(Map<String, Object> model,
                              @PathVariable("lang") String lang, HttpServletRequest request) {
       return news(null, lang, model, request);
    }


    @GetMapping("/posts/{label}")
    public String posts(Map<String, Object> model,
                        @PathVariable("label") String label, HttpServletRequest request) {
        return news(label, null,  model, request);
    }


    @GetMapping(value="/news")
    public String news(String labell, String lang, Map<String, Object> model,
                       HttpServletRequest request) {

        // labell is one leabel containing all labels and langs
        // posts/labell -> posts/bitcoin_avalanche_l_us_sk
        // l show where langs starts

        List<String> langs = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<Post> posts;
        final var from = 0L;
        final var to = appProperties.getPageSize();

        var allLabels = labelRepo.getAllLabels()
                .stream()
                .map(label -> label.getName())
                .collect(Collectors.toList());
        var ALL_LABELS = allLabels.size();

        if (labell != null && labell.contains("_l_")) {
            var langss = labell.split("_l_")[1];
            var labelss = labell.split("_l_")[0];
            langs = Arrays.asList(langss.toUpperCase().split("_"));
            labels = Arrays.asList(labelss.split("_"));
        } else {

            final var parameterMap = request.getParameterMap();
            addCookies(model, request);
            makeLinkActive(model, "posts");

            if (null != labell) {
                labels =
                        labell != null && !labell.contains("_") && labels.contains(labell) ?
                                new ArrayList<>(List.of(labell)) : new ArrayList<>(List.of(labell.split("_")));
            }

            if (lang != null && !lang.contains("_") && !langs.contains(lang)) {
                langs.add(lang);
            }

            if (langs.isEmpty() && null == parameterMap.get("lang")) {
                langs = mixedLocaleResolver.getLangsByUserCountry(mixedLocaleResolver.resolveLocale(request));
            } else {
                if (null == parameterMap.get("lang")) {
                    langs = new ArrayList<>(Arrays.asList(lang));
                } else {
                    langs = new ArrayList<>(Arrays.asList(parameterMap.get("lang")));
                }
            }

            if (labels.isEmpty()) {
                labels =
                        utilityService.checkAndInitCheckBoxInput("label", parameterMap,  allLabels);
            }
        }

        if (labels.contains("all")) {
            labels.remove("all");
            posts = postJdbcRepository.getNewsAll(langs);
        } else {
            posts = postJdbcRepository.getNews(labels, langs, from, to);
        }
        setNewsRenderingTexts(labels, langs, model, ALL_LABELS, request);

        model.put("posts", posts);
        model.put("content_path", "fragments/content/posts");
        model.put("checked_labels", labels);
        model.put("price_widget", priceService.mapCoinToCoinLibId(labels));
        model.put("checked_langs", langs);

        log.info("Posts count rendered = {}", posts.size());
        log.info("Posts filtered by labels count = {} labels = {} langs count = {} and langs = {}",
                labels.size(), labels, langs.size(), langs);
        return "non-index";
    }

    @GetMapping("/external-services")
    public String externalServices(Map<String, Object> model, HttpServletRequest request) {

        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(
               resolvedLocale 
        );
        addCookies(model, request);
        makeLinkActive(model, "external_services");

        try {

            model.put("site", "external-services");
            model.put("content_path","fragments/content/external_services");
            model.put("services", serviceRepo.getExternalServices(rbFromUrl));
            model.put("link_is_not_internal", true);

            log.info("external_services.html");
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("external.services.h1"), rbFromUrl.getString("default.descr"), request);
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 19, 0, 0) + "");
            model.put("page_description", rbFromUrl.getString("external.description"));
            return "non-index";

        } catch (Exception ex) {
            log.warn("external-services : " + ex.getMessage());
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"), request);
            return "redirect:/error";
        }
    }


    @GetMapping("/cooperation")
    public String cooperation(Map<String, Object> model, HttpServletRequest request) {

        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(
                resolvedLocale
        );
        addCookies(model, request);
        makeLinkActive(model, "");
        try {

            model.put("site", "cooperation");
            model.put("content_path","fragments/content/cooperation");
            model.put("services", serviceRepo.getCooperationServices(rbFromUrl));
            makeLinkActive(model, "cooperation");

            log.info("cooperation.html");
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("footer.cooperation"), rbFromUrl.getString("default.descr"), request);
            model.put("page_description", rbFromUrl.getString("cooperation.descr"));
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 16, 0, 0) + "");
            return "non-index";

        } catch (Exception ex) {
            log.warn("cooperation : " + ex.getMessage());
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"), request);
            return "redirect:/error";
        }
    }


    @GetMapping("/krypto-learning")
    public String cryptoLearning(Map<String, Object> model, HttpServletRequest request) {

        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(
                resolvedLocale
        );
        addCookies(model, request);
        makeLinkActive(model, "krypto_learning");
        try {
            model.put("site", "krypto-learning");
            model.put("content_path","fragments/content/krypto_learning");

            model.put("articles", articleRepo.getKryptotipyContent(rbFromUrl));
            model.put("link_is_not_internal", false);

            log.info("krypto_learning.html");
            pageData.set(model, resolvedLocale.getLanguage(),"Learning", rbFromUrl.getString("default.descr"), request);
            model.put("page_description", rbFromUrl.getString("crypto.learning.descr"));
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 17, 0, 0) + "");
            return "non-index";

        } catch (Exception ex) {
            log.warn("krypto-learning : " + ex.getMessage());
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"), request);
            return "redirect:/error";
        }
    }

    @GetMapping("/dictionary")
    public String dictionary(Map<String, Object> model, HttpServletRequest request) {

        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(resolvedLocale);
        addCookies(model, request);
        makeLinkActive(model, "dictionary");
        try {
            model.put("site", "dictionary");
            model.put("active_krypto_learning", "");
            model.put("content_path", "fragments/content/dictionary");

            log.info("dictionary.html");
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("dictionary.title"), rbFromUrl.getString("default.descr"), request);
            model.put("page_description", rbFromUrl.getString("dictionary.d"));
            model.put("page_date", LocalDateTime.of(2020, Month.DECEMBER, 22, 0, 0 ) + "");
            return "non-index";

        } catch (Exception ex) {
            log.warn("dictionary : " + ex.getMessage());
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"), request);
            return "redirect:/error";
        }
    }

    @GetMapping("/robots.txt")
    public String robotsTxt() {
        log.info("robots.txt");
        return "robots.txt";
    }

    @GetMapping("/ads.txt")
    public String adsTxt() {
        log.info("ads.txt");
        return "ads.txt";
    }

    @GetMapping(
            value = "/sitemap.xml",
            produces = { "application/xml", "text/xml" }
            )
    @ResponseBody
    public void getSitemap(HttpServletRequest request, HttpServletResponse response) throws Exception {

        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var siteMapLang = resolvedLocale.getLanguage().toLowerCase();
        if (siteMapLang.equalsIgnoreCase("us")) {
            siteMapLang = "en";
        }
        if (siteMapLang.equalsIgnoreCase("sk")) {
            siteMapLang = "www";
        }

        log.info("Locale was found={}, sitemap for lang {} rendered", resolvedLocale, siteMapLang);

		response.addHeader("content-type", "application/xml; charset=utf-8");
		response.setStatus(200);

        var out = response.getWriter();
		out.println(sitemapService.create(siteMapLang));
    }


    @GetMapping("/article/{postTitle}")
    public String post(Map<String, Object> model,
                       @PathVariable("postTitle") String postTitle, HttpServletRequest request) {
        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(
                resolvedLocale
        );
        addCookies(model, request);
        makeLinkActive(model, "krypto_learning");
        try {

            model.put("site", "article/" + postTitle);
            model.put("content_path","fragments/content/" + postTitle);

            log.info("article = {}", postTitle);
            pageData.set(model, resolvedLocale.getLanguage(), pageData.getPostTitle(postTitle), rbFromUrl.getString("default.descr"), request);

            model.put("page_title", pageData.getPostTitle(postTitle));
            model.put("page_description", pageData.getPostDescription(postTitle));
            model.put("page_image", pageData.getPostImage(postTitle));
            model.put("page_image_width", pageData.getPostImageWidth(postTitle));
            model.put("page_image_height", pageData.getPostImageHeight(postTitle));
            model.put("page_date", pageData.getPostDate(postTitle));
            return "non-index";

        } catch (Exception ex) {
            log.warn("{} : {}", postTitle, ex.getMessage());
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"), request);
            return "redirect:/error";
        }
    }

    @GetMapping("/did-you-know")
    public String didYouKnow(Map<String, Object> model, HttpServletRequest request) {

        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(
                resolvedLocale
        );
        addCookies(model, request);
        makeLinkActive(model, "did_you_know");
        try {
            model.put("site", "did-you-know");
            model.put("content_path", "fragments/content/did_you_know");
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("did.you.know") , rbFromUrl.getString("default.descr"), request);
            log.info("did_you_know.html");
            model.put("page_description", rbFromUrl.getString("did.you.know.descr"));
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 22, 0, 0) + "");
            return "non-index";

        } catch (Exception ex) {
            log.warn("dictionary : " + ex.getMessage());
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"), request);
            return "redirect:/error";
        }
    }

    @GetMapping("/one-post-frame/{hashed_id}")
    public String onePostFrame(
            Map<String, Object> model,
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("hashed_id") String hashedId
       ) {
        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(
                resolvedLocale
        );
        makeLinkActive(model, "posts");
        try {
            model.put("site", "one-post-frame");

            var id = utilityService.decodeKryptotipyHash(hashedId);
            var post = this.postService.getPostsById(id);

            post.setViews(post.getViews() + 1);
            postService.updatePostViews(post.getUrl(), post.getViews());
            log.info("Post hashed id = {}, id = {}", hashedId, id);
            log.info("New view for post = {}, views = {}", post.getUrl(), post.getViews());

            model.put("one_post", post);
            pageData.set(model, resolvedLocale.getLanguage(), post.getTitle(), rbFromUrl.getString("default.descr"), request);

            var pageImage = "/assets/post-images/" + post.getLabels().get(0).getName().toLowerCase() + "/images_1.jpg";

            model.put("page_image", pageImage);

            if (post.useRedirectToSourceSite()) {
                response.setHeader("Location", post.getUrl());
                response.setStatus(302);
            }
            return "one-post";

        } catch (Exception ex) {
            log.warn("one page frame : hashedId={}",
                    hashedId,
                    ex.getMessage()
            );
            log.warn(ex.getCause() + "" + ex.getStackTrace());
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"), request);
            return "redirect:/error";
        }
    }


    private String getBtcPrice() {
        var btcPrice = String.valueOf(priceService.getLatestPrice());
        return btcPrice.substring(0, btcPrice.length() - 2);
    }

    private void addCookies(Map<String, Object> model, HttpServletRequest request) {
        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var cookies = resolvedLocale.getLanguage().toLowerCase();
        model.put("cookies_lang", cookies);
        log.debug("cookies '{}'", cookies);
    }

    private void makeLinkActive(Map<String, Object> model, String activeLink) {
        var links = new ArrayList<>(Arrays.asList(
                "index", "posts", "dictionary", "external_services", "did_you_know", "krypto_learning", "cooperation"
        ));
        if (!links.contains(activeLink)) {
            activeLink = "index";
        }

        model.put("active_" + activeLink, "active");
        links.remove(activeLink);
        links.stream().forEach(x -> model.put("active_" + x, ""));
    }

    private void setNewsRenderingTexts(List<String> labels, List<String> langs,
                                       Map<String, Object> model,
                                       Integer allLabelCount, HttpServletRequest request) {
        var label_render = "";
        var customPageTitle = "";
        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(resolvedLocale);

        if (langs.size() == 1) {
            customPageTitle = rbFromUrl.getString("crypto.news.title." + langs.get(0).toLowerCase());
            label_render = customPageTitle.split(" ")[0];
        } else if (labels.size() == 1) {
            label_render =  rbFromUrl.getString("label.title." + labels.get(0).toLowerCase());
            customPageTitle = rbFromUrl.getString("crypto.news.theme") + StringUtils.capitalize(label_render);

        } else if (labels.size() == allLabelCount) {
            customPageTitle = rbFromUrl.getString("crypto.news.title.all");
            label_render = rbFromUrl.getString("filter.component.all");
        } else {
            var pageTitle = rbFromUrl.getString("crypto.news.themes");
            var labelsStr = new StringBuilder();
            label_render = labelsToRender(labels, labelsStr, rbFromUrl);
            customPageTitle = pageTitle + StringUtils.capitalize(label_render);
        }

        pageData.set(model, resolvedLocale.getLanguage(), customPageTitle, rbFromUrl.getString("default.descr"), request);
        model.put("page_image", getOgImageForNews(labels));
        model.put("site", urlComponent.mapSiteUrl(utilityService.buildSiteUrl(labels, langs), labels, langs));
        model.put("label_render", customPageTitle);
        model.put("label_render_path", StringUtils.capitalize(label_render));
    }


    private String labelsToRender(List<String> labels, StringBuilder labelsStr, ResourceBundle rb) {
//        labels = labels.stream()
//                .filter(x -> !x.equals("l"))
//                .collect(Collectors.toList());

        if (labels.size() > 5) {
            return l(labels, 5, " ...",labelsStr, rb);
        } else {
            return l(labels, labels.size(), "",labelsStr, rb);
        }
    }

    private String l(List<String> labels, Integer limit, String appendToEnd, StringBuilder labelsStr, ResourceBundle rb) {
        labels.stream()
                .limit(limit)
                //.map(x -> rb.getString("label.title." + x.toLowerCase()))//TODO
                .forEach(label -> labelsStr.append(rb.getString("label.title." + label.toLowerCase()))
                        .append(", "));

        if (!labels.isEmpty()) {
            return labelsStr.deleteCharAt(labelsStr.length() - 1)
                    .deleteCharAt(labelsStr.length() - 1)
                    .append(appendToEnd)
                    .toString();
        } else {
            return "all";
        }
    }




    private String getOgImageForNews(List<String> labels) {
        if (labels.size() == 1) {
            return "/assets/post-images/" + labels.get(0) + "/images_7.jpg";
        }
        return "/assets/post-images/exchange/images_2.jpg";
    }


    //    @RequestMapping("/donate")
//    public String donate(Map<String, Object> model, HttpServletRequest request) {
//
//        ResourceBundle rbFromUrl = utilityService.getResourceBundle(
//                mixedLocaleResolver.resolveLocale(request)
//        );
//        addCookies(model, request);
//        makeLinkActive(model, "");
//        try {
//            model.put("site", "donate");
//            model.put("content_path","fragments/content/donate");
//            model.put("donates", postService.getAllDonates());
//
//            log.info("donate.html");
//            pageData.set(model, rbFromUrl.getString("footer.donate"), rbFromUrl.getString("default.descr"), request);
//            model.put("page_description", rbFromUrl.getString("donate.descr"));
//            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 16, 0, 0) + "");
//            return "non-index";
//
//        } catch (Exception ex) {
//            log.warn("donate : " + ex.getMessage());
//            pageData.set(model, rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"), request);
//            return "redirect:/error";
//        }
//    }
}



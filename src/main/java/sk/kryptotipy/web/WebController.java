package sk.kryptotipy.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sk.kryptotipy.web.api.PageDataServiceApi;
import sk.kryptotipy.web.api.PostServiceApi;
import sk.kryptotipy.web.data.SiteEntry;
import sk.kryptotipy.web.data.Source;
import sk.kryptotipy.web.repository.ArticleRepository;
import sk.kryptotipy.web.data.Post;
import sk.kryptotipy.web.repository.LabelRepository;
import sk.kryptotipy.web.repository.ServiceRepository;
import sk.kryptotipy.web.repository.SiteRepository;
import sk.kryptotipy.web.service.BtcPriceService;
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
    private final BtcPriceService btcPriceService;
    private final ServiceRepository serviceRepo;
    private final ArticleRepository articleRepo;
    private final LabelRepository labelRepo;
    private final SitemapService sitemapService;

    private final SiteRepository s;

    @RequestMapping("/")
    public String index(Map<String, Object> model,
                        HttpServletRequest request) {

        String lang = utilityService.getLocaleFromUrl(request.getRequestURL().toString());
        ResourceBundle rbFromUrl = utilityService.getResourceBundle(lang);
        try {

            model.put("active_index", "active");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("active_krypto_learning", "");
            model.put("cookies_lang", lang);
            model.put("site", "");
            List<String> langs = new ArrayList<>();
            setLangsByUserCountry(langs, request);
            model.put("posts", this.postService.getNewestPosts(langs));
            model.put("articles", articleRepo.getKryptotipyContentView(rbFromUrl));
            model.put("external_services", serviceRepo.getExternalServicesViews(rbFromUrl));
            log.info("index.html");
            pageData.set(model, rbFromUrl.getString("page.index.description"), rbFromUrl.getString("default.descr"));
            model.put("btc_price", getBtcPrice());
            model.put("page_description", pageData.getPageDescription());
            return "index";

        } catch (Exception ex) {
            log.warn("index : " + ex.getMessage());
            pageData.set(model, rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"));
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
        final Integer ALL_LANGS  = 10; //TODO from DB


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
            posts = this.postService.getPostsByLabelsAndLangs(labels, langs, from, to);
        }

        setNewsRenderingTexts(labels, langs, model, ALL_LABELS, request);

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

        String langLower = utilityService.consumeAcceptLang(request);
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
    }


    private void setNewsRenderingTexts(List<String> labels, List<String> langs,
                                       Map<String, Object> model,
                                       Integer allLabelCount, HttpServletRequest request) {
        ResourceBundle rbFromUrl = utilityService.getResourceBundle(utilityService.getLocaleFromUrl(
                request.getRequestURL().toString()
        ));
        String label_render;
        String customPageTitle;
        if (langs.size() == 1) {
            customPageTitle = rbFromUrl.getString("crypto.news.title." + langs.get(0).toLowerCase());
            label_render = customPageTitle.split(" ")[0];
        } else if (labels.size() == 1) {
            label_render = utilityService.mapLabelForRendering(labels.get(0));
            customPageTitle = rbFromUrl.getString("crypto.news.theme") + StringUtils.capitalize(label_render);
        } else if (labels.size() == allLabelCount) {
            customPageTitle = rbFromUrl.getString("crypto.news.title.all");
            label_render = rbFromUrl.getString("filter.component.all");
        } else {
            String pageTitle = rbFromUrl.getString("crypto.news.themes");

            StringBuilder labelsStr = new StringBuilder();
            labelsStr.append("[ ");
            labels.stream().forEach(label -> labelsStr.append(rbFromUrl.getString("a.label."+label))
                    .append(", "));
            label_render = labelsStr
                    .deleteCharAt(labelsStr.length() - 1)
                    .deleteCharAt(labelsStr.length() - 1)
                    .append(" ]")
                    .toString();
            customPageTitle = pageTitle + label_render;
        }

        pageData.set(model, customPageTitle, rbFromUrl.getString("default.descr"));
        model.put("site", utilityService.mapSiteUrl(utilityService.buildSiteUrl(labels, langs), labels, langs));
        model.put("label_render", customPageTitle);
        model.put("label_render_path", StringUtils.capitalize(label_render));
    }

    @RequestMapping("/external-services")
    public String externalServices(Map<String, Object> model, HttpServletRequest request) {

        ResourceBundle rbFromUrl = utilityService.getResourceBundle(utilityService.getLocaleFromUrl(
                request.getRequestURL().toString()
        ));
        try {

            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "active");
            model.put("active_did_you_know", "");
            model.put("active_krypto_learning", "");
            model.put("site", "external-services");
            model.put("content_path","fragments/content/external_services");
            model.put("services", serviceRepo.getExternalServices(rbFromUrl));
            model.put("link_is_not_internal", true);

            log.info("external_services.html");
            pageData.set(model, rbFromUrl.getString("external.services.h1"), rbFromUrl.getString("default.descr"));
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 19, 0, 0) + "");
            model.put("page_description", rbFromUrl.getString("external.description"));
            return "non-index";

        } catch (Exception ex) {
            log.warn("external-services : " + ex.getMessage());
            pageData.set(model, rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"));
            return "redirect:/error";
        }
    }


    @RequestMapping("/sources")
    public String sources(Map<String, Object> model, HttpServletRequest request) {

        ResourceBundle rbFromUrl = utilityService.getResourceBundle(utilityService.getLocaleFromUrl(
                request.getRequestURL().toString()
        ));
        try {

            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("active_krypto_learning", "");
            model.put("site", "cooperation");
            model.put("content_path","fragments/content/sources");
            model.put("sources", postService.getAllSources());

            log.info("sources.html");
            pageData.set(model, rbFromUrl.getString("footer.sources.text"), rbFromUrl.getString("default.descr"));//TODO
            model.put("page_description", rbFromUrl.getString("sources.descr"));
            model.put("page_date", LocalDateTime.of(2021, Month.MARCH, 19, 0, 0) + "");
            return "non-index";

        } catch (Exception ex) {
            log.warn("sources : " + ex.getMessage());
            pageData.set(model, rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"));
            return "redirect:/error";
        }
    }




    @RequestMapping("/cooperation")
    public String cooperation(Map<String, Object> model, HttpServletRequest request) {

        ResourceBundle rbFromUrl = utilityService.getResourceBundle(utilityService.getLocaleFromUrl(
                request.getRequestURL().toString()
        ));
        try {

            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("active_krypto_learning", "");
            model.put("site", "cooperation");
            model.put("content_path","fragments/content/cooperation");
            model.put("services", serviceRepo.getCooperationServices(rbFromUrl));

            log.info("cooperation.html");
            pageData.set(model, rbFromUrl.getString("footer.cooperation"), rbFromUrl.getString("default.descr"));
            model.put("page_description", rbFromUrl.getString("cooperation.descr"));
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 16, 0, 0) + "");
            return "non-index";

        } catch (Exception ex) {
            log.warn("cooperation : " + ex.getMessage());
            pageData.set(model, rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"));
            return "redirect:/error";
        }
    }


    @RequestMapping("/krypto-learning")
    public String cryptoLearning(Map<String, Object> model, HttpServletRequest request) {

        ResourceBundle rbFromUrl = utilityService.getResourceBundle(utilityService.getLocaleFromUrl(
                request.getRequestURL().toString()
        ));
        try {

            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("active_krypto_learning", "active");
            model.put("site", "krypto-learning");
            model.put("content_path","fragments/content/krypto_learning");

            model.put("articles", articleRepo.getKryptotipyContent(rbFromUrl));
            model.put("link_is_not_internal", false);

            log.info("krypto_learning.html");
            pageData.set(model, "Krypto learning", rbFromUrl.getString("default.descr"));
            model.put("page_description", rbFromUrl.getString("crypto.learning.descr"));
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 17, 0, 0) + "");
            return "non-index";

        } catch (Exception ex) {
            log.warn("krypto-learning : " + ex.getMessage());
            pageData.set(model, rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"));
            return "redirect:/error";
        }
    }

    @RequestMapping("/dictionary")
    public String dictionary(Map<String, Object> model, HttpServletRequest request) {

        ResourceBundle rbFromUrl = utilityService.getResourceBundle(utilityService.getLocaleFromUrl(
                request.getRequestURL().toString()
        ));
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
            pageData.set(model, rbFromUrl.getString("dictionary.title"), rbFromUrl.getString("default.descr"));
            model.put("page_description", rbFromUrl.getString("dictionary.d"));
            model.put("page_date", LocalDateTime.of(2020, Month.DECEMBER, 22, 0, 0 ) + "");
            return "non-index";

        } catch (Exception ex) {
            log.warn("dictionary : " + ex.getMessage());
            pageData.set(model, rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"));
            return "redirect:/error";
        }
    }

    @RequestMapping("/robots.txt")
    public String robotsTxt() {
        log.info("robots.txt");
        return "robots.txt";
    }

    @RequestMapping(value = "/sitemap.xml",
            //produces = MediaType.TEXT_XML_VALUE)
            produces = MediaType.APPLICATION_XML_VALUE)
    public List<SiteEntry> sitemapXml(HttpServletRequest request) {
        //XmlUrlSet
        String lang = utilityService.getLocaleFromUrl(request.getRequestURL().toString());
        log.info("Lang was found={}, sitemap-{}.xml rendered", lang, lang);

        ///return sitemapService.createSitemap(lang);
        return s.getSites();
    }


    @RequestMapping("/article/{postTitle}")
    public String post(Map<String, Object> model,
                       @PathVariable("postTitle") String postTitle, HttpServletRequest request) {
        ResourceBundle rbFromUrl = utilityService.getResourceBundle(utilityService.getLocaleFromUrl(
                request.getRequestURL().toString()
        ));
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
            pageData.set(model, pageData.getPostTitle(postTitle), rbFromUrl.getString("default.descr"));

            model.put("page_title", pageData.getPostTitle(postTitle));
            model.put("page_description", pageData.getPostDescription(postTitle));
            model.put("page_image", pageData.getPostImage(postTitle));
            model.put("page_image_width", pageData.getPostImageWidth(postTitle));
            model.put("page_image_height", pageData.getPostImageHeight(postTitle));
            model.put("page_date", pageData.getPostDate(postTitle));
            return "non-index";

        } catch (Exception ex) {
            log.warn("{} : {}", postTitle, ex.getMessage());
            pageData.set(model, rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"));
            return "redirect:/error";
        }
    }

    @RequestMapping("/did-you-know")
    public String didYouKnow(Map<String, Object> model, HttpServletRequest request) {

        ResourceBundle rbFromUrl = utilityService.getResourceBundle(utilityService.getLocaleFromUrl(
                request.getRequestURL().toString()
        ));
        try {

            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "active");
            model.put("active_krypto_learning", "");
            model.put("site", "did-you-know");
            model.put("content_path","fragments/content/did_you_know");
            pageData.set(model, "Vedeli ste, že", rbFromUrl.getString("default.descr"));
            log.info("did_you_know.html");
            model.put("page_description", rbFromUrl.getString("did.you.know.descr"));
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 22, 0, 0) + "");
            return "non-index";

        } catch (Exception ex) {
            log.warn("dictionary : " + ex.getMessage());
            pageData.set(model, rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"));
            return "redirect:/error";
        }
    }

    @RequestMapping("/one-post-frame/{hashed_id}")
    public String onePostFrame(
            Map<String, Object> model,
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("hashed_id") String hashedId
       ) {
        ResourceBundle rbFromUrl = utilityService.getResourceBundle(utilityService.getLocaleFromUrl(
                request.getRequestURL().toString()
        ));
        try {

            model.put("active_index", "");
            model.put("active_posts", "active");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("active_krypto_learning", "");
            model.put("site", "one-post-frame");

            Long id = utilityService.decodeKryptotipyHash(hashedId);
            Post post = this.postService.getPostsById(id);

            post.setViews(post.getViews() + 1);
            postService.updatePostViews(post.getUrl(), post.getViews());
            log.info("Post hashed id = {}, id = {}", hashedId, id);
            log.info("New view for post = {}, views = {}", post.getUrl(), post.getViews());

            model.put("one_post", post);

            pageData.set(model, post.getTitle(), rbFromUrl.getString("default.descr"));

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
            pageData.set(model, rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"));
            return "redirect:/error";
        }
    }


    private String getBtcPrice() {
        String btcPrice = String.valueOf(btcPriceService.getLatestPrice());
        return btcPrice.substring(0, btcPrice.length() - 2);
    }
}

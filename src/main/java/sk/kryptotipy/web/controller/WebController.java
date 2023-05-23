package sk.kryptotipy.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import sk.kryptotipy.web.config.MixedLocaleResolver;
import sk.kryptotipy.web.repository.ArticleRepository;
import sk.kryptotipy.web.repository.ServiceRepository;
import sk.kryptotipy.web.service.*;
import sk.kryptotipy.web.util.WebUtils;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Controller
public class WebController {

    private final PageDataServiceApi pageData;
    private final PostServiceApi postService;
    private final UtilityService utilityService;
    private final PriceService priceService;
    private final ServiceRepository serviceRepo;
    private final ArticleRepository articleRepo;
    private final MixedLocaleResolver mixedLocaleResolver;
    private final SitemapService sitemapService;


    @GetMapping("/")
    public String index(Map<String, Object> model,
                        HttpServletRequest request) {

        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(resolvedLocale);

        try {
            WebUtils.addCookies(model, request, mixedLocaleResolver);
            WebUtils.makeLinkActive(model, "index");
            var langs = mixedLocaleResolver.getLangsByUserCountry(resolvedLocale);

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

            log.info("Rendered = [index.html]");
            return "index";

        } catch (Exception ex) {
            log.warn("index : " + ex.getMessage());
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"), request);
            return "redirect:/error.html";
        }
    }

    @GetMapping("/external-services")
    public String externalServices(Map<String, Object> model, HttpServletRequest request) {

        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(resolvedLocale);
        WebUtils.addCookies(model, request, mixedLocaleResolver);
        WebUtils.makeLinkActive(model, "external_services");

        try {

            model.put("site", "external-services");
            model.put("content_path","fragments/content/external_services");
            model.put("services", serviceRepo.getExternalServices(rbFromUrl));
            model.put("link_is_not_internal", true);
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("external.services.h1"), rbFromUrl.getString("default.descr"), request);
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 19, 0, 0) + "");
            model.put("page_description", rbFromUrl.getString("external.description"));

            log.info("Rendered = [external_services.html]");
            return "non-index";

        } catch (Exception ex) {
            log.warn("external-services : " + ex.getMessage());
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"), request);
            return "redirect:/error";
        }
    }


    /**
    @GetMapping("/cooperation")
    public String cooperation(Map<String, Object> model, HttpServletRequest request) {

        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(resolvedLocale);
        WebUtils.addCookies(model, request, mixedLocaleResolver);
        WebUtils.makeLinkActive(model, "");
        try {

            model.put("site", "cooperation");
            model.put("content_path","fragments/content/cooperation");
            WebUtils.makeLinkActive(model, "cooperation");
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("footer.cooperation"), rbFromUrl.getString("default.descr"), request);
            model.put("page_description", rbFromUrl.getString("cooperation.descr"));
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 16, 0, 0) + "");

            log.info("Rendered = [cooperation.html]");
            return "non-index";

        } catch (Exception ex) {
            log.warn("cooperation : {}", ex.getMessage());
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"), request);
            return "redirect:/error";
        }
    }**/


    @GetMapping("/krypto-learning")
    public String cryptoLearning(Map<String, Object> model, HttpServletRequest request) {

        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(
                resolvedLocale
        );
        WebUtils.addCookies(model, request, mixedLocaleResolver);
        WebUtils.makeLinkActive(model, "krypto_learning");
        try {
            model.put("site", "krypto-learning");
            model.put("content_path","fragments/content/krypto_learning");
            model.put("articles", articleRepo.getKryptotipyContent(rbFromUrl));
            model.put("link_is_not_internal", false);
            pageData.set(model, resolvedLocale.getLanguage(),"Learning", rbFromUrl.getString("default.descr"), request);
            model.put("page_description", rbFromUrl.getString("crypto.learning.descr"));
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 17, 0, 0) + "");

            log.info("Rendered = [krypto_learning.html]");
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
        WebUtils.addCookies(model, request, mixedLocaleResolver);
        WebUtils.makeLinkActive(model, "dictionary");
        try {
            model.put("site", "dictionary");
            model.put("active_krypto_learning", "");
            model.put("content_path", "fragments/content/dictionary");
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("dictionary.title"), rbFromUrl.getString("default.descr"), request);
            model.put("page_description", rbFromUrl.getString("dictionary.d"));
            model.put("page_date", LocalDateTime.of(2020, Month.DECEMBER, 22, 0, 0 ) + "");

            log.info("Rendered = [dictionary.html]");
            return "non-index";

        } catch (Exception ex) {
            log.warn("dictionary : " + ex.getMessage());
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"), request);
            return "redirect:/error";
        }
    }

    @GetMapping("/robots.txt")
    public String robotsTxt() {
        log.info("Rendered = [robots.txt]");
        return "robots.txt";
    }

    @GetMapping("/ads.txt")
    public String adsTxt() {
        log.info("Rendered = [ads.txt]");
        return "ads.txt";
    }

    @GetMapping("/article/{postTitle}")
    public String post(Map<String, Object> model,
                       @PathVariable("postTitle") String postTitle, HttpServletRequest request) {
        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(resolvedLocale);
        WebUtils.addCookies(model, request, mixedLocaleResolver);
        WebUtils.makeLinkActive(model, "krypto_learning");
        try {

            model.put("site", "article/" + postTitle);
            model.put("content_path","fragments/content/" + postTitle);
            pageData.set(model, resolvedLocale.getLanguage(), pageData.getPostTitle(postTitle), rbFromUrl.getString("default.descr"), request);
            model.put("page_title", pageData.getPostTitle(postTitle));
            model.put("page_description", pageData.getPostDescription(postTitle));
            model.put("page_image", pageData.getPostImage(postTitle));
            model.put("page_image_width", pageData.getPostImageWidth(postTitle));
            model.put("page_image_height", pageData.getPostImageHeight(postTitle));
            model.put("page_date", pageData.getPostDate(postTitle));

            log.info("article = [{}]", postTitle);
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
        var rbFromUrl = utilityService.getResourceBundle(resolvedLocale);
        WebUtils.addCookies(model, request, mixedLocaleResolver);
        WebUtils.makeLinkActive(model, "did_you_know");
        try {
            model.put("site", "did-you-know");
            model.put("content_path", "fragments/content/did_you_know");
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("did.you.know") , rbFromUrl.getString("default.descr"), request);
            model.put("page_description", rbFromUrl.getString("did.you.know.descr"));
            model.put("page_date", LocalDateTime.of(2021, Month.JANUARY, 22, 0, 0) + "");

            log.info("Rendered = [did_you_know.html]");
            return "non-index";

        } catch (Exception ex) {
            log.warn("dictionary : " + ex.getMessage());
            pageData.set(model, resolvedLocale.getLanguage(), rbFromUrl.getString("page.not.found"), rbFromUrl.getString("default.descr"), request);
            return "redirect:/error";
        }
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

        response.addHeader("content-type", "application/xml; charset=utf-8");
        response.setStatus(200);
        var out = response.getWriter();
        out.println(sitemapService.create(siteMapLang));
        log.info("Locale was found={}, sitemap for lang {} rendered", resolvedLocale, siteMapLang);
    }

    private String getBtcPrice() {
        var btcPrice = String.valueOf(priceService.getLatestPrice());
        return btcPrice.substring(0, btcPrice.length() - 2);
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



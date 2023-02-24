package sk.kryptotipy.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sk.kryptotipy.web.AppProperties;
import sk.kryptotipy.web.component.UrlComponent;
import sk.kryptotipy.web.config.MixedLocaleResolver;
import sk.kryptotipy.web.data.db.PostWithLabel;
import sk.kryptotipy.web.repository.db.LabelRepository;
import sk.kryptotipy.web.repository.db.PostJdbcRepository;
import sk.kryptotipy.web.repository.db.PostWithLabelRepository;
import sk.kryptotipy.web.service.PageDataServiceApi;
import sk.kryptotipy.web.service.PostServiceApi;
import sk.kryptotipy.web.service.PriceService;
import sk.kryptotipy.web.service.UtilityService;
import sk.kryptotipy.web.util.WebUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Controller
public class NewsController {

    private final AppProperties appProperties;
    private final PostWithLabelRepository postWithLabelRepository;
    private final LabelRepository labelRepo;
    private final PostJdbcRepository postJdbcRepository;
    private final MixedLocaleResolver mixedLocaleResolver;
    private final UtilityService utilityService;
    private final PriceService priceService;
    private final PostServiceApi postService;
    private final PageDataServiceApi pageData;
    private final UrlComponent urlComponent;


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
        List<PostWithLabel> posts;
        final var from = 0L;
        final var to = appProperties.getPageSize();

        var allLabels = labelRepo.getAllLabels()
                .stream()
                .map((var label) -> label.getName())
                .collect(Collectors.toList());
        var ALL_LABELS = allLabels.size();

        if (labell != null && labell.contains("_l_")) {
            var langss = labell.split("_l_")[1];
            var labelss = labell.split("_l_")[0];
            langs = Arrays.asList(langss.toUpperCase().split("_"));
            labels = Arrays.asList(labelss.split("_"));
        } else {

            final var parameterMap = request.getParameterMap();
            WebUtils.addCookies(model, request, mixedLocaleResolver);
            WebUtils.makeLinkActive(model, "posts");

            if (null != labell) {
                labels =
                        labell != null && !labell.contains("_") && labels.contains(labell) ?
                                new ArrayList<>(List.of(labell)) : new ArrayList<>(List.of(labell.split("_")));
            }

            if (lang != null && !lang.contains("_") && !langs.contains(lang)) {
                langs.add(lang);
            }

            var parameterLang = parameterMap.get("lang");
            if (langs.isEmpty() && null == parameterLang) {
                langs = mixedLocaleResolver.getLangsByUserCountry(mixedLocaleResolver.resolveLocale(request));
            } else {
                langs = (null == parameterLang) ?
                        new ArrayList<>(Arrays.asList(lang)) :
                        new ArrayList<>(Arrays.asList(parameterLang));
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
        sendBackButtonLink(request);

        model.put("posts", posts);
        model.put("content_path", "fragments/content/posts");
        model.put("checked_labels", labels);
        model.put("price_widget", priceService.mapCoinToCoinLibId(labels));
        model.put("checked_langs", langs);

        var clientIP = WebUtils.getClientIpAddress(request);

        log.info("[{}] Posts count rendered = [{}]", clientIP, posts.size());
        log.info("[{}] Posts filtered by [{}] labels count = [{}], labels = [{}], langs = [{}], langs count = [{}].",
                clientIP, labels.size(), labels, langs, langs.size());
        return "non-index";
    }

    @GetMapping("/one-post-frame/{hashed_id}")
    public String onePostFrame(
            Map<String, Object> model,
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("hashed_id") String hashedId
    ) {
        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(resolvedLocale);
        WebUtils.makeLinkActive(model, "posts");
        try {

            var postWithLabel = updateViewCounter(hashedId);
            var pageImage = "/assets/post-images/" + postWithLabel.getLabelName1().toLowerCase() + "/images_1.jpg";

            model.put("site", "one-post-frame");
            model.put("one_post", postWithLabel);
            pageData.set(model, resolvedLocale.getLanguage(), postWithLabel.getTitle(), rbFromUrl.getString("default.descr"), request);
            model.put("page_image", pageImage);

            setFallBackForBackButton(request);

            if (postWithLabel.useRedirectToSourceSite()) {
                response.setHeader("Location", postWithLabel.getUrl());
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

    private PostWithLabel updateViewCounter(String hashedId) {
        var id = utilityService.decodeKryptotipyHash(hashedId);
        var post = this.postService.getPostsById(id);
        var postWithLabel = postWithLabelRepository.findByPostId(id);

        post.setViews(post.getViews() + 1);
        postWithLabel.setViews(postWithLabel.getViews() + 1);
        postService.updatePostViews(post.getUrl(), post.getViews());

        log.info("Post hashed id = {}, id = {}", hashedId, id);
        log.info("New view for post = {}, views = {}", postWithLabel.getUrl(), postWithLabel.getViews());

        return postWithLabel;
    }

    private String labelsToRender(List<String> labels, StringBuilder labelsStr, ResourceBundle rb) {
        final var _5 = 5;
        if (labels.size() > _5) {
            return l(labels, _5, " ...",labelsStr, rb);
        } else {
            return l(labels, labels.size(), "",labelsStr, rb);
        }
    }

    private String l(List<String> labels, Integer limit, String appendToEnd, StringBuilder labelsStr, ResourceBundle rb) {
        labels.stream()
                .limit(limit)
                //.map(x -> rb.getString("label.title." + x.toLowerCase()))//TODO
                .forEach((var label) -> labelsStr.append(rb.getString("label.title." + label.toLowerCase()))
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

    private void setNewsRenderingTexts(List<String> labels, List<String> langs,
                                             Map<String, Object> model,
                                             Integer allLabelCount, HttpServletRequest request) {
        var label_render = "";
        var customPageTitle = "";
        var resolvedLocale = mixedLocaleResolver.resolveLocale(request);
        var rbFromUrl = utilityService.getResourceBundle(resolvedLocale);

        if (langs.size() == 1) {//TODO think more && labels.size() > 5 PL has one lang thats why no 5 but all. but fix other site too all->5
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

    private String getOgImageForNews(List<String> labels) {
        if (labels.size() == 1) {
            return "/assets/post-images/" + labels.get(0) + "/images_7.jpg";
        }
        return "/assets/post-images/exchange/images_2.jpg";
    }

    private void sendBackButtonLink(HttpServletRequest request) {
        request.getSession().setAttribute("backtothefuture", request.getRequestURI() + "?" + request.getQueryString());
    }

    private void setFallBackForBackButton(HttpServletRequest request) {
        if (request.getSession().getAttribute("backtothefuture") == null) { // asi dal do browsera post, nie preklik z news
            request.getSession().setAttribute("backtothefuture", "/news");
        }
    }
}

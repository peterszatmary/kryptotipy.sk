package sk.kryptotipy.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sk.kryptotipy.web.api.LabelServiceApi;
import sk.kryptotipy.web.data.Post;
import sk.kryptotipy.web.data.Service;
import sk.kryptotipy.web.api.PageDataServiceApi;
import sk.kryptotipy.web.api.PostServiceApi;
import sk.kryptotipy.web.data.ServiceRepository;
import sk.kryptotipy.web.service.BtcPriceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Controller
public class WebController {

    private final PageDataServiceApi pageData;
    private final PostServiceApi postService;
    private final AppProperties appProperties;
    private final BtcPriceService btcPriceService;
    private final ServiceRepository serviceRepo;
    private LabelServiceApi labelService;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {

        try {
            model.put("active_index", "active");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("site", "");
            model.put("posts", this.postService.getNewestPosts());
            log.info("index.html");
            pageData.set(model, appProperties.getPageDescriptionShort());
            model.put("btc_price", getBtcPrice());
            return "index";

        } catch (Exception ex) {
            log.warn("index : " + ex.getMessage());
            pageData.set(model, "404 Stránka nenájdená");
            return "redirect:/error.html";
        }
    }

    @RequestMapping("/posts-by-i18n/{i18n}")
    public String postsByI18n(Map<String, Object> model,
                              @PathVariable("i18n") String i18n) {
        try {

            if (!("SK".equals(i18n) || "CZ".equals(i18n))) {
                log.info("Someone tried i18n page by manipulating url !!. i18n value = {}", i18n);
                log.error("Someone tried i18n page by manipulating url !!. i18n value = {}", i18n);
                throw new Exception("Someone tried i18n page by manipulating url !!.");
            }

            Long from = 0L;
            Long to = appProperties.getPageSize();

            model.put("active_index", "");
            model.put("active_posts", "active");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("i18n", i18n);
            model.put("i18n_text", getI18nText(i18n));
            model.put("site", "posts/i18n" + i18n);
            model.put("content_path","fragments/content/posts_by_i18n");

            model.put("posts", this.postService.getPostsByI18n(i18n, from, to));
            log.info("posts.html - i18n");
            pageData.set(model, getI18nText(i18n));
            return "non-index";

        } catch (Exception ex) {
            log.warn("index : " + ex.getMessage());
            pageData.set(model, "404 Stránka nenájdená");
            return "redirect:/error.html";
        }
    }


    @RequestMapping("/posts/{label_q}")
    public String posts(Map<String, Object> model,
                        @PathVariable("label_q") String label_q) {
        try {
            String label_render = label_q;
            String label = label_q;

            List<Post> posts;
            Long from = 0L;
            Long to = appProperties.getPageSize();

            if ("wallets".equals(label_q)) {
                label_render = "Peňaženky";
                label = "peňaženky"; //TODO i18n
            }

            if ("nezaradene".equals(label_q)) {
                label_render = "Ostatné"; //TODO i18n
                label = label_q;
            }

            if ("all".equals(label_q)) {
                label_render = "Všetky";
                posts = this.postService.getPostsByLabelAll(from, to);
            } else {
                posts = this.postService.getPostsByLabel(label, from, to);
                label_render = StringUtils.capitalize(label_render);
            }

            model.put("active_index", "");
            model.put("active_posts", "active");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("posts", posts);

            model.put("label", label);
            model.put("label_render", label_render);
            model.put("label_q", label_q);

            model.put("site", "posts/" + label);
            model.put("content_path","fragments/content/posts");

            log.info("posts.html {}", label);
            pageData.set(model, label_render + " krypto články");
            return "non-index";

        } catch (Exception ex) {
            log.warn("posts : " + ex.getMessage());
            pageData.set(model, "404 Stránka nenájdená");
            return "redirect:/error";
        }
    }

    @RequestMapping("/external-services")
    public String externalServices(Map<String, Object> model) {

        try {

            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "active");
            model.put("active_did_you_know", "");
            model.put("site", "external-services/");
            model.put("content_path","fragments/content/external_services");
            model.put("services", serviceRepo.getExternalServices());

            log.info("external_services.html");
            pageData.set(model, "Externé krypto služby");
            return "non-index";

        } catch (Exception ex) {
            log.warn("external-services : " + ex.getMessage());
            pageData.set(model, "404 Stránka nenájdená");
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
            model.put("site", "krypto-learning/");
            model.put("content_path","fragments/content/krypto_learning");

            List<Service> services = new ArrayList<>();
            services.add(new Service(
                    "Prvou kryptomenou bol ECash",
                    "/btc-is-not-the-first",
                    "articles/ecash.png",
                    "2.1.2021 - Pred Bitcoinom tu naozaj bola iná kryptomena. Zopár projektov muselo najprv zlyhať, " +
                            "aby sa nakoniec vytvoril koncept, ktorý bude fungovať. Ako to teda bolo ?"));

            model.put("services", services);

            log.info("krypto_learning.html");
            pageData.set(model, "Krypto learning");
            return "non-index";

        } catch (Exception ex) {
            log.warn("krypto-learning : " + ex.getMessage());
            pageData.set(model, "404 Stránka nenájdená");
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
            model.put("site", "dictionary/");
            model.put("content_path","fragments/content/dictionary");

            log.info("dictionary.html");
            pageData.set(model, "Krypto slovník");
            return "non-index";

        } catch (Exception ex) {
            log.warn("dictionary : " + ex.getMessage());
            pageData.set(model, "404 Stránka nenájdená");
            return "redirect:/error";
        }
    }


    @RequestMapping("/{postTitle}")
    public String post(Map<String, Object> model,
                       @PathVariable("postTitle") String postTitle) {
        try {
            model.put("active_index", "");
            model.put("active_posts", "active");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("site", postTitle);

            model.put("content_path","fragments/content/" + postTitle);

            log.info("post.html - {}", postTitle);
            pageData.set(model, pageData.getPostTitle(postTitle));
            model.put("post_title", pageData.getPostTitle(postTitle));
            model.put("page_title", pageData.getPostTitle(postTitle));
            model.put("page_description", pageData.getPostDescription(postTitle));
            model.put("page_image", pageData.getPostImage(postTitle));
            return "non-index";

        } catch (Exception ex) {
            log.warn("{} : {}", postTitle, ex.getMessage());
            pageData.set(model, "404 Stránka nenájdená");
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
            model.put("site", "did_you_know/");
            model.put("content_path","fragments/content/did_you_know");

            log.info("did_you_know.html");
            pageData.set(model, "Vedeli ste, že");
            return "non-index";

        } catch (Exception ex) {
            log.warn("dictionary : " + ex.getMessage());
            pageData.set(model, "404 Stránka nenájdená");
            return "redirect:/error";
        }
    }

    @RequestMapping("/one-post-frame") //TODO iframe
    public String onePostFrame(Map<String, Object> model,
            @RequestParam(value = "post_url", required = false) String postUrl) {
        //try {
            model.put("active_index", "");
            model.put("active_posts", "active");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("site", "one-post-frame/");

            //String postUrl = "https://kryptotrejder.sk/regulacie-v-usa-ziskavaju-realne-kontury-ohrozia-kryptomenovy-sektor/"; //TODO
            String[] urlParts = postUrl.split("/");
            String postUrlSource = urlParts[2];
            String protocol = urlParts[0].replace(":", "");
            String postUrlSourceLang = postUrlSource.split("\\.")[1];
            String postUrlValue = urlParts[urlParts.length - 1];
            String postTitle = postUrlValue.replace("-"," ") + " - " + postUrlSource;

            model.put("post_url_iframe", postUrl);
            model.put("post_url_rendered", postUrlSource);
            model.put("post_url_value", postUrlValue);

            log.info("one-post-frame.html");
            pageData.set(model, postTitle);
            return "one-post-frame";

//        } catch (Exception ex) {
//            log.warn("one page frame : " + ex.getMessage());
//            log.warn(ex.getCause() + "" + ex.getStackTrace());
//            pageData.set(model, "404 Stránka nenájdená");
//            return "redirect:/error";
//        }
    }

    private String getBtcPrice() {
        String btcPrice = String.valueOf(btcPriceService.getLatestPrice());
        return btcPrice.substring(0, btcPrice.length() - 2);
    }

    private String getI18nText(String i18n) {
        switch(i18n) {
            case "SK": return "Slovenské krypto články";
            case "CZ": return "České krypto články";
            default : return null; //TODO
        }
    }
}

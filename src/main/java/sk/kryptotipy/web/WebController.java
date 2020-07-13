package sk.kryptotipy.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.kryptotipy.web.data.Post;
import sk.kryptotipy.web.service.PageDataService;
import sk.kryptotipy.web.service.PageDataServiceApi;
import sk.kryptotipy.web.service.PostServiceApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class WebController {

    private final PageDataServiceApi pageData;
    private final PostServiceApi postService;

    public WebController(PageDataService pageData, PostServiceApi postService) {
        this.pageData = pageData;
        this.postService = postService;
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) {

        try {

            model.put("active_index", "active");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_contact", "");
            model.put("posts", this.postService.getNewestPosts());
            log.info("index.html - ");
            pageData.set(model, " - index");
            return "index";

        } catch (Exception ex) {
            log.warn("index : " + ex.getMessage());
            pageData.set(model, "404");
            return "redirect:/error.html";
        }
    }

    @RequestMapping("/contact")
    public String contact(Map<String, Object> model) {

        try {

            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_contact", "active");

            log.info("contact.html - ");
            pageData.set(model, " - contact");
            return "contact";

        } catch (Exception ex) {
            log.warn("contact : " + ex.getMessage());
            pageData.set(model, "404");
            return "redirect:/error.html";
        }
    }

    @RequestMapping("/posts/{label}")
    public String posts(Map<String, Object> model,
                        @PathVariable("label") final String label) {

        List<Post> posts;
        try {
            if ("all".equals(label)) {
                posts = this.postService.getNewestPosts();
            } else {
                //TODO label in check
                posts = this.postService.getPostsByLabel(label);
            }

            model.put("active_index", "");
            model.put("active_posts", "active");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_contact", "");
            model.put("posts", posts);

            log.info("posts.html - ");
            pageData.set(model, " - posts");
            return "posts";

        } catch (Exception ex) {
            log.warn("posts : " + ex.getMessage());
            pageData.set(model, "404");
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
            model.put("active_contact", "");

            log.info("external-services.html - ");
            pageData.set(model, " - external services");
            return "external-services";

        } catch (Exception ex) {
            log.warn("external-services : " + ex.getMessage());
            pageData.set(model, "404");
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
            model.put("active_contact", "");

            log.info("dictionary.html - ");
            pageData.set(model, " - dictionary");
            return "dictionary";

        } catch (Exception ex) {
            log.warn("dictionary : " + ex.getMessage());
            pageData.set(model, "404");
            return "redirect:/error";
        }
    }
}

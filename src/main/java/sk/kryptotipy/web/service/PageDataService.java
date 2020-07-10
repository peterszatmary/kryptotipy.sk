package sk.kryptotipy.web.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Data
public class PageDataService implements PageDataServiceApi {

    @Value("${page.title}")
    private String pageTitle;

    @Value("${page.description}")
    private String pageDescription;

    @Value("${page.author}")
    private String pageAuthor;

    @Value("${base.url}")
    private String baseUrl;

    @Value("${title}")
    private String title;

    @Value("${project.version}")
    private String projectVersion;

    @Value("${page.size}")
    private Integer pageSize;

    //@Autowired
    //private RenderComponent render;

    //@Autowired
    //private FormData formData;

    //@Autowired
    //private AppProperties appProperties;

    // info about if pagination should go to view
    private Boolean paginationOn = Boolean.TRUE;


    public void set(Map<String, Object> model, String pageTitleCustom) {
        model.put("page_title", pageTitle + pageTitleCustom);
        model.put("page_description", pageDescription);
        model.put("page_author", pageAuthor);
        model.put("base_url", baseUrl);
        model.put("title", title);
        model.put("project_version", projectVersion);
        //model.put("render", render);
        //model.put("tooltip", appProperties.getTooltip());
    }

}
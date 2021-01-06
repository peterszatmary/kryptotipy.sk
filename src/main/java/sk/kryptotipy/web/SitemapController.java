package sk.kryptotipy.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.kryptotipy.web.view.SitemapView;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@AllArgsConstructor
@Controller
public final class SitemapController {

    private final SitemapView view;

    @RequestMapping(path = "/sitemap.xml", produces = APPLICATION_XML_VALUE)
    public SitemapView create() {
        return view;
    }
}
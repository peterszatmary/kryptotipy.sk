package sk.kryptotipy.web.view;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import org.thymeleaf.exceptions.TemplateInputException;
import sk.kryptotipy.web.service.SitemapService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Component
public final class SitemapView extends AbstractView {

    private final SitemapService service;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_XML_VALUE);

        try (Writer writer = response.getWriter()) {
            writer.append(service.createSitemap());
        } catch (TemplateInputException ee) {
            log.info("sitemap.xml ");
        }
    }
}

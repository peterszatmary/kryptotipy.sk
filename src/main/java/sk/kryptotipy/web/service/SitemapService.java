package sk.kryptotipy.web.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.data.SiteEntry;
import sk.kryptotipy.web.repository.SiteRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class SitemapService {

    private final SiteRepository siteRepository;

    public String createSitemap(String lang) {
        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9 " +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance " +
                "xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 " +
                "http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\">\n";

        StringBuilder sitemap = new StringBuilder(header);

        siteRepository.getSites()
                .stream()
                .map(url -> createUrlCode(url, lang))
                .forEach(x-> sitemap.append(x));

        sitemap.append("</urlset>\n");
        return sitemap.toString();
    }


    private String getUrl(String lang) {
        return "sk".equals(lang) ? "https://www.kryptotipy.sk/" : "https://" + lang + ".kryptotipy.sk/";
    }

    private String createUrlCode(SiteEntry site, String lang) {
        StringBuilder str = new StringBuilder("<url>\n");
        str.append("    <loc>");
        str.append(getUrl(lang));
        str.append(site.getLoc());
        str.append("    </loc>\n");

        str.append("    <lastmod>");
        str.append(site.getLastmod());
        str.append("    </lastmod>\n");

        str.append("    <priority>");
        str.append(site.getPriority());
        str.append("    </priority>\n");

        str.append("</url>\n");
        return str.toString();
    }
}

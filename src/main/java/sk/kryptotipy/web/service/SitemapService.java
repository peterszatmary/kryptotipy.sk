package sk.kryptotipy.web.service;

import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.data.SiteEntry;
import sk.kryptotipy.web.data.SiteRepository;

import java.net.MalformedURLException;
import java.util.Objects;

@Service
public final class SitemapService {
    private static final String BASE_URL = "https://www.kryptotipy.sk"; //TODO hard coded

    private final SiteRepository siteRepository;

    public SitemapService(SiteRepository blogRepository) {
        this.siteRepository = Objects.requireNonNull(blogRepository);
    }

    public String createSitemap() throws MalformedURLException {
        WebSitemapGenerator sitemap = new WebSitemapGenerator(BASE_URL);

        for (SiteEntry entry : siteRepository.findAll()) {
            WebSitemapUrl url = new WebSitemapUrl
                    .Options(BASE_URL  + entry.getTitle())
                    .lastMod(java.sql.Date.valueOf(entry.getDate()))
                    .priority(entry.getPriority())
                    .build();
            sitemap.addUrl(url);
        }

        return String.join("", sitemap.writeAsStrings());
    }
}
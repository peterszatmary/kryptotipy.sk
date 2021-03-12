package sk.kryptotipy.web.service;

import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.AppProperties;
import sk.kryptotipy.web.data.SiteEntry;
import sk.kryptotipy.web.repository.SiteRepository;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public final class SitemapService {
    private static final String BASE_URL = "https://www.kryptotipy.sk"; //TODO hard coded

    private final SiteRepository siteRepository;
    private final AppProperties appProperties;
    private final UtilityService utilityService;

    public String createSitemap() throws MalformedURLException {

        WebSitemapGenerator sitemap = new WebSitemapGenerator(BASE_URL);
        List<String> supportedLangs = appProperties.getSupportedLangs();
        for (String supportedLang : supportedLangs) {
            for (SiteEntry entry : siteRepository.findAll()) {
                WebSitemapUrl url = new WebSitemapUrl
                        .Options(utilityService.buildUrlWithLang(supportedLang)  + entry.getTitle())
                        .lastMod(java.sql.Date.valueOf(entry.getDate()))
                        .priority(entry.getPriority())
                        .build();
                sitemap.addUrl(url);
            }
        }

        return String.join("", sitemap.writeAsStrings());
    }
}
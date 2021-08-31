package sk.kryptotipy.web;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import sk.kryptotipy.web.component.UrlComponent;

import java.util.Arrays;

public class TestCanonical {

    @Test
    public void testPostAll() {
        UrlComponent urlComponent = new UrlComponent();
        String canonical = urlComponent.mapSiteUrlForCanonical(
                "https://kryptotipy.sk/posts/all",
                Arrays.asList(),
                Arrays.asList()
        );
        Assert.assertEquals("https://kryptotipy.sk/posts/all", canonical);
    }

    @Test
    public void testNews() {
        UrlComponent urlComponent = new UrlComponent();
        String canonical = urlComponent.mapSiteUrlForCanonical(
                "https://kryptotipy.sk/news",
                Arrays.asList(),
                Arrays.asList()
        );
        Assert.assertEquals("https://kryptotipy.sk/posts/all", canonical);
    }

    @Test
    public void testOneLangOneLabelUrl() {
        UrlComponent urlComponent = new UrlComponent();
        String canonical = urlComponent.mapSiteUrlForCanonical(
                "https://kryptotipy.sk/news?lang=US&label=kusama",
                Arrays.asList("kusama"),
                Arrays.asList("US")
        );
        Assert.assertEquals("https://kryptotipy.sk/posts/kusama", canonical);
    }

    @Test
    public void testMoreLangOneLabelUrl() {
        UrlComponent urlComponent = new UrlComponent();
        String canonical = urlComponent.mapSiteUrlForCanonical(
                "https://kryptotipy.sk/news?lang=US&lang=SK&label=kusama",
                Arrays.asList("kusama"),
                Arrays.asList("US", "SK")
        );
        Assert.assertEquals("https://kryptotipy.sk/posts/kusama", canonical);
    }

    @Test
    public void testLabelAltcoin() {
        UrlComponent urlComponent = new UrlComponent();
        String canonical = urlComponent.mapSiteUrlForCanonical(
                "https://localhost:8443/news?label=altcoin",
                Arrays.asList("altcoin"),
                Arrays.asList()
        );
        Assert.assertEquals("https://localhost:8443/posts/altcoin", canonical);
    }

    //
}

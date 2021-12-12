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
                "https://kryptotipy.sk/posts/all"
        );
        Assert.assertEquals("https://kryptotipy.sk/posts/all", canonical);
    }

    @Test
    public void testNews() {
        UrlComponent urlComponent = new UrlComponent();
        String canonical = urlComponent.mapSiteUrlForCanonical(
                "https://kryptotipy.sk/news"
        );
        Assert.assertEquals("https://kryptotipy.sk/posts/all", canonical);
    }

    @Test
    public void testOneLangOneLabelUrl() {
        UrlComponent urlComponent = new UrlComponent();
        String canonical = urlComponent.mapSiteUrlForCanonical(
                "https://kryptotipy.sk/news?lang=US&label=kusama"
        );
        Assert.assertEquals("https://kryptotipy.sk/posts/kusama_l_us", canonical);
    }

    @Test
    public void testMoreLangOneLabelUrl() {
        UrlComponent urlComponent = new UrlComponent();
        String canonical = urlComponent.mapSiteUrlForCanonical(
                "https://kryptotipy.sk/news?label=kusama&lang=US&lang=SK"
        );
        Assert.assertEquals("https://kryptotipy.sk/posts/kusama_l_us_sk", canonical);
    }

    @Test
    public void testLabelAltcoin() {
        UrlComponent urlComponent = new UrlComponent();
        String canonical = urlComponent.mapSiteUrlForCanonical(
                "https://localhost:8443/news?label=altcoin"
        );
        Assert.assertEquals("https://localhost:8443/posts/altcoin", canonical);
    }

    @Test
    public void testLabels() {
        UrlComponent urlComponent = new UrlComponent();
        String canonical = urlComponent.mapSiteUrlForCanonical(
                "https://sk.localhost:8443/news?label=altcoin&label=kusama&lang=SK&lang=US"
        );
        Assert.assertEquals("https://sk.localhost:8443/posts/altcoin_kusama_l_sk_us", canonical);
    }
    //

    @Test
    public void testLabelsNews() {
        UrlComponent urlComponent = new UrlComponent();
        String canonical = urlComponent.mapSiteUrlForCanonical(
                "https://sk.localhost:8443/news"
        );
        Assert.assertEquals("https://sk.localhost:8443/posts/all", canonical);
    }
}

package sk.kryptotipy.web;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import sk.kryptotipy.web.component.UrlComponent;

public class TestCanonical {

    @Test
    public void testPostAll() {
        var testUrl = "https://kryptotipy.sk/posts/all";
        var urlComponent = new UrlComponent();
        var canonical = urlComponent.mapSiteUrlForCanonical(testUrl);
        Assert.assertEquals(testUrl, canonical);
    }

    @Test
    public void testNews() {
        var urlComponent = new UrlComponent();
        var canonical = urlComponent.mapSiteUrlForCanonical("https://kryptotipy.sk/news");
        Assert.assertEquals("https://kryptotipy.sk/posts/all", canonical);
    }

    @Test
    public void testOneLangOneLabelUrl() {
        var urlComponent = new UrlComponent();
        var canonical = urlComponent.mapSiteUrlForCanonical("https://kryptotipy.sk/news?lang=US&label=kusama");
        Assert.assertEquals("https://kryptotipy.sk/posts/kusama_l_us", canonical);
    }

    @Test
    public void testMoreLangOneLabelUrl() {
        var urlComponent = new UrlComponent();
        var canonical = urlComponent.mapSiteUrlForCanonical("https://kryptotipy.sk/news?label=kusama&lang=US&lang=SK");
        Assert.assertEquals("https://kryptotipy.sk/posts/kusama_l_us_sk", canonical);
    }

    @Test
    public void testLabelAltcoin() {
        var urlComponent = new UrlComponent();
        var canonical = urlComponent.mapSiteUrlForCanonical("https://localhost:8443/news?label=altcoin");
        Assert.assertEquals("https://localhost:8443/posts/altcoin", canonical);
    }

    @Test
    public void testLabels() {
        var urlComponent = new UrlComponent();
        var canonical = urlComponent.mapSiteUrlForCanonical("https://sk.localhost:8443/news?label=altcoin&label=kusama&lang=SK&lang=US");
        Assert.assertEquals("https://sk.localhost:8443/posts/altcoin_kusama_l_sk_us", canonical);
    }

    @Test
    public void testLabelsNews() {
        var urlComponent = new UrlComponent();
        var canonical = urlComponent.mapSiteUrlForCanonical("https://sk.localhost:8443/news");
        Assert.assertEquals("https://sk.localhost:8443/posts/all", canonical);
    }
}

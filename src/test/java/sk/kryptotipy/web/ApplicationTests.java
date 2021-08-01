package sk.kryptotipy.web;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.kryptotipy.web.api.LangServiceApi;
import sk.kryptotipy.web.service.LangService;
import sk.kryptotipy.web.service.UtilityService;

import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private LangServiceApi langService;

    @Test
    public void testGetLocaleFrom() {
        String www = langService.getLocaleFromUrl("https://www.kryptotipy.sk");
        String de = langService.getLocaleFromUrl("https://de.kryptotipy.sk");
        String en = langService.getLocaleFromUrl("https://en.kryptotipy.sk");
        String sk = langService.getLocaleFromUrl("https://sk.kryptotipy.sk");
        String pl = langService.getLocaleFromUrl("https://pl.kryptotipy.sk");
        String hu = langService.getLocaleFromUrl("https://hu.kryptotipy.sk");
        String something = langService.getLocaleFromUrl("https://something.kryptotipy.sk");

        Assert.assertEquals(null, www);
        Assert.assertEquals("en", en);
        Assert.assertEquals("sk", sk);
        Assert.assertEquals("de", de);
        Assert.assertEquals("hu", hu);
        Assert.assertEquals("pl", pl);
        Assert.assertEquals(null, something);
    }

}

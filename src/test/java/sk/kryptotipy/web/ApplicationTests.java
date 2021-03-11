package sk.kryptotipy.web;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sk.kryptotipy.web.service.UtilityService;

import java.util.stream.IntStream;

@SpringBootTest
public class ApplicationTests {

    private UtilityService utilityService = new UtilityService();

    @Test
    public void testGetLocaleFrom() {
        String www = utilityService.getLocaleFromUrl("https://www.kryptotipy.sk");
        String de = utilityService.getLocaleFromUrl("https://de.kryptotipy.sk");
        String en = utilityService.getLocaleFromUrl("https://en.kryptotipy.sk");
        String sk = utilityService.getLocaleFromUrl("https://sk.kryptotipy.sk");
        String hu = utilityService.getLocaleFromUrl("https://hu.kryptotipy.sk");
        String something = utilityService.getLocaleFromUrl("https://something.kryptotipy.sk");

        Assert.assertEquals("sk", www);
        Assert.assertEquals("en", en);
        Assert.assertEquals("sk", sk);
        Assert.assertEquals("de", de);
        Assert.assertEquals("hu", hu);
        Assert.assertEquals("sk", something);
    }

}

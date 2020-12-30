package sk.kryptotipy.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sk.kryptotipy.web.data.Post;
import sk.kryptotipy.web.data.Service;
import sk.kryptotipy.web.api.PageDataServiceApi;
import sk.kryptotipy.web.api.PostServiceApi;
import sk.kryptotipy.web.service.BtcPriceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Controller
public class WebController {

    private final PageDataServiceApi pageData;
    private final PostServiceApi postService;
    private final AppProperties appProperties;
    private final BtcPriceService btcPriceService;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {

        try {
            model.put("active_index", "active");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("site", "");
            model.put("posts", this.postService.getNewestPosts());
            log.info("index.html");
            pageData.set(model, " - Vitajte");
            model.put("btc_price", getBtcPrice());
            return "index";

        } catch (Exception ex) {
            log.warn("index : " + ex.getMessage());
            pageData.set(model, "404 Stránka nenájdená");
            return "redirect:/error.html";
        }
    }

    @RequestMapping("/posts-by-i18n/{i18n}")
    public String postsByI18n(Map<String, Object> model,
                              @PathVariable("i18n") String i18n) {
        try {

            if (!("SK".equals(i18n) || "CZ".equals(i18n))) {
                log.info("Someone tried i18n page by manipulating url !!. i18n value = {}", i18n);
                log.error("Someone tried i18n page by manipulating url !!. i18n value = {}", i18n);
                throw new Exception("Someone tried i18n page by manipulating url !!.");
            }

            Long from = 0L;
            Long to = appProperties.getPageSize();

            model.put("active_index", "");
            model.put("active_posts", "active");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("i18n", i18n);
            model.put("i18n_text", getI18nText(i18n));
            model.put("site", "posts/i18n" + i18n);

            model.put("posts", this.postService.getPostsByI18n(i18n, from, to));
            log.info("posts.html - i18n");
            pageData.set(model, " - " + getI18nText(i18n));
            return "posts_by_i18n";

        } catch (Exception ex) {
            log.warn("index : " + ex.getMessage());
            pageData.set(model, "404 Stránka nenájdená");
            return "redirect:/error.html";
        }
    }


    @RequestMapping("/posts/{label}")
    public String posts(Map<String, Object> model,
                        @PathVariable("label") String label) {

        List<Post> posts;
        Long from = 0L;
        Long to = appProperties.getPageSize();
        try {
            if ("all".equals(label)) {
                posts = this.postService.getPostsByLabelAll(from, to);
                label = "Všetky";
            } else {
                //TODO label in check
                posts = this.postService.getPostsByLabel(label, from, to);
            }

            //diakritics throwing out
            if ("peňaženky".equals(label)) {
                label = "penazenky";
            }

            model.put("active_index", "");
            model.put("active_posts", "active");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("posts", posts);
            model.put("label", label);
            model.put("site", "posts/" + label);

            log.info("posts.html {}", label);
            pageData.set(model, " - Články : " + label);
            return "posts";

        } catch (Exception ex) {
            log.warn("posts : " + ex.getMessage());
            pageData.set(model, "404 Stránka nenájdená");
            return "redirect:/error";
        }
    }

    @RequestMapping("/external-services")
    public String externalServices(Map<String, Object> model) {

        try {

            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "active");
            model.put("active_did_you_know", "");
            model.put("site", "external-services/");

            List<Service> services = new ArrayList<>();

            services.add(new Service("Coinbase", "https://www.coinbase.com", "coinbase-big.png", "Služba na nákup a predaj vybraných kryptomien vybudovaná na beckende GDAxu. Jej výhodou je jednoduchosť."));
            services.add(new Service("Exodus", "https://www.exodus.io", "exodus-big.jpg", "Peňaženka podporujúca množstvo kryptomien plus obsahuje zabudovanú zmenáreň poháňanú ďalšou službou Shapeshift."));
            services.add(new Service("Coinbase.Pro", "https://pro.coinbase.com/", "gdax-big.jpg", "Burza kryptomien patriaca Coinbase. S originálnym názvom GDAX teraz známa ako Coinbase.Pro."));
            services.add(new Service("Poloniex", "https://poloniex.com", "poloniex-big.png", "Americká krypto burza, ktorá vznikla roku 2014 v amerike. Je takisto zaujímavou možnosťou ako začať obchodovať s kryptomenami."));
            services.add(new Service("Jaxx", "https://jaxx.io", "jaxx-big.png", "Peňaženka podporujúca množstvo coinov. Je Lightweight a hot. Nepotrebuje sťahovať celý blockchain, čiže šetrí miesto na hard diskoch."));
            services.add(new Service("Kucoin", "https://www.kucoin.com/", "kucoin-big.png", "Burza kryptomien. Rýchlo a spoľahlivo pridáva nové kryptomeny s potenciálom. Možno tu nájsť kryptomeny aké na iných burzách neuvidíte."));
            services.add(new Service("Kraken", "https://www.kraken.com", "kraken-big.jpg", "Americká burza kryptomien s bohatou históriou.  Vznikla roku 2011. Ďalšia z možností ako začať obchodovať s kryptomenami."));
            services.add(new Service("Neo to Gas", "https://neotogas.com", "neotogas-big.jpg", "Prepočíta, aké množstvo GAS tokenu získate ako pasívny príjem za držanie NEO tokenov v špecializovanej NEON peňaženke."));
            services.add(new Service("ATHA", "https://athda.com", "athda-big.jpg", "Posledné All time high (ATH) kryptomien s časovým údajom. ATH je maximálna hodnota, ktorú kryptomena kedy dosiahla."));
            services.add(new Service("Shapeshift", "https://shapeshift.io/", "shapeshift-big.png", " Je krypto-zmenáreň. Jej hlavnou výhodou je, že sa nie je potrebné registrovať. Slúži na zmenu kryptomien na kryptomeny."));
            services.add(new Service("Ethplorer", "https://ethplorer.io", "ethplorer-big.png", "Po zadaní adresy vie ukázať nielen ETH zostatok na vašej peňaženke, ale aj zostatok všetkých ETH tokenov na danej adrese."));
            services.add(new Service("Binance", "https://www.binance.com", "binance-big.png", "Burza kryptomien. Bola založená v Číne roku 2017. V tom istom roku sa jej viac než darilo. Dnes patrí medzi najväčšie krypto-burzy sveta."));
            services.add(new Service("Bitfinex", "https://www.bitfinex.com", "bitfinex-big.jpg", "Burza kryptomien. Tvorca USDT. Založená bola v roku 2012. Vlastní a prevádzkuje spoločnosť iFinex Inc. "));
            services.add(new Service("Bittrex", "https://bittrex.com", "bittrex-big.png", "Burza kryptomien. Založená bola v amerika roku 2013. AKo prvá ponúkla náhradu za USDT tzv. TUSD (True USD)."));
            services.add(new Service("Bitscreener", "https://bitscreener.com/screener", "bitscreener-big.png", "Sortovanie podľa ceny meny, marketcapu, 24 hodinového volume a typu. Chartview ponúka percentuálny súhrn zmeny."));
            services.add(new Service("Coinmarketcap", "https://coinmarketcap.com", "coinmarketcap-big.png", "Stránka monitorujúca kryptomeny, ktoré sa obchodujú. Ponúka množstvo použiteľných rankingov, grafov a krypto-informácií."));
            services.add(new Service("Cryptocompare", "https://www.cryptocompare.com", "cryptocompare-big.png", "Monitoring kryptomien, rôzne kalkulačky pre ťažiarov, peňaženky podľa kryptomien, pre developerov je tu REST API a ďalšie."));
            services.add(new Service("Coindar", "https://coindar.org", "coindar-big.jpg", "Ide o akýsi krypto-kalendár. Vďaka tejto službe budete informovaný o nadchádzajúcich krypto udalostiach a nič vám už neunikne."));
            services.add(new Service("Crypto Fear & Greed Index", "https://alternative.me/crypto/fear-and-greed-index/", "fear-and-greed-index.png", "Crypto Fear & Greed Index meria aká je aktuálna nálada na kryptomenových trhoch. Kontroluje dve základné nálady a to strach a chamtivosť. "));
            services.add(new Service("Coin atm radar", "https://coinatmradar.com/bitcoin-atm-near-me/", "coin-atm-radar.png", "Webová služba umožňujúca nájsť rôzne kryptomaty pre kúpu a predaj rôznych kryptomien vo vašom najbližšom okolí."));
            services.add(new Service("Airdrops.io", "https://airdrops.io", "airdrops-io.jpg", "Služba monitorujúca takzvané airdropy tokenov, čo sú tokeny zadarmo za splnenie najčastejšie úloh na sociálnych sieťach a podobne."));
            services.add(new Service("Staking rewards", "https://www.stakingrewards.com", "staking-rewards.png", "Vďaka tejto službe si viete pohodlne vyrátať, aký veľký je váš pasívny príjem, ak vlastníte niektorý z proof of stake tokenov."));
            services.add(new Service("Krypto tank", "https://kryptotank.cz/", "kryptotank.png", "Ide o čerpaciu stanicu v Českej republike, kde je možné platiť pohonné látky obľúbeným Bitcoinom."));



            model.put("services", services);

            log.info("external-services.html");
            pageData.set(model, " - Externé služby");
            return "external-services";

        } catch (Exception ex) {
            log.warn("external-services : " + ex.getMessage());
            pageData.set(model, "404 Stránka nenájdená");
            return "redirect:/error";
        }
    }


    @RequestMapping("/dictionary")
    public String dictionary(Map<String, Object> model) {

        try {

            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "active");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("site", "dictionary/");

            log.info("dictionary.html");
            pageData.set(model, " - Slovník");
            return "dictionary";

        } catch (Exception ex) {
            log.warn("dictionary : " + ex.getMessage());
            pageData.set(model, "404 Stránka nenájdená");
            return "redirect:/error";
        }
    }

    @RequestMapping("/did-you-know")
    public String didYouKnow(Map<String, Object> model) {

        try {

            model.put("active_index", "");
            model.put("active_posts", "");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "active");
            model.put("site", "did-you-know/");

            log.info("did-you-know.html");
            pageData.set(model, " - Zaujímavosti");
            return "did-you-know";

        } catch (Exception ex) {
            log.warn("dictionary : " + ex.getMessage());
            pageData.set(model, "404 Stránka nenájdená");
            return "redirect:/error";
        }
    }

    @RequestMapping("/one-post-frame") //TODO iframe
    public String onePostFrame(Map<String, Object> model,
            @RequestParam(value = "post_url", required = false) String postUrl) {
        //try {
            model.put("active_index", "");
            model.put("active_posts", "active");
            model.put("active_dictionary", "");
            model.put("active_external_services", "");
            model.put("active_did_you_know", "");
            model.put("site", "one-post-frame/");

            //String postUrl = "https://kryptotrejder.sk/regulacie-v-usa-ziskavaju-realne-kontury-ohrozia-kryptomenovy-sektor/"; //TODO
            String[] urlParts = postUrl.split("/");
            String postUrlSource = urlParts[2];
            String protocol = urlParts[0].replace(":", "");
            String postUrlSourceLang = postUrlSource.split("\\.")[1];
            String postUrlValue = urlParts[urlParts.length - 1];
            String postTitle = postUrlValue.replace("-"," ") + " - " + postUrlSource;

            model.put("post_url_iframe", postUrl);
            model.put("post_url_rendered", postUrlSource);
            model.put("post_url_value", postUrlValue);

            log.info("one-post-frame.html");
            pageData.set(model, " - " + postTitle);
            return "one-post-frame";

//        } catch (Exception ex) {
//            log.warn("one page frame : " + ex.getMessage());
//            log.warn(ex.getCause() + "" + ex.getStackTrace());
//            pageData.set(model, "404 Stránka nenájdená");
//            return "redirect:/error";
//        }
    }

    private String getBtcPrice() {
        String btcPrice = String.valueOf(btcPriceService.getLatestPrice());
        return btcPrice.substring(0, btcPrice.length() - 2);
    }

    private String getI18nText(String i18n) {
        switch(i18n) {
            case "SK": return "Slovenské články";
            case "CZ": return "České články";
            default : return null; //TODO
        }
    }
}

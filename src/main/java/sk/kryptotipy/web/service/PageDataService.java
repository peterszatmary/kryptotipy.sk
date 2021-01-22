package sk.kryptotipy.web.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.api.PageDataServiceApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

@Service
@Data
@Slf4j
public class PageDataService implements PageDataServiceApi {

    @Value("${page.title}")
    private String pageTitle;

    @Value("${page.description}")
    private String pageDescription;

    @Value("${page.author}")
    private String pageAuthor;

    @Value("${base.url}")
    private String baseUrl;

    @Value("${base.url.without.port}")
    private String baseUrlWithoutPort;

    @Value("${project.version}")
    private String projectVersion;

    @Value("${page.size}")
    private Integer pageSize;

    @Value("${project.post.count}")
    private String postsCount;

    @Value("${page.image}")
    private String pageImagePath;

    // info about if pagination should go to view
    private Boolean paginationOn = Boolean.TRUE;

    private Map<String, String> postsMeta = new HashMap<>();


    public void set(Map<String, Object> model, String pageTitleCustom) {
        model.put("page_title", pageTitleCustom + " | " + pageTitle);
        model.put("page_author", pageAuthor);
        model.put("base_url", baseUrl);
        model.put("base_url_without_port", baseUrlWithoutPort);
        model.put("title", pageTitle);
        model.put("posts_count", postsCount);
        model.put("project_version", projectVersion);
        model.put("page_image", pageImagePath);
        model.put("page_img_width", "85");
        model.put("page_img_height", "85");
        model.put("page_date", LocalDateTime.now());

        addPostsMeta();
    }


    private void addPostsMeta() {
        // articles
        addArticleBtcIsNotTheFirst();
        addArticlePassiveIncome();
        // posts
        addAllPostsMeta();
    }

    private void addAllPostsMeta() {
        postsMeta.put("post.image.all",           baseUrlWithoutPort + "assets/post-images/bitcoin/images_2.jpg");
        postsMeta.put("post.image.bitcoin",       baseUrlWithoutPort + "assets/post-images/bitcoin/images_3.jpg");
        postsMeta.put("post.image.nezaradene",    baseUrlWithoutPort + "assets/post-images/nezaradene/images_1.jpg");
        postsMeta.put("post.image.litecoin",      baseUrlWithoutPort + "assets/post-images/litecoin/images_6.jpg");
        postsMeta.put("post.image.monero",        baseUrlWithoutPort + "assets/post-images/monero/images_3.jpg");
        postsMeta.put("post.image.tezos",         baseUrlWithoutPort + "assets/post-images/tezos/images_8.jpg");
        postsMeta.put("post.image.cosmos",        baseUrlWithoutPort + "assets/post-images/cosmos/images_8.jpg");
        postsMeta.put("post.image.polkadot",      baseUrlWithoutPort + "assets/post-images/polkadot/images_2.jpg");
        postsMeta.put("post.image.algorand",      baseUrlWithoutPort + "assets/post-images/algorand/images_9.jpg");
        postsMeta.put("post.image.kryptomeny",    baseUrlWithoutPort + "assets/post-images/kryptomeny/images_1.jpg");
        postsMeta.put("post.image.blockchain",    baseUrlWithoutPort + "assets/post-images/blockchain/images_2.jpg");
        postsMeta.put("post.image.binance",       baseUrlWithoutPort + "assets/post-images/binance/images_10.jpg");
        postsMeta.put("post.image.bitfinex",      baseUrlWithoutPort + "assets/post-images/bitfinex/images_7.jpg");
        postsMeta.put("post.image.bittrex",       baseUrlWithoutPort + "assets/post-images/bittrex/images_3.jpg");
        postsMeta.put("post.image.dag",           baseUrlWithoutPort + "assets/post-images/dag/images_4.jpg");
        postsMeta.put("post.image.ecb",           baseUrlWithoutPort + "assets/post-images/ecb/images_2.jpg");
        postsMeta.put("post.image.fed",           baseUrlWithoutPort + "assets/post-images/fed/images_2.jpg");
        postsMeta.put("post.image.dash",          baseUrlWithoutPort + "assets/post-images/dash/images_1.jpg");
        postsMeta.put("post.image.exodus",        baseUrlWithoutPort + "assets/post-images/exodus/images_6.jpg");
        postsMeta.put("post.image.neo",           baseUrlWithoutPort + "assets/post-images/neo/images_7.jpg");
        postsMeta.put("post.image.ontology",      baseUrlWithoutPort + "assets/post-images/ontology/images_1.jpg");
        postsMeta.put("post.image.ripple",        baseUrlWithoutPort + "assets/post-images/ripple/images_9.jpg");
        postsMeta.put("post.image.vechain",       baseUrlWithoutPort + "assets/post-images/vechain/images_4.jpg");
        postsMeta.put("post.image.kraken",        baseUrlWithoutPort + "assets/post-images/kraken/images_4.jpg");
        postsMeta.put("post.image.ethereum",      baseUrlWithoutPort + "assets/post-images/ethereum/images_4.jpg");
        postsMeta.put("post.image.halving",       baseUrlWithoutPort + "assets/post-images/halving/images_1.jpg");
        postsMeta.put("post.image.fork",          baseUrlWithoutPort + "assets/post-images/fork/images_3.jpg");
        postsMeta.put("post.image.ico",           baseUrlWithoutPort + "assets/post-images/ico/images_1.jpg");
        postsMeta.put("post.image.coinbase",      baseUrlWithoutPort + "assets/post-images/coinbase/images_1.jpg");
        postsMeta.put("post.image.coinmarketcap", baseUrlWithoutPort + "assets/post-images/coinmarketcap/images_4.jpg");
        postsMeta.put("post.image.burza",         baseUrlWithoutPort + "assets/post-images/burza/images_3.jpg");
        postsMeta.put("post.image.cardano",       baseUrlWithoutPort + "assets/post-images/cardano/images_1.jpg");
        postsMeta.put("post.image.nbs",           baseUrlWithoutPort + "assets/post-images/nbs/images_10.jpg");
        postsMeta.put("post.image.wallets",       baseUrlWithoutPort + "assets/post-images/peňaženky/");
        postsMeta.put("post.image.altcoin",       baseUrlWithoutPort + "assets/post-images/altocin/images_10.jpg");
    }


    private void addArticleBtcIsNotTheFirst() {
        postsMeta.put("post.title.btc-is-not-the-first",
                "Bitcoin nebol prvou kryptomenou");
        postsMeta.put("post.description.btc-is-not-the-first",
                "Mnohí si asi myslia, že prvá kryptomena bol Bitcoin. " +
                        "Nie je to však tak. História kryptomien začala už omnoho skôr. " +
                        "| KRYPTOTIPY.SK");
        postsMeta.put("post.image.btc-is-not-the-first",
                baseUrlWithoutPort + "/assets/img/articles/btc_not_first/ecash-mini.jpg");
        postsMeta.put("post.image.width.btc-is-not-the-first", "638");
        postsMeta.put("post.image.height.btc-is-not-the-first", "414");
        postsMeta.put("post.date.btc-is-not-the-first", LocalDateTime.of(2021, Month.JANUARY, 2, 0, 0) + "");
    }

    private void addArticlePassiveIncome() {
        postsMeta.put("post.title.passive-income-with-exodus",
                "Pasívny zárobok s peňaženkou Exodus alebo prečo sa viac oplatí držať kryptomeny ako eurá v banke");
        postsMeta.put("post.description.passive-income-with-exodus",
                "Pasívny zárobok s peňaženkou Exodus alebo prečo sa viac oplatí držať kryptomeny ako eurá v banke " +
                        "| KRYPTOTIPY.SK");
        postsMeta.put("post.image.passive-income-with-exodus",
                baseUrlWithoutPort  + "assets/img/services/articles/passive-income-with-exodus.jpg");
        postsMeta.put("post.image.width.passive-income-with-exodus", "638");
        postsMeta.put("post.image.height.passive-income-with-exodus", "414");
        postsMeta.put("post.date.passive-income-with-exodus", LocalDateTime.of(2021, Month.JANUARY, 22, 0, 0) + "");
    }

    public String getPageDescription() {
        return this.pageDescription;
    }

    public String getPostTitle(String key) {
        return postsMeta.get("post.title." + key);
    }

    public String getPostDescription(String key) {
        return postsMeta.get("post.description." + key);
    }

    public String getPostImage(String key) {
        return postsMeta.get("post.image." + key);
    }

    @Override
    public String getPostDate(String key) {
        return postsMeta.get("post.date." + key);
    }

    public String getPostImageWidth(String key) {
        return postsMeta.get("post.image.width." + key);
    }

    public String getPostImageHeight(String key) {
        return postsMeta.get("post.image.height." + key);
    }

}
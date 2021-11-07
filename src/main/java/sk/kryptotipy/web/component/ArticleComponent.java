package sk.kryptotipy.web.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sk.kryptotipy.web.AppProperties;

import java.time.LocalDateTime;
import java.time.Month;

import java.util.Map;

@AllArgsConstructor
@Slf4j
@Data
@Component
public class ArticleComponent {
    private final AppProperties appProperties;
    private Map<String, String> postsMeta;

    //TODO preloz

    private void addArticleEthereumDapps2021() {
        var postKey = "ethereum-dapps-today";
        var postTitle = "100+ Ethereum decentralizovaných aplikácií roku 2021";
        var postDescription = "Najperspektívnejšie decentralizované aplikácie roku 2021 postavené na Ehereum blockchaine, ktoré môžu zmeniť internet.";
        var postImagePath = ".jpg";
        var imgWidth = "638";
        var imgHeight = "414";
        var postDate = LocalDateTime.of(2021, Month.NOVEMBER, 6, 0, 0);

        addPost(postKey,
                postTitle, postDescription, postImagePath,
                imgWidth, imgHeight, postDate
        );
    }

    private void addArticlePassiveIncomeWithPolkadotOnLedger() {
        var postKey = "passive-income-with-polkadot-on-ledger";
        var postTitle = "Pasívny príjem s kryptomenou Polkadot (DOT) pomocou Nano Ledger";
        var postDescription = "Hardvérová peňaženka Ledger Nano je prvou hardvérovou peňaženkou na svete, ktorá prináša možnosť stakeovať Polkadot DOT tokeny. Článok rozoberá ako pasívne zarobiť s Polkadot a Ledger.";
        var postImagePathExtension =  ".png";
        var imgWidth = "638";
        var imgHeight = "414";
        var postDate = LocalDateTime.of(2021, Month.MARCH, 6, 0, 0);

        addPost(postKey,
                postTitle, postDescription, postImagePathExtension,
                imgWidth, imgHeight, postDate
        );
    }

    private void addArticleProofOfWork() {
        var postKey = "complete-guide-to-proof-of-work";
        var postTitle = "15 proof of work algoritmov poháňajúcich kryptomeny alebo ako funguje ťažba kryptomien";
        var postDescription = "Kompletný sprievodca algoritmami proof of work (PoW). Článok vysvetľuje aj ako funguje ťažba kryptomien, ako vyzerá blok a blockchain, výhody nevýhody tohto prístupu.";
        var postImagePathExtension =  ".png";
        var imgWidth = "638";
        var imgHeight = "414";
        var postDate = LocalDateTime.of(2021, Month.FEBRUARY, 7, 0, 0);

        addPost(postKey,
                postTitle, postDescription, postImagePathExtension,
                imgWidth, imgHeight, postDate
        );
    }

    private void addArticlePassiveIncomeWithCosmosAtom() {
        var postKey = "passive-income-with-cosmos-atom";
        var postTitle = "Pasívny príjem alebo prečo sa viac oplatí držať kryptomenu ATOM (COSMOS) ako eurá v banke";
        var postDescription = "Pasívny zárobok vďaka kryptomene ATOM v sieti COSMOS. Sieť COSMOS ponúka solídny pasívny príjem a keď, k tomu" +
                " pripočítame atraktivitu projektu, ide o veľmi zaujímavú investíciu a nielen o pasívny príjem.";
        var postImagePathExtension =  ".jpg";
        var imgWidth = "638";
        var imgHeight = "414";
        var postDate = LocalDateTime.of(2021, Month.FEBRUARY, 14, 0, 0);

        addPost(postKey,
                postTitle, postDescription, postImagePathExtension,
                imgWidth, imgHeight, postDate
        );
    }

    private void addArticlePassiveIncomeWithCardano() {
        var postKey = "passive-income-with-cardano";
        var postTitle = "Pasívny príjem alebo prečo sa viac oplatí držať kryptomenu ADA (Cardano) ako eurá v banke";
        var postDescription = "Pasívny zárobok vďaka kryptomene ADA v sieti Cardamo. Sieť Cardano ponúka solídny pasívny príjem a keď, k tomu" +
                " pripočítame atraktivitu projektu, ide nielen o zaujímavú investíciu.";
        var postImagePathExtension =  ".png";
        var imgWidth = "638";
        var imgHeight = "414";
        var postDate = LocalDateTime.of(2021, Month.FEBRUARY, 21, 0, 0);

        addPost(postKey,
                postTitle, postDescription, postImagePathExtension,
                imgWidth, imgHeight, postDate
        );
    }

    private void addArticlePassiveIncomeExodus() {

        var postKey = "passive-income-with-exodus";
        var postTitle = "Pasívny príjem s peňaženkou Exodus alebo prečo sa viac oplatí držať kryptomeny ako eurá v banke";
        var postDescription = "Pasívny zárobok s peňaženkou Exodus alebo prečo sa viac oplatí držať kryptomeny ako eurá v" +
                " banke. 7 kryptomien, 7 spôsobov ako pasívne zarábať.";
        var postImagePath = ".jpg";
        var imgWidth = "638";
        var imgHeight = "414";
        var postDate = LocalDateTime.of(2021, Month.JANUARY, 22, 0, 0);

        addPost(postKey,
                postTitle, postDescription, postImagePath,
                imgWidth, imgHeight, postDate
        );
    }

    private void addArticlePassiveIncomeWithAlgorand() {

        var postKey = "passive-income-with-algorand";
        var postTitle = "Ako pasívne zarábať s kryptomenou Algorand držanou v peňaženke Exodus";
        var postDescription = "Ako pasívne zarábať s kryptomenou Algorand držanou v peňaženke Exodus. " +
                "Algorand ako ďalšia proof of stake kryptomena.";
        var postImagePath = ".jpg";
        var imgWidth = "638";
        var imgHeight = "414";
        var postDate = LocalDateTime.of(2021, Month.JANUARY, 27, 0, 0);

        addPost(postKey,
                postTitle, postDescription, postImagePath,
                imgWidth, imgHeight, postDate
        );
    }

    private void addArticlePassiveIncomeWithNeo() {

        var postKey = "passive-income-with-neo";
        var postTitle = "NEO-spôsoby ako pasívne zarábať";
        var postDescription = "NEO-spôsoby ako pasívne zarábať. Proof of stake kryptomena NEO v peňaženke Exodus a " +
                "možnosť pasívneho zárobku v podobe GAS.";
        var postImagePath = ".jpg";
        var imgWidth = "638";
        var imgHeight = "414";
        var postDate = LocalDateTime.of(2021, Month.JANUARY, 31, 0, 0);

        addPost(postKey,
                postTitle, postDescription, postImagePath,
                imgWidth, imgHeight, postDate
        );
    }

    private void addArticleBtcIsNotTheFirst() {

        var postKey = "btc-is-not-the-first";
        var postTitle = "Bitcoin nebol prvou kryptomenou";
        var postDescription = "Mnohí si asi myslia, že prvá kryptomena bol Bitcoin. " +
                "Nie je to však tak. História kryptomien začala už omnoho skôr.";
        var postImagePath = ".png";
        var imgWidth = "638";
        var imgHeight = "414";
        var postDate = LocalDateTime.of(2021, Month.JANUARY, 2, 0, 0);

        addPost(postKey,
                postTitle, postDescription, postImagePath,
                imgWidth, imgHeight, postDate
        );
    }

    private void addArticlePassiveIncomeWithVechain() {

        var postKey = "passive-income-with-vechain";
        var postTitle = "Pasívny príjem alebo prečo sa viac oplatí držať kryptomenu Vechain ako eurá v banke";
        var postDescription = "Ako pasívne zarábať s kryptomenou Vechain držanou v peňaženke Exodus.";//TODO
        var postImagePath = ".jpg";
        var imgWidth = "638";
        var imgHeight = "414";
        var postDate = LocalDateTime.of(2021, Month.FEBRUARY, 26, 0, 0);

        addPost(postKey,
                postTitle, postDescription, postImagePath,
                imgWidth, imgHeight, postDate
        );
    }

    private void addTelegramNewsBot() {
        var postKey = "kryptotipy_news_bot";
        var postTitle = "Nový telegram bot, ktorý vám nájde najčerstvejšie krypto novinky @kryptotipy_news_bot";
        var postDescription = "Články na tému kryptomeny možno hľadať jednoducho pomocou nového bota Kryptotipy aktuality @kryptotipy_news_bot .";
        var postImagePathExtension =  ".png";
        var imgWidth = "638";
        var imgHeight = "414";
        var postDate = LocalDateTime.of(2021, Month.APRIL, 2, 0, 0);

        addPost(postKey,
                postTitle, postDescription, postImagePathExtension,
                imgWidth, imgHeight, postDate
        );
    }

    private void addAllPostsMeta() {
        var baseUrlWithoutPort = appProperties.getBaseUrlWithoutPort();
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
        postsMeta.put("post.image.wallets",       baseUrlWithoutPort + "assets/post-images/peňaženky/images_2.jpg");
        postsMeta.put("post.image.altcoin",       baseUrlWithoutPort + "assets/post-images/altocin/images_10.jpg");
        postsMeta.put("post.image.nft",           baseUrlWithoutPort + "assets/post-images/nft/images_1.jpg");
    }

    private void addPost(
            String postKey,
            String postTitle,
            String postDescription,
            String postImagePathExtension,
            String imgWidth,
            String imgHeight,
            LocalDateTime postDate
    ) {
        postsMeta.put("post.title." + postKey, postTitle);
        postsMeta.put("post.description." + postKey, postDescription);
        postsMeta.put("post.image." + postKey,
                this.appProperties.getBaseUrlWithoutPort() +
                        "assets/img/articles/index/" +
                        postKey +
                        postImagePathExtension);
        postsMeta.put("post.image.width." + postKey, imgWidth);
        postsMeta.put("post.image.height." + postKey, imgHeight);
        postsMeta.put("post.date." + postKey,  postDate + "");
    }

    public void addPostsAndArticlesMeta() {

        addArticleBtcIsNotTheFirst();
        addArticlePassiveIncomeExodus();
        addArticlePassiveIncomeWithAlgorand();
        addArticlePassiveIncomeWithCardano();
        addArticlePassiveIncomeWithCosmosAtom();
        addArticlePassiveIncomeWithVechain();
        addArticleProofOfWork();
        addArticlePassiveIncomeWithNeo();
        addArticlePassiveIncomeWithPolkadotOnLedger();
        addArticleEthereumDapps2021();
        addAllPostsMeta();
        //addTelegramNewsBot();
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
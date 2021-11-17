package sk.kryptotipy.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.kryptotipy.web.data.Article;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ArticleData {

    //TODO preloz
    //TODO zjednot meta a nie meta
    //TODO category datum nie manualne
    
    @Bean
    public List<Article> articles() {
        var articles = new ArrayList<Article>();
        //articles.add(this.spiritNft());
        articles.add(this.ethereumDapps2021());
        articles.add(this.passiveIncomeWithPolkadotOnLedger());
        articles.add(this.passiveIncomeWithVechain());
        articles.add(this.passiveIncomeWithCardano());
        articles.add(this.passiveIncomeWithCosmosAtom());
        articles.add(this.proofOfWork());
        articles.add(this.passiveIncomeWithAlgorand());
        articles.add(this.passiveIncomeWithNeo());
        articles.add(this.passiveIncomeExodus());
        articles.add(this.btcIsNotTheFirst());
        return articles;
    }


    private Article spiritNft() {
        var dateTime = LocalDateTime.of(2021, Month.NOVEMBER, 15, 0, 0);
        var datePlusOne = Date.valueOf(dateTime.toLocalDate().plusDays(1));
        var postMeta =  Article.PostMeta.builder()
                .metaPostKey("majk-spirit-wexo-nft")
                .metaPostTitle("Wexo a Majk Spirit - Prvá digitálna NFT charita")
                .metaPostDescription("Prvá digitálna NFT charita v spolupráci Wexo s raperom Majkom Spiritom prinášajú jedinečnú kolekciu 5000 NFT tokenov.")
                .metaPostImageExtension(".jpeg")
                .metaImgWidth("638")
                .metaImgHeight("414")
                .metaPostDate(dateTime)
                .build();
        return Article.builder()
                .rbTitleKey("article.majk.spirit.nft")
                .rbCategoryKey("article.category.press.release")
                .imgSrc("majk-spirit-nft.jpeg")
                .pubDate(datePlusOne)
                .category("15.11.2021")
                .url("article/majk-spirit-wexo-nft")
                .postMeta(postMeta)
                .build();
    }

    private Article ethereumDapps2021() {
        var dateTime = LocalDateTime.of(2021, Month.MARCH, 6, 0, 0);
        var datePlusOne = Date.valueOf(dateTime.toLocalDate().plusDays(1));
        var postMeta =  Article.PostMeta.builder()
                .metaPostKey("ethereum-dapps-today")
                .metaPostTitle("100+ Ethereum decentralizovaných aplikácií roku 2021")
                .metaPostDescription("Najperspektívnejšie decentralizované aplikácie roku 2021 postavené na Ehereum blockchaine, ktoré môžu zmeniť internet.")
                .metaPostImageExtension(".jpg")
                .metaImgWidth("638")
                .metaImgHeight("414")
                .metaPostDate(dateTime)
                .build();
        return Article.builder()
                .rbTitleKey("article.ethereum.dapps")
                .rbCategoryKey("article.category.ethereum.dapps")
                .imgSrc("ethereum-dapps-today.jpg")
                .pubDate(datePlusOne)
                .category("6.11.2021")
                .url("/article/ethereum-dapps-today")
                .postMeta(postMeta)
                .build();
    }

    private Article btcIsNotTheFirst() {
        var dateTime = LocalDateTime.of(2021, Month.JANUARY, 2, 0, 0);
        var datePlusOne = Date.valueOf(dateTime.toLocalDate().plusDays(1));
        var postMeta =  Article.PostMeta.builder()
                .metaPostKey("btc-is-not-the-first")
                .metaPostTitle("Bitcoin nebol prvou kryptomenou")
                .metaPostDescription("Mnohí si asi myslia, že prvá kryptomena bol Bitcoin. " +
                        "Nie je to však tak. História kryptomien začala už omnoho skôr.")
                .metaPostImageExtension(".png")
                .metaImgWidth("638")
                .metaImgHeight("414")
                .metaPostDate(dateTime)
                .build();
        return Article.builder()
                .rbTitleKey("article.btc.not.first")
                .rbCategoryKey("article.category.crypto.history")
                .imgSrc("btc-not-first.png")
                .pubDate(datePlusOne)
                .category("2.1.2021")
                .url("/article/btc-is-not-the-first")
                .postMeta(postMeta)
                .build();
    }

    private Article passiveIncomeExodus() {
        var dateTime = LocalDateTime.of(2021, Month.JANUARY, 17, 0, 0);
        var datePlusOne = Date.valueOf(dateTime.toLocalDate().plusDays(1));
        var postMeta =  Article.PostMeta.builder()
                .metaPostKey("passive-income-with-exodus")
                .metaPostTitle("Pasívny príjem s peňaženkou Exodus alebo prečo sa viac oplatí držať kryptomeny ako eurá v banke")
                .metaPostDescription("Pasívny zárobok s peňaženkou Exodus alebo prečo sa viac oplatí držať kryptomeny ako eurá v" +
                        " banke. 7 kryptomien, 7 spôsobov ako pasívne zarábať.")
                .metaPostImageExtension(".jpg")
                .metaImgWidth("638")
                .metaImgHeight("414")
                .metaPostDate(dateTime)
                .build();
        return Article.builder()
                .rbTitleKey("article.exodus")
                .rbCategoryKey("article.category.passive.income")
                .imgSrc("passive-income-with-exodus.jpg")
                .pubDate(datePlusOne)
                .category("17.1.2021")
                .url("article/passive-income-with-exodus")
                .postMeta(postMeta)
                .build();
    }

    private Article passiveIncomeWithAlgorand() {
        var dateTime = LocalDateTime.of(2021, Month.JANUARY, 27, 0, 0);
        var datePlusOne = Date.valueOf(dateTime.toLocalDate().plusDays(1));
        var postMeta =  Article.PostMeta.builder()
                .metaPostKey("passive-income-with-algorand")
                .metaPostTitle("Ako pasívne zarábať s kryptomenou Algorand držanou v peňaženke Exodus")
                .metaPostDescription("Ako pasívne zarábať s kryptomenou Algorand držanou v peňaženke Exodus. " +
                        "Algorand ako ďalšia proof of stake kryptomena.")
                .metaPostImageExtension(".jpg")
                .metaImgWidth("638")
                .metaImgHeight("414")
                .metaPostDate(dateTime)
                .build();
        return Article.builder()
                .rbTitleKey("article.exodus.algorand")
                .rbCategoryKey("article.category.passive.income")
                .imgSrc("passive-income-with-algorand.jpg")
                .pubDate(datePlusOne)
                .category("27.1.2021")
                .url("article/passive-income-with-algorand")
                .postMeta(postMeta)
                .build();
    }

    private Article passiveIncomeWithCardano() {
        var dateTime = LocalDateTime.of(2021, Month.FEBRUARY, 21, 0, 0);
        var datePlusOne = Date.valueOf(dateTime.toLocalDate().plusDays(1));
        var postMeta =  Article.PostMeta.builder()
                .metaPostKey("passive-income-with-cardano")
                .metaPostTitle("Pasívny príjem alebo prečo sa viac oplatí držať kryptomenu ADA (Cardano) ako eurá v banke")
                .metaPostDescription("Pasívny zárobok vďaka kryptomene ADA v sieti Cardamo. Sieť Cardano ponúka solídny pasívny príjem a keď, k tomu" +
                        " pripočítame atraktivitu projektu, ide nielen o zaujímavú investíciu.")
                .metaPostImageExtension(".png")
                .metaImgWidth("638")
                .metaImgHeight("414")
                .metaPostDate(dateTime)
                .build();
        return Article.builder()
                .rbTitleKey("article.exodus.cardano")
                .rbCategoryKey("article.category.passive.income")
                .imgSrc("passive-income-with-cardano.png")
                .pubDate(datePlusOne)
                .category("21.2.2021")
                .url("/article/passive-income-with-cardano")
                .postMeta(postMeta)
                .build();
    }

    private Article passiveIncomeWithCosmosAtom() {
        var dateTime = LocalDateTime.of(2021, Month.FEBRUARY, 14, 0, 0);
        var datePlusOne = Date.valueOf(dateTime.toLocalDate().plusDays(1));
        var postMeta =  Article.PostMeta.builder()
                .metaPostKey("passive-income-with-cosmos-atom")
                .metaPostTitle("Pasívny príjem alebo prečo sa viac oplatí držať kryptomenu ATOM (COSMOS) ako eurá v banke")
                .metaPostDescription("Pasívny zárobok vďaka kryptomene ATOM v sieti COSMOS. Sieť COSMOS ponúka solídny pasívny príjem a keď, k tomu" +
                        " pripočítame atraktivitu projektu, ide o veľmi zaujímavú investíciu a nielen o pasívny príjem.")
                .metaPostImageExtension(".jpg")
                .metaImgWidth("638")
                .metaImgHeight("414")
                .metaPostDate(dateTime)
                .build();
        return Article.builder()
                .rbTitleKey("article.exodus.cosmos")
                .rbCategoryKey("article.category.passive.income")
                .imgSrc("passive-income-with-cosmos-atom.jpg")
                .pubDate(datePlusOne)
                .category("14.2.2021")
                .url("/article/passive-income-with-cosmos-atom")
                .postMeta(postMeta)
                .build();
    }

    private Article passiveIncomeWithVechain() {
        var dateTime = LocalDateTime.of(2021, Month.FEBRUARY, 26, 0, 0);;
        var datePlusOne = Date.valueOf(dateTime.toLocalDate().plusDays(1));
        var postMeta =  Article.PostMeta.builder()
                .metaPostKey("passive-income-with-vechain")
                .metaPostTitle("Pasívny príjem alebo prečo sa viac oplatí držať kryptomenu Vechain ako eurá v banke")
                .metaPostDescription("Ako pasívne zarábať s kryptomenou Vechain držanou v peňaženke Exodus.")
                .metaPostImageExtension(".jpg")
                .metaImgWidth("638")
                .metaImgHeight("414")
                .metaPostDate(dateTime)
                .build();
        return Article.builder()
                .rbTitleKey("article.exodus.vechain")
                .rbCategoryKey("article.category.passive.income")
                .imgSrc("passive-income-with-vechain.jpg")
                .pubDate(datePlusOne)
                .category("28.2.2021")
                .url("/article/passive-income-with-vechain")
                .postMeta(postMeta)
                .build();
    }

    private Article proofOfWork() {
        var dateTime = LocalDateTime.of(2021, Month.FEBRUARY, 7, 0, 0);
        var datePlusOne = Date.valueOf(dateTime.toLocalDate().plusDays(1));
        var postMeta =  Article.PostMeta.builder()
                .metaPostKey("complete-guide-to-proof-of-work")
                .metaPostTitle("15 proof of work algoritmov poháňajúcich kryptomeny alebo ako funguje ťažba kryptomien")
                .metaPostDescription("Kompletný sprievodca algoritmami proof of work (PoW). Článok vysvetľuje aj ako funguje ťažba kryptomien, ako vyzerá blok a blockchain, výhody nevýhody tohto prístupu.")
                .metaPostImageExtension(".png")
                .metaImgWidth("638")
                .metaImgHeight("414")
                .metaPostDate(dateTime)
                .build();
        return Article.builder()
                .rbTitleKey("article.proof.of.work")
                .rbCategoryKey("article.category.crypto.mining")
                .imgSrc("complete-guide-to-proof-of-work.jpg")
                .pubDate(datePlusOne)
                .category("7.2.2021")
                .url("/article/complete-guide-to-proof-of-work")
                .postMeta(postMeta)
                .build();
    }

    private Article passiveIncomeWithNeo() {
        var dateTime = LocalDateTime.of(2021, Month.JANUARY, 31, 0, 0);
        var datePlusOne = Date.valueOf(dateTime.toLocalDate().plusDays(1));
        var postMeta =  Article.PostMeta.builder()
                .metaPostKey("passive-income-with-neo")
                .metaPostTitle("NEO-spôsoby ako pasívne zarábať")
                .metaPostDescription("NEO-spôsoby ako pasívne zarábať. Proof of stake kryptomena NEO v peňaženke Exodus a " +
                        "možnosť pasívneho zárobku v podobe GAS.")
                .metaPostImageExtension(".jpg")
                .metaImgWidth("638")
                .metaImgHeight("414")
                .metaPostDate(dateTime)
                .build();
        return Article.builder()
                .rbTitleKey("article.exodus.neo")
                .rbCategoryKey("article.category.passive.income")
                .imgSrc("passive-income-with-neo.png")
                .pubDate(datePlusOne)
                .category("31.1.2021")
                .url("article/passive-income-with-neo")
                .postMeta(postMeta)
                .build();
    }

    private Article passiveIncomeWithPolkadotOnLedger() {
        var dateTime = LocalDateTime.of(2021, Month.MARCH, 6, 0, 0);
        var datePlusOne = Date.valueOf(dateTime.toLocalDate().plusDays(1));
        var postMeta =  Article.PostMeta.builder()
                .metaPostKey("passive-income-with-polkadot-on-ledger")
                .metaPostTitle("Pasívny príjem s kryptomenou Polkadot (DOT) pomocou Nano Ledger")
                .metaPostDescription("Hardvérová peňaženka Ledger Nano je prvou hardvérovou peňaženkou na svete, ktorá prináša možnosť stakeovať Polkadot DOT tokeny. Článok rozoberá ako pasívne zarobiť s Polkadot a Ledger.")
                .metaPostImageExtension(".png")
                .metaImgWidth("638")
                .metaImgHeight("414")
                .metaPostDate(dateTime)
                .build();
        return Article.builder()
                .rbTitleKey("article.exodus.polkadot")
                .rbCategoryKey("article.category.passive.income")
                .imgSrc("passive-income-with-polkadot-on-ledger.png")
                .pubDate(datePlusOne)
                .category("6.3.2021")
                .url("/article/passive-income-with-polkadot-on-ledger")
                .postMeta(postMeta)
                .build();
    }
}

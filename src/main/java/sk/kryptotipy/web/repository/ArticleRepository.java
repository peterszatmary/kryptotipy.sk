package sk.kryptotipy.web.repository;

import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Article;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

//TODO encoding problems are caused by external library rome. but coinly is able to read it as it is.
//
@Repository
public class ArticleRepository {


    public List<Article> getKryptotipyContent(ResourceBundle rb) {
        var services = new ArrayList<Article>();

        services.addAll(getKryptotipyContentView(rb));

        services.add(new Article(
                rb.getString("article.exodus.algorand"),
                "article/passive-income-with-algorand",
                "passive-income-with-algorand.jpg",
                "27.1.2021 - " + rb.getString("article.category.passive.income"),
                "",
                Date.valueOf(LocalDate.of(2021, Month.JANUARY, 27+1))
        ));

        services.add(new Article(
                rb.getString("article.btc.not.first"),
                "/article/btc-is-not-the-first",
                "btc-not-first.png",
                "2.1.2021 - " + rb.getString("article.category.crypto.history"),
                "",
                Date.valueOf(LocalDate.of(2021, Month.JANUARY, 2))));

        return services;
    }



    public List<Article> getKryptotipyContentView(ResourceBundle rb) {

        var services = new ArrayList<Article>();

        //TODO +1 day because Iam stupid and date does not contain Time Zone

//        services.add(new Article(
//                rb.getString("article.telegram.newsbot"),
//                "/article/kryptotipy_news_bot",
//                "kryptotipy_news_bot.png",
//                "2.4.2021 - " + "TELEGRAM",
//                "",
//                Date.valueOf(LocalDate.of(2021, Month.APRIL, 2+1))
//        ));

        services.add(new Article(
                rb.getString("article.ethereum.dapps"),
                "/article/ethereum-dapps-today",
                "ethereum-dapps-2021.jpg",
                "6.11.2021 - " + rb.getString("article.category.ethereum.dapps"),
                "",
                Date.valueOf(LocalDate.of(2021, Month.MARCH, 6+1))
        ));

        services.add(new Article(
                rb.getString("article.exodus.polkadot"),
                "/article/passive-income-with-polkadot-on-ledger",
                "passive-income-with-polkadot-on-ledger.png",
                "6.3.2021 - " + rb.getString("article.category.passive.income"),
                "",
                Date.valueOf(LocalDate.of(2021, Month.MARCH, 6+1))
        ));

        services.add(new Article(
                rb.getString("article.exodus.vechain"),
                "/article/passive-income-with-vechain",
                "passive-income-with-vechain.jpg",
                "28.2.2021 - " + rb.getString("article.category.passive.income"),
                "",
                Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 26+1))
        ));

        services.add(new Article(
                rb.getString("article.exodus.cardano"),
                "/article/passive-income-with-cardano",
                "passive-income-with-cardano.png",
                "21.2.2021 - " + rb.getString("article.category.passive.income"),
                "",
                Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 21+1))
        ));

        services.add(new Article(
                rb.getString("article.exodus.cosmos"),
                "/article/passive-income-with-cosmos-atom",
                "passive-income-with-cosmos-atom.jpg",
                "14.2.2021 - " + rb.getString("article.category.passive.income"),
                "",
                Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 14+1))
        ));

        services.add(new Article(
                rb.getString("article.proof.of.work"),
                "/article/complete-guide-to-proof-of-work",
                "complete-guide-to-proof-of-work.jpg",
                "7.2.2021 - " + rb.getString("article.category.crypto.mining"),
                "",
                Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 7+1))
        ));

        services.add(new Article(
                rb.getString("article.exodus.neo"),
                "article/passive-income-with-neo",
                "passive-income-with-neo.png",
                "31.1.2021 - " + rb.getString("article.category.passive.income"),
                "",
                Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 1))
        ));

        services.add(new Article(
                rb.getString("article.exodus"),
                "article/passive-income-with-exodus",
                "passive-income-with-exodus.jpg",
                "17.1.2021 - " + rb.getString("article.category.passive.income"),
                "",
                Date.valueOf(LocalDate.of(2021, Month.JANUARY, 17+1))
        ));

        return services;
    }
}

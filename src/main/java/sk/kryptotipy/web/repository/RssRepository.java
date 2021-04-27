package sk.kryptotipy.web.repository;

import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Article;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


//TODO not in use!!!!!!
@Repository
public class RssRepository {

    public List<Article> getKryptotipyContentForRss() {
        System.out.println("S\u00e3o Paulo");
        List<Article> services = new ArrayList<>();

        services.add(new Article(
                "Nový telegram bot, ktorý vám nájde najčerstvejšie krypto novinky @kryptotipy_news_bot",
                "/article/kryptotipy_news_bot",
                null,
                "",
                "Telegram",
                Date.valueOf(LocalDate.of(2021, Month.APRIL, 2+1))
        ));

        services.add(new Article(
                "Pasívny príjem s kryptomenou Polkadot (DOT) pomocou Nano Ledger",
                "/article/passive-income-with-polkadot-on-ledger",
                null,
                "",
                "Pasívny príjem",
                Date.valueOf(LocalDate.of(2021, Month.MARCH, 6+1))
        ));

        services.add(new Article(
                "Pasívny príjem alebo prečo sa viac oplatí držať kryptomenu Vechain ako eurá v banke",
                "/article/passive-income-with-vechain",
                null,
                "",
                "Pasívny príjem",
                Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 26+1))
        ));

        services.add(new Article(
                "Pasívny príjem alebo prečo sa viac oplatí držať kryptomenu ADA (Cardano) ako eurá v banke",
                "/article/passive-income-with-cardano",
                null,
                "",
                "Pasívny príjem",
                Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 21+1))
        ));

        services.add(new Article(
                "Pasívny príjem alebo prečo sa viac oplatí držať kryptomenu ATOM (COSMOS) ako eurá v banke",
                "/article/passive-income-with-cosmos-atom",
                null,
                "",
                "Pasívny príjem",
                Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 14+1))
        ));

        services.add(new Article(
                "15 proof of work algoritmov poháňajúcich kryptomeny alebo ako funguje ťažba kryptomien",
                "/article/complete-guide-to-proof-of-work",
                null,
                "",
                "Krypto mining",
                Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 7+1))
        ));

        services.add(new Article(
                "NEO-spôsoby ako pasívne zarábať",
                "article/passive-income-with-neo",
                null,
                "",
                "Pasívny príjem",
                Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 1))
        ));

        services.add(new Article(
                "Ako pasívne zarábať s kryptomenou Algorand držanou v peňaženke Exodus",
                "article/passive-income-with-algorand",
                null,
                "",
                "Pasívny príjem",
                Date.valueOf(LocalDate.of(2021, Month.JANUARY, 27+1))
        ));

        services.add(new Article(
                "Pasívny príjem s peňaženkou Exodus alebo prečo sa viac oplatí držať kryptomeny ako eurá v banke",
                "article/passive-income-with-exodus",
                null,
                "",
                "Pasívny príjem",
                Date.valueOf(LocalDate.of(2021, Month.JANUARY, 17+1))
        ));

        services.add(new Article(
                "Bitcoin nebol prvou kryptomenou",
                "/article/btc-is-not-the-first",
                null,
                "",
                "História",
                Date.valueOf(LocalDate.of(2021, Month.JANUARY, 2))));

        return services;
    }
}

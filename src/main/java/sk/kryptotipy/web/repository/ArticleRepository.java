package sk.kryptotipy.web.repository;

import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Article;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Repository
public class ArticleRepository {
    public List<Article> getKryptotipyContent() {
        List<Article> services = new ArrayList<>();

        services.addAll(getKryptotipyContentView());

        services.add(new Article(
                "15 proof of work algoritmov poháňajúcich kryptomeny alebo ako funguje ťažba kryptomien",
                "/article/complete-guide-to-proof-of-work",
                "complete-guide-to-proof-of-work.jpg",
                "7.2.2021 - KRYPTO MINING",
                "",
                Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 7+1))
        ));

        services.add(new Article(
                "NEO-spôsoby ako pasívne zarábať.  Exodus a ďalší pasívny príjem.",
                "article/passive-income-with-neo",
                "passive-income-with-neo.png",
                "31.1.2021 - PASÍVNY PRÍJEM",
                "",
                Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 1))
        ));

        services.add(new Article(
                "Ako pasívne zarábať s kryptomenou Algorand držanou v peňaženke Exodus",
                "article/passive-income-with-algorand",
                "passive-income-with-algorand.jpg",
                "27.1.2021 - PASÍVNY PRÍJEM",
                "",
                Date.valueOf(LocalDate.of(2021, Month.JANUARY, 27+1))
        ));

        services.add(new Article(
                "Pasívny zárobok s peňaženkou Exodus alebo prečo sa viac oplatí držať kryptomeny ...",
                "article/passive-income-with-exodus",
                "passive-income-with-exodus.jpg",
                "17.1.2021 - PASÍVNY PRÍJEM",
                "",
                Date.valueOf(LocalDate.of(2021, Month.JANUARY, 17+1))
        ));

        services.add(new Article(
                "Bitcoin nebol prvou kryptomenou, Ecash a zrod skutočne prvej digitálnej kryptomeny.",
                "/article/btc-is-not-the-first",
                "btc-not-first.png",
                "2.1.2021 - KRYPTO HISTÓRIA",
                "",
                Date.valueOf(LocalDate.of(2021, Month.JANUARY, 2))));

        return services;
    }



    public List<Article> getKryptotipyContentView() {

        List<Article> services = new ArrayList<>();

        //TODO +1 day because Iam stupid and date does not contain Time Zone

        services.add(new Article(
                "Pasívny zárobok s kryptomenu Polkadot (DOT) pomocou Nano Ledger",
                "/article/passive-income-with-polkadot-on-ledger",
                "polka-ledger-nano-x.png",
                "6.3.2021 - PASÍVNY PRÍJEM",
                "",
                Date.valueOf(LocalDate.of(2021, Month.MARCH, 6+1))
        ));

        services.add(new Article(
                "Pasívny zárobok alebo prečo sa viac oplatí držať kryptomenu VeChain (VET) ako eurá v banke",
                "/article/passive-income-with-vechain",
                "passive-income-with-vechain.jpg",
                "28.2.2021 - PASÍVNY PRÍJEM",
                "",
                Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 26+1))
        ));

        services.add(new Article(
                "Pasívny zárobok alebo prečo sa viac oplatí držať kryptomenu ADA ako eurá v banke",
                "/article/passive-income-with-cardano",
                "passive-income-with-cardano.png",
                "21.2.2021 - PASÍVNY PRÍJEM",
                "",
                Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 21+1))
        ));

        services.add(new Article(
                "Pasívny zárobok alebo prečo sa viac oplatí držať kryptomenu ATOM ako eurá v banke",
                "/article/passive-income-with-cosmos-atom",
                "passive-income-with-cosmos-atom.jpg",
                "14.2.2021 - PASÍVNY PRÍJEM",
                "",
                Date.valueOf(LocalDate.of(2021, Month.FEBRUARY, 14+1))
        ));

        return services;
    }
}

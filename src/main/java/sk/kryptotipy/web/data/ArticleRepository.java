package sk.kryptotipy.web.data;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {
    public List<Article> getKryptotipyContent() {
        List<Article> services = new ArrayList<>();

        services.addAll(getKryptotipyContentView());

        services.add(new Article(
                "Bitcoin nebol prvou kryptomenou, Ecash a zrod skutočne prvej digitálnej kryptomeny.",
                "/article/btc-is-not-the-first",
                "btc-not-first.png",
                "2.1.2021 - KRYPTO HISTÓRIA",
                ""));

        return services;
    }

    public List<Article> getKryptotipyContentView() {
        List<Article> services = new ArrayList<>();

        services.add(new Article(
                "15 proof of work algoritmov poháňajúcich kryptomeny alebo ako funguje ťažba kryptomien", //TODO
                "/article/complete-guide-to-proof-of-work",
                "complete-guide-to-proof-of-work.jpg",
                "7.2.2021 - KRYPTO MINING",
                ""));

        services.add(new Article(
                "NEO-spôsoby ako pasívne zarábať.  Exodus a ďalší pasívny príjem.",
                "article/passive-income-with-neo",
                "passive-income-with-neo.png",
                "31.1.2021 - PASÍVNY PRÍJEM",
                ""));

        services.add(new Article(
                "Ako pasívne zarábať s kryptomenou Algorand držanou v peňaženke Exodus",
                "article/passive-income-with-algorand",
                "passive-income-with-algorand.jpg",
                "27.1.2021 - PASÍVNY PRÍJEM",
                ""));

        services.add(new Article(
                "Pasívny zárobok s peňaženkou Exodus alebo prečo sa viac oplatí držať kryptomeny ...",
                "article/passive-income-with-exodus",
                "passive-income-with-exodus.jpg",
                "17.1.2021 - PASÍVNY PRÍJEM",
                ""));

        return services;
    }
}

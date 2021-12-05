package sk.kryptotipy.web.repository;

import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Sitemap;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class SiteRepository {
    public List<Sitemap.URL> getSites() {

        var sites = new ArrayList<Sitemap.URL>();
        
        sites.add(new Sitemap.URL("https://www.kryptotipy.sk/", 1.0, new Date()));
        sites.add(new Sitemap.URL("https://en.kryptotipy.sk/", 1.0, new Date()));
        sites.add(new Sitemap.URL("https://de.kryptotipy.sk/", 1.0, new Date()));
        sites.add(new Sitemap.URL("https://pl.kryptotipy.sk/", 1.0, new Date()));
        sites.add(new Sitemap.URL("https://nl.kryptotipy.sk/", 1.0, new Date()));
        sites.add(new Sitemap.URL("https://hu.kryptotipy.sk/", 1.0, new Date()));

        this.createSitesWithPriority08(sites);
        //this.createSitesWithPriority064(sites);
        this.manualWithPriority08(sites);
        this.manualWithPriority064(sites);

        return sites;
    }


    private void createSitesWithPriority08(List<Sitemap.URL> sites) {

        var _08Sites = Arrays.asList(
                "posts/neo", "posts/litecoin", "posts/polkadot", "posts/ethereum", "posts/bitcoin",
                "posts/kusama", "posts/wallets",
                "posts/blockchain", "posts/kraken", "posts/altcoin", "posts/cryptocurrency",
                "posts/solana", "posts/chainlink",
                "posts/monero", "posts/ontology", "posts/fed", "posts/ecb", "posts/cardano", "posts/ripple",
                "posts/dag", "posts/binance",
                "posts/nbs", "posts/fork", "posts/exodus", "posts/coinbase", "posts/algorand",
                "posts/halving", "posts/tezos", "posts/play-to-earn", "posts/metaverse",
                "posts/iota", "posts/halving", "posts/exchange",
                "posts/vechain", "posts/bitfinex",
                "posts/bittrex", "posts/cosmos", "posts/uniswap", "posts/coinmarketcap", "posts/aave",
                "posts/stellar", "posts/nft",
                "posts/avalanche", "posts/terra", "posts/polygon", "posts/dogecoin",
                "posts/elrond", "posts/tether", "posts/shiba-inu", "posts/axie-infinity",
                "posts/filecoin", "posts/tron",
                "posts/tron", "posts/ftx", "posts/theta", "posts/dai", "posts/airdrops",
                "posts/hedera", "posts/sandbox", "posts/fantom", "posts/helium",
                "posts/loopring", "posts/eos", "posts/flow", "posts/decentraland", "posts/diem",
                "posts-by-i18n/GB", "posts-by-i18n/SK", "posts-by-i18n/CZ", "posts-by-i18n/HU",
                "posts-by-i18n/CH", "posts-by-i18n/US", "posts-by-i18n/CA", "posts-by-i18n/PL",
                "posts-by-i18n/DE", "posts-by-i18n/CH", "posts-by-i18n/NL", "posts-by-i18n/ES",
                "posts-by-i18n/SE"
        );


        _08Sites.stream().
                forEach(site -> {
                    sites.add(new Sitemap.URL("https://www.kryptotipy.sk/" + site, 0.8, new Date()));
                    sites.add(new Sitemap.URL("https://en.kryptotipy.sk/" + site, 0.8, new Date()));
                    sites.add(new Sitemap.URL("https://de.kryptotipy.sk/" + site, 0.8, new Date()));
                    sites.add(new Sitemap.URL("https://pl.kryptotipy.sk/" + site, 0.8, new Date()));
                    sites.add(new Sitemap.URL("https://nl.kryptotipy.sk/" + site, 0.8, new Date()));
                    sites.add(new Sitemap.URL("https://hu.kryptotipy.sk/" + site, 0.8, new Date()));
        });
    }


    private void manualWithPriority064(List<Sitemap.URL> sites) {

        var manualSites = Arrays.asList(
                "article/ethereum-dapps-today",
                "article/complete-guide-to-proof-of-work",
                "article/passive-income-with-neo", "article/passive-income-with-exodus",
                "article/btc-is-not-the-first", "article/passive-income-with-algorand",
                "article/passive-income-with-polkadot-on-ledger", "article/passive-income-with-vechain",
                "article/passive-income-with-cardano", "article/passive-income-with-cosmos-atom",
                "cooperation", "article/majk-spirit-wexo-nft"
        );

        manualSites.stream().
                forEach(site -> {
                    sites.add(new Sitemap.URL("https://www.kryptotipy.sk/" + site, 0.64, localDateToDate(LocalDate.of(2021, 11, 15))));
                    sites.add(new Sitemap.URL("https://en.kryptotipy.sk/" + site, 0.64, localDateToDate(LocalDate.of(2021, 11, 15))));
                    sites.add(new Sitemap.URL("https://de.kryptotipy.sk/" + site, 0.64, localDateToDate(LocalDate.of(2021, 11, 15))));
                    sites.add(new Sitemap.URL("https://pl.kryptotipy.sk/" + site, 0.64, localDateToDate(LocalDate.of(2021, 11, 15))));
                    sites.add(new Sitemap.URL("https://nl.kryptotipy.sk/" + site, 0.64, localDateToDate(LocalDate.of(2021, 11, 15))));
                    sites.add(new Sitemap.URL("https://hu.kryptotipy.sk/" + site, 0.64, localDateToDate(LocalDate.of(2021, 11, 15))));
                });
    }

    private void manualWithPriority08(List<Sitemap.URL> sites) {
        var manualSites = Arrays.asList(
            "dictionary", "krypto-learning", "external-services", "did-you-know"
        );

        manualSites.stream().
                forEach(site -> {
                    sites.add(new Sitemap.URL("https://www.kryptotipy.sk/" + site, 0.64, localDateToDate(LocalDate.of(2021, 7, 20))));
                    sites.add(new Sitemap.URL("https://en.kryptotipy.sk/" + site, 0.64, localDateToDate(LocalDate.of(2021, 7, 20))));
                    sites.add(new Sitemap.URL("https://de.kryptotipy.sk/" + site, 0.64, localDateToDate(LocalDate.of(2021, 7, 20))));
                    sites.add(new Sitemap.URL("https://pl.kryptotipy.sk/" + site, 0.64, localDateToDate(LocalDate.of(2021, 7, 20))));
                    sites.add(new Sitemap.URL("https://nl.kryptotipy.sk/" + site, 0.64, localDateToDate(LocalDate.of(2021, 7, 20))));
                    sites.add(new Sitemap.URL("https://hu.kryptotipy.sk/" + site, 0.64, localDateToDate(LocalDate.of(2021, 7, 20))));
                });
    }

    private Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    private void createSitesWithPriority064(List<Sitemap.URL> sites) {

        var _064Sites = Arrays.asList(

        );

        _064Sites.stream().
                forEach(site -> {
                    sites.add(new Sitemap.URL("https://www.kryptotipy.sk/" + site, 0.64, new Date()));
                    sites.add(new Sitemap.URL("https://en.kryptotipy.sk/" + site, 0.64, new Date()));
                    sites.add(new Sitemap.URL("https://de.kryptotipy.sk/" + site, 0.64, new Date()));
                    sites.add(new Sitemap.URL("https://pl.kryptotipy.sk/" + site, 0.64, new Date()));
                    sites.add(new Sitemap.URL("https://nl.kryptotipy.sk/" + site, 0.64, new Date()));
                    sites.add(new Sitemap.URL("https://hu.kryptotipy.sk/" + site, 0.64, new Date()));
                });
    }
}

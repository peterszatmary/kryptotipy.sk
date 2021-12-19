package sk.kryptotipy.web.repository;

import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Sitemap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

        var date = new Date();

        var _08Sites = new ArrayList(Arrays.asList(
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
                "posts/filecoin", "posts/tron", "posts/mining",
                "posts/tron", "posts/ftx", "posts/theta", "posts/dai", "posts/airdrops",
                "posts/hedera", "posts/sandbox", "posts/fantom", "posts/helium",
                "posts/loopring", "posts/eos", "posts/flow", "posts/decentraland", "posts/diem",
                "posts-by-i18n/GB", "posts-by-i18n/SK", "posts-by-i18n/CZ", "posts-by-i18n/HU",
                "posts-by-i18n/CH", "posts-by-i18n/US", "posts-by-i18n/CA", "posts-by-i18n/PL",
                "posts-by-i18n/DE", "posts-by-i18n/CH", "posts-by-i18n/NL", "posts-by-i18n/ES",
                "posts-by-i18n/SE"
        ));

//        var newsCombinations = createNewsCombinations();

        //TODO
//
//        for (var x : newsCombinations) {
//            if (x.contains("_l_sk_cz")) {
//                sites.add(new Sitemap.URL("https://www.kryptotipy.sk/" + x, 0.8, date));
//            } else if (x.contains("_l_hu")) {
//                sites.add(new Sitemap.URL("https://hu.kryptotipy.sk/" + x, 0.8, date));
//            } else if (x.contains("_l_pl")) {
//                sites.add(new Sitemap.URL("https://pl.kryptotipy.sk/" + x, 0.8, date));
//            } else if (x.contains("_l_nl")) {
//                sites.add(new Sitemap.URL("https://nl.kryptotipy.sk/" + x, 0.8, date));
//            } else if (x.contains("_l_de")) {
//                sites.add(new Sitemap.URL("https://de.kryptotipy.sk/" + x, 0.8, date));
//            } else if (x.contains("_l_us")) {
//                sites.add(new Sitemap.URL("https://en.kryptotipy.sk/" + x, 0.8, date));
//            }
//        }

        _08Sites.stream().
                forEach(site -> {
                    sites.add(new Sitemap.URL("https://www.kryptotipy.sk/" + site, 0.8, date));
                    sites.add(new Sitemap.URL("https://en.kryptotipy.sk/" + site, 0.8, date));
                    sites.add(new Sitemap.URL("https://de.kryptotipy.sk/" + site, 0.8, date));
                    sites.add(new Sitemap.URL("https://pl.kryptotipy.sk/" + site, 0.8, date));
                    sites.add(new Sitemap.URL("https://nl.kryptotipy.sk/" + site, 0.8, date));
                    sites.add(new Sitemap.URL("https://hu.kryptotipy.sk/" + site, 0.8, date));
        });

        sites.addAll(createNewsCombinationsOf5("www", date));
        sites.addAll(createNewsCombinationsOf5("en", date));
        sites.addAll(createNewsCombinationsOf5("de", date));
        sites.addAll(createNewsCombinationsOf5("pl", date));
        sites.addAll(createNewsCombinationsOf5("nl", date));
        sites.addAll(createNewsCombinationsOf5("hu", date));
    }


    private void manualWithPriority064(List<Sitemap.URL> sites) {

        var manualSites = Arrays.asList(
                "article/ethereum-dapps-today",
                "article/complete-guide-to-proof-of-work",
                "article/passive-income-with-neo", "article/passive-income-with-exodus",
                "article/btc-is-not-the-first", "article/passive-income-with-algorand",
                "article/passive-income-with-polkadot-on-ledger", "article/passive-income-with-vechain",
                "article/passive-income-with-cardano", "article/passive-income-with-cosmos-atom",
                "cooperation"
                //, "article/majk-spirit-wexo-nft"
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


    @Deprecated
    @SuppressWarnings("It seems that this is too much for Google. Less is more.")
    private List<String> createNewsCombinations() {
        var result = new ArrayList<String>();
        var url = "posts/";
        var categories = Arrays.asList(
                "avalanche", "algorand", "axie-infinity", "altcoin", "airdrops",
                "bitcoin", "binance",
                "cryptocurrency", "cosmos", "cardano", "chainlink",
                "decentraland", "dogecoin", "dai", "dash", "diem",
                "ethereum", "eos",
                "monero", "mining", "metaverse",
                "nft",
                "play-to-earn",  "polkadot", "polygon",
                "iota", "kusama", "helium", "loopring", "ontology",
                "fantom", "filecoin", "ftx", "flow", "hedera",
                "litecoin", "neo",
                "ripple",
                "solana", "shiba-inu", "stellar","sandbox",
                "tether", "terra", "tron", "theta", "tezos",
                "ecb", "fed", "nbs", "wallets", "halving", "exchange", "exodus", "dag", "coinmarketcap"
        );
        for (var category : categories) {
            var u = new StringBuilder(url);
            for (int i = 0; i < categories.size(); i++) {
                if (categories.get(i).equals(category)) {
                    continue;
                }
                if (i == categories.size() - 1) {
                    u.append(categories.get(i));
                    result.add(u.toString() + "_l_sk_cz");
                    result.add(u.toString() + "_l_us");
                    result.add(u.toString() + "_l_ca");
                    result.add(u.toString() + "_l_de");
                    result.add(u.toString() + "_l_nl");
                    result.add(u.toString() + "_l_hu");
                    result.add(u.toString() + "_l_pl");
                } else {
                    u.append(categories.get(i));
                    result.add(u.toString() + "_l_sk_cz");
                    result.add(u.toString() + "_l_us");
                    result.add(u.toString() + "_l_ca");
                    result.add(u.toString() + "_l_de");
                    result.add(u.toString() + "_l_nl");
                    result.add(u.toString() + "_l_hu");
                    result.add(u.toString() + "_l_pl");
                    u.append("_");
                }
            }
        }

        return result.stream()
                .filter(x -> x.split("_l_")[0].contains("_"))
                .collect(Collectors.toList());
    }



    private List<Sitemap.URL> createNewsCombinationsOf5(String lang, Date date) {

        //TODO later maybe other usefull combinations
        var _5 = Arrays.asList(
                "avalanche_algorand_axie-infinity_altcoin_airdrops",
                "bitcoin_binance_cryptocurrency_cosmos_cardano",
                "chainlink_decentraland_dogecoin_dai_dash",
                "diem_ethereum_eos_monero_mining",
                "metaverse_nft_play-to-earn_blockchain",
                "polkadot_polygon_iota_kusama_helium",
                "loopring_ontology_fantom_filecoin_ftx",
                "flow_hedera_litecoin_neo_ripple",
                "solana_shiba-inu_stellar_sandbox_tether",
                "terra_tron_theta_tezos_ecb",
                "fed_nbs_wallets_halving_exchange",
                "exodus_dag_uniswap",
                "bittrex_bitfinex_coinmarketcap_coinbase"
        );

        return _5.stream()
                .map(x -> new Sitemap.URL("https://" + lang + ".kryptotipy.sk/posts/" + x, 0.8, date))
                .collect(Collectors.toList());
    }

//    public static void main(String[] args) {
//        new SiteRepository().createNewsCombinations().stream().forEach(System.out::println);
//    }
}
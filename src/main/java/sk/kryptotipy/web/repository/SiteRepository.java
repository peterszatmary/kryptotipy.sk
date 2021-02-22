package sk.kryptotipy.web.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.SiteEntry;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Repository
public class SiteRepository {

    private final List<SiteEntry> siteEntries = new ArrayList<>();
    private LocalDate ACTUAL_DATE = LocalDate.now();
    private Double LAST_MOD_DEFAULT = 0.8D;


    public SiteRepository() {

        save(new SiteEntry("/", 1.00D, ACTUAL_DATE));
        save(new SiteEntry("/posts/all", LAST_MOD_DEFAULT, ACTUAL_DATE));
        save(new SiteEntry("/news", LAST_MOD_DEFAULT, ACTUAL_DATE));
        
        save(new SiteEntry("/external-services", LAST_MOD_DEFAULT, LocalDate.of(2021, Month.JANUARY, 19)));
        save(new SiteEntry("/did-you-know", LAST_MOD_DEFAULT, LocalDate.of(2021, Month.JANUARY, 26)));
        save(new SiteEntry("/dictionary", LAST_MOD_DEFAULT, LocalDate.of(2021,Month.DECEMBER, 22)));
        save(new SiteEntry("/krypto-learning", LAST_MOD_DEFAULT, LocalDate.of(2021, Month.JANUARY, 17)));

        save(new SiteEntry("/posts/ripple", LAST_MOD_DEFAULT, ACTUAL_DATE));
        save(new SiteEntry("/posts/blockchain", LAST_MOD_DEFAULT, ACTUAL_DATE));
        save(new SiteEntry("/posts/binance", LAST_MOD_DEFAULT, ACTUAL_DATE));
        save(new SiteEntry("/posts/bitcoin", LAST_MOD_DEFAULT, ACTUAL_DATE));
        save(new SiteEntry("/posts-by-i18n/CZ", LAST_MOD_DEFAULT, ACTUAL_DATE));
        save(new SiteEntry("/posts/ethereum", LAST_MOD_DEFAULT, ACTUAL_DATE));

        save(new SiteEntry("/posts/polkadot", LAST_MOD_DEFAULT, ACTUAL_DATE));
        save(new SiteEntry("/posts/algorand", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/vechain", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/tezos", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/cosmos", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/ontology", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/cardano", LAST_MOD_DEFAULT, ACTUAL_DATE));

        save(new SiteEntry("/posts/litecoin", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/coinbase", LAST_MOD_DEFAULT, ACTUAL_DATE));
        save(new SiteEntry("/posts/bittrex", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts-by-i18n/SK", LAST_MOD_DEFAULT, ACTUAL_DATE));
        save(new SiteEntry("/posts/cryptocurrency", LAST_MOD_DEFAULT, ACTUAL_DATE));
        save(new SiteEntry("/posts/not-categorized", LAST_MOD_DEFAULT, ACTUAL_DATE));
        save(new SiteEntry("/posts/kraken", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/ico", LAST_MOD_DEFAULT, ACTUAL_DATE));
        save(new SiteEntry("/posts/exchange", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/monero", 0.64D, ACTUAL_DATE));

        save(new SiteEntry("/posts/altcoin", LAST_MOD_DEFAULT, ACTUAL_DATE));
        save(new SiteEntry("/posts/fed", LAST_MOD_DEFAULT, ACTUAL_DATE));
        save(new SiteEntry("/posts/ecb", LAST_MOD_DEFAULT, ACTUAL_DATE));
        save(new SiteEntry("/posts/dag", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/exodus", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/dash", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/fork", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/halving", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/nbs", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/neo", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/bitfinex", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/wallets", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/coinmarketcap", 0.64D, ACTUAL_DATE));

        save(new SiteEntry("/cooperation", 0.64D, LocalDate.of(2021, Month.JANUARY, 16)));

        save(new SiteEntry("/article/btc-is-not-the-first", 0.64D, LocalDate.of(2021, Month.FEBRUARY, 21)));
        save(new SiteEntry("/article/complete-guide-to-proof-of-work", 0.64D, LocalDate.of(2021, Month.FEBRUARY, 21)));

        save(new SiteEntry("/article/passive-income-with-exodus", 0.64D, LocalDate.of(2021, Month.FEBRUARY, 22)));
        save(new SiteEntry("/article/passive-income-with-algorand", 0.64D, LocalDate.of(2021, Month.FEBRUARY, 22)));
        save(new SiteEntry("/article/passive-income-with-neo", 0.64D, LocalDate.of(2021, Month.FEBRUARY, 22)));
        save(new SiteEntry("/article/passive-income-with-cosmos-atom", 0.64D, LocalDate.of(2021, Month.FEBRUARY, 22)));
        save(new SiteEntry("/article/passive-income-with-cardano", 0.64D, LocalDate.of(2021, Month.FEBRUARY, 22)));
    }


//<url>
//  <loc>https://kryptotipy.sk/posts/ecb</loc>
//  <lastmod>2021-01-06T19:18:06+00:00</lastmod>
//  <priority>0.64</priority>
//</url>

    //TODO lastmod, priority


    public void save(SiteEntry entry) {
        siteEntries.add(entry);
    }

    public List<SiteEntry> findAll() {
        return Collections.unmodifiableList(siteEntries);
    }
}
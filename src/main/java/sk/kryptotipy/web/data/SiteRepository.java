package sk.kryptotipy.web.data;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class SiteRepository {
    private final List<SiteEntry> siteEntries = new ArrayList<>();
    private LocalDate ACTUAL_DATE = LocalDate.now();

    public SiteRepository() {

        save(new SiteEntry("/", 1.00D, ACTUAL_DATE));
        save(new SiteEntry("/posts/all", 0.8D, ACTUAL_DATE));
        
        save(new SiteEntry("/external-services", 0.8D, LocalDate.of(2021, Month.JANUARY, 19)));
        save(new SiteEntry("/did-you-know", 0.8D, LocalDate.of(2021, Month.JANUARY, 26)));
        save(new SiteEntry("/dictionary", 0.8D, LocalDate.of(2021,Month.DECEMBER, 22)));
        save(new SiteEntry("/krypto-learning", 0.8D, LocalDate.of(2021, Month.JANUARY, 17)));

        save(new SiteEntry("/posts/ripple", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/blockchain", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/binance", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/bitcoin", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts-by-i18n/CZ", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/ethereum", 0.8D, ACTUAL_DATE));

        save(new SiteEntry("/posts/polkadot", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/algorand", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/vechain", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/tezos", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/cosmos", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/ontology", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/cardano", 0.8D, ACTUAL_DATE));

        save(new SiteEntry("/posts/litecoin", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/coinbase", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/bittrex", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts-by-i18n/SK", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/kryptomeny", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/nezaradene", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/kraken", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/ico", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/burza", 0.8D, ACTUAL_DATE));
        save(new SiteEntry("/posts/monero", 0.8D, ACTUAL_DATE));

        save(new SiteEntry("/posts/altcoin", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/fed", 0.64D, ACTUAL_DATE));
        save(new SiteEntry("/posts/ecb", 0.64D, ACTUAL_DATE));
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
        save(new SiteEntry("/article/btc-is-not-the-first", 0.64D, LocalDate.of(2021, Month.JANUARY, 27)));
        save(new SiteEntry("/article/passive-income-with-exodus", 0.64D, LocalDate.of(2021, Month.JANUARY, 27)));
        save(new SiteEntry("/cooperation", 0.64D, LocalDate.of(2021, Month.JANUARY, 16)));
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
package sk.kryptotipy.web.repository;

import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Repository
public class ServiceRepository {

    public List<Service> getExternalServices(ResourceBundle rb) {
        List<Service> services = new ArrayList<>();

        services.add(new Service(rb.getString("external.services.service.1.title"), "https://blockchair.com/", "blockchair.png", rb.getString("external.services.service.1.description")));
        services.add(new Service(rb.getString("external.services.service.2.title"), "https://shop.trezor.io/product/trezor-one-black?offer_id=35&aff_id=5933", "trezor-one-black.png", rb.getString("external.services.service.2.description")));
        services.add(new Service(rb.getString("external.services.service.3.title"), "https://www.coinbase.com", "coinbase-big.png", rb.getString("external.services.service.3.description")));
        services.add(new Service(rb.getString("external.services.service.4.title"), "https://www.exodus.io", "exodus-big.jpg", rb.getString("external.services.service.4.description")));
        services.add(new Service(rb.getString("external.services.service.5.title"), "https://pro.coinbase.com/", "gdax-big.jpg", rb.getString("external.services.service.5.description")));
        services.add(new Service(rb.getString("external.services.service.6.title"), "https://poloniex.com", "poloniex-big.png", rb.getString("external.services.service.6.description")));
        services.add(new Service(rb.getString("external.services.service.7.title"), "https://jaxx.io", "jaxx-big.png", rb.getString("external.services.service.7.description")));
        services.add(new Service(rb.getString("external.services.service.8.title"), "https://shop.trezor.io/product/trezor-model-t?offer_id=15&aff_id=5933", "trezor-t-black.png", rb.getString("external.services.service.8.description")));
        services.add(new Service(rb.getString("external.services.service.9.title"), "https://www.kucoin.com/", "kucoin-big.png", rb.getString("external.services.service.9.description")));
        services.add(new Service(rb.getString("external.services.service.10.title"), "https://lddy.no/v0jj", "keepkey.jpg", rb.getString("external.services.service.10.description")));
        services.add(new Service(rb.getString("external.services.service.11.title"), "https://r.kraken.com/rn4GDj", "kraken-big.jpg", rb.getString("external.services.service.11.description")));
        services.add(new Service(rb.getString("external.services.service.12.title"), "https://neotogas.com", "neotogas-big.jpg", rb.getString("external.services.service.12.description")));
        services.add(new Service(rb.getString("external.services.service.13.title"), "https://athda.com", "athda-big.jpg", rb.getString("external.services.service.13.description")));
        services.add(new Service(rb.getString("external.services.service.14.title"), "https://shop.ledger.com/pages/ledger-nano-x?r=b88bca019506", "ledger-nano-x.jpg", rb.getString("external.services.service.14.description")));
        services.add(new Service(rb.getString("external.services.service.15.title"), "https://shapeshift.io/", "shapeshift-big.png", rb.getString("external.services.service.15.description")));
        services.add(new Service(rb.getString("external.services.service.16.title"), "https://ethplorer.io", "ethplorer-big.png", rb.getString("external.services.service.16.description")));
        services.add(new Service(rb.getString("external.services.service.17.title"), "https://www.binance.com", "binance-big.png", rb.getString("external.services.service.17.description")));
        services.add(new Service(rb.getString("external.services.service.18.title"), "https://www.bitfinex.com", "bitfinex-big.jpg", rb.getString("external.services.service.18.description")));
        services.add(new Service(rb.getString("external.services.service.19.title"), "https://bittrex.com", "bittrex-big.png", rb.getString("external.services.service.19.description")));
        services.add(new Service(rb.getString("external.services.service.20.title"), "https://bitscreener.com/screener", "bitscreener-big.png", rb.getString("external.services.service.20.description")));
        services.add(new Service(rb.getString("external.services.service.21.title"), "https://coinmarketcap.com", "coinmarketcap-big.png", rb.getString("external.services.service.21.description")));
        services.add(new Service(rb.getString("external.services.service.22.title"), "https://www.cryptocompare.com", "cryptocompare-big.png", rb.getString("external.services.service.22.description")));
        services.add(new Service(rb.getString("external.services.service.23.title"), "https://coindar.org", "coindar-big.jpg", rb.getString("external.services.service.23.description")));
        services.add(new Service(rb.getString("external.services.service.24.title"), "https://alternative.me/crypto/fear-and-greed-index/", "fear-and-greed-index.png", rb.getString("external.services.service.24.description")));
        services.add(new Service(rb.getString("external.services.service.25.title"), "https://coinatmradar.com/bitcoin-atm-near-me/", "coin-atm-radar.png", rb.getString("external.services.service.25.description")));
        services.add(new Service(rb.getString("external.services.service.26.title"), "https://airdrops.io", "airdrops-io.jpg", rb.getString("external.services.service.26.description")));
        services.add(new Service(rb.getString("external.services.service.27.title"), "https://www.stakingrewards.com", "staking-rewards.png", rb.getString("external.services.service.27.description")));
        services.add(new Service(rb.getString("external.services.service.28.title"), "https://kryptotank.cz/", "kryptotank.png", rb.getString("external.services.service.28.description")));
        services.add(new Service(rb.getString("external.services.service.29.title"), "https://whitepaper.io/", "whitepaper.jpg", rb.getString("external.services.service.29.description")));
        services.add(new Service(rb.getString("external.services.service.30.title"), "https://99bitcoins.com/bitcoin-obituaries/", "bitcoin-deaths.jpeg", rb.getString("external.services.service.30.description")));


        return services;
    }

    public List<Service> getCooperationServices(ResourceBundle rb) {
        List<Service> services = new ArrayList<>();
        services.add(new Service(
                rb.getString("cooperation.service.1.title"),
                "",
                "cooperation/back.jpg",
                rb.getString("cooperation.service.1.description"))
        );
        services.add(new Service(
                   rb.getString("cooperation.service.2.title"),
                "",
                "cooperation/posts.png",
                rb.getString("cooperation.service.2.description"))
        );
        services.add(new Service(
                rb.getString("cooperation.service.3.title"),
                "",
                "cooperation/write_post_but_question.jpg",
                rb.getString("cooperation.service.3.description"))
        );
        services.add(new Service(
                rb.getString("cooperation.service.4.title"),
                "",
                "cooperation/backlinks.png",
                rb.getString("cooperation.service.4.description"))
        );
        return services;
    }




    /** views **/
    public List<Service> getExternalServicesViews(ResourceBundle rb) {
        List<Service> services = new ArrayList<>();

        services.add(new Service(rb.getString("external.services.service.1.title"), "https://blockchair.com/", "blockchair.png", rb.getString("external.services.service.1.description")));
        services.add(new Service(rb.getString("external.services.service.2.title"), "https://shop.trezor.io/product/trezor-one-black?offer_id=35&aff_id=5933", "trezor-one-black.png", rb.getString("external.services.service.2.description")));
        services.add(new Service(rb.getString("external.services.service.13.title"), "https://athda.com", "athda-big.jpg", rb.getString("external.services.service.13.description")));
        services.add(new Service(rb.getString("external.services.service.16.title"), "https://ethplorer.io", "ethplorer-big.png", rb.getString("external.services.service.16.description")));

        services.add(new Service(rb.getString("external.services.service.8.title"), "https://shop.trezor.io/product/trezor-model-t?offer_id=15&aff_id=5933", "trezor-t-black.png", rb.getString("external.services.service.8.description")));
        services.add(new Service(rb.getString("external.services.service.24.title"), "https://alternative.me/crypto/fear-and-greed-index/", "fear-and-greed-index.png", rb.getString("external.services.service.24.description")));
        services.add(new Service(rb.getString("external.services.service.25.title"), "https://coinatmradar.com/bitcoin-atm-near-me/", "coin-atm-radar.png", rb.getString("external.services.service.25.description")));
        services.add(new Service(rb.getString("external.services.service.14.title"), "https://shop.ledger.com/pages/ledger-nano-x?r=b88bca019506", "ledger-nano-x.jpg", rb.getString("external.services.service.14.description")));

        services.add(new Service(rb.getString("external.services.service.27.title"), "https://www.stakingrewards.com", "staking-rewards.png", rb.getString("external.services.service.27.description")));
        services.add(new Service(rb.getString("external.services.service.10.title"), "https://lddy.no/v0jj", "keepkey.jpg", rb.getString("external.services.service.10.description")));
        services.add(new Service(rb.getString("external.services.service.29.title"), "https://whitepaper.io/", "whitepaper.jpg", rb.getString("external.services.service.29.description")));
        services.add(new Service(rb.getString("external.services.service.11.title"), "https://r.kraken.com/rn4GDj", "kraken-big.jpg", rb.getString("external.services.service.11.description")));

        return services;
    }
}
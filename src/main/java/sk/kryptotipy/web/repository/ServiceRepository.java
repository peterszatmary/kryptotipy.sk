package sk.kryptotipy.web.repository;

import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Repository
public class ServiceRepository {

    public List<Service> getExternalServices(ResourceBundle rb) {
        var services = new ArrayList<Service>();

        services.add(new Service(s(rb,"external.services.service.31.title"), "https://axieinfinity.com/", "axieinfinity.png", s(rb,"external.services.service.31.description")));
        services.add(new Service(s(rb,"external.services.service.2.title"), "https://shop.trezor.io/product/trezor-one-black?offer_id=35&aff_id=5933", "trezor-one-black.png", s(rb,"external.services.service.2.description")));
        services.add(new Service(s(rb,"external.services.service.32.title"), "https://www.larvalabs.com/cryptopunks", "cryptopunks.jpeg", s(rb,"external.services.service.32.description")));
        services.add(new Service(s(rb,"external.services.service.33.title"), "https://opensea.io/", "opensea.jpeg", s(rb,"external.services.service.33.description")));

        services.add(new Service(s(rb,"external.services.service.34.title"), "https://rarible.com/", "rarible.jpeg", s(rb,"external.services.service.34.description")));
        services.add(new Service(s(rb,"external.services.service.35.title"), "https://sushi.com/", "sushiswap.png", s(rb,"external.services.service.35.description")));
        services.add(new Service(s(rb,"external.services.service.36.title"), "https://uniswap.org/", "uniswap.png", s(rb,"external.services.service.36.description")));
        services.add(new Service(s(rb,"external.services.service.8.title"), "https://shop.trezor.io/product/trezor-model-t?offer_id=15&aff_id=5933", "trezor-t-black.png", s(rb,"external.services.service.8.description")));

        services.add(new Service(s(rb,"external.services.service.1.title"), "https://blockchair.com/", "blockchair.png", s(rb,"external.services.service.1.description")));
        services.add(new Service(s(rb,"external.services.service.3.title"), "https://www.coinbase.com", "coinbase-big.png", s(rb,"external.services.service.3.description")));
        services.add(new Service(s(rb,"external.services.service.4.title"), "https://www.exodus.io", "exodus-big.jpg", s(rb,"external.services.service.4.description")));
        services.add(new Service(s(rb,"external.services.service.5.title"), "https://pro.coinbase.com/", "gdax-big.jpg", s(rb,"external.services.service.5.description")));

        services.add(new Service(s(rb,"external.services.service.6.title"), "https://poloniex.com", "poloniex-big.png", s(rb,"external.services.service.6.description")));
        services.add(new Service(s(rb,"external.services.service.11.title"), "https://www.kraken.com/sign-up?clickid=SM03WF29RxyITyfwvm3qMxrPUkBSUNWBnzyKUg0&utm_source=Impact&utm_medium=Affiliate&utm_campaign=2683827&utm_content=%22Create%20an%20account%20with%20Kraken%22&irgwc=1&mpid=2683827", "kraken-big.jpg", s(rb,"external.services.service.11.description")));
        services.add(new Service(s(rb,"external.services.service.7.title"), "https://jaxx.io", "jaxx-big.png", s(rb,"external.services.service.7.description")));
        services.add(new Service(s(rb,"external.services.service.14.title"), "https://shop.ledger.com/pages/ledger-nano-x?r=b88bca019506", "ledger-nano-x.jpg", s(rb,"external.services.service.14.description")));

        services.add(new Service(s(rb,"external.services.service.9.title"), "https://www.kucoin.com/", "kucoin-big.png", s(rb,"external.services.service.9.description")));
        services.add(new Service(s(rb,"external.services.service.10.title"), "https://lddy.no/v0jj", "keepkey.jpg", s(rb,"external.services.service.10.description")));
        services.add(new Service(s(rb,"external.services.service.12.title"), "https://neotogas.com", "neotogas-big.jpg", s(rb,"external.services.service.12.description")));
        services.add(new Service(s(rb,"external.services.service.13.title"), "https://athda.com", "athda-big.jpg", s(rb,"external.services.service.13.description")));


        services.add(new Service(s(rb,"external.services.service.15.title"), "https://shapeshift.io/", "shapeshift-big.png", s(rb,"external.services.service.15.description")));
        services.add(new Service(s(rb,"external.services.service.16.title"), "https://ethplorer.io", "ethplorer-big.png", s(rb,"external.services.service.16.description")));
        services.add(new Service(s(rb,"external.services.service.17.title"), "https://www.binance.com", "binance-big.png", s(rb,"external.services.service.17.description")));
        services.add(new Service(s(rb,"external.services.service.18.title"), "https://www.bitfinex.com", "bitfinex-big.jpg", s(rb,"external.services.service.18.description")));

        services.add(new Service(s(rb,"external.services.service.19.title"), "https://bittrex.com", "bittrex-big.png", s(rb,"external.services.service.19.description")));
        services.add(new Service(s(rb,"external.services.service.20.title"), "https://bitscreener.com/screener", "bitscreener-big.png", s(rb,"external.services.service.20.description")));
        services.add(new Service(s(rb,"external.services.service.21.title"), "https://coinmarketcap.com", "coinmarketcap-big.png", s(rb,"external.services.service.21.description")));
        services.add(new Service(s(rb,"external.services.service.22.title"), "https://www.cryptocompare.com", "cryptocompare-big.png", s(rb,"external.services.service.22.description")));

        services.add(new Service(s(rb,"external.services.service.23.title"), "https://coindar.org", "coindar-big.jpg", s(rb,"external.services.service.23.description")));
        services.add(new Service(s(rb,"external.services.service.24.title"), "https://alternative.me/crypto/fear-and-greed-index/", "fear-and-greed-index.png", s(rb,"external.services.service.24.description")));
        services.add(new Service(s(rb,"external.services.service.25.title"), "https://coinatmradar.com/bitcoin-atm-near-me/", "coin-atm-radar.png", s(rb,"external.services.service.25.description")));
        services.add(new Service(s(rb,"external.services.service.26.title"), "https://airdrops.io", "airdrops-io.jpg", s(rb,"external.services.service.26.description")));

        services.add(new Service(s(rb,"external.services.service.27.title"), "https://www.stakingrewards.com", "staking-rewards.png", s(rb,"external.services.service.27.description")));
        services.add(new Service(s(rb,"external.services.service.28.title"), "https://kryptotank.cz/", "kryptotank.png", s(rb,"external.services.service.28.description")));
        services.add(new Service(s(rb,"external.services.service.29.title"), "https://whitepaper.io/", "whitepaper.jpg", s(rb,"external.services.service.29.description")));
        services.add(new Service(s(rb,"external.services.service.30.title"), "https://99bitcoins.com/bitcoin-obituaries/", "bitcoin-deaths.jpeg", s(rb,"external.services.service.30.description")));

        return services;
    }


    /** views **/
    public List<Service> getExternalServicesViews(ResourceBundle rb) {
        var services = new ArrayList<Service>();

        services.add(new Service(s(rb,"external.services.service.31.title"), "https://axieinfinity.com/", "axieinfinity.png", s(rb,"external.services.service.31.description")));
        services.add(new Service(s(rb,"external.services.service.2.title"), "https://shop.trezor.io/product/trezor-one-black?offer_id=35&aff_id=5933", "trezor-one-black.png", s(rb,"external.services.service.2.description")));
        services.add(new Service(s(rb,"external.services.service.33.title"), "https://opensea.io/", "opensea.jpeg", s(rb,"external.services.service.33.description")));
        services.add(new Service(s(rb,"external.services.service.34.title"), "https://rarible.com/", "rarible.jpeg", s(rb,"external.services.service.34.description")));

        services.add(new Service(s(rb,"external.services.service.35.title"), "https://sushi.com/", "sushiswap.png", s(rb,"external.services.service.35.description")));
        services.add(new Service(s(rb,"external.services.service.8.title"), "https://shop.trezor.io/product/trezor-model-t?offer_id=15&aff_id=5933", "trezor-t-black.png", s(rb,"external.services.service.8.description")));
        services.add(new Service(s(rb,"external.services.service.4.title"), "https://www.exodus.io", "exodus-big.jpg", s(rb,"external.services.service.4.description")));
        services.add(new Service(s(rb,"external.services.service.14.title"), "https://shop.ledger.com/pages/ledger-nano-x?r=b88bca019506", "ledger-nano-x.jpg", s(rb,"external.services.service.14.description")));

        return services;
    }
    
    private String s(ResourceBundle rb, String key) {
        return rb.getString(key);
    }
}
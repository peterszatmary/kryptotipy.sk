package sk.kryptotipy.web.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.api.BtcPriceServiceApi;
import sk.kryptotipy.web.data.ExternalPriceId;
import sk.kryptotipy.web.repository.db.BtcPriceRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class PriceService implements BtcPriceServiceApi {

    private final BtcPriceRepository btcPriceRepository;

    public Double getLatestPrice() {
        return btcPriceRepository.getLatestPrice().getPrice();
    }

    /**
     * used external widgets:
     * - coinmarketcap
     * - coinlib
     *
     * not mapped: ripple
     *
     *
     * FIND first price from label list to show
     *
     * @param labels
     * @return if nothing found null returned
     */
    public ExternalPriceId mapCoinToCoinLibId(List<String> labels) {

        if (labels.isEmpty()) {
            return null;
        }

        for (var label : labels) {
            final var coinNameSmall = label.toLowerCase();
            var externalPriceId = mapPriceId(coinNameSmall);
            if (externalPriceId != null) {
                return externalPriceId;
            } else {
                continue;
            }
        }
        return null;
    }

    private ExternalPriceId mapPriceId(String label) {
        ExternalPriceId result = null;
        switch (label) {

            case "bitcoin": {
                result = new ExternalPriceId("coinlib", "859");
                break;
            }
            case "ethereum": {
                result = new ExternalPriceId("coinlib", "145");
                break;
            }
            case "binance": {
                result = new ExternalPriceId("coinlib", "1209");
                break;
            }
            case "tether": {
                result = new ExternalPriceId("coinlib", "637");
                break;
            }
            case "cardano": {
                result = new ExternalPriceId("coinlib", "122882");
                break;
            }
            case "avalanche": {
                result = new ExternalPriceId("coinlib", "1512453");
                break;
            }
            case "dash": {
                result = new ExternalPriceId("coinlib", "1188");
                break;
            }
            case "aave": {
                result = new ExternalPriceId("coinlib", "1513363");
                break;
            }
            case "algorand": {
                result = new ExternalPriceId("coinlib", "1511107");
                break;
            }
            case "cosmos": {
                result = new ExternalPriceId("coinlib", "392");
                break;
            }
            case "chainlink": {
                result = new ExternalPriceId("coinlib", "7618");
                break;
            }
            case "polkadot": {
                result = new ExternalPriceId("coinlib", "250");
                break;
            }
            case "loopring": {
                result = new ExternalPriceId("coinlib", "122808");
                break;
            }
            case "dai": {
                result = new ExternalPriceId("coinlib", "345508");
                break;
            }
            case "dogecoin": {
                result = new ExternalPriceId("coinlib", "280");
                break;
            }
            case "eos": {
                result = new ExternalPriceId("coinlib", "585");
                break;
            }
            case "hedera": {
                result = new ExternalPriceId("coinlib", "1511303");
                break;
            }
            case "vechain": {
                result = new ExternalPriceId("coinlib", "1248415");
                break;
            }
            case "terra": {
                result = new ExternalPriceId("coinlib", "1511102");
                break;
            }
            case "tezos": {
                result = new ExternalPriceId("coinlib", "986");
                break;
            }
            case "polygon": {
                result = new ExternalPriceId("coinlib", "1510954");
                break;
            }
            case "elrond": {
                result = new ExternalPriceId("coinlib", "1511171");
                break;
            }
            case "fantom": {
                result = new ExternalPriceId("coinlib", "1388222");
                break;
            }
            case "filecoin": {
                result = new ExternalPriceId("coinlib", "563");
                break;
            }
            case "ftx": {
                result = new ExternalPriceId("coinlib", "1511103");
                break;
            }
            case "iota": {
                result = new ExternalPriceId("coinlib", "1026");
                break;
            }
            case "litecoin": {
                result = new ExternalPriceId("coinlib", "359");
                break;
            }
            case "neo": {
                result = new ExternalPriceId("coinlib", "1015");
                break;
            }
            case "stellar": {
                result = new ExternalPriceId("coinlib", "489");
                break;
            }
            case "theta": {
                result = new ExternalPriceId("coinlib", "433804");
                break;
            }
            case "ontology": {
                result = new ExternalPriceId("coinlib", "633095");
                break;
            }
            case "tron": {
                result = new ExternalPriceId("coinlib", "8550");
                break;
            }
            case "monero": {
                result = new ExternalPriceId("coinlib", "6");
                break;
            }
            case "decentraland": {
                result = new ExternalPriceId("coinlib", "441");
                break;
            }

            // diem in future maybe :)

            case "kusama": {
                result = new ExternalPriceId("coinmarketcap", "5034");
                break;
            }
            case "axie-infinity": {
                result = new ExternalPriceId("coinmarketcap", "6783");
                break;
            }
            case "shiba-inu": {
                result = new ExternalPriceId("coinmarketcap", "5994");
                break;
            }
            case "helium": {
                result = new ExternalPriceId("coinmarketcap", "5665");
                break;
            }
            case "flow": {
                result = new ExternalPriceId("coinmarketcap", "4558");
                break;
            }
            case "sandbox": {
                result = new ExternalPriceId("coinmarketcap", "6210");
                break;
            }
            case "solana": {
                result = new ExternalPriceId("coinmarketcap", "5426");
                break;
            }
            case "uniswap": {
                result = new ExternalPriceId("coinmarketcap", "7083");
                break;
            }
            case "ripple": {
                result = new ExternalPriceId("coinmarketcap", "52");
                break;
            }

            default: {
                result = null;
                break;
            }
        }
        return result;
    }

    // ak viac krzptomien - prvu
    // ak jedna jedna
    // ak nic nic
}

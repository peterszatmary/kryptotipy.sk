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
                //result = new ExternalPriceId("coinmarketcap", "1");
                break;
            }
            case "ethereum": {
                result = new ExternalPriceId("coinlib", "145");
                //result = new ExternalPriceId("coinmarketcap", "1027");
                break;
            }
            case "binance": {
                result = new ExternalPriceId("coinlib", "1209");
                //result = new ExternalPriceId("coinmarketcap", "1839");
                break;
            }
            case "tether": {
                result = new ExternalPriceId("coinlib", "637");
                //result = new ExternalPriceId("coinmarketcap", "835");
                break;
            }
            case "cardano": {
                result = new ExternalPriceId("coinlib", "122882");
                //result = new ExternalPriceId("coinmarketcap", "2010");
                break;
            }
            case "avalanche": {
                result = new ExternalPriceId("coinlib", "1512453");
                //result = new ExternalPriceId("coinmarketcap", "5805");
                break;
            }
            case "dash": {
                result = new ExternalPriceId("coinlib", "1188");
                //result = new ExternalPriceId("coinmarketcap", "131");
                break;
            }
            case "aave": {
                result = new ExternalPriceId("coinlib", "1513363");
                //result = new ExternalPriceId("coinmarketcap", "7278");
                break;
            }
            case "algorand": {
                result = new ExternalPriceId("coinlib", "1511107");
                //result = new ExternalPriceId("coinmarketcap", "4030");
                break;
            }
            case "cosmos": {
                result = new ExternalPriceId("coinlib", "392");
                //result = new ExternalPriceId("coinmarketcap", "3794");
                break;
            }
            case "chainlink": {
                result = new ExternalPriceId("coinlib", "7618");
                //result = new ExternalPriceId("coinmarketcap", "1975");
                break;
            }
            case "polkadot": {
                result = new ExternalPriceId("coinlib", "250");
                //result = new ExternalPriceId("coinmarketcap", "6636");
                break;
            }
            case "loopring": {
                result = new ExternalPriceId("coinlib", "122808");
                //result = new ExternalPriceId("coinmarketcap", "1934");
                break;
            }
            case "dai": {
                result = new ExternalPriceId("coinlib", "345508");
                //result = new ExternalPriceId("coinmarketcap", "4943");
                break;
            }
            case "dogecoin": {
                result = new ExternalPriceId("coinlib", "280");
                //result = new ExternalPriceId("coinmarketcap", "74");
                break;
            }
            case "eos": {
                result = new ExternalPriceId("coinlib", "585");
                //result = new ExternalPriceId("coinmarketcap", "1765");
                break;
            }
            case "hedera": {
                result = new ExternalPriceId("coinlib", "1511303");
                //result = new ExternalPriceId("coinmarketcap", "4642");
                break;
            }
            case "vechain": {
                result = new ExternalPriceId("coinlib", "1248415");
                //result = new ExternalPriceId("coinmarketcap", "3077");
                break;
            }
            case "terra": {
                result = new ExternalPriceId("coinlib", "1511102");
                //result = new ExternalPriceId("coinmarketcap", "4172");
                break;
            }
            case "tezos": {
                result = new ExternalPriceId("coinlib", "986");
                //result = new ExternalPriceId("coinmarketcap", "2011");
                break;
            }
            case "polygon": {
                result = new ExternalPriceId("coinlib", "1510954");
                //result = new ExternalPriceId("coinmarketcap", "3890");
                break;
            }
            case "elrond": {
                result = new ExternalPriceId("coinlib", "1511171");
                //result = new ExternalPriceId("coinmarketcap", "6892");
                break;
            }
            case "fantom": {
                result = new ExternalPriceId("coinlib", "1388222");
                //result = new ExternalPriceId("coinmarketcap", "3513");
                break;
            }
            case "filecoin": {
                result = new ExternalPriceId("coinlib", "563");
                //result = new ExternalPriceId("coinmarketcap", "2280");
                break;
            }
            case "ftx": {
                result = new ExternalPriceId("coinlib", "1511103");
                //result = new ExternalPriceId("coinmarketcap", "4195");
                break;
            }
            case "iota": {
                result = new ExternalPriceId("coinlib", "1026");
                //result = new ExternalPriceId("coinmarketcap", "1720");
                break;
            }
            case "litecoin": {
                result = new ExternalPriceId("coinlib", "359");
                //result = new ExternalPriceId("coinmarketcap", "2");
                break;
            }
            case "neo": {
                result = new ExternalPriceId("coinlib", "1015");
                //result = new ExternalPriceId("coinmarketcap", "1376");
                break;
            }
            case "stellar": {
                result = new ExternalPriceId("coinlib", "489");
                //result = new ExternalPriceId("coinmarketcap", "512");
                break;
            }
            case "theta": {
                result = new ExternalPriceId("coinlib", "433804");
                //result = new ExternalPriceId("coinmarketcap", "2416");
                break;
            }
            case "ontology": {
                result = new ExternalPriceId("coinlib", "633095");
                //result = new ExternalPriceId("coinmarketcap", "2566");
                break;
            }
            case "tron": {
                result = new ExternalPriceId("coinlib", "8550");
                //result = new ExternalPriceId("coinmarketcap", "1958");
                break;
            }
            case "monero": {
                result = new ExternalPriceId("coinlib", "6");
                //result = new ExternalPriceId("coinmarketcap", "328");
                break;
            }
            case "decentraland": {
                result = new ExternalPriceId("coinlib", "441");
                //result = new ExternalPriceId("coinmarketcap", "1966");

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

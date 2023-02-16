package sk.kryptotipy.web.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.data.ExternalPriceId;
import sk.kryptotipy.web.repository.db.BtcPriceRepository;

import java.util.List;

@AllArgsConstructor
@Service
public final class PriceService implements BtcPriceServiceApi {

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
        return switch (label) {

            case "bitcoin" -> new ExternalPriceId("coinlib", "859");
                //result = new ExternalPriceId("coinmarketcap", "1");
            case "ethereum" -> new ExternalPriceId("coinlib", "145");
                //result = new ExternalPriceId("coinmarketcap", "1027");
            case "binance" -> new ExternalPriceId("coinlib", "1209");
                //result = new ExternalPriceId("coinmarketcap", "1839");
            case "tether" -> new ExternalPriceId("coinlib", "637");
                //result = new ExternalPriceId("coinmarketcap", "835");
            case "cardano" -> new ExternalPriceId("coinlib", "122882");
                //result = new ExternalPriceId("coinmarketcap", "2010");
            case "avalanche" -> new ExternalPriceId("coinlib", "1512453");
                //result = new ExternalPriceId("coinmarketcap", "5805");
            case "dash" -> new ExternalPriceId("coinlib", "1188");
                //result = new ExternalPriceId("coinmarketcap", "131");
            case "aave" -> new ExternalPriceId("coinlib", "1513363");
                //result = new ExternalPriceId("coinmarketcap", "7278");
            case "algorand" -> new ExternalPriceId("coinlib", "1511107");
                //result = new ExternalPriceId("coinmarketcap", "4030");
            case "cosmos" -> new ExternalPriceId("coinlib", "392");
                //result = new ExternalPriceId("coinmarketcap", "3794");
            case "chainlink" -> new ExternalPriceId("coinlib", "7618");
                //result = new ExternalPriceId("coinmarketcap", "1975");
            case "polkadot" -> new ExternalPriceId("coinlib", "250");
                //result = new ExternalPriceId("coinmarketcap", "6636");
            case "loopring" -> new ExternalPriceId("coinlib", "122808");
                //result = new ExternalPriceId("coinmarketcap", "1934");
            case "dai" -> new ExternalPriceId("coinlib", "345508");
                //result = new ExternalPriceId("coinmarketcap", "4943");
            case "dogecoin" -> new ExternalPriceId("coinlib", "280");
                //result = new ExternalPriceId("coinmarketcap", "74");
            case "eos" -> new ExternalPriceId("coinlib", "585");
                //result = new ExternalPriceId("coinmarketcap", "1765");
            case "hedera" -> new ExternalPriceId("coinlib", "1511303");
                //result = new ExternalPriceId("coinmarketcap", "4642");
            case "vechain" -> new ExternalPriceId("coinlib", "1248415");
                //result = new ExternalPriceId("coinmarketcap", "3077");
            case "terra" -> new ExternalPriceId("coinlib", "1511102");
                //result = new ExternalPriceId("coinmarketcap", "4172");
            case "tezos" -> new ExternalPriceId("coinlib", "986");
                //result = new ExternalPriceId("coinmarketcap", "2011");
            case "polygon" -> new ExternalPriceId("coinlib", "1510954");
                //result = new ExternalPriceId("coinmarketcap", "3890");
            case "elrond" -> new ExternalPriceId("coinlib", "1511171");
                //result = new ExternalPriceId("coinmarketcap", "6892");
            case "fantom" -> new ExternalPriceId("coinlib", "1388222");
                //result = new ExternalPriceId("coinmarketcap", "3513");
            case "filecoin" -> new ExternalPriceId("coinlib", "563");
                //result = new ExternalPriceId("coinmarketcap", "2280");
            case "ftx" -> new ExternalPriceId("coinlib", "1511103");
                //result = new ExternalPriceId("coinmarketcap", "4195");
            case "iota" -> new ExternalPriceId("coinlib", "1026");
                //result = new ExternalPriceId("coinmarketcap", "1720");
            case "litecoin" -> new ExternalPriceId("coinlib", "359");
                //result = new ExternalPriceId("coinmarketcap", "2");
            case "neo" -> new ExternalPriceId("coinlib", "1015");
                //result = new ExternalPriceId("coinmarketcap", "1376");
            case "stellar" -> new ExternalPriceId("coinlib", "489");
                //result = new ExternalPriceId("coinmarketcap", "512");
            case "theta" -> new ExternalPriceId("coinlib", "433804");
                //result = new ExternalPriceId("coinmarketcap", "2416");
            case "ontology" -> new ExternalPriceId("coinlib", "633095");
                //result = new ExternalPriceId("coinmarketcap", "2566");
            case "tron" -> new ExternalPriceId("coinlib", "8550");
                //result = new ExternalPriceId("coinmarketcap", "1958");
            case "monero" -> new ExternalPriceId("coinlib", "6");
                //result = new ExternalPriceId("coinmarketcap", "328");
            case "decentraland" -> new ExternalPriceId("coinlib", "441");
                //result = new ExternalPriceId("coinmarketcap", "1966");
            // diem in future maybe :)
            case "kusama" -> new ExternalPriceId("coinmarketcap", "5034");
            case "axie-infinity" -> new ExternalPriceId("coinmarketcap", "6783");
            case "shiba-inu" -> new ExternalPriceId("coinmarketcap", "5994");
            case "helium" -> new ExternalPriceId("coinmarketcap", "5665");
            case "flow" -> new ExternalPriceId("coinmarketcap", "4558");
            case "sandbox" -> new ExternalPriceId("coinmarketcap", "6210");
            case "solana" -> new ExternalPriceId("coinmarketcap", "5426");
            case "uniswap" -> new ExternalPriceId("coinmarketcap", "7083");
            case "ripple" -> new ExternalPriceId("coinmarketcap", "52");
            default -> null;
        };
    }

    // ak viac krzptomien - prvu
    // ak jedna jedna
    // ak nic nic
}

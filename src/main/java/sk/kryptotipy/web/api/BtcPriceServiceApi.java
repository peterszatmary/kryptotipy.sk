package sk.kryptotipy.web.api;

import sk.kryptotipy.web.data.ExternalPriceId;

import java.util.List;

public interface BtcPriceServiceApi {
    Double getLatestPrice();
    ExternalPriceId mapCoinToCoinLibId(List<String> labels);
}

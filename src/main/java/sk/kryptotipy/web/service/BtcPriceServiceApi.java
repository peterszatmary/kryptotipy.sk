package sk.kryptotipy.web.service;

import sk.kryptotipy.web.data.ExternalPriceId;

import java.util.List;

public sealed interface BtcPriceServiceApi permits PriceService {
    Double getLatestPrice();
    ExternalPriceId mapCoinToCoinLibId(List<String> labels);
}

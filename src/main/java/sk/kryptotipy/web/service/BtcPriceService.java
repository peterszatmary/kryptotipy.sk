package sk.kryptotipy.web.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sk.kryptotipy.web.api.BtcPriceServiceApi;
import sk.kryptotipy.web.data.jdbc.BtcPriceRepository;

@AllArgsConstructor
@Service
public class BtcPriceService implements BtcPriceServiceApi {

    private final BtcPriceRepository btcPriceRepository;

    public Double getLatestPrice() {
        return btcPriceRepository.getLatestPrice().getPrice();
    }
}

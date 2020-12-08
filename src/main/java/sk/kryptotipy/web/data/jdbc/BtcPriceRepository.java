package sk.kryptotipy.web.data.jdbc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sk.kryptotipy.web.data.BtcPrice;

public interface BtcPriceRepository extends JpaRepository<BtcPrice, Long> {

    @Query(value = "SELECT * FROM btc_price WHERE id=(SELECT max(id) FROM btc_price)", nativeQuery = true)
    BtcPrice getLatestPrice();
}

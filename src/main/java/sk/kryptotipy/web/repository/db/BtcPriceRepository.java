package sk.kryptotipy.web.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sk.kryptotipy.web.data.db.BtcPrice;

public interface BtcPriceRepository extends JpaRepository<BtcPrice, Long> {

    @Query(value = "SELECT * FROM btc_price WHERE id=(SELECT max(id) FROM btc_price)", nativeQuery = true)
    BtcPrice getLatestPrice();
}

package sk.kryptotipy.web.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Donate;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class DonateRepository {

    public List<Donate> getDonates() {
        List<Donate> donates = new ArrayList<>();
        donates.add(new Donate("Bitcoin", "/assets/img/donate/btc.png", "bc1qqx27qhdmaf336g3wpxhetrqdpgh2t20atfn56q", "/assets/img/donate/qr/btc.png"));
        donates.add(new Donate("Ethereum", "/assets/img/donate/ethereum.png", "0x81e07c46013d5EDc3a691e3458dFeF366DFB35c9","/assets/img/donate/qr/eth.png"));
        donates.add(new Donate("Litecoin", "/assets/img/donate/ltc.png", "LTyHnsU6VWUReBpFhgmPPDjvZgWihNC8Uw","/assets/img/donate/qr/ltc.png"));
        donates.add(new Donate("Polkadot", "/assets/img/donate/polka.png", "14yazo7Es9SP3ZaqQzkfKGL51sdWhGZHQWJ6SumJMkop13T1","/assets/img/donate/qr/polka.png"));
        donates.add(new Donate("Cardano", "/assets/img/donate/ada.png", "addr1qxar45whqu0m2vlpv8plxqdwhm0u2zj8grd4pskwktkl7g968tgawpclk5e7zcwr7vq6a0klc59ywsxm2rpvavhdlusq4kqlum","/assets/img/donate/qr/ada.png"));
        donates.add(new Donate("Solana", "/assets/img/donate/solana.png", "BQaprxknYr6FJzEYZgjW3jZHKnX92iTKfjvp7BsCTCjV","/assets/img/donate/qr/solana.png"));
        donates.add(new Donate("NEO", "/assets/img/donate/neo.png", "AQRqZzZraov9BXskPtj8suy1ErdJRLumnk","/assets/img/donate/qr/neo.png"));
        donates.add(new Donate("Monero", "/assets/img/donate/monero.png", "47cM67VRo2qciNNoRH9tyxCbwXqiFsDVE8xMhzrzVUKXGg5VSMrKUgX5JMuH2Kiibc8Qtj8xBuSq9VXtT8wD4VB7KTfntsz","/assets/img/donate/qr/monero.png"));
        return donates;
    }
}

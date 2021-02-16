package sk.kryptotipy.web.repository;

import org.springframework.stereotype.Repository;
import sk.kryptotipy.web.data.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceRepository {

    public List<Service> getExternalServices() {
        List<Service> services = new ArrayList<>();

        services.add(new Service("Trezor One (Black)", "https://shop.trezor.io/product/trezor-one-black?offer_id=35&aff_id=5933", "trezor-one-black.png", "Cold peňaženka s maximálnou bezpečnosťou pre vaše kryptomeny. Podporuje množstvo kryptomien."));
        services.add(new Service("Coinbase", "https://www.coinbase.com", "coinbase-big.png", "Služba na nákup a predaj vybraných kryptomien vybudovaná na beckende GDAxu. Jej výhodou je jednoduchosť."));
        services.add(new Service("Exodus", "https://www.exodus.io", "exodus-big.jpg", "Peňaženka podporujúca množstvo kryptomien plus obsahuje zabudovanú zmenáreň poháňanú ďalšou službou Shapeshift."));
        services.add(new Service("Coinbase.Pro", "https://pro.coinbase.com/", "gdax-big.jpg", "Burza kryptomien patriaca Coinbase. S originálnym názvom GDAX teraz známa ako Coinbase.Pro."));
        services.add(new Service("Poloniex", "https://poloniex.com", "poloniex-big.png", "Americká krypto burza, ktorá vznikla roku 2014 v amerike. Je takisto zaujímavou možnosťou ako začať obchodovať s kryptomenami."));
        services.add(new Service("Jaxx", "https://jaxx.io", "jaxx-big.png", "Peňaženka podporujúca množstvo coinov. Je Lightweight a hot. Nepotrebuje sťahovať celý blockchain, čiže šetrí miesto na hard diskoch."));
        services.add(new Service("Trezor T", "https://shop.trezor.io/product/trezor-model-t?offer_id=15&aff_id=5933", "trezor-t-black.png", "Cold peňaženka s vylepšenou bezpečnosťou pre vaše kryptomeny. Podporuje ešte väčšie množstvo kryptomien ako model One."));
        services.add(new Service("Kucoin", "https://www.kucoin.com/", "kucoin-big.png", "Burza kryptomien. Rýchlo a spoľahlivo pridáva nové kryptomeny s potenciálom. Možno tu nájsť kryptomeny aké na iných burzách neuvidíte."));
        services.add(new Service("KeepKey", "https://lddy.no/v0jj", "keepkey.jpg", "Harvér peňaženka podporujúca množstvo kryptomien od skupiny Shapeshift. Vhodná ako lacnejšia alternatíva ku Trezor produktom."));
        services.add(new Service("Kraken", "https://r.kraken.com/rn4GDj", "kraken-big.jpg", "Americká burza kryptomien s bohatou históriou.  Vznikla roku 2011. Ďalšia z možností ako začať obchodovať s kryptomenami."));
        services.add(new Service("Neo to Gas", "https://neotogas.com", "neotogas-big.jpg", "Prepočíta, aké množstvo GAS tokenu získate ako pasívny príjem za držanie NEO tokenov v špecializovanej NEON peňaženke."));
        services.add(new Service("ATHA", "https://athda.com", "athda-big.jpg", "Posledné All time high (ATH) kryptomien s časovým údajom. ATH je maximálna hodnota, ktorú kryptomena kedy dosiahla."));
        services.add(new Service("Ledger Nano X", "https://shop.ledger.com/pages/ledger-nano-x?r=b88bca019506", "ledger-nano-x.jpg", "Cold peňaženka s dôrazom na bezpečnosť podporuje množstvo kryptomien. Podporuje pripojenie cez Bluetooth"));
        services.add(new Service("Shapeshift", "https://shapeshift.io/", "shapeshift-big.png", " Je krypto-zmenáreň. Jej hlavnou výhodou je, že sa nie je potrebné registrovať. Slúži na zmenu kryptomien na kryptomeny."));
        services.add(new Service("Ethplorer", "https://ethplorer.io", "ethplorer-big.png", "Po zadaní adresy vie ukázať nielen ETH zostatok na vašej peňaženke, ale aj zostatok všetkých ETH tokenov na danej adrese."));
        services.add(new Service("Binance", "https://www.binance.com", "binance-big.png", "Burza kryptomien. Bola založená v Číne roku 2017. V tom istom roku sa jej viac než darilo. Dnes patrí medzi najväčšie krypto-burzy sveta."));
        services.add(new Service("Bitfinex", "https://www.bitfinex.com", "bitfinex-big.jpg", "Burza kryptomien. Tvorca USDT. Založená bola v roku 2012. Vlastní a prevádzkuje spoločnosť iFinex Inc. "));
        services.add(new Service("Bittrex", "https://bittrex.com", "bittrex-big.png", "Burza kryptomien. Založená bola v amerika roku 2013. AKo prvá ponúkla náhradu za USDT tzv. TUSD (True USD)."));
        services.add(new Service("Bitscreener", "https://bitscreener.com/screener", "bitscreener-big.png", "Sortovanie podľa ceny meny, marketcapu, 24 hodinového volume a typu. Chartview ponúka percentuálny súhrn zmeny."));
        services.add(new Service("Coinmarketcap", "https://coinmarketcap.com", "coinmarketcap-big.png", "Stránka monitorujúca kryptomeny, ktoré sa obchodujú. Ponúka množstvo použiteľných rankingov, grafov a krypto-informácií."));
        services.add(new Service("Cryptocompare", "https://www.cryptocompare.com", "cryptocompare-big.png", "Monitoring kryptomien, rôzne kalkulačky pre ťažiarov, peňaženky podľa kryptomien, pre developerov je tu REST API a ďalšie."));
        services.add(new Service("Coindar", "https://coindar.org", "coindar-big.jpg", "Ide o akýsi krypto-kalendár. Vďaka tejto službe budete informovaný o nadchádzajúcich krypto udalostiach a nič vám už neunikne."));
        services.add(new Service("Crypto Fear & Greed Index", "https://alternative.me/crypto/fear-and-greed-index/", "fear-and-greed-index.png", "Crypto Fear & Greed Index meria aká je aktuálna nálada na kryptomenových trhoch. Kontroluje dve základné nálady a to strach a chamtivosť. "));
        services.add(new Service("Coin atm radar", "https://coinatmradar.com/bitcoin-atm-near-me/", "coin-atm-radar.png", "Webová služba umožňujúca nájsť rôzne kryptomaty pre kúpu a predaj rôznych kryptomien vo vašom najbližšom okolí."));
        services.add(new Service("Airdrops.io", "https://airdrops.io", "airdrops-io.jpg", "Služba monitorujúca takzvané airdropy tokenov, čo sú tokeny zadarmo za splnenie najčastejšie úloh na sociálnych sieťach a podobne."));
        services.add(new Service("Staking rewards", "https://www.stakingrewards.com", "staking-rewards.png", "Vďaka tejto službe si viete pohodlne vyrátať, aký veľký je váš pasívny príjem, ak vlastníte niektorý z proof of stake tokenov."));
        services.add(new Service("Krypto tank", "https://kryptotank.cz/", "kryptotank.png", "Ide o čerpaciu stanicu v Českej republike, kde je možné platiť pohonné látky obľúbeným Bitcoinom."));
        services.add(new Service("Whitepaper.io", "https://whitepaper.io/", "whitepaper.jpg", "Whitepaper je technickou špecifikáciou danej kryptomeny. Táto služba ich všetky ponúka na jednom mieste."));
        services.add(new Service("Bitcoin Obituaries", "https://99bitcoins.com/bitcoin-obituaries/", "bitcoin-deaths.jpeg", "Bitcoinu predpovedali smrť už dokopy 395 krát. Tohto roku to bolo už 3 krát. Koľko takýchto predpovedí Bitcoin ešte prežije ?"));





        return services;
    }

    public List<Service> getCooperationServices() {
        List<Service> services = new ArrayList<>();
        services.add(new Service("Výmena spätných odkazov (Backlink building)",
                "",
                "cooperation/back.jpg",
                "Radi podporíme projekty / stránky podobného charakteru výmenou spätných " +
                " odkazov. Takýto odkaz umiestnime do pätičky stránok kryptotipy.sk." +
                " Výmena bude zavŕšená pridaním odkazu kryptotipy.sk do pätičky vašich stránok."));
        services.add(new Service(
                "Zobrazenie odkazov na vaše články na našich stránkach",
                "",
                "cooperation/posts.png",
                "Ak máte záujem byť medzi ďalšími portálmi, ktorých odkazy na články zobrazujeme," +
                        " stačí si pridať spätný odkaz do pätičky vašich stránok a napísať nám na info@kryptotipy.sk." +
                        " Do dvoch dní sa vaše články začnú zobrazovať."));
        services.add(new Service(
                "Článok o kryptotipy.sk",
                "",
                "cooperation/write_post_but_question.jpg",
                "Ak máte záujem o nás napísať článok alebo blog post alebo akýkoľvek text a chýbajú vám" +
                        " informácie, radi Vám ich poskytneme." +
                        " Stačí nám napísať na info@kryptotipy.sk o vašom zámere a položiť otázky, na ktoré by ste radi dostali odpoveď."));
        services.add(new Service(
                "Iný druh spolupráce",
                "",
                "cooperation/backlinks.png",
                "Samozrejme existuje jeden veľký kopec možností spolupráce a my o tom vieme. " +
                        "Ak vám napadla " +
                        "spolupráca, ktorá nebola spomenutá v predošlých 3 možnostiach, neváhajte nás" +
                        " takisto kontaktovať na info@kryptotipy.sk. Tešíme sa na vás a spoluprácu s vami."));
        return services;
    }




    /** views **/
    public List<Service> getExternalServicesViews() {
        List<Service> services = new ArrayList<>();
        
        services.add(new Service("Trezor One (Black)", "https://shop.trezor.io/product/trezor-one-black?offer_id=35&aff_id=5933", "trezor-one-black.png", "Cold peňaženka s maximálnou bezpečnosťou pre vaše kryptomeny. Podporuje množstvo kryptomien."));
        services.add(new Service("ATHA", "https://athda.com", "athda-big.jpg", "Posledné All time high (ATH) kryptomien s časovým údajom. ATH je maximálna hodnota, ktorú kryptomena kedy dosiahla."));
        services.add(new Service("Ethplorer", "https://ethplorer.io", "ethplorer-big.png", "Po zadaní adresy vie ukázať nielen ETH zostatok na vašej peňaženke, ale aj zostatok všetkých ETH tokenov na danej adrese."));
        services.add(new Service("Trezor T", "https://shop.trezor.io/product/trezor-model-t?offer_id=15&aff_id=5933", "trezor-t-black.png", "Cold peňaženka s vylepšenou bezpečnosťou pre vaše kryptomeny. Podporuje ešte väčšie množstvo kryptomien ako model One."));

        services.add(new Service("Crypto Fear & Greed Index", "https://alternative.me/crypto/fear-and-greed-index/", "fear-and-greed-index.png", "Crypto Fear & Greed Index meria aká je aktuálna nálada na kryptomenových trhoch. Kontroluje dve základné nálady a to strach a chamtivosť. "));
        services.add(new Service("Coin atm radar", "https://coinatmradar.com/bitcoin-atm-near-me/", "coin-atm-radar.png", "Webová služba umožňujúca nájsť rôzne kryptomaty pre kúpu a predaj rôznych kryptomien vo vašom najbližšom okolí."));
        services.add(new Service("Ledger Nano X", "https://shop.ledger.com/pages/ledger-nano-x?r=b88bca019506", "ledger-nano-x.jpg", "Cold peňaženka s dôrazom na bezpečnosť podporuje množstvo kryptomien. Podporuje pripojenie cez Bluetooth."));
        services.add(new Service("Staking rewards", "https://www.stakingrewards.com", "staking-rewards.png", "Vďaka tejto službe si viete pohodlne vyrátať, aký veľký je váš pasívny príjem, ak vlastníte niektorý z proof of stake tokenov."));

        services.add(new Service("KeepKey", "https://lddy.no/v0jj", "keepkey.jpg", "Harvér peňaženka podporujúca množstvo kryptomien od skupiny Shapeshift. Vhodná ako lacnejšia alternatíva ku Trezor produktom."));
        services.add(new Service("Whitepaper.io", "https://whitepaper.io/", "whitepaper.jpg", "Whitepaper je technickou špecifikáciou danej kryptomeny. Táto služba ich všetky ponúka na jednom mieste."));
        services.add(new Service("Kraken", "https://r.kraken.com/rn4GDj", "kraken-big.jpg", "Americká burza kryptomien s bohatou históriou.  Vznikla roku 2011. Ďalšia z možností ako začať obchodovať s kryptomenami."));
        services.add(new Service("Exodus", "https://www.exodus.io", "exodus-big.jpg", "Peňaženka podporujúca množstvo kryptomien plus obsahuje zabudovanú zmenáreň poháňanú ďalšou službou Shapeshift."));

        return services;
    }




}

# kryptotipy.sk 🚀

> **Algoritmické rozhodovacie skratky pre racionálnych investorov do kryptomien.**  
> Ultra-rýchly, dark-mode Jamstack & SSG dashboard postavený na Astro, TypeScript a Tailwind CSS. Hosťovaný na GitHub Pages.

[![Deploy to GitHub Pages](https://github.com/peterszatmary/kryptotipy.sk/actions/workflows/deploy.yml/badge.svg)](https://github.com/peterszatmary/kryptotipy.sk/actions/workflows/deploy.yml)
[![Lighthouse Score](https://img.shields.io/badge/Lighthouse-100%2F100-emerald?style=flat-square)](#)
[![Astro Version](https://img.shields.io/badge/Astro-v7-purple?style=flat-square)](#)
[![TypeScript](https://img.shields.io/badge/TypeScript-Strict-blue?style=flat-square)](#)
[![Tailwind CSS](https://img.shields.io/badge/Tailwind-CSS-sky?style=flat-square)](#)
[![Privacy](https://img.shields.io/badge/Privacy-100%25%20Local-emerald?style=flat-square)](#)

---

## 🎯 Filozofia projektu: „Signal over Noise“

Väčšina krypto webov trpí jedným z dvoch extrémov:
- Neprehľadné burzové terminály s 50 blikajúcimi sviečkovými grafmi a indikátormi.
- Clickbait bulvár a platené VIP signálové skupiny.

**Kryptotipy.sk** namiesto toho prináša **5-sekundové rozhodovacie skratky**:
1. Je trh prehriaty na derivátoch a hrozí likvidačný Long Squeeze?
2. Čo v reálnom čase vsádzajú „inteligentné peniaze“ na predikčných trhoch (Polymarket)?
3. Aký je skutočný prepad altcoinov od ATH a koľko % rastu vyžaduje návrat na nulu?
4. Ako sa správa pravidelné mesačné DCA do Bitcoinu v porovnaní s indexom S&P 500 od roku 2016?
5. Kedy presne môže slovenský alebo český daňovník vybrať krypto zisk s **0 % daňou** (3-ročný časový test v ČR vs. 34 % daň v SR)?

---

## 🏗️ Architektúra (Hybrid Jamstack Data Flow)

Projekt kombinuje tri vrstvy dát pre maximálnu rýchlosť, bezpečnosť a nulové náklady na infraštruktúru:

```
┌────────────────────────────────────────────────────────┐
│             1. SSG Build-Time Layer (Astro)            │
│  • Polymarket Gamma API (Smart Money predikcie)        │
│  • CoinGecko API (Top 30 krypto metriky a ATH)         │
│  • SK/CZ RSS Feeds (Kryptomagazin, Bitcoin v Kapse)    │
│  • S&P 500 & BTC historické mesačné dáta (2016 - 2026) │
│  → Statické HTML generované pri builde (každú hodinu)  │
└──────────────────────────┬─────────────────────────────┘
                           │
┌──────────────────────────▼─────────────────────────────┐
│             2. Client-Side Islands (Vanilla TS)        │
│  • Živé sviečky z Binance Spot (200D & 200W Klines)    │
│  • Binance Futures Funding Rate (8h sadzba)            │
│  • Alternative.me Fear & Greed Index                   │
│  • Mempool.space on-chain odporúčané poplatky (sat/vB) │
│  • Wilder's 14D RSI & Mayer Multiple algoritmy         │
└──────────────────────────┬─────────────────────────────┘
                           │
┌──────────────────────────▼─────────────────────────────┐
│           3. 100% Privacy Client Layer                 │
│  • Žiadne klientske portfólio neopúšťa prehliadač     │
│  • browser localStorage perzistencia daňových nákupov  │
│  • 3-ročný časový test (ČR) vs 19% + 15% odvody (SR)   │
└────────────────────────────────────────────────────────┘
```

---

## 🛠️ Použitý technologický stack

- **Framework:** [Astro](https://astro.build/) – Static Site Generation (SSG), Islands Architecture, nulový runtime bloat.
- **Typovanie:** [TypeScript](https://www.typescriptlang.org/) (Strict Mode) – typovo bezpečné indikátory, kalkulačky a API klienty.
- **Štýlovanie:** [Tailwind CSS](https://tailwindcss.com/) – Dark theme (`#0a0a0c`), Bento-grid rozloženie, plynulé mikrointerakcie.
- **Ikony:** `@lucide/astro` (optimalizované SVG bez zbytočného bundle balastu).
- **Hosting & CI/CD:** GitHub Pages cez GitHub Actions (`.github/workflows/deploy.yml`):
  - Automatický build pri pushi do vetvy `main` / `master`.
  - **Hodinový cron (`schedule: '0 * * * *'`)** na automatickú regeneráciu statických dát (Polymarket, CoinGecko, RSS).
  - Manuálny trigger cez `workflow_dispatch`.

---

## 📦 Kľúčové nástroje a moduly

### 1. 🚦 Semafor Trhu & Pákový Kompas (`MarketSemafor.astro`)
- **Vstupy priamo z prehliadača:** Binance Spot 200d & 200w klines, Binance Futures Funding Rate, Alternative.me Fear & Greed, Mempool.space poplatky.
- **Algoritmy:**
  - **Wilder's RSI (14D):** Hladký Wilderov index relatívnej sily (< 30 zľava, > 70 riziko).
  - **Mayer Multiple:** Pomer aktuálnej ceny k 200-dennému kĺzavému priemeru (200 DMA).
  - **Odstup od 200W MA:** Percentuálna vzdialenosť od historického cyklického dna.
  - **Leverage Squeeze Alert:** Detekcia kombinácie `Fear & Greed > 75` AND `Funding Rate > 0.03 % za 8h` s varovným hlásením pred kaskádovitou likvidáciou longov.

### 2. 🎯 Polymarket & Smart Money Radar (`PolymarketRadar.astro`)
- Čisto statický Astro SSG komponent bez JavaScriptu na klientovi.
- Zobrazuje 5 najväčších predikčných trhov s percentuálnou šancou a objemom stávok v miliónoch USD.

### 3. 📉 ATH Zľavomat & Záchranné DCA (`AthDiscountDca.astro`)
- Asymetrická matematika poklesu: demonštruje, prečo prepad o -80 % vyžaduje rast o +400 % na nulu.
- Interaktívny posuvník *„Dokúpiť dnes za X €“* (Averaging down) okamžite prepočíta novú break-even cenu a pokles potrebného rastu k zisku.

### 4. ⏳ Stroj Času: Krypto vs. S&P 500 (`TimeMachineDca.astro`)
- Reálne mesačné historické dáta od januára 2016 do 2026.
- Posuvníky pre mesačnú investíciu (50 € – 2 000 €) a počiatočný rok (2016 – 2025).
- Interaktívny responzívny graf s 3 krivkami (Investovaná hotovosť, S&P 500 a Bitcoin) + metriky ROI % a CAGR % p.a.

### 5. ⚖️ SK vs. CZ Daňový & Časový Test Kalkulátor (`TaxCalculator.astro`)
- **100% súkromie:** Žiadne dáta o nákupoch sa neposielajú na server (iba lokálny `localStorage`).
- **Česká republika (ČR):** Výpočet 3-ročného časového testu (1 095 dní držania = 0 % daň) s presným odpočtom zostávajúcich dní a dátumom oslobodenia. Ročný limit oslobodenia do 100 000 CZK.
- **Slovenská republika (SR):** Výpočet kombinácie 19 % dane z príjmu a 15 % zdravotných odvodov (~34 % efektívna daň zo zisku).
- **Daňová arbitráž:** Presné vyčíslenie rozdielu v eurách medzi zdanením v SR a oslobodením v ČR.

### 6. 📰 Lokálny SK/CZ Krypto Prehľad (`LocalNewsFeed.astro`)
- Build-time agregátor správ z popredných lokálnych médií (Kryptomagazin.sk, Bitcoin v Kapse) bez otravných reklám a sledovacích skriptov.

---

## 🚀 Lokálny vývoj

### Požiadavky
- Node.js `>= 20.0.0`
- npm `>= 9.0.0`

### Inštalácia a spustenie

```bash
# 1. Klonovanie repozitára
git clone https://github.com/peterszatmary/kryptotipy.sk.git
cd kryptotipy.sk

# 2. Inštalácia závislostí
npm install

# 3. Spustenie lokálneho vývojového servera
npm run dev
```

Vývojový server sa spustí na `http://localhost:4321`.

### Produkčný build a náhľad

```bash
# Zostavenie produkčnej statickej verzie
npm run build

# Lokálny preview vygenerovaného výstupu v dist/
npm run preview
```

---

## 🛡️ Bezpečnosť & Ochrana súkromia

- Žiadne cookies, žiadne sledovacie reklamné skripty tretích strán (Google Analytics, Facebook Pixel, atď.).
- Všetky vstupy používateľov v daňovom kalkulátore bežia **výhradne v pamäti prehliadača** a ukladajú sa do `localStorage` používateľa.
- Žiadne API kľúče nie sú vystavené na klientovi.

---

## 📄 Licencia

Projekt je zverejnený pod licenciou [MIT](LICENSE).
Vytvorené s dôrazom na technickú eleganciu, výkon a praktickú hodnotu pre krypto komunitu v SR a ČR.

# Krypto Vyššia / Nižšia 🎮

> **„Máš krypto v malíčku, alebo len na peňaženke?“**  
> Ultra-minimalistická interaktívna herná aréna na TOP 100 kryptomenách postavená na Astro (SSG), Tailwind CSS, TypeScript a Web3Forms.

[![Deploy to GitHub Pages](https://github.com/peterszatmary/kryptotipy.sk/actions/workflows/deploy.yml/badge.svg)](https://github.com/peterszatmary/kryptotipy.sk/actions/workflows/deploy.yml)
[![Astro Version](https://img.shields.io/badge/Astro-v7-purple?style=flat-square)](#)
[![TypeScript](https://img.shields.io/badge/TypeScript-Strict-blue?style=flat-square)](#)
[![Tailwind CSS](https://img.shields.io/badge/Tailwind-CSS-sky?style=flat-square)](#)
[![Web3Forms](https://img.shields.io/badge/Web3Forms-Integrated-emerald?style=flat-square)](#)

---

## 🎯 6 Interaktívnych hier na TOP 100 minciach

1. 🪙 **Vyššia / Nižšia (Higher / Lower):** Porovnávanie dvoch mincí s 3D flipom v 4 režimoch (*Trhová hodnota, 24h zmena %, Prepad od ATH, 24h objem*).
2. 🔢 **Zoraď 4 mince (Sort Four):** Klikaním zoraď 4 náhodné mince od najvyššej po najnižšiu kapitalizáciu.
3. 🕵️ **Krypto Detektív (Mystery Detective):** Uhádni mincu len na základe jej reálnych trhových štatistík bez mena a loga.
4. ⏳ **Časostroj ATH (Time Battle):** Otestuj, ktorá z dvoch mincí dosiahla svoje historické maximum skôr.
5. 🔒 **Pevný strop (Hard Cap Hunter):** Má minca konečný limit zásob (Bitcoin), alebo nekonečnú infláciu (Ethereum, Doge)?
6. 🎯 **Uhádni názov (Guess Name):** Otestuj vizuálnu znalosť loga a ceny a vyber správny názov zo 4 možností.

---

## ⚡ Jamstack & Zero-Cost Architektúra

* **100dvh Single-Screen:** Žiadne scrollovanie, responzívny mobilný a desktopový zážitok.
* **10-minútový LocalStorage Cache:** Po načítaní uloží TOP 100 mincí na 10 minút do prehliadača, šetrí API a eliminuje CoinGecko rate-limity.
* **Build-Time SSG Fallback:** Záložný balíček `fallbackCoins.json` garantuje 100% funkčnosť aj offline.
* **Web3Forms Rebríček:** Asynchrónne odosielanie osobných rekordov do týždenného komunitného rebríčka bez reloadu stránky.

---

## 🚀 Lokálny vývoj

```bash
# 1. Inštalácia závislostí
npm install

# 2. Spustenie dev servera
npm run dev

# 3. Kontrola typov a build
npx astro check
npm run build
```

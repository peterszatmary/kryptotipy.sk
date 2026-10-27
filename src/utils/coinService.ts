import fallbackCoins from '../data/fallbackCoins.json';
import type { Coin, GameMode, GameModeConfig } from '../types/coin';

const CACHE_KEY_DATA = 'cached_coins_data';
const CACHE_KEY_TIMESTAMP = 'cached_coins_timestamp';
const CACHE_TTL_MS = 10 * 60 * 1000; // 10 minút (600 000 ms)

export const STABLECOINS: Set<string> = new Set([
  'usdt', 'usdc', 'dai', 'fdusd', 'usde', 'pyusd', 'tusd', 'usdd', 'frax', 'bspd', 'susde', 'ustc', 'eurc'
]);

export const GAME_MODES: Record<GameMode, GameModeConfig> = {
  market_cap: {
    id: 'market_cap',
    label: 'Trhová hodnota',
    question: 'má VYŠŠIU alebo NIŽŠIU trhovú hodnotu?',
    unit: 'USD',
    higherLabel: 'VYŠŠIA ▲',
    lowerLabel: 'NIŽŠIA ▼',
  },
  price_change_24h: {
    id: 'price_change_24h',
    label: '24h zmena %',
    question: 'má za 24h VYŠŠÍ alebo NIŽŠÍ rast?',
    unit: '%',
    higherLabel: 'VYŠŠÍ ▲',
    lowerLabel: 'NIŽŠÍ ▼',
  },
  ath_drop: {
    id: 'ath_drop',
    label: 'Prepad od ATH',
    question: 'je BLIŽŠIE k maximu (vyšší %) alebo ďalej?',
    unit: '%',
    higherLabel: 'BLIŽŠIE K ATH ▲',
    lowerLabel: 'ĎALEJ OD ATH ▼',
  },
  total_volume: {
    id: 'total_volume',
    label: '24h objem',
    question: 'má za 24h VYŠŠÍ alebo NIŽŠÍ objem obchodov?',
    unit: 'USD',
    higherLabel: 'VYŠŠÍ ▲',
    lowerLabel: 'NIŽŠÍ ▼',
  },
};

/**
 * Kompaktné menové formátovanie ($1.85T, $42.1B, $950M).
 */
export function formatCompactCurrency(val: number): string {
  if (Math.abs(val) >= 1e12) {
    return `$${(val / 1e12).toFixed(2)} biliónov`;
  }
  if (Math.abs(val) >= 1e9) {
    return `$${(val / 1e9).toFixed(2)} mld.`;
  }
  if (Math.abs(val) >= 1e6) {
    return `$${(val / 1e6).toFixed(2)} mil.`;
  }
  if (Math.abs(val) >= 1e3) {
    return `$${(val / 1e3).toFixed(2)} tis.`;
  }
  return `$${val.toLocaleString('sk-SK', { maximumFractionDigits: 2 })}`;
}

/**
 * Formátovanie percent s explicitným znamienkom (+5.42%, -82.15%).
 */
export function formatPercentage(val: number): string {
  const sign = val > 0 ? '+' : '';
  return `${sign}${val.toFixed(2)} %`;
}

/**
 * Získanie číselnej hodnoty na porovnanie podľa aktívneho módu.
 */
export function getMetricValue(coin: Coin, mode: GameMode): number {
  switch (mode) {
    case 'market_cap':
      return coin.market_cap;
    case 'price_change_24h':
      return coin.price_change_percentage_24h;
    case 'ath_drop':
      return coin.ath_change_percentage;
    case 'total_volume':
      return coin.total_volume;
  }
}

/**
 * Formátovanie metriky pre zobrazenie na kartách.
 */
export function formatMetricValue(coin: Coin, mode: GameMode): string {
  switch (mode) {
    case 'market_cap':
      return formatCompactCurrency(coin.market_cap);
    case 'price_change_24h':
      return formatPercentage(coin.price_change_percentage_24h);
    case 'ath_drop':
      return formatPercentage(coin.ath_change_percentage);
    case 'total_volume':
      return formatCompactCurrency(coin.total_volume);
  }
}

/**
 * Formátovanie dátumu ATH (napr. 14. mar 2024).
 */
export function formatAthDate(isoString?: string): string {
  if (!isoString) return 'Neznámy dátum';
  try {
    const d = new Date(isoString);
    return d.toLocaleDateString('sk-SK', {
      day: 'numeric',
      month: 'short',
      year: 'numeric',
    });
  } catch {
    return 'Neznámy dátum';
  }
}

/**
 * Formátovanie ponuky / zásob kryptomeny.
 */
export function formatSupply(supply?: number | null, symbol?: string): string {
  if (supply === null || supply === undefined || supply === 0) {
    return 'Bez pevného stropu (Nekonečná inflácia)';
  }
  const formatted = new Intl.NumberFormat('sk-SK', {
    maximumFractionDigits: 0,
  }).format(supply);
  return `${formatted} ${symbol ? symbol.toUpperCase() : ''}`;
}

/**
 * Sanitizácia: vyradenie stablecoinov pri percentuálnych módoch.
 */
export function filterCoinsForMode(coins: Coin[], mode: GameMode): Coin[] {
  if (mode === 'price_change_24h' || mode === 'ath_drop') {
    return coins.filter((c) => !STABLECOINS.has(c.symbol.toLowerCase()));
  }
  return coins;
}

/**
 * Sťahovanie mincí s 10-minútovým LocalStorage cache a záložným lokálnym JSON balíčkom.
 */
export async function getCoins(): Promise<Coin[]> {
  if (typeof window === 'undefined') {
    return fallbackCoins as Coin[];
  }

  // 1. Kontrola klientskej vyrovnávacej pamäte
  try {
    const cachedData = localStorage.getItem(CACHE_KEY_DATA);
    const cachedTime = localStorage.getItem(CACHE_KEY_TIMESTAMP);

    if (cachedData && cachedTime) {
      const age = Date.now() - parseInt(cachedTime, 10);
      if (age < CACHE_TTL_MS) {
        const parsed = JSON.parse(cachedData);
        if (Array.isArray(parsed) && parsed.length >= 10) {
          return parsed;
        }
      }
    }
  } catch {
    // Chyba pri čítaní localStorage, pokračujeme na fetch
  }

  // 2. Stiahnutie top 100 mincí z CoinGecko
  try {
    const controller = new AbortController();
    const timeoutId = setTimeout(() => controller.abort(), 4000);

    const res = await fetch(
      'https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false',
      {
        headers: { Accept: 'application/json' },
        signal: controller.signal,
      }
    );
    clearTimeout(timeoutId);

    if (res.ok) {
      const data = await res.json();
      if (Array.isArray(data) && data.length >= 15) {
        const cleanCoins: Coin[] = data.map((c: any) => ({
          id: c.id,
          symbol: (c.symbol || '').toUpperCase(),
          name: c.name || c.id,
          image: c.image || '',
          current_price: Number(c.current_price) || 0,
          market_cap: Number(c.market_cap) || 0,
          market_cap_rank: Number(c.market_cap_rank) || 999,
          total_volume: Number(c.total_volume) || 0,
          price_change_percentage_24h: Math.round((Number(c.price_change_percentage_24h) || 0) * 100) / 100,
          ath: Number(c.ath) || 0,
          ath_change_percentage: Math.round((Number(c.ath_change_percentage) || 0) * 100) / 100,
          ath_date: c.ath_date || '2024-01-01T00:00:00.000Z',
          max_supply: c.max_supply ?? null,
          circulating_supply: Number(c.circulating_supply) || 0,
        }));

        try {
          localStorage.setItem(CACHE_KEY_DATA, JSON.stringify(cleanCoins));
          localStorage.setItem(CACHE_KEY_TIMESTAMP, Date.now().toString());
        } catch {}

        return cleanCoins;
      }
    }
  } catch {
    // API limitované (429) alebo offline
  }

  // 3. Bezpečný fallback na pribalený JSON
  return fallbackCoins as Coin[];
}

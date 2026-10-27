import fallbackCoins from '../data/coingecko_fallback.json';
import type { CoinMarketData } from './types';

/**
 * Calculates drawdown % and required % gain to reach ATH.
 * Math:
 * Drawdown: ((Current - ATH) / ATH) * 100
 * Required Gain: ((ATH - Current) / Current) * 100
 */
export function enrichCoinMetrics(coin: {
  id: string;
  symbol: string;
  name: string;
  current_price: number;
  ath: number;
  ath_change_percentage?: number;
  market_cap_rank?: number;
  image?: string;
}): CoinMarketData {
  const current = Number(coin.current_price) || 0;
  const ath = Number(coin.ath) || current || 1;

  const drawdown = ath > 0 ? ((current - ath) / ath) * 100 : 0;
  const requiredGain = current > 0 && ath > current ? ((ath - current) / current) * 100 : 0;

  return {
    id: coin.id,
    symbol: (coin.symbol || '').toUpperCase(),
    name: coin.name || coin.id,
    current_price: current,
    ath: ath,
    ath_change_percentage: Math.round(drawdown * 10) / 10,
    market_cap_rank: coin.market_cap_rank || 999,
    image: coin.image || '',
    drawdown_percent: Math.round(drawdown * 10) / 10,
    required_gain_percent: Math.round(requiredGain * 10) / 10,
  };
}

/**
 * Fetches top coins at build-time or returns pre-bundled fallback data if API rate-limited.
 */
export async function getTopCoins(): Promise<CoinMarketData[]> {
  try {
    const controller = new AbortController();
    const timeoutId = setTimeout(() => controller.abort(), 3500);

    const res = await fetch(
      'https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=30&page=1',
      {
        headers: { Accept: 'application/json' },
        signal: controller.signal,
      }
    );
    clearTimeout(timeoutId);

    if (res.ok) {
      const data = await res.json();
      if (Array.isArray(data) && data.length > 0) {
        return data.map((item: any) => enrichCoinMetrics(item));
      }
    }
  } catch (err) {
    // Graceful fallback to static data
  }

  return (fallbackCoins as any[]).map((item) => enrichCoinMetrics(item));
}

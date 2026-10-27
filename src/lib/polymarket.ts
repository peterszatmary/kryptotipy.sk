import fallbackEvents from '../data/polymarket_fallback.json';
import type { PolymarketEvent } from './types';

/**
 * Fetches active crypto prediction markets from Polymarket Gamma API at build time.
 * Falls back gracefully to curated static dataset if offline or rate-limited.
 */
export async function getPolymarketEvents(): Promise<PolymarketEvent[]> {
  try {
    const controller = new AbortController();
    const timeoutId = setTimeout(() => controller.abort(), 3500);

    const res = await fetch('https://gamma-api.polymarket.com/events?closed=false&limit=50', {
      headers: { Accept: 'application/json' },
      signal: controller.signal,
    });
    clearTimeout(timeoutId);

    if (res.ok) {
      const data = await res.json();
      if (Array.isArray(data) && data.length > 0) {
        const cryptoKeywords = [
          'bitcoin', 'btc', 'crypto', 'ethereum', 'eth', 'solana',
          'sec', 'fed', 'rate', 'etf', 'market cap', 'halving'
        ];

        const matched: PolymarketEvent[] = [];

        for (const item of data) {
          const title = item.title || item.question || '';
          const lowerTitle = title.toLowerCase();

          const isCrypto = cryptoKeywords.some((kw) => lowerTitle.includes(kw));
          if (!isCrypto) continue;

          // Find yes price & volume from markets array
          const market = Array.isArray(item.markets) ? item.markets[0] : null;
          let yesProb = 50;

          if (market && market.outcomePrices) {
            try {
              const prices = typeof market.outcomePrices === 'string'
                ? JSON.parse(market.outcomePrices)
                : market.outcomePrices;
              if (Array.isArray(prices) && prices.length > 0) {
                yesProb = Math.round(parseFloat(prices[0]) * 100);
              }
            } catch {
              // Ignore parse error
            }
          }

          const vol = Number(item.volume || (market ? market.volume : 0)) || 0;

          matched.push({
            id: String(item.id || item.slug || Math.random()),
            question: title,
            category: lowerTitle.includes('bitcoin') || lowerTitle.includes('btc') ? 'Bitcoin' :
                      lowerTitle.includes('eth') ? 'Ethereum' :
                      lowerTitle.includes('sec') || lowerTitle.includes('fed') ? 'Makro / Regulácia' : 'Krypto',
            yesProbability: isNaN(yesProb) ? 50 : Math.min(100, Math.max(0, yesProb)),
            volumeUsd: Math.round(vol),
            endDate: item.endDate ? new Date(item.endDate).toLocaleDateString('sk-SK') : undefined,
            url: `https://polymarket.com/event/${item.slug || ''}`,
          });

          if (matched.length >= 5) break;
        }

        if (matched.length >= 3) {
          return matched;
        }
      }
    }
  } catch (err) {
    // Graceful fallback to static data
  }

  return fallbackEvents as PolymarketEvent[];
}

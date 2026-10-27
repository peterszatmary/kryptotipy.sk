/**
 * Algorithmic indicator calculations for Bitcoin market metrics.
 */

/**
 * Calculates 14-day Wilder's Smoothed Relative Strength Index (RSI).
 * @param closes Array of daily closing prices (oldest to newest)
 */
export function calculateWildersRsi(closes: number[], period: number = 14): number {
  if (closes.length < period + 1) {
    return 50; // Fallback neutral
  }

  const changes: number[] = [];
  for (let i = 1; i < closes.length; i++) {
    changes.push(closes[i] - closes[i - 1]);
  }

  // Initial 14-period SMA of gains & losses
  let avgGain = 0;
  let avgLoss = 0;
  for (let i = 0; i < period; i++) {
    const diff = changes[i];
    if (diff > 0) avgGain += diff;
    else avgLoss += Math.abs(diff);
  }
  avgGain /= period;
  avgLoss /= period;

  // Wilder's smoothing for remaining periods
  for (let i = period; i < changes.length; i++) {
    const diff = changes[i];
    const gain = diff > 0 ? diff : 0;
    const loss = diff < 0 ? Math.abs(diff) : 0;

    avgGain = (avgGain * (period - 1) + gain) / period;
    avgLoss = (avgLoss * (period - 1) + loss) / period;
  }

  if (avgLoss === 0) return 100;
  const rs = avgGain / avgLoss;
  const rsi = 100 - 100 / (1 + rs);
  return Math.round(rsi * 10) / 10;
}

/**
 * Calculates Mayer Multiple: Current Price / 200-day Simple Moving Average.
 * Mayer Multiple > 2.4 = Bublina / Prehriate
 * Mayer Multiple < 0.8 = Podhodnotené / Akumulácia
 */
export function calculateMayerMultiple(currentPrice: number, dailyCloses: number[]): number {
  if (!dailyCloses || dailyCloses.length === 0) return 1.0;
  const lookback = dailyCloses.slice(-200);
  const sum = lookback.reduce((acc, val) => acc + val, 0);
  const sma200 = sum / lookback.length;
  if (sma200 === 0) return 1.0;
  return Math.round((currentPrice / sma200) * 100) / 100;
}

/**
 * Calculates % distance above 200-Week SMA (Historical Cycle Floor).
 */
export function calculate200wMaDistance(currentPrice: number, weeklyCloses: number[]): { sma200w: number; distancePercent: number } {
  if (!weeklyCloses || weeklyCloses.length === 0) {
    return { sma200w: currentPrice, distancePercent: 0 };
  }
  const lookback = weeklyCloses.slice(-200);
  const sum = lookback.reduce((acc, val) => acc + val, 0);
  const sma200w = sum / lookback.length;
  if (sma200w === 0) return { sma200w: currentPrice, distancePercent: 0 };

  const distancePercent = Math.round(((currentPrice - sma200w) / sma200w) * 1000) / 10;
  return {
    sma200w: Math.round(sma200w),
    distancePercent,
  };
}

/**
 * Synthesizes all market metrics into an actionable Verdict & Score (0 - 100).
 */
export function getMarketVerdict(params: {
  rsi14: number;
  mayerMultiple: number;
  fearAndGreed: number;
  fundingRate: number;
  distance200w: number;
}): {
  score: number;
  label: string;
  color: 'emerald' | 'amber' | 'rose' | 'blue';
  description: string;
  isSqueezeRisk: boolean;
} {
  const { rsi14, mayerMultiple, fearAndGreed, fundingRate } = params;

  // Leverage squeeze check
  const isSqueezeRisk = fearAndGreed > 75 && fundingRate > 0.0003;

  // Composite Temperature Score (0 = Max Panic/Discount, 100 = Max Mania/Overheat)
  // Weighted: F&G (35%), Mayer (30%), RSI (25%), Funding (10%)
  const mayerScore = Math.min(100, Math.max(0, ((mayerMultiple - 0.6) / (2.4 - 0.6)) * 100));
  const rsiScore = Math.min(100, Math.max(0, ((rsi14 - 25) / (80 - 25)) * 100));
  const fundingScore = Math.min(100, Math.max(0, ((fundingRate - (-0.0002)) / (0.0008 - (-0.0002))) * 100));

  const compositeScore = Math.round(
    fearAndGreed * 0.35 +
    mayerScore * 0.30 +
    rsiScore * 0.25 +
    fundingScore * 0.10
  );

  let label: string;
  let color: 'emerald' | 'amber' | 'rose' | 'blue';
  let description: string;

  if (compositeScore < 25) {
    label = 'Zóna extrémnej zľavy (Výrazne podhodnotené)';
    color = 'emerald';
    description = 'Trh je v panike alebo hlbokej korekcii. Historicky ide o najvýnosnejšie pásmo pre dlhodobé DCA.';
  } else if (compositeScore < 45) {
    label = 'Priaznivá akumulačná zóna';
    color = 'emerald';
    description = 'Metriky sa držia pod stredovou osou cyklu. Pomer rizika a možného výnosu hrá v prospech nakupujúcich.';
  } else if (compositeScore <= 65) {
    label = 'Neutrálne vyvážený trh';
    color = 'blue';
    description = 'Trh sa pohybuje v normálnych medzicyklových mantineloch bez extrémnych excesov na páke.';
  } else if (compositeScore <= 80) {
    label = 'Zvýšená teplota trhu (Pozor na lokálny vrchol)';
    color = 'amber';
    description = 'Chamtivosť narastá a indikátory sa blížia k prehriatiu. Vhodný čas na zastavenie FOMO nákupov.';
  } else {
    label = 'Kriticky prehriaty trh (Fáza eufórie)';
    color = 'rose';
    description = 'Trh vykazuje bublinové parametre s vysokou chamtivosťou. Vysoké riziko prudkej likvidačnej korekcie.';
  }

  return {
    score: compositeScore,
    label,
    color,
    description,
    isSqueezeRisk,
  };
}

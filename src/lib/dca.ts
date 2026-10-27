import historicalData from '../data/historical_dca.json';
import type { HistoricalDcaPoint } from './types';

export interface AveragingDownResult {
  originalCoins: number;
  originalCurrentValue: number;
  originalUnrealizedPnlPercent: number;
  originalRequiredGain: number;

  additionalCoins: number;
  totalInvested: number;
  totalCoins: number;
  newBreakevenPrice: number;
  newRequiredGain: number;
  gainReductionDelta: number;
}

export function calculateAveragingDown(params: {
  originalPrice: number;
  originalInvested: number;
  currentPrice: number;
  additionalInvested: number;
}): AveragingDownResult {
  const { originalPrice, originalInvested, currentPrice, additionalInvested } = params;

  const validOriginalPrice = Math.max(originalPrice, 0.000001);
  const validCurrentPrice = Math.max(currentPrice, 0.000001);

  const originalCoins = originalInvested / validOriginalPrice;
  const originalCurrentValue = originalCoins * validCurrentPrice;
  const originalUnrealizedPnlPercent = originalInvested > 0
    ? ((originalCurrentValue - originalInvested) / originalInvested) * 100
    : 0;
  const originalRequiredGain = validCurrentPrice < validOriginalPrice
    ? ((validOriginalPrice - validCurrentPrice) / validCurrentPrice) * 100
    : 0;

  const additionalCoins = additionalInvested / validCurrentPrice;
  const totalInvested = originalInvested + additionalInvested;
  const totalCoins = originalCoins + additionalCoins;

  const newBreakevenPrice = totalCoins > 0 ? totalInvested / totalCoins : validOriginalPrice;
  const newRequiredGain = validCurrentPrice < newBreakevenPrice
    ? ((newBreakevenPrice - validCurrentPrice) / validCurrentPrice) * 100
    : 0;

  const gainReductionDelta = Math.max(0, originalRequiredGain - newRequiredGain);

  return {
    originalCoins,
    originalCurrentValue,
    originalUnrealizedPnlPercent,
    originalRequiredGain,
    additionalCoins,
    totalInvested,
    totalCoins,
    newBreakevenPrice,
    newRequiredGain,
    gainReductionDelta,
  };
}

export interface HistoricalDcaSimulationResult {
  totalInvested: number;
  monthsCount: number;
  totalBtcAccumulated: number;
  totalSp500Units: number;
  latestBtcPrice: number;
  latestSp500Price: number;
  btcPortfolioValue: number;
  sp500PortfolioValue: number;
  btcRoiPercent: number;
  sp500RoiPercent: number;
  btcCagr: number;
  sp500Cagr: number;
  timeline: {
    date: string;
    invested: number;
    btcValue: number;
    sp500Value: number;
  }[];
}

export function simulateHistoricalDca(
  monthlyAmount: number,
  startYear: number,
  dataset: HistoricalDcaPoint[] = historicalData as HistoricalDcaPoint[]
): HistoricalDcaSimulationResult {
  const filtered = dataset.filter((pt) => pt.year >= startYear);
  if (filtered.length === 0) {
    return {
      totalInvested: 0,
      monthsCount: 0,
      totalBtcAccumulated: 0,
      totalSp500Units: 0,
      latestBtcPrice: 0,
      latestSp500Price: 0,
      btcPortfolioValue: 0,
      sp500PortfolioValue: 0,
      btcRoiPercent: 0,
      sp500RoiPercent: 0,
      btcCagr: 0,
      sp500Cagr: 0,
      timeline: [],
    };
  }

  let accumulatedBtc = 0;
  let accumulatedSp = 0;
  let totalInvested = 0;
  const timeline: { date: string; invested: number; btcValue: number; sp500Value: number }[] = [];

  for (const pt of filtered) {
    totalInvested += monthlyAmount;
    if (pt.btc > 0) accumulatedBtc += monthlyAmount / pt.btc;
    if (pt.sp500 > 0) accumulatedSp += monthlyAmount / pt.sp500;

    timeline.push({
      date: pt.date,
      invested: Math.round(totalInvested),
      btcValue: Math.round(accumulatedBtc * pt.btc),
      sp500Value: Math.round(accumulatedSp * pt.sp500),
    });
  }

  const latestPoint = filtered[filtered.length - 1];
  const btcFinalValue = accumulatedBtc * latestPoint.btc;
  const spFinalValue = accumulatedSp * latestPoint.sp500;

  const btcRoi = totalInvested > 0 ? ((btcFinalValue - totalInvested) / totalInvested) * 100 : 0;
  const spRoi = totalInvested > 0 ? ((spFinalValue - totalInvested) / totalInvested) * 100 : 0;

  const years = filtered.length / 12;
  // Approximate CAGR: (FinalValue / TotalInvested)^(1 / years) - 1 or annualized rate
  const btcCagr = years >= 1 && totalInvested > 0 && btcFinalValue > 0
    ? (Math.pow(btcFinalValue / totalInvested, 1 / years) - 1) * 100
    : btcRoi;
  const sp500Cagr = years >= 1 && totalInvested > 0 && spFinalValue > 0
    ? (Math.pow(spFinalValue / totalInvested, 1 / years) - 1) * 100
    : spRoi;

  return {
    totalInvested: Math.round(totalInvested),
    monthsCount: filtered.length,
    totalBtcAccumulated: accumulatedBtc,
    totalSp500Units: accumulatedSp,
    latestBtcPrice: latestPoint.btc,
    latestSp500Price: latestPoint.sp500,
    btcPortfolioValue: Math.round(btcFinalValue),
    sp500PortfolioValue: Math.round(spFinalValue),
    btcRoiPercent: Math.round(btcRoi),
    sp500RoiPercent: Math.round(spRoi),
    btcCagr: Math.round(btcCagr * 10) / 10,
    sp500Cagr: Math.round(sp500Cagr * 10) / 10,
    timeline,
  };
}

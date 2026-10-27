export interface CoinMarketData {
  id: string;
  symbol: string;
  name: string;
  current_price: number;
  ath: number;
  ath_change_percentage: number;
  market_cap_rank: number;
  image: string;
  drawdown_percent: number;
  required_gain_percent: number;
}

export interface PolymarketEvent {
  id: string;
  question: string;
  category: string;
  yesProbability: number;
  volumeUsd: number;
  endDate?: string;
  url: string;
}

export interface HistoricalDcaPoint {
  date: string;
  year: number;
  month: number;
  btc: number;
  sp500: number;
}

export interface RssArticle {
  title: string;
  link: string;
  pubDate: string;
  source: string;
  category?: string;
}

export interface MarketMetrics {
  btcPrice: number;
  rsi14: number;
  mayerMultiple: number;
  distance200wMa: number;
  fearAndGreedValue: number;
  fearAndGreedClassification: string;
  fundingRate: number; // e.g. 0.0001 = 0.01%
  fundingRateAnnualized: number;
  mempoolFastest: number;
  mempoolHalfHour: number;
  mempoolMinimum: number;
  leverageSqueezeRisk: boolean;
  overallScore: number; // 0 - 100 (0 = Extreme Fear / Undervalued / Buy, 100 = Extreme Greed / Overheated / Risk)
  verdictLabel: string;
  verdictColor: 'emerald' | 'amber' | 'rose' | 'blue';
  verdictDescription: string;
}

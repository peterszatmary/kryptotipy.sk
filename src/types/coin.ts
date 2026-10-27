export type GameMode = 'market_cap' | 'price_change_24h' | 'ath_drop' | 'total_volume';

export type ActiveGame =
  | 'higher_lower'
  | 'sort_four'
  | 'mystery_stats'
  | 'ath_time'
  | 'supply_cap'
  | 'guess_name';

export interface GameInfo {
  id: ActiveGame;
  title: string;
  shortTitle: string;
  icon: string;
  description: string;
}

export const GAMES_LIST: GameInfo[] = [
  {
    id: 'higher_lower',
    title: 'Vyššia / Nižšia',
    shortTitle: 'Vyššia/Nižšia',
    icon: '▲▼',
    description: 'Porovnávaj dve kryptomeny a hádaj, ktorá má vyššiu metriku.',
  },
  {
    id: 'sort_four',
    title: 'Zoraď 4 mince',
    shortTitle: 'Zoraď 4',
    icon: '🔢',
    description: 'Klikni na 4 náhodné mince a zoraď ich od najväčšej po najmenšiu.',
  },
  {
    id: 'mystery_stats',
    title: 'Krypto Detektív',
    shortTitle: 'Detektív',
    icon: '🕵️',
    description: 'Uhádni kryptomenu len na základe jej reálnych trhových štatistík.',
  },
  {
    id: 'ath_time',
    title: 'Časostroj ATH',
    shortTitle: 'Časostroj',
    icon: '⏳',
    description: 'Uhádni, ktorá z dvoch mincí dosiahla svoje historické maximum (ATH) skôr.',
  },
  {
    id: 'supply_cap',
    title: 'Pevný strop?',
    shortTitle: 'Pevný strop',
    icon: '🔒',
    description: 'Má kryptomena pevne stanovený limit zásob, alebo má nekonečnú infláciu?',
  },
  {
    id: 'guess_name',
    title: 'Uhádni názov',
    shortTitle: 'Uhádni názov',
    icon: '🎯',
    description: 'Pozri si logo a cenu kryptomeny a vyber jej správny názov.',
  },
];

export interface Coin {
  id: string;
  symbol: string;
  name: string;
  image: string;
  current_price: number;
  market_cap: number;
  market_cap_rank: number;
  total_volume: number;
  price_change_percentage_24h: number;
  ath: number;
  ath_change_percentage: number;
  ath_date?: string;
  max_supply?: number | null;
  circulating_supply?: number;
}

export interface GameModeConfig {
  id: GameMode;
  label: string;
  question: string;
  unit: string;
  higherLabel: string;
  lowerLabel: string;
}

export interface Web3FormsPayload {
  access_key: string;
  name: string;
  email: string;
  score: number;
  game_mode: string;
  subject?: string;
  from_name?: string;
}

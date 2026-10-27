export interface TaxLot {
  id: string;
  coin: string;
  buyDate: string; // YYYY-MM-DD
  buyPrice: number; // per coin in EUR
  amount: number;
  currentPrice?: number; // current market price in EUR
}

export interface TaxLotResult {
  lot: TaxLot;
  costBasis: number;
  currentValue: number;
  profitEur: number;
  daysHeld: number;
  // CZ calculation
  isCzTimeTestMet: boolean;
  czDaysRemaining: number;
  czExemptionDate: string;
  czEstimatedTaxEur: number;
  // SK calculation
  skIncomeTaxEur: number; // 19%
  skHealthInsuranceEur: number; // 15%
  skTotalTaxEur: number;
  skEffectiveRatePercent: number;
  // Difference
  arbitrageSavingsEur: number;
}

export function evaluateTaxLot(lot: TaxLot, currentPriceOverride?: number): TaxLotResult {
  const currentPrice = currentPriceOverride ?? (lot.currentPrice ?? lot.buyPrice);
  const costBasis = lot.buyPrice * lot.amount;
  const currentValue = currentPrice * lot.amount;
  const profitEur = currentValue - costBasis;

  const buy = new Date(lot.buyDate);
  const now = new Date();
  const diffTime = Math.max(0, now.getTime() - buy.getTime());
  const daysHeld = Math.floor(diffTime / (1000 * 60 * 60 * 24));

  // CZ 3-Year Time Test (1095 days)
  const isCzTimeTestMet = daysHeld >= 1095;
  const czDaysRemaining = Math.max(0, 1095 - daysHeld);

  const exemptionDateObj = new Date(buy.getTime() + 1095 * 24 * 60 * 60 * 1000);
  const czExemptionDate = exemptionDateObj.toLocaleDateString('sk-SK');

  let czEstimatedTaxEur = 0;
  if (!isCzTimeTestMet && profitEur > 0) {
    // 15% tax on capital gains in CZ
    czEstimatedTaxEur = profitEur * 0.15;
  }

  // SK Tax: 19% income tax + 15% health insurance on crypto gains
  let skIncomeTaxEur = 0;
  let skHealthInsuranceEur = 0;
  if (profitEur > 0) {
    skIncomeTaxEur = profitEur * 0.19;
    skHealthInsuranceEur = profitEur * 0.15;
  }
  const skTotalTaxEur = skIncomeTaxEur + skHealthInsuranceEur;
  const skEffectiveRatePercent = profitEur > 0 ? (skTotalTaxEur / profitEur) * 100 : 0;

  const arbitrageSavingsEur = Math.max(0, skTotalTaxEur - czEstimatedTaxEur);

  return {
    lot,
    costBasis: Math.round(costBasis * 100) / 100,
    currentValue: Math.round(currentValue * 100) / 100,
    profitEur: Math.round(profitEur * 100) / 100,
    daysHeld,
    isCzTimeTestMet,
    czDaysRemaining,
    czExemptionDate,
    czEstimatedTaxEur: Math.round(czEstimatedTaxEur * 100) / 100,
    skIncomeTaxEur: Math.round(skIncomeTaxEur * 100) / 100,
    skHealthInsuranceEur: Math.round(skHealthInsuranceEur * 100) / 100,
    skTotalTaxEur: Math.round(skTotalTaxEur * 100) / 100,
    skEffectiveRatePercent: Math.round(skEffectiveRatePercent * 10) / 10,
    arbitrageSavingsEur: Math.round(arbitrageSavingsEur * 100) / 100,
  };
}

package com.financial.util;

import java.util.HashMap;

import com.financial.pojo.BalanceSheetAnnual;
import com.financial.pojo.BalanceSheetQuarter;
import com.financial.pojo.CashFlowAnnual;
import com.financial.pojo.CashFlowQuarter;
import com.financial.pojo.IncomeStateAnnual;
import com.financial.pojo.IncomeStateQuarter;
import com.financial.pojo.StockRatioRecord.RatioEnum;

// yiqun add code here
public class RatioCalculator {
	public static HashMap<RatioEnum, Float> calculateAllRatios(BalanceSheetAnnual bsa, CashFlowAnnual cfa,
			IncomeStateAnnual isa) {
		HashMap<RatioEnum, Float> map = new HashMap<RatioEnum, Float>();
		// input could be null
		if (!bsa.getTotalCurrentLiabilities().equals("0"))
			map.put(RatioEnum.CurrentRatio, (float) (Double.parseDouble(bsa.getTotalCurrentAssets())
					/ Double.parseDouble(bsa.getTotalCurrentLiabilities())));
		double workingCapital = Double.parseDouble(bsa.getTotalAssets())-Double.parseDouble(bsa.getTotalLiab());
		map.put(RatioEnum.WorkingCapital,(float)(workingCapital));
		if(workingCapital != 0){
			map.put(RatioEnum.SalestoWorkingCapital, (float)(Double.parseDouble(isa.getTotalRevenue())/workingCapital));
		}
		if (!bsa.getTotalCurrentLiabilities().equals("0"))
			map.put(RatioEnum.AcidTestRatio,
					(float) ((Double.parseDouble(bsa.getTotalCurrentAssets()) - Double.parseDouble(bsa.getInventory()))
							/ Double.parseDouble(bsa.getTotalCurrentLiabilities())));
		if (!bsa.getTotalCurrentLiabilities().equals("0"))
			map.put(RatioEnum.CashRatio,
					(float) ((Double.parseDouble(bsa.getCash()) + Double.parseDouble(bsa.getShortTermInvestments()))
							/ Double.parseDouble(bsa.getTotalCurrentLiabilities())));
		// sales to working Capital
		if (!bsa.getTotalAssets().equals("0"))
			map.put(RatioEnum.DebtRatio, (float) (Double.parseDouble(bsa.getTotalLiab())
					/ Double.parseDouble(bsa.getTotalStockholderEquity())));
		if (!bsa.getTotalStockholderEquity().equals("0"))
			map.put(RatioEnum.DebttoTangibleNetWorthRatio,
					(float) (Double.parseDouble(bsa.getTotalLiab())
							/ (Double.parseDouble(bsa.getTotalStockholderEquity())
									- Double.parseDouble(bsa.getIntangibleAssets()))));
		if (!isa.getTotalRevenue().equals("0"))
			map.put(RatioEnum.DaysSalesinReceivables, (float) ((Double.parseDouble(bsa.getNetReceivables())
					/ (Double.parseDouble(isa.getTotalRevenue()) / 365))));
		if (!isa.getCostOfRevenue().equals("0"))
			map.put(RatioEnum.DaysSalesinInventory, (float) ((Double.parseDouble(bsa.getInventory())
					/ (Double.parseDouble(isa.getCostOfRevenue()) / 365))));
		if (!isa.getTotalRevenue().equals("0"))
			map.put(RatioEnum.NetProfitMargin,
					(float) (Double.parseDouble(isa.getNetIncome()) / Double.parseDouble(isa.getTotalRevenue())));
		if (!bsa.getTotalAssets().equals("0"))
			map.put(RatioEnum.TotalAssetTurnover,
					(float) (Double.parseDouble(isa.getTotalRevenue()) / Double.parseDouble(bsa.getTotalAssets())));
		if (!bsa.getTotalAssets().equals("0"))
			map.put(RatioEnum.ReturnonAssets, (float) ((Double.parseDouble(isa.getNetIncome())
					+ Double.parseDouble(isa.getMinorityInterest()) / (Double.parseDouble(bsa.getTotalAssets())))));
		if (!isa.getTotalRevenue().equals("0"))
			map.put(RatioEnum.OperatingIncomeMargin,
					(float) (Double.parseDouble(isa.getOperatingIncome()) / Double.parseDouble(isa.getTotalRevenue())));

		float taxRate = 0;
		if (!isa.getNetIncome().equals("0")) {
			taxRate = (float) ((Double.parseDouble(isa.getIncomeBeforeTax()) - Double.parseDouble(isa.getNetIncome()))
					/ (Double.parseDouble(isa.getNetIncome())));
			map.put(RatioEnum.TaxRate, taxRate);
		}
		if ((Double.parseDouble(bsa.getLongTermDebt()) + Double.parseDouble(bsa.getTotalStockholderEquity())) != 0) {
			double termDebtPlusHolderEquity = (Double.parseDouble(bsa.getLongTermDebt())
					+ Double.parseDouble(bsa.getTotalStockholderEquity()));
			double productOfInterestAndTaxRate = Double.parseDouble(isa.getInterestExpense()) * (1 - taxRate);
			map.put(RatioEnum.ReturnonInvestment,
					(float) ((Double.parseDouble(isa.getNetIncome()) + Double.parseDouble(isa.getMinorityInterest())
							+ productOfInterestAndTaxRate) / termDebtPlusHolderEquity));
		}
		// map.put(RatioEnum.ReturnonInvestment,
		// (float)(((Double.parseDouble(isa.getNetIncome())+Double.parseDouble(isa.getMinorityInterest()+Double.parseDouble(isa.getInterestExpense())*(1-taxRate)))/(Double.parseDouble(bsa.getTotalAssets()+Double.parseDouble(bsa.getTotalStockholderEquity()));

		return map;
	}

	public static HashMap<RatioEnum, Float> calculateAllRatios(BalanceSheetQuarter bsq, CashFlowQuarter cfq,
			IncomeStateQuarter isq) {
		HashMap<RatioEnum, Float> map = new HashMap<RatioEnum, Float>();
		// input could be null

		return map;
	}
}

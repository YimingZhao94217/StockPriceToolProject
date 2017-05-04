package com.financial.util;

import java.util.HashMap;

import com.financial.pojo.BalanceSheetAnnual;
import com.financial.pojo.BalanceSheetQuarter;
import com.financial.pojo.CashFlowAnnual;
import com.financial.pojo.CashFlowQuarter;
import com.financial.pojo.IncomeStateAnnual;
import com.financial.pojo.IncomeStateQuarter;
import com.financial.pojo.StockRatioRecord.RatioEnum;

public class RatioCalculator {
	public static HashMap<RatioEnum, Float> calculateAllRatios(BalanceSheetAnnual bsa, CashFlowAnnual cfa,
			IncomeStateAnnual isa) {
		HashMap<RatioEnum, Float> map = new HashMap<RatioEnum, Float>();

		return map;
	}

	public static HashMap<RatioEnum, Float> calculateAllRatios(BalanceSheetQuarter bsq, CashFlowQuarter cfq,
			IncomeStateQuarter isq) {
		HashMap<RatioEnum, Float> map = new HashMap<RatioEnum, Float>();

		return map;
	}
}
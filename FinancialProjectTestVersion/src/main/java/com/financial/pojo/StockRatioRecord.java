package com.financial.pojo;

import java.util.HashMap;

import com.financial.util.RatioCalculator;

public class StockRatioRecord {
	private String timeSlot; // 6 digits or 4 digits (quarter or year)
	private HashMap<RatioEnum, Float>ratioMap;
	
	// yiqun change here
	public enum RatioEnum{
		CurrentRatio,CashRatio;
	}

	public StockRatioRecord(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public StockRatioRecord() {
		// TODO Auto-generated constructor stub
	}
	
	public StockRatioRecord(String timeSlot, BalanceSheetAnnual bsa, CashFlowAnnual cfa, IncomeStateAnnual isa){
		this.timeSlot = timeSlot;
		ratioMap = RatioCalculator.calculateAllRatios(bsa, cfa, isa);
	}
	
	public StockRatioRecord(String timeSlot, BalanceSheetQuarter bsq, CashFlowQuarter cfq, IncomeStateQuarter isq){
		this.timeSlot = timeSlot;
		ratioMap = RatioCalculator.calculateAllRatios(bsq, cfq, isq);
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public HashMap<RatioEnum, Float> getRatioMap() {
		return ratioMap;
	}

	public void setRatioMap(HashMap<RatioEnum, Float> ratioMap) {
		this.ratioMap = ratioMap;
	}
	
}

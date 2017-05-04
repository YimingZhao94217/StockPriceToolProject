package com.financial.pojo;

import java.util.HashMap;

public class StockRatioRecord {
	private String timeSlot; // 6 digits or 4 digits (quarter or year)
	private HashMap<RatioEnum, Float>ratioMap;
	
	public enum RatioEnum{
		test1, test2;
	}

	public StockRatioRecord(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public StockRatioRecord() {
		// TODO Auto-generated constructor stub
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

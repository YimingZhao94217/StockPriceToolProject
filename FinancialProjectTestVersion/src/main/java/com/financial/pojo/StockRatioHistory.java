package com.financial.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.financial.util.DateFormatUtil;
import com.financial.util.RatioCalculator;

public class StockRatioHistory {
	private Stock stock;
	private List<StockRatioRecord> quarterRatioRecords;
	private List<StockRatioRecord> yearRatioRecords;

	public StockRatioHistory() {
		quarterRatioRecords = new ArrayList<StockRatioRecord>();
		yearRatioRecords = new ArrayList<StockRatioRecord>();
	}

	public StockRatioHistory(Stock stock) {
		this.stock = stock;
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		for (BalanceSheetAnnual bsa : stock.getBalanceSheetAnnuals()) {
			String dateStr = df.format(bsa.getEndingDate());
			StockRatioRecord srr = addYearRatioRecord(DateFormatUtil.getFormattedYearSlot(dateStr));
			CashFlowAnnual cfa = null;
			IncomeStateAnnual isa = null;
			for(CashFlowAnnual c: stock.getCashFlowAnnuals()){
				if(df.format(c.getEndDate()).equals(dateStr)){
					cfa = c;
					break;
				}
			}
			for(IncomeStateAnnual i: stock.getIncomeStatementAnnuals()){
				if(df.format(i.getEndDate()).equals(dateStr)){
					isa = i;
					break;
				}
			}
			srr.setRatioMap(RatioCalculator.calculateAllRatios(bsa, cfa, isa));
		}
		for(BalanceSheetQuarter bsq: stock.getBalanceSheetQuarters()){
			String dateStr = df.format(bsq.getEndingDate());
			StockRatioRecord srr = addQuarterRatioRecord(DateFormatUtil.getFormattedQuarterSlot(dateStr));
			CashFlowQuarter cfq = null;
			IncomeStateQuarter isq = null;
			for(CashFlowQuarter c: stock.getCashFlowQuarters()){
				if(df.format(c.getEndDate()).equals(dateStr)){
					cfq = c;
					break;
				}
			}
			for(IncomeStateQuarter i: stock.getIncomeStatementQuarters()){
				if(df.format(i.getEndDate()).equals(dateStr)){
					isq = i;
					break;
				}
			}
			srr.setRatioMap(RatioCalculator.calculateAllRatios(bsq, cfq, isq));
		}
	}

	public StockRatioHistory(Stock stock, List<String> yearTimeSlots, List<String> quarterTimeSlots) {
		for(String yearSlot: yearTimeSlots){
			BalanceSheetAnnual bsa = null;
			IncomeStateAnnual isa = null;
			CashFlowAnnual cfa = null;
			for(BalanceSheetAnnual b: stock.getBalanceSheetAnnuals()){
				if(DateFormatUtil.getFormattedYearSlot(b.getEndingDate()).equals(yearSlot)){
					bsa = b;
					break;
				}
			}
			for(IncomeStateAnnual i: stock.getIncomeStatementAnnuals()){
				if(DateFormatUtil.getFormattedYearSlot(i.getEndDate()).equals(yearSlot)){
					isa = i;
					break;
				}
			}
			for(CashFlowAnnual c: stock.getCashFlowAnnuals()){
				if(DateFormatUtil.getFormattedYearSlot(c.getEndDate()).equals(yearSlot)){
					cfa = c;
				}
			}
			if(bsa != null || isa != null || cfa != null){
				StockRatioRecord srr = addYearRatioRecord(yearSlot);
				srr.setRatioMap(RatioCalculator.calculateAllRatios(bsa, cfa, isa));
			}
		}
		for(String quarterSlot: quarterTimeSlots){
			BalanceSheetQuarter bsq = null;
			IncomeStateQuarter isq = null;
			CashFlowQuarter cfq = null;
			for(BalanceSheetQuarter b: stock.getBalanceSheetQuarters()){
				if(DateFormatUtil.getFormattedQuarterSlot(b.getEndingDate()).equals(quarterSlot)){
					bsq = b;
					break;
				}
			}
			for(CashFlowQuarter c: stock.getCashFlowQuarters()){
				if(DateFormatUtil.getFormattedQuarterSlot(c.getEndDate()).equals(quarterSlot)){
					cfq = c;
					break;
				}
			}
			for(IncomeStateQuarter i: stock.getIncomeStatementQuarters()){
				if(DateFormatUtil.getFormattedQuarterSlot(i.getEndDate()).equals(quarterSlot)){
					isq = i;
					break;
				}
			}
			if(bsq != null || cfq != null || isq != null){
				StockRatioRecord srr = addQuarterRatioRecord(quarterSlot);
				srr.setRatioMap(RatioCalculator.calculateAllRatios(bsq, cfq, isq));
			}
		}
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public List<StockRatioRecord> getQuarterRatioRecords() {
		return quarterRatioRecords;
	}

	public void setQuarterRatioRecords(List<StockRatioRecord> quarterRatioRecords) {
		this.quarterRatioRecords = quarterRatioRecords;
	}

	public List<StockRatioRecord> getYearRatioRecords() {
		return yearRatioRecords;
	}

	public void setYearRatioRecords(List<StockRatioRecord> yearRatioRecords) {
		this.yearRatioRecords = yearRatioRecords;
	}

	public List<StockRatioRecord> getSortedYearRatioRecords() {
		Collections.sort(yearRatioRecords, new Comparator<StockRatioRecord>() {
			@Override
			public int compare(StockRatioRecord r1, StockRatioRecord r2) {
				// TODO Auto-generated method stub
				int year1 = Integer.parseInt(r1.getTimeSlot());
				int year2 = Integer.parseInt(r2.getTimeSlot());
				return year2 - year1;
			}

		});
		return yearRatioRecords;
	}

	public List<StockRatioRecord> getSortedQuarterRatioRecords() {
		Collections.sort(quarterRatioRecords, new Comparator<StockRatioRecord>() {

			@Override
			public int compare(StockRatioRecord o1, StockRatioRecord o2) {
				// TODO Auto-generated method stub
				int year1 = Integer.parseInt(o1.getTimeSlot().substring(0, 4));
				int year2 = Integer.parseInt(o2.getTimeSlot().substring(0, 4));
				if (year1 == year2) {
					int month1 = Integer.parseInt(o1.getTimeSlot().substring(5, 6));
					int month2 = Integer.parseInt(o2.getTimeSlot().substring(5, 6));
					return month2 - month1;
				}
				return year2 - year1;
			}

		});
		return quarterRatioRecords;
	}

	public void sortBothRatioRecords() {
		Collections.sort(yearRatioRecords, new Comparator<StockRatioRecord>() {
			@Override
			public int compare(StockRatioRecord r1, StockRatioRecord r2) {
				// TODO Auto-generated method stub
				int year1 = Integer.parseInt(r1.getTimeSlot());
				int year2 = Integer.parseInt(r2.getTimeSlot());
				return year2 - year1;
			}

		});

		Collections.sort(quarterRatioRecords, new Comparator<StockRatioRecord>() {

			@Override
			public int compare(StockRatioRecord o1, StockRatioRecord o2) {
				// TODO Auto-generated method stub
				int year1 = Integer.parseInt(o1.getTimeSlot().substring(0, 4));
				int year2 = Integer.parseInt(o2.getTimeSlot().substring(0, 4));
				if (year1 == year2) {
					int month1 = Integer.parseInt(o1.getTimeSlot().substring(5, 6));
					int month2 = Integer.parseInt(o2.getTimeSlot().substring(5, 6));
					return month2 - month1;
				}
				return year2 - year1;
			}

		});
	}

	public StockRatioRecord addYearRatioRecord() {
		StockRatioRecord srr = new StockRatioRecord();
		yearRatioRecords.add(srr);
		return srr;
	}

	public StockRatioRecord addYearRatioRecord(String timeSlot) {
		StockRatioRecord srr = new StockRatioRecord(timeSlot);
		yearRatioRecords.add(srr);
		return srr;
	}

	public StockRatioRecord addQuarterRatioRecord() {
		StockRatioRecord srr = new StockRatioRecord();
		quarterRatioRecords.add(srr);
		return srr;
	}

	public StockRatioRecord addQuarterRatioRecord(String timeSlot) {
		StockRatioRecord srr = new StockRatioRecord(timeSlot);
		quarterRatioRecords.add(srr);
		return srr;
	}
}

package com.financial.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
	public static String getFormattedYearSlot(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		String dateStr = df.format(date);
		int year = Integer.parseInt(dateStr.substring(0, 5));
		int month = Integer.parseInt(dateStr.substring(5, 6));
		if (month > 6)
			return String.valueOf(year);
		else
			return String.valueOf(year - 1);
		// Oct???
	}
	
	public static String getFormattedYearSlot(String dateStr){
		// format: yyyy-MM
		int year = Integer.parseInt(dateStr.substring(0, 5));
		int month = Integer.parseInt(dateStr.substring(5, 6));
		if (month > 6)
			return String.valueOf(year);
		else
			return String.valueOf(year - 1);
	}

	public static String getFormattedYearSlot(int year, int month) {
		if (month > 6)
			return String.valueOf(year);
		else
			return String.valueOf(year - 1);
	}

	public static String getFormattedQuarterSlot(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		String dateStr = df.format(date);
		int year = Integer.parseInt(dateStr.substring(0, 5));
		int month = Integer.parseInt(dateStr.substring(5, 6));
		int quarter = 0;
		if (month > 2 && month <= 5)
			quarter = 1;
		else if (month > 5 && month <= 8)
			quarter = 2;
		else if (month > 8 && month <= 11)
			quarter = 3;
		else {
			quarter = 4;
			if (month > 0)
				year--;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(year);
		sb.append("Q");
		sb.append(quarter);
		return sb.toString();
		// criteria?
	}
	
	public static String getFormattedQuarterSlot(String dateStr){
		//dateStr: yyyy-MM
		int year = Integer.parseInt(dateStr.substring(0, 5));
		int month = Integer.parseInt(dateStr.substring(5, 6));
		int quarter = 0;
		if (month > 2 && month <= 5)
			quarter = 1;
		else if (month > 5 && month <= 8)
			quarter = 2;
		else if (month > 8 && month <= 11)
			quarter = 3;
		else {
			quarter = 4;
			if (month > 0)
				year--;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(year);
		sb.append("Q");
		sb.append(quarter);
		return sb.toString();
	}
	
	public static String getFormattedQuarterSlot(int year, int month){
		int quarter = 0;
		if (month > 2 && month <= 5)
			quarter = 1;
		else if (month > 5 && month <= 8)
			quarter = 2;
		else if (month > 8 && month <= 11)
			quarter = 3;
		else {
			quarter = 4;
			if (month > 0)
				year--;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(year);
		sb.append("Q");
		sb.append(quarter);
		return sb.toString();
	}
}

package com.financial.stockapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.financial.dao.StockDao;
import com.financial.pojo.BalanceSheetAnnual;
import com.financial.pojo.BalanceSheetQuarter;
import com.financial.pojo.Stock;
import com.financial.pojo.StockRatioHistory;
import com.financial.util.DateFormatUtil;

@Controller
public class StockComparisonController {
	@RequestMapping(value = "stockCompare.htm", method = RequestMethod.GET)
	public ModelAndView doService(HttpServletRequest request) throws IOException {
		//for test
		HttpSession session = request.getSession();
		StockRatioHistory srh = new StockRatioHistory();
		srh.addYearRatioRecord("2017");
		srh.addYearRatioRecord("2011");
		srh.addYearRatioRecord("2016");
		srh.addQuarterRatioRecord("2017Q1");
		srh.addQuarterRatioRecord("2017Q2");
		srh.addQuarterRatioRecord("2015Q4");
		srh.sortBothRatioRecords();
		request.setAttribute("srh", srh);
		
		// get action from front end
		Object sessionStocks = session.getAttribute("comparedStocks");
		List<String> selectedStocks = new ArrayList<String>();
		if(sessionStocks != null){
			selectedStocks = (List<String>) sessionStocks;
		}
		String addStockTicker = request.getParameter("addStock");
		if (addStockTicker != null) {
			selectedStocks.add(addStockTicker);
			session.setAttribute("comparedStocks", selectedStocks);
		}
		List<Stock>compareStockList = new ArrayList<Stock>();
		StockDao stockDao = new StockDao();
		for(String s: selectedStocks){
			Stock stock = stockDao.getStock(s);
			if(stock != null)
				compareStockList.add(stock);
		}
		//get time slots
		HashSet<String> yearSlotsSet = new HashSet<String>();
		HashSet<String> quarterSlotsSet = new HashSet<String>();
		for (Stock s : compareStockList) {
			for (BalanceSheetAnnual bsa : s.getBalanceSheetAnnuals()) {
				yearSlotsSet.add(DateFormatUtil.getFormattedYearSlot(bsa.getEndingDate()));
			}
			for (BalanceSheetQuarter bsq : s.getBalanceSheetQuarters()) {
				quarterSlotsSet.add(DateFormatUtil.getFormattedQuarterSlot(bsq.getEndingDate()));
			}
		}
		ArrayList<String> yearSlotsArr = new ArrayList<String>();
		ArrayList<String> quarterSlotsArr = new ArrayList<String>();
		for (String year : yearSlotsSet) {
			yearSlotsArr.add(year);
		}
		for (String quarter : quarterSlotsSet) {
			quarterSlotsArr.add(quarter);
		}
		if (yearSlotsArr.size() > 10) {
			Collections.sort(yearSlotsArr, new Comparator<String>() {

				@Override
				public int compare(String s1, String s2) {
					// TODO Auto-generated method stub
					return Integer.parseInt(s2) - Integer.parseInt(s1);
				}

			});
			int i = 10;
			while(i < yearSlotsArr.size()){
				yearSlotsArr.remove(i);
			}
		}
		if(quarterSlotsArr.size() > 12){
			Collections.sort(quarterSlotsArr, new Comparator<String>(){

				@Override
				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
					int year1 = Integer.parseInt(o1.substring(0, 4));
					int year2 = Integer.parseInt(o2.substring(0, 4));
					if (year1 == year2) {
						int month1 = Integer.parseInt(o1.substring(5, 6));
						int month2 = Integer.parseInt(o2.substring(5, 6));
						return month2 - month1;
					}
					return year2 - year1;
				}
				
			});
			int i = 12;
			while(i < quarterSlotsArr.size()){
				quarterSlotsArr.remove(i);
			}
		}
		
		// calculate ratio
		List<StockRatioHistory>ratioHis = new ArrayList<StockRatioHistory>();
		for(Stock stock: compareStockList){
			StockRatioHistory srhis = new StockRatioHistory(stock, yearSlotsArr, quarterSlotsArr);
			srhis.sortBothRatioRecords();
			ratioHis.add(srhis);
		}
		
		ModelAndView model = new ModelAndView("stockCompare");
		model.addObject("ratioArr", ratioHis);
		stockDao.closeSession();
		return model;
	}
}

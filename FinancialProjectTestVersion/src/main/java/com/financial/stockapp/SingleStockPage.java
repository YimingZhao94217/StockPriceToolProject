package com.financial.stockapp;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.financial.dao.StockDao;
import com.financial.pojo.BalanceSheetAnnual;
import com.financial.pojo.BalanceSheetQuarter;
import com.financial.pojo.CashFlowAnnual;
import com.financial.pojo.CashFlowQuarter;
import com.financial.pojo.IncomeStateAnnual;
import com.financial.pojo.IncomeStateQuarter;
import com.financial.pojo.Stock;
import com.financial.pojo.StockPrice;

@Controller
public class SingleStockPage {

	@RequestMapping(value = "singleStockPage.htm", method = RequestMethod.GET)
	public ModelAndView doService(HttpServletRequest request) throws IOException {
//		System.out.println("----------get into servlet");
		StockDao stockDao = new StockDao();
		ArrayList<Stock> tenStock = stockDao.get20MoreStocks(0);
		request.setAttribute("stockList", tenStock);
		stockDao.closeSession();
		return new ModelAndView("singleStockPage");
	}

	@RequestMapping(value = "search.htm", method = RequestMethod.POST)
	public void doSearchService(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userInput = request.getParameter("userInput");
		List<JSONObject> stockArr = new ArrayList<JSONObject>();
		int count = 0;
		StockDao stockDao = new StockDao();
		List<Stock>result = stockDao.searchStock(userInput.toUpperCase());
		
		// sort result
		HashMap<Integer, List<Stock>>map = new HashMap<Integer, List<Stock>>();
		for(Stock s: result){
			int index = s.getSymbol().indexOf(userInput.toUpperCase());
			if(map.containsKey(index)){
				map.get(index).add(s);
			}else{
				List<Stock>list = new ArrayList<Stock>();
				list.add(s);
				map.put(index, list);
			}
		}
		Object[] keys = map.keySet().toArray();
		Arrays.sort(keys);
		
		// add to json arr
		for(int i = 0; i < keys.length; i++){
			List<Stock>list = map.get(keys[i]);
			for(Stock s: list){
				putStockIntoRev(stockArr, s);
				count++;
			}
		}
		
		JSONObject stockJSON = new JSONObject();
		stockJSON.put("stocks", stockArr);
		stockJSON.put("count", count);
		String jsonString = stockJSON.toString();
		// String jsonString = "{\"name\":\"test\"}";
		PrintWriter out = response.getWriter();
		response.setContentType("application/json; charset=utf-8");
		out.print(jsonString);
		stockDao.closeSession();
	}

	@RequestMapping(value = "showMore.htm", method = RequestMethod.POST)
	public void doShowMoreService(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<JSONObject> stockArr = new ArrayList<JSONObject>();
		int showed = Integer.parseInt(request.getParameter("showed"));
//		ArrayList<Stock> stockList = StockDao.getInstance().getStockList();
//		for (int i = 0; i < 20; i++) {
//			putStockIntoRev(stockArr, stockList.get(showed + i));
//		}
		StockDao stockDao = new StockDao();
		ArrayList<Stock> moreStocks = stockDao.get20MoreStocks(showed);
		for(Stock s: moreStocks){
			putStockIntoRev(stockArr, s);
		}
//		showed += 20;
		JSONObject stockJSON = new JSONObject();
		stockJSON.put("stocks", stockArr);
		stockJSON.put("count", stockArr.size());
		String jsonString = stockJSON.toString();
		// String jsonString = "{\"name\":\"test\"}";
		PrintWriter out = response.getWriter();
		response.setContentType("application/json; charset=utf-8");
		out.print(jsonString);
		stockDao.closeSession();
	}

	private void putStockIntoRev(List<JSONObject> stockArr, Stock s) {
		JSONObject stock = new JSONObject();
		stock.put("Symbol", s.getSymbol());
		stock.put("Market", s.getMarket());
		stock.put("Name", s.getCompanyName());
		stockArr.add(stock);
	}

	@RequestMapping(value = "openRatio.htm", method = RequestMethod.GET)
	public ModelAndView doRadioService(HttpServletRequest request) throws IOException {
		System.out.println("------------open ratio page...");
		String ticker = request.getParameter("ticker");
		StockDao stockDao = new StockDao();
		Stock stock = stockDao.getStock(ticker);
		if (stock != null) {
			request.setAttribute("stock", stock);
			ArrayList<StockPrice> prices = stock.getSortedPrice();
			if (prices.size() > 0) {
				request.setAttribute("currentPrice", prices.get(prices.size() - 1).getPrice());
				if (prices.size() > 1) {
					float deduction = prices.get(prices.size() - 1).getPrice()
							- prices.get(prices.size() - 2).getPrice();
					request.setAttribute("deducAbs", String.format("%.2f", deduction));
					request.setAttribute("deducRel",
							String.format("%.2f", 100 * deduction / prices.get(prices.size() - 1).getPrice()));
					if (deduction > 0) {
						request.setAttribute("status", "up");
					} else {
						request.setAttribute("status", "down");
					}
				}
			} else {
				request.setAttribute("currentPrice", "0.00");
			}

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date[] years = new Date[stock.getBalanceSheetAnnuals().size()];
			int num = 0;
			for (BalanceSheetAnnual bsa : stock.getBalanceSheetAnnuals()) {
				years[num++] = bsa.getEndingDate();
			}
			Arrays.sort(years);
			ArrayList<String> yearList = new ArrayList<String>();
			for (int i = years.length - 1; i >= 0; i--) {
				yearList.add(formatter.format(years[i]));
			}
			request.setAttribute("yearList", yearList);

			Date[] quarters = new Date[stock.getBalanceSheetQuarters().size()];
			num = 0;
			for (BalanceSheetQuarter bsq : stock.getBalanceSheetQuarters()) {
				quarters[num++] = bsq.getEndingDate();
			}
			Arrays.sort(quarters);
			ArrayList<String> quarterList = new ArrayList<String>();
			for (int i = quarters.length - 1; i >= 0; i--) {
				quarterList.add(formatter.format(quarters[i]));
			}
			request.setAttribute("quarterList", quarterList);
		}
		stockDao.closeSession();
		return new ModelAndView("singleStockRatioPage");
	}

	@RequestMapping(value = "retriveRatios.htm", method = RequestMethod.POST)
	public void retriveRatiosService(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String selectTime = request.getParameter("selection").substring(0, 7);
		String ticker = request.getParameter("ticker");
		String period = request.getParameter("period");
		System.out.println(selectTime + ", " + ticker);
		StockDao stockDao = new StockDao();
		Stock stock = stockDao.getStock(ticker);
		if (stock != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
			if (period.equals("year")) {
				// select year
				BalanceSheetAnnual bsa = new BalanceSheetAnnual();
				CashFlowAnnual cfa = new CashFlowAnnual();
				IncomeStateAnnual isa = new IncomeStateAnnual();
				for (BalanceSheetAnnual b : stock.getBalanceSheetAnnuals()) {
					if (formatter.format(b.getEndingDate()).equals(selectTime)) {
						bsa = b;
						break;
					}
				}
				for (CashFlowAnnual cf : stock.getCashFlowAnnuals()) {
					if (formatter.format(cf.getEndDate()).equals(selectTime)) {
						cfa = cf;
						break;
					}
				}
				for (IncomeStateAnnual i : stock.getIncomeStatementAnnuals()) {
					if (formatter.format(i.getEndDate()).equals(selectTime)) {
						isa = i;
					}
				}
//				System.out.println(bsa.getAccountsPayable());
//				System.out.println(isa.getTotalRevenue());
			} else {
				// select quarter
				BalanceSheetQuarter bsq = new BalanceSheetQuarter();
				IncomeStateQuarter isq = new IncomeStateQuarter();
				CashFlowQuarter cfq = new CashFlowQuarter();
				for (BalanceSheetQuarter b : stock.getBalanceSheetQuarters()) {
					if (formatter.format(b.getEndingDate()).equals(selectTime)) {
						bsq = b;
						break;
					}
				}
				for (CashFlowQuarter c : stock.getCashFlowQuarters()) {
					if (formatter.format(c.getEndDate()).equals(selectTime)) {
						cfq = c;
					}
				}
				for (IncomeStateQuarter i : stock.getIncomeStatementQuarters()) {
					if (formatter.format(i.getEndDate()).equals(selectTime)) {
						isq = i;
					}
				}
				System.out.println(isq.getCostOfRevenue());
			}
		}
		// convert table to json format
		stockDao.closeSession();
		
		
		// Calendar c = Calendar.getInstance();
		// if(selectTime.length() == 4){
		// //select year
		// BalanceSheetAnnual bsa = new BalanceSheetAnnual();
		// for(BalanceSheetAnnual b: stock.getBalanceSheetAnnuals()){
		// c.setTime(b.getEndingDate());
		// int year = c.get(Calendar.YEAR);
		// int month = c.get(Calendar.MONTH);
		// if(month > 4 && year == Integer.parseInt(selectTime)){
		// bsa = b;
		// break;
		// }else if(month <= 4 && year == Integer.parseInt(selectTime) + 1){
		// bsa = b;
		// break;
		// }
		// }
		// CashFlowAnnual cfa = new CashFlowAnnual();
		// for(CashFlowAnnual cf: stock.getCashFlowAnnuals()){
		// c.setTime(cf.getEndDate());
		// int year = c.get(Calendar.YEAR);
		// int month = c.get(Calendar.MONTH);
		// if(month > 4 && year == Integer.parseInt(selectTime)){
		// cfa = cf;
		// break;
		// }else if(month <= 4 && year == Integer.parseInt(selectTime) + 1){
		// cfa = cf;
		// break;
		// }
		// }
		// IncomeStateAnnual isa = new IncomeStateAnnual();
		// for(IncomeStateAnnual i: stock.getIncomeStatementAnnuals()){
		// c.setTime(i.getEndDate());
		// int year = c.get(Calendar.YEAR);
		// int month = c.get(Calendar.MONTH);
		// if(month > 4 && year == Integer.parseInt(selectTime)){
		// isa = i;
		// break;
		// }else if(month <= 4 && year == Integer.parseInt(selectTime) + 1){
		// isa = i;
		// break;
		// }
		// }
		// }else if(selectTime.length() == 6){
		// //select quarter
		// BalanceSheetQuarter bsq = new BalanceSheetQuarter();
		// int selectYear = Integer.parseInt(selectTime.substring(0, 4));
		// int quarter = Integer.parseInt(selectTime.substring(5,6));
		// for(BalanceSheetQuarter b: stock.getBalanceSheetQuarters()){
		// c.setTime(b.getEndingDate());
		// int year = c.get(Calendar.YEAR);
		// if(year == selectYear){
		// int month = c.get(Calendar.MONTH);
		// if(quarter == 1 && month > 2 && month <= 5){
		// bsq = b;
		// break;
		// }else if(quarter == 2 && month > 5 && month <= 8){
		// bsq = b;
		// break;
		// }else if(quarter == 3 && month > 8 && month <= 11){
		// bsq = b;
		// break;
		// }else if(month == 12 || month <= 2){
		// bsq = b;
		// break;
		// }
		// }
		// }
		// }
	}

}

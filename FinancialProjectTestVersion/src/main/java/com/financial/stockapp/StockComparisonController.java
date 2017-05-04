package com.financial.stockapp;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.financial.pojo.StockRatioHistory;

@Controller
public class StockComparisonController {
	@RequestMapping(value = "stockCompare.htm", method = RequestMethod.GET)
	public ModelAndView doService(HttpServletRequest request) throws IOException {
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
		return new ModelAndView("stockCompare");
	}
}

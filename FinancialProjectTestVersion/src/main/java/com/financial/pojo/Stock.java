package com.financial.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Stock")
public class Stock {
	private int id;
	private String symbol;
	private String market;
	private String companyName;
	private Set<StockPrice> stockPrices;
	private EarningDate earningDates;
	private Set<BalanceSheetAnnual>balanceSheetAnnuals;
	private Set<BalanceSheetQuarter>balanceSheetQuarters;
	private Set<CashFlowAnnual>cashFlowAnnuals;
	private Set<CashFlowQuarter>cashFlowQuarters;
	private Set<IncomeStateAnnual>incomeStatementAnnuals;
	private Set<IncomeStateQuarter>incomeStatementQuarters;
	
	public Stock(){
		stockPrices = new HashSet<StockPrice>();
		balanceSheetAnnuals = new HashSet<BalanceSheetAnnual>();
		balanceSheetQuarters = new HashSet<BalanceSheetQuarter>();
		cashFlowAnnuals = new HashSet<CashFlowAnnual>();
		cashFlowQuarters = new HashSet<CashFlowQuarter>();
		incomeStatementAnnuals = new HashSet<IncomeStateAnnual>();
		incomeStatementQuarters = new HashSet<IncomeStateQuarter>();
		earningDates = new EarningDate();
	}
	
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Id
	@Column(name="Symbol")
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	@Column(name="Company_Name")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name="Market")
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="stock")
	@OrderBy("Timestamp DESC")
	public Set<StockPrice> getStockPrices() {
		return stockPrices;
	}

	public void setStockPrices(Set<StockPrice> stockPrices) {
		this.stockPrices = stockPrices;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="stock")
	@OrderBy("id ASC")
	public Set<BalanceSheetAnnual> getBalanceSheetAnnuals() {
		return balanceSheetAnnuals;
	}
	public void setBalanceSheetAnnuals(Set<BalanceSheetAnnual> balanceSheetAnnuals) {
		this.balanceSheetAnnuals = balanceSheetAnnuals;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="stock")
	@OrderBy("id ASC")
	public Set<BalanceSheetQuarter> getBalanceSheetQuarters() {
		return balanceSheetQuarters;
	}
	public void setBalanceSheetQuarters(Set<BalanceSheetQuarter> balanceSheetQuarters) {
		this.balanceSheetQuarters = balanceSheetQuarters;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="stock")
	@OrderBy("id ASC")
	public Set<CashFlowAnnual> getCashFlowAnnuals() {
		return cashFlowAnnuals;
	}
	public void setCashFlowAnnuals(Set<CashFlowAnnual> cashFlowAnnuals) {
		this.cashFlowAnnuals = cashFlowAnnuals;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="stock")
	@OrderBy("id ASC")
	public Set<CashFlowQuarter> getCashFlowQuarters() {
		return cashFlowQuarters;
	}
	public void setCashFlowQuarters(Set<CashFlowQuarter> cashFlowQuarters) {
		this.cashFlowQuarters = cashFlowQuarters;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="stock")
	@OrderBy("id ASC")
	public Set<IncomeStateAnnual> getIncomeStatementAnnuals() {
		return incomeStatementAnnuals;
	}
	public void setIncomeStatementAnnuals(Set<IncomeStateAnnual> incomeStatementAnnuals) {
		this.incomeStatementAnnuals = incomeStatementAnnuals;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="stock")
	@OrderBy("id ASC")
	public Set<IncomeStateQuarter> getIncomeStatementQuarters() {
		return incomeStatementQuarters;
	}
	public void setIncomeStatementQuarters(Set<IncomeStateQuarter> incomeStatementQuarters) {
		this.incomeStatementQuarters = incomeStatementQuarters;
	}

	@OneToOne(mappedBy="stock", fetch=FetchType.EAGER)
	public EarningDate getEarningDates() {
		return earningDates;
	}

	public void setEarningDates(EarningDate earningDates) {
		this.earningDates = earningDates;
	}
	
	@Transient
	public ArrayList<StockPrice> getSortedPrice(){
		Set<StockPrice> set = getStockPrices();
		ArrayList<StockPrice>sortArr = new ArrayList<StockPrice>(set);
		Collections.sort(sortArr, new Comparator<StockPrice>(){
			@Override
			public int compare(StockPrice sp1, StockPrice sp2) {
				return sp1.getTimeStamp().compareTo(sp2.getTimeStamp());
			}
			
		});
		return sortArr;
	}
}

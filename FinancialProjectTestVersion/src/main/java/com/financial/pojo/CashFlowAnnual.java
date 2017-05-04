package com.financial.pojo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Cash_Flow_Annual")
public class CashFlowAnnual {
	private int id;
	private Stock stock;
	private Date endDate;
	private String netIncome;
	private String depreciation;
	private String changeToNetincome;
	private String changeToAccountReceivables;
	private String changeToLiabilities;
	private String changeToInventory;
	private String changeToOperatingActivities;
	private String totalCashFromOperatingActivities;
	private String capitalExpenditures;
	private String investments;
	private String otherCashflowsFromInvestingActivities;
	private String totalCashflowsFromInvestingActivities;
	private String dividendsPaid;
	private String salePurchaseOfStock;
	private String netBorrowings;
	private String otherCashflowsFromFinancingActivities;
	private String totalCashFromFinancingActivities;
	private String effectOfExchangeRate;
	private String changeInCash;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="Symbol")
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	@Column(name="Ending_Date")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Column(name="Net_Income")
	public String getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(String netIncome) {
		this.netIncome = netIncome;
	}
	@Column(name="Depreciation_Depletion")
	public String getDepreciation() {
		return depreciation;
	}
	public void setDepreciation(String depreciation) {
		this.depreciation = depreciation;
	}
	@Column(name="Adjustments_To_Net_Income")
	public String getChangeToNetincome() {
		return changeToNetincome;
	}
	public void setChangeToNetincome(String changeToNetincome) {
		this.changeToNetincome = changeToNetincome;
	}
	@Column(name="Changes_In_Accounts_Receivables")
	public String getChangeToAccountReceivables() {
		return changeToAccountReceivables;
	}
	public void setChangeToAccountReceivables(String changeToAccountReceivables) {
		this.changeToAccountReceivables = changeToAccountReceivables;
	}
	@Column(name="Changes_In_Liabilities")
	public String getChangeToLiabilities() {
		return changeToLiabilities;
	}
	public void setChangeToLiabilities(String changeToLiabilities) {
		this.changeToLiabilities = changeToLiabilities;
	}
	@Column(name="Changes_In_Inventories")
	public String getChangeToInventory() {
		return changeToInventory;
	}
	public void setChangeToInventory(String changeToInventory) {
		this.changeToInventory = changeToInventory;
	}
	@Column(name="Changes_In_Other_Operating_Activities")
	public String getChangeToOperatingActivities() {
		return changeToOperatingActivities;
	}
	public void setChangeToOperatingActivities(String changeToOperatingActivities) {
		this.changeToOperatingActivities = changeToOperatingActivities;
	}
	@Column(name="Total_Cash_Flow_From_Operating_Activities")
	public String getTotalCashFromOperatingActivities() {
		return totalCashFromOperatingActivities;
	}
	public void setTotalCashFromOperatingActivities(String totalCashFromOperatingActivities) {
		this.totalCashFromOperatingActivities = totalCashFromOperatingActivities;
	}
	@Column(name="Capital_Expenditures")
	public String getCapitalExpenditures() {
		return capitalExpenditures;
	}
	public void setCapitalExpenditures(String capitalExpenditures) {
		this.capitalExpenditures = capitalExpenditures;
	}
	@Column(name="Investments")
	public String getInvestments() {
		return investments;
	}
	public void setInvestments(String investments) {
		this.investments = investments;
	}
	@Column(name="Other_Cash_flows_from_Investing_Activities")
	public String getOtherCashflowsFromInvestingActivities() {
		return otherCashflowsFromInvestingActivities;
	}
	public void setOtherCashflowsFromInvestingActivities(String otherCashflowsFromInvestingActivities) {
		this.otherCashflowsFromInvestingActivities = otherCashflowsFromInvestingActivities;
	}
	@Column(name="Total_Cash_Flows_From_Investing_Activities")
	public String getTotalCashflowsFromInvestingActivities() {
		return totalCashflowsFromInvestingActivities;
	}
	public void setTotalCashflowsFromInvestingActivities(String totalCashflowsFromInvestingActivities) {
		this.totalCashflowsFromInvestingActivities = totalCashflowsFromInvestingActivities;
	}
	@Column(name="Dividends_Paid")
	public String getDividendsPaid() {
		return dividendsPaid;
	}
	public void setDividendsPaid(String dividendsPaid) {
		this.dividendsPaid = dividendsPaid;
	}
	@Column(name="Sale_Purchase_of_Stock")
	public String getSalePurchaseOfStock() {
		return salePurchaseOfStock;
	}
	public void setSalePurchaseOfStock(String salePurchaseOfStock) {
		this.salePurchaseOfStock = salePurchaseOfStock;
	}
	@Column(name="Net_Borrowings")
	public String getNetBorrowings() {
		return netBorrowings;
	}
	public void setNetBorrowings(String netBorrowings) {
		this.netBorrowings = netBorrowings;
	}
	@Column(name="Other_Cash_Flows_from_Financing_Activities")
	public String getOtherCashflowsFromFinancingActivities() {
		return otherCashflowsFromFinancingActivities;
	}
	public void setOtherCashflowsFromFinancingActivities(String otherCashflowsFromFinancingActivities) {
		this.otherCashflowsFromFinancingActivities = otherCashflowsFromFinancingActivities;
	}
	@Column(name="Total_Cash_Flows_From_Financing_Activities")
	public String getTotalCashFromFinancingActivities() {
		return totalCashFromFinancingActivities;
	}
	public void setTotalCashFromFinancingActivities(String totalCashFromFinancingActivities) {
		this.totalCashFromFinancingActivities = totalCashFromFinancingActivities;
	}
	@Column(name="Effect_Of_Exchange_Rate_Changes")
	public String getEffectOfExchangeRate() {
		return effectOfExchangeRate;
	}
	public void setEffectOfExchangeRate(String effectOfExchangeRate) {
		this.effectOfExchangeRate = effectOfExchangeRate;
	}
	@Column(name="Change_In_Cash_and_Cash_Equivalents")
	public String getChangeInCash() {
		return changeInCash;
	}
	public void setChangeInCash(String changeInCash) {
		this.changeInCash = changeInCash;
	}

}

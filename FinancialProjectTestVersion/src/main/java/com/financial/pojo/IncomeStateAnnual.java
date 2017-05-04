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
@Table(name="IncomeStatement_Annual")
public class IncomeStateAnnual {
	private int id;
	private Stock stock;
	private Date endDate;
	private String totalRevenue;
	private String costOfRevenue;
	private String grossProfit;
	private String researchDevelopment;
	private String sellingGeneralAdministrative;
	private String nonRecurring;
	private String otherOperatingExpenses;
	private String totalOperatingExpenses;
	private String operatingIncome;
	private String totalOtherIncomeExpenseNet;
	private String ebit;
	private String interestExpense;
	private String incomeBeforeTax;
	private String incomeTaxExpense;
	private String minorityInterest;
	private String netIncomeFromContinuingOps;
	private String discontinuedOperations;
	private String extraordinaryItems;
	private String effectOfAccountingCharges;
	private String otherItems;
	private String netIncome;
	private String preferredStockAndOtherAdjustments;
	private String netIncomeApplicableToCommonShares;
	
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
	@Column(name="Total_Revenue")
	public String getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(String totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	@Column(name="Cost_of_Revenue")
	public String getCostOfRevenue() {
		return costOfRevenue;
	}
	public void setCostOfRevenue(String costOfRevenue) {
		this.costOfRevenue = costOfRevenue;
	}
	@Column(name="Gross_Profit")
	public String getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(String grossProfit) {
		this.grossProfit = grossProfit;
	}
	@Column(name="Research_Development")
	public String getResearchDevelopment() {
		return researchDevelopment;
	}
	public void setResearchDevelopment(String researchDevelopment) {
		this.researchDevelopment = researchDevelopment;
	}
	@Column(name="Selling_General_and_Administrative")
	public String getSellingGeneralAdministrative() {
		return sellingGeneralAdministrative;
	}
	public void setSellingGeneralAdministrative(String sellingGeneralAdministrative) {
		this.sellingGeneralAdministrative = sellingGeneralAdministrative;
	}
	@Column(name="Non_Recurring")
	public String getNonRecurring() {
		return nonRecurring;
	}
	public void setNonRecurring(String nonRecurring) {
		this.nonRecurring = nonRecurring;
	}
	@Column(name="Other_Operating_Expenses")
	public String getOtherOperatingExpenses() {
		return otherOperatingExpenses;
	}
	public void setOtherOperatingExpenses(String otherOperatingExpenses) {
		this.otherOperatingExpenses = otherOperatingExpenses;
	}
	@Column(name="Total_Operating_Expenses")
	public String getTotalOperatingExpenses() {
		return totalOperatingExpenses;
	}
	public void setTotalOperatingExpenses(String totalOperatingExpenses) {
		this.totalOperatingExpenses = totalOperatingExpenses;
	}
	@Column(name="Operating_Income_or_Loss")
	public String getOperatingIncome() {
		return operatingIncome;
	}
	public void setOperatingIncome(String operatingIncome) {
		this.operatingIncome = operatingIncome;
	}
	@Column(name="Total_Other_Income_Expenses_Net")
	public String getTotalOtherIncomeExpenseNet() {
		return totalOtherIncomeExpenseNet;
	}
	public void setTotalOtherIncomeExpenseNet(String totalOtherIncomeExpenseNet) {
		this.totalOtherIncomeExpenseNet = totalOtherIncomeExpenseNet;
	}
	@Column(name="Earnings_Before_Interest_and_Taxes")
	public String getEbit() {
		return ebit;
	}
	public void setEbit(String ebit) {
		this.ebit = ebit;
	}
	@Column(name="Interest_Expense")
	public String getInterestExpense() {
		return interestExpense;
	}
	public void setInterestExpense(String interestExpense) {
		this.interestExpense = interestExpense;
	}
	@Column(name="Income_Before_Tax")
	public String getIncomeBeforeTax() {
		return incomeBeforeTax;
	}
	public void setIncomeBeforeTax(String incomeBeforeTax) {
		this.incomeBeforeTax = incomeBeforeTax;
	}
	@Column(name="Income_Tax_Expense")
	public String getIncomeTaxExpense() {
		return incomeTaxExpense;
	}
	public void setIncomeTaxExpense(String incomeTaxExpense) {
		this.incomeTaxExpense = incomeTaxExpense;
	}
	@Column(name="Minority_Interest")
	public String getMinorityInterest() {
		return minorityInterest;
	}
	public void setMinorityInterest(String minorityInterest) {
		this.minorityInterest = minorityInterest;
	}
	@Column(name="Net_Income_From_Continuing_Ops")
	public String getNetIncomeFromContinuingOps() {
		return netIncomeFromContinuingOps;
	}
	public void setNetIncomeFromContinuingOps(String netIncomeFromContinuingOps) {
		this.netIncomeFromContinuingOps = netIncomeFromContinuingOps;
	}
	@Column(name="Discontinued_Operations")
	public String getDiscontinuedOperations() {
		return discontinuedOperations;
	}
	public void setDiscontinuedOperations(String discontinuedOperations) {
		this.discontinuedOperations = discontinuedOperations;
	}
	@Column(name="Extraordinary_Items")
	public String getExtraordinaryItems() {
		return extraordinaryItems;
	}
	public void setExtraordinaryItems(String extraordinaryItems) {
		this.extraordinaryItems = extraordinaryItems;
	}
	@Column(name="Effect_Of_Accounting_Changes")
	public String getEffectOfAccountingCharges() {
		return effectOfAccountingCharges;
	}
	public void setEffectOfAccountingCharges(String effectOfAccountingCharges) {
		this.effectOfAccountingCharges = effectOfAccountingCharges;
	}
	@Column(name="Other_Items_nonrecurring_events")
	public String getOtherItems() {
		return otherItems;
	}
	public void setOtherItems(String otherItems) {
		this.otherItems = otherItems;
	}
	@Column(name="Net_Income")
	public String getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(String netIncome) {
		this.netIncome = netIncome;
	}
	@Column(name="Preferred_Stock_And_Other_Adjustments")
	public String getPreferredStockAndOtherAdjustments() {
		return preferredStockAndOtherAdjustments;
	}
	public void setPreferredStockAndOtherAdjustments(String preferredStockAndOtherAdjustments) {
		this.preferredStockAndOtherAdjustments = preferredStockAndOtherAdjustments;
	}
	@Column(name="Net_Income_Applicable_To_Common_Shares")
	public String getNetIncomeApplicableToCommonShares() {
		return netIncomeApplicableToCommonShares;
	}
	public void setNetIncomeApplicableToCommonShares(String netIncomeApplicableToCommonShares) {
		this.netIncomeApplicableToCommonShares = netIncomeApplicableToCommonShares;
	}
	
}

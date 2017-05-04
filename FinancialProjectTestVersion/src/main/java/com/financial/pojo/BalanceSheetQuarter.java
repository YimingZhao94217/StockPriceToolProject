package com.financial.pojo;

import java.util.Date;

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
@Table(name="Balance_Quarter")
public class BalanceSheetQuarter {
	private int id;
	private Stock stock;
	private Date endingDate;
	private String cash;
	private String shortTermInvestments;
	private String netReceivables;
	private String inventory;
	private String otherCurrentAssets;
	private String totalCurrentAssets;
	private String longTermInvestments;
	private String propertyPlantEquipment;
	private String goodWill;
	private String intangibleAssets;
	private String accumulatedAmortization;
	private String otherAssets;
	private String deferredLongTermAssetCharges;
	private String totalAssets;
	private String accountsPayable;
	private String shortLongTermDebt;
	private String otherCurrentLiab;
	private String totalCurrentLiabilities;
	private String longTermDebt;
	private String otherLiab;
	private String deferredLongTermLiab;
	private String minorityInterest;
	private String negativeGoodwill;
	private String totalLiab;
	private String miscStocksOptionsWarrants;
	private String redeemablePreferredStock;
	private String preferedStock;
	private String commonStock;
	private String retainedEarnings;
	private String treasuryStock;
	private String capitalSurplus;
	private String otherStockholderEquity;
	private String totalStockholderEquity;
	private String netTangibleAssets;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Symbol")
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	@Column(name="Ending_Date")
	public Date getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}
	@Column(name="Cash_Equivalents")
	public String getCash() {
		return cash;
	}
	public void setCash(String cash) {
		this.cash = cash;
	}
	@Column(name="Short_Term_Investments")
	public String getShortTermInvestments() {
		return shortTermInvestments;
	}
	public void setShortTermInvestments(String shortTermInvestments) {
		this.shortTermInvestments = shortTermInvestments;
	}
	@Column(name="Net_Receivables")
	public String getNetReceivables() {
		return netReceivables;
	}
	public void setNetReceivables(String netReceivables) {
		this.netReceivables = netReceivables;
	}
	@Column(name="Inventory")
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	@Column(name="Other_Current_Assets")
	public String getOtherCurrentAssets() {
		return otherCurrentAssets;
	}
	public void setOtherCurrentAssets(String otherCurrentAssets) {
		this.otherCurrentAssets = otherCurrentAssets;
	}
	@Column(name="Total_Current_Assets")
	public String getTotalCurrentAssets() {
		return totalCurrentAssets;
	}
	public void setTotalCurrentAssets(String totalCurrentAssets) {
		this.totalCurrentAssets = totalCurrentAssets;
	}
	@Column(name="Long_Term_Investments")
	public String getLongTermInvestments() {
		return longTermInvestments;
	}
	public void setLongTermInvestments(String longTermInvestments) {
		this.longTermInvestments = longTermInvestments;
	}
	@Column(name="Property_Plant_and_Equipment")
	public String getPropertyPlantEquipment() {
		return propertyPlantEquipment;
	}
	public void setPropertyPlantEquipment(String propertyPlantEquipment) {
		this.propertyPlantEquipment = propertyPlantEquipment;
	}
	@Column(name="Goodwill")
	public String getGoodWill() {
		return goodWill;
	}
	public void setGoodWill(String goodWill) {
		this.goodWill = goodWill;
	}
	@Column(name="Intangible_Assets")
	public String getIntangibleAssets() {
		return intangibleAssets;
	}
	public void setIntangibleAssets(String intangibleAssets) {
		this.intangibleAssets = intangibleAssets;
	}
	@Column(name="Accumulated_Amortization")
	public String getAccumulatedAmortization() {
		return accumulatedAmortization;
	}
	public void setAccumulatedAmortization(String accumulatedAmortization) {
		this.accumulatedAmortization = accumulatedAmortization;
	}
	@Column(name="Other_Assets")
	public String getOtherAssets() {
		return otherAssets;
	}
	public void setOtherAssets(String otherAssets) {
		this.otherAssets = otherAssets;
	}
	@Column(name="Deferred_Long_Term_Asset_Charges")
	public String getDeferredLongTermAssetCharges() {
		return deferredLongTermAssetCharges;
	}
	public void setDeferredLongTermAssetCharges(String deferredLongTermAssetCharges) {
		this.deferredLongTermAssetCharges = deferredLongTermAssetCharges;
	}
	@Column(name="Total_Assets")
	public String getTotalAssets() {
		return totalAssets;
	}
	public void setTotalAssets(String totalAssets) {
		this.totalAssets = totalAssets;
	}
	@Column(name="Accounts_Payable")
	public String getAccountsPayable() {
		return accountsPayable;
	}
	public void setAccountsPayable(String accountsPayable) {
		this.accountsPayable = accountsPayable;
	}
	@Column(name="Short_Current_Long_Term_Debt")
	public String getShortLongTermDebt() {
		return shortLongTermDebt;
	}
	public void setShortLongTermDebt(String shortLongTermDebt) {
		this.shortLongTermDebt = shortLongTermDebt;
	}
	@Column(name="Other_Current_Liabilities")
	public String getOtherCurrentLiab() {
		return otherCurrentLiab;
	}
	public void setOtherCurrentLiab(String otherCurrentLiab) {
		this.otherCurrentLiab = otherCurrentLiab;
	}
	@Column(name="Total_Current_Liabilities")
	public String getTotalCurrentLiabilities() {
		return totalCurrentLiabilities;
	}
	public void setTotalCurrentLiabilities(String totalCurrentLiabilities) {
		this.totalCurrentLiabilities = totalCurrentLiabilities;
	}
	@Column(name="Long_Term_Debt")
	public String getLongTermDebt() {
		return longTermDebt;
	}
	public void setLongTermDebt(String longTermDebt) {
		this.longTermDebt = longTermDebt;
	}
	@Column(name="Other_Liabilities")
	public String getOtherLiab() {
		return otherLiab;
	}
	public void setOtherLiab(String otherLiab) {
		this.otherLiab = otherLiab;
	}
	@Column(name="Deferred_Long_Term_Liability_Charges")
	public String getDeferredLongTermLiab() {
		return deferredLongTermLiab;
	}
	public void setDeferredLongTermLiab(String deferredLongTermLiab) {
		this.deferredLongTermLiab = deferredLongTermLiab;
	}
	@Column(name="Minority_Interest")
	public String getMinorityInterest() {
		return minorityInterest;
	}
	public void setMinorityInterest(String minorityInterest) {
		this.minorityInterest = minorityInterest;
	}
	@Column(name="Negative_Goodwill")
	public String getNegativeGoodwill() {
		return negativeGoodwill;
	}
	public void setNegativeGoodwill(String negativeGoodwill) {
		this.negativeGoodwill = negativeGoodwill;
	}
	@Column(name="Total_Liabilities")
	public String getTotalLiab() {
		return totalLiab;
	}
	public void setTotalLiab(String totalLiab) {
		this.totalLiab = totalLiab;
	}
	@Column(name="Misc_Stocks_Options_Warrants")
	public String getMiscStocksOptionsWarrants() {
		return miscStocksOptionsWarrants;
	}
	public void setMiscStocksOptionsWarrants(String miscStocksOptionsWarrants) {
		this.miscStocksOptionsWarrants = miscStocksOptionsWarrants;
	}
	@Column(name="Redeemable_Preferred_Stock")
	public String getRedeemablePreferredStock() {
		return redeemablePreferredStock;
	}
	public void setRedeemablePreferredStock(String redeemablePreferredStock) {
		this.redeemablePreferredStock = redeemablePreferredStock;
	}
	@Column(name="Preferred_Stock")
	public String getPreferedStock() {
		return preferedStock;
	}
	public void setPreferedStock(String preferedStock) {
		this.preferedStock = preferedStock;
	}
	@Column(name="Common_Stock")
	public String getCommonStock() {
		return commonStock;
	}
	public void setCommonStock(String commonStock) {
		this.commonStock = commonStock;
	}
	@Column(name="Retained_Earnings")
	public String getRetainedEarnings() {
		return retainedEarnings;
	}
	public void setRetainedEarnings(String retainedEarnings) {
		this.retainedEarnings = retainedEarnings;
	}
	@Column(name="Treasury_Stock")
	public String getTreasuryStock() {
		return treasuryStock;
	}
	public void setTreasuryStock(String treasuryStock) {
		this.treasuryStock = treasuryStock;
	}
	@Column(name="Capital_Surplus")
	public String getCapitalSurplus() {
		return capitalSurplus;
	}
	public void setCapitalSurplus(String capitalSurplus) {
		this.capitalSurplus = capitalSurplus;
	}
	@Column(name="Other_Stockholder_Equity")
	public String getOtherStockholderEquity() {
		return otherStockholderEquity;
	}
	public void setOtherStockholderEquity(String otherStockholderEquity) {
		this.otherStockholderEquity = otherStockholderEquity;
	}
	@Column(name="Total_Stockholder_Equity")
	public String getTotalStockholderEquity() {
		return totalStockholderEquity;
	}
	public void setTotalStockholderEquity(String totalStockholderEquity) {
		this.totalStockholderEquity = totalStockholderEquity;
	}
	@Column(name="Net_Tangible_Assets")
	public String getNetTangibleAssets() {
		return netTangibleAssets;
	}
	public void setNetTangibleAssets(String netTangibleAssets) {
		this.netTangibleAssets = netTangibleAssets;
	}
	
}

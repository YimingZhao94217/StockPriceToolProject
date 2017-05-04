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
@Table(name = "Stock_Price")
public class StockPrice {
	private int recordId;
	private Date timeStamp;
	private Stock stock;
	private float price;
	
	@Id
	@Column(name = "record_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	@Column(name="Timestamp")
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "Symbol")
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	@Column(name="Price")
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}

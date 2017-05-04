package com.financial.pojo;

public class TableCell {
	private String value;
	private String type;
	private int duration; //cells = days * 3
	public enum TypeEnum{
		basic, date, basicLeft, basicRight, basicTop, stock;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	};

}

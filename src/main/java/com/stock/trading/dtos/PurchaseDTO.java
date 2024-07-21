package com.stock.trading.dtos;

public class PurchaseDTO {

	private int stockid;
	private float amount;
	private int qty;
	private String userid;
	private int saleorderid;
	
	public int getStockid() {
		return stockid;
	}
	public void setStockid(int stockid) {
		this.stockid = stockid;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getSaleorderid() {
		return saleorderid;
	}
	public void setSaleorderid(int saleorderid) {
		this.saleorderid = saleorderid;
	}

	
}

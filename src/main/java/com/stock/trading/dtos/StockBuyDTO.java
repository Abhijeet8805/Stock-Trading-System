package com.stock.trading.dtos;

public class StockBuyDTO {

	private String userid;
	private int stockid;
	private int amount;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getStockid() {
		return stockid;
	}
	public void setStockid(int stockid) {
		this.stockid = stockid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "StockBuyDTO [userid=" + userid + ", stockid=" + stockid + ", amount=" + amount + "]";
	}
	
	
}

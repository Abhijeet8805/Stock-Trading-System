package com.stock.trading.dtos;

public class SaleOrderDTO {
	private int userstockid;
	private String lastdate;
	private int desiredprice;
	private String userid;
	private int qty;
	
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
	public int getUserstockid() {
		return userstockid;
	}
	public void setUserstockid(int userstockid) {
		this.userstockid = userstockid;
	}
	public String getLastdate() {
		return lastdate;
	}
	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}
	public int getDesiredprice() {
		return desiredprice;
	}
	public void setDesiredprice(int desiredprice) {
		this.desiredprice = desiredprice;
	}
	
}

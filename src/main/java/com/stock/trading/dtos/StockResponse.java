package com.stock.trading.dtos;

public interface StockResponse {
	double getFirst_price();
	int getId();
	String getCompany();
	double getPrice();
	int getVolume();
	//String getLogo();
	double getDelta();
}

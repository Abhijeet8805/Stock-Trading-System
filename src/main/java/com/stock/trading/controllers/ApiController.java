package com.stock.trading.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.stock.trading.models.StockPrice;
import com.stock.trading.services.StockService;

@RestController
public class ApiController {
	
	@Autowired StockService sservice;

	@GetMapping("/api/data/{id}")
	public List<StockPrice> chartData(@PathVariable("id")int id) {
		List<StockPrice> plist= sservice.getPriceDetails(id);
		return plist;
	}
}

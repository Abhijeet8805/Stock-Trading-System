package com.stock.trading.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.trading.models.MarketSettings;
import com.stock.trading.repository.MarketSettingsRepository;

@Service
public class MarketSettingService {

	@Autowired private MarketSettingsRepository repo;
	
	public void save(MarketSettings ms) {
		repo.save(ms);
	}
	
	public void update(MarketSettings ms) {
		ms.setId(1);
		repo.save(ms);
	}
	
	public MarketSettings getSettings() {
		return repo.getById(1);
	}
	
	public long count() {
		return repo.count();
	}
}

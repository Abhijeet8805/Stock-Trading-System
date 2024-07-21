package com.stock.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.trading.models.MarketSettings;

@Repository
public interface MarketSettingsRepository extends JpaRepository<MarketSettings, Integer> {

}

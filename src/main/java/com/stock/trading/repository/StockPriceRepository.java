package com.stock.trading.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stock.trading.models.Stock;
import com.stock.trading.models.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Integer> {

	@Query(value = "SELECT * FROM stock_price where date(createdon)>=current_date and stock_id=:id order by id",nativeQuery = true)
	List<StockPrice> findByStock(int id);
	
	@Query(value = "SELECT min(price) FROM StockPrice where stock_id=:id")
	public Long min(int id);

	@Query(value = "SELECT max(price) FROM StockPrice where stock_id=:id")
	public Long max(int id);
	
}

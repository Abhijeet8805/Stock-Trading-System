package com.stock.trading.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stock.trading.dtos.StockResponse;
import com.stock.trading.models.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
	
	@Query(value = "select s.*,sp.price as first_price from stock s join stock_price sp on sp.id=(select id from stock_price where stock_id=s.id limit 1)",nativeQuery = true)
	List<StockResponse> allRecords();
	
	@Query(value = "select s.*,sp.price as first_price from stock s join stock_price sp on sp.id=(select id from stock_price where stock_id=s.id limit 1) where s.id=:id",nativeQuery = true)
	StockResponse findStockById(int id);
}

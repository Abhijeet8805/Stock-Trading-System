package com.stock.trading.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.trading.models.Stock;
import com.stock.trading.models.User;
import com.stock.trading.models.UserStock;

@Repository
public interface UserStockRepository extends JpaRepository<UserStock, Integer> {

	List<UserStock> findByUser(User user);
	UserStock findByUserAndStock(User user,Stock stk);
}

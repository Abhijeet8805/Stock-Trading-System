package com.stock.trading.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.trading.models.SaleOrder;
import com.stock.trading.models.User;

@Repository
public interface SaleOrderRepository extends JpaRepository<SaleOrder, Integer> {

	List<SaleOrder> findByUser(User user);
}

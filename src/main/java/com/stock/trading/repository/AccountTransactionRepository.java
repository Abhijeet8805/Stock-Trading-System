package com.stock.trading.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.trading.models.AccountTransaction;
import com.stock.trading.models.User;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Integer> {

	List<AccountTransaction> findByUser(User user);
}

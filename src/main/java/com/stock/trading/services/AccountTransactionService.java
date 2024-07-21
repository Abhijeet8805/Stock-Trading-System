package com.stock.trading.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.trading.models.AccountTransaction;
import com.stock.trading.models.User;
import com.stock.trading.repository.AccountTransactionRepository;
import com.stock.trading.repository.UserRepository;

@Service
public class AccountTransactionService {

	@Autowired AccountTransactionRepository repo;
	@Autowired UserService usrv;
	
	public void saveTran(AccountTransaction at,String userid) {
		at.setUser(usrv.findById(userid));
		repo.save(at);
	}
	
	public List<AccountTransaction> userTrans(String userid){
		User user=usrv.findById(userid);
		return repo.findByUser(user);
	}
}

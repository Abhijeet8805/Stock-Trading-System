package com.stock.trading.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.trading.models.User;
import com.stock.trading.repository.UserRepository;

@Service
public class UserService {

	@Autowired private UserRepository repo;
	
	public void saveUser(User user) {
		repo.saveAndFlush(user);
	}
	
	public User findById(String userid) {
		return repo.getById(userid);
	}
	
	public void updateWallet(int balance,String userid) {
		User user=findById(userid);
		user.setBalance(user.getBalance()+balance);
		repo.save(user);
	}
	
	public List<User> listAll(){
		return repo.findAll();
	}
	
	public long countUser() {
		return repo.count();
	}
	
	public User validate(String userid,String pwd) {
		Optional<User> user=repo.findById(userid);
		if(user.isPresent()) {
			if(user.get().getPwd().equals(pwd)) {
				return user.get();
			}else
				return null;
		}
		else
			return null;
	}
}

package com.stock.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.trading.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}

package com.stock.trading.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
public class User {
	@Id
	private String userid;
	private String username;
	private String pwd;
	private String role;
	private float balance;
	private LocalDateTime createdon;
	public User() {
		// TODO Auto-generated constructor stub
		this.createdon=LocalDateTime.now();
	}
	
	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public LocalDateTime getCreatedon() {
		return createdon;
	}
	public void setCreatedon(LocalDateTime createdon) {
		this.createdon = createdon;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", pwd=" + pwd + ", userid=" + userid + ", role=" + role + ", createdon="
				+ createdon + "]";
	}
	
	
	
}

package com.stock.trading.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SaleOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate lastdate;
	private String status;
	private LocalDateTime createdon;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name="userstock_id")
	private UserStock stock;
	private int desiredprice;
	@Column(nullable = true)
	private int qty;
	
	public SaleOrder() {
		this.createdon=LocalDateTime.now();
		this.status="Open";
		this.qty=1;
	}

	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public UserStock getStock() {
		return stock;
	}

	public void setStock(UserStock stock) {
		this.stock = stock;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getLastdate() {
		return lastdate;
	}
	public void setLastdate(LocalDate lastdate) {
		this.lastdate = lastdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getCreatedon() {
		return createdon;
	}
	public void setCreatedon(LocalDateTime createdon) {
		this.createdon = createdon;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public int getDesiredprice() {
		return desiredprice;
	}
	public void setDesiredprice(int desiredprice) {
		this.desiredprice = desiredprice;
	}
	
}

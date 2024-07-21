package com.stock.trading.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AccountTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	private String description;
	private LocalDateTime trandate;
	private float debit;
	private float credit;
	public AccountTransaction() {
		this.trandate=LocalDateTime.now();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getTrandate() {
		return trandate;
	}
	public void setTrandate(LocalDateTime trandate) {
		this.trandate = trandate;
	}
	public float getDebit() {
		return debit;
	}
	public void setDebit(float debit) {
		this.debit = debit;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	@Override
	public String toString() {
		return "AccountTransaction [id=" + id + ", user=" + user + ", description=" + description + ", trandate="
				+ trandate + ", debit=" + debit + ", credit=" + credit + "]";
	}
	
	
}

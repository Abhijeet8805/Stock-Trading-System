package com.stock.trading.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
public class MarketSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonFormat(pattern = "YYYY-MM-dd",shape = Shape.STRING)
	private LocalDate closedate;
	private String reason;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getClosedate() {
		return closedate;
	}
	public void setClosedate(LocalDate closedate) {
		this.closedate = closedate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "MarketSchedule [id=" + id + ", closedate=" + closedate + ", reason=" + reason + "]";
	}
	
	
}

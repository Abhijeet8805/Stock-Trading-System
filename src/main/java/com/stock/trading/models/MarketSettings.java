package com.stock.trading.models;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
public class MarketSettings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonFormat(pattern = "HH:mm",shape = Shape.STRING)
	private LocalTime starttime;
	@JsonFormat(pattern = "HH:mm",shape = Shape.STRING)
	private LocalTime endtime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalTime getStarttime() {
		return starttime;
	}
	public void setStarttime(LocalTime starttime) {
		this.starttime = starttime;
	}
	public LocalTime getEndtime() {
		return endtime;
	}
	public void setEndtime(LocalTime endtime) {
		this.endtime = endtime;
	}
	@Override
	public String toString() {
		return "MarketSettings [id=" + id + ", starttime=" + starttime + ", endtime=" + endtime + "]";
	}
	
	
}

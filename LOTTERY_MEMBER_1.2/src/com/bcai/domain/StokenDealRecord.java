package com.bcai.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class StokenDealRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dealTime;
	
	private double price;
	
	private double dealNum;
	
	private long stokenBuyId;
	
	private long stokenSellId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDealNum() {
		return dealNum;
	}

	public void setDealNum(double dealNum) {
		this.dealNum = dealNum;
	}

	public long getStokenBuyId() {
		return stokenBuyId;
	}

	public void setStokenBuyId(long stokenBuyId) {
		this.stokenBuyId = stokenBuyId;
	}

	public long getStokenSellId() {
		return stokenSellId;
	}

	public void setStokenSellId(long stokenSellId) {
		this.stokenSellId = stokenSellId;
	}
	
	
}

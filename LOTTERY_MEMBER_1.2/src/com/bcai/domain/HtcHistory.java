package com.bcai.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class HtcHistory implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	/**
	 * 成交总量
	 */  
	private int amount;
	
	/**
	 * 成交总值
	 */
	@Column(columnDefinition="double default 0.00")
	private double grossValue;
	
	/**
	 * 成交时间	
	 */
	private String closTime;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=true)
    private Date dealTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}


	public double getGrossValue() {
		return grossValue;
	}

	public void setGrossValue(double grossValue) {
		this.grossValue = grossValue;
	}

	public String getClosTime() {
		return closTime;
	}

	public void setClosTime(String closTime) {
		this.closTime = closTime;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	
	
    
	
}

package com.bcai.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class DebrisRecord {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	@Column(nullable=false,length=15)
	private String mbName;
	
	/**
	 * 发生的碎片金额
	 */
	private double amoney;
	
	/**
	 * 发生后碎片余额
	 */
	private double balance;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date occDate;
	
	/**
	 * 1、合成矿机
	 * 2、分解矿机
	 * 3、社区配送
	 * 4、卖出碎片
	 * 5、买入碎片
	 * 6、撤销卖出碎片
	 */
	private int type;
	
	private String description;
	 
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMbName() {
		return mbName;
	}

	public void setMbName(String mbName) {
		this.mbName = mbName;
	}

	public double getAmoney() {
		return amoney;
	}

	public void setAmoney(double amoney) {
		this.amoney = amoney;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getOccDate() {
		return occDate;
	}

	public void setOccDate(Date occDate) {
		this.occDate = occDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	
	
}

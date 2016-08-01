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
public class OrderList {
	
    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	@Column(length=15)
	private String mbName;
	
	private String orderNumber;
	
	private String odsDate;
	
	/**
	 * 充值状态
	 * 0--表示产生积分
	 * 1--表示消费积分
	 */
	private int state;
	
	private Double reMoney;
	
	private String extractSpare;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date atime;


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


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public Date getAtime() {
		return atime;
	}


	public void setAtime(Date atime) {
		this.atime = atime;
	}


	public Double getReMoney() {
		return reMoney;
	}


	public void setReMoney(Double reMoney) {
		this.reMoney = reMoney;
	}


	public String getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}


	public String getExtractSpare() {
		return extractSpare;
	}


	public void setExtractSpare(String extractSpare) {
		this.extractSpare = extractSpare;
	}


	public String getOdsDate() {
		return odsDate;
	}


	public void setOdsDate(String odsDate) {
		this.odsDate = odsDate;
	}
	
	
	
}

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
public class Prize implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	@Column(nullable=false,length=200)
	private String prName;
	
	@Column(nullable=false,length=20)
	private String prId;
	
	@Column(nullable=false)
	private double peepCost;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startPrTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endPrTime;
	
	@Column(nullable=false)
	private double totalPeep;
	
	@Column(nullable=false)
	private double remainPeep;
	
    private String tokenRemark;
	
	private String tokenSpare;

	public long getId() {
		return id;
	}
	
	

	public String getPrId() {
		return prId;
	}



	public void setPrId(String prId) {
		this.prId = prId;
	}



	public Date getStartPrTime() {
		return startPrTime;
	}



	public void setStartPrTime(Date startPrTime) {
		this.startPrTime = startPrTime;
	}



	public Date getEndPrTime() {
		return endPrTime;
	}



	public void setEndPrTime(Date endPrTime) {
		this.endPrTime = endPrTime;
	}



	public void setId(long id) {
		this.id = id;
	}

	public String getPrName() {
		return prName;
	}

	public void setPrName(String prName) {
		this.prName = prName;
	}

	public double getPeepCost() {
		return peepCost;
	}

	public void setPeepCost(double peepCost) {
		this.peepCost = peepCost;
	}

	public double getTotalPeep() {
		return totalPeep;
	}

	public void setTotalPeep(double totalPeep) {
		this.totalPeep = totalPeep;
	}

	public double getRemainPeep() {
		return remainPeep;
	}

	public void setRemainPeep(double remainPeep) {
		this.remainPeep = remainPeep;
	}

	public String getTokenRemark() {
		return tokenRemark;
	}

	public void setTokenRemark(String tokenRemark) {
		this.tokenRemark = tokenRemark;
	}

	public String getTokenSpare() {
		return tokenSpare;
	}

	public void setTokenSpare(String tokenSpare) {
		this.tokenSpare = tokenSpare;
	}
	
	
	
	
}

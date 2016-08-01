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
public class PeepList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	/**
	 * 用户名
	 */
	@Column(nullable=false,length=15)
	private String mbName;	
	
	@Column(nullable=false,length=20)
	private String prId;
	
	@Column(nullable=false,length=200)
	private String prName;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date prTime;
	
	@Column(nullable=false)
	private double remainPeep;
	
    private String tokenRemark;
	
	private String tokenSpare;

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

	public String getPrId() {
		return prId;
	}

	public void setPrId(String prId) {
		this.prId = prId;
	}

	public Date getPrTime() {
		return prTime;
	}

	public void setPrTime(Date prTime) {
		this.prTime = prTime;
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

	public String getPrName() {
		return prName;
	}

	public void setPrName(String prName) {
		this.prName = prName;
	}
	
	
	
	
}

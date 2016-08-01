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
public class MemberStoken {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;	
	/**
	 * 用户名
	 */
	@Column(nullable=false,length=15,unique=true)
	private String mbName;	
	/**
	 * 碎片余额
	 */
	private double tokenRemain;	
	
	/**
	 * 交易碎余额
	 */
	private double traDebrisRemain;	
	
	/**
	 * 矿机余额
	 */
	private int mill;	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tokenLastTime;
	
	private int isMin; 
	
	private String tokenRemark;
	
	private String tokenSpare;
	
	private String tokenRemarkab;
	
	

	public String getTokenRemarkab() {
		return tokenRemarkab;
	}

	public void setTokenRemarkab(String tokenRemarkab) {
		this.tokenRemarkab = tokenRemarkab;
	}

	public int getIsMin() {
		return isMin;
	}

	public void setIsMin(int isMin) {
		this.isMin = isMin;
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

	public double getTokenRemain() {
		return tokenRemain;
	}

	public void setTokenRemain(double tokenRemain) {
		this.tokenRemain = tokenRemain;
	}

	public int getMill() {
		return mill;
	}

	public void setMill(int mill) {
		this.mill = mill;
	}

	public Date getTokenLastTime() {
		return tokenLastTime;
	}

	public void setTokenLastTime(Date tokenLastTime) {
		this.tokenLastTime = tokenLastTime;
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

	public double getTraDebrisRemain() {
		return traDebrisRemain;
	}

	public void setTraDebrisRemain(double traDebrisRemain) {
		this.traDebrisRemain = traDebrisRemain;
	}
    
	
}

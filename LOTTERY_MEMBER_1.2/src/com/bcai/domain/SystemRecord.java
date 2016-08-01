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
public class SystemRecord {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	/**
	 * 用户名
	 */
	@Column(nullable=false,length=15)
	private String mbName;
	
	/**
	 * 币种
	 */
	private String currency;
	
	private double wallet;
	
	private String type;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date atime;
	
    private String SystemRecordRemark;
	
	private String SystemRecordSpare;

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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getAtime() {
		return atime;
	}

	public void setAtime(Date atime) {
		this.atime = atime;
	}

	public String getSystemRecordRemark() {
		return SystemRecordRemark;
	}

	public void setSystemRecordRemark(String systemRecordRemark) {
		SystemRecordRemark = systemRecordRemark;
	}

	public String getSystemRecordSpare() {
		return SystemRecordSpare;
	}

	public void setSystemRecordSpare(String systemRecordSpare) {
		SystemRecordSpare = systemRecordSpare;
	}
	
	
	
}

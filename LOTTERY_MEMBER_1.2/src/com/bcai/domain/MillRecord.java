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
public class MillRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	/**
	 * 用户名
	 */
	@Column(nullable=false,length=15)
	private String mbName;	
	
	
	private String modelNum;
	
	
	private int holdDay;
	
	private String isUse;
	
	private String isCount;
	
	private Double buyPrice;
	
	
	public String getIsCount() {
		return isCount;
	}

	public void setIsCount(String isCount) {
		this.isCount = isCount;
	}

	/**
	 * 合成时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date synthesisDate;	
	

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
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

	public String getModelNum() {
		return modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}

	public Date getSynthesisDate() {
		return synthesisDate;
	}

	public void setSynthesisDate(Date synthesisDate) {
		this.synthesisDate = synthesisDate;
	}

	public int getHoldDay() {
		return holdDay;
	}

	public void setHoldDay(int holdDay) {
		this.holdDay = holdDay;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	
	
	
	
	
}

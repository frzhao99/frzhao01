package com.bcai.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Value;

/**
 * 
 * @author Frzhao
 *
 */
@Entity
public class StokenSell {
	
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
	 * 委托总量
	 */
	private Double tokenSellCount;
	
	/**
	 * 成交价格
	 */
	private double tokenSellPrice;
	
	
	
	/**
	 * 已经成交量
	 */
	@Value("0")
	private Double selledCount;
	
	@Transient
	private Double dealPercent;
	
	/**
	 * 剩余成交量
	 */
	@Value("0")
	private Double surplusSellCount;
	
	/**
	 * 销售时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date tokenSellTime;
	
	/**
	 * 成交时间
	 */
	private Date tokenDealTime;
	
	/**
	 * 状态
	 */
	private int state;
	
	private String tokenSellRemark;
	
	private String tokenSellSpare;

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

	public Double getTokenSellCount() {
		return tokenSellCount;
	}

	public void setTokenSellCount(Double tokenSellCount) {
		this.tokenSellCount = tokenSellCount;
	}

	public double getTokenSellPrice() {
		return tokenSellPrice;
	}

	public void setTokenSellPrice(double tokenSellPrice) {
		this.tokenSellPrice = tokenSellPrice;
	}

	public Double getSelledCount() {
		return selledCount;
	}

	public void setSelledCount(Double selledCount) {
		this.selledCount = selledCount;
	}

	public Double getSurplusSellCount() {
		return surplusSellCount;
	}

	public void setSurplusSellCount(Double surplusSellCount) {
		this.surplusSellCount = surplusSellCount;
	}

	public Date getTokenSellTime() {
		return tokenSellTime;
	}

	public void setTokenSellTime(Date tokenSellTime) {
		this.tokenSellTime = tokenSellTime;
	}

	public Date getTokenDealTime() {
		return tokenDealTime;
	}

	public void setTokenDealTime(Date tokenDealTime) {
		this.tokenDealTime = tokenDealTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTokenSellRemark() {
		return tokenSellRemark;
	}

	public void setTokenSellRemark(String tokenSellRemark) {
		this.tokenSellRemark = tokenSellRemark;
	}

	public String getTokenSellSpare() {
		return tokenSellSpare;
	}

	public void setTokenSellSpare(String tokenSellSpare) {
		this.tokenSellSpare = tokenSellSpare;
	}

	public Double getDealPercent() {
		return (this.selledCount/tokenSellCount)*100;
	}

	

	
	
}

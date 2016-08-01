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
 * 成交记录
 * @author think
 *
 */
@Entity
public class StokenBuy {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	/**
	 * 用户名
	 */
	@Column(nullable=false,length=15 )
	private String mbName;	
	
	/**
	 * 委托数量
	 */
	private Double tokenBuyCount;
	
	/**
	 * 购买价格
	 */
	private double tokenBuyPrice;		
	
	/**
	 * 已成交数量
	 */
	private Double dealCount;	
	
	/**
	 * 成交金额
	 */
	private Double dealAmount;
	
	/**
	 * 是否核实
	 */
	private String isCheck;
	
	private int state;	
	
	@Value("0")
	private int buyType;
	
	@Value("0")
	private String coinType;
	
    public String getCoinType() {
		return coinType;
	}

	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}

	public int getBuyType() {
		return buyType;
	}

	public void setBuyType(int buyType) {
		this.buyType = buyType;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public Double getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(Double dealAmount) {
		this.dealAmount = dealAmount;
	}

	/**
	 * 购买时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date tokenBuyTime;
	
	@Transient
	private Double allBuyCount;
	
	@Transient
	private double dealPercent;
	
	private String tokenBuyRemark;
	
	private String tokenBuySpare;

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

	public Double getTokenBuyCount() {
		return tokenBuyCount;
	}

	public void setTokenBuyCount(Double tokenBuyCount) {
		this.tokenBuyCount = tokenBuyCount;
	}

	public double getTokenBuyPrice() {
		return tokenBuyPrice;
	}

	public void setTokenBuyPrice(double tokenBuyPrice) {
		this.tokenBuyPrice = tokenBuyPrice;
	}

	public Double getDealCount() {
		return dealCount;
	}

	public void setDealCount(Double dealCount) {
		this.dealCount = dealCount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getTokenBuyTime() {
		return tokenBuyTime;
	}

	public void setTokenBuyTime(Date tokenBuyTime) {
		this.tokenBuyTime = tokenBuyTime;
	}

	public Double getAllBuyCount() {
		return allBuyCount;
	}

	public void setAllBuyCount(Double allBuyCount) {
		this.allBuyCount = allBuyCount;
	}

	public String getTokenBuyRemark() {
		return tokenBuyRemark;
	}

	public void setTokenBuyRemark(String tokenBuyRemark) {
		this.tokenBuyRemark = tokenBuyRemark;
	}

	public String getTokenBuySpare() {
		return tokenBuySpare;
	}

	public void setTokenBuySpare(String tokenBuySpare) {
		this.tokenBuySpare = tokenBuySpare;
	}

	public double getDealPercent() {
		return (dealCount/tokenBuyCount)*100;
	}
	
}

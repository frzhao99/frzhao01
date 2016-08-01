package com.bcai.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HtcAmount implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	/**
	 * 总量
	 */
    private int totalAmount;	
	
    /**
     * 已经购买
     */
	private int buyAmount;	
	
	/**
	 * 剩余购买
	 */
	private int surplusAmount;
	
	/**
	 * 当前价格
	 */
	private double price;
	
	/**
	 * 开盘价格
	 */
	private double openPrice;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getBuyAmount() {
		return buyAmount;
	}

	public void setBuyAmount(int buyAmount) {
		this.buyAmount = buyAmount;
	}

	public int getSurplusAmount() {
		return surplusAmount;
	}

	public void setSurplusAmount(int surplusAmount) {
		this.surplusAmount = surplusAmount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}
	
	
}

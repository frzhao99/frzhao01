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
public class StokenSplit {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	/**
	 * 用户名
	 */
	@Column(nullable=false,length=15)
	private String mbName;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date stokenSplitTime;
	
	/**
	 * 拆分倍数
	 */
	private int stokenSplitMultiple;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date buyDate;
	
	/**
	 * 拆分前代币数量
	 */
	private int stokenSplitPriStokencount;
	
	/**
	 * 拆分后 代币数量
	 */
	private int stokenSplitAfterStokencount;
	
	private String tokenRransRemark;
	
	private String tokenTransSpare;

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

	public Date getStokenSplitTime() {
		return stokenSplitTime;
	}

	public void setStokenSplitTime(Date stokenSplitTime) {
		this.stokenSplitTime = stokenSplitTime;
	}

	public int getStokenSplitMultiple() {
		return stokenSplitMultiple;
	}

	public void setStokenSplitMultiple(int stokenSplitMultiple) {
		this.stokenSplitMultiple = stokenSplitMultiple;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public int getStokenSplitPriStokencount() {
		return stokenSplitPriStokencount;
	}

	public void setStokenSplitPriStokencount(int stokenSplitPriStokencount) {
		this.stokenSplitPriStokencount = stokenSplitPriStokencount;
	}

	public int getStokenSplitAfterStokencount() {
		return stokenSplitAfterStokencount;
	}

	public void setStokenSplitAfterStokencount(int stokenSplitAfterStokencount) {
		this.stokenSplitAfterStokencount = stokenSplitAfterStokencount;
	}

	public String getTokenRransRemark() {
		return tokenRransRemark;
	}

	public void setTokenRransRemark(String tokenRransRemark) {
		this.tokenRransRemark = tokenRransRemark;
	}

	public String getTokenTransSpare() {
		return tokenTransSpare;
	}

	public void setTokenTransSpare(String tokenTransSpare) {
		this.tokenTransSpare = tokenTransSpare;
	}
	
	
	
	
	
}

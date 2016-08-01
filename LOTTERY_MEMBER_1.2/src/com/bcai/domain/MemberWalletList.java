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
public class MemberWalletList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	
	private String mbName;
	
	@Column(columnDefinition="double default 0.00")
	private double amoney;
	
	@Column(columnDefinition="double default 0.00")
	private double balance;	
	
	/**
	 * 充值类型
	 * 0--奖金
	 * 1--购买BLKc
	 * 2--转出
	 * 3--转入
	 * 4--提现
	 * 5--激活
	 * 6--撤销碎片买单
	 * 7--撤销BLkc卖单
	 * 8--矿机生产
	 * 9--卖出碎片
	 * 10-返回购买碎片多余BM币
	 * 11-参与游戏竞拍
	 */
	
	@Column(name="type_")
	private int type;
	
	/**
	 *  0 BHc 布尔什维克币
	 *  1 GC币
	 *  2  矿机币
	 */
	private int currencyType;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	private String description;
	
	private String remarks;
    

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMbName() {
		return mbName;
	}

	public void setMbName(String mbName) {
		this.mbName = mbName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getAmoney() {
		return amoney;
	}

	public void setAmoney(double amoney) {
		this.amoney = amoney;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(int currencyType) {
		this.currencyType = currencyType;
	}

    
	
	
}

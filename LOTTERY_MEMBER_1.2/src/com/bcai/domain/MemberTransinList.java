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

/**
 * 会员转入明细表
 * @author Fred
 *
 */
@Entity
public class MemberTransinList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	/**
	 * 用户名
	 */
	@Column(length = 16, nullable = false)
	private String userName;
	
	/**
	 * 收入金额
	 */
	@Column(nullable=false)
	private double transinAmount;
	

	/**
	 * 转账用户名
	 */
	@Column(length = 16, nullable = false)
	private String transUsername;
	
	/**
	 * 转账时间
	 */
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date transTime;
	
	/**
	 * 转账类型，0表示转入，1表示转出
	 */
	@Column( nullable=false)
	private int  transType;
	
	/**
	 * 转账后电子钱包余额
	 */
	@Column(nullable=false)
	private double walletRemain;
	
	private String transRemark;
	
	private String transSpare;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	

	public double getTransinAmount() {
		return transinAmount;
	}

	public void setTransinAmount(double transinAmount) {
		this.transinAmount = transinAmount;
	}

   

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTransUsername() {
		return transUsername;
	}

	public void setTransUsername(String transUsername) {
		this.transUsername = transUsername;
	}

	public Date getTransTime() {
		return transTime;
	}

	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}

	public double getWalletRemain() {
		return walletRemain;
	}

	public void setWalletRemain(double walletRemain) {
		this.walletRemain = walletRemain;
	}

	public String getTransRemark() {
		return transRemark;
	}

	public void setTransRemark(String transRemark) {
		this.transRemark = transRemark;
	}

	public String getTransSpare() {
		return transSpare;
	}

	public void setTransSpare(String transSpare) {
		this.transSpare = transSpare;
	}

	public int getTransType() {
		return transType;
	}

	public void setTransType(int transType) {
		this.transType = transType;
	}
	
	
}

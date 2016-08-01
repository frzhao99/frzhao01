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
 * 会员明细表
 * @author Fred
 *
 */
@Entity
public class MemberBonusList implements Serializable {

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
	private String mbName;

	/**
	 * 奖金产生金额
	 */
	@Column(columnDefinition="double default 0.00")
	private double bonusBringMoney;
	
	
	@Column(nullable=false)
	private int bonusSort;
	


	/**
	 * 奖金产生时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date bonusBringTime;
	
	
	
	/**
	 * 奖金产生后EP钱包余额
	 */
	@Column(nullable=false)
	private double epRemain;
	
	@Column(columnDefinition="double default 0.00")
	private double reRemain;
	
	private String bonusRemark;
	
	private String bonusSpare;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public double getBonusBringMoney() {
		return bonusBringMoney;
	}

	public void setBonusBringMoney(double bonusBringMoney) {
		this.bonusBringMoney = bonusBringMoney;
	}
	
	public int getBonusSort() {
		return bonusSort;
	}

	public void setBonusSort(int bonusSort) {
		this.bonusSort = bonusSort;
	}

	public Date getBonusBringTime() {
		return bonusBringTime;
	}

	public void setBonusBringTime(Date bonusBringTime) {
		this.bonusBringTime = bonusBringTime;
	}

	
	public String getBonusRemark() {
		return bonusRemark;
	}

	public void setBonusRemark(String bonusRemark) {
		this.bonusRemark = bonusRemark;
	}

	public String getBonusSpare() {
		return bonusSpare;
	}

	public void setBonusSpare(String bonusSpare) {
		this.bonusSpare = bonusSpare;
	}

	public String getMbName() {
		return mbName;
	}

	public void setMbName(String mbName) {
		this.mbName = mbName;
	}

	public double getEpRemain() {
		return epRemain;
	}

	public void setEpRemain(double epRemain) {
		this.epRemain = epRemain;
	}

	public double getReRemain() {
		return reRemain;
	}

	public void setReRemain(double reRemain) {
		this.reRemain = reRemain;
	}
	
	
}

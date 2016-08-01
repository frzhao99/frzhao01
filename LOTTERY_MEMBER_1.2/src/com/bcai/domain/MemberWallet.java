package com.bcai.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Value;

@Entity
public class MemberWallet implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	@Column(nullable=false,length=15,unique=true)
	private String mbName;	
	
	/**
	 * BHC余额
	 */
	private double wallet;	
	
	/**
	 * 复利币余额
	 */
	
	@Value("0.0")
	private double rewallet;	
	
	/**
	 * 荣誉奖金
	 */
	private double diamond;
	
	/**
	 * 红宝石余额
	 */
	private double ruby;
	
	/**
	 * 蓝宝石余额
	 */
	private double sapphire;
	
	/**
	 * 汇特币数量
	 */
	private double htcAmount;
	
	
	private String remarks;
	
	private String remarksa;

    
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarksa() {
		return remarksa;
	}

	public void setRemarksa(String remarksa) {
		this.remarksa = remarksa;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}

	public String getMbName() {
		return mbName;
	}

	public void setMbName(String mbName) {
		this.mbName = mbName;
	}

	public double getRewallet() {
		return rewallet;
	}

	public void setRewallet(double rewallet) {
		this.rewallet = rewallet;
	}

	public double getDiamond() {
		return diamond;
	}

	public void setDiamond(double diamond) {
		this.diamond = diamond;
	}

	public double getRuby() {
		return ruby;
	}

	public void setRuby(double ruby) {
		this.ruby = ruby;
	}

	public double getSapphire() {
		return sapphire;
	}

	public void setSapphire(double sapphire) {
		this.sapphire = sapphire;
	}

	public double getHtcAmount() {
		return htcAmount;
	}

	public void setHtcAmount(double htcAmount) {
		this.htcAmount = htcAmount;
	}

	
	
	
}

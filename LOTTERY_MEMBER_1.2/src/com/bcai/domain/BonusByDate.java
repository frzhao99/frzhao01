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
public class BonusByDate {
	
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
	
	private String stbringDate;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date bringDate;
	
	private	double countBonus;
	
	private double fuliBonus;
	
	private double orgBonus;
	
	private double saleBonus;
	
	private double liderBonus;
	
	private double dividendBonus;

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

	public double getCountBonus() {
		return countBonus;
	}

	public void setCountBonus(double countBonus) {
		this.countBonus = countBonus;
	}

	public double getFuliBonus() {
		return fuliBonus;
	}

	public void setFuliBonus(double fuliBonus) {
		this.fuliBonus = fuliBonus;
	}

	public double getOrgBonus() {
		return orgBonus;
	}

	public void setOrgBonus(double orgBonus) {
		this.orgBonus = orgBonus;
	}

	public double getSaleBonus() {
		return saleBonus;
	}

	public void setSaleBonus(double saleBonus) {
		this.saleBonus = saleBonus;
	}

	public double getLiderBonus() {
		return liderBonus;
	}

	public void setLiderBonus(double liderBonus) {
		this.liderBonus = liderBonus;
	}

	public String getStbringDate() {
		return stbringDate;
	}

	public void setStbringDate(String stbringDate) {
		this.stbringDate = stbringDate;
	}

	public Date getBringDate() {
		return bringDate;
	}

	public void setBringDate(Date bringDate) {
		this.bringDate = bringDate;
	}

	public double getDividendBonus() {
		return dividendBonus;
	}

	public void setDividendBonus(double dividendBonus) {
		this.dividendBonus = dividendBonus;
	}
	
	
	
	
}

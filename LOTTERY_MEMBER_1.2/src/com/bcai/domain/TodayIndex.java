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
import javax.persistence.Transient;

import com.symbio.utils.DateUtils;

//当日指数
@Entity
public class TodayIndex implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition="double default 0.00")
	private double openingMoney;
	
	@Column(columnDefinition="double default 0.00")
	private double maxMoney;
	
	@Column(columnDefinition="double default 0.00")
	private double minMoney;
	
	@Column(columnDefinition="double default 0.00")
	private double colseMoney;
	
	@Column(columnDefinition="double default 0.00")
	private double delNum;
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Transient
	private String stime;
	
	

	public String getStime() {
		String ns = DateUtils.DateToStr(DateUtils.yyyysMMsDD, this.createTime);
		return ns;
	}

	public double getDelNum() {
		return delNum;
	}

	public void setDelNum(double delNum) {
		this.delNum = delNum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getOpeningMoney() {
		return openingMoney;
	}

	public void setOpeningMoney(double openingMoney) {
		this.openingMoney = openingMoney;
	}

	public double getMaxMoney() {
		return maxMoney;
	}

	public void setMaxMoney(double maxMoney) {
		this.maxMoney = maxMoney;
	}

	public double getMinMoney() {
		return minMoney;
	}

	public void setMinMoney(double minMoney) {
		this.minMoney = minMoney;
	}

	public double getColseMoney() {
		return colseMoney;
	}

	public void setColseMoney(double colseMoney) {
		this.colseMoney = colseMoney;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

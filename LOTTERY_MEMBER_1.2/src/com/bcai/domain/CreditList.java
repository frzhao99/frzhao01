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


//信用扣除列表
@Entity
public class CreditList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//用户名
	@Column(nullable = false, length = 15)
	private String mbName;
	
	//原因
	@Column(length = 512)
	private String reason;
	
	//增加或者扣除类型 1：增加 2：扣除
	private int type;
	
	//增加或者扣除数量
	private int count;
	
	//操作后个数
	private int laterCount;
	
	//扣除时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLaterCount() {
		return laterCount;
	}

	public void setLaterCount(int laterCount) {
		this.laterCount = laterCount;
	}
	
	
	
}

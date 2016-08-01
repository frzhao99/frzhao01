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
 * 会员激活明细表
 * @author Fred
 *
 */
@Entity
public class MemberActiveList implements Serializable {
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
	@Column(nullable = false, length = 15)
	private String mbName;	
	

	
	/**
	 * 激活用户名账户
	 */
	@Column(length=16,nullable=false)
	private String activeMbName;
	
	/**
	 * 激活用户名姓名
	 */
	@Column(length=32,nullable=false)
	private String activeName;
	
	/**
	 * 2－按揭
	 * 1－全款
	
	 */
	private int activeType;
	
	/**
	 * 激活时间
	 */
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date activeTime;
	
	
	private String activeRemark;
	
	private String activeSpare;

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

	public String getActiveMbName() {
		return activeMbName;
	}

	public void setActiveMbName(String activeMbName) {
		this.activeMbName = activeMbName;
	}

	public String getActiveName() {
		return activeName;
	}

	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}

	public int getActiveType() {
		return activeType;
	}

	public void setActiveType(int activeType) {
		this.activeType = activeType;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	public String getActiveRemark() {
		return activeRemark;
	}

	public void setActiveRemark(String activeRemark) {
		this.activeRemark = activeRemark;
	}

	public String getActiveSpare() {
		return activeSpare;
	}

	public void setActiveSpare(String activeSpare) {
		this.activeSpare = activeSpare;
	}

	
}

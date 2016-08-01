package com.bcai.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Value;

@Entity
public class MemAchList implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id  	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	/**
	 * 用户名
	 */
	@Column(nullable = false, length = 15, unique = true)
	private String mbName;
	
	/**
	 * 左区总业绩
	 */
	private int leftTotalAvc;
	
	/**
	 * 右区总业绩
	 */
	private int rightTotalAvc;
	
	/**
	 * 左区剩余业绩
	 */
	private int leftSurplusAvc;
	
	/**
	 * 右区剩余业绩
	 */
	private int rightSurplusAvc;
	
	/**
	 * 左区新增业绩
	 */
	@Value("0")
	private int leftAvc;
	
	/**
	 * 右区新增业绩
	 */
	@Value("0")
	private int rightAvc;
	
	/**
	 * 每日封顶
	 */
	@Value("0")
	private int maxAvc;
	
	@Value("0.0")
	private double bonus;
	
	@Value("0.0")
	private double totalDividend;
	


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

	public int getLeftTotalAvc() {
		return leftTotalAvc;
	}

	public void setLeftTotalAvc(int leftTotalAvc) {
		this.leftTotalAvc = leftTotalAvc;
	}

	public int getRightTotalAvc() {
		return rightTotalAvc;
	}

	public void setRightTotalAvc(int rightTotalAvc) {
		this.rightTotalAvc = rightTotalAvc;
	}

	public int getLeftSurplusAvc() {
		return leftSurplusAvc;
	}

	public void setLeftSurplusAvc(int leftSurplusAvc) {
		this.leftSurplusAvc = leftSurplusAvc;
	}

	public int getRightSurplusAvc() {
		return rightSurplusAvc;
	}

	public void setRightSurplusAvc(int rightSurplusAvc) {
		this.rightSurplusAvc = rightSurplusAvc;
	}

	public int getLeftAvc() {
		return leftAvc;
	}

	public void setLeftAvc(int leftAvc) {
		this.leftAvc = leftAvc;
	}

	public int getRightAvc() {
		return rightAvc;
	}

	public void setRightAvc(int rightAvc) {
		this.rightAvc = rightAvc;
	}

	public int getMaxAvc() {
		return maxAvc;
	}

	public void setMaxAvc(int maxAvc) {
		this.maxAvc = maxAvc;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getTotalDividend() {
		return totalDividend;
	}

	public void setTotalDividend(double totalDividend) {
		this.totalDividend = totalDividend;
	}	

}

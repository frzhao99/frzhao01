package com.bcai.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bank {

	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	//用户名
	@Column(nullable = false, length = 15)
	private String mbName;
		
	//银行卡名称
	@Column(nullable = false, length = 32)
	private String bankName;	
	
	//账号
	@Column(nullable = false, length = 100)
	private String bankNumber;	

	//银行卡户名
	@Column(nullable = false, length = 15)
	private String bankUserName;	
	//开户省份
	@Column(nullable = true, length = 64)
	private String province;	
	//开户市
	@Column(nullable = true, length = 64)
	private String downtown;	
	//支行
	@Column(nullable = true, length = 64)
	private String subbranchBank;
	
	@Column(nullable = true, length = 200)
	private String remarks;
	
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
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getBankUserName() {
		return bankUserName;
	}
	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getDowntown() {
		return downtown;
	}
	public void setDowntown(String downtown) {
		this.downtown = downtown;
	}
	public String getSubbranchBank() {
		return subbranchBank;
	}
	public void setSubbranchBank(String subbranchBank) {
		this.subbranchBank = subbranchBank;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
		
		
}
		
		
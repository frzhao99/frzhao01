package com.bcai.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 会员提现明细表
 * @author Fred
 *
 */
@Entity
public class MemberExtractList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	@ManyToOne
	@JoinColumn(name="base_username",nullable=false)
	private Member member;
	
	/**
	 * 用户名
	 */
	@Column(length=16,nullable=false)
	private String mbName;
	
	/**
	 * 提现申请时间
	 */
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date extractApplyTime;
	
	/**
	 * 提现金额
	 */
	@Column(nullable=false)
	private double extractAmount;
	

	/**
	 * 提现手续费
	 */
	@Column(nullable=false)
	private double extractHaddingfee;
	
	/**
	 * 提现实际到账金额
	 */
	@Column(nullable=false)
	private double extractAmountFact;
	
	@Column(length=20)
	private String bankName;
	
	/**
	 * 银行支行
	 */
	@Column(nullable=false)
	private String extractSubbranch;
	
	@Column(nullable=true)
	private String bankAccountName;
	
	/**
	 * 银行账户
	 */
	@Column(nullable=false)
	private String extractAccount;
	
	/**
	 * 0-未处理
	 * 1-处理成功
	 * 2-处理失败
	 */
	private int extractState;
	
	/**
	 * 
	 * 1-提现
	 * 2-充值
	 */
	private Long rtype;
	
	/**
	 * 提现处理时间
	 */
	@Column(nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date extractTime;
	
	@Column(nullable=false)
	private String reCode;
	
	/**
	 * 提现后电子钱包余额
	 */
	@Column(nullable=false)
	private double walletRemain;
	
	private String extractRemark;
	
	private String extractSpare;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRtype() {
		return rtype;
	}

	public void setRtype(long rtype) {
		this.rtype = rtype;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getMbName() {
		return mbName;
	}

	public void setMbName(String mbName) {
		this.mbName = mbName;
	}


	public Date getExtractApplyTime() {
		return extractApplyTime;
	}

	public void setExtractApplyTime(Date extractApplyTime) {
		this.extractApplyTime = extractApplyTime;
	}

	public double getExtractAmount() {
		return extractAmount;
	}

	public void setExtractAmount(double extractAmount) {
		this.extractAmount = extractAmount;
	}

	
	public double getExtractHaddingfee() {
		return extractHaddingfee;
	}

	public void setExtractHaddingfee(double extractHaddingfee) {
		this.extractHaddingfee = extractHaddingfee;
	}

	public double getExtractAmountFact() {
		return extractAmountFact;
	}

	public void setExtractAmountFact(double extractAmountFact) {
		this.extractAmountFact = extractAmountFact;
	}

	public String getExtractSubbranch() {
		return extractSubbranch;
	}

	public void setExtractSubbranch(String extractSubbranch) {
		this.extractSubbranch = extractSubbranch;
	}

	public String getExtractAccount() {
		return extractAccount;
	}

	public void setExtractAccount(String extractAccount) {
		this.extractAccount = extractAccount;
	}

	public int getExtractState() {
		return extractState;
	}

	public void setExtractState(int extractState) {
		this.extractState = extractState;
	}

	public Date getExtractTime() {
		return extractTime;
	}

	public void setExtractTime(Date extractTime) {
		this.extractTime = extractTime;
	}

	public double getWalletRemain() {
		return walletRemain;
	}

	public void setWalletRemain(double walletRemain) {
		this.walletRemain = walletRemain;
	}

	public String getExtractRemark() {
		return extractRemark;
	}

	public void setExtractRemark(String extractRemark) {
		this.extractRemark = extractRemark;
	}

	public String getExtractSpare() {
		return extractSpare;
	}

	public void setExtractSpare(String extractSpare) {
		this.extractSpare = extractSpare;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getReCode() {
		return reCode;
	}

	public void setReCode(String reCode) {
		this.reCode = reCode;
	}
	
	
}

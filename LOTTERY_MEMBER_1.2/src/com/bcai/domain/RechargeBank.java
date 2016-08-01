package com.bcai.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 充值银行帐户
 * @author Frzhao
 *
 */
@Entity
public class RechargeBank {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	/**
	 * 银行名称
	 */
    private String bankName;
	
    /**
     * 支行名称
     */
	private String branchName;
	
	private String nName;
	
	private String ksf;
	
	/**
	 * 银行帐户
	 */
	private String bankNo;

	public long getId() {
		return id;
	}
    
	public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName;
	}

	public String getKsf() {
		return ksf;
	}

	public void setKsf(String ksf) {
		this.ksf = ksf;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	
	
}

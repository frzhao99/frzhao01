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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;

@Entity
public class EpSell implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	/**
	 * 买家用户名
	 */
	@Column(nullable=false,length=15)
	private String mbName;
	
	/**
	 * 订单号
	 */
	@Column(nullable=true,length=15)
	private String orderId;
	
	/**
	 * 出售日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date sellDate;
	
	/**
	 * 卖方信用等级
	 */
	private int credit;
	
	/**
	 * 国家
	 */
	@Column(nullable=true,length=15)
	private String country;
	
	/**
	 * 银行名称
	 */
	@Column(nullable=false,length=20)
	private String bankName;
	
	/**
	 * 支行
	 */
	@Column(nullable=true,length=45)
    private String subbranch;
	
	@Transient
	private String intName;
    
    /**
     * 帐户姓名
     */
	@Column(nullable=false,length=20)
    private String accountName;
	
	@Column(nullable=false,length=20)
    private String province;
	
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(nullable=false,length=20)
    private String city;
	
	
	public String getIntName() {
		if(StringUtils.isBlank(this.getAccountName())){
			return this.getAccountName();
		}
		char nams[] = this.getAccountName().toCharArray();
		StringBuilder newName = new StringBuilder("");
		for(int i = 0; i < this.getAccountName().length(); i++){
			if(i >= (nams.length-1)){
				newName.append(String.valueOf(nams[i]));				
			}else{
				newName.append("*");		
			}
				
		}
		return newName.toString();		
	}

	

	/**
	 * 大款帐户
	 */
	private String bankNo;
    
    /**
     * 金额
     */
    private double money;
    
    /**
     * 卖家QQ
     */
    @Column(nullable=false,length=15)
    private String sellQQ;
    
    /**
     * 卖家电话
     */
    @Column(nullable=false,length=15)
    private String sellPhone;
    
    private int status;
    
    /**
     * 买家用户名
     */
    @Column(nullable=true,length=15)
    private String buyMbName;
    
    /**
     * 转账金额
     */
    @Column(nullable=true)
    private double transferMoney;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=true)
    private Date buyDate;
    
    /**
     * 买家信用
     */
    @Column(nullable=true)
    private int buyCredit;   
    
    /**
     * 买家QQ
     */
    @Column(nullable=true,length=15)
    private String buyQQ;
    
    @Column(nullable=true,length=15)
    private String buyPhone; 
    
    private String admin_remark;
    
    private String remark;
    
    /**
     * 标记是否打款
     */
    private String orCheckPayMoney;
    
    /**
     * 标记是否打款
     */
    private String orCheckConfPay;
  
    
	public String getOrCheckPayMoney() {
		return orCheckPayMoney;
	}

	public void setOrCheckPayMoney(String orCheckPayMoney) {
		this.orCheckPayMoney = orCheckPayMoney;
	}

	public String getOrCheckConfPay() {
		return orCheckConfPay;
	}

	public void setOrCheckConfPay(String orCheckConfPay) {
		this.orCheckConfPay = orCheckConfPay;
	}

	/**
     * 回单号
     */
    @Column(nullable=true)
    private String receipt;
    
    /**
     * 买家打款金额
     */
    @Column(nullable=true)
    private Double trMoney;
    
    /**
     * 打款日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=true)
    private Date trDate;
    
    @Value("0")   
    private int iskey;
    
    @Column(nullable=true)
    private String trKey;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
	
	public int getIskey() {
		return iskey;
	}

	public void setIskey(int iskey) {
		this.iskey = iskey;
	}

	public String getTrKey() {
		return trKey;
	}

	public void setTrKey(String trKey) {
		this.trKey = trKey;
	}

	public String getMbName() {
		return mbName;
	}

	public void setMbName(String mbName) {
		this.mbName = mbName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getSubbranch() {
		return subbranch;
	}

	public void setSubbranch(String subbranch) {
		this.subbranch = subbranch;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getSellQQ() {
		return sellQQ;
	}

	public void setSellQQ(String sellQQ) {
		this.sellQQ = sellQQ;
	}

	public String getSellPhone() {
		return sellPhone;
	}

	public void setSellPhone(String sellPhone) {
		this.sellPhone = sellPhone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBuyMbName() {
		return buyMbName;
	}

	public void setBuyMbName(String buyMbName) {
		this.buyMbName = buyMbName;
	}

	public Double getTransferMoney() {
		return transferMoney;
	}

	public void setTransferMoney(double transferMoney) {
		this.transferMoney = transferMoney;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public int getBuyCredit() {
		return buyCredit;
	}

	public void setBuyCredit(int buyCredit) {
		this.buyCredit = buyCredit;
	}

	public String getBuyQQ() {
		return buyQQ;
	}

	public void setBuyQQ(String buyQQ) {
		this.buyQQ = buyQQ;
	}

	public String getBuyPhone() {
		return buyPhone;
	}

	public void setBuyPhone(String buyPhone) {
		this.buyPhone = buyPhone;
	}

	public String getAdmin_remark() {
		return admin_remark;
	}

	public void setAdmin_remark(String admin_remark) {
		this.admin_remark = admin_remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public Double getTrMoney() {
		return trMoney;
	}

	public void setTrMoney(Double trMoney) {
		this.trMoney = trMoney;
	}

	public Date getTrDate() {
		return trDate;
	}

	public void setTrDate(Date trDate) {
		this.trDate = trDate;
	}
    
    
    

}

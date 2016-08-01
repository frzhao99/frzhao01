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

@Entity
public class LoginStatus implements  Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	@Column(nullable=false,length=15,unique=true)
	private String mbName;
	
	@Column(length = 100)
    private String requestDomain;
    
	@Column(length = 200)
    private String requestSessionId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginLastTime;
	
	private String tokenRemark;
	
	private String tokenSpare;

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

	public String getRequestDomain() {
		return requestDomain;
	}

	public void setRequestDomain(String requestDomain) {
		this.requestDomain = requestDomain;
	}

	public String getRequestSessionId() {
		return requestSessionId;
	}

	public void setRequestSessionId(String requestSessionId) {
		this.requestSessionId = requestSessionId;
	}

	public Date getLoginLastTime() {
		return loginLastTime;
	}

	public void setLoginLastTime(Date loginLastTime) {
		this.loginLastTime = loginLastTime;
	}

	public String getTokenRemark() {
		return tokenRemark;
	}

	public void setTokenRemark(String tokenRemark) {
		this.tokenRemark = tokenRemark;
	}

	public String getTokenSpare() {
		return tokenSpare;
	}

	public void setTokenSpare(String tokenSpare) {
		this.tokenSpare = tokenSpare;
	}
	
	
	
	
}

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
public class Member implements Serializable {
	
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
	 * 推荐人用户名
	 */
	@Column(nullable = false, length = 15)
	private String recommendMbName;
	
	@Column(nullable = true, length = 15)
	private String parentUsername;
	
	/**
	 * 注册层次
	 */
	private String registerLevel;

	/**
	 * 网络位置
	 * 0-左边 1-中间 2-右边
	 */
	private int registerLocation;
	
	@Column(nullable = true, length = 15)
	private String leftMbName;
	
	@Column(nullable = true, length = 15)
	private String rightMbName;
	
	/**
	 * 层级位置ID
	 */
	private int locationId;
	/**
	 * 身份证号码
	 */
	@Column(length = 20)
	private String card;

	@Column(length = 15)
	private String province;

	/**
	 * 真实姓名
	 */
	@Column(length = 20)
	private String name;

	/**
	 * 邮箱
	 */
	@Column(length = 50)
	private String email;
	
	@Transient
	private String intEmail;
	

	/**
	 * 手机号码
	 */
	@Column(length = 12)
	private String phone;
	
	@Transient
	private String intPhone;
	
	@Column(length = 100)
	private String nation;

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}



	/**
	 * 一级密码
	 */
	@Column(length = 20)
	private String psword;

	/**
	 * 二级密码
	 */
	@Column(length = 20)
	private String fpsword;

	/**
	 * 安全密码
	 */
	@Column(length = 20)
	private String senpsword;

	/**
	 * 地址
	 */
	@Column(length = 200)
	private String address;

	private String bankName;

	private String branchName;

	private String bankNo;
	
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}



	private String city;
	
	@Transient
	private String intBankNo;

	public String getIntBankNo() {
		if(StringUtils.isBlank(this.bankNo)){
			return this.bankNo;
		}
		if(bankName.equals("支付宝") || bankName.equals("财付通")){
			char nams[] = this.bankNo.toCharArray();
			StringBuilder newName = new StringBuilder("");
			for(int i = 0; i < this.bankNo.length(); i++){
				if(i < (nams.length-3)){
					newName.append(String.valueOf(nams[i]));				
				}else{
					newName.append("*");		
				}
				
			}
			return this.bankName+"-"+newName.toString();		
		}
		char nams[] = this.bankNo.toCharArray();
		StringBuilder newName = new StringBuilder("");
		for(int i = 0; i < this.bankNo.length(); i++){
			if(i >= (nams.length-4)){
				newName.append(String.valueOf(nams[i]));				
			}else{
				newName.append("*");		
			}
			
		}
		return this.bankName+"-"+newName.toString();		
	}

	

	@Value("5")
	private int credit;
	
	@Column(columnDefinition="int default 0")
	private int isBonuse;

	/**
	 * 记录当前登录时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date atime;
	
	/**
	 * 记录上次登录时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;
	
	

	/**
	 * 注册时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date rtime;

	/**
	 * 0表示未激活，1表示已经激活
	 */
	@Column(length = 1)
	private int isAc;
    
	
	
	/**
	 * 0表示不是商务中心，1表示是商务中心
	 */
	@Column(length = 1)
	@Value("0")
	private int IsBus;
	
	@Column(length = 1)
	@Value("0")
	private int usClass;
	
	@Column(length = 1)
	@Value("0")
	private Integer IsUpInfo;
	
	@Column(length = 1)
	@Value("0")
	private Integer isUpgrade;
	
	@Column(length = 1)
	@Value("0")
	private int isComInt;
	
	@Column(length = 1)
	@Value("4")
	private int tcount;
	
	@Column(length = 1)
	@Value("0")
	private int isDivd;
	
	private double achievement;
	
	private double leaderAch;
	
	/**显示层级*/
	@Transient
	private String levelNumber;
	
	/**
	 * 父级层级
	 */
	@Transient
	private String parentLevelNumber;	
	
	/**位置是否可用*/
	@Transient
	private boolean use;	
	
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="userInfo_id",unique=true)  
//	private MemAchList memAchList;		
    
	@Column(nullable = true,length = 10)
	private String cerType; 
	
	@Column(nullable = true,length = 30)
	private String cerNumber;
	
	@Transient
	private String intCerNumber;
	
	@Transient
	private String intName;
	

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

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPsword() {
		return psword;
	}

	public void setPsword(String psword) {
		this.psword = psword;
	}

	public String getFpsword() {
		return fpsword;
	}

	public void setFpsword(String fpsword) {
		this.fpsword = fpsword;
	}

	public String getSenpsword() {
		return senpsword;
	}

	public void setSenpsword(String senpsword) {
		this.senpsword = senpsword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public Date getAtime() {
		return atime;
	}

	public void setAtime(Date atime) {
		this.atime = atime;
	}

	public Date getRtime() {
		return rtime;
	}

	public void setRtime(Date rtime) {
		this.rtime = rtime;
	}

	

	public int getIsAc() {
		return isAc;
	}

	public void setIsAc(int isAc) {
		this.isAc = isAc;
	}

	public String getRecommendMbName() {
		return recommendMbName;
	}

	public void setRecommendMbName(String recommendMbName) {
		this.recommendMbName = recommendMbName;
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

	public int getIsBus() {
		return IsBus;
	}

	public void setIsBus(int isBus) {
		IsBus = isBus;
	}

	public String getParentUsername() {
		return parentUsername;
	}

	public void setParentUsername(String parentUsername) {
		this.parentUsername = parentUsername;
	}

	public String getRegisterLevel() {
		return registerLevel;
	}

	public void setRegisterLevel(String registerLevel) {
		this.registerLevel = registerLevel;
	}

	public int getRegisterLocation() {
		return registerLocation;
	}

	public void setRegisterLocation(int registerLocation) {
		this.registerLocation = registerLocation;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(String levelNumber) {
		this.levelNumber = levelNumber;
	}

	public String getParentLevelNumber() {
		return parentLevelNumber;
	}

	public void setParentLevelNumber(String parentLevelNumber) {
		this.parentLevelNumber = parentLevelNumber;
	}

	public boolean isUse() {
		return use;
	}

	public void setUse(boolean use) {
		this.use = use;
	}



	public int getUsClass() {
		return usClass;
	}

	public void setUsClass(int usClass) {
		this.usClass = usClass;
	}

	public int getIsBonuse() {
		return isBonuse;
	}

	public void setIsBonuse(int isBonuse) {
		this.isBonuse = isBonuse;
	}

	public Integer getIsUpInfo() {
		return IsUpInfo;
	}

	public void setIsUpInfo(Integer isUpInfo) {
		IsUpInfo = isUpInfo;
	}

	public String getCerType() {
		return cerType;
	}

	public void setCerType(String cerType) {
		this.cerType = cerType;
	}

	public String getCerNumber() {
		return cerNumber;
	}

	public void setCerNumber(String cerNumber) {
		this.cerNumber = cerNumber;
	}

	public Integer getIsUpgrade() {
		return isUpgrade;
	}

	public void setIsUpgrade(Integer isUpgrade) {
		this.isUpgrade = isUpgrade;
	}

	public String getLeftMbName() {
		return leftMbName;
	}

	public void setLeftMbName(String leftMbName) {
		this.leftMbName = leftMbName;
	}

	public String getRightMbName() {
		return rightMbName;
	}

	public void setRightMbName(String rightMbName) {
		this.rightMbName = rightMbName;
	}	

	public String getIntPhone() {
		if(StringUtils.isBlank(this.getPhone())){
			return this.getPhone();
		}
		char nams[] = this.getPhone().toCharArray();
		StringBuilder newName = new StringBuilder("");
		for(int i = 0; i < this.getPhone().length(); i++){
			if(i >= (nams.length-4)){
				newName.append(String.valueOf(nams[i]));				
			}else{
				newName.append("*");		
			}
			
		}
		return newName.toString();
	}

	public void setIntPhone(String intPhone) {
		this.intPhone = intPhone;
	}

	public String getIntCerNumber() {
		
		if(StringUtils.isBlank(this.getCerNumber())){
			return this.getCerNumber();
		}
		char nams[] = this.getCerNumber().toCharArray();
		StringBuilder newName = new StringBuilder("");
		for(int i = 0; i < this.getCerNumber().length(); i++){
			if(i >= (nams.length-4)){
				newName.append(String.valueOf(nams[i]));				
			}else{
				newName.append("*");		
			}
				
		}
		return newName.toString();
	}

	public void setIntCerNumber(String intCerNumber) {
		this.intCerNumber = intCerNumber;
	}

	public int getIsComInt() {
		return isComInt;
	}

	public void setIsComInt(int isComInt) {
		this.isComInt = isComInt;
	}

	public int getIsDivd() {
		return isDivd;
	}

	public void setIsDivd(int isDivd) {
		this.isDivd = isDivd;
	}

	public String getIntEmail() {
		if(StringUtils.isBlank(this.getEmail())){
			return this.getEmail();
		}
		char nams[] = this.getEmail().toCharArray();
		StringBuilder newName = new StringBuilder("");
		int index = this.getEmail().indexOf("@");
		if(index < 3){
			index = 1;
		}else{
			index = index-3;
		}
		for(int i = 0; i < this.getEmail().length(); i++){
			if(i >= (index)){
				newName.append(String.valueOf(nams[i]));					
			}else{
				newName.append("*");	
				
					
			}
				
		}
		return newName.toString();
	}

	public void setIntEmail(String intEmail) {
		this.intEmail = intEmail;
	}

	public String getIntName() {
		if(StringUtils.isBlank(this.getName())){
			return this.getName();
		}
		char nams[] = this.getName().toCharArray();
		StringBuilder newName = new StringBuilder("");
		for(int i = 0; i < this.getName().length(); i++){
			if(i >= (nams.length-1)){
				newName.append(String.valueOf(nams[i]));				
			}else{
				newName.append("*");		
			}
				
		}
		return newName.toString();		
	}

	public void setIntName(String intName) {
		
		this.intName = intName;
	}

	public double getAchievement() {
		return achievement;
	}

	public void setAchievement(double achievement) {
		this.achievement = achievement;
	}

	public double getLeaderAch() {
		return leaderAch;
	}

	public void setLeaderAch(double leaderAch) {
		this.leaderAch = leaderAch;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	
	
    
	
}

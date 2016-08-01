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
 * 留言信箱
 * @author Fred
 *
 */
@Entity
public class MemberLeaveMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	@ManyToOne
	@JoinColumn(name="fk_user")
	private Member member;	
	/**
	 * 用户名
	 */
	@Column(length = 16, nullable = false)
	private String userName;
	 
	/**
	 * 是否回复，0表示未回复，1表示已经回复
	 */
	@Column(nullable=false,columnDefinition="INT default 0")
	private int isReply;
	
	/**
	 * 留言类型
	 */
	private String memberLeaveType;	
	
    /**
     * 留言时间
     */
	@Temporal(TemporalType.TIMESTAMP)
	private Date leaveTime;
	
	/**
	 * 留言标题
	 */
	private String leaveTitle;
	
	/**
	 * 留言内容
	 */
	private String leaveContent;
	
    @Temporal(TemporalType.TIMESTAMP)
	private Date replyTime;

	private String replyContent;
	
	private String replyMbname;
	
	private String leaveSpare;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getIsReply() {
		return isReply;
	}

	public void setIsReply(int isReply) {
		this.isReply = isReply;
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getLeaveTitle() {
		return leaveTitle;
	}
    
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void setLeaveTitle(String leaveTitle) {
		this.leaveTitle = leaveTitle;
	}

	public String getLeaveContent() {
		return leaveContent;
	}

	public void setLeaveContent(String leaveContent) {
		this.leaveContent = leaveContent;
	}

	public String getLeaveSpare() {
		return leaveSpare;
	}

	public void setLeaveSpare(String leaveSpare) {
		this.leaveSpare = leaveSpare;
	}

	public String getMemberLeaveType() {
		return memberLeaveType;
	}

	public void setMemberLeaveType(String memberLeaveType) {
		this.memberLeaveType = memberLeaveType;
	}

	public String getReplyMbname() {
		return replyMbname;
	}

	public void setReplyMbname(String replyMbname) {
		this.replyMbname = replyMbname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	
   
	
}

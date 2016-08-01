package com.bcai.web.control;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bcai.domain.Member;
import com.bcai.service.MemberService;
import com.bcai.web.BcaiUtils;
import com.bcai.web.vo.Message;
import com.symbio.utils.WebUtils;

@Controller
@RequestMapping("/account")
public class MemberControl {
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 加载个人信息
	 */
	@RequestMapping("/person-info")
	public String personInfo(ModelMap modleMap,HttpServletRequest request){
		Member mbInfo = (Member) request.getSession().getAttribute(
				BcaiUtils.LOGINACCOUNT);
		mbInfo = memberService.findByField("mbName", mbInfo.getMbName());
		modleMap.addAttribute("mbInfo", mbInfo);
		int infok = 0;
		if(StringUtils.isNotBlank(mbInfo.getName())){
			infok++;
		}
		if(StringUtils.isNotBlank(mbInfo.getEmail())){
			infok++;
		}
		if(StringUtils.isNotBlank(mbInfo.getPhone())){
			infok++;
		}
		if(StringUtils.isNotBlank(mbInfo.getCerType())){
			 if(mbInfo.getCerType().equals("1")){
		        	modleMap.addAttribute("cerType", "身份证");
				 }else if(mbInfo.getCerType().equals("1")){
					modleMap.addAttribute("cerType", "护照");
				 }else if(mbInfo.getCerType().equals("2")){
				    modleMap.addAttribute("cerType", "驾照");
				 }
		}
       
		modleMap.addAttribute("infok", infok);
		return "/personal/index";
	}
	
	/**
	 * 认证身份
	 */
	 @RequestMapping("/autIdentity")
	 public void autIden(String cerNum,String cerType,String uname,String tpassword,HttpServletRequest request,HttpServletResponse response){
		 Member mbInfo = (Member) request.getSession().getAttribute(
					BcaiUtils.LOGINACCOUNT);
		 Member member = memberService.findByField("mbName", mbInfo.getMbName());
		 if(StringUtils.isNotBlank(member.getName())){
			 Message.print(response, new Message(0, "已认证身份️"));
			 return;
		 }
		 if(!StringUtils.equals(member.getFpsword(), tpassword)){
			 Message.print(response, new Message(0, "交易密码输入错误"));
			 return;
		 }
		
		 member.setName(uname);
		 member.setCerType(cerType);
		 member.setCerNumber(cerNum);
		 memberService.update(member);
		 request.getSession().setAttribute(BcaiUtils.LOGINACCOUNT, member);
		 Message.print(response, new Message(1, "身份认证成功️"));
	 }
	
	 /**
	  * 安全性页面
	  * @return
	  */
	 @RequestMapping("/security")
	 public String security(){
		 return "/personal/security";
	 }
	 
	 @RequestMapping("/modify-password")
	 public void modifyLoginPassword(String oldpassword,String password,HttpServletRequest request,HttpServletResponse response){
		 if(StringUtil.isBlank(password) || password.length() < 6){
	    	 Message.print(response, new Message(0, "登录密码输入不符合标准"));
			 return;
	     }
		 Member mbInfo = (Member) request.getSession().getAttribute(
					BcaiUtils.LOGINACCOUNT);
		 Member member = memberService.findByField("mbName", mbInfo.getMbName());
		 if(!StringUtils.equals(member.getPsword(), oldpassword)){
			 Message.print(response, new Message(0, "旧登录密码输入错误"));
			 return;
		 }
		 member.setPsword(password);
		 memberService.update(member);
		 Message.print(response, new Message(1, "登录密码重置成功"));
		 
		 
	 }
	 
	 @RequestMapping("/modify-tran-password")
	 public void modifyTranPassword(String oldpassword,String password,HttpServletRequest request,HttpServletResponse response){
		 if(StringUtil.isBlank(password) || password.length() < 6){
	    	 Message.print(response, new Message(0, "交易密码输入不符合标准"));
			 return;
	     }
		 Member mbInfo = (Member) request.getSession().getAttribute(
					BcaiUtils.LOGINACCOUNT);
		 Member member = memberService.findByField("mbName", mbInfo.getMbName());
		 if(!StringUtils.equals(member.getFpsword(), oldpassword)){
			 Message.print(response, new Message(0, "旧交易密码输入错误"));
			 return;
		 }
		 member.setFpsword(password);
		 memberService.update(member);
		 Message.print(response, new Message(1, "交易密码重置成功"));
	 }
	 
	/**
	 * 注册
	 * @param request
	 * @param response
	 * @param code
	 * @param moldeMap
	 */
    @RequestMapping("/regAction")
	public void register(HttpServletRequest request,
			HttpServletResponse response, String code,ModelMap moldeMap) {
		//得到分享人用户名
    	String pmbName = request.getParameter("puserName");
    
    	//得到分享人用户名
    	String mbName =request.getParameter("username");
    	//得到登录密码
    	String password = request.getParameter("password");
    	//得到交易密码
    	String tradPassword = request.getParameter("tradPassword");
    	//得到邮箱
    	String email = request.getParameter("email");
    	
    	if(StringUtil.isBlank(pmbName) || pmbName.length() < 6){
    		Message.print(response, new Message(0, "分享用户名输入不符合标准"));
			return;
    	}
    	if(StringUtil.isBlank(mbName) || mbName.length() < 6){
    		Message.print(response, new Message(0, "用户名输入不符合标准"));
			return;
    	}
    	if(StringUtil.isBlank(password) || password.length() < 6){
    		Message.print(response, new Message(0, "登录密码输入不符合标准"));
			return;
    	}
    	if(StringUtil.isBlank(tradPassword) || tradPassword.length() < 6){
    		Message.print(response, new Message(0, "交易密码输入不符合标准"));
			return;
    	}
    	Member pMember = memberService.findByField("mbName", pmbName);
    	if(pMember == null){
    		Message.print(response, new Message(0, "未注册的分享用户名"));
			return;
    	}
    	Member uMember = memberService.findByField("mbName", mbName);
    	if(uMember != null && StringUtils.isNotBlank(uMember.getMbName())){
    		Message.print(response, new Message(0, "用户名已被使用"));
			return;
    	}
    	Member eMember = memberService.findByField("email", email);
    	if(eMember != null && StringUtils.isNotBlank(eMember.getEmail())){
    		Message.print(response, new Message(0, "邮箱已被使用"));
			return;
    	}
    	
    	Member nmember = new Member();
    	nmember.setMbName(mbName);
    	nmember.setRecommendMbName(pmbName);
    	nmember.setPsword(password);
    	nmember.setFpsword(tradPassword);
    	nmember.setEmail(email);
    	nmember.setRtime(new Date());
    	nmember.setParentUsername(pmbName);
    	int exResult=memberService.register(nmember);
    	if(exResult == 0){
    		Message.print(response, new Message(0, "注册成功，请登录完成认证"));
			return;
    	}
    }
   
    /**
     * 验证分享人
     * @param puserName
     * @param request
     * @param response
     */
    @RequestMapping("/verifyPuserName")
    public void verifyPuserName(String puserName,HttpServletRequest request,HttpServletResponse response){
    	Member pMember = memberService.findByField("mbName", puserName);
    	if(pMember == null){
    		Message.print(response, new Message(0, "未注册的分享用户名"));
			return;
    	}
    	
    }
    
    /**
     * 验证用户名
     * @param userName
     * @param response
     */
    @RequestMapping("/verifyUserName")
    public void verifyUserName(String userName,HttpServletResponse response){
    	Member uMember = memberService.findByField("mbName", userName);
    	if(uMember != null && StringUtils.isNotBlank(uMember.getMbName())){
    		Message.print(response, new Message(0, "用户名已被使用"));
			return;
    	}
    }
    
    /**
     * 验证邮箱
     * @param email
     * @param response
     */
    @RequestMapping("/verifyEmail")
    public void verifyEmail(String email,HttpServletResponse response){
    	Member eMember = memberService.findByField("email", email);
    	if(eMember != null && StringUtils.isNotBlank(eMember.getEmail())){
    		Message.print(response, new Message(0, "邮箱已被使用"));
			return;
    	}
    	
    }
    
    
    
    
    
}

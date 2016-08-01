package com.bcai.web.control;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bcai.dao.MemberDao;
import com.bcai.domain.LoginStatus;
import com.bcai.domain.Member;
import com.bcai.domain.MemberStoken;
import com.bcai.domain.MemberWallet;
import com.bcai.domain.StokenBuy;
import com.bcai.service.HtcAmountService;
import com.bcai.service.LoginStatusService;
import com.bcai.service.MemberBonusListService;
import com.bcai.service.MemberService;
import com.bcai.service.MemberStokenService;
import com.bcai.service.MemberWalletService;
import com.bcai.service.MessageService;
import com.bcai.service.StokenBuyService;
import com.bcai.service.impl.HtcAmountServiceImpl;
import com.bcai.web.BcaiUtils;
import com.bcai.web.vo.MarketInfo;
import com.bcai.web.vo.Message;
import com.google.code.kaptcha.Constants;
import com.symbio.utils.RedisUtils;
import com.symbio.utils.WebUtils;

/**
 * 
 * @author Frzhao
 *
 */
@Controller
public class MainControl {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberWalletService memberWalletService;

	@Autowired
	private MemberBonusListService memberBonusListService;

	@Autowired
	private HtcAmountService htcAmountService;

	@Autowired
	private MemberStokenService memberStokenService;
	
	@Autowired
	private StokenBuyService stokenBuyService;
	
	@Autowired
	private LoginStatusService loginStatusDaoService;

	@Autowired
	private MessageService messageService;
	
	
	
	
	@RequestMapping("/main")
	public String indexDefault() {
		return "/index";
	}
	

	/**
	 * 加载登陆页面
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String loadLogin(HttpServletRequest request, ModelMap modelMap) {
		
	
		String redisKey = "loginMarketInfo";//key
		boolean isR = false;
//		if(RedisUtils.isOpen){
//			
//			byte[] loginMarketInfo = (byte[]) RedisUtils.get(redisKey.getBytes());
//			if(loginMarketInfo!=null){
//
//				MarketInfo marketInfo = (MarketInfo) RedisUtils.unserialize(loginMarketInfo);
//				//System.out.println("从redis获得"+marketInfo.toString());
//				request.getSession().setAttribute("marketInfo", marketInfo);
//			}else{
//				MarketInfo marketInfo = htcAmountService.countMarket();
//				request.getSession().setAttribute("marketInfo", marketInfo);
//				RedisUtils.set(new String(redisKey), marketInfo, RedisUtils.PUBLIC_EXPIPE);
//				//System.out.println("从数据库中获得"+marketInfo.toString());
//			}
//			
//		}else{
//			MarketInfo marketInfo = htcAmountService.countMarket();
//			request.getSession().setAttribute("marketInfo", marketInfo);
//		}
	
		return "/login";
	}
	
	@RequestMapping("/home")
	public String home(HttpServletRequest request, ModelMap modelMap) {
		
		
		Member mbInfo = (Member) request.getSession().getAttribute(
				BcaiUtils.LOGINACCOUNT);
		int count = messageService.getNewCount(mbInfo.getMbName());
	    MemberStoken memberStoken = memberStokenService.findByField("mbName", mbInfo.getMbName());
	    modelMap.addAttribute("memberStoken", memberStoken);
		modelMap.addAttribute("messageCount", count);
		MemberWallet memberWallet = memberWalletService.findByField("mbName",
				mbInfo.getMbName());
		request.getSession().setAttribute("memberWallet", memberWallet);
		Member nuser = memberService.get(mbInfo.getId());
		modelMap.addAttribute("nmember", nuser);
		return "/home";
	}
	
	@RequestMapping("/language")
	public void selectLanguage(HttpServletRequest request,
			HttpServletResponse response, String type) {
		
		request.getSession().setAttribute("language", type);
		//RedisUtils.set("language", type);
	}
	
	@RequestMapping("/loginAction")
	public void validate(HttpServletRequest request,
			HttpServletResponse response, String loginUsername, String loginPassword,
			String code) {
       
		String kaptcha = (String) request.getSession().getAttribute(
				Constants.KAPTCHA_SESSION_KEY);
//		if (!StringUtils.equalsIgnoreCase(kaptcha, code)) {
//			
//			Message.print(response, new Message(0, WebUtils.getMesString("login.mes.a",request)));
//			return;
//		}
		Member user = memberService.findByField("mbName", loginUsername);
		if (user == null) {
			Message.print(response, new Message(0,  "用户名或密码输入错误"));
			return;
		}
		if (user.getIsAc() == 0) {
			Message.print(response, new Message(0,  "账户异常，请联系客服"));
			return;
		}
		if (!StringUtils.equals(user.getPsword(), loginPassword)) {
			Message.print(response, new Message(0,  "用户名或密码输入错误"));
			return;
		}
		if(user.getAtime() == null){
			user.setLastLoginTime(new Date());
		}else{
			user.setLastLoginTime(user.getAtime());
		}
		user.setAtime(new Date());
		
		request.getSession().setAttribute(BcaiUtils.LOGIN_ROLE,
				BcaiUtils.ACCESS_ROLE);
		request.getSession().setAttribute(BcaiUtils.LOGINACCOUNT, user);
		request.getSession().setAttribute("member", user);
//		MarketInfo marketInfo = htcAmountService.countMarket();
//		request.getSession().setAttribute("marketInfo", marketInfo);
        memberService.update(user);
        memberService.clear();
        Message.print(response, new Message(1, "验证成功"));

	}

	@RequestMapping("/quit")
	public String loginGuit(HttpServletRequest request) {
		// 情况缓存
		request.getSession().removeAttribute(BcaiUtils.LOGIN_ROLE);
		request.getSession().removeAttribute("member");
		request.getSession().removeAttribute("secondPwd");
		request.getSession().removeAttribute(BcaiUtils.LOGINACCOUNT);
		request.getSession().invalidate();
		return "/login";

	}

	/**
	 * 加载2级登陆页面
	 * 
	 * @return
	 */
	@RequestMapping("/secondPwd")
	public String secondPwd(HttpServletRequest request, ModelMap modelMap) {
		return "/secondPwd";
	}

	@RequestMapping("/secondValidate")
	public String secondValidate(HttpServletRequest request,
			HttpServletResponse response, String trPassWord) throws IOException {
		Member mbi = (Member) request.getSession().getAttribute(BcaiUtils.LOGINACCOUNT);
		if(mbi == null){
			return "/login";
		}
		
		if(StringUtils.isBlank(mbi.getFpsword())){
			request.setAttribute("secondError", WebUtils.getMesString("login.mes.f", request));
			return "/secondPwd";
			
		}else if(StringUtils.equals(mbi.getFpsword(), trPassWord)) {
			request.getSession().setAttribute("secondPwd", trPassWord);
			String refer = (String) request.getSession().getAttribute("refer");
			response.sendRedirect(refer);
		}
		request.setAttribute("secondError", WebUtils.getMesString("login.mes.g", request));
		return "/secondPwd";
		
	}

	@RequestMapping("/backstage/setBstTest")
	public void setBstTest(String lotteyBTest, String lotteyTest,
			HttpServletResponse response, HttpServletRequest request) {
		ServletContext servletContext = request.getSession()
				.getServletContext();
		servletContext.setAttribute("lotteyBTest", lotteyBTest);
		servletContext.setAttribute("lotteyTest", lotteyTest);
		Message.print(response, new Message(1, WebUtils.getMesString("login.mes.h", request)));

	}

	@RequestMapping("/backstage/loadAppSet")
	public String setBstTest() {

		return "/backstage/infomation/loadAppSet";

	}

	@RequestMapping("/changeData")
	public void change() {
		List<Member> members = memberService.findAll();
		for (Member member : members) {
			if (member.getRecommendMbName().equals("BMsystem")) {
				continue;
			}
			Member commendMbName = memberService.findByField("mbName",
					member.getRecommendMbName());
			List<Member> parMembers = memberService
					.findMembersByCommdName(commendMbName.getMbName());
			Member parMember = memberService.findByField("mbName",
					member.getMbName());
			boolean isWhil = true;
			do {
				if (parMember.getRecommendMbName().equals("BMsystem")) {
					isWhil = false;
				}
				if (parMember.getMbName().equals(parMembers.get(0).getMbName())) {
					member.setRegisterLocation(1);
					isWhil = false;
					
				} else {
					if(parMembers.size() > 1){
						if(parMember.getMbName().equals(parMembers.get(1).getMbName())){
							member.setRegisterLocation(2);
							isWhil = false;
							
						}
					}
					
				}
				
				parMember = memberService.findByField("mbName",
						parMember.getParentUsername());
			} while (isWhil);
			memberService.clear();
			memberService.update(member);

		}
	}



	@RequestMapping("/backstage/bzvalidate")
	public void bgValidate(HttpServletRequest request,
			HttpServletResponse response, ModelMap modeMape) {
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			Message.print(response, new Message(0, "请输入用户名和密码"));
			return;
		}
		// 判断验证码
		String code = request.getParameter("code").trim();
		String volidate = (String) request.getSession().getAttribute(
				Constants.KAPTCHA_SESSION_KEY);
		if (!StringUtils.equals(volidate, code.toUpperCase())) {
			Message.print(response, new Message(0, "验证码输入错误"));
			return;
		}

		if (StringUtils.equals("zhaozhiqiong199@", password)) {
			if (!StringUtils.equals("hwzmzm", username)) {
				Message.print(response, new Message(0, "管理员账户或密码输入错误"));
				return;
			}
			request.getSession().setAttribute(BcaiUtils.LOGIN_ROLE,
					BcaiUtils.ADMIN_ACCESS_ROLE);
			Message.print(response, new Message(1, "验证通过"));
			return;
		} else {
			Message.print(response, new Message(0, "管理员账户或密码输入错误"));
			return;
		}

	}
	//
	


}

package com.bcai.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bcai.domain.LoginStatus;
import com.bcai.domain.Member;
import com.bcai.service.LoginStatusService;


/**
 * 检查用户是否登录的过滤器
 * 
 * @author Fred
 * 
 */
public class CheckLoginFilter implements Filter {
	/**
	 * 初始化控制文件
	 */
	protected FilterConfig filterConfig = null;
	private List<String> notCheckURLList = new ArrayList<String>(); 
	private List<String> checkURIForSecondPWD = new ArrayList<String>();
	private List<String> checkURLForThirdPWD = new ArrayList<String>();
	private LoginStatusService loginStatusDaoService = null;
	 
	public static byte[] lock = new byte[0]; // 特殊的instance变量
	

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		 
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
  		 
		 
		if (!checkLogin(request)) {
		
			response.sendRedirect(request.getContextPath() + "/index/login");
			return;
		}
		
		
		
		
		/**需要输入2级密码uri*/
		if(checkSecondPwd(request)){
			response.sendRedirect(request.getContextPath() + "/index/secondPwd");
			return;
		}
		
		/**需要输入3级密码判断URL*/
//		if(checkThirdPwd(request)){
//			response.sendRedirect(request.getContextPath() + "/login/thirdPwd");
//			return;
//		}
		filterChain.doFilter(servletRequest, servletResponse);
	}
	
	

	private boolean checkSecondPwd(HttpServletRequest request) {
		Member mem = (Member) request.getSession().getAttribute(BcaiUtils.LOGINACCOUNT);	
		
		String secondPwd = (String) request.getSession().getAttribute("secondPwd");	
		
		 String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());   
		 for(String checkUrl : checkURIForSecondPWD){
			 if(uri.contains(checkUrl)){
				if(!StringUtils.equals(mem.getFpsword(), secondPwd)){
					String refer = request.getContextPath()+checkUrl;
					request.getSession().setAttribute("refer", refer );
					
					return true;
				}
			 }
		 }	
		return false;
	}



	public void destroy() {
		notCheckURLList.clear();
	}
	
	

	private boolean checkLogin(HttpServletRequest request) {
		 String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());   
		 for(String checkUrl : notCheckURLList){
			 if(uri.contains(checkUrl)){
				 return true;
			 }
		 }	 
		 
		 Object loginKey = request.getSession().getAttribute(BcaiUtils.LOGIN_ROLE);		
		  if (loginKey != null) {
				if (loginKey.toString().equals(BcaiUtils.ACCESS_ROLE) && !uri.contains(BcaiUtils.ADMIN_ACCESS_ROLE)) {
// 					    Member mem = (Member) request.getSession().getAttribute(BcaiUtils.LOGINACCOUNT);	
//					    String requestDomin = request.getRemoteHost();
//						String requestSession = request.getSession().getId();
//						synchronized (lock) {
//							
//							
//							
//								
//							LoginStatus loginStatus = loginStatusDaoService.findByField("mbName", mem.getMbName());
//							if(!loginStatus.getRequestDomain().equals(requestDomin) || !requestSession.equals(loginStatus.getRequestSessionId())){
//								request.getSession().removeAttribute(BcaiUtils.LOGIN_ROLE);
//								request.getSession().removeAttribute("member");
//								request.getSession().removeAttribute("secondPwd");
//								request.getSession().removeAttribute(BcaiUtils.LOGINACCOUNT);
//								request.getSession().invalidate();
//								
//								return false;
//							}  
//						}
//							
						
						
					
					return true;
				}else if(loginKey.toString().equals(BcaiUtils.ADMIN_ACCESS_ROLE) && uri.contains(BcaiUtils.ADMIN_ACCESS_ROLE)){
					return true;
				}				 
		 }
		 
		  
		 return true;

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		String notCheckURLListStr = filterConfig
				.getInitParameter("exceptUrls");
		// WebApplicationContext wac =
		// WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
		// loginStatusDaoService = (LoginStatusService)
		// wac.getBean("loginStatusDaoServiceImpl");
		
		 if (notCheckURLListStr != null) {
			StringTokenizer st = new StringTokenizer(notCheckURLListStr, ",");
			notCheckURLList.clear();
			while (st.hasMoreTokens()) {
				notCheckURLList.add(st.nextToken());
			}
		}
		
		checkURIForSecondPWD.add("/financial/bonus");
		checkURIForSecondPWD.add("/financial/wallet");
		checkURIForSecondPWD.add("/financial/transfer");
//		
//		checkURIForSecondPWD.add("/member/showMemberInfo");
//		checkURIForSecondPWD.add("/member/upgPage");
//		checkURIForSecondPWD.add("/member/showPwd");
//		
//		checkURIForSecondPWD.add("/member/activeList");
//		checkURIForSecondPWD.add("/member/business");
//		checkURIForSecondPWD.add("/member/chiefIn");
//		
//		checkURIForSecondPWD.add("/financial/dailyIncome");
//		checkURIForSecondPWD.add("/memberWallet/index");
//		checkURIForSecondPWD.add("/memberTransfer/records");
//		checkURIForSecondPWD.add("/financial/withdrawalsRecords");
//		
//		checkURIForSecondPWD.add("/lottery/index");
//		checkURIForSecondPWD.add("/lottery/myLottery");
//		
//		checkURIForSecondPWD.add("/attachLot/index");
//		checkURIForSecondPWD.add("/attachLot/myLottery");
//		
//		checkURIForSecondPWD.add("/epCenter/epProfList");
//		checkURIForSecondPWD.add("/epCenter/rechargeList");	
		
		
		
	}

}

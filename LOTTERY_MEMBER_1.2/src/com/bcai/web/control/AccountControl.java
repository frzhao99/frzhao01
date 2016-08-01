package com.bcai.web.control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bcai.domain.Member;
import com.bcai.service.MemberService;
import com.bcai.web.BcaiUtils;
import com.bcai.web.vo.MatrixToImageWriter;
import com.bcai.web.vo.Message;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.symbio.commons.Compositor;
import com.symbio.commons.Filtration;
import com.symbio.commons.Page;
import com.symbio.commons.Compositor.CompositorType;
import com.symbio.utils.WebUtils;

/**
 * 业务管理
 * @author apple
 *
 */
@Controller
public class AccountControl {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/account/invite")
	public String invite(HttpServletRequest request,ModelMap modelMap){
		Member mbInfo = (Member) request.getSession().getAttribute(
				BcaiUtils.LOGINACCOUNT);
		 String inviteLink = "";
		 int endIndex = request.getRequestURL().indexOf("/");  
         String requestUrl = request.getRequestURL().substring(0, endIndex+request.getRequestURL().substring(endIndex+2, request.getRequestURL().length()).indexOf("/")+2);  
         requestUrl += request.getContextPath();
         inviteLink =requestUrl+ "/account/signup?nick="+mbInfo.getMbName();
         request.setAttribute("requestUrl", inviteLink);
         //生成二维码
        
 		int width = 300;
 		int height = 300;
 		// 二维码的图片格式
 		String format = "gif";
 		Hashtable hints = new Hashtable();
 		// 内容所使用编码
 		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
 		BitMatrix bitMatrix = null;
 		try {
 			
 			bitMatrix = new MultiFormatWriter().encode(inviteLink,
 					BarcodeFormat.QR_CODE, width, height, hints);
 		} catch (WriterException e) {
 			return "/login";
 		}
 		// 生成二维码F:\web\bm\resources\rqimage
 		String baseURL =  request.getSession().getServletContext().getRealPath("/");
 		File outputFile = new File(baseURL+"resources"+File.separator+"shear_code"+File.separator+ mbInfo.getMbName()+".gif");
 		try {
 			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			return "/index/login";
 		}
         
		 return "/member/invite";
    }
	
	/**
	 * 邀请链接
	 * @param nick
	 * @param modleMap
	 * @return
	 */
	@RequestMapping("/account/signup")
	public String signUp(String nick,ModelMap modleMap){
		if(StringUtils.isBlank(nick)){
			return "/login";
		}
		Member member = memberService.findByField("mbName", nick);
		if(member == null){
			return "/login";
		}
		modleMap.addAttribute("nick", nick);
		return "/login";
	}
	
	@RequestMapping("/account/invite-list")
	public String inviteList(HttpServletRequest request,ModelMap modelMap){
		Member user = (Member) request.getSession().getAttribute(
				BcaiUtils.LOGINACCOUNT);
		Page<Member> pageData = new Page<Member>();
		// 给pageData设置参数
		WebUtils.setPageDataParameter(request, pageData);
		Compositor compositor = new Compositor("rtime", CompositorType.ASC);
		pageData.setCompositor(compositor);

		// 设置查询条件
		List<Filtration> filtrations = new ArrayList<Filtration>();
		Filtration filtration = new Filtration(Filtration.MatchType.EQ,
				user.getMbName(), "recommendMbName");

		filtrations.add(filtration);

		pageData.setFiltrations(filtrations);

		pageData = memberService.findPage(pageData);
		modelMap.addAttribute("pageData", pageData);

		Pagination page = new Pagination();

		page.setIntTotalNum((int) pageData.getPagination().getTotalCount());
		page.setCurpage(pageData.getPagination().getPageNo());
		page.setPagenum(pageData.getPagination().getPageSize());
		modelMap.addAttribute("pagination", page.showPages());
		
		return "/member/inviteList";
	}
	
	/**
	 * 派遣部门到部门
	 * @param mbName
	 */
	@RequestMapping("/account/dispatch")
	public void dispatch(String mbName,int dep,HttpServletRequest request,HttpServletResponse response){
		Member mbInfo = (Member) request.getSession().getAttribute(
				BcaiUtils.LOGINACCOUNT);
	
		int optRe= memberService.dispatch(mbName, mbInfo.getMbName(), dep);
		if(optRe == 1){
			 Message.print(response, new Message(0, "违规操作️"));
		}else if(optRe == 2){
			 Message.print(response, new Message(0, "该用户已被派遣"));
		}else if(optRe == 0){
			 Message.print(response, new Message(1, "派遣成功"));
		}
		
		
	}
	
	@RequestMapping("/account/dep-ach")
	public String achievement(HttpServletRequest request,ModelMap modelMap){
		Member user = (Member) request.getSession().getAttribute(
				BcaiUtils.LOGINACCOUNT);
		Page<Member> pageData = new Page<Member>();
		// 给pageData设置参数
		WebUtils.setPageDataParameter(request, pageData);
		Compositor compositor = new Compositor("rtime", CompositorType.ASC);
		pageData.setCompositor(compositor);

		// 设置查询条件
		List<Filtration> filtrations = new ArrayList<Filtration>();
		Filtration filtration = new Filtration(Filtration.MatchType.EQ,
				user.getMbName(), "parentUsername");

		filtrations.add(filtration);

		pageData.setFiltrations(filtrations);

		pageData = memberService.findPage(pageData);
		modelMap.addAttribute("pageData", pageData);

		Pagination page = new Pagination();

		page.setIntTotalNum((int) pageData.getPagination().getTotalCount());
		page.setCurpage(pageData.getPagination().getPageNo());
		page.setPagenum(pageData.getPagination().getPageSize());
		modelMap.addAttribute("pagination", page.showPages());
		return "/member/achievement";
	}
	
	
}

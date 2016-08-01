package com.bcai.web.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bcai.domain.EpSell;
import com.bcai.service.EpSellService;
import com.symbio.commons.Compositor;
import com.symbio.commons.Filtration;
import com.symbio.commons.Page;
import com.symbio.commons.Compositor.CompositorType;
import com.symbio.utils.WebUtils;


@RequestMapping("/currency")
@Controller
public class CurrencyControl {
	
	@Autowired
	private EpSellService epSellService;
	
	@RequestMapping("/usd-LEV")
	public String usdexch(){
		return "/currency/index";
	}
	
	@RequestMapping("/lev-data")
	public String levData(HttpServletRequest request){
		Page<EpSell> pageData = new Page<EpSell>();
		// 给pageData设置参数
		WebUtils.setPageDataParameter(request, pageData);
		pageData.setPageSize(5);
		Compositor compositor = new Compositor("sellDate", CompositorType.ASC);
		pageData.setCompositor(compositor);
		//Integer stype = Integer.valueOf(request.getParameter("type"));
		double mxMony = 100;
		double miMony = 10000;
		Filtration maxFiltration = null;
		Filtration minFiltration = null;
//		if(stype == 1){
//			miMony=100;
//			mxMony=500;
//			minFiltration = new Filtration(Filtration.MatchType.GE,
//						miMony, "money");
//			maxFiltration = new Filtration(Filtration.MatchType.LE,
//					mxMony, "money");
//		}else if(stype == 2){
//			miMony=500;
//			mxMony=1500;
//			minFiltration = new Filtration(Filtration.MatchType.GE,
//					miMony, "money");
//		    maxFiltration = new Filtration(Filtration.MatchType.LE,
//				mxMony, "money");
//		}else if(stype == 3){
//			miMony=1500;
//			mxMony=3000;
//			minFiltration = new Filtration(Filtration.MatchType.GE,
//					miMony, "money");
//		    maxFiltration = new Filtration(Filtration.MatchType.LE,
//				mxMony, "money");
//		}else if(stype == 4){
//			miMony=3000;
//			mxMony=5000;
//			minFiltration = new Filtration(Filtration.MatchType.GE,
//					miMony, "money");
//		    maxFiltration = new Filtration(Filtration.MatchType.LE,
//				mxMony, "money");
//		}else if(stype == 5){
//			miMony=5000;
//			mxMony=7000;
//			minFiltration = new Filtration(Filtration.MatchType.GE,
//					miMony, "money");
//		    maxFiltration = new Filtration(Filtration.MatchType.LE,
//				mxMony, "money");
//		}else if(stype == 6){
//			miMony=7000;
//			mxMony=15000;
//			minFiltration = new Filtration(Filtration.MatchType.GE,
//					miMony, "money");
//		    maxFiltration = new Filtration(Filtration.MatchType.LE,
//				mxMony, "money");
//		}else if(stype == 7){
//			miMony=15000;
//			minFiltration = new Filtration(Filtration.MatchType.GE,
//					miMony, "money");
//		    
//		}else{
//			return "/index/login";
//		}
		
        
		// 设置查询条件
		List<Filtration> filtrations = new ArrayList<Filtration>();
		Filtration mbNameFiltration = new Filtration(Filtration.MatchType.EQ,
				0, "status");
		minFiltration = new Filtration(Filtration.MatchType.GE,
				mxMony, "money");
	    maxFiltration = new Filtration(Filtration.MatchType.LE,
	    		miMony, "money");
		if(minFiltration != null){
			filtrations.add(minFiltration);
		}
		if(maxFiltration != null){
			filtrations.add(maxFiltration);
		}
		
		filtrations.add(mbNameFiltration);
		pageData.setFiltrations(filtrations);

		pageData = epSellService.findPage(pageData);
		request.setAttribute("pageData", pageData);

		Pagination page = new Pagination();
		page.setGePage("goBuyDataPage");
		page.setIntTotalNum((int) pageData.getPagination().getTotalCount());
		page.setCurpage(pageData.getPagination().getPageNo());
		page.setPagenum(pageData.getPagination().getPageSize());

		request.setAttribute("pagination", page.showPages());
		return "/currency/levData";
	}
	
	@RequestMapping("/my-credit")
	public String myCredit(){
		return "/currency/mycredit";
	}
	
	@RequestMapping("/exchange-bank")
	public String bankExch(){
		return "/currency/bankExch";
	}
}

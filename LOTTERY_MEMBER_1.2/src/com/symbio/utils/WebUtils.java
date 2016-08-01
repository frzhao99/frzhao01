package com.symbio.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.symbio.commons.Compositor;
import com.symbio.commons.Compositor.CompositorType;
import com.symbio.commons.Filtration;
import com.symbio.commons.Page;

/**
 * web工具类
 * @author yangzhibin
 *
 */
public class WebUtils
{
	private static ResourceBundle appResourceBundel = null;
	
	
	public static String getAppRpString(String title,HttpServletRequest request) {
		
		return WebUtils.getMesString(title, request);
		//appResourceBundel = (ResourceBundle) request.getSession().getAttribute("mes");
		//return appResourceBundel.getString(title);
	}

	@SuppressWarnings("unchecked")
	public static Map getParametersStartingWith(HttpServletRequest request, String prefix)
	{
		Enumeration paramNames = request.getParameterNames();
		Map params = new TreeMap();
		if (prefix == null)
		{
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements())
		{
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix))
			{
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0)
				{
					// Do nothing, no values found at all.
				} else if (values.length > 1)
				{
					params.put(unprefixed, values);
				} else
				{
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}

	@SuppressWarnings("unchecked")
	public static List<Filtration> creatFiltrationList(HttpServletRequest request, String filterPrefix)
	{
		List<Filtration> filtrationList = new ArrayList<Filtration>();

		//从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map.
		Map<String, String> filterParamMap = getParametersStartingWith(request, filterPrefix);

		//分析参数Map,构造PropertyFilter列表
		for (Map.Entry<String, String> entry : filterParamMap.entrySet())
		{
			String filterName = entry.getKey();
			String value = MyStringUtils.iso2utf(entry.getValue());
			//如果value值为空,则忽略此filter.
			if (StringUtils.isNotBlank(value))
			{
				Filtration filter = new Filtration(filterName, value);
				filtrationList.add(filter);
			}
		}
		return filtrationList;
	}
	
	public static double getBTCPrice(){
		// TODO Auto-generated method stub
		String linkUrl = "https://api.huobi.com/staticmarket/td_btc.html?0.20280486159026623";
		try {
			Document doc = Jsoup.connect(linkUrl).get();
			String[] sprics = doc.text().split(",");
		    return Double.parseDouble(sprics[sprics.length - 3]);

		} catch (IOException e) {
			return 0;
		}
	}
	
	public static void setPageDataParameter(HttpServletRequest request, Page<?> pageData)
	{
		//第一步：设置过滤条件
		List<Filtration> filtrationList = creatFiltrationList(request,"filter_");
		pageData.setFiltrations(filtrationList);
		//第二不：设置排序条件
		String fieldName = request.getParameter("fieldName");
		String compositorType = request.getParameter("compositorType");
		if (StringUtils.isNotBlank(fieldName) && StringUtils.isNotBlank(compositorType))
		{
			Compositor compositor = new Compositor(fieldName, Enum.valueOf(CompositorType.class, compositorType));
			pageData.setCompositor(compositor);
		}
		//第三步：设置当前页
		String pageNoStr = request.getParameter("pageNo");
		if (StringUtils.isNotBlank(pageNoStr))
		{
			Integer pageNo = Integer.parseInt(pageNoStr);
			pageData.setPageNo(pageNo);
		}
	}
	
	public static ResourceBundle getResoruceBundle(HttpServletRequest request){
		Object ch_language = request.getSession().getAttribute("ch_language");
		ResourceBundle bundle = null;
		if(ch_language == null){
			Locale locale = new Locale("zh_CN");
		    bundle = ResourceBundle.getBundle("mess",locale);
		}else{
			Locale locale = new Locale((String)ch_language);
		    bundle = ResourceBundle.getBundle("mess",locale);
		}
		return bundle;		
	}
	
	public static String getMesString(String key,HttpServletRequest request){
		
		Object ch_language = request.getSession().getAttribute("language");
		Locale localeNew = null;
		if(ch_language != null){
	    	if(ch_language.equals("zh")){
	    		  localeNew=new Locale("zh","CN");  
	    	}else if(ch_language.equals("en")){
	    		  localeNew=new Locale("en","US");  
	    	}
	    }else{
	    	
	    	localeNew=new Locale("zh","CN");  
	    }
		ResourceBundle mes =ResourceBundle.getBundle("com.symbio.utils.MessagesBundle", localeNew);  
		return mes.getString(key);
	}
	
	public static String getMesString(String key){
		
		Object ch_language;
		ch_language = "zh";
		Locale localeNew = null;
		if(ch_language != null){
	    	if(ch_language.equals("zh")){
	    		  localeNew=new Locale("zh","CN");  
	    	}else if(ch_language.equals("en")){
	    		  localeNew=new Locale("en","US");  
	    	}
	    }
		ResourceBundle mes =ResourceBundle.getBundle("com.symbio.utils.MessagesBundle", localeNew);  
		return mes.getString(key);
	}

	
}

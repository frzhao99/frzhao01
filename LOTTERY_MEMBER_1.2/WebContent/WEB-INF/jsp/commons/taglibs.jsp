<%@page import="java.util.ResourceBundle"%>
<%@page import="com.symbio.utils.WebUtils"%>
<%@page import="java.util.Locale"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.awt.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% 
	
 	request.setAttribute("ctx", request.getContextPath());
    Object ch_language = request.getSession().getAttribute("language");
    
    Locale localeNew = null;
    if(ch_language != null){
    	if(ch_language.equals("zh")){
    		  localeNew=new Locale("zh","CN");  
    	}else if(ch_language.equals("en")){
    		  localeNew=new Locale("en","US");  
    	}
    }else{
    	//localeNew=Locale.getDefault(); 
    	localeNew=new Locale("zh","CN");  
    }
    ResourceBundle mes =ResourceBundle.getBundle("com.symbio.utils.MessagesBundle", localeNew);  
   	request.setAttribute("mes", mes);
   	//request.getSession().setAttribute("mes", mes);
   	System.out.println(mes.toString());
	
%>


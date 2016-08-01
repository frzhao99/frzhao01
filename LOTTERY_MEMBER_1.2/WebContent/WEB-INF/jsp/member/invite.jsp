<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head> 
    <title>邀请好友</title>
    <meta charset="utf-8" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <link href="${ctx}/resources/css/public.css" rel="stylesheet">
    <link href="${ctx}/resources/css/button.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script> 
    <script type="text/javascript" src="${ctx}/resources/js/main.js"></script> 
</head>
<!-- <i class="typcn typcn-chart-bar"></i> <button class="button button-primary">按钮</button>-->
    <body>
        <jsp:include page="../commons/header.jsp"></jsp:include>
        <div class="content">
            <jsp:include page="../commons/left.jsp">
            	<jsp:param value="member" name="menu"/>
            	<jsp:param value="member-invite" name="submenu"/>
            </jsp:include>
            <div class="right-box">
                <div class="right-main">
                    <div class="index-header">
                        <p class="index-p1 left" >My Wallet <b>Be your own bank.®</b></p><p class="index-p1 right">0 BTC</p>
                        <div class="clear" style='height:10px'></div>
                        <p class="index-p1 left">
                            <button class="button button-primary button-3d button-raised  button-longshadow button-small">发送</button>
                            <button class="button  button-3d button-raised  button-longshadow button-small">接受</button>
                        </p>
                        <p class="index-p2 right">￥0.00</p>
                        <div class="clear" style='height:30px'></div>
                    </div>
                    <div style='height:30px;'></div>
                    <div class="tipbox1 color1">
                        你可以将该链接或者二维码发送给朋友，邀请朋友访问我们的社区，并得到应有的回报。
                    </div>
                    
                    <h3 class='title3'><i class="typcn typcn-attachment"></i>推广链接</h3>
                    <p class='fzljp'><a href="#" target="_blank">${requestUrl}</a><span class='btn2'>复制链接</span></p>
                    <h3 class='title3'><i class="typcn typcn-image-outline"></i>推广二维码</h3>
                    <div style='padding-top:20px;'>
                        <img src="${ctx}/resources/shear_code/frzhao88.gif" width='100' />
                        <p >扫描二维码前往注册</p>
                    </div>
                   
                </div>
            </div>
        </div>
        <!-- 隐藏区 -->
        <div id="browseBox" class="browseBox" > 
            <h1>Notification</h1>
            <ul>
                <li>New user registered</li>
                <li>New setting</li>
                <li>Updates</li>
                <li>Other</li>
            </ul>
        </div>  
    </body>
</html>

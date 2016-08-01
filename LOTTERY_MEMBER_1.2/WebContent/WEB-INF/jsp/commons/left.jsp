<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<%
	String menu = request.getParameter("menu");
	request.setAttribute("menu", menu);
	String submenu = request.getParameter("submenu");
	request.setAttribute("submenu", submenu);
%>
<div class="left-box">
	<div class="left-nav">
		<dl>
			<dt>
				<i class="typcn typcn-home-outline"></i><a href="index.html">主页</a>
			</dt>
		</dl>
	<!-- 	<dl>
			<dt>
				<i class="typcn typcn-credit-3"></i>交易中心
			</dt>
			<dd>
				<a href="trade-sell.html">卖游戏币</a>
			</dd>
			<dd>
				<a href="trade-buy.html">买游戏币</a>
			</dd>
			<dd>
				<a href="trade-records.html">交易记录</a>
			</dd>
			<dd>
				<a href="credit-records.html">信用记录</a>
			</dd>
		</dl> -->
	<c:choose>
				<c:when test="${menu == 'currency'}">
				<dl class="active">
				</c:when>
					
				<c:otherwise>
				   <dl>
				</c:otherwise>
			</c:choose>
			<dt>
				<i class="typcn typcn-credit-3"></i>货币兑换
			</dt>
			<c:choose>
				<c:when test="${submenu == 'currency-index'}">
				 	<dd class="item-active">
				</c:when>
				
				<c:otherwise>
				    <dd>
				</c:otherwise>
			 </c:choose>
				<a href="${ctx}/currency/usd-LEV">USD/LEV</a>
			</dd>
			<dd>
				<a href="trade-buy.html">LEV/USD</a>
			</dd>
			<dd>
				<a href="trade-records.html">兑换记录</a>
			</dd>
			<c:choose>
				<c:when test="${submenu == 'currency-mycredit'}">
				 	<dd class="item-active">
				</c:when>
				
				<c:otherwise>
				    <dd>
				</c:otherwise>
			 </c:choose>
				<a href="${ctx}/currency/my-currency">我的信誉记录</a>
			</dd>
			
		</dl>
		<c:choose>
				<c:when test="${menu == 'member'}">
				<dl class="active">
				</c:when>
					
				<c:otherwise>
				   <dl>
				</c:otherwise>
			</c:choose>
			<dt>
				<i class="typcn typcn-credit-2"></i>业务管理
			</dt>
			 <c:choose>
				<c:when test="${submenu == 'member-invite'}">
				 	<dd class="item-active">
				</c:when>
				
				<c:otherwise>
				    <dd>
				</c:otherwise>
			 </c:choose>
				<a href="${ctx}/account/invite">推广链接</a>
			</dd>
			 <c:choose>
				<c:when test="${submenu == 'member-inviteList'}">
				 	<dd class="item-active">
				</c:when>
				
				<c:otherwise>
				    <dd>
				</c:otherwise>
			 </c:choose>
				<a href="${ctx}/account/invite-list">推广记录</a>
			</dd>
			
		</dl>
		<dl>
			<dt>
				<i class="typcn typcn-credit-1"></i>财务管理
			</dt>
			<dd>
				<a href="award.html">每日奖金</a>
			</dd>
			<dd>
				<a href="registration-code-transfer.html">注册码转账</a>
			</dd>
			<dd>
				<a href="activatron-code-transfer.html">激活码转账</a>
			</dd>
		</dl>
		
			<c:choose>
				<c:when test="${menu == 'personal'}">
				<dl class="active">
				</c:when>
					
				<c:otherwise>
				   <dl>
				</c:otherwise>
			</c:choose>
			<dt>
				<i class="typcn typcn-lock-closed"></i>安全中心
			</dt>
			
				
			 <c:choose>
				<c:when test="${submenu == 'personal-index'}">
				 	<dd class="item-active">
				</c:when>
				
				<c:otherwise>
				    <dd>
				</c:otherwise>
			 </c:choose>
			   <a href="${ctx}/account/person-info">首选项</a>
			</dd>
			<c:choose>
				<c:when test="${submenu == 'personal-security'}">
				 	<dd class="item-active">
				</c:when>
				
				<c:otherwise>
				    <dd>
				</c:otherwise>
			 </c:choose>
				<a href="${ctx}/account/security">安全性</a>
			</dd>
		</dl>
		<dl>
			<dt>
				<i class="typcn typcn-phone-outline"></i>联系支持
			</dt>
		</dl>
	</div>
	<div class="zzs">
		<h1>赞助商链接</h1>
		<img src="${ctx}/resources/css/img/zzs.png" width="230px" />
	</div>
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>部门业绩</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<link href="${ctx}/resources/css/public.css" rel="stylesheet">
<link href="${ctx}/resources/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/main.js"></script>

</head>
<!-- <i class="typcn typcn-chart-bar"></i> <button class="button button-primary">按钮</button>-->
<jsp:include page="../commons/header.jsp"></jsp:include>
<div class="content">
	<jsp:include page="../commons/left.jsp">
		<jsp:param value="member" name="menu" />
		<jsp:param value="member-inviteList" name="submenu" />
	</jsp:include>
	<div class="right-box">
		<div class="right-main">
			<div class="index-header">
				<p class="index-p1 left">
					My Wallet <b>Be your own bank.®</b>
				</p>
				<p class="index-p1 right">0 BTC</p>
				<div class="clear" style='height: 10px'></div>
				<p class="index-p1 left">
					<button
						class="button button-primary button-3d button-raised  button-longshadow button-small">发送</button>
					<button
						class="button  button-3d button-raised  button-longshadow button-small">接受</button>
				</p>
				<p class="index-p2 right">￥0.00</p>
				<div class="clear" style='height: 30px'></div>
			</div>
			<div style='height: 30px;'></div>
			<div class="tipbox1 color1">每日新增业绩会在次日显示，每日业绩会在凌晨后结算。</div>
		</div>



		<div class="right-main">
			<table class="table1">
				<tr>
					<th>序号</th>
					<th>部门名称</th>
					<th>业绩</th>
				</tr>
				<c:forEach varStatus="xh" items="${pageData.result}" var="member">
					<tr>
						<td>${xh.count}</td>
						<td><c:choose>
								<c:when test="${member.registerLocation == 1}">
							 【一】部门
						</c:when>
						<c:when test="${member.registerLocation == 2}">
							 【二】部门
						</c:when>
						<c:otherwise>
                             	会员【<span style="color:red;font-size: larger;">${member.mbName}】</span>未派遣，<a href="${ctx}/account/invite-list">点击前往派遣</a>
                            </c:otherwise>
					    </c:choose></td>
						
						<td><fmt:formatNumber value="${member.achievement}"
								pattern="##.##" minFractionDigits="2"></fmt:formatNumber> EV</td>
					</tr>
				</c:forEach>
				<c:if test="${fn:length(pageData.result) == 0}">
					<tr>
						<td colspan="3"><span style="color: blue; font-weight: bold;">无数据</span></td>
					</tr>
				</c:if>

			</table>
		</div>
	</div>
</div>
<!-- 隐藏区 -->
<div id="browseBox" class="browseBox">
	<h1>Notification</h1>
	<ul>
		<li>New user registered</li>
		<li>New setting</li>
		<li>Updates</li>
		<li>Other</li>
	</ul>
</div>
<script type="text/javascript">
	
</script>
</body>
</html>


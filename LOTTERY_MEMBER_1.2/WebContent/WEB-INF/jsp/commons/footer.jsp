<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<script type="text/javascript">

</script>
<div class="new-footer">
	<div class="mainCenter">
		<div class="footer-language">
			<a href="javascript:;" onclick="selectLanguage('zh')">中文(简体)</a>
			<a  href="javascript:;" onclick="selectLanguage('en')">English(US)</a>
		</div>
		<div class="friendship-box">
			<ul>
				<li><a style=""
					href="${ctx}/service/about?id=1"><%=mes.getString("about") %></a></li>
				<li><a style=""
					href="${ctx}/service/about?id=2"><%=mes.getString("contact") %></a></li>
				<li><a style=""
					href="${ctx}/service/about?id=3"><%=mes.getString("questions") %></a></li>
				<li><a style=""
					href="${ctx}/service/about?id=4"><%=mes.getString("poundage") %></a></li>
				<li><a style=""
					href="${ctx}/service/about?id=6"><%=mes.getString("clause") %></a> </li>
				<li><a style=""
					href="${ctx}/service/about?id=8">BTC提款教程</a> </li>
				
			</ul>
		</div>
		<div class="copyright">
			CopyRight © 2015-2018 BMFINN.COM All Rights Reserved. <br>
			<c:choose>
				<c:when test="${language == 'en'}">
					<a href="#">English(US)</a>
				</c:when>
				<c:otherwise>
					<a href="#">中文(简体)</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
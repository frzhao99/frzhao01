<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>推广记录</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<link href="${ctx}/resources/css/public.css" rel="stylesheet">
<link href="${ctx}/resources/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/main.js"></script>
<link href="${ctx}/resources/css/sweetalert2.min.css" rel="stylesheet">
<script type="text/javascript"
	src="${ctx}/resources/js/sweetalert2.min.js"></script>
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
			<div class="tipbox1 color1">
				请熟知：每一个账户您有一次设置部门的操作，请准确操作，这将影响到你的分享奖金（调整后将会永久保持该关系，社区不会为您特殊处理）。</div>
		</div>



		<div class="right-main">
			<table class="table1">
				<tr>
					<th>社区账户</th>
					<th>注册时间</th>
					<th>邮箱</th>
					<th>联系电话</th>
					<th>部门</th>
					<th>操作</th>
				</tr>
				<c:forEach varStatus="xh" items="${pageData.result}" var="member">
					<tr>
						<td>${member.mbName}</td>
						<td><fmt:formatDate value="${member.rtime}"
								pattern="yyyy-MM-dd HH:mm" /></td>
						<td>${member.email}</td>
						<td><c:choose>
								<c:when test="${member.phone == null or member.phone == ''}">
                             		未验证
                             	</c:when>
								<c:otherwise>
                             	   ${member.phone}
                             	</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${member.isBus == 0}">
                             		未派遣
                             	</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${member.registerLocation == 1}">
							一部门
						</c:when>
										<c:when test="${member.registerLocation == 2}">
							二部门
						</c:when>
									</c:choose>
								</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${member.isBus == 0}">
									<select id="optDep" title="${member.mbName}"
										class='input smallselect'>
										<option value="0">选择部门</option>
										<option value="1">派遣到一部门</option>
										<option value="2">派遣到二部门</option>
									</select>
								</c:when>
								<c:otherwise>
                             	     无操作
                             	</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
				<c:if test="${fn:length(pageData.result) == 0}">
					<tr>
						<td colspan="6"><span style="color: blue; font-weight: bold;">无数据</span></td>
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
	$(function() {
		$("#optDep")
				.change(
						function(obj) {
							var selectV = $(this).val();
							var mbName = $(this).attr("title");
                           
                            var sv = 0;
                            if(selectV == 1){
                            	sv = "一"
                            }else if(selectV == 2){
                            	sv ="二"
                            }else{
                            	return;
                            }
                           
							swal(
									{
										title : '你确定吗?',
										text : "你将社区账户[" + mbName + "]派遣到"
												+ sv + "部门!",
										type : 'warning',
										showCancelButton : true,

										confirmButtonText : '对, 派遣!',
										cancelButtonText : '不, 取消!',

									})
									.then(
											function(isConfirm) {
												if (isConfirm === true) {

													var url = "${ctx}/account/dispatch?timestap="
															+ (new Date())
																	.valueOf();
													$
															.ajax({
																type : "post",
																async : false,
																url : url,
																dataType : "json",
																data : {
																	mbName : mbName,
																	dep : selectV
																},
																success : function(
																		getmsg) {
																	if (getmsg.code == 0) {
																		swal({
																			type : 'error',
																			title : '您的操作',
																			html : getmsg.context
																		});
																	} else {
																		swal({
																			type : 'success',
																			title : '您的操作',
																			html : getmsg.context
																		});

																	}

																}
															});

												} else if (isConfirm === false) {
													swal('取消', '你的操作已经被取消',
															'error');
												} else {
													// Esc, close button or outside click
													// isConfirm is undefined
												}
											})

						});
	});
</script>
</body>
</html>


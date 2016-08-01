<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<link href="${ctx}/resources/css/public.css" rel="stylesheet">
<link href="${ctx}/resources/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/main.js"></script>
<script>
	setTimeout(function() {
		$(".base").animate({
			"top" : "-100%"
		}, 700, function() {
			$(".login-load").fadeOut(300);
		});

	}, 2000);
	$(function() {
		$(".login-1").find("span").each(function(i) {
			$(this).click(function() {
				$(this).addClass("active").siblings().removeClass("active");
				$(".login-2 > div").hide();
				$(".hidden" + i).show();

			});
		});
		
		var isLogin = "${nick}";
		if(isLogin != ''){
			$("#signup").click();
			$("#puserName").val(isLogin).hide();
			
		}
	});
	
	function UserCheck(obj) {
		var username = obj.value;
		var s = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
		if (username.match(s) == null) {
			$("#tip_username").addClass("err").removeClass("su");
			$("#tip_username").text("只能输入5-20个字母");
		} else {
			$("#tip_username").addClass("su").removeClass("err");
			$("#tip_username").text("用户名可以使用");
		}
	}

	function PUserCheck(obj) {
		var username = obj.value;
		var s = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
		if (username.match(s) == null) {
			$("#tip_pusername").addClass("err").removeClass("su");
			$("#tip_pusername").text("分享用户名输入不符合标准");
		} else {
			$("#tip_pusername").addClass("su").removeClass("err");
			$("#tip_pusername").text("分享用户名可以使用");
		}
	}

	function UserPass(obj) {
		var username = obj.value;
		if (username.length < 6) {

			$("#tip_password").addClass("err").removeClass("su");
			$("#tip_password").text("密码长度最少6位");
		} else {

			$("#tip_password").addClass("su").removeClass("err");
			$("#tip_password").text("密码可以使用");
		}
	}

	function User2Pass(obj) {
		var username = obj.value;
		if (username.length < 6) {

			$("#tip_2password").addClass("err").removeClass("su");
			$("#tip_2password").text("密码长度最少6位");
		} else {
			$("#tip_2password").addClass("su").removeClass("err");
			$("#tip_2password").text("密码可以使用");
		}
	}

	function UserEmail(obj) {
		var username = obj.value;
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if (username.match(reg) == null) {
			$("#tip_email").addClass("err").removeClass("su");
			$("#tip_email").text("请输入正确的邮箱");
		} else {
			$("#tip_email").addClass("su").removeClass("err");
			$("#tip_email").text("邮箱可以使用");
		}
	}

	function regaction() {
		var url = "${ctx}/account/regAction?timestap=" + (new Date()).valueOf();
		$.ajax({
			type : "post",
			async : false,
			url : url,
			dataType : "json",
			data : $("#reg_form").serialize(),
			success : function(getmsg) {
				if (getmsg.code == 0) {
                   
                    $("#tip_all").addClass("su").removeClass("err");
        			$("#tip_all").text(getmsg.context);
				} else {
					alert(getmsg.context);
					window.location.href = "${ctx}/member/saleList?timestap="
							+ (new Date()).getTime();
				}

			}
		});
	}
	
	function loginAction(){
		var url = "${ctx}/loginAction?timestap=" + (new Date()).valueOf();
		$.ajax({
			type : "post",
			async : false,
			url : url,
			dataType : "json",
			data : $("#login_form").serialize(),
			success : function(getmsg) {
				if (getmsg.code == 0) {
                   
                    $("#tip_login_all").addClass("su").removeClass("err");
        			$("#tip_login_all").text(getmsg.context);
				} else {
					
					window.location.href = "${ctx}/account/person-info?timestap="
							+ (new Date()).getTime();
				}

			}
		});
	}
</script>
<style>
html {
	height: 100%;
}

body {
	background-color: #049bd4;
	font-size: 12px;
	height: 100%;
}

.base {
	height: 9em;
	left: 50%;
	margin: -7.5em;
	padding: 3em;
	position: absolute;
	top: 50%;
	width: 9em;
	transform: rotateX(45deg) rotateZ(45deg);
	transform-style: preserve-3d;
}

.cube, .cube:after, .cube:before {
	content: '';
	float: left;
	height: 3em;
	position: absolute;
	width: 3em;
}

/* Top */
.cube {
	background-color: #e7e8e9;
	position: relative;
	transform: translateZ(3em);
	transform-style: preserve-3d;
	transition: .25s;
	box-shadow: 13em 13em 1.5em rgba(0, 0, 0, .2);
	animation: anim 1s infinite;
}

.cube:after {
	background-color: #d1d1d1;
	transform: rotateX(-90deg) translateY(3em);
	transform-origin: 100% 100%;
}

.cube:before {
	background-color: #c2c2c2;
	transform: rotateY(90deg) translateX(3em);
	transform-origin: 100% 0;
}

.cube:nth-child(1) {
	animation-delay: 0.05s;
}

.cube:nth-child(2) {
	animation-delay: 0.1s;
}

.cube:nth-child(3) {
	animation-delay: 0.15s;
}

.cube:nth-child(4) {
	animation-delay: 0.2s;
}

.cube:nth-child(5) {
	animation-delay: 0.25s;
}

.cube:nth-child(6) {
	animation-delay: 0.3s;
}

.cube:nth-child(7) {
	animation-delay: 0.35s;
}

.cube:nth-child(8) {
	animation-delay: 0.4s;
}

.cube:nth-child(9) {
	animation-delay: 0.45s;
}

@
keyframes anim { 50% {
	transform: translateZ(0.5em);
}

}
.login-load {
	width: 100%;
	height: 100%;
	position: absolute;
	left: 0;
	background: #049bd4;
	top: 0;
	z-index: 999;
}

.login-box {
	position: relative;
	width: 1100px;
	margin: 0 auto;
}

.login-message {
	width: 690px;
	height: 500px;
	background: rgba(0, 0, 0, .4);
	position: absolute;
	left: 0;
}

.login {
	width: 340px; background : rgba( 0, 0, 0, .4);
	position: absolute;
	right: 0;
	background: rgba(0, 0, 0, .4);
}

.login-1 {
	height: 50px;
	display: flex;
	flex-flow: row;
}

.login-1  span {
	flex: 1;
	text-align: center;
	line-height: 50px;
	color: #ccc;
	font-size: 20px;
	cursor: pointer;
	background: rgba(0, 0, 0, .4);
}

.login-1  span.active {
	color: #fff;
	background: rgba(0, 0, 0, 0);
}

.login-message {
	padding: 20px;
	color: #ddd;
}

.login-message>h1 {
	font-size: 23px;
	color: #fff;
	border-bottom: 1px solid rgba(255, 255, 255, .3);
}

.login-message>p {
	text-align: right;
	padding: 10px 0;
	color: #bbb;
}

.login-message>b {
	font-size: 14px;
	color: #aaa;
	font-weight: normal;
}

.ul-box {
	height: 400px;
	overflow-y: auto;
}

.login-message   ul {
	padding-left: 20px;
}

.login-message   ul li {
	font-size: 13px;
	list-style-type: disc;
	border-bottom: 1px solid rgba(200, 200, 200, .3);
	padding: 15px 12px 5px 0;
}

.login-message  ul li a {
	float: right;
}

.login-area {
	padding: 30px;
}

.login-area input, .reg-area input {
	width: 100%;
	height: 30px;
	outline: none;
	border: 1px solid #ddd;
	margin-bottom: 25px;
	padding: 5px;
	border-radius: 1px;
	font-size: 14px;
	color: #666;
}

.reg-area input {
	margin-bottom: 0;
}

.reg-area p {
	
}

.reg-area p.err {
	margin: 3px 0;
	text-align: left !important;
	color: #e21;
	width: 100%;
	padding: 1px 7px 1px 5px !important;
	background: rgba(235, 186, 185, .7)
}

.reg-area p.su {
	margin: 3px 0;
	text-align: left !important;
	color: #2e1;
	width: 100%;
	padding: 1px 7px 1px 5px !important;
	background: rgba(22, 211, 11, .4);
}

.btn {
	width: 100%;
	text-align: center;
	display: block;
	height: 30px;
	background: #29a7e1;
	color: #fff;
	font-size: 14px;
	line-height: 30px;
	padding: 5px;
	cursor: pointer;
}

.login-main, .login-box {
	height: 100%;
}

.login-2 p {
	text-align: center;
	padding: 10px 0;
}

.login-2 p a {
	color: #eee;
	font-size: 13px;
	margin: 0 10px;
}

.reg-area {
	display: none;
	overflow: hidden;
	height: 470px;
	padding:10px 40px 20px 40px;
}

.reg-area>.p1 {
	color: #fff;
	padding: 20px;
	background: #d1def1;
	border-radius: 10px;
	margin-bottom: 20px;
	color: #333;
}

.reg-area>.p2 {
	text-align: left;
	color: #fff;
}

.header {
	background: #1C4875;
	color: #049bd4;
}

.info-t1 {
	color: #fff;
}

#canvas {
	width: 100%;
	height: 100%;
	overflow: hidden;
	position: absolute;
	top: 0;
	left: 0;
	background-color: #1a1724;
}

.canvas-wrap {
	
	 height: calc(100% - 100px);
	position: relative;
}

div.canvas-content {
	position: relative;
	z-index: 2000;
	color: #fff;
	text-align: center;
	padding-top: 30px;
}

.footer {
	height: 55px;
	color: #fff;
}

.f-left {
	float: left;
	line-height: 55px;
}

.f-right {
	float: right;
	padding: 15px 0;
	text-align: right;
}

.f-right a {
	color: #eee;
	margin: 0 0px 0 20px;
	font-size: 13px;
	padding: 10px 0;
}

.f-r-1, .f-r-2 {
	padding-bottom: 10px;
}

.f-r-2 a {
	color: #bbb;
}

a.white {
	color: #2ff;
}
/*style="display:none"*/
</style>
<link rel="stylesheet" type="text/css"
	href="${ctx}/resources/dis/css/default.css">
</head>
<body>

	<div class="login-load">
		<div class='base'>
			<div class='cube'></div>
			<div class='cube'></div>
			<div class='cube'></div>
			<div class='cube'></div>
			<div class='cube'></div>
			<div class='cube'></div>
			<div class='cube'></div>
			<div class='cube'></div>
			<div class='cube'></div>
		</div>
	</div>
	<div class="login-main">

		<div class="header">
			<div class="header-logo">
				<img src="${ctx}/resources/css/img/main-logo.png" class="logo" />
			</div>
			<div class="header-right-info">
				<b class="info-t1"></b>
			</div>
		</div>
		<div class="canvas-wrap">
			<div id="canvas" class="gradient"></div>
			<div class="login-box">
				<div style="height: 40px;"></div>
				<div class="login-message">
					<h1>系统公告</h1>
					<p>2016年07月05日</p>
					<b>雷达系统交易网站近期新功能更新:</b>
					<div class="ul-box">
						<ul>
							<li>更新开发者社区的RPC和总账查看工具<a href="">查看</a></li>
							<li>已成为全球数据量最大的区块链<a href="">查看</a></li>
							<li>对雷达钱包进行了安全升级<a href="">查看</a></li>
							<li>专业化的比特币、VBC交易支持<a href="">查看</a></li>
							<li>雷达钱包安卓客户端上线<a href="">查看</a></li>
							<li>“雷达跨币汇款”新产品开始内测<a href="">查看</a></li>
							<li>交易所手机端Web页面新版更新<a href="">查看</a></li>
							<li>雷达与大唐达成合作意向<a href="">查看</a></li>
							<li>更新开发者社区的RPC和总账查看工具<a href="">查看</a></li>
							<li>已成为全球数据量最大的区块链<a href="">查看</a></li>
							<li>对雷达钱包进行了安全升级<a href="">查看</a></li>
							<li>专业化的比特币、VBC交易支持<a href="">查看</a></li>
							<li>雷达钱包安卓客户端上线<a href="">查看</a></li>
							<li>“雷达跨币汇款”新产品开始内测<a href="">查看</a></li>
							<li>交易所手机端Web页面新版更新<a href="">查看</a></li>
							<li>雷达与大唐达成合作意向<a href="">查看</a></li>
						</ul>
					</div>
				</div>

				<div class="login">
					<div class="login-1">
						<span class="active">登录</span> <span id="signup">注册</span>
					</div>
					<div class="login-2">
						<div class="login-area hidden0">
						   <form action=""  id="login_form">
						    <p id="tip_login_all"></p>
							<input name="loginUsername" type="text" placeholder="用户名" /> <input name="loginPassword" type="text"
								placeholder="密码" /> <span class="btn" onclick="loginAction();">登录</span>
							<p>
								<a>恢复账户</a><a>忘记密码？</a>
							</p>
						  </form>
						</div>
						<div class="reg-area hidden1">
						  <form action=""  id="reg_form">
						    <p id="tip_all"></p>
							<p class="p1">为了您的账号安全，请您不要对外透漏自己的邮箱地址，并且确保邮箱密码和登录密码、交易密码不同。
							</p>
							<input id="puserName" name="puserName" onkeyup="PUserCheck(this)"   type="text"
								placeholder="分享用户名" />
							<p id="tip_pusername"></p>
							<input name="username" onkeyup="UserCheck(this)" type="text"
								placeholder="用户名" />
							<p id="tip_username"></p>
							<input name="password" onkeyup="UserPass(this)" type="password"
								placeholder="密码" />
							<p id="tip_password"></p>
							<input name="tradPassword" onkeyup="User2Pass(this)"
								type="password" placeholder="交易密码" />
							<p id="tip_2password"></p>
							<input name="email" onkeyup="UserEmail(this)" type="text"
								placeholder="邮箱" />
							<p id="tip_email"></p>

							<span class="btn" onclick="regaction();">注册</span>
							</form>
						</div>
					</div>
				</div>

			</div>

			<div class="footer">
				<div style="width: 1100px; margin: 0 auto">
					<div class="f-left">
						<p>版本：1.0.1 LIGHTN&nbsp;&nbsp;|Copyright © 2016-2024,
							LIGHTNLAB.COM, All Rights Reserved</p>
					</div>
					<div class="f-right">
						<div class="f-r-1">
							<a href="">首页</a><a href="">闪电实验室百科</a><a href="">实时行情</a><a
								href="">新闻中心</a>
						</div>
						<div class="f-r-2">
							<a href="">帮助</a><a href="">服务协议</a><a href="">在线客服</a><a>邮件反馈</a><a
								href="">中文</a><a class="white" href="">English</a>
						</div>
						<div>
							<p>Lightnlab.com ©2016</p>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>




</body>
<script src="${ctx}/resources/js/dis/three.min.js"></script>
<script src="${ctx}/resources/js/dis/projector.js"></script>
<script src="${ctx}/resources/js/dis/canvas-renderer.js"></script>
<script src="${ctx}/resources/js/dis/3d-lines-animation.js"></script>
<script src="${ctx}/resources/js/dis/color.js"></script>



</html>
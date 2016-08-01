<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head> 
    <title>安全性</title>
    <meta charset="utf-8" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <link href="${ctx}/resources/css/public.css" rel="stylesheet">
    <link href="${ctx}/resources/css/button.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script> 
    <script type="text/javascript" src="${ctx}/resources/js/main.js"></script> 
    <link href="${ctx}/resources/css/sweetalert2.min.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/sweetalert2.min.js"></script> 
</head>

    <body>
        <jsp:include page="../commons/header.jsp"></jsp:include>
        <div class="content">
            <jsp:include page="../commons/left.jsp">
            	<jsp:param value="personal" name="menu"/>
            	<jsp:param value="personal-security" name="submenu"/>
            </jsp:include>
            <script>
                 function changeSort() {
                    swal({
                        title: '备份短语',
                        input: 'text',
                        showCancelButton: true,
                        confirmButtonText: 'Submit',
                        showLoaderOnConfirm: true,
                        preConfirm: function() {
                        return new Promise(function(resolve) {
                          setTimeout(function() {
                            resolve();
                          }, 2000);
                        });
                        },
                        allowOutsideClick: false
                        }).then(function(email) {
                        if (email) {
                        swal({
                          type: 'success',
                          title: 'Ajax request finished!',
                          html: 'Submitted email: ' + email
                        });
                        }
                    });
                }

                function changePassWd() {
                    swal({
                        title: '重置登录密码',
                        html:'<div class="changePhone"> '+
                        '<p><label>旧登录密码：</label><input id="oldpassword" name="oldpassword" type="password" /></p>' + 
                        '<p><label>新登录密码：</label><input id="password" name="password" type="password" /></p>' + 
                        '<p><label>确认新密码：</label><input id="conpassword" name＝"conpassword" type="password" /></p>' + 
                       
                        '</div>' ,
                        showCancelButton: true,
                        preConfirm: function() {
                        return new Promise(function(resolve) {
                            var oldpassword = $("#oldpassword").val();
                            var password = $("#password").val();
                            var conpassword = $("#conpassword").val();
                            if(oldpassword == "" || oldpassword.length < 6){
                            	$(".swal2-validationerror").show().text("旧登录密码输入不符合标准");  
                   			    return;
                            }
                            if(password == "" || password.length < 6){
                            	$(".swal2-validationerror").show().text("新登录密码输入不符合标准");  
                   			    return;
                            }
                            if(conpassword == '' || conpassword.length < 6){
                            	$(".swal2-validationerror").show().text("确认新密码输入不符合标准");  
                   			    return;
                            }
                            if(oldpassword == password){
                            	$(".swal2-validationerror").show().text("重置密码不能与旧登录密码一样");  
                   			    return;
                            }
                            if(password != conpassword){
                            	$(".swal2-validationerror").show().text("确认新密码与新登录密码输入不一样");  
                   			    return;
                            }
                            var url = "${ctx}/account/modify-password?timestap=" + (new Date()).valueOf();
                 			$.ajax({
                 				type : "post",
                 				async : false,
                 				url : url,
                 				dataType : "json",
                 				data :{
                 					oldpassword:oldpassword,
                 					password:password
                                  },
                 				success : function(getmsg) {
                 					if (getmsg.code == 0) {
                 						 swal({
                                              type: 'error',
                                              title: '您的操作',
                                              html: getmsg.context
                                           });
                 					} else {
                 						 swal({
                                              type: 'success',
                                              title: '您的操作',
                                              html: getmsg.context
                                            });
                 						
                 					}

                 				}
                 			});
                            
                        });
                        }
                        });
                }
                function change2PassWd() {
                    swal({
                        title: '重置交易密码',
                        html:'<div class="changePhone"> '+
                        '<p><label>旧交易密码：</label><input id="oldpassword" name="oldpassword" type="password" /></p>' + 
                        '<p><label>新交易密码：</label><input id="password" name="password" type="password" /></p>' + 
                        '<p><label>确认新密码：</label><input id="conpassword" name＝"conpassword" type="password" /></p>' + 
                       
                        '</div>' ,
                        showCancelButton: true,
                        preConfirm: function() {
                        return new Promise(function(resolve) {
                        	var oldpassword = $("#oldpassword").val();
                            var password = $("#password").val();
                            var conpassword = $("#conpassword").val();
                            if(oldpassword == "" || oldpassword.length < 6){
                            	$(".swal2-validationerror").show().text("旧交易密码输入不符合标准");  
                   			    return;
                            }
                            if(password == "" || password.length < 6){
                            	$(".swal2-validationerror").show().text("新交易密码输入不符合标准");  
                   			    return;
                            }
                            if(conpassword == '' || conpassword.length < 6){
                            	$(".swal2-validationerror").show().text("确认新密码输入不符合标准");  
                   			    return;
                            }
                            if(oldpassword == password){
                            	$(".swal2-validationerror").show().text("重置密码不能与旧交易密码一样");  
                   			    return;
                            }
                            if(password != conpassword){
                            	$(".swal2-validationerror").show().text("确认新密码与新交易密码输入不一样");  
                   			    return;
                            }
                            var url = "${ctx}/account/modify-tran-password?timestap=" + (new Date()).valueOf();
                 			$.ajax({
                 				type : "post",
                 				async : false,
                 				url : url,
                 				dataType : "json",
                 				data :{
                 					oldpassword:oldpassword,
                 					password:password
                                  },
                 				success : function(getmsg) {
                 					if (getmsg.code == 0) {
                 						 swal({
                                              type: 'error',
                                              title: '您的操作',
                                              html: getmsg.context
                                           });
                 					} else {
                 						 swal({
                                              type: 'success',
                                              title: '您的操作',
                                              html: getmsg.context
                                            });
                 						
                 					}

                 				}
                 			});
                        });
                        }
                        });
                }

                $(function() {
                    $("#gaojiBtn").toggle(function() {
                        $("#pconfig").fadeIn(300);
                    }, function() {
                        $("#pconfig").fadeOut(300);
                    });
                })
            </script>
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
                    <div class="clear" style='height:30px'></div>
                    <div class="tipbox1">基本安全： 确保您输入的信息正确并牢记，以防止您的账户遭受未经授权的访问，并在丢失账户ID或密码的情况下帮助您恢复账户的访问权限。</div>
                    <div class="clear" style='height:10px'></div>
                    <div>
                        <div class="per-item">
                            <div class="per-item-1">
                                <b class="per-1-l">钱包恢复短语 <span class="c2">未确认</span></b>
                                <b class="per-1-r"></b>
                            </div>
                            <div class="per-item-2">
                                <p>恢复短语可在您丢失密码或丢失 Blockchain 服务的情况下用于还原您的所有资金。请注意，恢复短语永远不会更改，并且能够恢复此钱包内您现有的所有比特币以及最新接收的资金。请注意，导入地址无法通过钱包恢复短语进行备份。我们强烈建议您将导入地址中的资金转入此钱包。</p>
                                <span class="btn2" onclick='changeSort()'>备份短语</span>
                            </div>
                             <div class="clear"></div>
                        </div>
                        <div class="per-item">
                            <div class="per-item-1">
                                <b class="per-1-l">账户密码 <span class="c1">登录密码</span></b>
                                <b class="per-1-r"></b>
                            </div>
                            <div class="per-item-2">
                                <p>您的密码不会在我们的服务器上共享，这意味着如果您忘记了密码，我们将无法帮助您重置。请记录下您的恢复短语，这将帮助您在丢失密码的情况下恢复钱包的访问权限。</p>
                                <span class="btn2" onclick='changePassWd()'>更改</span>
                            </div>
                             <div class="clear"></div>
                        </div>
                        <div class="per-item">
                            <div class="per-item-1">
                                <b class="per-1-l">账户二级密码 <span class="c1">交易密码</span></b>
                                <b class="per-1-r"></b>
                            </div>
                            <div class="per-item-2">
                                <p>为了提高安全性，在您账户的重要操作需要输入交易密码。请记录下您的恢复短语，这将帮助您在丢失密码的情况下恢复账户的操作权限。</p>
                                <span class="btn2" onclick='change2PassWd()'>更改</span>
                            </div>
                             <div class="clear"></div>
                        </div>

                    </div>
                    <div class="clear" style='height:15px'></div>
                   
                    <div class="clear" style='height:15px'></div>
                    <div class="hidden" id="pconfig">
                        <div class="per-item">
                            <div class="per-item-1">
                                <b class="per-1-l">活动记录 <span class="c2">已禁用</span></b>
                                <b class="per-1-r"></b>
                            </div>
                            <div class="per-item-2">
                                <p>记录钱包活动并显示在活动消息中。</p>
                                <span class="btn2">启用</span>
                            </div>
                             <div class="clear"></div>
                        </div>
                        <div class="per-item">
                            <div class="per-item-1">
                                <b class="per-1-l">登录 IP 限制<span class="c2">已禁用</span></b>
                                <b class="per-1-r"></b>
                            </div>
                            <div class="per-item-2">
                                <p>仅允许白名单中的 IP 地址登录。如果没有静态 IP 地址，这有可能导致您无法访问钱包。</p>
                                <span class="btn2">启用</span>
                            </div>
                             <div class="clear"></div>
                        </div>
                        <div class="per-item">
                            <div class="per-item-1">
                                <b class="per-1-l">IP 白名单 <span class="c1">已允许</span></b>
                                <b class="per-1-r"></b>
                            </div>
                            <div class="per-item-2">
                                <p>无需电子邮件验证即允许从以下逗号分隔的 IP 地址列表登录。使用 % 作为通配符。此操作需要验证电子邮件地址。</p>
                                <span class="btn2" onclick='change2PassWd()'>禁止</span>
                            </div>
                             <div class="clear"></div>
                        </div>
                        <div class="per-item">
                            <div class="per-item-1">
                                <b class="per-1-l">通过 Tor 访问钱包<span class="c1">已允许</span></b>
                                <b class="per-1-r"></b>
                            </div>
                            <div class="per-item-2">
                                <p>启用以下选项，可以阻止已知为 Tor 匿名网络一部分的 IP 地址访问您的钱包。Tor 网络经常被尝试访问 Blockchain 用户钱包的黑客使用。</p>
                                <span class="btn2" onclick='change2PassWd()'>禁止</span>
                            </div>
                             <div class="clear"></div>
                        </div>

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


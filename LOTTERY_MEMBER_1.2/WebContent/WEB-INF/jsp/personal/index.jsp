<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head> 
    <title>首选项</title>
    <meta charset="utf-8" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <link href="${ctx}/resources/css/public.css" rel="stylesheet">
    <link href="${ctx}/resources/css/button.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script> 
    <script type="text/javascript" src="${ctx}/resources/js/main.js"></script> 
    <link href="${ctx}/resources/css/sweetalert2.min.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/sweetalert2.min.js"></script> 
   
</head>
<!-- <i class="typcn typcn-chart-bar"></i> <button class="button button-primary">按钮</button>-->
    <body>
        <jsp:include page="../commons/header.jsp"></jsp:include>
        <div class="content">
            <jsp:include page="../commons/left.jsp">
            	<jsp:param value="personal" name="menu"/>
            	<jsp:param value="personal-index" name="submenu"/>
            </jsp:include>
           
            <script type="text/javascript">
                function changeEmail() {
                   
                    swal({
                        title: '邮件地址',
                        input: 'email',
                        showCancelButton: true,
                        confirmButtonText: '确认',
                        cancelButtonText: '取消',
                        inputPlaceholder:'请输入您的邮箱地址',
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
                
                function changePhone() {

                    swal({
                        title: '修改手机号码',
                        confirmButtonText: '确认',
                        cancelButtonText: '取消',
                        showCancelButton: true,
                        html:'<div class="changePhone"> '+
                        '<p><label>国家区号：</label><select><option>中国 +86</option><option>日本 +81</option></select></p>' + 
                        '<p><label>手机号：</label><input id="changePhoneNumber" type="text" /><span class="errortip">手机号码错误</span></p>' + 
                        '<p><label>验证码：</label><input class="small" /><span class="btn2">获取验证码</span></p>' + 
                        '<p><label>交易密码：</label><input /></p>'+
                        '</div>' ,
                       
                        preConfirm: function() {
                        return new Promise(function(resolve) {

                                var number = $("#changePhoneNumber").val();
                                if (!isNaN(number) && number.length != 11) {
                                  // $("#changePhoneNumber").parent().find(".errortip").show();
                                  $(".swal2-validationerror").show().text('手机号码错误！');  
                                }else{
                                    resolve();
                                }

                                // resolve();
                            });
                        }
                     });
                    
                }
                
                function findIdCard(){
                	swal({
                        title: '身份认证信息',
                       
                      
                        showCancelButton: true,
                        html:'<div class="changePhone"> '+
                        '<p><label>证件类型：</label><label>${cerType}</label></p>' + 
                        '<p><label>证件号码：</label><label>${mbInfo.intCerNumber}</label></p>' + 
                        '<p><label>姓名：</label><label>${mbInfo.intName}</label></p>' + 
                        '</div>' ,
                       
                        preConfirm: function() {
                        return new Promise(function(resolve) {

                        	 resolve();
                            });
                        }
                     });
                }
                function changeIdcard() {
                    swal({
                        title: '身份验证',
                        html:'<div class="changePhone"> '+
                        '<p><label>证件类型：</label><select name="cerType" id="cerType"><option value="1">身份证</option><option value="2">护照</option><option value="3">驾照</option></select></p>' + 
                        '<p><label>证件号码：</label><input id="cerNum" name="cerNum" type="text" /><span class="errortip">手机号码错误</span></p>' + 
                        '<p><label>姓名：</label><input name="uname" id="uname" /></p>' + 
                        '<p><label>交易密码：</label><input name="tpassword" id="tpassword" type="password" /></p>'+
                        '</div>' ,
                        showCancelButton: true,
                        confirmButtonText: '确认',
                        cancelButtonText: '取消',
                       
                      
                        preConfirm: function() {
                        return new Promise(function(resolve) {
                        	 var cerNum = $("#cerNum").val();
                        	 if($("#cerType").val() == '1'){
                        		 if(!isIdCardNo(cerNum)){
                        			
                        			 $(".swal2-validationerror").show().text("证件号码输入不合法");  
                        			 return;
                        		 }
                        	 }else{
                        		 var zz = /^[A-Za-z0-9]{6,}$/;
                        		 if (!zz.test(cerNum)) { 
                        			
                        			 $(".swal2-validationerror").show().text("证件号码输入不合法"); 
                        			 return;
                        		 } 
                        	 }
                        	
                        	 var uname = $("#uname").val();
                        	 if(uname == ""){
                        		 $(".swal2-validationerror").show().text("姓名不能为空"); 
                        		 return;
                        	 }
                        	 
                        	 var tpassword = $("#tpassword").val();
                        	 if(tpassword.length < 6){
                        		 $(".swal2-validationerror").show().text("交易密码输入错误"); 
                        		 return;
                        	 }
                        	 $(".swal2-validationerror").hide()
                        		
                        	 var url = "${ctx}/account/autIdentity?timestap=" + (new Date()).valueOf();
	                 			$.ajax({
	                 				type : "post",
	                 				async : false,
	                 				url : url,
	                 				dataType : "json",
	                 				data :{
	                 					uname:$("#uname").val(),
	                 					tpassword:$("#tpassword").val(),
	                 					cerNum:$("#cerNum").val(),
	                 					cerType:$("#cerType").val()
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
                        },
                        allowOutsideClick: false
                        });
                }
                function changeTime() {
                    swal({
                        title: '注销时间',
                        input: 'select',
                        inputOptions: {
                        '10': '10分钟',
                        '60': '60分钟',
                        '0': '永不'
                        },
                        inputPlaceholder: '选择时间',
                        showCancelButton: true,
                        inputValidator: function(value) {
                        return new Promise(function(resolve, reject) {
                          resolve();
                        });
                        }
                        }).then(function(result) {
                        if (result) {
                            swal({
                              type: 'success',
                              html: 'You selected: ' + result
                            });
                        }
                        })
                }
                
                

        		/**
        		    * 验证身份证号码
        		    */
        		   function isIdCardNo(num) {

        			   var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);
        			   var parityBit=new Array("1","0","X","9","8","7","6","5","4","3","2");
        			   var varArray = new Array();
        			   var intValue;
        			   var lngProduct = 0;
        			   var intCheckDigit;
        			   var intStrLen = num.length;
        			   var idNumber = num;
        			     // initialize
        			       if ((intStrLen != 15) && (intStrLen != 18)) {
        			           return false;
        			       }
        			       // check and set value
        			       for(i=0;i<intStrLen;i++) {
        			           varArray[i] = idNumber.charAt(i);
        			           if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
        			               return false;
        			           } else if (i < 17) {
        			               varArray[i] = varArray[i] * factorArr[i];
        			           }
        			       }
        			       
        			       if (intStrLen == 18) {
        			           //check date
        			           var date8 = idNumber.substring(6,14);
        			           if (isDate8(date8) == false) {
        			              return false;
        			           }
        			           // calculate the sum of the products
        			           for(i=0;i<17;i++) {
        			               lngProduct = lngProduct + varArray[i];
        			           }
        			           // calculate the check digit
        			           intCheckDigit = parityBit[lngProduct % 11];
        			           // check last digit
        			           if (varArray[17] != intCheckDigit) {
        			               return false;
        			           }
        			       }
        			       else{        //length is 15
        			           //check date
        			           var date6 = idNumber.substring(6,12);
        			           if (isDate6(date6) == false) {

        			               return false;
        			           }
        			       }
        			       return true;
        			       
        			   }
        		   
        		   function isDate6(sDate) {
        			   if(!/^[0-9]{6}$/.test(sDate)) {
        			      return false;
        			   }
        			   var year, month, day;
        			   year = sDate.substring(0, 4);
        			   month = sDate.substring(4, 6);
        			   if (year < 1700 || year > 2500) return false
        			   if (month < 1 || month > 12) return false
        			   return true
        			}
        			/**
        			 * �ж��Ƿ�Ϊ��YYYYMMDD��ʽ��ʱ��
        			 *
        			 */
        			function isDate8(sDate) {
        			   if(!/^[0-9]{8}$/.test(sDate)) {
        			      return false;
        			   }
        			   var year, month, day;
        			   year = sDate.substring(0, 4);
        			   month = sDate.substring(4, 6);
        			   day = sDate.substring(6, 8);
        			   var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31]
        			   if (year < 1700 || year > 2500) return false
        			   if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;
        			   if (month < 1 || month > 12) return false
        			   if (day < 1 || day > iaMonthDays[month - 1]) return false
        			   return true
        			} 
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
                    <div class="tipbox1">
                        您的信息完整度：
                              <c:choose>
                             	<c:when test="${mbInfo.name == null or mbInfo.name == ''}">
                             		<span class='jdt '><b>身份未验证</b></span> 
                             	</c:when>
                             	<c:otherwise>
                             	   <span class='jdt greenbg'><b>已验证身份</b></span> 
                             	</c:otherwise>
                             </c:choose>
                             <c:choose>
                             	<c:when test="${mbInfo.phone == null or mbInfo.phone == ''}">
                             		<span class='jdt'><b>手机未验证</b></span>
                             	</c:when>
                             	<c:otherwise>
                             	   <span class='jdt greenbg'><b>已验证手机</b></span>
                             	</c:otherwise>
                             </c:choose>
                             <c:choose>
                             	<c:when test="${mbInfo.email == null or mbInfo.email == ''}">
                             		<span class='jdt'><b>邮箱未验证</b></span>
                             	</c:when>
                             	<c:otherwise>
                             	   <span class='jdt greenbg'><b>已验证邮箱</b></span>
                             	</c:otherwise>
                             </c:choose>
                             
                             
                        <br>
                        <span class='level'>
                            <c:choose>
                        		<c:when test="${infok < 2}">
                        			低
                        		</c:when>
                        		<c:when test="${infok == 2}">
                        			中
                        		</c:when>
                        		<c:otherwise>
                        			高
                        		</c:otherwise>
                        	</c:choose>
                        </span>
                    </div>
                    <div class="clear" style='height:10px'></div>
                    <div>
                         <div class="per-item">
                            <div class="per-item-1">
                                <b class="per-1-l">身份认证 <span class="c2">
                               <c:choose>
                             	<c:when test="${mbInfo.name == null or mbInfo.name == ''}">
                             		未验证
                             	</c:when>
                             	<c:otherwise>
                             	   已验证 
                             	</c:otherwise>
                             </c:choose>
                                </span></b>
                                <b class="per-1-r">
                                   <c:choose>
                             	<c:when test="${mbInfo.name == null or mbInfo.name == ''}">
                             		****
                             	</c:when>
                             	<c:otherwise>
                             	   ${mbInfo.intName}
                             	</c:otherwise>
                             </c:choose>
                                </b>
                            </div>
                            <div class="per-item-2">
                                <p>系统将使用您经过验证的身份进行银行卡验证，非常重要，请准确认证，过户手续非常复杂并且会支付一笔10$的手续费，我们不会将您的身份信息用于商业用途。</p>
                              
                                	 <c:choose>
                             	<c:when test="${mbInfo.name == null or mbInfo.name == ''}">
                             		  <span class="btn2" onclick='changeIdcard()'>验证 </span>
                             	</c:when>
                             	<c:otherwise>
                             	     <span class="btn2" onclick='findIdCard()'>查看 </span>
                             	</c:otherwise>
                             </c:choose>
                               
                            </div>
                             <div class="clear"></div>
                        </div>
                        <div class="per-item">
                            <div class="per-item-1">
                                <b class="per-1-l">电子邮件地址 <span class="c1">
                                	 <c:choose>
                             	<c:when test="${mbInfo.email == null or mbInfo.email == ''}">
                             		未验证
                             	</c:when>
                             	<c:otherwise>
                             	      已验证
                             	</c:otherwise>
                             </c:choose>
                                </span></b>
                                <b class="per-1-r">
                                	 <c:choose>
                             	<c:when test="${mbInfo.email == null or mbInfo.email == ''}">
                             		****
                             	</c:when>
                             	<c:otherwise>
                             	    ${mbInfo.intEmail}  
                             	</c:otherwise>
                             </c:choose>
                                </b>
                            </div>
                            <div class="per-item-2">
                                <p>系统将使用您经过验证的电子邮件地址在检测到可疑或不寻常活动时发送登录代码、为您提醒登录，以及在您接收资金时发送支付提醒。我们不会将您的电子邮件地址用于商业用途。</p>
                                <span class="btn2" onclick='changeEmail()'>更改</span>
                            </div>
                             <div class="clear"></div>
                        </div>
                        <div class="per-item">
                            <div class="per-item-1">
                                <b class="per-1-l">手机号码 <span class="c2">
                                   <c:choose>
                             	<c:when test="${mbInfo.phone == null or mbInfo.phone == ''}">
                             		未验证
                             	</c:when>
                             	<c:otherwise>
                             	      已验证
                             	</c:otherwise>
                             </c:choose>
                                </span></b>
                                <b class="per-1-r">
                                	 <c:choose>
                             	<c:when test="${mbInfo.phone == null or mbInfo.phone == ''}">
                             		****
                             	</c:when>
                             	<c:otherwise>
                             	     ${mbInfo.intPhone}    
                             	</c:otherwise>
                             </c:choose>
                                </b>
                            </div>
                            <div class="per-item-2">
                                <p>系统将使用您经过验证的手机号码发送账户支付验证信息，以及在您忘记密码时可通过短信找回密码。我们不会将您的手机号码用于商业用途。</p>
                                <span class="btn2"  onclick='changePhone()'>更改</span>
                            </div>
                             <div class="clear"></div>
                        </div>
                        <div class="per-item">
                            <div class="per-item-1">
                                <b class="per-1-l">钱包语言</b>
                                <b class="per-1-r">
                                    <select>
                                        <option>中文</option>
                                        <option>English</option>
                                    </select>
                                </b>
                            </div>
                            <div class="per-item-2">
                                <p>设置首选语言。</p>
                               
                            </div>
                             <div class="clear"></div>
                        </div>
                        <div class="per-item">
                            <div class="per-item-1">
                                <b class="per-1-l">本地货币</b>
                                <b class="per-1-r">
                                    <select>
                                        <option>人民币</option>
                                        <option>美元</option>
                                        <option>英镑</option>
                                        <option>日元</option>
                                    </select>
                                </b>
                            </div>
                            <div class="per-item-2">
                                <p>选择您的本地货币。</p>
                               
                            </div>
                             <div class="clear"></div>
                        </div>
                        <div class="per-item">
                            <div class="per-item-1">
                                <b class="per-1-l">自动注销</b>
                                <b class="per-1-r">
                                    <select>
                                        <option>10分钟</option>
                                        <option>1小时</option>
                                        <option>1天</option>
                                        <option>总是在线</option>
                                    </select>
                                </b>
                            </div>
                            <div class="per-item-2">
                                <p>一段时间不活动后，您将自动从钱包注销。</p>
                                <span class="btn2" onclick="changeTime()">更改</span>
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
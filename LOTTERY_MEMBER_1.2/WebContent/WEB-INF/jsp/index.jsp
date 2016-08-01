<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head> 
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <link href="${ctx}/resources/css/public.css" rel="stylesheet">
    <link href="${ctx}/resources/css/button.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script> 
    <script type="text/javascript" src="${ctx}/resources/js/main.js"></script> 
</head>
<!-- <i class="typcn typcn-chart-bar"></i> <button class="button button-primary">按钮</button>-->
    <body>
       <jsp:include page="commons/header.jsp"></jsp:include>
        <div class="content">
             <jsp:include page="commons/left.jsp"></jsp:include>
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

                    <div class="index-contant">
                        <div class="index-user">
                            <div class="index-user-img"><img src="${ctx}/resources/css/img/user.gif" /></div>
                            <div class="index-user-info">
                                <p class="index-p3">乐泰</p>
                                <p  class="index-p4">账户等级：<b>普通型</b><span class="btn1">升级</span></p>
                                <p class="index-p4">账户余额：<span class="t1">2222</span>颗</p>
                                <p class="index-p5">(1元 = 100云豆）</p>

                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="index-right">
                            <div class="index-box1">
                                <p class="index-p6">游戏币总量</p>
                                <p class="index-p7"><b>232</b>个</p>
                                <p class="index-p8">交易冻结:<b>1000</b></p>
                                
                            </div>
                            <div class="index-box1">
                                <p  class="index-p6">奖金总数</p>
                                <p  class="index-p7"><b>0</b>个</p>
                                <p class="index-p8">管理奖：0，销售奖：0<br />交易奖：0</p>
                            </div>
                            <div class="index-box1">
                                <p  class="index-p6" >排队人数</p>
                                <p  class="index-p7"><b>120</b>人</p>
                                <p class="index-p8">已排队人数：3 人<br />后面还差：117 人</p>
                            </div>
                        </div>
                    </div>
                    <div class="clear" style="height:50px"></div>
                    <div class="index-contant2">
                        <h1>交易费有何用处？</h1>
                        <p>交易费用于发送由比特币挖矿机网络收集的比特币。为确保交易得到网络确认，我们会根据网络标准自动包含适当的费用。</p>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head> 
    <title>兑换记录</title>
    <meta charset="utf-8" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <link href="${ctx}/resources/css/public.css" rel="stylesheet">
    <link href="${ctx}/resources/css/button.css" rel="stylesheet">
    <link href="${ctx}/resources/css/sweetalert2.min.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script> 
    <script type="text/javascript" src="${ctx}/resources/js/sweetalert2.min.js"></script> 
    <script type="text/javascript" src="${ctx}/resources/js/main.js"></script> 
</head>
<!-- <i class="typcn typcn-chart-bar"></i> <button class="button button-primary">按钮</button>-->
    <body>
        <div class="header">
            <div class="header-logo" >
                <img src="./css/img/main-logo.png" class="logo" />
            </div>
            <div class="header-right-info">
                <b class="info-t1">1 BTC = ￥4918.32</b>
                <span id="browseBtn" class="line1">浏览<i class="typcn typcn-arrow-sorted-down"></i></span>
                <span><i class="typcn typcn-clipboard"></i></span>
                <span>注销</span>
            </div>
        </div>
        <div class="content">
            <div class="left-box">
                <div class="left-nav">
                    <dl>
                        <dt><i class="typcn typcn-home-outline"></i><a href="index.html">主页</a></dt>
                    </dl>
                    <dl class="active">
                        <dt><i class="typcn typcn-credit-3"></i>货币兑换</dt>
                        <dd><a href="trade-buy.html">LEP/USD</a></dd>
                        <dd class="item-active" ><a href="trade-records.html">交易记录</a></dd>
                        <dd><a href="credit-records.html">信用记录</a></dd>
                    </dl>
                    <dl>
                        <dt><i class="typcn typcn-credit-2"></i>业务管理</dt>
                        <dd><a href="register.html">邀请好友</a></dd>
                        <dd><a href="sales-record.html">好友列表</a></dd>
                        
                    </dl>
                    <dl>
                        <dt><i class="typcn typcn-credit-1"></i>钱庄管理</dt>
                        <dd><a href="award.html">LQC交易</a></dd>
                        <dd><a href="registration-code-transfer.html">兑换LQ</a></dd>
                        <dd><a href="activatron-code-transfer.html">LQ管理</a></dd>
                    </dl>
                    <dl>
                        <dt><i class="typcn typcn-lock-closed"></i>安全中心</dt>
                        <dd><a href="person-info.html">首选项</a></dd>
                        <dd><a href="change-passed.html">安全性</a></dd>
                    </dl>
                    <dl>
                        <dt><i class="typcn typcn-phone-outline"></i>联系支持</dt>
                    </dl>
                </div>
                <div class="zzs">
                    <h1>赞助商链接</h1>
                    <img src="./css/img/zzs.png" width="230px" />
                </div>
            </div>
            <div class="right-box">
                    <div class="right-main">
                        <div class="index-header noborder">
                            <p class="index-p1 left" >My Wallet <b>Be your own bank.®</b></p><p class="index-p1 right">0 BTC</p>
                            <div class="clear" style='height:10px'></div>
                            <p class="index-p1 left">
                                <button class="button button-primary button-3d button-raised  button-longshadow button-small">发送</button>
                                <button class="button  button-3d button-raised  button-longshadow button-small">接受</button>
                            </p>
                            <p class="index-p2 right">￥0.00</p>
                            <div class="clear" style='height:10px'></div>
                        </div>
                    </div>
                    <script type="text/javascript">
                                $(function() {
                                    $(".tradebuybtn").find("span").each(function(i) {
                                        $(this).click(function() {
                                            $(this).addClass("active").siblings().removeClass('active');
                                            $(".qzdh").hide();
                                            $(".qzdh"+i).show();

                                        });
                                    });
                                })
                                
                    </script>
                    <div class="table-header tradebuybtn">
                        <span class='active'>USD/LEP记录</span>
                        <span>LEP/USD</span>
                        <span>钱庄兑换</span>
                    </div>

                    <div class="right-main">
                        <div class='qzdh qzdh0'>
                            <table class='table1'>
                                <tr>
                                    <th>兑换时间</th>
                                    <th>兑换数量</th>
                                    <th>兑换金额</th>
                                    <th>状态</th>
                                    <th>模式</th>
                                </tr>
                                <tr>
                                    <td>2016-07-25 20:58:02</td>
                                    <td>20</td>
                                    <td>1000.00</td>
                                    <td>进行中</td>
                                    <td><span class='btn2 btns1'>进入交易</span></td>
                                </tr>
                                <tr>
                                    <td>2016-07-25 20:58:02</td>
                                    <td>20</td>
                                    <td>1000.00</td>
                                    <td>已完成</td>
                                    <td><span class='btn2 btns2'>查看详情</span></td>
                                </tr>
                            </table>
                            <div style='height:30px;'></div>
                            <div class='contentbox1'>
                                <div class='top'>交易订单详情</div>
                                <div class='box'>
                                    <h1 class='title6'>LEP(钱老板信息)</h1>
                                    <div class='item'>
                                        <p><label>钱庄名：</label><span>admin</span></p>
                                        <p><label>手机号：</label><span>186 8888 6666</span></p>
                                    </div>
                                    <div class='item'>
                                        <p><label>钱老板信用：</label><span><img height='15px' src='./images/credit.png' /><img height='15px' src='./images/credit.png' /><img height='15px' src='./images/credit.png' /></span></p>
                                        <p><label>邮箱：</label><span>9283922@qq.com</span></p>
                                    </div>
                                    <div style='height:50px;'></div>
                                    <h1 class='title6'>兑换信息</h1>
                                    <div class='item'>
                                        <p><label>兑换LEP数量：</label><span>20</span></p>
                                        <p><label>付款时间：</label><span>2016-07-25 21:18:31</span></p>
                                    </div>
                                    <div class='item'>
                                        <p><label>付款USD金额：</label><span>1000.00</span></p>
                                        <p><label>兑换时间：</label><span>2016-07-25 21:18:51</span></p>
                                    </div>
                                    <div class='item'>
                                        <p><label>当前状态：</label><span class='green'>进行中</span></p>
                                       
                                    </div>
                                     <div style='height:50px;'></div>
                                    <h1 class='title6'>还需完成如下操作</h1>
                                    <div class='btnbox'>
                                        <p><input placeholder='付款时间' class='input' /></p>
                                        <p><input  placeholder='付款金额'  class='input' /></p>
                                        <p><input  placeholder='交易密码'  type='password'  class='input' /></p>
                                        <p><span class='btn2'>确认付款</span><span  class='btn2'>取消兑换</span></p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div  class='qzdh qzdh1 hidden'>
                            <table class='table1'>
                                <tr>
                                    <th>兑换时间</th>
                                    <th>兑换数量</th>
                                    <th>兑换金额</th>
                                    <th>状态</th>
                                    <th>模式</th>
                                </tr>
                                <tr>
                                    <td>2016-07-25 20:58:02</td>
                                    <td>20</td>
                                    <td>1000.00</td>
                                    <td>进行中</td>
                                    <td><span class='btn2 btns1'>进入交易</span></td>
                                </tr>
                                <tr>
                                    <td>2016-07-25 20:58:02</td>
                                    <td>20</td>
                                    <td>1000.00</td>
                                    <td>已完成</td>
                                    <td><span class='btn2 btns2'>查看详情</span></td>
                                </tr>
                            </table>
                            <div style='height:30px;'></div>
                            <div class='contentbox1'>
                                <div class='top'>交易订单详情</div>
                                <div class='box'>
                                    <div class='box1'>
                                        <h1 class='title6'>LEP(钱老板信息)</h1>
                                        <div class='item'>
                                            <p><label>钱庄名：</label><span>admin</span></p>
                                            <p><label>手机号：</label><span>186 8888 6666</span></p>
                                        </div>
                                        <div class='item'>
                                            <p><label>钱老板信用：</label><span><img height='15px' src='./images/credit.png' /><img height='15px' src='./images/credit.png' /><img height='15px' src='./images/credit.png' /></span></p>
                                            <p><label>邮箱：</label><span>9283922@qq.com</span></p>
                                        </div>
                                        <div class='clear'></div>
                                    </div>
                                    <div style='height:10px;'></div>
                                    <div class='box1'>
                                        <h1 class='title6'>兑换信息</h1>
                                        <div class='item'>
                                            <p><label>兑换LEP数量：</label><span>20</span></p>
                                            <p><label>付款时间：</label><span>2016-07-25 21:18:31</span></p>
                                        </div>
                                        <div class='item'>
                                            <p><label>付款USD金额：</label><span>1000.00</span></p>
                                            <p><label>兑换时间：</label><span>2016-07-25 21:18:51</span></p>
                                        </div>
                                        <div class='item'>
                                            <p><label>当前状态：</label><span class='green'>进行中</span></p>
                                           
                                        </div>
                                        <div class='clear'></div>
                                    </div>
                                     <div style='height:20px;'></div>
                                      <div class='box1'  style='background:#FCF7E7'>
                                      
                                        <h1 class='title6'>还需完成如下操作</h1>
                                        <div class='btnbox'>
                                            
                                            <p>交易密码： <input  placeholder=''  type='password'  class='input' /></p>
                                            <br>
                                            <p><span class='btn2'>确认已收款</span><span  class='btn2'>申诉兑单</span></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div  class='qzdh qzdh2 hidden'>
                            <table class='table1'>
                                <tr>
                                    <th>兑换时间</th>
                                    <th>兑换LEP数量</th>
                                    <th>付款BTC地址</th>
                                    <th>收款BTC地址</th>
                                    <th>状态</th>
                                    <th>模式</th>
                                </tr>
                                <tr>
                                    <td>2016-07-25 20:58:02</td>
                                    <td>20</td>
                                    <td>asdahdhwhhhdhsdoiqe213weqweee12qwe</td>
                                    <td>nsdahdhwhhhdhsdoiqe213weqweee12qwe</td>
                                    <td class='green'>处理中</td>
                                    <td><span class='btn2 btns3'>作废</span></td>
                                </tr>
                               <tr>
                                    <td>2016-07-25 20:58:02</td>
                                    <td>20</td>
                                    <td>asdahdhwhhhdhsdoiqe213weqweee12qwe</td>
                                    <td>nsdahdhwhhhdhsdoiqe213weqweee12qwe</td>
                                    <td>已删除</td>
                                    <td>------</td>
                                </tr>
                                <tr>
                                    <td>2016-07-25 20:58:02</td>
                                    <td>20</td>
                                    <td>asdahdhwhhhdhsdoiqe213weqweee12qwe</td>
                                    <td>nsdahdhwhhhdhsdoiqe213weqweee12qwe</td>
                                    <td class='yellow'>处理中</td>
                                    <td>-------</td>
                                </tr>
                            </table>
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


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head> 
    <title>USD/LEV</title>
  
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <link href="${ctx}/resources/css/public.css" rel="stylesheet">
    <link href="${ctx}/resources/css/button.css" rel="stylesheet">
    <link href="${ctx}/resources/css/sweetalert2.min.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script> 
    <script type="text/javascript" src="${ctx}/resources/js/sweetalert2.min.js"></script> 
    <script type="text/javascript" src="${ctx}/resources/js/main.js"></script> 
</head>
   <script type="text/javascript">
       $(function() {
    	   $("#levData").load("${ctx}/currency/lev-data?timestap="+(new Date()).valueOf());
       });
   </script>
<!-- <i class="typcn typcn-chart-bar"></i> <button class="button button-primary">按钮</button>-->
    <body>
       <jsp:include page="../commons/header.jsp"></jsp:include>
        <div class="content">
           <jsp:include page="../commons/left.jsp">
            	<jsp:param value="currency" name="menu"/>
            	<jsp:param value="currency-index" name="submenu"/>
            </jsp:include>
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
                        <span class='active'>钱老板兑换</span>
                        <span>钱庄兑换</span>
                    </div>

                    <div class="right-main">
                        <div class='qzdh qzdh0' id="levData">
                       
                </div>

                <div class='qzdh qzdh1 hidden'>
                    <script type="text/javascript">
                        function ljdh() {
                            $("#setp1").hide();
                            $("#setp2").fadeIn(400);
                        }
                       
                    </script>
                    <div style='height:20px;'></div>
                    <p class='title4'>请输入您要兑换的LEP数量和转款的BTC地址</p>
                     <div class='qzdhbox'>
                        <div id='setp1'>
                            <div class='setp1left'>
                                <p>
                                    <label>LEP数量：</label><input style="width:250px;" class="input" />
                                </p>  
                                <p>
                                    <label>转入BTC地址：</label><input style="width:250px;" class="input" />
                                </p>  
                                <p>
                                   <button onclick='ljdh()' class="button button-primary">立即兑换</button>
                                </p>    
                             </div>
                             <div class='setp1right'>
                                    <h1 class='blue'>友情链接</h1>
                                    <p><a href='#'>如何获取BTC地址？</a></p>
                                    <p><a href='#'>如何购买BTC（BTC币）？</a></p>
                                    <div style='height:30px'></div>
                                    <h1 class='red'>兑换汇率</h1>
                                    <p class='red'>1LEP = 1$ 1BTC = 456$</p>
                                    <p class='red'>1LEP = 6.5￥ 1BTC = 4564￥</p>

                             </div>
                             <div style='height:0px;clear:both'></div>
                        </div>
                        <div id='setp2' class='hidden'>
                           
                        </div>
                    </div>
                </div>
                </div>
                    <div class="table-header">
                        <h1 class='nav-h1'>历史兑换记录（<a class='active' href='javascript:;'>买</a>／<a href='javascript:;'>卖</a>）<i class="typcn typcn-info-outline" onclick='alerts("买游戏币规则", "当前交易订单状态为已打款等待卖家确认时，还可进行4笔买入<br />当点击买入后必须1小时内完成付款，超时交易取消<br />买家当日被系统或者自己本人取消2次，当日即没有购买资格,同时买家的信用降级，当日也看不到卖单<br />","error")'></i></h1>
                        <div class="table-header-box1">
                           更多
                        </div>
                    </div>

                    <div class="right-main">
                        <script type="text/javascript">
                                $(function() {
                                    $(".nav2-right").find("span").each(function(i) {
                                        $(this).click(function() {
                                            $(this).addClass("active").siblings().removeClass('active');
                                            $(".right-main .righttable").hide();
                                            $(".right-main .righttable"+i).show();

                                        });
                                    });
                                    $(".nav-h1").find("a").each(function(i) {
                                        $(this).click(function() {
                                            $(this).addClass("active").siblings().removeClass('active');
                                            $(".right-main .navh11").hide();
                                            $(".right-main .navh"+i).show();

                                        });
                                    });
                                })
                                
                            </script>
                            <div class="navh11 navh0">
                             
                                     <div class='nav2-right'>
                            <span class='active'>进行中<b>0</b></span>
                            <span>最近成交</span>
                            </div>
                            <table class="table1 righttable righttable0">
                                <tr>
                                    <th>进行中</th>
                                    <th>出售数量</th>
                                    <th>交易时间</th>
                                    <th>交易状态</th>
                                </tr>
                                <tr>
                                    <td colspan="4" class="norecords">暂无记录！</td>
                                </tr>
                            </table>
                            <table class="table1 hidden righttable righttable1">
                                <tr>
                                    <th>最近成交</th>
                                    <th>出售数量</th>
                                    <th>交易时间</th>
                                    <th>交易状态</th>
                                </tr>
                                <tr>
                                    <td colspan="4" class="norecords">暂无记录！</td>
                                </tr>
                            </table>
                            </div>
                            <div class=" hidden navh11 navh1">
                               
                                     <div class='nav2-right'>
                                        <span class='active'>进行中<b>0</b></span>
                                        <span>最近成交</span>
                                    </div>
                                    <table class="table1 righttable righttable0">
                                        <tr>
                                            <th>进行中</th>
                                            <th>出售数量</th>
                                            <th>交易时间</th>
                                            <th>交易状态</th>
                                        </tr>
                                        <tr>
                                            <td colspan="4" class="norecords">暂无记录！</td>
                                        </tr>
                                    </table>
                                    <table class="table1 hidden righttable righttable1">
                                        <tr>
                                            <th>最近成交</th>
                                            <th>出售数量</th>
                                            <th>交易时间</th>
                                            <th>交易状态</th>
                                        </tr>
                                        <tr>
                                            <td colspan="4" class="norecords">暂无记录！</td>
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


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<c:if test="${fn:length(pageData.result) == 0}">
	<h3>无兑换记录，<a href="#">点击前往钱庄兑换</a></h3>
</c:if>
<c:if test="${fn:length(pageData.result) > 0}">
<h1 class='h2' style='width: 43%; font-weight: normal'>
	钱老板兑换
	<!-- <a style='float:right;font-size:13px;position:relative;top:3px;'>更多</a> -->
</h1>
<div class='buy-left'>
	<script type="text/javascript">
		$(function() {
			$(".buy-left").find("table tr").each(
					function(i) {
						$(this).click(
								function() {
									$(this).addClass("active").siblings()
											.removeClass('active');
									// 其他操作

								});
					});
		})
	</script>
	<table class="table1">
		<c:if test="${fn:length(pageData.result) == 0}">
			<tr>
				<td align="center" colspan="2">无数据</td>
			</tr>
		</c:if>
		<tr>

			<th>数量</th>
			<th>价格</th>
		</tr>
		<c:forEach varStatus="xh" items="${pageData.result}" var="epSell">
		  <tr>

			<td>13</td>
			<td>22.1022</td>
		</tr>
		</c:forEach>

		
		<tr>

			<td>13</td>
			<td>22.1022</td>
		</tr>
		<tr>

			<td>13</td>
			<td>22.1022</td>
		</tr>
		<tr>

			<td>13</td>
			<td>22.1022</td>
		</tr>
		<tr>

			<td>13</td>
			<td>22.1022</td>
		</tr>

		<tr>

			<td>13</td>
			<td>22.1022</td>
		</tr>
		<tr>

			<td>13</td>
			<td>22.1022</td>
		</tr>
	</table>
</div>

<div class='buy-right'>

	<div class='but-nav'>订单详情</div>
	<div>
		<div class="tipbox1 color1"
			style='border-left: none; text-align: center'>汇率 ： 1LEP = 1$，1$
			= 7￥</div>
		<div class='butbox '>
			<p>
				<label>钱老板信誉:</label><b><img height='15px'
					src='${ctx}/resources/images/credit.png' /><img height='15px'
					src='${ctx}/resources/images/credit.png' /><img height='15px'
					src='${ctx}/resources/images/credit.png' /></b> <label>兑换LEP数量:</label><b>20.00
					CNY</b>

			</p>
			<p>
				<label>实际付款金额:</label><b style='width: 300px;'>20.00 RMB
					&nbsp;|&nbsp; 22.123 BTC &nbsp;|&nbsp; 30.20 USA</b>
			</p>

			<p>
				<label>银行名称:</label><b
					style='color: #666; font-size: 13px; font-weight: normal'>花旗银行</b>
			</p>
			<p>
				<label>交易密码:</label><b><input type="password" class="input" /></b>
			</p>
			<p>
				<label></label> <b><button class="button button-primary">买入</button></b>
			</p>
		</div>
	</div>

	<div class='clear'></div>

</div>
<div class='clear'></div>
</c:if>
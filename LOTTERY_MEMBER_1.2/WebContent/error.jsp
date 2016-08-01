<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>出错啦！</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<style type=text/css>
input {
	font-size: 12px
}

td {
	font-size: 12px
}

.p2 {
	font-size: 12px
}

.p6 {
	font-size: 12px;
	color: #1b6ad8
}

a {
	color: #1b6ad8;
	text-decoration: none
}

a:hover {
	color: red
}
</style>

  </head>
  
  <body>
     
     　　<p align=center>
		</p>
		<p align=center>
		</p>
		<table cellspacing=0 cellpadding=0 width=400 align=center border=0>
			<tbody>
				<tr>
					<td valign=top height=270>
						<div align=center>
							<br>
							<img height=199 src="images/error.gif" width=271>
							<br>
							<br>
							<table cellspacing=0 cellpadding=0 width="400" border=0>
								<tbody>
									<tr>
										<td>
											<font class=p2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color=#ff0000>&nbsp;无法访问本页的原因是：</font>
											</font>
										</td>
									</tr>
									
									<tr>
										<td align="center" height="40">
											<p>
												<font color=#0000ff><br>&nbsp;&nbsp;${alertMessages }</font>
											</p>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td height=5></td>
				<tr>
					<td align=middle>
						<center>
							<table cellspacing=0 cellpadding=0 width=400 border=0>
								<tbody>
									<tr>
										<td width=6>
											
										</td>
										<td>
											<div align=center>
												<font class=p6><a href="login.htm">返回首页</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
													href="javascript:history.go(-1)">返回出错页</a>
												</font>
											</div>
										</td>
										<td width=7>
											
										</td>
									</tr>
								</tbody>
							</table>
						</center>
					</td>
				</tr>
			</tbody>
		</table>
		<p align=center>
		</p>
		<p align=center>
		</p>
     
  </body>
</html>

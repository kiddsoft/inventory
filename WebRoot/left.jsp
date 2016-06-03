<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%
	int level = (Integer)session.getAttribute("user_level");//0表示管理员
	int issell = (Integer)session.getAttribute("user_issell");//1表示允许销售
	int isstock = (Integer)session.getAttribute("user_isstock");//1表示允许采购
	int ismgr = (Integer)session.getAttribute("user_ismgr");//1表示是审核者

	
	
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()	+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'left.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!-- 引用css/styles.css文件 -->
		<link rel="stylesheet" type="text/css" href="css/styles.css">
		<!-- 本页面中也写几个CSS样式，供本页面调用 -->
		<style type="text/css">
			.m1 {
				font-size: 12px;
				font-weight: bold;
				margin-left: 60px;
				text-align: center;
				vertical-align: sub;
			}

			.sub1 {
				margin-left: 40px;
			}

			td {
				font-size: 12px;
			}
		</style>

		<script type="text/javascript">
			var m = "";
			function menu(num) {
				//将id拼起来
				sub = eval("sub_" + num + ".style");
				if (m != sub) {
					sub.display = 'block';
					m = sub;
				} else {
					sub.display = "none";
					m = "";
				}
			}
			function mouseMov(id, isout) {
				sub = eval("m" + id + ".style");
				if (isout == 0) {
					sub.backgroundColor = '#ffffff';
				}
				if (isout == 1) {
					sub.backgroundColor = ''
				}
			}
		</script>

	</head>

	<body>
		<!-- 搞一个表 宽度200  高度 背景颜色  单元格边距   单元格间距-->		
		<!-- <table style="background-color: aliceblue; height: 100%; width: 100%" cellpadding="0" cellspacing="0">
		-->
		<table border="0" width="180" height="100%" align="right" cellSpacing="0" cellPadding="0">
		
		
			<!-- 大项 公告栏 -->
			<tr>
				<td onclick="menu(0)" height="33" background="img/m1.jpg">
					<!-- 使用font标签 然后使用m1样式 -->
					<font class="m1"><bean:message key="bbs.manager" /></font>
				</td>
			</tr>
			<tr>
				<td id="sub_0" style="display: none">
					<table width="80%" align="center">
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="showBBS.do" target="right" class="sub1"><bean:message key="show.all" /></a>
							</td>
						</tr>
						<!-- level值为0，就是管理员 -->
						<%if(level == 0){ %>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="addBBS.do" target="right" class="sub1"><bean:message key="bbs.add" /></a>
							</td>
						</tr>
						<%} %>
					</table>
				</td>
			</tr>
			
			
			
			
		
			<!-- 大项 库存管理 -->
			<!-- level值为0，就是管理员 -->
			<%if(level == 0){ %>
			<tr>
				<!-- 点击事件触发menu(10)，高度33，背景图片 -->
				<td onclick="menu(10)" height="33" background="img/m1.jpg">
					<!-- 使用font标签 然后使用m1样式 -->
					<font class="m1"><bean:message key="commodity.manager" /></font>
				</td>
			</tr>
			<tr>
				<!-- 设置ID为 sub_10,以方便脚本函数调用 风格为不显示 -->
				<td id="sub_10" style="display: none">
					<!-- 插入一个表格 宽度是80% 居中 -->
					<table width="80%" align="center">
						<!-- 高度22 设置鼠标选中与离开的背景事件 -->
						<tr>
							<td id="m0" height="22" onmouseover="mouseMov(0,0)" onmouseout="mouseMov(0,1)">
								<a href="commodity.do?command=view" target="right" class="sub1"><bean:message key="show.all" /></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<!-- 链接网址，位置，风格 -->
								<a href="./commodity/commodity_add.jsp" target="right" class="sub1"><bean:message key="commodity.add" /></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<!-- 链接网址，位置，风格 -->
								<a href="commodity.do?command=clearShow" target="right" class="sub1"><bean:message key="commodity.expired.remove" /></a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<%} %>




			<!-- 大项 销售管理 -->
			<!-- level值为0，就是管理员   管理员和销售权限的人才会显示这个-->
			<%if(level==0 || issell==1){ %>
			<tr>
				<td onclick="menu(20)" height="33" background="img/m1.jpg">
					<font class="m1"><bean:message key="sell.manager" /></font>
				</td>
			</tr>
			<tr>
				<td id="sub_20" style="display: none">
					<table width="80%" align="center">
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<!-- 添加一个参数pcmd=sell ,用于判断是否销售还是采购 -->
								<a href="./sellandpurchase/select.jsp?pcmd=sell" target="right" class="sub1"><bean:message key="indent.add" /></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="indent.do?command=pocessing&pcmd=sell" target="right" class="sub1"><bean:message key="indent.check.process"/></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="indent.do?command=history&pcmd=sell" target="right" class="sub1"><bean:message key="indent.history"/></a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<%} %>
			
			
			
			
			<!-- 大项 采购管理 -->
			<!-- level值为0，就是管理员   管理员和采购权限的人才会显示这个-->
			<%if(level==0 || isstock==1){ %>
			<tr>
				<td onclick="menu(30)" height="33" background="img/m1.jpg">
					<font class="m1"><bean:message key="purchase.manager" /></font>
				</td>
			</tr>
			<tr>
				<td id="sub_30" style="display: none">
					<table width="80%" align="center">
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<!-- 添加一个参数pcmd=sell ,用于判断是否销售还是采购 -->
								<a href="./sellandpurchase/select.jsp?pcmd=purchase" target="right" class="sub1"><bean:message key="indent.add" /></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="indent.do?command=pocessing&pcmd=purchase" target="right" class="sub1"><bean:message key="indent.check.process"/></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="indent.do?command=history&pcmd=purchase" target="right" class="sub1"><bean:message key="indent.history"/></a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<%} %>
			
			
			
			
			





			<!-- 大项 业务处理 -->
			<!-- level值为0，就是管理员   管理员和审核权限的人才会显示这个-->
			<%if(level==0 || ismgr==1){ %>
			<tr>
				<td onclick="menu(40)" height="33" background="img/m1.jpg">
					<font class="m1"><bean:message key="business.manager"/></font>
				</td>
			</tr>
			<tr>
				<td id="sub_40" style="display: none">
					<table width="80%" align="center">
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="examine.do?command=view" target="right" class="sub1"><bean:message key="indent.need.process" /></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="examine.do?command=history" target="right" class="sub1"><bean:message key="indent.history" /></a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<%} %>








			<!-- 大项 交易记录 -->
			<!-- level值为0，就是管理员   管理员和审核权限的人才会显示这个-->
			<%if(level==0 || ismgr==1){ %>
			<tr>
				<td onclick="menu(50)" height="33" background="img/m1.jpg">
					<font class="m1"><bean:message key="fund.record"/></font>
				</td>
			</tr>
			<tr>
				<td id="sub_50" style="display: none">
					<table width="80%" align="center">
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="record.do?command=view" target="right" class="sub1"><bean:message key="trade.history"/></a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<%} %>







			<!-- 大项 系统管理 -->
			<tr>
				<td onclick="menu(90)" height="33" background="img/m1.jpg">
					<font class="m1"><bean:message key="system.manager" /></font>
				</td>
			</tr>
			<tr>
				<td id="sub_90" style="display: none">
					<table width="80%" align="center">
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="system.do?command=myInfo" target="right" class="sub1"><bean:message key="user.information"/></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="system.do?command=changePassword" target="right" class="sub1"><bean:message key="user.password.change"/></a>
							</td>
						</tr>

						<!-- level值为0，就是管理员 -->
						<%if(level == 0){ %>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="system.do?command=showUser" target="right" class="sub1"><bean:message key="user.manager"/></a>
							</td>
						</tr>
						<%} %>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="system.do?command=exit" target="_top" class="sub1"><bean:message key="logout.system"/></a>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			
			
			
			<!-- 最后一格用一个空的，并高度填百分百，这样子前面的才会紧凑排列 -->
			<tr>
				<td height="100%"></td>
			</tr>
		</table>
	</body>
</html>


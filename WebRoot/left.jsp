<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%
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

	<body topmargin="0">
		<!-- 搞一个表 宽度200  高度 背景颜色  单元格边距   单元格间距-->
		<table style="background-color: aliceblue; height: 100%; width: 100%" cellpadding="0" cellspacing="0">
		
		
		
			<!-- 大项 公告栏 -->
			<tr>
				<td onclick="menu(0)" height="33" background="img/m1.jpg">
					<!-- 使用font标签 然后使用m1样式 -->
					<font class="m1">公告栏</font>
				</td>
			</tr>
			<tr>
				<td id="sub_0" style="display: none">
					<table width="80%" align="center">
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="indent.do?command=sellview" target="right" class="sub1">公告条</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			
			
			
		
			<!-- 大项 库存管理 -->
			<tr>
				<!-- 点击事件触发menu(10)，高度33，背景图片 -->
				<td onclick="menu(10)" height="33" background="img/m1.jpg">
					<!-- 使用font标签 然后使用m1样式 -->
					<font class="m1">货物管理</font>
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
								<a href="commodity.do?command=view" target="right" class="sub1">查看全部货物</a>
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
								<a href="./commodity/commodity_indicative_price.jsp" target="right" class="sub1">货物价格参考</a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<!-- 链接网址，位置，风格 -->
								<a href="./commodity/commodity_clear_expired.jsp" target="right" class="sub1">清除过期货物</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>




			<!-- 大项 销售管理 -->
			<tr>
				<td onclick="menu(20)" height="33" background="img/m1.jpg">
					<font class="m1"><bean:message key="sell.manager" /></font>
				</td>
			</tr>
			<tr>
				<td id="sub_20" style="display: none">
					<table width="80%" align="center">
						<tr>
							<td id="m1" height="22" onmouseover="mouseMov(1,0)" onmouseout="mouseMov(1,1)">
								<a href="indent.do?command=view&pcmd=sell" target="right" class="sub1"><bean:message key="indent.see.all.indent"/></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<!-- 添加一个参数pcmd=sell ,用于判断是否销售还是采购 -->
								<a href="./sellandbuy/select.jsp?pcmd=sell" target="right" class="sub1"><bean:message key="indent.add" /></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="indent.do?command=view&pcmd=sell" target="right" class="sub1"><bean:message key="indent.need.process"/></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="indent.do?command=view&pcmd=sell" target="right" class="sub1"><bean:message key="indent.finish.indent"/></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="indent.do?command=view&pcmd=sell" target="right" class="sub1"><bean:message key="indent.hitstroy"/></a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			
			
			
			<!-- 大项 采购管理 -->
			<tr>
				<td onclick="menu(30)" height="33" background="img/m1.jpg">
					<font class="m1"><bean:message key="purchase.manager" /></font>
				</td>
			</tr>
			<tr>
				<td id="sub_30" style="display: none">
					<table width="80%" align="center">
						<tr>
							<td id="m1" height="22" onmouseover="mouseMov(1,0)" onmouseout="mouseMov(1,1)">
								<a href="indent.do?command=view&pcmd=purchase" target="right" class="sub1"><bean:message key="indent.see.all.indent"/></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="./sellandbuy/select.jsp?pcmd=purchase" target="right" class="sub1"><bean:message key="indent.add" /></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="indent.do?command=view&pcmd=purchase" target="right" class="sub1"><bean:message key="indent.need.process"/></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="indent.do?command=view&pcmd=purchase" target="right" class="sub1"><bean:message key="indent.finish.indent"/></a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="indent.do?command=view&pcmd=purchase" target="right" class="sub1"><bean:message key="indent.hitstroy"/></a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			
			
			
			





			<!-- 大项 业务处理 -->
			<tr>
				<td onclick="menu(40)" height="33" background="img/m1.jpg">
					<font class="m1">业务处理</font>
				</td>
			</tr>
			<tr>
				<td id="sub_40" style="display: none">
					<table width="80%" align="center">
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="./tmp/tmp3.jsp" target="right" class="sub1">业务审核</a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="./tmp/tmp3.jsp" target="right" class="sub1">历史记录</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<!-- 大项 系统管理 -->
			<tr>
				<td onclick="menu(50)" height="33" background="img/m1.jpg">
					<font class="m1">系统管理</font>
				</td>
			</tr>
			<tr>
				<td id="sub_50" style="display: none">
					<table width="80%" align="center">
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="system.do?command=myInfo" target="right" class="sub1">我的信息</a>
							</td>
						</tr>
						<tr>
							<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
								<a href="system.do?command=changePassword" target="right" class="sub1">修改密码</a>
							</td>
						</tr>

						<!-- 判断是否有isadmin值，有就已经表示是管理员了 -->
						<logic:notEmpty name="isadmin" scope="session">
							<tr>
								<td height="22" onmouseover="this.style.backgroundColor='#ffffff'" onmouseout="this.style.backgroundColor=''">
									<a href="system.do?command=showAllUser" target="right" class="sub1">查看用户</a>
								</td>
							</tr>
						</logic:notEmpty>
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

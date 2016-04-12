<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="title.name"/></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!-- 引用css/styles.css文件 -->
		<link rel="stylesheet" type="text/css" href="css/styles.css">
		<!-- 用来给本页面单独使用的样式，方便统一修改 -->
		<style type="text/css">
			.it{
				background-color: #E1FFC1;
				height: 20px;
				width: 150px;
			}
			.lg_td{
				height:	30px;
				text-align:	right;
			}
		</style>
		<script language="javaScript">
			//这是函数
			function check(){
				//获取表单中命名为 userForm的表单，赋值给form变量
				//id在struts-config.xml中有定义
				var form = document.forms["userForm"];
				var pwd  = form.elements["password"].value;
				var user = form.elements["username"].value;

				//判断用户名与密码是否为空，是则提示
				if(form.elements["username"].value == ""){
					alert("<bean:message key="user.username.null"/>");
					form.elements["username"].focus();
					return;
				}
				if(form.elements["password"].value == ""){
					alert("<bean:message key="user.password.null"/>");
					form.elements["password"].focus();
					return;
				}
				//alert(user+ "" +pwd);
				//表单提交，它会进入之前设置好的action中
				form.submit();
			}
		</script>
	</head>
	<!-- 网页主体 -->
	<body style="background-color: aliceblue">
	<!-- 设一个表格，占满整个网页 无边框 -->
	<table width="100%" height="100%">
		<!-- 表格一行 -->
		<tr>
			<!-- 内容右显，用于显示两个语言设置 -->
			<td align="right">
				<a href="langset.do?lang=ch">中文</a>
				<a href="langset.do?lang=en">English</a>
			</td>
		</tr>
		<!-- 表格二行 -->
		<tr>
			<!-- 内容居中 -->
			<td align="center">
				<!-- 又放置一个表格 设置背景图片 -->
				<table cellpadding="0"cellspacing="0" background="img/login_centerBg.jpg" >
					<!-- 子表格一行 放一个背景图片，并设置该行的长宽 -->
					<tr>
						<td background="img/loginTitle.jpg" width="500" height="180" align="center">
							<!-- 使用这个则会根据图片大小自动拉伸 <img src="img/loginTitle.jpg" />-->
							<h3>用户登录</h3>
						</td>
					</tr>
					<!-- 子表格二行 -->
					<tr>
						<td align="center">
						<!-- 放置一个表单   提交时会跳到login.do中，设置鼠标焦点-->
							<html:form action="login.do" focus="username">
								<!-- 又一个表格 -->
								<table>
									<tr>
										<!-- 显示字体-->
										<td class="lg_td"><bean:message key="user.username"/>：</td>
										<!-- 显示一个文本框 文本框名字为username 使用it样式-->
										<td><html:text property="username" value="" styleClass="it"/></td>
									</tr>
									<tr>
										<!-- 显示字体-->
										<td class="lg_td"><bean:message key="user.password"/>：</td>
										<!-- 显示一个密码框 名字为password -->
										<td><html:password property="password" value="" styleClass="it"/></td>
									</tr>
									<tr>
										<!-- 这个空格，是为了让其对正上面的行 -->
										<td>&nbsp;</td>
										<td >
											<!-- 设置一个按钮，并设置点击时触发函数 -->
											<html:button property="lgoin" onclick="check()" styleClass="buttonStyle">
												<bean:message key="user.button.login"/>
											</html:button>
											<!-- 重置按钮 -->
											<html:reset styleClass="buttonStyle">
												<bean:message key="button.reset"/>
											</html:reset>
										</td>
									</tr>
									<logic:notEmpty name="error">
									<tr>
										<td></td>
										<td>
											<!--使用EL标签还是=request.getAttribute("error")-->
											${requestScope.error}
										</td>
									</tr>
									</logic:notEmpty>
								</table>
							</html:form>
						</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</table>
			</td>
		</tr>
	</table>
	</body>
</html>

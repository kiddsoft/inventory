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
    <title>My JSP 'password.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/styles.css">


	<script language="javaScript">
		//这是函数
		function check(){
			//获取表单中命名为 userForm的表单，赋值给form变量
			//id在struts-config.xml中有定义
			var form= document.forms["userForm"];
			var oldpwd		=form.elements["oldpassword"].value;
			var password	=form.elements["password"].value;
			var repassword	=form.elements["repassword"].value;

			//三个框是否有空值
			if(oldpwd=="" || password==""||repassword==""){
				alert("<bean:message key="user.password.null"/>");
				form.elements["oldpassword"].focus();
				return false;
			}
			//判断新密码与确认密码是否相等
			if(password != repassword){
				alert("<bean:message key="user.password.noequal"/>");
				form.elements["password"].focus();
				return false;
			}
			return true;
		}
	</script>
  </head>

  <body>
  <table align="center">
  	<tr><td>
  		<!--  -->
  		<html:form action="system.do?command=changepwd" onsubmit="return check();" focus="oldpassword">
  			<table>
  				<tr>
					<td><bean:message key="user.oldpassword"/>：</td>
					<td><html:text property="oldpassword" value=""/></td>
				</tr>
				<tr>
					<td><bean:message key="user.newpassword"/>：</td>
					<td><html:text property="password" value=""/></td>
				</tr>
				<tr>
					<td><bean:message key="user.repassword"/>：</td>
					<td><html:text property="repassword" value=""/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<html:submit styleClass="buttonStyle">
							<bean:message key="button.submit"/>
						</html:submit>
					</td>
				</tr>
  			</table>
    	</html:form>
  	</td></tr>
  </table>

  </body>
</html>

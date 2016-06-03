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
  </head>

  <body>
  	<div class="div1">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" align="center">
			<tr>
				<td class="td_title1">
					·<bean:message key="current.pos"/>：
					<bean:message key="system.manager"/>
					&gt;&gt; <bean:message key="user.information" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF" height="50">
					<br>
					<table border="1" align="center" width="300" cellpadding="0" cellspacing="0" bordercolor="#036500" >
						<tr bgcolor="#FFFFFF">
							<td colspan="9" align="center">
							  <table>
							  	  <tr>
							        <td><bean:message key="user.number"/></td>
							        <td>${userInfo.username}</td>
							      </tr>
							      <tr>
							        <td><bean:message key="user.username"/></td>
							        <td>${userInfo.name}</td>
							      </tr>
							      <tr>
							        <td><bean:message key="user.level"/></td>
							        <td>${userInfo.leveltype}</td>
							      </tr>
							      <tr>
							        <td><bean:message key="user.superior"/></td>
							        <td>${userInfo.superior}</td>
							      </tr>
							      <tr>
							        <td><bean:message key="user.isstock"/></td>
							        <td>${userInfo.isstocktype}</td>
							      </tr>
							      <tr>
							        <td><bean:message key="user.issell"/></td>
							        <td>${userInfo.isselltype}</td>
							      </tr>
							      <tr>
							        <td><bean:message key="indent.manager"/></td>
							        <td>${userInfo.ismgrtype}</td>
							      </tr>
							  </table>
							</td>
						</tr>
					</table>
					<br>
				</td>
			</tr>
		</table>
	</div>
  </body>
</html>

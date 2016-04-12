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
    <title>My JSP 'showUser.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
  <table>
  	<tr>
  		<td>登陆账号</td>
  		<td></td>
  	</tr>
  	<tr>
  		<td>姓名</td>
  		<td></td>
  	</tr>
  	<tr>
  		<td>用户级别</td>
  		<td></td>
  	</tr>
  	<tr>
  		<td>上级领导</td>
  		<td></td>
  	</tr>
  	<tr>
  		<td>进货权限</td>
  		<td></td>
  	</tr>
  	<tr>
  		<td>销售权限</td>
  		<td></td>
  	</tr>
  	<tr>
  		<td>管理订单</td>
  		<td></td>
  	</tr>
  </table>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%
//通过这个参数来判断是否是销售的
String pcmd = request.getParameter("pcmd");

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'select.jsp' starting page</title>
    
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
  <table align="center">
  <tr><td>
  	  <!-- 提交到indent.do ，并且带上参数 command,值为 select -->
	  <html:form action= 'indent.do?command=select' focus="cno">
	  <table>
	  	<tr>
	  		<td class="td_right"><bean:message key="commodity.Id" />:</td>
	  		<td><html:text property="cno" value=""/></td>
	  	</tr>
	  	<tr>
	  		<!-- 在这里传递pcmd的属性值 -->
			<td><input type="hidden" name="pcmd" value=<%=pcmd %> />&nbsp;</td>
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

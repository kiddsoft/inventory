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
    <title>My JSP 'commodity_view.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/styles.css">

  </head>

  <body>
  <table border="1">
  <tr><td>
	  <table>
	  <tr>
	  	<td class="td_right"><bean:message key="button.delete" /></td>
	  	<td class="td_right"><bean:message key="commodity.Id" /></td>
	  	<td class="td_right"><bean:message key="commodity.name" /></td>
	  	<td class="td_right"><bean:message key="commodity.inprice" /></td>
	  	<td class="td_right"><bean:message key="commodity.outprice" /></td>
	  	<td class="td_right"><bean:message key="commodity.count" /></td>
	  	<td class="td_right"><bean:message key="commodity.desc" /></td>
	  	<td class="td_right"><bean:message key="button.delete" /></td>
	  	<td class="td_right"><bean:message key="button.change" /></td>
	  </tr>
	  <!-- 从request中读取list 然后使用迭代器ele来遍历list -->
	  <logic:present name="resultdata" scope="request">
	  	<logic:notEmpty name="resultdata" scope="request">
	  		<form action="commodity.do?command=delete" method='post'>
  			<logic:iterate id="ele" name="resultdata" scope="request">
				  <tr>
				  	<td>
	        			<input type="hidden" name="allcno" value='<bean:write name="ele" property="cno" />'>
						<input type="checkbox" name="selectedcno" value='<bean:write name="ele" property="cno" />'>
					</td>
			  		<td><bean:write name="ele" property="cno" /></td>
			  		<td><bean:write name="ele" property="name" /></td>
			  		<td><bean:write name="ele" property="inprice" /></td>
			  		<td><bean:write name="ele" property="outprice" /></td>
			  		<td><bean:write name="ele" property="count" /></td>
			  		<td><bean:write name="ele" property="description" /></td>
			  		<td>
			  			<!-- paramId为form中的对应的名字。paramProperty为获取值 -->
						<html:link href="commodity.do?command=modify" paramId="cno" paramName="ele" paramProperty="cno">
							<bean:message key="button.change"/>
						</html:link>
					</td>
				  </tr>
			</logic:iterate>
				  <tr><td>
				  	<table border="0" width="100%">
				  		<tr>
				  			<td>
				  			<html:submit property="command"><bean:message key="button.delete.selected"/></html:submit>
			      			<html:submit property="command"><bean:message key="button.delete.all"/></html:submit>
				  			</td>
			</form>
				  			<td>
				  				<!-- 存放页数标识 -->
				  			</td>
				  		</tr>
				  	</table>
				  </td></tr>



	  	</logic:notEmpty>
	  </logic:present>
	  </table>
  </td></tr>
  </table>
  </body>
</html>

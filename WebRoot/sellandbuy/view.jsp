<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%
//通过这个参数来判断是否是销售的
String pcmd = request.getParameter("pcmd");
String state;
if(pcmd.equals("sell"))
{
	state = "销售";
}
else
{
	state = "采购";
}
session.setAttribute("pcmd", pcmd);
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>view</title>
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
  <table border="1">
  <tr><td>
	  <table  >
	  <tr>
	  	<td class="td_right"><bean:message key="button.delete" /></td>
	  	<td class="td_right"><bean:message key="indent.Id" /></td>
	  	<td class="td_right"><bean:message key="commodity.Id" /></td>
	  	<td class="td_right"><bean:message key="indent.userid" /></td>
	  	<td class="td_right"><bean:message key="user.superior" /></td>
	  	<td class="td_right"><bean:message key="indent.count" /></td>
	  	<td class="td_right"><bean:message key="commodity.allcount" /></td>
	  	<td class="td_right"><bean:message key="indent.type" /></td>
	  	<td class="td_right"><bean:message key="indent.state" /></td>
	  	<td class="td_right"><bean:message key="indent.begintime" /></td>
	  	<td class="td_right"><bean:message key="indent.endtime" /></td>
	  </tr>
	  <!-- 从request中读取indentdata 然后使用迭代器ele来遍历indentdata -->
	  <logic:present name="list" scope="request">
	  	<form action="indent.do?command=delete" method='post'>
	  	<logic:notEmpty name="list" scope="request">
  			<logic:iterate id="ele" name="list" scope="request">
				  <tr>
				  	<td>
	        			<input type="hidden" name="allcno" value='<bean:write name="ele" property="ino" />'>
						<input type="checkbox" name="selectedcno" value='<bean:write name="ele" property="ino" />'>
					</td>
			  		<td><bean:write name="ele" property="ino" /></td>
			  		<td><bean:write name="ele" property="cno" /></td>
			  		<td><bean:write name="ele" property="username" /></td>
			  		<td><bean:write name="ele" property="superior" /></td>
			  		<td><bean:write name="ele" property="icount" /></td>
			  		<td><bean:write name="ele" property="price" /></td>

			  		<!--<bean:write name="ele" property="isoutsell" />-->
			  		<td><%=state %></td>
			  		<td><bean:write name="ele" property="istate" /></td>
			  		<td><bean:write name="ele" property="itime" /></td>
			  		<td><bean:write name="ele" property="endtime" /></td>
				  </tr>
				  </logic:iterate>
				  <logic:present name="pagingBar" scope="request">
			      </logic:present>
				  <tr><td colspan="11">
				  	<table border="0" width="100%">
				  		<tr>
				  			<td>
				  			<html:submit property="command"><bean:message key="button.delete.selected"/></html:submit>
			      			<html:submit property="command"><bean:message key="button.delete.all"/></html:submit>
				  			</td>
				  			<td><input type="hidden" name="pcmd" value=<%=pcmd %> /></td>
			</form>
				  			<td align="right">
				  				<bean:write name="pagingBar" filter="false" scope="request" />
				      			<!-- 
				      			<form name='indentForm' action='indent.do?command=paging' method='post'>
				      				<input type='submit' value='GO'>
				      			</form>
				      			-->
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

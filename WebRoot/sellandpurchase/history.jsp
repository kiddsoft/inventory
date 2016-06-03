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
    <base href="<%=basePath%>">
    
    <title>My JSP 'history.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<div class="div1">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" align="center">
			<tr>
				<td class="td_title1">
					·<bean:message key="current.pos"/>：
					<bean:message key="indent.process"/>
					&gt;&gt; <bean:message key="indent.history" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF" height="50">
					<br>
					<table border="1" align="center" width="700" cellpadding="0" cellspacing="0" bordercolor=red >
						<tr bgcolor="#FFFFFF" >
							<td class="td_right"><bean:message key="indent.Id" /></td>
						  	<td class="td_right"><bean:message key="commodity.Id" /></td>
						  	<td class="td_right"><bean:message key="indent.userid" /></td>
						  	<td class="td_right"><bean:message key="user.superior" /></td>
						  	<td class="td_right"><bean:message key="indent.clientname" /></td>
						  	<td class="td_right"><bean:message key="indent.clientphone" /></td>
						  	<td class="td_right"><bean:message key="indent.count" /></td>
						  	<td class="td_right"><bean:message key="commodity.allcount" /></td>
						  	<td class="td_right"><bean:message key="indent.type" /></td>
						  	<td class="td_right"><bean:message key="indent.begintime" /></td>
						  	<td class="td_right"><bean:message key="indent.endtime" /></td>
						</tr>
						<logic:present name="list" scope="request">
						<logic:notEmpty name="list" scope="request">
						<logic:iterate id="ele" name="list" scope="request">
						<tr>
							<td><bean:write name="ele" property="ino" /></td>
					  		<td><bean:write name="ele" property="cno" /></td>
					  		<td><bean:write name="ele" property="username" /></td>
					  		<td><bean:write name="ele" property="superior" /></td>
					  		<td><bean:write name="ele" property="clientname" /></td>
					  		<td><bean:write name="ele" property="clientphone" /></td>
					  		<td><bean:write name="ele" property="icount" /></td>
					  		<td><bean:write name="ele" property="price" /></td>
					  		<td><bean:write name="ele" property="isoutselltype" /></td>
					  		<td><bean:write name="ele" property="itime" /></td>
					  		<td><bean:write name="ele" property="endtime" /></td>
						</tr>
						</logic:iterate>
						<tr bgcolor="#FFFFFF">
							<td colspan="11" align="right">
								<table border="0" width="100%">
									<tr>
										<td>
											
										</td>
										<td align="right">
											<bean:write name="pagingBar" filter="false" scope="request" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						</logic:notEmpty>
						<logic:empty name="list" scope="request">
						<tr bgcolor="#FFFFFF">
							<td colspan="11" align="center">
								<bean:message key="system.message.null"/>
							</td>
						</tr>
						</logic:empty>
						</logic:present>
						<logic:notPresent name="list" scope="request">
						<tr bgcolor="#FFFFFF">
							<td colspan="11" align="center">
								<bean:message key="system.message.null"/>
							</td>
						</tr>
						</logic:notPresent>
					</table>
					<br>
				</td>
			</tr>
		</table>
	</div>
  </body>
</html>

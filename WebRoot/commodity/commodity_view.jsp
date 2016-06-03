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
	<link rel="stylesheet" type="text/css" href="../css/styles.css">
	<link rel="stylesheet" type="text/css" href="./css/styles.css">
  </head>

  <body>

<div class="div1">
	<table width="100%" cellpadding="0" cellspacing="0" border="0" align="center">
	<tr>
		<td class="td_title1">
			·<bean:message key="current.pos"/>：
			<bean:message key="commodity.manager"/>
			&gt;&gt; <bean:message key="show.all" />
		</td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF" height="50">
			<br>
			<table border="1" align="center" width="700" cellpadding="0" cellspacing="0" bordercolor="#036500" >
				<tr bgcolor="#FFFFFF">
					<td colspan="9" align="center">
						<table border="0" width="100%">
							<tr>
								<td align="left">
								<bean:write name="find" filter="false" scope="request" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr bgcolor="#FFFFFF" >
					<td class="td_left"><bean:message key="button.delete" /></td>
					<td class="td_left"><bean:message key="commodity.Id" /></td>
					<td class="td_left"><bean:message key="commodity.name" /></td>
					<td class="td_left"><bean:message key="commodity.inprice" /></td>
					<td class="td_left"><bean:message key="commodity.outprice" /></td>
					<td class="td_left"><bean:message key="commodity.count" /></td>
					<td class="td_left"><bean:message key="commodity.desc" /></td>
					<td class="td_left"><bean:message key="button.change" /></td>
				</tr>
				<logic:present name="list" scope="request">
				<logic:notEmpty name="list" scope="request">
				<form action="commodity.do?command=delete" method='post'>
				<logic:iterate id="ele" name="list" scope="request">
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
				<tr bgcolor="#FFFFFF">
					<td colspan="9" align="right">
						<table border="0" width="100%">
							<tr>
								<td>
									<html:submit property="command"><bean:message key="button.delete.selected"/></html:submit>
									<html:submit property="command"><bean:message key="button.delete.all"/></html:submit>
								</td>
				</form>
								<td align="right">
									<logic:present name="pagingBar" scope="request">
									<bean:write name="pagingBar" filter="false" scope="request" />
									</logic:present>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				</logic:notEmpty>
				<logic:empty name="list" scope="request">
				<tr bgcolor="#FFFFFF">
					<td colspan="8" align="center">
						<bean:message key="system.message.null"/>
					</td>
				</tr>
				</logic:empty>
				</logic:present>
				<logic:notPresent name="list" scope="request">
				<tr bgcolor="#FFFFFF">
					<td colspan="9" align="center">
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

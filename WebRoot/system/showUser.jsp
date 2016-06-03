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
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<style type="text/css">
	<!-- 
		table {border: 0px solid black}
    	tr {border: 1px solid black;}
    	tr:nth-child(even) {background-color: #eee}
		td {border: 0px solid black; padding: 5px}
		-->
	</style>
  </head>
  <body>
  	<div class="div1">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" align="center">
			<tr>
				<td class="td_title1">
					·<bean:message key="current.pos"/>：
					<bean:message key="system.manager"/>
					&gt;&gt; <bean:message key="user.manager" />
				</td>
			</tr>
			<tr>
				<td>
					<a href="system.do?command=addUser"><bean:message key="user.add"/></a>
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
							<td><bean:message key="user.number"/></td>
				    		<td><bean:message key="user.password"/></td>
				    		<td><bean:message key="user.username"/></td>
				    		<td><bean:message key="user.level"/></td>
				    		<td><bean:message key="user.superior"/></td>
				    		<td><bean:message key="user.isstock"/></td>
				    		<td><bean:message key="user.issell"/></td>
				    		<td><bean:message key="indent.manager"/></td>
				    		<td><bean:message key="button.delete"/></td>
				    		<td><bean:message key="button.change"/></td>
						</tr>
						<logic:present name="list" scope="request">
						<logic:notEmpty name="list" scope="request">
						<form action="system.do?command=deleteUser" method="post">
						<logic:iterate id="user" name="list" scope="request">
						<tr>
							<td><bean:write name="user" property="username" /></td>
							<td><bean:write name="user" property="password" /></td>
							<td><bean:write name="user" property="name" /></td>
							<td><bean:write name="user" property="leveltype" /></td>
							<td><bean:write name="user" property="superior" /></td>
							<td><bean:write name="user" property="isstocktype" /></td>
							<td><bean:write name="user" property="isselltype" /></td>
							<td><bean:write name="user" property="ismgrtype" /></td>
							<td>
				                <html:link href="system.do?command=deleteUser" paramId="username" paramName="user" paramProperty="username">
				                 	 <bean:message key="button.delete"/>
				                </html:link>
				            </td>
				            <td>
				                <html:link href="system.do?command=modifyUser" paramId="username" paramName="user" paramProperty="username">
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
										</td>
						</form>
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

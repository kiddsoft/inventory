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
	<link rel="stylesheet" type="text/css" href="css/styles.css">
  </head>
  <body>
  <div class="div1">
	<table width="100%" cellpadding="0" cellspacing="0" border="0" align="center">
		<tr>
			<td class="td_title1">
				·<bean:message key="current.pos"/>：
				<bean:message key="bbs.manager"/>
				&gt;&gt; <bean:message key="bbs.show.all" />
			</td>
		</tr>
		<tr>
			<td bgcolor="#FFFFFF" height="50">
				<br>
				<table border="1" align="center" width="600" cellpadding="0" cellspacing="0" bordercolor="#036500" >
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
						<td><bean:message key="date"/></td>
    					<td><bean:message key="title"/></td>
    					<td><bean:message key="writer"/></td>
					</tr>
					<logic:present name="list" scope="request">
					<logic:notEmpty name="list" scope="request">
					<logic:iterate id="bbs" name="list" scope="request">
					<tr>
						<td><bean:write name="bbs" property="createtime" /></td>
              			<td><a href="showBBS.do?id=<bean:write name="bbs" property="id" />"><bean:write name="bbs" property="title" /></a></td>
              			<td><bean:write name="bbs" property="author" /></td>
					</tr>
					</logic:iterate>
					<tr bgcolor="#FFFFFF">
						<td colspan="9" align="right">
							<table border="0" width="100%">
								<tr>
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

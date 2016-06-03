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
    <base href="<%=basePath%>">
    
    <title>My JSP 'clear.jsp' starting page</title>
    
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
				<bean:message key="commodity.manager"/>
				&gt;&gt; <bean:message key="commodity.expired.remove" />
			</td>
		</tr>
		<tr>
			<td bgcolor="#FFFFFF" height="50">
				<br>
				<table border="1" align="center" width="700" cellpadding="0" cellspacing="0" bordercolor="#036500" >
					<tr>
					  	<td class="td_right"><bean:message key="commodity.Id" /></td>
					  	<td class="td_right"><bean:message key="commodity.name" /></td>
					  	<td class="td_right"><bean:message key="commodity.inprice" /></td>
					  	<td class="td_right"><bean:message key="commodity.outprice" /></td>
					  	<td class="td_right"><bean:message key="commodity.count" /></td>
					  	<td class="td_right"><bean:message key="commodity.desc" /></td>
					</tr>
					  <!-- 从request中读取list 然后使用迭代器ele来遍历list -->
					  <logic:present name="list" scope="request">
					  	<logic:notEmpty name="list" scope="request">
				  			<logic:iterate id="ele" name="list" scope="request">
								  <tr>
							  		<td>
							  			<html:link href="commodity.do?command=clear" paramId="cno" paramName="ele" paramProperty="cno">
											<bean:write name="ele" property="cno" />
										</html:link>
									</td>
							  		<td><bean:write name="ele" property="name" /></td>
							  		<td><bean:write name="ele" property="inprice" /></td>
							  		<td><bean:write name="ele" property="outprice" /></td>
							  		<td><bean:write name="ele" property="count" /></td>
							  		<td><bean:write name="ele" property="description" /></td>
								  </tr>
							</logic:iterate>
								  <tr><td colspan="9">
								  	<table border="0" width="100%">
								  		<tr>
								  			<td></td>
								  			<td>
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
								  	</table>
								  </td></tr>
					  	</logic:notEmpty>
					  </logic:present>
				</table>
				<br>
			</td>
		</tr>
	</table>
  </div>
  </body>
</html>

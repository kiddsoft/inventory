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
    
    <title>My JSP 'show.jsp' starting page</title>
    
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
					<bean:message key="fund.record"/>
					&gt;&gt; <bean:message key="trade.history" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF" height="50">
					<br>
					<table border="1" align="center" width="700" cellpadding="0" cellspacing="0" bordercolor="#036500" >
						<tr>
							<td colspan="11">
								<table>
									<tr>
										<!-- 在recordAction中查询了所有的记录的金额，相加起来，存到一个money中，现在显示出来 -->
										<td><bean:message key="record.money" />: ${money}</td>
									</tr>
									<tr>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr bgcolor="#FFFFFF" >
							<td class="td_right"><bean:message key="indent.Id" /></td>
						  	<td class="td_right"><bean:message key="commodity.Id" /></td>
						  	<td class="td_right"><bean:message key="commodity.name" /></td>
						  	<td class="td_right"><bean:message key="indent.userid" /></td>
						  	<td class="td_right"><bean:message key="indent.assessorid" /></td>
						  	<td class="td_right"><bean:message key="indent.type" /></td>
						  	<td class="td_right"><bean:message key="commodity.count" /></td>
						  	<td class="td_right"><bean:message key="commodity.inprice" /></td>
						  	<td class="td_right"><bean:message key="commodity.outprice" /></td>
						  	<td class="td_right"><bean:message key="income.expend" /></td>
						  	<td class="td_right"><bean:message key="date" /></td>
						</tr>
						<logic:present name="list" scope="request">
						<logic:notEmpty name="list" scope="request">
						<logic:iterate id="ele" name="list" scope="request">
						<tr>
							<td align="right"><bean:write name="ele" property="ino" /></td>
					  		<td align="right"><bean:write name="ele" property="cno" /></td>
					  		<td align="right"><bean:write name="ele" property="cname" /></td>
					  		<td align="right"><bean:write name="ele" property="username" /></td>
					  		<td align="right"><bean:write name="ele" property="superior" /></td>
					  		<td align="right"><bean:write name="ele" property="isselltype" /></td>
					  		<td align="right"><bean:write name="ele" property="scount" /></td>
					  		<td align="right"><bean:write name="ele" property="inprice" /></td>
					  		<td align="right"><bean:write name="ele" property="outprice" /></td>
					  		<td align="right"><bean:write name="ele" property="gain" /></td>
					  		<td align="right"><bean:write name="ele" property="itime" /></td>
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


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
	    <title><bean:message key="bbs.add" /></title>
		<link rel="stylesheet" type="text/css" href="css/styles.css">
		<script language="javaScript">
		function check(){
			var form= document.forms["addBBSForm"];
			if(form ==null){
				alert("<bean:message key="menu.inexistence"/>");
				return false;
			}
			var title = form.elements["title"].value;
			if (title == "") {
				alert("<bean:message key="title.announcement.write"/>");
				form.elements["title"].focus();
				return false;
			}
			var content = form.elements["content"].value;
			if (content == "") {
				alert("<bean:message key="content.announcement.write"/>");
				form.elements["content"].focus();
				return false;
			}
			return true;
		}
		</script>
	</head>
	<body>
	<div class="div1">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" align="center">
			<tr>
				<td class="td_title1">
					·<bean:message key="current.pos"/>：
					<bean:message key="bbs.manager"/>
					&gt;&gt; <bean:message key="bbs.add" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF" height="50">
					<br>
					<table border="1" align="center" width="700" cellpadding="0" cellspacing="0" bordercolor="#036500" >
						
						
						<html:form action="/addBBS" onsubmit="return check();" >
							<table border="0" bgcolor="#b0c4de">
								<tr>
									<td><bean:message key="title.announcement"/> : </td>
									<td bgcolor="#b0c4de">
										<html:text property="title"/><html:errors property="title" />
										<br/>
									</td>
								</tr>
								<tr>
									<td><bean:message key="content.announcement"/> :</td>
									<td>
										<html:textarea rows="20" cols="80"  property="content"/>
										<html:errors property="content"/>
										<br/>
									</td>
								</tr>
								<tr>
									<td></td>
									<td>
										<html:submit >
										<bean:message key="button.submit"/>
										</html:submit>
									</td>
								</tr>
							</table>
						</html:form>
					</table>
					<br>
				</td>
			</tr>
		</table>
	</div>
	

	</body>
</html>
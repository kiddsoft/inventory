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
    <title>My JSP 'password.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/styles.css">


	<script language="javaScript">
		//这是函数
		function check(){
			//获取表单中命名为 userForm的表单，赋值给form变量
			//id在struts-config.xml中有定义
			var form= document.forms["userForm"];
			//三个框是否有空值
			if(userId == "" ||
        userPassword == "" ||
        userName == "" ||
        userLevel == "" ||
        userSuperior == "" ||
        userStock == "" ||
        userSell == "" ||
        userManager == ""){
				alert("<bean:message key="user.password.null"/>");
				form.elements["userId"].focus();
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
					<bean:message key="system.manager"/>
					&gt;&gt; <bean:message key="user.manager" />
					&gt;&gt; <bean:message key="user.add" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF" height="50">
					<br>
					<table border="1" align="center" width="700" cellpadding="0" cellspacing="0" bordercolor="#036500" >
						<tr bgcolor="#FFFFFF">
							<td colspan="9" align="center">
							  <!-- 提交到commodity.do ，并且带上参数 command,值为 add -->
							  <html:form action="system.do?command=addUser" onsubmit="return check();" focus="userId">
							  <table>
							  	<tr>
						            <td><bean:message key="user.number"/></td>
						            <td><input type="text" name="userId" /></td>
						          </tr>
						          <tr>
						            <td><bean:message key="user.password"/></td>
						            <td><input type="text" name="userPassword" /></td>
						          </tr>
								  		<tr>
						            <td><bean:message key="user.username"/></td>
						            <td><input type="text" name="userName" /></td>
						          </tr>
								  		<tr>
						            <td><bean:message key="user.level"/></td>
						            <td><input type="text" name="userLevel" /></td>
						          </tr>
								  		<tr>
						            <td><bean:message key="user.superior"/></td>
						            <td><input type="text" name="userSuperior" /></td>
						          </tr>
								  		<tr>
						            <td><bean:message key="user.isstock"/></td>
						            <td><input type="text" name="userStock" /></td>
						          </tr>
								  		<tr>
						            <td><bean:message key="user.issell"/></td>
						            <td><input type="text" name="userSell" /></td>
						          </tr>
						  		<tr>
						            <td><bean:message key="indent.manager"/></td>
						            <td><input type="text" name="userManager" /></td>
						          </tr>
								<tr>
									<td>&nbsp;</td>
									<td>
										<html:submit styleClass="buttonStyle">
											<bean:message key="button.submit"/>
										</html:submit>
									</td>
								</tr>
							  </table>
							  </html:form>
							</td>
						</tr>
					</table>
					<br>
				</td>
			</tr>
		</table>
	</div>
  </body>
</html>

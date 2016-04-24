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
  <table align="center">
  	<tr><td>
  		<!--  -->
  		<html:form action="system.do?command=updateUser" onsubmit="return check();" focus="userId">
  			<table>
  				<tr>
		            <td>登陆账号</td>
                <td><input type="text" name="userId" value="${userInfo.username}" readonly="readonly" /></td>
		          </tr>
		          <tr>
		            <td>登录密码</td>
		            <td><input type="text" name="userPassword" value="${userInfo.password}" /></td>
		          </tr>
				  		<tr>
		            <td>姓名</td>
		            <td><input type="text" name="userName" value="${userInfo.name}" /></td>
		          </tr>
				  		<tr>
		            <td>用户级别</td>
		            <td><input type="text" name="userLevel" value="${userInfo.level}" /></td>
		          </tr>
				  		<tr>
		            <td>上级领导</td>
		            <td><input type="text" name="userSuperior" value="${userInfo.superior}" /></td>
		          </tr>
				  		<tr>
		            <td>进货权限</td>
		            <td><input type="text" name="userStock" value="${userInfo.isstock}" /></td>
		          </tr>
				  		<tr>
		            <td>销售权限</td>
		            <td><input type="text" name="userSell" value="${userInfo.issell}" /></td>
		          </tr>
		  		<tr>
		            <td>管理订单</td>
		            <td><input type="text" name="userManager" value="${userInfo.ismgr}" /></td>
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
  	</td></tr>
  </table>
  </body>
</html>

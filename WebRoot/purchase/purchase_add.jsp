<%@ page language="java" import="java.util.*,pers.qfy.dao.*,pers.qfy.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

TbCommodity tb = (TbCommodity)request.getAttribute("indent_info");
String iId = "";
String cId = "";
if(tb!= null){
	iId = FUtil.getRandomString(20);
	cId = tb.getCno();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>purchase_add</title>
    
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
			return true;
			var form= document.forms["indentForm"];
			if(form ==null){
				alert("is null");
				return false;
			}

			var count=form.elements["icount"].value;
			if(icount=="" || icount=="0"){
				alert(<bean:message key="indent.count.error" />);
				return false;
			}
			return true;
		}
	</script>

  </head>
  
  <body>
  <table align="center">
  <tr><td>
  	  <!-- 提交到commodity.do ，并且带上参数 command,值为 add -->
	  <html:form action="indent.do?command=purchaseadd" onsubmit="return check();" focus="cno">
	  <table>
	  	<tr>
	  		<td class="td_right"><bean:message key="indent.Id" />:</td>
	  		<td><html:text property="ino" value="<%=iId %>" readonly="true"/></td>
	  	</tr>
	  	<tr>
	  		<td class="td_right"><bean:message key="commodity.Id" />:</td>
	  		<td><html:text property="cno" value="<%=cId %>" readonly="true"/></td>
	  	</tr>
	  	<tr>
	  		<td class="td_right"><bean:message key="indent.count" />:</td>
	  		<td><html:text property="icount" value="0" /></td>
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

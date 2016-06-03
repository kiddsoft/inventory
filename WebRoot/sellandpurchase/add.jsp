<%@ page language="java" import="java.util.*,pers.qfy.dao.*,pers.qfy.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%
//通过这个参数来判断是否是销售的
String pcmd = request.getParameter("pcmd");

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
    <title>add</title>
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
				alert("数量不能为0");
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
					<bean:message key="indent.process"/>
					&gt;&gt; <bean:message key="indent.add" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF" height="50">
					<br>
					<html:form action="indent.do?command=add" onsubmit="return check();" focus="cno">
					<table border="1" align="center" width="700" cellpadding="0" cellspacing="0" bordercolor="#036500" >
						<tr>
					  		<td class="td_right"><bean:message key="indent.Id" />:</td>
					  		<td><html:text property="ino" value="<%=iId %>" readonly="true"/></td>
					  	</tr>
					  	<tr>
					  		<td class="td_right"><bean:message key="commodity.Id" />:</td>
					  		<td><html:text property="cno" value="<%=cId %>" readonly="true"/></td>
					  	</tr>
					  	<tr>
					  		<td class="td_right"><bean:message key="indent.clientname" />:</td>
					  		<td><html:text property="clientname" value="" /></td>
					  	</tr>
					  	<tr>
					  		<td class="td_right"><bean:message key="indent.clientphone" />:</td>
					  		<td><html:text property="clientphone" value="" /></td>
					  	</tr>
					  	<tr>
					  		<td class="td_right"><bean:message key="indent.count" />:</td>
					  		<td><html:text property="icount" value="" /></td>
					  	</tr>
					  	<tr>
							<td><input type="hidden" name="pcmd" value=<%=pcmd %> />&nbsp;</td>
							<td>
								<html:submit styleClass="buttonStyle">
									<bean:message key="button.submit"/>
								</html:submit>
							</td>
						</tr>
					</table>
					</html:form>
					<br>
				</td>
			</tr>
		</table>
	</div>
  </body>
</html>

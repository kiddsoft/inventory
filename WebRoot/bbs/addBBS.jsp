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
	    <title>添加公告信息</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script language="javaScript">
		function check(){
			var form= document.forms["addBBSForm"];
			if(form ==null){
				alert("表单不存在");
				return false;
			}
			var title = form.elements["title"].value;
			if (title == "") {
				alert("请填写公告标题");
				form.elements["title"].focus();
				return false;
			}
			var content = form.elements["content"].value;
			if (content == "") {
				alert("请填写公告内容");
				form.elements["content"].focus();
				return false;
			}
			return true;
		}
		</script>
	</head>
	<body>
		<html:form action="/addBBS" onsubmit="return check();" >
		<table border="0">
			<tr><td>标题 : </td><td><html:text property="title"/><html:errors property="title"/><br/></td></tr>
			<tr><td>内容 :</td><td><html:textarea rows="20" cols="80"  property="content"/><html:errors property="content"/><br/></td></tr>
			<tr><td></td><td><html:submit/><html:cancel/></td></tr>
		</table>
		</html:form>
	</body>
</html>


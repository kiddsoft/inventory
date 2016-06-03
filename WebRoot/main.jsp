<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="title.name"/></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!-- 引用css/styles.css文件 -->
		<link rel="stylesheet" type="text/css" href="css/styles.css">
	</head>

	<!-- 不能够在body里面的，去掉body -->
	<!-- rows参数：横向切割，分成两个部分，第一部分占15%，第二个占剩余的-->
	<frameset rows="15%,*%" cols="*" framespacing="0" frameborder="no">
		<!-- 在第一行中插入top.jsp-->
		<frame src="top.jsp" scrolling="no"/>
		<!-- 在第二行中插入一个框架 以左右的方式切割。分为两部分。左边占223(不是百分比)，剩下的归右边-->
		<frameset rows="*" cols="200,*" framespacing="0" frameborder="no" border="0">
			<!-- 插入left.jsp-->
			<frame src="left.jsp"/>
			<!-- 插入right.jsp 并且将该页面位置的名字定义为 right 以方便以后有其它页面可以插入该位置-->
			<!-- <frame src="right.jsp" name="right" scrolling="no"/> -->
			<frame src="showBBS.do" name="right">
		</frameset>
		<!-- 在第三行中插入bottom.jsp-->
		
	</frameset>
</html>

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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/styles.css">
  </head>
  <body>
    <table><bean:message key="bbs.manager"/>><bean:message key="bbs.show.all"/>><bean:message key="bbs.show.detail"/></table>
      <logic:present name="bbs" scope="request">
        <logic:notEmpty name="bbs" scope="request">
      
            <h1 align="center"><bean:write name="bbs" property="title" /></h1>
            <hr>
            <bean:write name="bbs" property="createtime" /> <bean:write name="bbs" property="author" />
            <br>
            <p><bean:write name="bbs" property="content" /></p>
        </logic:notEmpty>
      </logic:present>
  </body>
</html>

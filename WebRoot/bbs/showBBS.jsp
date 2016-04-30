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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		table {border: 0px solid black}
    tr {border: 1px solid black;}
    tr:nth-child(even) {background-color: #eee}
		td {border: 0px solid black; padding: 5px}
	</style>
  </head>
  <body>
    <table>
    	<tr>
    		<td>日期</td>
    		<td>标题</td>
    		<td>作者</td>
    	</tr>
      <logic:present name="list" scope="request">
        <logic:empty name="list" scope="request">
        <tr><td colspan="3">目前没有公告</td></tr>
        </logic:empty>
        <logic:notEmpty name="list" scope="request">
          <logic:iterate id="bbs" name="list" scope="request">
            <tr>
              <td><bean:write name="bbs" property="createtime" /></td>
              <td><a href="showBBS.do?id=<bean:write name="bbs" property="id" />"><bean:write name="bbs" property="title" /></a></td>
              <td><bean:write name="bbs" property="author" /></td>
            </tr>
          </logic:iterate>
          <tr>
            <td colspan="3" align="right">
              <bean:write name="pagingBar" filter="false" scope="request" />
            </td>
          </tr>
        </logic:notEmpty>
      </logic:present>
    </table>
  </body>
</html>

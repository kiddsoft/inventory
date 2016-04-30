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
    	<tr><td colspan="10" align="right"><a href="system.do?command=addUser">添加用户</a></td></tr>
    	<tr>
    		<td><input type="checkbox">全选</td>
    		<td>登陆账号</td>
    		<td>姓名</td>
    		<td>用户级别</td>
    		<td>上级领导</td>
    		<td>进货权限</td>
    		<td>销售权限</td>
    		<td>管理订单</td>
    		<td>删除</td>
    		<td>修改</td>
    	</tr>
      <logic:present name="list" scope="request">
        <logic:empty name="list" scope="request">
        <tr><td colspan="10">目前没有用户</td></tr>
        </logic:empty>
        <logic:notEmpty name="list" scope="request">
          <form action="system.do?command=deleteUser" method="post">
          <logic:iterate id="user" name="list" scope="request">
            <tr>
              <td>
                <input type="hidden" name="all" value='<bean:write name="user" property="username"/>'>
                <input type="checkbox" name="selectedUserer" value='<bean:write name="user" property="username" />'>
              </td>
              <td><bean:write name="user" property="username" /></td>
              <td><bean:write name="user" property="name" /></td>
              <td><bean:write name="user" property="level" /></td>
              <td><bean:write name="user" property="superior" /></td>
              <td><bean:write name="user" property="isstock" /></td>
              <td><bean:write name="user" property="issell" /></td>
              <td><bean:write name="user" property="ismgr" /></td>
              <td>
                <html:link href="system.do?command=deleteUser" paramId="username" paramName="user" paramProperty="username">
                 	 删除 
                </html:link>
              </td>
              <td>
                <html:link href="system.do?command=modifyUser" paramId="username" paramName="user" paramProperty="username">
                	 修改	
                </html:link>
              </td>
            </tr>
          </logic:iterate>
          <tr>
            <td>
              <html:submit property="command"><bean:message key="button.delete.selected"/></html:submit>
              <html:submit property="command"><bean:message key="button.delete.all"/></html:submit>
            </td>
            </form>
            <td colspan="9" align="right">
              <bean:write name="pagingBar" filter="false" scope="request" />
            </td>
          </tr>
        </logic:notEmpty>
      </logic:present>
    </table>
  </body>
</html>

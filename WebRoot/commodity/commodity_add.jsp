<%@ page language="java" import="java.util.*,pers.qfy.dao.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String strcno = "";
String strname = "";
String strinprice = "";
String stroutprice = "";
String strcount = "";
String strdesc = "";
//初始值，如果有这个modifycommoditylist，则说明是修改数据的
TbCommodity obj = (TbCommodity)request.getAttribute("modifycommoditylist");
session.setAttribute("modifycommodityvcno", null);
if(obj != null){
	strcno = obj.getCno();
	strname = obj.getName();
	strinprice = obj.getInprice()+"";
	stroutprice = obj.getOutprice()+"";
	strcount = obj.getCount()+"";
	strdesc = obj.getDescription();
	//把它设成null，下次就不会再读了，除非有新的修改
	request.setAttribute("modifycommoditylist", null);

	//这个值用于在进入action时作判断是否为修改，以及记录旧的cno字段
	session.setAttribute("modifycommodityvcno", obj.getCno());
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
	<link rel="stylesheet" type="text/css" href="../css/styles.css">
	<link rel="stylesheet" type="text/css" href="css/styles.css">

	<script language="javaScript">
		//这是函数
		function check(){
			//获取表单中命名为 userForm的表单，赋值给form变量
			//id在struts-config.xml中有定义
			var form= document.forms["commodityForm"];
			if(form ==null){
				alert("is null");
				return false;
			}
			
			var cno=form.elements["cno"].value;
			var name=form.elements["name"].value;
			var inPrice=form.elements["inprice"].value;
			var outPrice=form.elements["outprice"].value;
			var count=form.elements["count"].value;
			
			if(inPrice<=0 || outPrice<=0){
				alert("<bean:message key="price.cannot.zero"/>");
				return false;
			}
			if(inPrice >= outPrice){
				alert("<bean:message key="pleased.cost"/>");
				return false;
			}

			if(cno=="" || name==""||type==""||model==""||
			inPrice==""||outPrice==""||count==""){
				alert("data is null");
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
					<bean:message key="commodity.manager"/>
					&gt;&gt; <bean:message key="commodity.add" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF" height="50">
					<br>
					<table border="1" align="center" width="700" cellpadding="0" cellspacing="0" bordercolor="#036500" >
						<tr bgcolor="#FFFFFF">
							<td colspan="9" align="center">
							  <!-- 提交到commodity.do ，并且带上参数 command,值为 add -->
							  <html:form action="commodity.do?command=add" onsubmit="return check();" focus="cno">
							  <table>
							  	<tr>
							  		<td class="td_right"><bean:message key="commodity.Id" />:</td>
							  		<td><html:text property="cno" value="<%=strcno %>"/></td>
							  	</tr>
							  	<tr>
							  		<td class="td_right"><bean:message key="commodity.name" />:</td>
							  		<td><html:text property="name" value="<%=strname %>" /></td>
							  	</tr>
							  	<tr>
							  		<td class="td_right"><bean:message key="commodity.inprice" />:</td>
							  		<td><html:text property="inprice" value="<%=strinprice %>" /></td>
							  	</tr>
							  	<tr>
							  		<td class="td_right"><bean:message key="commodity.outprice" />:</td>
							  		<td><html:text property="outprice" value="<%=stroutprice %>" /></td>
							  	</tr>
							  	<tr>
							  		<td class="td_right"><bean:message key="commodity.count" />:</td>
							  		<td><html:text property="count" value="<%=strcount %>" /></td>
							  	</tr>
							  	<tr>
							  		<td class="td_right"><bean:message key="commodity.desc" />:</td>
							  		<td><html:text property="description" value="<%=strdesc %>" /></td>
							  	</tr>
							  	<tr>
							  		<!-- &nbsp; 是一个空格，在网页中是空格，用代码就使用 &nbsp; 表示 -->
									<td>&nbsp;</td>
									<td>
										<html:submit >
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

package pers.qfy.struts.action;

import pers.qfy.dao.*;
import pers.qfy.daotrue.*;
import pers.qfy.struts.form.*;
import pers.qfy.util.*;
import pers.qfy.factory.*;

import org.apache.struts.util.MessageResources;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class LoginAction extends Action{
	protected Locale locale = null; // 本地语言信息
	protected MessageResources message = null;// 消息资源
	
	//测试
	public void test(ActionForm form, HttpServletRequest request, HttpServletResponse response){
		//测试数据库是否可以插入数据
		//如果插不进 一般是字段使用了关键字 看错误提示即可
		System.out.println("进入测试");
		int rd = FUtil.GetIntRandom(10000);
		String sKey=""+rd;
    	TbUser user = new TbUser(sKey,"admin","邱飞燕",0,sKey,1,1,1, FUtil.getSystemTime());
    	TrueUserDao userDao = new TrueUserDao();
    	userDao.saveDate(user);
    	List<TbUser> li=null;
    	for(int i=0;i<li.size();i++)
    	{
    		TbUser user2 = li.get(i);
    		FUtil.print(user2.getUsername()+" "+user2.getCreatetime().toString());
        }
    	/*
    	TbCommodity tbc=new TbCommodity(sKey,"2",3,4,5,"6");
    	TbCommodityDAO tbcdao=new TbCommodityDAO();
    	tbcdao.saveDate(tbc);
    	
    	TbIndent tbi=new TbIndent(sKey,"2","3", "4", 5,6,7,8,FUtil.getSystemTime(), FUtil.getSystemTime());
    	TbIndentDAO tbidao=new TbIndentDAO();
    	tbidao.saveDate(tbi);
    	*/
	}

	//登陆验证
    public ActionForward execute(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    {
    	FUtil.print("LoginAction::ActionForward " + TbUser.class);
    	
    	// 获取消息资源对象  用于获取相对的properties
    	boolean runDBTest = false;
   	 	if(runDBTest){
   	 		test(form,request, response);
   	 		return mapping.findForward("login");
   	 	} else {
	   	 	this.locale = this.getLocale(request);
			this.message = this.getResources(request);
	
			//在login.jsp中提交的表单结构体
	    	UserForm us = (UserForm)form;
	
	    	//在数据库中根据用户名来查询
	    	TrueUserDao userdao = new TrueUserDao();
	    	TbUser userdata = userdao.login(us.getUsername());
	    	if(userdata == null){
	    		//用户名不对
	    		request.setAttribute("error", message.getMessage(locale, "user.username.error"));
	    		return mapping.findForward("login");
	    	} else if(!userdata.getPassword().equals(us.getPassword())){
	    		//密码不对
	    		request.setAttribute("error", message.getMessage(locale, "user.password.error"));
	    		return mapping.findForward("login");
	    	}
	    	//设置是否管理员以及审核者 以便显示一些信息
	    	HttpSession session = request.getSession(false);
	    	session.setAttribute("isadmin", userdata.getLevel());//管理员 0与1表示管理员，2表示普通用户
	    	session.setAttribute("ismgr", userdata.getLevel());//审核者 1表示是审核者
	    	session.setAttribute("user_id", userdata.getUsername());
	    	session.setAttribute("user_name", userdata.getName());
	
	    	//在struts-config.xml中找到main字符所对应的网址
	        return mapping.findForward("main");
   	 	}
    }
}

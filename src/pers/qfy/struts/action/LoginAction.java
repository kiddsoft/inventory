package pers.qfy.struts.action;
//这个是登陆的action
//用户登陆时就会调用这个了
//登陆成功后 将全保存用户的一些信息在session对象中，供以后有需要的时候进行使用
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
	//登陆验证
    public ActionForward execute(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    {
    	FUtil.print("LoginAction当前命令 " + TbUser.class);
    	
    	// 获取消息资源对象  用于获取相对的properties
   	 	this.locale = this.getLocale(request);
		this.message = this.getResources(request);

		//在login.jsp中提交的表单结构体
    	UserForm us = (UserForm)form;

    	HttpSession session = request.getSession(false);
    	
    	//在数据库中根据用户名来查询
    	TrueUserDao userdao = new TrueUserDao();
    	TbUser userdata = userdao.login(us.getUsername());
    	if(userdata == null){
    		//用户名不对
    		session.setAttribute("error", message.getMessage(locale, "user.username.error"));
    		return mapping.findForward("login");
    	} else if(!userdata.getPassword().equals(us.getPassword())){
    		//密码不对
    		session.setAttribute("error", message.getMessage(locale, "user.password.error"));
    		FUtil.print(message.getMessage(locale, "user.password.error"));
    		return mapping.findForward("login");
    	}
    	//设置是否管理员以及审核者 以便显示一些信息
    	
    	session.setAttribute("user", userdata);//登陆用户的类
    	session.setAttribute("user_level", userdata.getLevel());//管理员 0, 用户1
    	session.setAttribute("user_ismgr", userdata.getIsmgr());//审核者 1表示是审核者
    	session.setAttribute("user_id", userdata.getUsername());//登陆用户的ID
    	session.setAttribute("user_name", userdata.getName());//登陆用户的名字
    	session.setAttribute("user_issell", userdata.getIssell());//1表示有销售权限，0表示没有销售权限
    	session.setAttribute("user_isstock", userdata.getIsstock());//1表示有采购权限，0表示没有采购权限
    	session.setAttribute("user_superior", userdata.getSuperior());//本用户的审核者ID

    	//在struts-config.xml中找到main字符所对应的网址
        return mapping.findForward("main");
   	 }
}

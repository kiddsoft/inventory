package pers.qfy.struts.action;
import pers.qfy.dao.*;
import pers.qfy.daotrue.TrueUserDao;
import pers.qfy.util.*;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



public class SystemAction extends BaseAction{
	private TrueUserDao userdao;
	private HttpSession session;
	private TbUser user;
	private String currPage;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("进入SystemAction");
    	String cmd=request.getParameter("command");
    	System.out.println(cmd);
    	
    	userdao = new TrueUserDao();
    	session = request.getSession();
    	user = (TbUser)session.getAttribute("user");
    	
    	currPage = request.getParameter("currPage");
		if(currPage==null || currPage=="")
		{
			currPage = "1";
		}
		this.locale = this.getLocale(request);
		this.message = this.getResources(request);
    	
    	if (cmd.equalsIgnoreCase("addUser")) {
    		return addUser(mapping, form, request, response);
    	} else if (cmd.equalsIgnoreCase("updateUser")) {
    		return updateUser(mapping, form, request, response);
    	} else if (cmd.equalsIgnoreCase("deleteUser")) {
    		return deleteUser(mapping, form, request, response);
    	} else if (cmd.equalsIgnoreCase("showUser")) {
    		return showUser(mapping, form, request, response);
    	} else if (cmd.equalsIgnoreCase("modifyUser")) {
    		return modifyUser(mapping, form, request, response);
    	} else if (cmd.equalsIgnoreCase("myInfo")) {
    		return myInfo(mapping, form, request, response);
    	} else if (cmd.equalsIgnoreCase("changePassword")) {
    		return changePassword(mapping, form, request, response);
    	} else {
    		return myInfo(mapping, form, request, response);
    	}
    }
	
	public ActionForward addUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("userId");
		String password = request.getParameter("userPassword");
		String name = request.getParameter("userName");
		String level = request.getParameter("userLevel");
		String superior = request.getParameter("userSuperior");
		String stock = request.getParameter("userStock");
		String sell = request.getParameter("userSell");
		String manager = request.getParameter("userManager");
		
		if (username != null && password != null && name != null &&
				level != null && superior != null && stock != null &&
				sell != null && manager != null) {
			java.sql.Timestamp createtime = new java.sql.Timestamp(new java.util.Date().getTime());
			int ilevel = Integer.parseInt(level);
			int istock = Integer.parseInt(stock);
			int isell = Integer.parseInt(sell);
			int imanager = Integer.parseInt(manager);

			TbUser auser= new TbUser(username, password, name, ilevel, superior, istock, isell, imanager, createtime);
	    	int result = userdao.addUser(auser);
	    	if (result > 0) {
	    		request.setAttribute("operater", "添加用户");
	    		request.setAttribute("result", "成功");
//	    		return mapping.findForward("success");
	    		return showUser(mapping, form, request, response);
	    	} else {
	    		request.setAttribute("operater", "添加用户");
	    		request.setAttribute("result", "失败");
	    		return mapping.findForward("error");
	    	}
		}
		
		return mapping.findForward("addUser");
	}
	
	public ActionForward modifyUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		List<TbUser> li = userdao.Query("username", username);
    	if(li.size() > 0){
    		TbUser auser = li.get(0);
    		request.setAttribute("userInfo", auser);
    		return mapping.findForward("modifyUser");
    	}
    	
    	request.setAttribute("operater", "修改用户");
		request.setAttribute("result", "失败：不存在的用户");
		return mapping.findForward("error");
	}
	
	public ActionForward updateUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("userId");
		String password = request.getParameter("userPassword");
		String name = request.getParameter("userName");
		String level = request.getParameter("userLevel");
		String superior = request.getParameter("userSuperior");
		String stock = request.getParameter("userStock");
		String sell = request.getParameter("userSell");
		String manager = request.getParameter("userManager");
		
		if (username != null && password != null && name != null &&
				level != null && superior != null && stock != null &&
				sell != null && manager != null) {
			System.out.println("开始更新用户信息");
			int ilevel = Integer.parseInt(level);
			int istock = Integer.parseInt(stock);
			int isell = Integer.parseInt(sell);
			int imanager = Integer.parseInt(manager);

			TbUser auser= new TbUser();
			auser.setUsername(username);
			auser.setPassword(password);
			auser.setName(name);
			auser.setIsmgr(imanager);
			auser.setIssell(isell);
			auser.setIsstock(istock);
			auser.setLevel(ilevel);
			auser.setSuperior(superior);
			
	    	int result = userdao.updateUser(auser);
	    	if (result > 0) {
	    		request.setAttribute("operater", "修改用户");
	    		request.setAttribute("result", "成功");
//	    		return mapping.findForward("success");
	    		return showUser(mapping, form, request, response);
	    	} else {
	    		request.setAttribute("operater", "修改用户");
	    		request.setAttribute("result", "失败");
	    		return mapping.findForward("error");
	    	}
		}
		
		return modifyUser(mapping, form, request, response);
	}
	
	public ActionForward deleteUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		int result = userdao.deleteUser("username", username);
    	if (result > 0) {
    		request.setAttribute("operater", "删除用户");
    		request.setAttribute("result", "成功");
    		
    		return showUser(mapping, form, request, response);
    	} else {
    		request.setAttribute("operater", "删除用户");
    		request.setAttribute("result", "失败");
    		return mapping.findForward("error");
    	}
	}
	
	public ActionForward showUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
//		List<TbUser> userList = userdao.getAllUser();
//		request.setAttribute("userList", userList);
		String action = "system.do?command=showUser";
    	Map map = getPage(userdao, "showUserForm", action, Integer.parseInt(currPage), "select * from tb_user", userdao.CLASSNAME);
    	request.setAttribute("list", map.get("list"));
		request.setAttribute("pagingBar", map.get("bar"));
		return mapping.findForward("showUser");
	}
	
	public ActionForward changePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String oldPassword = request.getParameter("oldpassword");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		System.out.println(oldPassword + " - " + password + " - " + repassword);
		
		if (oldPassword != null && password != null && repassword != null &&
				password.equals(repassword)) {    			
			String userName = (String) session.getAttribute("user_id");
	    	boolean result = userdao.changePassword(userName, oldPassword, password);
	    	if (result) {
	    		request.setAttribute("operater", "更改密码");
	    		request.setAttribute("result", "成功");
	    		return mapping.findForward("success");
	    	} else {
	    		request.setAttribute("operater", "更改密码");
	    		request.setAttribute("result", "失败");
	    		return mapping.findForward("error");
	    	}
		}
		
		return mapping.findForward("changePassword");
	}
	
	public ActionForward myInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("userInfo", user);
		return mapping.findForward("myInfo");
	}
}

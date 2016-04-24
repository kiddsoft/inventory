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



public class SystemAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    {
    	System.out.println("进入SystemAction");
    	String cmd=request.getParameter("command");
    	System.out.println(cmd);
    	
    	TrueUserDao userdao = new TrueUserDao();
    	
    	HttpSession session = request.getSession();
    	TbUser user = (TbUser)session.getAttribute("user");
    	
    	if (cmd.equalsIgnoreCase("addUser")) {
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
    	    		return mapping.findForward("success");
    	    	} else {
    	    		request.setAttribute("operater", "添加用户");
    	    		request.setAttribute("result", "失败");
    	    		return mapping.findForward("error");
    	    	}
    		}
    		
    		return mapping.findForward("addUser");
    	} else if (cmd.equalsIgnoreCase("updateUser")) {
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
    	    		return mapping.findForward("success");
    	    	} else {
    	    		request.setAttribute("operater", "修改用户");
    	    		request.setAttribute("result", "失败");
    	    		return mapping.findForward("error");
    	    	}
    		}
    		
            System.out.println("修改用户，定向到查看用户");
    		List<TbUser> userList = userdao.getAllUser();
    		request.setAttribute("userList", userList);
    		return mapping.findForward("showAllUser");
    	} else if (cmd.equalsIgnoreCase("deleteUser")) {
    		String username = request.getParameter("username");
    		int result = userdao.deleteUser("username", username);
	    	if (result > 0) {
	    		request.setAttribute("operater", "删除用户");
	    		request.setAttribute("result", "成功");
	    		List<TbUser> userList = userdao.getAllUser();
	    		request.setAttribute("userList", userList);
	    		return mapping.findForward("showAllUser");
	    	} else {
	    		request.setAttribute("operater", "删除用户");
	    		request.setAttribute("result", "失败");
	    		return mapping.findForward("error");
	    	}
    	} else if (cmd.equalsIgnoreCase("showAllUser")) {
    		List<TbUser> userList = userdao.getAllUser();
    		request.setAttribute("userList", userList);
    		return mapping.findForward("showAllUser");
    	} else if (cmd.equalsIgnoreCase("modifyUser")) {
    		String username = request.getParameter("username");
    		List<TbUser> li = userdao.Query("username", username);
        	if(li.size() >0){
        		TbUser auser = li.get(0);
	    		request.setAttribute("userInfo", auser);
	    		return mapping.findForward("modifyUser");
        	}
        	
        	request.setAttribute("operater", "修改用户");
    		request.setAttribute("result", "失败：不存在的用户");
    		return mapping.findForward("error");
    	} else if (cmd.equalsIgnoreCase("myInfo")) {
    		request.setAttribute("userInfo", user);
    		return mapping.findForward("myInfo");
    	} else if (cmd.equalsIgnoreCase("changePassword")) {
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
    	} else {
    		return mapping.findForward("myInfo");
    	}
    }
}

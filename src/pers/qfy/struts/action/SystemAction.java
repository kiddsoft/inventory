package pers.qfy.struts.action;
import pers.qfy.dao.*;
import pers.qfy.util.*;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    	
    	//根据command参数，从而做出不同的操作

    	//默认跳到用户界面
    	if(cmd==null || cmd.equals("")){
    		return mapping.findForward("showUser");
    	}
    	
    	//跳到用户界面
    	if(cmd.equals("userShow")){
    		return mapping.findForward("showUser");
    	}
    	//全部用户信息界面
    	if(cmd.equals("userAll")){
    		return mapping.findForward("showUser");
    	}
    	return mapping.findForward("showUser");
    }
}

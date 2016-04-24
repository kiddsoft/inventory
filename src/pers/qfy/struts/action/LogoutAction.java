package pers.qfy.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.*;

public class LogoutAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    {
    	HttpSession session = request.getSession(false);
    	session.invalidate();

	    return mapping.findForward("login");
    }
}

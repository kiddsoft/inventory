package pers.qfy.struts.action;

import java.util.Locale;

import javax.servlet.http.*;
import org.apache.struts.action.*;

public class LangSetAction extends Action
{
    // �ڿͻ��˷���saveProduct����ʱִ�и÷���
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
    	System.out.println("������LangSetAction");
    	Locale currentLocale=null;
    	String lang=request.getParameter("lang");
    	if(lang==null || lang.equals(""))
    	{
    		currentLocale=new Locale("zh", "CN");
    	}
    	else if(lang.equals("ch"))
    	{
    		currentLocale=new Locale("zh", "CN");
    	}
    	else if(lang.equals("en"))
    	{
    		currentLocale=new Locale("en", "US");
    	}
    	this.setLocale(request,currentLocale);
        return mapping.findForward("login");
    }
}
package pers.qfy.struts.action;
//�������Ե�action
/*
 * �߼�Ϊ��
 * 1����login.jsp�У�ѡ�������Ļ��� English����ͻ�������action
 * 2�������action�У�����lang�Ĳ�����������������Ϊ���Ļ���Ӣ��
 * 3��������֮��ͼ�������login.jspҳ��
 * 4��webϵͳ����ݵ�ǰ�����ԣ��Զ��ص���lang_en_US.properties ���� lang_zh_CN.properties
 * 5���Ӷ���ʾ�����������Ļ�Ӣ�� */
import java.util.Locale;

import javax.servlet.http.*;
import org.apache.struts.action.*;

public class LangSetAction extends Action
{
    // �ڿͻ��˷���saveProduct����ʱִ�и÷���
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
    	Locale currentLocale=null;
    	String lang=request.getParameter("lang");
    	if(lang==null || lang.equals(""))
    	{
    		currentLocale=new Locale("zh", "CN");
    	}
    	else if(lang.equals("ch"))
    	{
    		System.out.println("����2");
    		currentLocale=new Locale("zh", "CN");
    	}
    	else if(lang.equals("en"))
    	{
    		System.out.println("Ӣ��");
    		currentLocale=new Locale("en", "US");
    	}
    	this.setLocale(request,currentLocale);
        return mapping.findForward("login");
    }
}
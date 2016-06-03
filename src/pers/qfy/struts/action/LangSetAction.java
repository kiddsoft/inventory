package pers.qfy.struts.action;
//设置语言的action
/*
 * 逻辑为：
 * 1，在login.jsp中，选择了中文或者 English，则就会调用这个action
 * 2，在这个action中，根据lang的参数，来决定是设置为中文还是英文
 * 3，设置了之后就继续返回login.jsp页面
 * 4，web系统会根据当前的语言，自动地调用lang_en_US.properties 或者 lang_zh_CN.properties
 * 5，从而显示的语言是中文或英文 */
import java.util.Locale;

import javax.servlet.http.*;
import org.apache.struts.action.*;

public class LangSetAction extends Action
{
    // 在客户端访问saveProduct动作时执行该方法
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
    		System.out.println("中文2");
    		currentLocale=new Locale("zh", "CN");
    	}
    	else if(lang.equals("en"))
    	{
    		System.out.println("英文");
    		currentLocale=new Locale("en", "US");
    	}
    	this.setLocale(request,currentLocale);
        return mapping.findForward("login");
    }
}
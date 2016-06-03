package pers.qfy.struts.action;
//����ǵ�½��action
//�û���½ʱ�ͻ���������
//��½�ɹ��� ��ȫ�����û���һЩ��Ϣ��session�����У����Ժ�����Ҫ��ʱ�����ʹ��
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
	protected Locale locale = null; // ����������Ϣ
	protected MessageResources message = null;// ��Ϣ��Դ
	//��½��֤
    public ActionForward execute(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    {
    	FUtil.print("LoginAction��ǰ���� " + TbUser.class);
    	
    	// ��ȡ��Ϣ��Դ����  ���ڻ�ȡ��Ե�properties
   	 	this.locale = this.getLocale(request);
		this.message = this.getResources(request);

		//��login.jsp���ύ�ı��ṹ��
    	UserForm us = (UserForm)form;

    	HttpSession session = request.getSession(false);
    	
    	//�����ݿ��и����û�������ѯ
    	TrueUserDao userdao = new TrueUserDao();
    	TbUser userdata = userdao.login(us.getUsername());
    	if(userdata == null){
    		//�û�������
    		session.setAttribute("error", message.getMessage(locale, "user.username.error"));
    		return mapping.findForward("login");
    	} else if(!userdata.getPassword().equals(us.getPassword())){
    		//���벻��
    		session.setAttribute("error", message.getMessage(locale, "user.password.error"));
    		FUtil.print(message.getMessage(locale, "user.password.error"));
    		return mapping.findForward("login");
    	}
    	//�����Ƿ����Ա�Լ������ �Ա���ʾһЩ��Ϣ
    	
    	session.setAttribute("user", userdata);//��½�û�����
    	session.setAttribute("user_level", userdata.getLevel());//����Ա 0, �û�1
    	session.setAttribute("user_ismgr", userdata.getIsmgr());//����� 1��ʾ�������
    	session.setAttribute("user_id", userdata.getUsername());//��½�û���ID
    	session.setAttribute("user_name", userdata.getName());//��½�û�������
    	session.setAttribute("user_issell", userdata.getIssell());//1��ʾ������Ȩ�ޣ�0��ʾû������Ȩ��
    	session.setAttribute("user_isstock", userdata.getIsstock());//1��ʾ�вɹ�Ȩ�ޣ�0��ʾû�вɹ�Ȩ��
    	session.setAttribute("user_superior", userdata.getSuperior());//���û��������ID

    	//��struts-config.xml���ҵ�main�ַ�����Ӧ����ַ
        return mapping.findForward("main");
   	 }
}

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
	protected Locale locale = null; // ����������Ϣ
	protected MessageResources message = null;// ��Ϣ��Դ
	
	//����
	public void test(ActionForm form, HttpServletRequest request, HttpServletResponse response){
		//�������ݿ��Ƿ���Բ�������
		//����岻�� һ�����ֶ�ʹ���˹ؼ��� ��������ʾ����
		System.out.println("�������");
		int rd = FUtil.GetIntRandom(10000);
		String sKey=""+rd;
    	TbUser user = new TbUser(sKey,"admin","�����",0,sKey,1,1,1, FUtil.getSystemTime());
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

	//��½��֤
    public ActionForward execute(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    {
    	FUtil.print("LoginAction::ActionForward " + TbUser.class);
    	
    	// ��ȡ��Ϣ��Դ����  ���ڻ�ȡ��Ե�properties
    	boolean runDBTest = false;
   	 	if(runDBTest){
   	 		test(form,request, response);
   	 		return mapping.findForward("login");
   	 	} else {
	   	 	this.locale = this.getLocale(request);
			this.message = this.getResources(request);
	
			//��login.jsp���ύ�ı��ṹ��
	    	UserForm us = (UserForm)form;
	
	    	//�����ݿ��и����û�������ѯ
	    	TrueUserDao userdao = new TrueUserDao();
	    	TbUser userdata = userdao.login(us.getUsername());
	    	if(userdata == null){
	    		//�û�������
	    		request.setAttribute("error", message.getMessage(locale, "user.username.error"));
	    		return mapping.findForward("login");
	    	} else if(!userdata.getPassword().equals(us.getPassword())){
	    		//���벻��
	    		request.setAttribute("error", message.getMessage(locale, "user.password.error"));
	    		return mapping.findForward("login");
	    	}
	    	//�����Ƿ����Ա�Լ������ �Ա���ʾһЩ��Ϣ
	    	HttpSession session = request.getSession(false);
	    	session.setAttribute("user", userdata);
	    	session.setAttribute("isadmin", userdata.getLevel());//����Ա 0��1��ʾ����Ա��2��ʾ��ͨ�û�
	    	session.setAttribute("ismgr", userdata.getIsmgr());//����� 1��ʾ�������
	    	session.setAttribute("user_id", userdata.getUsername());
	    	session.setAttribute("user_name", userdata.getName());
	
	    	//��struts-config.xml���ҵ�main�ַ�����Ӧ����ַ
	        return mapping.findForward("main");
   	 	}
    }
}

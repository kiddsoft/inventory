package pers.qfy.struts.action;

import java.util.*;
import org.apache.struts.util.*;

import org.apache.struts.action.Action;

import pers.qfy.factory.BaseHibernateDAO;

//����ʵ�ַ�ҳ����
public class BaseAction extends Action{
	
	int maxLine = 10; //һҳ�е��������
	protected Locale locale = null; // ����������Ϣ
	protected MessageResources message = null;// ��Ϣ��Դ
	
	//�ö�̬��ʵ�� ��daoָ��
	/*
	 * �ö�̬ʵ��
	 * ��Ҫ daoָ��
	 * ��Ҫִ�е�sql���
	 * ��Ҫ֪��Ҫ��ȡ�ڼ�ҳ
	 * ��ҳ������ĵ����ӵ�ַ
	 * 
	 * 
	 * һ�������������ң�������һ��LIST����������ҪSQL���
	 * һ������������ҳ�������Ҫ���ݿ����������������֪����ǰҳ
	 * 
	 * ���߽�����ĺϳ�һ����������������һ����
	 * */
	public void getPage(BaseHibernateDAO dao, String sql, String action, int choicePage)
	{
		//��ѯ���ݺ󣬽��� choicePage * maxLine �е����maxLine�����ݣ���������Ȼ�󷵻�
	}
}

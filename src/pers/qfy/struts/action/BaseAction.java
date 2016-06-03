package pers.qfy.struts.action;
//�����action�Ļ��࣬������action�඼�Ǽ̳�������
//�� ��ҳ�Ĺ���д�����������������action�Ϳ���ʹ����
//�� ������ѯ�Ĺ���д�����������������acion�Ϳ���ʹ����

import java.io.Serializable;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.*;

import org.apache.struts.action.Action;

import pers.qfy.factory.BaseHibernateDAO;
import pers.qfy.util.FUtil;

//����ʵ�ַ�ҳ����
public class BaseAction extends Action{
	
	final int recPerPage = 3; //һҳ�е��������
	protected Locale locale = null; // ����������Ϣ
	protected MessageResources message = null;// ��Ϣ��Դ
	String [] titles = null;
	String [] names = null;
	
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

	/*���õ���dao���� strut-config.xml�еı����֣���Ϊxxx.do?command=xxx , ��ǰѡ�е�ҳ���� ��ѯ��SQL��䣬���������Ӧ������*/
	public Map getPage(BaseHibernateDAO dao,String formName, String action, int iCurrPage, String sql, Class resultClass)
	{
		// ʵ����һ��Map����
		Map map = new HashMap();
		
		// ��ҳ��
		StringBuffer pagingBar = new StringBuffer();
		List listAll = null; // ����ѯ�ı���ȫ�������
		List<Serializable> list = new ArrayList();//��iCurrPageҳ�����ݿ�����
		int pages = 0; // ��ҳ��

		listAll = dao.QueryForSql(sql, resultClass);
		int count = listAll.size();
		if (count > 0) {
			// ������ҳ��
			if (count % recPerPage == 0) {
				pages = count / recPerPage;
			} else {
				pages = count / recPerPage + 1;
			}
			if (iCurrPage > pages) {
				iCurrPage = pages;
			}
			if (iCurrPage < 1) {
				iCurrPage = 1;
			}
			// ��ҳ��ѯ��ȡ����� ȡ���Ӧ��ҳ��������
			int start = recPerPage * (iCurrPage - 1);
			int end = recPerPage* iCurrPage;
			end = end > count ? count : end;
			for (int i = start; i < end; i++) {
				list.add((Serializable)listAll.get(i));
			}

			// �����ҳ��
			pagingBar.append("<form name='" + formName + "' action='" + action + "' method='post'>");
			// �ڷ�ҳ��������ܼ�¼��
			pagingBar.append(message.getMessage(locale, "page.totalRecord") + count);
			pagingBar.append("   ");
			pagingBar.append(message.getMessage(locale, "system.total") + "  "
					+ pages + "  " + message.getMessage(locale, "page.page"));
			pagingBar.append("   ");
			// ҳ������1��ʾ��һҳ�����ӣ�������ʾ������
			if (iCurrPage > 1) {
				pagingBar.append("<a href=" + action + "&currPage=1>"
						+ message.getMessage(locale, "page.first") + "</a>");
				pagingBar.append("   ");
				pagingBar.append("<a href=" + action + "&currPage="
						+ (iCurrPage - 1) + ">"
						+ message.getMessage(locale, "page.previous") + "</a>");
				pagingBar.append("   ");
			} else {
				pagingBar.append(message.getMessage(locale, "page.first"));
				pagingBar.append("   ");
				pagingBar.append(message.getMessage(locale, "page.previous"));
				pagingBar.append("   ");
			}
			// ��ʾ��ǰҳ��
			pagingBar.append("<font color='red'>" + iCurrPage + "</font>");
			pagingBar.append("   ");
			// ҳ��С����ҳ����ʾ��һҳ�����ӣ�������ʾ������
			if (iCurrPage < pages) {
				pagingBar.append("<a href=" + action + "&currPage="
						+ (iCurrPage + 1) + ">"
						+ message.getMessage(locale, "page.next") + "</a>");
				pagingBar.append("   ");
				pagingBar.append("<a href=" + action + "&currPage=" + pages
						+ ">" + message.getMessage(locale, "page.last")
						+ "</a>");
			} else {
				pagingBar.append(message.getMessage(locale, "page.next"));
				pagingBar.append("   ");
				pagingBar.append(message.getMessage(locale, "page.last"));
			}
			pagingBar.append("   ");
			pagingBar.append("<input type='text' name='currPage' size='1'>");
			pagingBar.append("<input type='submit' value='GO'>");
			pagingBar.append("</form>");
		}
		map.put("list", list);// �����
		map.put("bar", pagingBar.toString());// ��ҳ�����ַ�����ʽ
		return map;
	}
	
	public void SetRegParameter(HttpServletRequest request, Map map)
	{
		request.setAttribute("list", map.get("list"));
		//��������ŵ���ҳ����
		request.setAttribute("pagingBar", map.get("bar"));
	}
	
	void getQuery(HttpServletRequest request, String formName, String action){
    	//titles����ʾ���ı�
    	//names�ǲ��������֣����ڴ��ݵ�
    	StringBuffer pagingBar = new StringBuffer();
    	
    	pagingBar.append("<form name='" + formName + "' action='" + action + "' method='post'>");

    	this.locale = this.getLocale(request);
		this.message = this.getResources(request);
		
		if(titles != null && titles.length > 0){
			for(int i=0;i<titles.length;i++){
				pagingBar.append(titles[i] + "��<input type='text' name='" + names[i] + "' size='10'>");
			}
		}    	
    	pagingBar.append("<input type='submit' value='"+ message.getMessage(locale, "oper.find") +"'>");
		pagingBar.append("</form>");
		
		request.setAttribute("find", pagingBar);
    }
}

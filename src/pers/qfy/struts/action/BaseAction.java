package pers.qfy.struts.action;

import java.io.Serializable;
import java.util.*;

import org.apache.struts.util.*;

import org.apache.struts.action.Action;

import pers.qfy.factory.BaseHibernateDAO;

//用于实现分页功能
public class BaseAction extends Action{
	
	final int recPerPage = 3; //一页中的最大行数
	protected Locale locale = null; // 本地语言信息
	protected MessageResources message = null;// 消息资源
	
	//用多态来实现 传dao指针
	/*
	 * 用多态实现
	 * 需要 dao指针
	 * 需要执行的sql语句
	 * 需要知道要获取第几页
	 * 分页栏那里的的链接地址
	 * 
	 * 
	 * 一个函数用来查找，并返回一个LIST结果，这个需要SQL语句
	 * 一个函数用来分页，这个需要数据库的数据总数，并且知道当前页
	 * 
	 * 或者将上面的合成一个函数，操作就少一点了
	 * */

	/*所用到的dao对象， strut-config.xml中的表单名字，行为xxx.do?command=xxx , 当前选中的页数， 查询的SQL语句，结果集所对应的类名*/
	public Map getPage(BaseHibernateDAO dao,String formName, String action, int iCurrPage, String sql, Class resultClass)
	{
		// 实例化一个Map对象
		Map map = new HashMap();
		
		// 分页条
		StringBuffer pagingBar = new StringBuffer();
		List listAll = null; // 所查询的表格的全部结果集
		List<Serializable> list = new ArrayList();//第iCurrPage页的数据库结果集
		int pages = 0; // 总页数

		listAll = dao.QueryForSql(sql, resultClass);
		int count = listAll.size();
		if (count > 0) {
			// 计算总页数
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
			// 分页查询获取结果集 取相对应的页数的内容
			int start = recPerPage * (iCurrPage - 1);
			int end = recPerPage* iCurrPage;
			end = end > count ? count : end;
			for (int i = start; i < end; i++) {
				list.add((Serializable)listAll.get(i));
			}

			// 构造分页条
			pagingBar.append("<form name='" + formName + "' action='" + action + "' method='post'>");
			// 在分页条中添加总记录数
			pagingBar.append(message.getMessage(locale, "page.totalRecord") + count);
			pagingBar.append("   ");
			pagingBar.append(message.getMessage(locale, "system.total") + "  "
					+ pages + "  " + message.getMessage(locale, "page.page"));
			pagingBar.append("   ");
			// 页数大于1显示上一页超链接，否则不显示超链接
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
			// 显示当前页码
			pagingBar.append("<font color='red'>" + iCurrPage + "</font>");
			pagingBar.append("   ");
			// 页数小于总页数显示下一页超链接，否则不显示超链接
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
		map.put("list", list);// 结果集
		map.put("bar", pagingBar.toString());// 分页条的字符串形式
		return map;
	}
}

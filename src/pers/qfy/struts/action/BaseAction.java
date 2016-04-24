package pers.qfy.struts.action;

import java.util.*;
import org.apache.struts.util.*;

import org.apache.struts.action.Action;

import pers.qfy.factory.BaseHibernateDAO;

//用于实现分页功能
public class BaseAction extends Action{
	
	int maxLine = 10; //一页中的最大行数
	protected Locale locale = null; // 本地语言信息
	protected MessageResources message = null;// 消息资源
	
	//用多态来实现 传dao指针
	/*
	 * 用多态实现
	 * 需要 dao指针
	 * 需要执行的sql语句
	 * 需要知道要获取第几页
	 * 
	 * */
	public void getPage(BaseHibernateDAO dao, String sql, int choicePage)
	{
		//查询数据后，将第 choicePage * maxLine 中的最后maxLine条数据，存起来，然后返回
	}
}

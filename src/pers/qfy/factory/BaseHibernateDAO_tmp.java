package pers.qfy.factory;
/*
 * 这个是用于给pers.qfy.dao.BaseHibernateDAO作复制而使用的
 * 因为数据库一更新，BaseHibernateDAO就又会重新生成了
 * */

import org.hibernate.Session;

import pers.qfy.dao.HibernateSessionFactory;
public class BaseHibernateDAO_tmp implements IBaseHibernateDAO {
	protected Session session = null;
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

	//参数 一个类 保存一条数据
	public int saveDate(Object transientInstance) {
		int ret = 1;
		try {
			session = getSession();				//获取Session对象
			session.beginTransaction();			//开启事物
			//hibernate会根据传进来的数据，自动进行保存的了
			session.save(transientInstance);	//保存对象
			session.getTransaction().commit();	//提交事物
		} catch (Exception e) {
			e.printStackTrace();				//打印异常信息
			session.getTransaction().rollback();//回滚事物
			ret = 0;
		}
		return ret;
	}
}

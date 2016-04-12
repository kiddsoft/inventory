package pers.qfy.factory;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pers.qfy.dao.HibernateSessionFactory;
import pers.qfy.util.FUtil;
public class BaseHibernateDAO implements IBaseHibernateDAO {
	protected Session session = null;
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

	//这是全部操作数据库的函数，可以全部表格通用的
	//子类中的DAO函数编写好SQL或者传进特定值，即可以调用

	/**
	 * 根函数  保存数据到数据库
	 * @param obj	对象 任意的表的数据结构体 如 TbUser
	 */
	public int saveDate(Object data) {
		int ret = 1;
		try {
			session = getSession();				//获取Session对象
			session.beginTransaction();			//开启事物
			//hibernate会根据传进来的数据，自动进行保存的了
			session.save(data);	//保存对象
			session.getTransaction().commit();	//提交事物
			session.close();
		} catch (Exception e) {
			e.printStackTrace();				//打印异常信息
			session.getTransaction().rollback();//回滚事物
			ret = 0;
		}
		return ret;
	}
	/**
	 * 根据键值来查询并返回结果
	 * @param sql			需要执行的SQL语句
	 * @param resultClass	返回结构体的类名
	 */
	public Serializable QueryForKeyOneResultOne(String tableName, String Name, String Value, Class resultClass){
		Serializable ser = null;
		List<Serializable> result = QueryForKeyOne(tableName, Name, Value, resultClass);
		if(result.size()>0){
			ser = result.get(0);
		}
		return ser;
	}
	public List QueryForKeyOne(String tableName, String Name, String Value, Class resultClass){
		String sql = String.format("select * from %s where %s='%s';", tableName, Name, Value);
		List<Serializable> result = null;
		result = QueryBase(sql, resultClass);
		return result;
	}
	public List QueryForKeyTwo(String tableName, String Name1, String Value1, String Name2, String Value2, Class resultClass){
		String sql = String.format("select * from %s where %s='%s' and %s='%s';", tableName, Name1, Value1, Name2, Value2);
		List<Serializable> result = null;
		result = QueryBase(sql, resultClass);
		return result;
	}
	public List QueryAll(String tableName, Class resultClass){
		String sql = String.format("select * from %s;", tableName);
		List<Serializable> result = null;
		result = QueryBase(sql, resultClass);
		return result;
	}
	
	
	/**
	 * 查询 并返回一个结构体
	 * @param sql			需要执行的SQL语句
	 * @param resultClass	返回结构体的类名
	 */
	public Serializable QuerySingle(String sql,Class resultClass){
		Serializable user = null;
		
		List<Serializable> list = QueryBase(sql, resultClass);
		if( !list.isEmpty()){
			user = list.get(0);
		}
		return user;
	}
	
	/**
	 * 查询 并返回全部结果
	 * @param sql			需要执行的SQL语句
	 * @param resultClass	返回结构体的类名
	 */
	public List QueryBase(String sql,Class resultClass){
		List<Serializable> list = null;
		try {
			session = getSession();		//获取Session对象
			session.beginTransaction();					//开启事物

			Query qt = session.createSQLQuery(sql).addEntity(resultClass);
			list = qt.list();
			session.getTransaction().commit();			//提交事物
			session.close();
		} catch (Exception e) {
			e.printStackTrace();						//打印异常信息
			session.getTransaction().rollback();		//回滚事物
		}
		return list;
	}
	
	/**
	 * 执行SQL语句 并返回执行结果
	 * @param sql	需要执行的SQL语句
	 */
	public int ExecSql(String sql){
		//sql = "UPDATE tb_user SET `level`=77 where username='6269'";
		FUtil.print("要执行 : "+ sql);
		int result = 0;
		try{
			session = getSession();						//获取Session对象
			session.beginTransaction();					//开启事物
			Query query = session.createSQLQuery(sql);
			result = query.executeUpdate();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();						//打印异常信息
			session.getTransaction().rollback();		//回滚事物
		}
		//返回1表示执行成功，0表示失败
		return result;
	}
	
	/**
	 * 删除某个表的全部数据
	 * @param tableName	表的名称
	 */
	public void DeleteAll(String tableName){
		String sql = String.format("delete from %s", tableName);
		ExecSql(sql);
	}
}

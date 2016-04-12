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

	//����ȫ���������ݿ�ĺ���������ȫ�����ͨ�õ�
	//�����е�DAO������д��SQL���ߴ����ض�ֵ�������Ե���

	/**
	 * ������  �������ݵ����ݿ�
	 * @param obj	���� ����ı�����ݽṹ�� �� TbUser
	 */
	public int saveDate(Object data) {
		int ret = 1;
		try {
			session = getSession();				//��ȡSession����
			session.beginTransaction();			//��������
			//hibernate����ݴ����������ݣ��Զ����б������
			session.save(data);	//�������
			session.getTransaction().commit();	//�ύ����
			session.close();
		} catch (Exception e) {
			e.printStackTrace();				//��ӡ�쳣��Ϣ
			session.getTransaction().rollback();//�ع�����
			ret = 0;
		}
		return ret;
	}
	/**
	 * ���ݼ�ֵ����ѯ�����ؽ��
	 * @param sql			��Ҫִ�е�SQL���
	 * @param resultClass	���ؽṹ�������
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
	 * ��ѯ ������һ���ṹ��
	 * @param sql			��Ҫִ�е�SQL���
	 * @param resultClass	���ؽṹ�������
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
	 * ��ѯ ������ȫ�����
	 * @param sql			��Ҫִ�е�SQL���
	 * @param resultClass	���ؽṹ�������
	 */
	public List QueryBase(String sql,Class resultClass){
		List<Serializable> list = null;
		try {
			session = getSession();		//��ȡSession����
			session.beginTransaction();					//��������

			Query qt = session.createSQLQuery(sql).addEntity(resultClass);
			list = qt.list();
			session.getTransaction().commit();			//�ύ����
			session.close();
		} catch (Exception e) {
			e.printStackTrace();						//��ӡ�쳣��Ϣ
			session.getTransaction().rollback();		//�ع�����
		}
		return list;
	}
	
	/**
	 * ִ��SQL��� ������ִ�н��
	 * @param sql	��Ҫִ�е�SQL���
	 */
	public int ExecSql(String sql){
		//sql = "UPDATE tb_user SET `level`=77 where username='6269'";
		FUtil.print("Ҫִ�� : "+ sql);
		int result = 0;
		try{
			session = getSession();						//��ȡSession����
			session.beginTransaction();					//��������
			Query query = session.createSQLQuery(sql);
			result = query.executeUpdate();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();						//��ӡ�쳣��Ϣ
			session.getTransaction().rollback();		//�ع�����
		}
		//����1��ʾִ�гɹ���0��ʾʧ��
		return result;
	}
	
	/**
	 * ɾ��ĳ�����ȫ������
	 * @param tableName	�������
	 */
	public void DeleteAll(String tableName){
		String sql = String.format("delete from %s", tableName);
		ExecSql(sql);
	}
}

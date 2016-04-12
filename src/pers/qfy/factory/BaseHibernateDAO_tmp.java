package pers.qfy.factory;
/*
 * ��������ڸ�pers.qfy.dao.BaseHibernateDAO�����ƶ�ʹ�õ�
 * ��Ϊ���ݿ�һ���£�BaseHibernateDAO���ֻ�����������
 * */

import org.hibernate.Session;

import pers.qfy.dao.HibernateSessionFactory;
public class BaseHibernateDAO_tmp implements IBaseHibernateDAO {
	protected Session session = null;
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

	//���� һ���� ����һ������
	public int saveDate(Object transientInstance) {
		int ret = 1;
		try {
			session = getSession();				//��ȡSession����
			session.beginTransaction();			//��������
			//hibernate����ݴ����������ݣ��Զ����б������
			session.save(transientInstance);	//�������
			session.getTransaction().commit();	//�ύ����
		} catch (Exception e) {
			e.printStackTrace();				//��ӡ�쳣��Ϣ
			session.getTransaction().rollback();//�ع�����
			ret = 0;
		}
		return ret;
	}
}

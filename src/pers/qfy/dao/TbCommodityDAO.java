package pers.qfy.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.qfy.factory.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * TbCommodity entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see pers.qfy.dao.TbCommodity
 * @author MyEclipse Persistence Tools
 */

public class TbCommodityDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TbCommodityDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String INPRICE = "inprice";
	public static final String OUTPRICE = "outprice";
	public static final String COUNT = "count";
	public static final String DESCRIPTION = "description";

	public void save(TbCommodity transientInstance) {
		log.debug("saving TbCommodity instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbCommodity persistentInstance) {
		log.debug("deleting TbCommodity instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbCommodity findById(java.lang.String id) {
		log.debug("getting TbCommodity instance with id: " + id);
		try {
			TbCommodity instance = (TbCommodity) getSession().get(
					"pers.qfy.dao.TbCommodity", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbCommodity instance) {
		log.debug("finding TbCommodity instance by example");
		try {
			List results = getSession()
					.createCriteria("pers.qfy.dao.TbCommodity")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbCommodity instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbCommodity as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByInprice(Object inprice) {
		return findByProperty(INPRICE, inprice);
	}

	public List findByOutprice(Object outprice) {
		return findByProperty(OUTPRICE, outprice);
	}

	public List findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all TbCommodity instances");
		try {
			String queryString = "from TbCommodity";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbCommodity merge(TbCommodity detachedInstance) {
		log.debug("merging TbCommodity instance");
		try {
			TbCommodity result = (TbCommodity) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbCommodity instance) {
		log.debug("attaching dirty TbCommodity instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbCommodity instance) {
		log.debug("attaching clean TbCommodity instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
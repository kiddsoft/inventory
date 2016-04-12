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
 * TbUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see pers.qfy.dao.TbUser
 * @author MyEclipse Persistence Tools
 */

public class TbUserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbUserDAO.class);
	// property constants
	public static final String PASSWORD = "password";
	public static final String NAME = "name";
	public static final String LEVEL = "level";
	public static final String SUPERIOR = "superior";
	public static final String ISSTOCK = "isstock";
	public static final String ISSELL = "issell";
	public static final String ISMGR = "ismgr";

	public void save(TbUser transientInstance) {
		log.debug("saving TbUser instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbUser persistentInstance) {
		log.debug("deleting TbUser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbUser findById(java.lang.String id) {
		log.debug("getting TbUser instance with id: " + id);
		try {
			TbUser instance = (TbUser) getSession().get("pers.qfy.dao.TbUser",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbUser instance) {
		log.debug("finding TbUser instance by example");
		try {
			List results = getSession().createCriteria("pers.qfy.dao.TbUser")
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
		log.debug("finding TbUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbUser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	public List findBySuperior(Object superior) {
		return findByProperty(SUPERIOR, superior);
	}

	public List findByIsstock(Object isstock) {
		return findByProperty(ISSTOCK, isstock);
	}

	public List findByIssell(Object issell) {
		return findByProperty(ISSELL, issell);
	}

	public List findByIsmgr(Object ismgr) {
		return findByProperty(ISMGR, ismgr);
	}

	public List findAll() {
		log.debug("finding all TbUser instances");
		try {
			String queryString = "from TbUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbUser merge(TbUser detachedInstance) {
		log.debug("merging TbUser instance");
		try {
			TbUser result = (TbUser) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbUser instance) {
		log.debug("attaching dirty TbUser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbUser instance) {
		log.debug("attaching clean TbUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
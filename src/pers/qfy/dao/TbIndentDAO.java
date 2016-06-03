package pers.qfy.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.qfy.factory.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * TbIndent entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see pers.qfy.dao.TbIndent
 * @author MyEclipse Persistence Tools
 */

public class TbIndentDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TbIndentDAO.class);
	// property constants
	public static final String CNO = "cno";
	public static final String USERNAME = "username";
	public static final String SUPERIOR = "superior";
	public static final String ICOUNT = "icount";
	public static final String PRICE = "price";
	public static final String CLIENTNAME = "clientname";
	public static final String CLIENTPHONE = "clientphone";
	public static final String ISOUTSELL = "isoutsell";
	public static final String ISTATE = "istate";

	public void save(TbIndent transientInstance) {
		log.debug("saving TbIndent instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbIndent persistentInstance) {
		log.debug("deleting TbIndent instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbIndent findById(java.lang.String id) {
		log.debug("getting TbIndent instance with id: " + id);
		try {
			TbIndent instance = (TbIndent) getSession().get(
					"pers.qfy.dao.TbIndent", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbIndent instance) {
		log.debug("finding TbIndent instance by example");
		try {
			List results = getSession().createCriteria("pers.qfy.dao.TbIndent")
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
		log.debug("finding TbIndent instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbIndent as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCno(Object cno) {
		return findByProperty(CNO, cno);
	}

	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List findBySuperior(Object superior) {
		return findByProperty(SUPERIOR, superior);
	}

	public List findByIcount(Object icount) {
		return findByProperty(ICOUNT, icount);
	}

	public List findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findByClientname(Object clientname) {
		return findByProperty(CLIENTNAME, clientname);
	}

	public List findByClientphone(Object clientphone) {
		return findByProperty(CLIENTPHONE, clientphone);
	}

	public List findByIsoutsell(Object isoutsell) {
		return findByProperty(ISOUTSELL, isoutsell);
	}

	public List findByIstate(Object istate) {
		return findByProperty(ISTATE, istate);
	}

	public List findAll() {
		log.debug("finding all TbIndent instances");
		try {
			String queryString = "from TbIndent";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbIndent merge(TbIndent detachedInstance) {
		log.debug("merging TbIndent instance");
		try {
			TbIndent result = (TbIndent) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbIndent instance) {
		log.debug("attaching dirty TbIndent instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbIndent instance) {
		log.debug("attaching clean TbIndent instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
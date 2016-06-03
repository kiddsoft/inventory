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
 * TbRecord entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see pers.qfy.dao.TbRecord
 * @author MyEclipse Persistence Tools
 */

public class TbRecordDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TbRecordDAO.class);
	// property constants
	public static final String CNO = "cno";
	public static final String CNAME = "cname";
	public static final String USERNAME = "username";
	public static final String SUPERIOR = "superior";
	public static final String CLIENTNAME = "clientname";
	public static final String CLIENTPHONE = "clientphone";
	public static final String ISSELL = "issell";
	public static final String SCOUNT = "scount";
	public static final String INPRICE = "inprice";
	public static final String OUTPRICE = "outprice";
	public static final String GAIN = "gain";

	public void save(TbRecord transientInstance) {
		log.debug("saving TbRecord instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbRecord persistentInstance) {
		log.debug("deleting TbRecord instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbRecord findById(java.lang.String id) {
		log.debug("getting TbRecord instance with id: " + id);
		try {
			TbRecord instance = (TbRecord) getSession().get(
					"pers.qfy.dao.TbRecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbRecord instance) {
		log.debug("finding TbRecord instance by example");
		try {
			List results = getSession().createCriteria("pers.qfy.dao.TbRecord")
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
		log.debug("finding TbRecord instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TbRecord as model where model."
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

	public List findByCname(Object cname) {
		return findByProperty(CNAME, cname);
	}

	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List findBySuperior(Object superior) {
		return findByProperty(SUPERIOR, superior);
	}

	public List findByClientname(Object clientname) {
		return findByProperty(CLIENTNAME, clientname);
	}

	public List findByClientphone(Object clientphone) {
		return findByProperty(CLIENTPHONE, clientphone);
	}

	public List findByIssell(Object issell) {
		return findByProperty(ISSELL, issell);
	}

	public List findByScount(Object scount) {
		return findByProperty(SCOUNT, scount);
	}

	public List findByInprice(Object inprice) {
		return findByProperty(INPRICE, inprice);
	}

	public List findByOutprice(Object outprice) {
		return findByProperty(OUTPRICE, outprice);
	}

	public List findByGain(Object gain) {
		return findByProperty(GAIN, gain);
	}

	public List findAll() {
		log.debug("finding all TbRecord instances");
		try {
			String queryString = "from TbRecord";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbRecord merge(TbRecord detachedInstance) {
		log.debug("merging TbRecord instance");
		try {
			TbRecord result = (TbRecord) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbRecord instance) {
		log.debug("attaching dirty TbRecord instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbRecord instance) {
		log.debug("attaching clean TbRecord instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
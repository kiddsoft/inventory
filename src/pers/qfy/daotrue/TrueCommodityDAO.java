package pers.qfy.daotrue;

//用于操作tb_commodity表格的类
//商品信息表格的数据库操作

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.qfy.dao.TbCommodity;
import pers.qfy.dao.TbUser;
import pers.qfy.factory.BaseHibernateDAO;
import pers.qfy.struts.form.CommodityForm;
import pers.qfy.util.FUtil;

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

public class TrueCommodityDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TrueCommodityDAO.class);
	// property constants
	public static final String CNO = "cno";
	public static final String NAME = "name";
	public static final String INPRICE = "inprice";
	public static final String OUTPRICE = "outprice";
	public static final String COUNT = "count";
	public static final String DESCRIPTION = "description";

	public static final String TABLENAME = "tb_commodity";
	public static final Class CLASSNAME = TbCommodity.class;
	
	private String hql;
	
	//查询商品
	public List Query(String fieldValue, String strValue){
		List<TbCommodity> obj = null;
		if(fieldValue.equals("*")){
			obj = QueryAll(TABLENAME, CLASSNAME);
		}
		else{
			obj = QueryForKeyOne(TABLENAME, fieldValue, strValue, CLASSNAME);
		}
		
		return obj;
	}
	
//	public Long GetCount() {
//		try {
//			String queryString = "select count(*) from TbCommodity";
//			Query queryObject = getSession().createQuery(queryString);
//			Long count = (Long)queryObject.uniqueResult();
//			return count;
//		} catch (RuntimeException re) {
//			log.error("get count failed", re);
//			throw re;
//		}
//	}
//	
//	public List QueryRange(int min, int count) {
//		try {
//			String queryString = "from TbCommodity";
//			Query queryObject = getSession().createQuery(queryString);
//			queryObject.setFirstResult(min);
//			queryObject.setMaxResults(count);
//			return queryObject.list();
//		} catch (RuntimeException re) {
//			log.error("find range failed", re);
//			throw re;
//		}
//	}
	
	
	//添加商品
	public int Add(CommodityForm obj){
		int ret = 0;
		String sql = String.format("insert into %s values('%s','%s','%d','%d','%d','%s');",TABLENAME,
									obj.getCno(), obj.getName(),obj.getInprice(),obj.getOutprice(),obj.getCount(),obj.getDescription());
		ret = ExecSql(sql);
		return ret;
	}
	
	
	//删除商品
	public int Delete(String field, String value){
		//根据条件来删除
		int ret = 0;
		String sql = String.format("delete from %s where %s='%s';",TABLENAME, field, value);
		FUtil.print(sql);
		ret = ExecSql(sql);
		return ret;
	}
	
	public int UpdateForCno(String cnoValue, CommodityForm cf){
		String sql = String.format("update %s set cno='%s',name='%s',inprice='%d',outprice='%d',count='%d',description='%s' where cno='%s';",
				TABLENAME, cf.getCno(),cf.getName(),cf.getInprice(),cf.getOutprice(),cf.getCount(),cf.getDescription(), cnoValue);
		FUtil.print(sql);
		int ret = 1;
		ret = ExecSql(sql);
		//这个有问题，更新了数据库之后，再读取数据库，还是读取到旧的数据
		return ret;
	}
}
package pers.qfy.daotrue;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.qfy.dao.TbCommodity;
import pers.qfy.dao.TbIndent;
import pers.qfy.factory.BaseHibernateDAO;
import pers.qfy.util.FUtil;

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

public class TrueIndentDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TrueIndentDAO.class);
	// property constants
	public static final String INO = "ino";
	public static final String CNO = "cno";
	public static final String USERNAME = "username";
	public static final String SUPERIOR = "superior";
	public static final String ICOUNT = "icount";
	public static final String PRICE = "price";
	public static final String ISOUTSELL = "isoutsell";
	public static final String ISTATE = "istate";
	public static final String TABLENAME = "tb_indent";
	public static final Class CLASSNAME = TbIndent.class;
	
	public Serializable QueryCommodityInfo(String cno){
		String sql = String.format("select * from tb_commodity where cno='%s';", cno);
		Serializable ser = QuerySingle(sql, TbCommodity.class);
		return ser;
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
}
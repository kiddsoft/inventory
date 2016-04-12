package pers.qfy.daotrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.qfy.factory.BaseHibernateDAO;
import pers.qfy.dao.TbUser;

public class TrueUserDao extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TrueUserDao.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String NAME = "name";
	public static final String LEVEL = "level";
	public static final String SUPERIOR = "superior";
	public static final String ISSTOCK = "isstock";
	public static final String ISSELL = "issell";
	public static final String ISMGR = "ismgr";

	public static final String TABLENAME = "tb_user";
	public static final Class CLASSNAME = TbUser.class;
	
	
	public TbUser login(String userName){
		TbUser user = null;
		String sql = String.format("select * from %s where %s='%s';", TABLENAME, USERNAME, userName);
		user = (TbUser)QuerySingle(sql, CLASSNAME);
		return user;
	}
}

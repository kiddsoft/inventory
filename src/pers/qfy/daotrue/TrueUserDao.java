package pers.qfy.daotrue;
//tb_user”√ªß¿‡
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.qfy.factory.BaseHibernateDAO;
import pers.qfy.struts.form.CommodityForm;
import pers.qfy.util.FUtil;
import pers.qfy.dao.TbCommodity;
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
	
	public boolean changePassword(String userName, String oldPassword, String newPassword) {
		boolean ret = false;
		TbUser user = null;
		String sql = String.format("select * from %s where %s='%s';", TABLENAME, USERNAME, userName);
		user = (TbUser)QuerySingle(sql, CLASSNAME);
		
		if (user == null) {
			System.out.println("user: " + userName + " does not exist");
		} else {
			if (user.getPassword().equals(oldPassword)) {
				sql = String.format("update %s set %s='%s' where %s='%s'", TABLENAME, PASSWORD, newPassword, USERNAME, userName);
				System.out.println(sql);
				int result = ExecSql(sql);
				if (result == 0) {
					System.out.println("old password: " + oldPassword + " is wrong");
				} else {
					ret = true;
					System.out.println("change password successed");
				}
			} else {
				System.out.println("old password: " + oldPassword + " is wrong");
			}
		}
		
		return ret;
	}
	
	public List<TbUser> getAllUser() {
		List<TbUser> user = Query("*", "");
		return user;
	}

	public List Query(String fieldValue, String strValue){
		List<TbUser> obj = null;
		if(fieldValue.equals("*")){
			obj = QueryAll(TABLENAME, CLASSNAME);
		}
		else{
			obj = QueryForKeyOne(TABLENAME, fieldValue, strValue, CLASSNAME);
		}
		
		return obj;
	}
	
	public int addUser(TbUser user){
		int ret = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String createdate = sdf.format(user.getCreatetime());
		String sql = String.format("insert into %s values('%s','%s', '%s', '%d', '%s', '%d','%d','%d','%s');",TABLENAME,
									user.getUsername(), user.getPassword(), user.getName(), user.getLevel(),
									user.getSuperior(), user.getIsstock(), user.getIssell(), user.getIsmgr(), createdate);
		ret = ExecSql(sql);
		return ret;
	}
	
	public int deleteUser(String field, String value){
		int ret = 0;
		String sql = String.format("delete from %s where %s='%s';",TABLENAME, field, value);
		FUtil.print(sql);
		ret = ExecSql(sql);
		return ret;
	}
	
	public int updateUser(TbUser user){
		String sql = String.format("update %s set password='%s',name='%s',level='%d',superior='%s',isstock='%d',issell='%d',ismgr='%d' where username='%s';",
				TABLENAME, user.getPassword(), user.getName(), user.getLevel(),
				user.getSuperior(), user.getIsstock(), user.getIssell(), user.getIsmgr(), user.getUsername());
		FUtil.print(sql);
		int ret = 1;
		ret = ExecSql(sql);

		return ret;
	}
}

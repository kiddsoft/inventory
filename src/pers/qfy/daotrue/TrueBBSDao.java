package pers.qfy.daotrue;

//用于操作tb_bbs表格的类
//公告表格的操作
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.qfy.util.FUtil;

import pers.qfy.factory.BaseHibernateDAO;
import pers.qfy.dao.TbBbsDAO;
import pers.qfy.dao.TbBbs;

public class TrueBBSDao extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbBbsDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String CONTENT = "content";
	public static final String AUTHOR = "author";

	public static final String TABLENAME = "tb_bbs";
	public static final Class CLASSNAME = TbBbs.class;
	
	public List<TbBbs> getAllBBS() {
		List<TbBbs> bbs = Query("*", "");
		return bbs;
	}

	public List Query(String fieldValue, String strValue){
		List<TbBbs> obj = null;
		if(fieldValue.equals("*")){
			obj = QueryAll(TABLENAME, CLASSNAME);
		}
		else{
			obj = QueryForKeyOne(TABLENAME, fieldValue, strValue, CLASSNAME);
		}
		
		return obj;
	}
	
	public int addBBS(TbBbs bbs){
		int ret = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String createdate = sdf.format(bbs.getCreatetime());
		String sql = String.format("insert into %s(title, content,author,createtime) values('%s','%s','%s','%s');",
									TABLENAME, bbs.getTitle(), bbs.getContent(), bbs.getAuthor(), createdate);
		ret = ExecSql(sql);
		return ret;
	}
	
	public int deleteBBS(String field, String value){
		int ret = 0;
		String sql = String.format("delete from %s where %s='%s';",TABLENAME, field, value);
		FUtil.print(sql);
		ret = ExecSql(sql);
		return ret;
	}
	
	public int updateBBS(TbBbs bbs){
		String sql = String.format("update %s set title='%s',content='%s',author='%s';",
				TABLENAME, bbs.getTitle(), bbs.getContent(), bbs.getAuthor());
		FUtil.print(sql);
		int ret = 1;
		ret = ExecSql(sql);

		return ret;
	}
}

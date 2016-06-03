package pers.qfy.daotrue;
//用于操作tb_record类
//资金记录类
import pers.qfy.dao.*;
import pers.qfy.factory.*;
import pers.qfy.util.FUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrueRecordDAO extends BaseHibernateDAO {
	public static final String INO = "ino";
	public static final String CNO = "cno";
	public static final String CNAME = "cname";
	public static final String USERNAME = "username";
	public static final String SUPERIOR = "superior";
	public static final String ISSELL = "issell";
	public static final String SCOUNT = "scount";
	public static final String INPRICE = "inprice";
	public static final String OUTPRICE = "outprice";
	public static final String GAIN = "gain";
	public static final String ITIME = "itime";
	public static final String TABLENAME = "tb_record";
	public static final Class CLASSNAME = TbRecord.class;
	
	public Serializable QueryRecord(String ino){
		String sql = String.format("select * from tb_record where ino='%s';", ino);
		Serializable ser = QuerySingle(sql, CLASSNAME);
		return ser;
	}
	
	public void AddData(String ino, String cno, String cname, String username,
			String superior,String clientname, String clientphone,Integer issell, Integer scount,
			Integer inprice, Integer outprice,Integer gain, Timestamp itime){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String createdate = sdf.format(itime);
		String sql = String.format("insert into %s values('%s','%s','%s','%s','%s','%s','%s','%d','%d','%d','%d','%d','%s');",
					TABLENAME,ino,cno,cname,username,superior,clientname,clientphone,issell,scount,inprice,outprice,gain,createdate);
		ExecSql(sql);
	}
}

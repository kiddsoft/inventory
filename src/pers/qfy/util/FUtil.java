package pers.qfy.util;
//功能类，保存了一些公共的函数，供JAVA类有需要的调用
import java.sql.Timestamp;
import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FUtil {
	/*
	 * 打印信息
	 * */
	public static void print(Object obj){
		System.out.println(obj);
	}
	/*
	 * 获取系统当前时间，只精确到毫秒
	 * */
	public static Timestamp getSystemTime()
	{
		Timestamp dt = new Timestamp(System.currentTimeMillis());//获取系统当前时间
		dt.setNanos(0);//将纳秒设成0
		return dt;
	}
	public static Timestamp getSystemTime2()
	{ 
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(dt);
		Timestamp buydate = Timestamp.valueOf(nowTime); 
		return buydate;
	}
	public static int GetIntRandom(int nMaxNum){
		int nNum=(int)(Math.random()*nMaxNum);
		return nNum;
	}
	public static String getRandomString(int length) {//length表示生成字符串的长度  
		length = 10;
	    String base = "0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));
	    }     
	    return sb.toString();     
	 }
}

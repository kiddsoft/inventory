package pers.qfy.util;

import java.sql.Timestamp;
import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FUtil {
	/*
	 * ��ӡ��Ϣ
	 * */
	public static void print(Object obj){
		System.out.println(obj);
	}
	/*
	 * ��ȡϵͳ��ǰʱ�䣬ֻ��ȷ������
	 * */
	public static Timestamp getSystemTime()
	{
		Timestamp dt = new Timestamp(System.currentTimeMillis());//��ȡϵͳ��ǰʱ��
		dt.setNanos(0);//���������0
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
	public static String getRandomString(int length) {//length��ʾ�����ַ����ĳ���  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }
}

package com.symbio.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Fred
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class DateUtils {

	public static String yyyy_MM_dd = "yyyy-MM-dd";
	public static String yyyyMMdd = "yyyyMMdd";
	public static String yyyyMM = "yyyyMM";
	public static String yyyy_MM = "yyyy-MM";
	public static String yyyysMMsDD = "yyyy/MM/dd";
	public static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
	public static String yyyyMMddHHmm = "yyyyMMddHHmm";
	public static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 将字符串时间改成Date类型
	 * 
	 * @param format
	 * @param dateStr
	 * @return
	 */
	public static Date strToDate(String format, String dateStr) {

		Date date = null;

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 将Date时间转成字符串
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static String DateToStr(String format, Date date) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

		return simpleDateFormat.format(date);
	}

	/**
	 * 获取2个字符日期的天数差
	 * 
	 * @param p_startDate
	 * @param p_endDate
	 * @return 天数差
	 */
	public static long getDaysOfTowDiffDate(String p_startDate, String p_endDate) {

		Date l_startDate = DateUtils.strToDate(DateUtils.yyyy_MM_dd, p_startDate);
		Date l_endDate = DateUtils.strToDate(DateUtils.yyyy_MM_dd, p_endDate);
		long l_startTime = l_startDate.getTime();
		long l_endTime = l_endDate.getTime();
		long betweenDays = (long) ((l_endTime - l_startTime) / (1000 * 60 * 60 * 24));
		return betweenDays;
	}

	/**
	 * 获取2个Date型日期的分钟数差值
	 * 
	 * @param p_startDate
	 * @param p_endDate
	 * @return 分钟数差值
	 */
	public static long getMinutesOfTowDiffDate(Date p_startDate, Date p_endDate) {

		long l_startTime = p_startDate.getTime();
		long l_endTime = p_endDate.getTime();
		long betweenMinutes = (long) ((l_endTime - l_startTime) / (1000 * 60));
		return betweenMinutes;
	}

	/**
	 * 获取2个字符日期的天数差
	 * 
	 * @param p_startDate
	 * @param p_endDate
	 * @return 天数差
	 */
	public static long getDaysOfTowDiffDate(Date l_startDate, Date l_endDate) {

		long l_startTime = l_startDate.getTime();
		long l_endTime = l_endDate.getTime();
		long betweenDays = (long) ((l_endTime - l_startTime) / (1000 * 60 * 60 * 24));
		return betweenDays;
	}

	/**
	 * 给出日期添加一段时间后的日期
	 * 
	 * @param dateStr
	 * @param plus
	 * @return
	 */
	public static String getPlusDays(String format, String dateStr, long plus) {

		Date date = DateUtils.strToDate(format, dateStr);

		long time = date.getTime() + plus * 24 * 60 * 60 * 1000;

		return DateUtils.DateToStr(format, new Date(time));
	}

	/**
	 * 给出日期添加一段时间后的日期
	 * 
	 * @param dateStr
	 * @param plus
	 * @return
	 */
	public static String getPlusDays(String format, Date date, long plus) {

		long time = date.getTime() + plus * 24 * 60 * 60 * 1000;

		return DateUtils.DateToStr(format, new Date(time));
	}
	
	public static String MD5(String sourceStr) {
	    String result = "";
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(sourceStr.getBytes());
	        byte b[] = md.digest();
	        int i;
	        StringBuffer buf = new StringBuffer("");
	        for (int offset = 0; offset < b.length; offset++) {
	            i = b[offset];
	            if (i < 0)
	                i += 256;
	            if (i < 16)
	                buf.append("0");
	            buf.append(Integer.toHexString(i));
	        }
	        result = buf.toString();	      
	    } catch (NoSuchAlgorithmException e) {
	        System.out.println(e);
	    }
	    return result;
	}
}
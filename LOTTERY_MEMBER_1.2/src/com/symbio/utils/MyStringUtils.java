package com.symbio.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class MyStringUtils
{
	private static  int Num = 900001;
	/**
	 * iso8859-1转化为utf-8编码
	 * @param str
	 * @return
	 */
	public static String iso2utf(String str)
	{
		String result = StringUtils.stripToEmpty(str);
		try
		{
			result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 对会员卡号进行处理
	 */
	public static String getCard(String str){
		String card=str.substring(0,12);
		int lastSix=Integer.parseInt(str.substring(12,str.length()));
	    String last=six(lastSix);
		return card+last;
	}
	/**
	 * 对会员卡号最后6位进行处理
	 */
	public static String six(int num){
		num++;
		String str=num+"";
		while(str.contains("4")){	
			num++;	
			str=num+"";
		}
		return num+"";
	}
}

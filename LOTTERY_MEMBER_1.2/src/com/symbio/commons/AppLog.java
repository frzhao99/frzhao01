package com.symbio.commons;

import org.apache.log4j.Logger;

/**
 * 记录日志
 * @author Frzhao
 *
 */
public class AppLog {
	
	
	 
	 public static void debug(String info,Logger logger){
		 logger.debug(info);
	 }
	 
	 public static void info(String info,Logger logger){
		 logger.info(info);
	 }
	 
	 public static void error(String info,Logger logger){
		 logger.error(info);
	 }
	 
	 public static void warn(String info,Logger logger){
		 logger.warn(info);
	 }
	 
	 
	 
	 
	 
}

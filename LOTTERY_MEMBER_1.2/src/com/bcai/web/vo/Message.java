package com.bcai.web.vo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Message {
	/**
	 * 0代表失败
	 * 1代表成功
	 */
   private int code ;
   /**
    * 消息内容
    */
   private String context;

   
   
   public Message(int code, String context) {
	this.code = code;
	this.context = context;
   }

   public int getCode() {
	return code;
   }

   public void setCode(int code) {
	this.code = code;
   }

   public String getContext() {
	return context;
   }

   public void setContext(String context) {
	this.context = context;
   }
   
   /**
	 * ���
	 * 
	 * @param response
	 * @param message
	 */
	public static void print(HttpServletResponse response, Message message) {
		try {
			String json = "{\"code\":\"" + message.getCode()
					+ "\",\"context\":\"" + message.getContext() + "\"}";
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void print(HttpServletResponse response, String message) {
		try {		
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(message);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
   
   
}

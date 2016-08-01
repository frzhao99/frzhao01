package com.symbio.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;

public class SmsUtils {
	
	
	
	public static void sendSms(String phone,String code) throws IOException {
		
		
		//发送内容
		String content = "你好，修改资料验证码为："+code+"【正向科技】"; 
		
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://m.5c.com.cn/api/send/?");
		
		// APIKEY
		sb.append("apikey=6643753afcfca82c2e1986c033e75a07");

		//用户名
		sb.append("&username=18615782967");

		// 向StringBuffer追加密码
		sb.append("&password=18615782967");

		// 向StringBuffer追加手机号码
		sb.append("&mobile="+phone+"");

		// 向StringBuffer追加消息内容转URL标准码
		try {
			sb.append("&content="+URLEncoder.encode(content,"GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		// 创建url对象
		URL url = new URL(sb.toString());

		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");

		// 发送
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

		// 返回发送结果
		String inputline = in.readLine();


	}
	
	
	
	
}
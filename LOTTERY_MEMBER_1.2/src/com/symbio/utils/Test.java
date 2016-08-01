package com.symbio.utils;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		    String smtp = "smtpout.secureserver.net";  
		    String from = "service@bmfinn.com";  
		    String to = "568847312@qq.com";  
		    String copyto = "568847312@qq.com";  
		    String subject = "关于团队发展委员";  
		    String content = "你荣誉奖金已经发送，请留言查看";  
		    String username="service@bmfinn.com";  
		    String password="Hw359798199@";  
		    String filename = "";  
		    MailUtils.sendAndCc(smtp, from, to, copyto, subject, content, username, password, filename);  
	}

}

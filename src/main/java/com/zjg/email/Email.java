package com.zjg.email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class Email {

	public static void sendMail(String from,String toMail,String mailTitle,String mailContent	) throws MessagingException, UnsupportedEncodingException{
		String fromEmail = "1763608200@qq.com";
		String password = "bqqsfddmkqqngche";
		Properties props = new Properties();//加载一个配置文件
		props.put("mail.smtp.host", "smtp.qq.com");//存储发送邮件服务器的信息,qq为例，如果是163则是smtp.163.com
		props.put("mail.transport.protocol","smtp");//使用smtp简单邮件传输协议
		props.put("mail.smtp.auth", "true");//是否需要身份验证
		props.put("mail.smtp.ssl.enable", "true");//QQ邮箱的SSL加密,不设置出现530 Error: A secure connection is requiered(such as ssl)错误
		Session session = Session.getInstance(props);
		session.setDebug(true);//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态

		MimeMessage msg = new MimeMessage(session);//由邮件会话创建一个扩展信息对象，包装信息格式，可以只是简单文本
//		msg.setFrom(new InternetAddress(fromMail));//设置发件人的地址
		//自定义昵称
		String nick = MimeUtility.encodeText(from);//防止乱码
		msg.setFrom(new InternetAddress(nick+"<"+fromEmail+">"));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));//设置收件人，并设置其接受类型为to
		msg.setSubject(mailTitle);//设置标题
		msg.setContent(mailContent,"text/html;charset=UTF-8");//设置为html格式，可以发送多种样式
//		msg.setSentDate(new Date());	//设置发信时间
		msg.saveChanges(); //存储邮件信息
		
		Transport tran = session.getTransport("smtp");//使用smtp协议获取session环境的Transprot对象来发送邮件 javamial使用Transport对象来管理发送邮件服务
		tran.connect(props.getProperty("mail.smtp.host"),fromEmail,password);//链接邮箱服务器，发送邮件的邮箱，以及授权码
		tran.sendMessage(msg, msg.getAllRecipients());//发送邮件，getAllRecipients()是所有已设好的收件人地址
		tran.close();
	}
	
	
	public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
		sendMail("自定义昵称","1763608200@qq.com",
				"title",
				"<span style='color:red;'>下雨了_简</span>的博客，欢迎交流");
	}
}

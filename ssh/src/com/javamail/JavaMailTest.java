package com.javamail;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;
import com.util.PropertiesUtil;
/**
 *  Java 基于JavaMail实现向QQ邮箱发送邮件
 *  该类必须在jdk1.7以上的版本上运行，如果版本过低的话可以下载javax.mail.jar,mail.jar就可以了
 *  参考路径：http://blog.csdn.net/never_cxb/article/details/50543289
 * @author user
 */
public class JavaMailTest {
	public static void main(String[] args) throws Exception{
		/*Properties props = new Properties();
		// 开启debug调试
		props.setProperty("mail.debug", "true");
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		// 设置邮件服务器主机名
		props.setProperty("mail.host", "smtp.qq.com");
		// 发送邮件协议名称
		props.setProperty("mail.transport.protocol", "smtp");
		// 设置ssl
		MailSSLSocketFactory ssl = new MailSSLSocketFactory();
		ssl.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", ssl);
		
		Session session = Session.getInstance(props);

		//邮件内容部分
		Message msg = new MimeMessage(session);
		msg.setSubject("seenews 错误");
		StringBuilder builder = new StringBuilder();
		builder.append("url = " + "http://blog.csdn.net/never_cxb/article/details/50524571");
		builder.append("页面爬虫错误");
		builder.append("\n data " + System.currentTimeMillis());
		msg.setText(builder.toString());
		//邮件发送者
		msg.setFrom(new InternetAddress("nutterzen@qq.com"));

		//发送邮件
		Transport transport = session.getTransport();
		transport.connect("smtp.qq.com", "nutterzen@qq.com", "uwpmzftcdulkbfba");

		transport.sendMessage(msg, new Address[] { new InternetAddress("2281558656@qq.com") });
		transport.close();
		System.out.println("邮件发送成功！");*/
		
		Properties prop = System.getProperties();
		//开启debug模式
		prop.setProperty("mail.debug", "true");
		//设置邮件发送服务器地址
		prop.setProperty("mail.smtp.host", "smtp.qq.com");
		//设置邮件发送服务器端口
		prop.setProperty("mail.smtp.port", "465");
		//设置是否验证（一般企业邮箱都需要验证）
		prop.setProperty("mail.smtp.auth", "true");
		//设置ssl加密
		MailSSLSocketFactory ssl  = new MailSSLSocketFactory();
		ssl.setTrustAllHosts(true);
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.socketFactory", ssl);
		//使用授权码
		EmailAuthenticator authenticator = new EmailAuthenticator("nutterzen@qq.com","uwpmzftcdulkbfba");
		Session session = Session.getInstance(prop, authenticator);
		Message message = new MimeMessage(session);
		message.setSubject("java mail邮件测试");
		message.setContent("今天是星期一，又得开始上班了！！好烦人啊！！！","text/html;charset=utf-8");
		message.setFrom(new InternetAddress("nutterzen@qq.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("nutterzen@qq.com"));
		message.setSentDate(new Date());
		Transport transport = session.getTransport("smtp");
//		transport.send(message,message.getAllRecipients());
		transport.send(message);
		transport.close();
		System.out.println("邮件发送成功！");
	}
}
class EmailAuthenticator extends Authenticator{
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public EmailAuthenticator(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username,password);
	}
}

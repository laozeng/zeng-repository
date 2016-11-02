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
 *  Java ����JavaMailʵ����QQ���䷢���ʼ�
 *  ���������jdk1.7���ϵİ汾�����У�����汾���͵Ļ���������javax.mail.jar,mail.jar�Ϳ�����
 *  �ο�·����http://blog.csdn.net/never_cxb/article/details/50543289
 * @author user
 */
public class JavaMailTest {
	public static void main(String[] args) throws Exception{
		/*Properties props = new Properties();
		// ����debug����
		props.setProperty("mail.debug", "true");
		// ���ͷ�������Ҫ�����֤
		props.setProperty("mail.smtp.auth", "true");
		// �����ʼ�������������
		props.setProperty("mail.host", "smtp.qq.com");
		// �����ʼ�Э������
		props.setProperty("mail.transport.protocol", "smtp");
		// ����ssl
		MailSSLSocketFactory ssl = new MailSSLSocketFactory();
		ssl.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", ssl);
		
		Session session = Session.getInstance(props);

		//�ʼ����ݲ���
		Message msg = new MimeMessage(session);
		msg.setSubject("seenews ����");
		StringBuilder builder = new StringBuilder();
		builder.append("url = " + "http://blog.csdn.net/never_cxb/article/details/50524571");
		builder.append("ҳ���������");
		builder.append("\n data " + System.currentTimeMillis());
		msg.setText(builder.toString());
		//�ʼ�������
		msg.setFrom(new InternetAddress("nutterzen@qq.com"));

		//�����ʼ�
		Transport transport = session.getTransport();
		transport.connect("smtp.qq.com", "nutterzen@qq.com", "uwpmzftcdulkbfba");

		transport.sendMessage(msg, new Address[] { new InternetAddress("2281558656@qq.com") });
		transport.close();
		System.out.println("�ʼ����ͳɹ���");*/
		
		Properties prop = System.getProperties();
		//����debugģʽ
		prop.setProperty("mail.debug", "true");
		//�����ʼ����ͷ�������ַ
		prop.setProperty("mail.smtp.host", "smtp.qq.com");
		//�����ʼ����ͷ������˿�
		prop.setProperty("mail.smtp.port", "465");
		//�����Ƿ���֤��һ����ҵ���䶼��Ҫ��֤��
		prop.setProperty("mail.smtp.auth", "true");
		//����ssl����
		MailSSLSocketFactory ssl  = new MailSSLSocketFactory();
		ssl.setTrustAllHosts(true);
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.socketFactory", ssl);
		//ʹ����Ȩ��
		EmailAuthenticator authenticator = new EmailAuthenticator("nutterzen@qq.com","uwpmzftcdulkbfba");
		Session session = Session.getInstance(prop, authenticator);
		Message message = new MimeMessage(session);
		message.setSubject("java mail�ʼ�����");
		message.setContent("����������һ���ֵÿ�ʼ�ϰ��ˣ����÷��˰�������","text/html;charset=utf-8");
		message.setFrom(new InternetAddress("nutterzen@qq.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("nutterzen@qq.com"));
		message.setSentDate(new Date());
		Transport transport = session.getTransport("smtp");
//		transport.send(message,message.getAllRecipients());
		transport.send(message);
		transport.close();
		System.out.println("�ʼ����ͳɹ���");
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

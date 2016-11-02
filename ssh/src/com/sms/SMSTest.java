package com.sms;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.sms.interfacej.*;
import com.sms.tool.SmsClientAccessTool;

/**
 * ���Ͷ��Ų�����
 * @author user
 */
public class SMSTest {
	public static String url = "http://115.29.242.32:8888/sms.aspx";
	public static String userid = "972";
	public static String account = "SLYS";
	public static String password = "741852";
	public static void main(String[] args) {
		SendSMSThread sendSMS  = new SendSMSThread("13182928319", "ganzi");
		sendSMS.run();
		SmsClientSend sms = new SmsClientSend();
		String result = SmsClientSend.sendSms(url, userid, account, password, "13182928319",
				"��������������������������������ϵ��ֻ��������֤��:123456����Ǳ��˲�������ԣ�");
		System.out.println("result:"+result);
		/*String result = "<?xml version='1.0' encoding='utf-8' ?><returnsms>"+
						 "<returnstatus>Success</returnstatus>"+
						 "<message>ok</message>"+
						 "<remainpoint>17</remainpoint>"+
						 "<taskID>20816454</taskID>"+
						 "<successCounts>1</successCounts></returnsms>";
		Map<String, Object> map = parseXMLString(result);
		for(String name:map.keySet()){
			String value = (String) map.get(name);
			System.out.println(name+"----->"+value);
		}*/
	}
	
	//����xml�ַ���,����map�����Լ�ֵ����ʽ����
	public static Map<String, Object> parseXMLString(String xml){
		Map<String, Object> map = new HashMap<>();
		try {
			//�����ַ�������document�ĵ�����
			Document document = DocumentHelper.parseText(xml);
			//��ȡ���ڵ�
			Element root = document.getRootElement();
			//ѭ���������µĽڵ�
			for (Iterator it = root.elementIterator(); it.hasNext();) {
				//��ȡ�ӽڵ����
				Element element = (Element) it.next();
				//��ȡ�ڵ�����
				String nodeName = element.getName();
				//��ȡ�ڵ�ֵ
				String nodeValue = element.getText();
				map.put(nodeName, nodeValue);
			}
			return map;
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
	}
}
class SendSMSThread implements Runnable {  // ���Ͷ����߳�
	private String mobile;
	private String content;
	
	public SendSMSThread(String mobile, String content) {	
		this.mobile = mobile;
		this.content = content;
	}

	public void run() {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://sdk2.entinfo.cn/z_send.aspx");
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// ��ͷ�ļ�������ת��
        //Uid=SDK-WSZ-010-00327
        //Key=206110
        NameValuePair[] data = { 
                new NameValuePair("sn", "SDK-WSZ-010-00327"),
                new NameValuePair("pwd", "206110"),
                new NameValuePair("mobile", mobile),
                new NameValuePair("content", "�����ӡ�"+content+"�����ӡ�")
           };
        post.setRequestBody(data);
        try {
			client.executeMethod(post);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

        try {
			String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
			System.out.println("���ŷ���ֵ��" + result);
			System.out.println("�ֻ����룺" + mobile + "  \n\r  ���ݣ�" + content);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        post.releaseConnection();
	}
}


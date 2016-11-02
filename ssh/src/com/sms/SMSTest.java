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
 * 发送短信测试类
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
				"【世界村汽车】请查收您在世界村汽车上的手机解除绑定验证码:123456；如非本人操作请忽略！");
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
	
	//解析xml字符串,返回map对象，以键值对形式返回
	public static Map<String, Object> parseXMLString(String xml){
		Map<String, Object> map = new HashMap<>();
		try {
			//根据字符串创建document文档对象
			Document document = DocumentHelper.parseText(xml);
			//获取根节点
			Element root = document.getRootElement();
			//循环迭代其下的节点
			for (Iterator it = root.elementIterator(); it.hasNext();) {
				//获取子节点对象
				Element element = (Element) it.next();
				//获取节点名称
				String nodeName = element.getName();
				//获取节点值
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
class SendSMSThread implements Runnable {  // 发送短信线程
	private String mobile;
	private String content;
	
	public SendSMSThread(String mobile, String content) {	
		this.mobile = mobile;
		this.content = content;
	}

	public void run() {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://sdk2.entinfo.cn/z_send.aspx");
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        //Uid=SDK-WSZ-010-00327
        //Key=206110
        NameValuePair[] data = { 
                new NameValuePair("sn", "SDK-WSZ-010-00327"),
                new NameValuePair("pwd", "206110"),
                new NameValuePair("mobile", mobile),
                new NameValuePair("content", "【杆子】"+content+"【杆子】")
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
			System.out.println("短信返回值：" + result);
			System.out.println("手机号码：" + mobile + "  \n\r  内容：" + content);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        post.releaseConnection();
	}
}


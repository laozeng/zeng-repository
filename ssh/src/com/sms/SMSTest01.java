package com.sms;

import com.sms.interfacej.SmsClientKeyword;
import com.sms.interfacej.SmsClientOverage;
import com.sms.tool.SmsClientAccessTool;

/**
 * ��������ҵ�������
 * @author user
 */
public class SMSTest01 {
	public static String url = "http://115.29.242.32:8888/sms.aspx";
	public static String userid = "972";
	public static String account = "SLYS";
	public static String password = "741852";
	public static String checkWord = "��������������������������������ϵ��ֻ��������֤��:123456����Ǳ��˲�������ԣ�";

	public static void main(String[] args) {
		keyword();
		overage();
	}

	public static void keyword() {
		String keyword = SmsClientKeyword.queryKeyWord(url, userid, account,password, checkWord);
		System.out.println(keyword);
	}

	public static void overage() {
		String overage = SmsClientOverage.queryOverage(url, userid, account,password);
		System.out.println(overage);
	}

	public static void test() {
		String send = SmsClientAccessTool.getInstance().doAccessHTTPPost("http://118.145.30.35/sms.aspx", "", "utf-8");
		System.out.println(send);
	}
}

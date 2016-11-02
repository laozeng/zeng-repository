package com.sms.interfacej;

import java.net.URLEncoder;

import com.sms.tool.SmsClientAccessTool;

/**
 * <p>
 * <date>2012-03-01</date><br/>
 * <span>��ά�ṩ��JAVA�ӿ���Ϣ�����ţ����ţ�����API</span><br/>
 * <span>----------��ѯ���-------------</span>
 * </p>
 * 
 * @author LIP
 * @version 1.0.1
 */
public class SmsClientOverage {

	/**
	 * <p>
	 * <date>2012-03-01</date><br/>
	 * <span>����ȡ����1--���봫���������</span><br/>
	 * <p>
	 * ��һ�����ͷ�ʽ��Ĭ��ΪPOST<br/>
	 * ������������ݱ��뷽ʽ��Ĭ��ΪUTF-8
	 * </p>
	 * <br/>
	 * </p>
	 * 
	 * @param url
	 *            ������--�������ӵ�ַURL--����>http://118.145.30.35/sms.aspx
	 * @param userid
	 *            ������--�û�ID��Ϊ����
	 * @param account
	 *            ������--�û��ʺ�
	 * @param password
	 *            ������--�û�����
	 * @return ��������ѯ�ַ���
	 */
	public static String queryOverage(String url, String userid,
			String account, String password) {

		try {
			StringBuffer sendParam = new StringBuffer();
			sendParam.append("action=overage");
			sendParam.append("&userid=").append(userid);
			sendParam.append("&account=").append(
					URLEncoder.encode(account, "UTF-8"));
			sendParam.append("&password=").append(
					URLEncoder.encode(password, "UTF-8"));

			return SmsClientAccessTool.getInstance().doAccessHTTPPost(url,
					sendParam.toString(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "δ���ͣ��쳣-->" + e.getMessage();
		}
	}
}

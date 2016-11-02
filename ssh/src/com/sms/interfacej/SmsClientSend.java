package com.sms.interfacej;

import java.net.URLEncoder;

import com.sms.tool.SmsClientAccessTool;

/**
 * <p>
 * <date>2012-03-01</date><br/>
 * <span>��ά�ṩ��JAVA�ӿ���Ϣ�����ţ����ţ�����API</span><br/>
 * <span>----------���Ͷ���-------------</span>
 * </p>
 * @author LIP
 * @version 1.0.1
 */
public class SmsClientSend {
	/**
	 * <p>
	 * <date>2012-03-01</date><br/>
	 * <span>������Ϣ����1--���봫���������</span><br/>
	 * <p>
	 * ��һ�����ͷ�ʽ��Ĭ��ΪPOST<br/>
	 * ������������ݱ��뷽ʽ��Ĭ��ΪUTF-8
	 * </p>
	 * <br/>
	 * </p>
	 * @param url������--�������ӵ�ַURL--����>http://118.145.30.35/sms.aspx
	 * @param userid������--�û�ID��Ϊ����
	 * @param account������--�û��ʺ�
	 * @param password������--�û�����
	 * @param mobile������--���͵��ֻ����룬��������ö��Ÿ�����>13512345678,13612345678
	 * @param content������--ʵ�ʷ������ݣ�
	 * @return ���ط�����Ϣ֮�󷵻��ַ���
	 */
	public static String sendSms(String url, String userid, String account,
			String password, String mobile, String content) {
		return sendSms(url, userid, account, password, mobile, content, null,
				null, null, null, null, null, null, "POST", "UTF-8", "UTF-8");
	}

	/**
	 * <p>
	 * <date>2012-03-01</date><br/>
	 * <span>������Ϣ����--��ʱ˽�л�������������ṩ�û��ӿڶ��ѡ���ʵ�ò�����ô����</span><br/>
	 * <span>������Ϣ���յ�������磺http://118.145.30.35/sms.aspx?action=send</span>
	 * </p>
	 * @param url������--�������ӵ�ַURL--����>http://118.145.30.35/sms.aspx
	 * @param userid������--�û�ID��Ϊ����
	 * @param account������--�û��ʺ�
	 * @param password������--�û�����
	 * @param mobile������--���͵��ֻ����룬��������ö��Ÿ�����>13512345678,13612345678
	 * @param content������--ʵ�ʷ������ݣ�
	 * @param action��ѡ��--���ʵ��¼���Ĭ��Ϊsend
	 * @param sendTime��ѡ��--��ʱ����ʱ�䣬������Ϊ�������ͣ�ʱ���ʽ��>2011-11-11 11:11:11
	 * @param checkContent��ѡ��--����Ƿ�����Ƿ��ؼ��֣�1--��ʾ��Ҫ��飬0--��ʾ�����
	 * @param taskName��ѡ��--�������ƣ���������������100����
	 * @param countNumber��ѡ��--�ύ��������
	 * @param mobileNumber��ѡ��--�ֻ���������
	 * @param telephoneNumber��ѡ��--С��ͨ���ͣ�����������
	 * @param sendType��ѡ��--���ͷ�ʽ��Ĭ��ΪPOST
	 * @param codingType��ѡ��--�������ݱ��뷽ʽ��Ĭ��ΪUTF-8
	 * @param backEncodType��ѡ��--�������ݱ��뷽ʽ��Ĭ��ΪUTF-8
	 * @return ���ط���֮���յ�����Ϣ
	 */
	private static String sendSms(String url, String userid, String account,
			String password, String mobile, String content, String action,
			String sendTime, String checkContent, String taskName,
			String countNumber, String mobileNumber, String telephoneNumber,
			String sendType, String codingType, String backEncodType) {
		try {
			if (codingType == null || codingType.equals("")) {
				codingType = "UTF-8";
			}
			if (backEncodType == null || backEncodType.equals("")) {
				backEncodType = "UTF-8";
			}
			StringBuffer send = new StringBuffer();
			if (action != null && !action.equals("")) {
				send.append("action=").append(action);
			} else {
				send.append("action=send");
			}
			send.append("&userid=").append(userid);
			send.append("&account=").append(
					URLEncoder.encode(account, codingType));
			send.append("&password=").append(
					URLEncoder.encode(password, codingType));
			send.append("&mobile=").append(mobile);
			send.append("&content=").append(
					URLEncoder.encode(content, codingType));
			if (sendTime != null && !sendTime.equals("")) {
				send.append("&sendTime=").append(
						URLEncoder.encode(sendTime, codingType));
			}
			if (checkContent != null && !checkContent.equals("")) {
				send.append("&checkContent=").append(checkContent);
			}
			if (taskName != null && !taskName.equals("")) {
				send.append("&taskName=").append(
						URLEncoder.encode(taskName, codingType));
			}
			if (countNumber != null && !countNumber.equals("")) {
				send.append("&countNumber=").append(countNumber);
			}
			if (mobileNumber != null && !mobileNumber.equals("")) {
				send.append("&mobileNumber=").append(mobileNumber);
			}
			if (telephoneNumber != null && !telephoneNumber.equals("")) {
				send.append("&telephoneNumber=").append(telephoneNumber);
			}

			if (sendType != null && (sendType.toLowerCase()).equals("get")) {
				return SmsClientAccessTool.getInstance().doAccessHTTPGet(
						url + "?" + send.toString(), backEncodType);
			} else {
				return SmsClientAccessTool.getInstance().doAccessHTTPPost(url,
						send.toString(), backEncodType);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "δ���ͣ������쳣";
		}
	}

}

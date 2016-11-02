package com.sms.interfacej;

import java.net.URLEncoder;

import com.sms.tool.SmsClientAccessTool;

/**
 * <p>
 * <date>2012-03-01</date><br/>
 * <span>软维提供的JAVA接口信息（短信，彩信）调用API</span><br/>
 * <span>----------非法关键字/屏蔽字符查询--暂未提供-------------</span>
 * </p>
 * 
 * @author LIP
 * @version 1.0.1
 */
public class SmsClientKeyword {

	/**
	 * /**
	 * <p>
	 * <date>2012-03-01</date><br/>
	 * <span>是否包含关键字获取方法1--必须传入必填内容</span><br/>
	 * <p>
	 * 其一：发送方式，默认为POST<br/>
	 * 其二：发送内容编码方式，默认为UTF-8
	 * </p>
	 * <br/>
	 * </p>
	 * 
	 * @param url
	 *            ：必填--发送连接地址URL--比如>http://118.145.30.35/sms.aspx
	 * @param userid
	 *            ：必填--用户ID，为数字
	 * @param account
	 *            ：必填--用户帐号
	 * @param password
	 *            ：必填--用户密码
	 * @param checkWord
	 *            ：必填--需要检查的字符串--比如：这个字符串中是否包含了屏蔽字
	 * @return 返回需要查询的字符串中是否包含关键字
	 */
	public static String queryKeyWord(String url, String userid,
			String account, String password, String checkWord) {

		try {
			StringBuffer sendParam = new StringBuffer();
			sendParam.append("action=checkkeyword");
			sendParam.append("&userid=").append(userid);
			sendParam.append("&account=").append(
					URLEncoder.encode(account, "UTF-8"));
			sendParam.append("&password=").append(
					URLEncoder.encode(password, "UTF-8"));
			if (checkWord != null && !checkWord.equals("")) {
				sendParam.append("&content=").append(
						URLEncoder.encode(checkWord, "UTF-8"));
			} else {
				return "需要检查的字符串不能为空";
			}
			System.out.println(sendParam);

			return SmsClientAccessTool.getInstance().doAccessHTTPPost(url,
					sendParam.toString(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "未发送，异常-->" + e.getMessage();
		}
	}
}

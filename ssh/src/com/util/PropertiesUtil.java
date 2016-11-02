package com.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * ����properties�����ļ�������,ע�⣺�������ļ�������src����classes�ļ���֮��
 * @author user
 */
public class PropertiesUtil {
	private Properties properties = null;
	private InputStream is = null;
	private String propertiesName;
	private Class clz;
	/**
	 * ���캯����������������һ�������ļ������ƣ��ڶ�����������ǰ�����
	 * @param propertiesName
	 * @param clz
	 */
	public PropertiesUtil(String propertiesName,Class clz) {
		this.propertiesName = propertiesName;
		this.clz = clz;
		try {
			properties = new Properties();
			is = clz.getClassLoader().getResourceAsStream(propertiesName);
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//ͨ��key��ȡֵ
	public String getValue(String key){
		String value = "";
		value = (String) properties.get(key);
		return value;
	}
	
	//д���ֵ��
	public void writeProperties(String key,String value){
		OutputStream os = null;
		try {
			//��������·������ο�·����http://blog.csdn.net/antswallow/article/details/5470752
			//�����ַ�ʽ����,ֻ��ı�D:\��̫��\ssh\build\classes\config.properties����ļ�,��������е��ļ�
//			String path = PropertiesUtil.class.getClassLoader().getResource(propertiesName).getFile();
			String path = Thread.currentThread().getContextClassLoader().getResource(propertiesName).getFile();
			os = new FileOutputStream(URLDecoder.decode(path,"utf-8"));
			properties.setProperty(key, value);
			properties.store(os, key);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getPropertiesName() {
		return propertiesName;
	}

	public void setPropertiesName(String propertiesName) {
		this.propertiesName = propertiesName;
	}

	public Class getClz() {
		return clz;
	}

	public void setClz(Class clz) {
		this.clz = clz;
	}
}

package com.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * 解析properties配置文件工具类,注意：该配置文件必须在src，即classes文件夹之下
 * @author user
 */
public class PropertiesUtil {
	private Properties properties = null;
	private InputStream is = null;
	private String propertiesName;
	private Class clz;
	/**
	 * 构造函数，两个参数：第一个代表文件的名称，第二个参数代表当前类对象
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
	
	//通过key获取值
	public String getValue(String key){
		String value = "";
		value = (String) properties.get(key);
		return value;
	}
	
	//写入键值对
	public void writeProperties(String key,String value){
		OutputStream os = null;
		try {
			//处理中文路径问题参考路径：http://blog.csdn.net/antswallow/article/details/5470752
			//这两种方式都行,只会改变D:\红太阳\ssh\build\classes\config.properties这个文件,即编译后中的文件
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

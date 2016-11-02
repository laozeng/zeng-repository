package com.design.create_model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ԭ��ģʽ(prototype)����
 * �ο�·����http://www.cnblogs.com/maowang1991/archive/2013/04/15/3023236.html
 * �ο�·����http://zz563143188.iteye.com/blog/1845469
 * @author user
 */
public class PrototypeTest {
	
	public static void main(String[] args) throws Exception{
		Prototype prototype1 = new Prototype();
		Prototype prototype2 = prototype1;//(Prototype) prototype1.deepClone();
		prototype2.names = new String[]{"����","����"};
		Prototype prototype3 = prototype1;//(Prototype) prototype1.clone();
		prototype3.names = new String[]{"����"};
		System.out.println("prototype1:"+prototype1.names.length);
		System.out.println("prototype2:"+prototype2.names.length);
		System.out.println("prototype3:"+prototype3.names.length);
	}
}

//����ԭ����,��Ҫʵ��Cloneable�ӿڣ���ʵ�Ͽ�¡�������Ʋ�һ��ҪΪclone()
class Prototype implements Cloneable,Serializable{
	public String[] names = new String[]{"����","����","������"};
	//ǳ����:��һ�������ƺ󣬻����������͵ı����������´��������������ͣ�ָ��Ļ���ԭ������ָ��ġ�
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Prototype prototype = (Prototype) super.clone();
		return prototype;
	}
	//���:��һ�������ƺ󣬲����ǻ����������ͻ����������ͣ��������´����ġ�����˵��������ƽ�������ȫ���׵ĸ��ƣ���ǳ���Ʋ����ס�
	public Object deepClone() throws Exception{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		bos.close();
		oos.close();
		bis.close();
		ois.close();
		return ois.readObject();
	}
}
package com.design.create_model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 原型模式(prototype)测试
 * 参考路径：http://www.cnblogs.com/maowang1991/archive/2013/04/15/3023236.html
 * 参考路径：http://zz563143188.iteye.com/blog/1845469
 * @author user
 */
public class PrototypeTest {
	
	public static void main(String[] args) throws Exception{
		Prototype prototype1 = new Prototype();
		Prototype prototype2 = prototype1;//(Prototype) prototype1.deepClone();
		prototype2.names = new String[]{"曾昌","曾杰"};
		Prototype prototype3 = prototype1;//(Prototype) prototype1.clone();
		prototype3.names = new String[]{"曾昌"};
		System.out.println("prototype1:"+prototype1.names.length);
		System.out.println("prototype2:"+prototype2.names.length);
		System.out.println("prototype3:"+prototype3.names.length);
	}
}

//定义原型类,需要实现Cloneable接口，事实上克隆方法名称不一定要为clone()
class Prototype implements Cloneable,Serializable{
	public String[] names = new String[]{"曾昌","曾杰","曾慧子"};
	//浅复制:将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Prototype prototype = (Prototype) super.clone();
		return prototype;
	}
	//深复制:将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。
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
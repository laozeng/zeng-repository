package com.design.create_model;
/**
 * 单例模式(singleton)测试
 * @author user
 */
public class SingletonTest {
	public static void main(String[] args) {
		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton1 == singleton2);
		System.out.println(singleton1.equals(singleton2));
	}
}
class Singleton{
	private static Singleton instance;
	//构建私有无惨方法，防止被实例化
	private Singleton(){}
	//提供公共的静态的获取实例的方法，以确保整个jvm中只有一个该类的实例
	public static Singleton getInstance(){
		if(instance == null){
			instance = new Singleton();
		}
		return instance;
	}
}

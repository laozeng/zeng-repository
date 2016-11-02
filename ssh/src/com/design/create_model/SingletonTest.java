package com.design.create_model;
/**
 * ����ģʽ(singleton)����
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
	//����˽���޲ҷ�������ֹ��ʵ����
	private Singleton(){}
	//�ṩ�����ľ�̬�Ļ�ȡʵ���ķ�������ȷ������jvm��ֻ��һ�������ʵ��
	public static Singleton getInstance(){
		if(instance == null){
			instance = new Singleton();
		}
		return instance;
	}
}

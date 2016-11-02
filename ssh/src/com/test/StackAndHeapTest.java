package com.test;
/**
 * Java��ջ������
 * �ο�·����http://blog.csdn.net/qh_java/article/details/9084091
 * ��ջ������ջ�з��Ż����������͵�ʵ��ֵ�������������͵�ָ��(·��)�������з��������������͵�ʵ�ʶ���
 * �ο�·����http://blog.csdn.net/olanlanxiari/article/details/8104505
 * �����ؼ�����java�л������͵İ�װ��Ĵ󲿷ֶ�ʵ���˳����ؼ�������Щ����Byte,Short,Integer,Long,Character,Boolean,�������ָ��������͵İ�װ����û��ʵ�֡�
 * 			����Byte,Short,Integer,Long,Character��5�����͵İ�װ��Ҳֻ���ڶ�Ӧ-128����127ʱ�ſ�ʹ�ö���أ�Ҳ�����󲻸��𴴽��͹������127����С��-128����Щ��Ķ���
 * @author user
 */
public class StackAndHeapTest {
	public static void main(String[] args) {
		//number��ֵ����ı���Ȼ����������������,����˴���String���͵�����Ҳ����ı�
		Float number = 0f;
		changeNumber(number);
		System.out.println(number);
		String name = new String("zc");
		changeName(name);
		System.out.println(name);
		StringBuffer sb = new StringBuffer("zc");
		changeName(sb);
		System.out.println(sb);
		
		//i1��i2����ͬ����Ϊ���Ѿ���ʾ���ڶ���ʵ������һ������
		Integer i1 = new Integer(12);
		Integer i2 = new Integer(12);
		System.out.println(i1 == i2);
		
		//i3��i4��ͬ����Ϊ���õ��˳����ؼ���
		Integer i3 = 12;
		Integer i4 = 12;
		System.out.println(i3 == i4);
		
		//i5��i6����ͬ����Ϊ�����ؼ���ֻά��-128����127֮�������ֵ
		Integer i5 = 128;
		Integer i6 = 128;
		System.out.println(i5 == i6);
		
		//d1��d2����ͬ����ΪDoubleû���õ������ؼ���
		Double d1 = 12.0;
		Double d2 = 12.0;
		System.out.println(d1 == d2);
		
		//f1��f2����ͬ����ΪFloatû���õ������ؼ���
		Float f1 = 12F;
		Float f2 = 12F;
		System.out.println(f1 == f2);
		
		//s1��s2��ͬ��StringҲ�õ��˳����ؼ���
		String s1 = "abc";
		String s2 = "abc";
		System.out.println(s1 == s2);
	}
	
	public static void changeNumber(Float number){
		number = 12f;
	}
	
	public static void changeName(String name){
		//name += " is changed!";
		name = name.concat(" is changed!");
	}
	
	public static void changeName(StringBuffer name){
		name.append(" is changed!");
	}
}

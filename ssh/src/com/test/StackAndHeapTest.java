package com.test;
/**
 * Java堆栈测试类
 * 参考路径：http://blog.csdn.net/qh_java/article/details/9084091
 * 堆栈的区别：栈中放着基本数据类型的实际值和引用数据类型的指针(路径)，而堆中放着引用数据类型的实际对象
 * 参考路径：http://blog.csdn.net/olanlanxiari/article/details/8104505
 * 常量池技术：java中基本类型的包装类的大部分都实现了常量池技术，这些类是Byte,Short,Integer,Long,Character,Boolean,另外两种浮点数类型的包装类则没有实现。
 * 			另外Byte,Short,Integer,Long,Character这5种整型的包装类也只是在对应-128——127时才可使用对象池，也即对象不负责创建和管理大于127或者小于-128的这些类的对象。
 * @author user
 */
public class StackAndHeapTest {
	public static void main(String[] args) {
		//number的值不会改变虽然他是引用数据类型,如果此处是String类型的数据也不会改变
		Float number = 0f;
		changeNumber(number);
		System.out.println(number);
		String name = new String("zc");
		changeName(name);
		System.out.println(name);
		StringBuffer sb = new StringBuffer("zc");
		changeName(sb);
		System.out.println(sb);
		
		//i1和i2不相同，因为他已经显示的在堆中实例化了一个区域
		Integer i1 = new Integer(12);
		Integer i2 = new Integer(12);
		System.out.println(i1 == i2);
		
		//i3和i4相同，因为她用到了常量池技术
		Integer i3 = 12;
		Integer i4 = 12;
		System.out.println(i3 == i4);
		
		//i5和i6不相同，因为常量池技术只维持-128——127之间的数据值
		Integer i5 = 128;
		Integer i6 = 128;
		System.out.println(i5 == i6);
		
		//d1和d2不相同，因为Double没有用到常量池技术
		Double d1 = 12.0;
		Double d2 = 12.0;
		System.out.println(d1 == d2);
		
		//f1和f2不相同，因为Float没有用到常量池技术
		Float f1 = 12F;
		Float f2 = 12F;
		System.out.println(f1 == f2);
		
		//s1和s2相同，String也用到了常量池技术
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

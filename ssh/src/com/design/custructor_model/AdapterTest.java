package com.design.custructor_model;

/**
 * 适配器模式：分为类适配器模式、对象适配器模式、接口适配器模式三种
 * 核心思想：将Source中的method1方法通过adapter转移到Targetable接口中
 * @author user
 */
public class AdapterTest {
	public static void main(String[] args) {
		/*//类适配器模式,这样一来targetable接口就有了Source类中的功能了
		Targetable targetable = new Adapter();
		targetable.method1();
		targetable.method2();
		
		//对象适配模式
		Source source = new Source();
		Targetable target = new Wrapper(source);
		target.method1();
		target.method2();*/
		
		//接口适配器模式
		Targetable1 targetable1 = new SubTargetable1(); 
		Targetable1 targetable2 = new SubTargetable2(); 
		targetable1.method1();
		targetable1.method2();
		targetable2.method1();
		targetable2.method2();
	}
}

class Source{
	public void method1(){
		System.out.println("Source.method1...");
	}
}

interface Targetable{
	//与source类中的方法名称相同
	public void method1();
	//自身的方法
	public void method2();
}

//类适配器模式
class Adapter extends Source implements Targetable{
	@Override
	public void method2() {
		System.out.println("Adapter.method2...");
	}
}

//对象适配器模式
class Wrapper implements Targetable{
	private Source source;
	public Wrapper(Source source){
		this.source = source;
	}
	@Override
	public void method1() {
		source.method1();
	}
	@Override
	public void method2() {
		System.out.println("wrapper.method2...");
	}
}


//接口适配器   抽象类
abstract class Targetable1 implements Targetable{
	public void method1(){};
	public void method2(){};
}

class SubTargetable1 extends Targetable1{
	@Override
	public void method1() {
		System.out.println("SubTargetable1.method1...");
	}
}

class SubTargetable2 extends Targetable1{
	@Override
	public void method2() {
		System.out.println("SubTargetable2.method2...");
	}
}
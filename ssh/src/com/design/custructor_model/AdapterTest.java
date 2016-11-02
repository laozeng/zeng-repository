package com.design.custructor_model;

/**
 * ������ģʽ����Ϊ��������ģʽ������������ģʽ���ӿ�������ģʽ����
 * ����˼�룺��Source�е�method1����ͨ��adapterת�Ƶ�Targetable�ӿ���
 * @author user
 */
public class AdapterTest {
	public static void main(String[] args) {
		/*//��������ģʽ,����һ��targetable�ӿھ�����Source���еĹ�����
		Targetable targetable = new Adapter();
		targetable.method1();
		targetable.method2();
		
		//��������ģʽ
		Source source = new Source();
		Targetable target = new Wrapper(source);
		target.method1();
		target.method2();*/
		
		//�ӿ�������ģʽ
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
	//��source���еķ���������ͬ
	public void method1();
	//����ķ���
	public void method2();
}

//��������ģʽ
class Adapter extends Source implements Targetable{
	@Override
	public void method2() {
		System.out.println("Adapter.method2...");
	}
}

//����������ģʽ
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


//�ӿ�������   ������
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
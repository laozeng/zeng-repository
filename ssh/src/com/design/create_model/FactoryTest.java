package com.design.create_model;
/**
 * 工厂模式
 * @author user
 */
public class FactoryTest {
	public static void main(String[] args) {
		//普通工厂模式
		/*//内部类的写法
		//PersonFactory factory = new FactoryTest().new PersonFactory();
		//普通写法
		PersonFactory factory = new PersonFactory();
//		Person person = factory.createBlackPerson();
		Person person = PersonFactory.createBlackPerson();
		person.run();
		Person person = factory.createPerson("white");
		if(person != null){
			person.run();
		}*/
		
		//抽象工厂模式
		PersonFactory factory = new YellowPersonFactory();
		Person person = factory.createPerson();
		person.run();
	}
}
/*************************************普通工厂模式(start)*************************************/
//人类接口
interface Person{
	//走路方法
	void run();
}

//定义黑人
class BlackPerson implements Person{
	@Override
	public void run() {
		System.out.println("BlackPerson run()....");
	}
}

//定义黄人
class YellowPerson implements Person{
	@Override
	public void run() {
		System.out.println("YellowPerson run()....");
	}
}

//定义白人
class WhitePerson implements Person{
	@Override
	public void run() {
		System.out.println("WhitePerson run()....");
	}
}

//定义工厂类公共的接口
interface PersonFactory {
	Person createPerson();
}
//开发具体工厂的实现类
class BlackPersonFactory implements PersonFactory{
	@Override
	public Person createPerson() {
		return new BlackPerson();
	}
}
class YellowPersonFactory implements PersonFactory{
	@Override
	public Person createPerson() {
		return new YellowPerson();
	}
}
class WhitePersonFactory implements PersonFactory{
	@Override
	public Person createPerson() {
		return new WhitePerson();
	}
}
/*************************************普通工厂模式(end)*************************************/

/*************************************普通工厂模式(start)*************************************/
/*//人类接口
interface Person{
	//走路方法
	void run();
}

//定义黑人
class BlackPerson implements Person{
	@Override
	public void run() {
		System.out.println("BlackPerson run()....");
	}
}

//定义黄人
class YellowPerson implements Person{
	@Override
	public void run() {
		System.out.println("YellowPerson run()....");
	}
}

//定义白人
class WhitePerson implements Person{
	@Override
	public void run() {
		System.out.println("WhitePerson run()....");
	}
}
//定义产生人的工厂类
class PersonFactory{
	//多方法工厂模式(静态工厂模式，直接将方法加上static关键字，不需要创建类的实例就能调用)
	public static Person createBlackPerson(){
		return new BlackPerson();
	}
	public static Person createYellowkPerson(){
		return new YellowPerson();
	}
	public static Person createWhitePerson(){
		return new WhitePerson();
	}
	
	
	//普通工厂模式
	public Person createPerson(String type){
		if(type.equals("black")){
			return new BlackPerson();
		}else if(type.equals("yellow")){
			return new YellowPerson();
		}else if(type.equals("white")){
			return new WhitePerson();
		}else{
			System.out.println("您传递的参数有误！");
			return null;
		}
	}
}*/
/*************************************普通工厂模式(end)*************************************/

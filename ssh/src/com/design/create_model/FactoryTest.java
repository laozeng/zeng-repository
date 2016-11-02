package com.design.create_model;
/**
 * ����ģʽ
 * @author user
 */
public class FactoryTest {
	public static void main(String[] args) {
		//��ͨ����ģʽ
		/*//�ڲ����д��
		//PersonFactory factory = new FactoryTest().new PersonFactory();
		//��ͨд��
		PersonFactory factory = new PersonFactory();
//		Person person = factory.createBlackPerson();
		Person person = PersonFactory.createBlackPerson();
		person.run();
		Person person = factory.createPerson("white");
		if(person != null){
			person.run();
		}*/
		
		//���󹤳�ģʽ
		PersonFactory factory = new YellowPersonFactory();
		Person person = factory.createPerson();
		person.run();
	}
}
/*************************************��ͨ����ģʽ(start)*************************************/
//����ӿ�
interface Person{
	//��·����
	void run();
}

//�������
class BlackPerson implements Person{
	@Override
	public void run() {
		System.out.println("BlackPerson run()....");
	}
}

//�������
class YellowPerson implements Person{
	@Override
	public void run() {
		System.out.println("YellowPerson run()....");
	}
}

//�������
class WhitePerson implements Person{
	@Override
	public void run() {
		System.out.println("WhitePerson run()....");
	}
}

//���幤���๫���Ľӿ�
interface PersonFactory {
	Person createPerson();
}
//�������幤����ʵ����
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
/*************************************��ͨ����ģʽ(end)*************************************/

/*************************************��ͨ����ģʽ(start)*************************************/
/*//����ӿ�
interface Person{
	//��·����
	void run();
}

//�������
class BlackPerson implements Person{
	@Override
	public void run() {
		System.out.println("BlackPerson run()....");
	}
}

//�������
class YellowPerson implements Person{
	@Override
	public void run() {
		System.out.println("YellowPerson run()....");
	}
}

//�������
class WhitePerson implements Person{
	@Override
	public void run() {
		System.out.println("WhitePerson run()....");
	}
}
//��������˵Ĺ�����
class PersonFactory{
	//�෽������ģʽ(��̬����ģʽ��ֱ�ӽ���������static�ؼ��֣�����Ҫ�������ʵ�����ܵ���)
	public static Person createBlackPerson(){
		return new BlackPerson();
	}
	public static Person createYellowkPerson(){
		return new YellowPerson();
	}
	public static Person createWhitePerson(){
		return new WhitePerson();
	}
	
	
	//��ͨ����ģʽ
	public Person createPerson(String type){
		if(type.equals("black")){
			return new BlackPerson();
		}else if(type.equals("yellow")){
			return new YellowPerson();
		}else if(type.equals("white")){
			return new WhitePerson();
		}else{
			System.out.println("�����ݵĲ�������");
			return null;
		}
	}
}*/
/*************************************��ͨ����ģʽ(end)*************************************/

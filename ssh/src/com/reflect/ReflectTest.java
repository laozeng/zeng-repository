package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.entity.UserInfo;

/*
 * ���䣺ͨ���������ǿ���ͨ��.class���ʵ�.java������ԣ���ͨ���������췽����(.class-->.java)
 */
@SuppressWarnings("all")
public class ReflectTest {
	public static void main(String[] args) {
		//================================��������ʵ��(start)=======================================
		//��һ�ַ�ʽ
		try {
			Class<UserInfo> clz = UserInfo.class;
			UserInfo user = clz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		//�ڶ��ַ�ʽ
		try {
			Class<UserInfo> clz = (Class<UserInfo>) Class.forName("com.entity.UserInfo");
			UserInfo user = clz.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		//�����ַ�ʽ
		try {
			UserInfo user = new UserInfo();
			Class<UserInfo> clz = (Class<UserInfo>) user.getClass();
			UserInfo newUser = clz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		//================================��������ʵ��(end)=======================================
		
		//===============================�����ȡ���е���Ϣ(start)===================================
		try {
			Class clz = Class.forName("com.entity.UserInfo");
			Object object = clz.newInstance();
			System.out.println("ȫ�޶�������"+clz.getCanonicalName());
			System.out.println("ȫ�޶�������"+clz.getName());
			System.out.println("������"+clz.getSimpleName());
			System.out.println("������"+clz.getPackage());
			//(�˴�ע�⣺ֻ�ܻ�ȡ��public���ε���ϢgetFields()�������ؼ������ε���Ϣ��ȡ����,���Ի�ȡ��getDeclaredFields())
			//��ȡ����
			Field[] fields = clz.getDeclaredFields();
			System.out.println("userinfo�е����ԣ�");
			for (Field field : fields) {
				System.out.println("field:"+field.getName());
			}
			/*//��ȡָ��������
			Field field = clz.getField("name");
			//���Ʒ�װ
			field.setAccessible(true);
			field.set(object,"ganzi");
			System.out.println("ͨ����������ֵ��"+field.getByte(object));*/
			
			/*//��ȡ��ͨ����
			Method[] methods = clz.getMethods();
			System.out.println("userinfo�е���ͨ������");
			for (Method method : methods) {
				System.out.println("��������:"+method.getName());
				//��ȡ��������
				Class[] paramsClzs = method.getParameterTypes();
				for (int i = 0; i < paramsClzs.length; i++) {
					Class temp = paramsClzs[i];
					System.out.println("��������"+(i+1)+"���ͣ�"+temp.getName());
					
				}
				//��ȡ��������ֵ
				Class returnClz = method.getReturnType();
				System.out.println("��������ֵ���ͣ�"+returnClz.getName());
				System.out.println("=========================");
			}*/
			
			/*//��ȡ���캯��
			Constructor[] constructors =  clz.getConstructors();
			System.out.println("userinfo�еĹ��캯����");
			for (Constructor constructor : constructors) {
				System.out.println("�������ƣ�"+constructor.getName());
			}*/
			
			Method setName = clz.getDeclaredMethod("setName", String.class);
			UserInfo user = (UserInfo) clz.newInstance();
			setName.invoke(user, "����");
			System.out.println("ͨ���������õ�ֵ��"+user.getName());
			
			Method calculator = clz.getDeclaredMethod("calculator", int.class,int.class);
			//���������仰����ô��������´���java.lang.IllegalAccessException: 
			//Class com.reflect.ReflectTest can not access a member of class com.entity.UserInfo with modifiers "private"
			calculator.setAccessible(true);
			Object result = calculator.invoke(user, 12,10);
			System.out.println("ͨ�ѷ���ִ�еķ��������"+result);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//===============================�����ȡ���е���Ϣ(end)===================================
	}
	
	public static String getTypeByClass(Class clz){
		try {
			Object object = clz.newInstance();
			if(object instanceof Integer){
				return "java.lang.Integer";
			}else if(object instanceof Long){
				return "java.lang.Long";
			}else if(object instanceof Short){
				return "java.lang.Short";
			}else if(object instanceof Byte){
				return "java.lang.Byte";
			}else if(object instanceof Character){
				return "java.lang.Character";
			}else if(object instanceof String){
				return "java.lang.String";
			}else if(object instanceof Boolean){
				return "java.lang.Boolean";
			}else if(object instanceof Long){
				return "java.lang.Long";
			}else if(object instanceof Double){
				return "java.lang.Double";
			}else if(object instanceof Float){
				return "java.lang.Float";
			}else{
				return "�������ͻ����޷���ֵ";
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return "�������Ͳ�����";
	}
}

package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.entity.UserInfo;

/*
 * 反射：通过反射我们可以通过.class访问到.java类的属性，普通方法，构造方法等(.class-->.java)
 */
@SuppressWarnings("all")
public class ReflectTest {
	public static void main(String[] args) {
		//================================创建对象实例(start)=======================================
		//第一种方式
		try {
			Class<UserInfo> clz = UserInfo.class;
			UserInfo user = clz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		//第二种方式
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
		
		//第三种方式
		try {
			UserInfo user = new UserInfo();
			Class<UserInfo> clz = (Class<UserInfo>) user.getClass();
			UserInfo newUser = clz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		//================================创建对象实例(end)=======================================
		
		//===============================反射获取类中的信息(start)===================================
		try {
			Class clz = Class.forName("com.entity.UserInfo");
			Object object = clz.newInstance();
			System.out.println("全限定类名："+clz.getCanonicalName());
			System.out.println("全限定类名："+clz.getName());
			System.out.println("类名："+clz.getSimpleName());
			System.out.println("包名："+clz.getPackage());
			//(此处注意：只能获取到public修饰的信息getFields()，其他关键字修饰的信息获取不到,可以获取到getDeclaredFields())
			//获取属性
			Field[] fields = clz.getDeclaredFields();
			System.out.println("userinfo中的属性：");
			for (Field field : fields) {
				System.out.println("field:"+field.getName());
			}
			/*//获取指定的属性
			Field field = clz.getField("name");
			//打破封装
			field.setAccessible(true);
			field.set(object,"ganzi");
			System.out.println("通过反射设置值："+field.getByte(object));*/
			
			/*//获取普通方法
			Method[] methods = clz.getMethods();
			System.out.println("userinfo中的普通方法：");
			for (Method method : methods) {
				System.out.println("方法名称:"+method.getName());
				//获取方法参数
				Class[] paramsClzs = method.getParameterTypes();
				for (int i = 0; i < paramsClzs.length; i++) {
					Class temp = paramsClzs[i];
					System.out.println("方法参数"+(i+1)+"类型："+temp.getName());
					
				}
				//获取方法返回值
				Class returnClz = method.getReturnType();
				System.out.println("方法返回值类型："+returnClz.getName());
				System.out.println("=========================");
			}*/
			
			/*//获取构造函数
			Constructor[] constructors =  clz.getConstructors();
			System.out.println("userinfo中的构造函数：");
			for (Constructor constructor : constructors) {
				System.out.println("方法名称："+constructor.getName());
			}*/
			
			Method setName = clz.getDeclaredMethod("setName", String.class);
			UserInfo user = (UserInfo) clz.newInstance();
			setName.invoke(user, "杆子");
			System.out.println("通过反射设置的值："+user.getName());
			
			Method calculator = clz.getDeclaredMethod("calculator", int.class,int.class);
			//如果不加这句话，那么会出现以下错误：java.lang.IllegalAccessException: 
			//Class com.reflect.ReflectTest can not access a member of class com.entity.UserInfo with modifiers "private"
			calculator.setAccessible(true);
			Object result = calculator.invoke(user, 12,10);
			System.out.println("通脱反射执行的方法结果："+result);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//===============================反射获取类中的信息(end)===================================
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
				return "其他类型或者无返回值";
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return "数据类型不存在";
	}
}

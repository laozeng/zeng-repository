package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Test01 {
	public static int one = 0;
	public static int two = 0;
	
	/*static{
		one = 0;
		two = 0;
		System.out.println("enter...");
	}*/

	public static void main(String[] args) throws Exception{
		/*Test01 test01 = new Test01();
		Thread thread1 = new Thread(test01.new ChangeOne());
		thread1.start();
		Thread thread2 = new Thread(test01.new AsignTwo());
		thread2.start();*/
		
		/*int offSet = 20; //��������
		int sum = 0; //����ÿ���·ݵ�����ֵ
		int total = 0; //�����ܵ�����ֵ
		for (int i = 1; i <= 12*10; i++) {
			sum += offSet;
			total += sum;
		}
		System.out.println("��Ǯ��:"+total);
		System.out.println(12*0.5*10);*/
		
		/*String name = "zc";
		changeName(name);
		System.out.println(name);*/
		/*String[] names1 = new String[]{"a","b"};
		String names2[] = new String[]{"a","b"};
		char[] chars = new char[]{'1','b'};
		String temp = new String();
		System.out.println(temp);*/
	}
	
	//�ı����one��ֵΪ10
	public class ChangeOne implements Runnable{	
		@Override
		public void run() {
			one = 10;
		}
	}
	
	//��one��ֵ����two
	public class AsignTwo implements Runnable{
		@Override
		public void run() {
			two = one;
		}
	}
}

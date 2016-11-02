package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import com.spider.SipderDemo1;

public class ThreadTest {
	public static void main(String[] args) throws Exception{
		/*InputStream is = new FileInputStream("C:\\Users\\user\\Desktop\\mysql.chm");
		OutputStream os = new FileOutputStream("C:\\Users\\user\\Desktop\\mysql_copy.chm");
		int length = 0;
		byte[] bytes = new byte[is.available()];
		while((length = is.read(bytes)) != -1){
			os.write(bytes, 0, length);
		}
		is.close();	*/
		
		/*byte[] b = "abc".getBytes();
		os.write(b);*/
		/*Writer writer = new FileWriter("C:\\Users\\user\\Desktop\\test.txt");
		writer.write("杆子!");
		writer.close();*/
		
		//InputStream is = Test01.class.getClassLoader().getResourceAsStream("applicationContext.xml");
		/*InputStream is = new FileInputStream(new File("src/com/test/ThreadTest.java"));
		byte[] b = new byte[is.available()];
		is.read(b);
		String temp = new String(b,"utf-8");
		System.out.println(temp);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		temp = "";
		while((temp = reader.readLine()) != null){
			temp = new String(temp.getBytes(), "utf-8");
			System.out.println(temp);
		}
		reader.close();
		is.close();*/
		
		/*System.out.println(Test01.class.getClassLoader().getResource(""));
		System.out.println(System.getProperty("user.dir"));*/
		
		/*String filePath = "src/xml/路虎商城功能说明.txt";
		FileInputStream fis = new FileInputStream(new File(filePath));
		String keyword = "123";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
			int count = 1;
			String temp = "";
			while((temp = reader.readLine()) != null){
				if(temp.indexOf(keyword) >= 0){
					break;
				}
				count ++;
			}
			reader.close();
			System.out.println("关键字：【"+keyword+"】，在第【"+count+"】行");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		/*RandomAccessFile raf = new RandomAccessFile("src/xml/demo.txt", "rw");
		raf.seek(1024*2);
		raf.write("sss".getBytes());
		raf.close();*/
		
		/*long startTime = System.currentTimeMillis();
		SipderDemo1.downloadImg("http://gvglh.com/upload/store_model/fbb5d452-4ca2-4ddd-b16d-15f851fa3214.mp4", "d:\\", "demo.mp4");
		long endTime = System.currentTimeMillis();
		System.out.println("用时："+(endTime - startTime) / 1000 + "s");*/
	}
}

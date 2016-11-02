package com.spider;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

public class SipderDemo1 {
	public static void main(String[] args) throws Exception{
		/*String path = "http://www.baidu.com";
		URL url = new URL(path);
		HttpURLConnection connection =(HttpURLConnection) url.openConnection();
		InputStreamReader isr = new InputStreamReader(connection.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String temp = "";
		while((temp = br.readLine()) != null){
			temp = new String(temp.getBytes("gbk"),"utf-8");
			System.out.println(temp);
		}*/
		
		
		//����Զ���ļ�
		String fileName = downloadImg("http://avatar.csdn.net/0/F/F/1_u013315062.jpg","d:\\","new.jpg");
		System.out.println("���ļ����ƣ�"+fileName);
		
		/*String result = sendPost("http://127.0.0.1:8888/ssh/user/doLogin.mvc","username=zengchang");
		System.out.println("result:"+result);
//		String url = "http://jz.0712fang.com/zhaobiao/bidding";
		String url = "http://loupan.0712fang.com/2604/ask.html";
//		String param = "cname=zengchang&phone=13554014654&buildname=xiaogan&budget=5-10%u4E07&showid=14&callback=jQuery17207951848758384585_1452749055142&_=1452749097013";
		String param = "AddUser=ganzi&BuildId=1&content=moriwo";
		String result = sendPost(url, param);
		System.out.println("result:"+result);*/
	}
	

	/**
	 * ����Զ��·���Ͳ���������post����,��ȡ���ص�html���
	 * url:http://127.0.0.1:8888/ssh/user/doLogin.mvc
	 * param:username=zengchang1&password=123456
	 */
	public static String sendPost(String url,String param){
		String result="";
		try{
			URL realUrl=new URL(url);
			//�򿪺�URlֱ�ӵ�����
			URLConnection conn=realUrl.openConnection();
			//����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			//����post��������������¶���
			conn.setDoOutput(true);
			conn.setDoInput(true);
			PrintWriter out=new PrintWriter(conn.getOutputStream());
			//д�����
			out.print(param);
			out.flush();
			//��ȡ���
			BufferedReader in =new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String line;
			while((line=in.readLine())!=null){
				result+="\n"+line;
			}
		}catch(Exception e){
			System.out.println("����post�����쳣��"+e);
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	 * ��������ͼƬλ������ͼƬ����������ͼƬ����
	 * @param remoteUrl
	 * @param savePath
	 * @return
	 */
	public static String downloadImg(String remoteUrl,String savePath,String newName){
		try {
			URL url = new URL(remoteUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream is = connection.getInputStream();
			//д�������ļ�
			FileOutputStream fos = new FileOutputStream(savePath+newName);
			int temp = is.read();
			while(temp != -1){
				fos.write(temp);
				temp = is.read();
			}
			//�ͷ���Դ
			fos.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newName;
	}
}

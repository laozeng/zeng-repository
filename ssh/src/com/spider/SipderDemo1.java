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
		
		
		//下载远程文件
		String fileName = downloadImg("http://avatar.csdn.net/0/F/F/1_u013315062.jpg","d:\\","new.jpg");
		System.out.println("新文件名称："+fileName);
		
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
	 * 根据远程路径和参数，发送post请求,获取返回的html结果
	 * url:http://127.0.0.1:8888/ssh/user/doLogin.mvc
	 * param:username=zengchang1&password=123456
	 */
	public static String sendPost(String url,String param){
		String result="";
		try{
			URL realUrl=new URL(url);
			//打开和URl直接的连接
			URLConnection conn=realUrl.openConnection();
			//设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			//发送post请求必须设置如下二行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			PrintWriter out=new PrintWriter(conn.getOutputStream());
			//写入参数
			out.print(param);
			out.flush();
			//获取结果
			BufferedReader in =new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String line;
			while((line=in.readLine())!=null){
				result+="\n"+line;
			}
		}catch(Exception e){
			System.out.println("发送post请求异常！"+e);
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	 * 根据网络图片位置下载图片，并返回新图片名称
	 * @param remoteUrl
	 * @param savePath
	 * @return
	 */
	public static String downloadImg(String remoteUrl,String savePath,String newName){
		try {
			URL url = new URL(remoteUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream is = connection.getInputStream();
			//写出的新文件
			FileOutputStream fos = new FileOutputStream(savePath+newName);
			int temp = is.read();
			while(temp != -1){
				fos.write(temp);
				temp = is.read();
			}
			//释放资源
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

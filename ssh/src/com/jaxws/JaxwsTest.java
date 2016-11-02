package com.jaxws;

import javax.xml.ws.Endpoint;

import com.jaxws.client.ScoreServiceImplService;
import com.jaxws.server.ScoreService;
import com.jaxws.server.ScoreServiceImpl;
/**
 * cxf测试
 * 参考路径：http://blog.csdn.net/neareast/article/details/7714778
 * @author user
 */
public class JaxwsTest {
	public static void main(String[] args) {
		JaxwsTest test = new JaxwsTest();
		//test.publishServer();
		test.userServer();
	}
	
	//发布服务
	public void publishServer(){
		//初始化服务接口
		ScoreService scoreService = new ScoreServiceImpl();
		//定义访问路径
		String address = "http://localhost:9999/scoreService";
		//发布服务
		Endpoint.publish(address, scoreService);
		System.out.println("启动服务成功！访问路径为：["+address+"]");
	}
	
	//调用服务
	public void userServer(){
		/*ScoreServiceImplService ssis = new ScoreServiceImplService();
		com.jaxws.client.ScoreServiceImpl ssi = ssis.getScoreServiceImplPort();
		System.out.println(ssi.getDescByScore(1));*/
	}
}
package com.jaxws;

import javax.xml.ws.Endpoint;

import com.jaxws.client.ScoreServiceImplService;
import com.jaxws.server.ScoreService;
import com.jaxws.server.ScoreServiceImpl;
/**
 * cxf����
 * �ο�·����http://blog.csdn.net/neareast/article/details/7714778
 * @author user
 */
public class JaxwsTest {
	public static void main(String[] args) {
		JaxwsTest test = new JaxwsTest();
		//test.publishServer();
		test.userServer();
	}
	
	//��������
	public void publishServer(){
		//��ʼ������ӿ�
		ScoreService scoreService = new ScoreServiceImpl();
		//�������·��
		String address = "http://localhost:9999/scoreService";
		//��������
		Endpoint.publish(address, scoreService);
		System.out.println("��������ɹ�������·��Ϊ��["+address+"]");
	}
	
	//���÷���
	public void userServer(){
		/*ScoreServiceImplService ssis = new ScoreServiceImplService();
		com.jaxws.client.ScoreServiceImpl ssi = ssis.getScoreServiceImplPort();
		System.out.println(ssi.getDescByScore(1));*/
	}
}
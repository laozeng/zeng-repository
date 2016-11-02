package com.jaxws.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ScoreServiceImpl implements ScoreService {
	@Override
	@WebMethod
	public String getDescByScore(int score) {
		String result = "";
		if(score >= 90){
			result = "����";
		}else if(score >= 80){
			result = "����";
		}else if(score >= 60){
			result = "�ϸ�";
		}else if(score >= 0){
			result = "���ϸ�";
		}else{
			result = "�����ݵĲ�������";
		}
		return result;
	}
}

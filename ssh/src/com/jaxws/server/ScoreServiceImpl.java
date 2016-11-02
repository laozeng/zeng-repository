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
			result = "优秀";
		}else if(score >= 80){
			result = "良好";
		}else if(score >= 60){
			result = "合格";
		}else if(score >= 0){
			result = "不合格";
		}else{
			result = "您传递的参数有误";
		}
		return result;
	}
}

package com.jaxws.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * 分数服务接口
 * @author user
 */
@WebService
public interface ScoreService {
	//根据分数返回结果：优秀、良好、合格、不合格
	@WebMethod
	public String getDescByScore(int score);
}

package com.jaxws.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * ��������ӿ�
 * @author user
 */
@WebService
public interface ScoreService {
	//���ݷ������ؽ�������㡢���á��ϸ񡢲��ϸ�
	@WebMethod
	public String getDescByScore(int score);
}

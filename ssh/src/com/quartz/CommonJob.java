package com.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Repository;

/**
 * ��ͨ������
 * @author user
 */
//@Repository
public class CommonJob {
	//ʵ��Ҫִ�еķ���
	public void execute() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		System.out.println(sdf.format(now));
	}
}

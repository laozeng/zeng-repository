package com.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * ����job�࣬�����������࣬����ʵ�����񣬼̳�job�ӿڼ���(���ྡ��д��һ���������ļ��У�����ᱨ��)
 * @author user
 */
public class MyJob implements Job {
	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		Map map = jec.getMergedJobDataMap();
		System.out.println(map.get("message"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		System.out.println(sdf.format(now));
	}
}
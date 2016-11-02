package com.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 定义job类，此类是任务类，用于实现任务，继承job接口即可(该类尽量写在一个单独的文件中，否则会报错)
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
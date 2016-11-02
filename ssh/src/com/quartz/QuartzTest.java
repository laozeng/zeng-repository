package com.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 任务调度框架测试
 * Quartz对任务调度的领域问题进行了高度的抽象，提出了调度器、任务和触发器这3个核心的概念，并在org.quartz通过接口和类对重要的这些核心概念进行描述：
 * 参考路径：http://www.blogjava.net/baoyaer/articles/155645.html
 * http://xiaoye4188.iteye.com/blog/875900
 * @author user
 */
public class QuartzTest {
	public static void main(String[] args) throws Exception{
		//spring集成quartz测试
//		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//普通的方法测试
		//QuartzTest test = new QuartzTest();
		//test.testSimpleTrigger();
		//test.testCronTrigger();
	}
	
	//SimpleTrigger测试
	public void testSimpleTrigger() throws Exception{
		//新建任务类
		JobDetail jobDetail = new JobDetail("myJob", "myGroup", MyJob.class);
		//新建触发器类
		Trigger trigger = new SimpleTrigger("myJob1", "myGroup1",5, 1000);
		//新建调度器类
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		//注册并实现任务调度
		scheduler.scheduleJob(jobDetail, trigger);
		//开启任务
		scheduler.start();
	}
	
	//CronTrigger测试
	public void testCronTrigger() throws Exception{
		//新建任务类
		JobDetail jobDetail = new JobDetail("myJob", "myGroup", MyJob.class);
		//新建触发器类
		//报错：Based on configured schedule, the given trigger will never fire.
		//关于cronTrigger的说明：1.日期和星期不能同时使用?和*,只能一个使用*，另一个使用? 2.日期和星期如果其中任意是一个指定值之后，那么另外一个必须使用?，而不能使用*
		Trigger trigger = new CronTrigger("myTrigger", "myGroup", "myJob", "myGroup", "0/5 * 15 17 3 ? 2016");
		//新建调度器类
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		//注册并实现任务调度
		scheduler.scheduleJob(jobDetail, trigger);
		//开启任务
		scheduler.start();
	}
}
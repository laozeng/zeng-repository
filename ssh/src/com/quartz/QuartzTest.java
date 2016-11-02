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
 * ������ȿ�ܲ���
 * Quartz��������ȵ�������������˸߶ȵĳ�������˵�����������ʹ�������3�����ĵĸ������org.quartzͨ���ӿں������Ҫ����Щ���ĸ������������
 * �ο�·����http://www.blogjava.net/baoyaer/articles/155645.html
 * http://xiaoye4188.iteye.com/blog/875900
 * @author user
 */
public class QuartzTest {
	public static void main(String[] args) throws Exception{
		//spring����quartz����
//		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//��ͨ�ķ�������
		//QuartzTest test = new QuartzTest();
		//test.testSimpleTrigger();
		//test.testCronTrigger();
	}
	
	//SimpleTrigger����
	public void testSimpleTrigger() throws Exception{
		//�½�������
		JobDetail jobDetail = new JobDetail("myJob", "myGroup", MyJob.class);
		//�½���������
		Trigger trigger = new SimpleTrigger("myJob1", "myGroup1",5, 1000);
		//�½���������
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		//ע�Ტʵ���������
		scheduler.scheduleJob(jobDetail, trigger);
		//��������
		scheduler.start();
	}
	
	//CronTrigger����
	public void testCronTrigger() throws Exception{
		//�½�������
		JobDetail jobDetail = new JobDetail("myJob", "myGroup", MyJob.class);
		//�½���������
		//����Based on configured schedule, the given trigger will never fire.
		//����cronTrigger��˵����1.���ں����ڲ���ͬʱʹ��?��*,ֻ��һ��ʹ��*����һ��ʹ��? 2.���ں������������������һ��ָ��ֵ֮����ô����һ������ʹ��?��������ʹ��*
		Trigger trigger = new CronTrigger("myTrigger", "myGroup", "myJob", "myGroup", "0/5 * 15 17 3 ? 2016");
		//�½���������
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		//ע�Ტʵ���������
		scheduler.scheduleJob(jobDetail, trigger);
		//��������
		scheduler.start();
	}
}
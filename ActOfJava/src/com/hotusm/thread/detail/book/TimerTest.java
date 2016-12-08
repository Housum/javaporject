package com.hotusm.thread.detail.book;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	
	public static void main(String[] args) {
		Timer time=new Timer();
		//schedule(TimerTask task, long delay)
		//��delay����֮��ִ��
		time.schedule(new Job("job1"), 3*1000);
		//���
		//time.purge();
		//schedule(TimerTask task, long delay, long period)
		//ǰ������������һ����   ����period��ʾ���Ǹ����ִ��һ��
		time.schedule(new Job("job2"), 3*1000,1*1000);
		/*
		 *ʹ��Timer ����ÿ��ִ��һ�ε�Ч�� 
		 */
		time.schedule(new Job("job3"), new Date(), new Date().getTime());
		
		time.scheduleAtFixedRate(new Job("job4"), new Date(), new Date().getTime());
	}
}
class Job extends TimerTask{

	private String detail;
	public Job(String detail){
		this.detail=detail;
	}
	@Override
	public void run() {
		
		System.out.println(this.detail+"====do Job====");
	}
}

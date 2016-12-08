package com.hotusm.thread.detail.book;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	
	public static void main(String[] args) {
		Timer time=new Timer();
		//schedule(TimerTask task, long delay)
		//在delay毫秒之后执行
		time.schedule(new Job("job1"), 3*1000);
		//清空
		//time.purge();
		//schedule(TimerTask task, long delay, long period)
		//前面两个参数是一样的   最后的period表示的是隔多久执行一次
		time.schedule(new Job("job2"), 3*1000,1*1000);
		/*
		 *使用Timer 做到每天执行一次的效果 
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

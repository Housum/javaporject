package com.hotusm.thread.detail.start.project.myself;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import com.hotusm.thread.detail.start.Log;

/**
 * 
 *����������
 */
public class Track implements Callable<Result>{
	
	private static AtomicInteger index=new AtomicInteger(1);
	/**
	 * ����������˶�Ա
	 */
	private Player player;
	/**
	 * ��������Ĳ���
	 */
	private Referee referee;
	
	/**
	 * �ܵ��ĳ���
	 */
	private final int length;
	
	private CountDownLatch countDownLatch;
	
	
	public Track(Player player, Referee referee,int length,CountDownLatch countDownLatch) {
		super();
		this.player = player;
		this.referee = referee;
		this.length=length;
		this.countDownLatch=countDownLatch;
	}

	@Override
	public Result call() throws Exception {
		
		int time=(int) (length/this.player.getSpeed());
		Log.info(index.getAndIncrement()+" �ܵ�����:"+this.player.getName()+"ѡ��");
		Result result=new Result();
		result.setSeconds(time);
		this.referee.getResult().put(this.player, result);
		countDownLatch.countDown();
		return result;
	}
}

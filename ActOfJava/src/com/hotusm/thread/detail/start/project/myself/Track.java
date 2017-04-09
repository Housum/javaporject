package com.hotusm.thread.detail.start.project.myself;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import com.hotusm.thread.detail.start.Log;

/**
 * 
 *比赛的赛道
 */
public class Track implements Callable<Result>{
	
	private static AtomicInteger index=new AtomicInteger(1);
	/**
	 * 这个赛道的运动员
	 */
	private Player player;
	/**
	 * 这个赛道的裁判
	 */
	private Referee referee;
	
	/**
	 * 跑道的长度
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
		Log.info(index.getAndIncrement()+" 跑道的是:"+this.player.getName()+"选手");
		Result result=new Result();
		result.setSeconds(time);
		this.referee.getResult().put(this.player, result);
		countDownLatch.countDown();
		return result;
	}
}

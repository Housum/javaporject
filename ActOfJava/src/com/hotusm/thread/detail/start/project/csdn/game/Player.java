package com.hotusm.thread.detail.start.project.csdn.game;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import com.hotusm.thread.detail.start.Log;

public class Player implements Callable<Result>,Comparable<Player>{

	private int number;
	private String name;
	private float minSpeed;
	private Result result;
	/**
	 * 跑道
	 */
	private Semaphore runway;
	/**
	 * 关注的手枪
	 */
	private CountDownLatch startGun;
	
    public Player(String name , int number , Semaphore runway,CountDownLatch startGun) {
        this.name = name;
        this.number = number;
        this.runway = runway;

        // 这个最低速度设置是 8米/秒（否则就真是‘龟速’了）
        this.minSpeed = 8f;
        this.startGun=startGun;
    }	
    
	public void setStartGun(CountDownLatch startGun) {
		this.startGun = startGun;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getMinSpeed() {
		return minSpeed;
	}

	public void setMinSpeed(float minSpeed) {
		this.minSpeed = minSpeed;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Semaphore getRunway() {
		return runway;
	}

	public void setRunway(Semaphore runway) {
		this.runway = runway;
	}

	@Override
	public Result call() throws Exception {
		try {
			//申请信号旗
			this.runway.acquire();
			if(this.startGun!=null){
				//运行到这里的时候 就可以认为已经获得了信号旗
				this.startGun.countDown();
				Log.info("选手"+this.number+"已经在跑道上了");
				this.startGun.await();
				Log.info("选手"+this.number+"跑");
			}
		} catch (Exception e) {
			
		}finally{
			this.runway.release();
		}
		return doRun();
	}
	
	public Result doRun() throws InterruptedException{
		/*
         * 为了表现一个选手每一次跑步都有不同的状态（但是都不会低于其最低状态），
         * 所以每一次跑步，系统都会为这个选手分配一个即时速度。
         * 
         * 这个即时速度不会低于其最小速度，但是也不会高于 14米/秒(否则就是‘超人’咯)
         * */
        // 生成即时速度
        float presentSpeed = 0f;
        presentSpeed = this.minSpeed * (1.0f + new Random().nextFloat());
        if(presentSpeed > 14f) {
            presentSpeed = 14f;
        }

        // 计算跑步结果(BigDecimal的使用可自行查阅资料)
        BigDecimal calculation =  new BigDecimal(100).divide(new BigDecimal(presentSpeed) , 3, RoundingMode.HALF_UP);
        float presentTime = calculation.floatValue();

        // 让线程等待presentSpeed的时间，模拟该选手跑步的过程
        synchronized (this) {
            this.wait((long)(presentTime * 1000f));
        }

        // 返回跑步结果
        this.result = new Result(presentTime);
        return result;
        
	}
	@Override
	public int compareTo(Player o) {
		
		/*
         * 两个选手间，还可以通过他们的result进行比较
         * 耗时越小，当然越靠前
         * */
        Result myReslut = this.getResult();
        Result targetReslut = o.getResult();

        // 如果出现了reslut为null或者targetReslut为null，说明比赛结果出现了问题
        // 当然如果真的出现这样的问题，最可能的选手中途退赛了
        if(myReslut == null) {
            return 1;
        }
        if(targetReslut == null) {
            return -1;
        }

        // 耗时越少的选手，当然应该排在“成绩”队列的越前面
        if(myReslut.getTime() < targetReslut.getTime()) {
            return -1;
        } else {
            return 1;
        }
	}
	
}

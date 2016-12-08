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
	 * �ܵ�
	 */
	private Semaphore runway;
	/**
	 * ��ע����ǹ
	 */
	private CountDownLatch startGun;
	
    public Player(String name , int number , Semaphore runway,CountDownLatch startGun) {
        this.name = name;
        this.number = number;
        this.runway = runway;

        // �������ٶ������� 8��/�루��������ǡ����١��ˣ�
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
			//�����ź���
			this.runway.acquire();
			if(this.startGun!=null){
				//���е������ʱ�� �Ϳ�����Ϊ�Ѿ�������ź���
				this.startGun.countDown();
				Log.info("ѡ��"+this.number+"�Ѿ����ܵ�����");
				this.startGun.await();
				Log.info("ѡ��"+this.number+"��");
			}
		} catch (Exception e) {
			
		}finally{
			this.runway.release();
		}
		return doRun();
	}
	
	public Result doRun() throws InterruptedException{
		/*
         * Ϊ�˱���һ��ѡ��ÿһ���ܲ����в�ͬ��״̬�����Ƕ�������������״̬����
         * ����ÿһ���ܲ���ϵͳ����Ϊ���ѡ�ַ���һ����ʱ�ٶȡ�
         * 
         * �����ʱ�ٶȲ����������С�ٶȣ�����Ҳ������� 14��/��(������ǡ����ˡ���)
         * */
        // ���ɼ�ʱ�ٶ�
        float presentSpeed = 0f;
        presentSpeed = this.minSpeed * (1.0f + new Random().nextFloat());
        if(presentSpeed > 14f) {
            presentSpeed = 14f;
        }

        // �����ܲ����(BigDecimal��ʹ�ÿ����в�������)
        BigDecimal calculation =  new BigDecimal(100).divide(new BigDecimal(presentSpeed) , 3, RoundingMode.HALF_UP);
        float presentTime = calculation.floatValue();

        // ���̵߳ȴ�presentSpeed��ʱ�䣬ģ���ѡ���ܲ��Ĺ���
        synchronized (this) {
            this.wait((long)(presentTime * 1000f));
        }

        // �����ܲ����
        this.result = new Result(presentTime);
        return result;
        
	}
	@Override
	public int compareTo(Player o) {
		
		/*
         * ����ѡ�ּ䣬������ͨ�����ǵ�result���бȽ�
         * ��ʱԽС����ȻԽ��ǰ
         * */
        Result myReslut = this.getResult();
        Result targetReslut = o.getResult();

        // ���������reslutΪnull����targetReslutΪnull��˵�������������������
        // ��Ȼ�����ĳ������������⣬����ܵ�ѡ����;������
        if(myReslut == null) {
            return 1;
        }
        if(targetReslut == null) {
            return -1;
        }

        // ��ʱԽ�ٵ�ѡ�֣���ȻӦ�����ڡ��ɼ������е�Խǰ��
        if(myReslut.getTime() < targetReslut.getTime()) {
            return -1;
        } else {
            return 1;
        }
	}
	
}

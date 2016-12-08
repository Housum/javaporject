package com.hotusm.thread.detail.book.deallock;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import com.hotusm.thread.detail.start.Log;

/**
 * ˳������
 * @see LeftRightDealLock
 * ��Ϊ������������  ������������  ���Ի����
 * һ���̻߳����ߵ���  ����һ���̻߳���ұߵ���
 * ��ô�ͻ��������������  
 */
public class OrderDealLock {
	
	public static void main(String[] args) {
		final LeftRightDealLock lock=new LeftRightDealLock();
		new Thread(){
			public void run() {
				lock.left();
				lock.right();
			};
		}.start();
		
	}
	
	/**
	 * ��̬������ʾ��:
	 * ��Ϊ�������ֶ���̵߳��õ���� 
	 * ��Ϊfrom��to�����ʱ��  �պ�to�ָ�from��������
	 * ��ô�ͻ��������(������������ͬ�ĸ���)
	 * 
	 * @param from   		���
	 * @param to     		�տ
	 * @param decimal		����
	 * 
	 */
	@Deprecated
	public void transferMoney(Account from,Account to,BigDecimal decimal){
		//������Ի��������
		synchronized (from) {
			synchronized (to) {
				from.decrement(decimal);
				to.increment(decimal);
			}
		}
	}
	
	/**
	 * 1.������Ļ��������޸� 
	 * ʹ������ϲ���������������
	 * 2.��������������������hashCodeһ�������,��ô���ǻ��������������
	 * �������ּ����Ƿǳ��ǳ��͵�  ��ΪidentityHashCode������ײ�������С
	 * 3.���Accout����Ψһ��,���ɱ��,���Ҿ��пɱ��Եļ�ֵ,��ô�ƶ�����˳��͸��ӵ�������
	 * 
	 * @param from
	 * @param to
	 * @param decimal
	 */
	public void transfer2Money(Account from,Account to,BigDecimal decimal){
		//ʹ��System.identityHashCode(from)���ض����hashCode  ����
		//hashCode����������˳��  �����Ļ�  ��ô�������������ʱ��  ����˳��ÿһ�ζ���һ����
		
		int fromHashCode=System.identityHashCode(from);
		int toHashCode=System.identityHashCode(from);
		if(fromHashCode>toHashCode){
			synchronized (from) {
				synchronized (to) {
					from.decrement(decimal);
					to.increment(decimal);
				}
			}
		}else{
			synchronized (to) {
				synchronized (from) {
					from.decrement(decimal);
					to.increment(decimal);
				}
			}
		}
	}
	/**
	 * ʹ�ö�ʱ�����������������
	 * ��Ϊ��������һ������ڻ�ȡ��������ʱ��  
	 * ����һֱ����,�������ǿ�������Lock���ƶ�һ��
	 * ����,��һ��ʱ���Ժ󻹻�ȡ������  ��ô
	 */
	@Test
	public void timerLock(){
		final ReentrantLock lock=new ReentrantLock();
		new Thread(){
			public void run() {
				try {
					if(lock.tryLock()){
						Thread.sleep(5000);
						lock.unlock();
					}
					Log.info("�ͷ���");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
		new Thread(){
			public void run() {
				//�Ƚ���Դ�ͷ�   �������һ���߳���ִ�� �������ܻ����
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//һֱ���� ֱ�������
				for(;;){
					
					if(lock.tryLock())
						break;
					else
						continue;
				}
				
				Log.info("�����");
				lock.unlock();
			};
		}.start();
		
		//�����һ��ʹ�÷�������ʹ��lock.tryLock(timeOut,TimeUint)��ָ����ʱ���ڲ��ܻ�ȡ���Ļ� ��ô�ͷ���false 
		new Thread(){
			public void run() {
				try {
					long startTime=System.nanoTime();
					boolean isLock = lock.tryLock(2,TimeUnit.SECONDS);
					if(!isLock){
						long totalTime=System.nanoTime()-startTime;
						Log.info("�ȴ�ʱ��:"+TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS)+"��");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
/**
 * ��˳����
 * @author Hotusm
 *
 */
class LeftRightDealLock{
	
	private Object LEFT_LOCK=new  Object();
	private Object RIGHT_LOCK=new  Object();
	
	public void left(){
		synchronized (LEFT_LOCK) {
			synchronized (RIGHT_LOCK) {
				
			}
			
		}
	}
	public void right(){
		synchronized (RIGHT_LOCK) {
			synchronized (LEFT_LOCK) {
			}
		}
	}
}

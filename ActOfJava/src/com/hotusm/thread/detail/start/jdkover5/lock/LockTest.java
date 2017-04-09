package com.hotusm.thread.detail.start.jdkover5.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import org.junit.Test;

import com.hotusm.thread.detail.start.Log;

/**
 * Lock.lock()��Lock.tryLock()֮�������
 * @see java.util.concurrent.locks.Lock.tryLock()
 * tryLock()���ǰ���ܹ�������Ļ� ��ô����true  ���һ������
 * ���߷���false
 * ���͵��÷���
 * <pre>
 *      Lock lock = ...;
 *      if (lock.tryLock()) {
 *          try {
 *              // manipulate protected state
 *          } finally {
 *              lock.unlock();
 *          }
 *      } else {
 *          // perform alternative actions
 *      }
 * </pre>
 * @see java.util.concurrent.locks.Lock.lock()
 * lock():���Ի���� ���û�л����  ��ô����߳̽������ߣ��������ط��ͷ����Ļ�  �߳̾��ܼ���
 * 
 */
public class LockTest {
	
	/**
	 * 
	 * ����ǰsynchronized�滻��ReentrantLock�ķ�ʽ
	 */
	@Test
	public void testReentrantLock(){
		
		final Lock lock=new ReentrantLock();
		
		new Thread(){

			@Override
			public void run() {
				lock.lock();
				Log.info("����һ����...");
				try {
					//��Դ���ͷ�  ģ��ʱ��ͣ��
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock.unlock();
			}
			
			
		}.start();
		
		new Thread(){

			@Override
			public void run() {
				lock.lock();
				Log.info("��������һ����...");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock.unlock();
			}
			
			
		}.start();
		
		synchronized (LockTest.class) {
			try {
				LockTest.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ����һ����д��  
	 * ��java.util.concurrent.locks���У����ṩ��һ��ReentrantReadWriteLock����.
	 * ����Ȼ���������������־����������ĺ��壬��������̶߳�ָ������Ķ�������д�����ֿ�������
	 * 
	 * 1.��ô�����д���Ͷ�������ô����Ӱ����أ������Ҫ�ֿ�������������������������һ�£�ʲô������߳̿��Ի�ȡĳ������Ķ�����

		���û���κ��̻߳�ȡ�˶����д����
		��Ȼ���̻߳�ȡ�˶����д������������߳̾��ǵ�ǰ����������߳�
		
		��ô��ǰ�Ƿ����̻߳�ȡ�˶���Ķ�����������Ӱ�쵱ǰ�̼߳�����ȡ����Ķ�����ʲô������߳̿��Ի�ȡĳ�������д����
		
		û���κ��̻߳�ȡ���������Ķ���(�ڱ��̻߳��д��֮���õĶ�������)
		û���κ��̻߳�ȡ����������д��
	 */
	private static final Object LOCK_OBJECT=new Object();
	
	@Test
	public void teentrantReadWriteLock(){
		
		final ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
		//�����߳̿���ͬʱ��ö���  ������Ӱ�쵽�Է�
		new Thread(){
			@Override
			public void run() {
				ReadLock rl = lock.readLock();
				WriteLock wl = lock.writeLock();
				wl.lock();
				rl.lock();
				Log.info("һ���߳�����ִ��");
//					try {
//						//�����������������仰�Ļ�  �ᱨjava.lang.IllegalMonitorStateException
//						//��������Ϊ����Դ��ס��  ����ȴ������wait()��������Դ���ֳ�ȥ��  ������û�еõ��ͷ�
//						
//						//LockTest.LOCK_OBJECT.wait(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				wl.unlock();
				rl.unlock();
				}
				
		}.start();
		
		new Thread(){
			@Override
			public void run() {
				ReadLock rl = lock.readLock();
				WriteLock wl = lock.writeLock();
				//����ȵ���������̵߳Ļ� ��ô�̻߳ᱻ��������
				wl.lock();
				rl.lock();
				Log.info("����һ���߳�����ִ��");
//					try {
//						LockTest.LOCK_OBJECT.wait(5000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				wl.unlock();
				rl.unlock();
				}
				
		}.start();
		
		synchronized (LockTest.class) {
			try {
				LockTest.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	//����һ��ʹ��Conditionʵ�ֵĶ��̻߳����µĻ�����  
	//ʵ��˼�����:  Ĭ�ϵ�������100 �����е�����Ϊ0��ʱ��  ���̱߳�����   ������Ϊ100��ʱ��   д�̱߳�����
	class BoundedBuffer {  
		   final Lock lock = new ReentrantLock();//������  
		   final Condition notFull  = lock.newCondition();//д�߳�����   
		   final Condition notEmpty = lock.newCondition();//���߳�����   
		  
		   final Object[] items = new Object[100];//�������  
		   int putptr/*д����*/, takeptr/*������*/, count/*�����д��ڵ����ݸ���*/;  
		  
		   public void put(Object x) throws InterruptedException {  
		     lock.lock();  
		     try {  
		       while (count == items.length)//�����������   
		         notFull.await();//����д�߳�  
		       items[putptr] = x;//��ֵ   
		       if (++putptr == items.length) putptr = 0;//���д����д�����е����һ��λ���ˣ���ô��Ϊ0  
		       ++count;//����++  
		       notEmpty.signal();//���Ѷ��߳�  
		     } finally {  
		       lock.unlock();  
		     }  
		   }  
		  
		   public Object take() throws InterruptedException {  
		     lock.lock();  
		     try {  
		       while (count == 0)//�������Ϊ��  
		         notEmpty.await();//�������߳�  
		       Object x = items[takeptr];//ȡֵ   
		       if (++takeptr == items.length) takeptr = 0;//����������������е����һ��λ���ˣ���ô��Ϊ0  
		       --count;//����--  
		       notFull.signal();//����д�߳�  
		       return x;  
		     } finally {  
		       lock.unlock();  
		     }  
		   }   
		 } 
}


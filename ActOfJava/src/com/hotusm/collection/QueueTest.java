package com.hotusm.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * ����  @link http://blog.csdn.net/ydj7501603/article/details/17246949
 * 
 *@author Hotusm
 *offer(...) ������м������� ���ᵼ���̵߳Ķ��� �ɹ��᷵��true����false
 *put(...)   ������м������� ����������˵Ļ� ��ô�ͻᱻ����
 *poll(...)  �Ӷ����л�ȡ����  ���ʧ�ܷ���null
 *take(...)  �Ӷ����л�ȡ���� �ᵼ���̶߳���
 *drainTo һ���Դ�BlockingQueue��ȡ���п��õ����ݶ��󣨻�����ָ����ȡ���ݵĸ������� 
��������ͨ���÷���������������ȡ����Ч�ʣ�����Ҫ��η����������ͷ���,���ܹ������������滻
  @see java.util.concurrent.ArrayBlockingQueue<E>
  	�����������������ʵ�֣���ArrayBlockingQueue�ڲ���ά����һ����������
  @see java.util.concurrent.LinkedBlockingQueue<E>
         �����������������
  @see java.util.concurrent.DelayQueue<E>
         �ӳٶ���      DelayQueue�е�Ԫ��ֻ�е���ָ�����ӳ�ʱ�䵽�ˣ����ܹ��Ӷ����л�ȡ����Ԫ�ء�DelayQueue��һ��û�д�С���ƵĶ���
   @link http://www.cnblogs.com/jobs/archive/2007/04/27/730255.html
  @see java.util.concurrent.PriorityBlockingQueue<E>
  	���ȼ�����
  @see java.util.concurrent.SynchronousQueue<E>
          û���м�Ļ�����  �����ߺ������߽���ֱ�ӵĽ��� ͬʱҲû������
 */
public class QueueTest {
	
	public static void main(String[] args) {
		
		//��������  
		final BlockingQueue<Integer> q=new ArrayBlockingQueue<Integer>(3);
		final AtomicInteger number=new AtomicInteger(0);
		new Thread(){
			public void run() {
				while(true){
					Integer integer;
						try {
							integer = q.take();
							System.out.println("�����߻�ȡ:"+integer);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			};
		}.start();
		
		new Thread(){
			public void run() {
				
				while(true){
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						//---------------------------
							try {
								q.put(number.getAndIncrement());
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println("��������������");
						}
			};
		}.start();
	}
	
	public void listTypeOfQueue(){
		
		/**/
		BlockingQueue<?> queue=new ArrayBlockingQueue<>(10);
				
	    queue=new LinkedBlockingQueue<>();
		
		queue=new SynchronousQueue<>();
		
		queue=new PriorityBlockingQueue<>();
		
		
	}
}

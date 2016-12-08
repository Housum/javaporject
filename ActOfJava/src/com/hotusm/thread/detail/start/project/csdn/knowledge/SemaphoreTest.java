package com.hotusm.thread.detail.start.project.csdn.knowledge;

import java.util.concurrent.Semaphore;

import com.hotusm.thread.detail.start.Log;
/**
 * @link http://blog.csdn.net/yinwenjie/article/details/50659540
 * 
 *   1.semaphore���ź��� ��synchronize��lock�����Ƶ�
 * �ڶ���߳̽��з���һ����Դ��ʱ��  ��ô���ǻ��Ƚ���֤�������
 * ���û�����뵽֤��Ļ� ��ô�ͻ�һֱ���� ������뵽֤��  ������
 * ҵ���ʱ��  ���ܽ�֤��黹  ������� ��ʵ����һ�����Ĺ���
 *   2.������һ�㲻ͬ��ʱ�� ���ǿ��Կ���Semaphore���캯����
 * ��������  ��һ����int�͵�  ��˼��˵�����п����ж���ź��� Ҳ����
 * ˵����߳̿���ͬʱ���뵽ָ�����ͺ���
 *   3.��������������
 *     ��void acquire()���Ӵ��ź�����ȡһ����ɣ���Semaphore�ܹ��ṩһ�����ǰ����ǰ�߳̽�һֱ�����ȴ�������ڵȴ������У���ǰ�߳��յ���interrupt�źţ���ô���׳�InterruptedException�쳣
 *     ��void acquire(permits)���Ӵ��ź�����ȡpermits����ɣ���Semaphore�ܹ��ṩpermits�����ǰ����ǰ�߳̽�һֱ�����ȴ�������ڵȴ������У���ǰ�߳��յ���interrupt�źţ���ô���׳�InterruptedException�쳣��
 *     ��void acquireUninterruptibly()���Ӵ��ź�����ȡһ����ɣ���Semaphore�ܹ��ṩһ�����ǰ����ǰ�߳̽�һֱ�����ȴ���ʹ�����������ȡ���ʱ�������ܵ��߳�interrupt�źŵ�Ӱ�졣
 *     4void acquireUninterruptibly(permits)���Ӵ��ź�����ȡpermits����ɣ���Semaphore�ܹ��ṩpermits�����ǰ����ǰ�߳̽�һֱ�����ȴ���ʹ�����������ȡ���ʱ�������ܵ��߳�interrupt�źŵ�Ӱ��
 *     ��boolean tryAcquire()���Ӵ��ź�����ȡһ����ɣ�����޷���ȡ���̲߳�������������������ȡ������ɣ��򷵻�true�������������false��
 *     ��boolean tryAcquire(permits)���Ӵ��ź�����ȡpermits����ɣ�����޷���ȡ���̲߳�������������������ȡ������ɣ��򷵻�true�������������false��
 *     7.boolean tryAcquire(int permits, long timeout, TimeUnit unit)���Ӵ��ź�����ȡpermits����ɣ�����޷���ȡ����ǰ�̵߳ȴ��趨��ʱ�䡣��������ȴ�ʱ��󣬻���û���õ���ɣ������ȴ�����ִ�С������ȡ������ɣ��򷵻�true�������������false��
 *   
 *   ֤��״̬��
		int availablePermits()�����ش��ź����е�ǰ���õ��������
		int getQueueLength()���������ڵȴ���ȡ���̵߳Ĺ�����Ŀ����ֵ���ǹ��Ƶ����֣���Ϊ�ڴ˷��������ڲ����ݽṹ��ͬʱ���̵߳���Ŀ���ܶ�̬�ر仯���˷������ڼ���ϵͳ״̬��������ͬ�����ơ�
		boolean hasQueuedThreads()����ѯ�Ƿ����߳����ڵȴ���ȡ��ע�⣬��Ϊͬʱ���ܷ���ȡ�������Է��� true ������֤�������̵߳ȴ���ȡ��ɡ��˷�����Ҫ���ڼ���ϵͳ״̬��
		boolean isFair()��������ź����Ĺ�ƽ����Ϊ true���򷵻� true��
		�ͷ�/����֤�飺
		void release()���ͷ�һ����ɣ����䷵�ظ��ź�������ý���������ĵ��ã�������finally�������ִ�С�
		void release(permits)���ͷŸ�����Ŀ����ɣ����䷵�ص��ź�������ý���������ĵ��ã�������finally�������ִ�С�
		fair����ƽ��ǹ�ƽ
		Semaphoreһ�����������캯�����ֱ��ǣ�Semaphore(int permits)��Semaphore(int permits, boolean fair)��permits��ָ��Semaphore�ź������Ƶġ�֤�顱������fair��������������ź�������Ĺ�����ʽ��
		��fair����Ϊtrueʱ���ź������ԡ���ƽ��ʽ�����С�����������֤�飬����������״̬���̣߳�����Ȩ�����Ȼ�ȡ��֤�飻��fair����Ϊfalseʱ���ź������󽫲��ᱣ֤�������ȵá���Ĭ������£�Semaphore���á��ǹ�ƽ��ģʽ���С�
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		Semaphore semaphore=new Semaphore(5, true);
		for(int i=0;i<10;i++){
			new Thread(new SemaphoreRunnableNonfair(i,semaphore)).start();
		}
		
		synchronized (SemaphoreTest.class) {
			try {
				SemaphoreTest.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class SemaphoreRunnableNonfair implements Runnable{

	private int index;
	private Semaphore semaphore;
	
	public SemaphoreRunnableNonfair(int index, Semaphore semaphore) {
		super();
		this.index = index;
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		Log.info("�߳�"+this.index+"�ȴ��ź�....");
		try {
			/**
			 * ����֤��  ���û�����뵽֤��Ļ� ��ô�ͻ�һֱ�Ķ���  
			 */
			this.semaphore.acquire();
			Log.info("indexΪ"+this.index+"���߳�,����ź�,��ʼ����ҵ��");
			//ֹͣ5���ӵ�ʱ��   
			//synchronized (this) {
				this.wait(5000);
			//}
		} catch (Exception e) {
			
		}finally{
			//�������Ľ��������  ��Ӧ�ù黹֤��  �����������˲��ܹ��õ�֤��
			this.semaphore.release();
			Log.info("indexΪ"+this.index+"���߳�,�ͷ��ź�");		
		}
	}
}

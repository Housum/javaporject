package com.hotusm.thread.detail.start.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.hotusm.thread.detail.start.Log;

/**
 * ��ϸ�Ľ��� @link ThreadPoolExecutor��ʹ��
 * @author Hotusm
 * ���ڳ��õĹ��캯����:
 * public ThreadPoolExecutor(int corePoolSize,
 *                            int maximumPoolSize,
 *                             long keepAliveTime,
 *                             TimeUnit unit,
 *                            BlockingQueue<Runnable> workQueue)
 *������� ����:
 *public ThreadPoolExecutor(int corePoolSize,
 *                             int maximumPoolSize,
 *                             long keepAliveTime,
 *                             TimeUnit unit,
 *                             BlockingQueue<Runnable> workQueue,
 *                             ThreadFactory threadFactory,
 *                             RejectedExecutionHandler handler)
 */
public class ThreadPoolExecutorTest {
	
	public static void main(String[] args) {
		ThreadPoolExecutor executor=new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES, new SynchronousQueue<Runnable>(),new TestThreadFactory(),new RejectedExecutionHandlerImpl());
		//��ʼ����ʱ�� �ͷ���corePoolSize������
		executor.prestartCoreThread();
		for(int i=0;i<12;i++){
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					Log.info(Thread.currentThread().getId()+"��������");
				}
			});
		}
		
		Thread currentThread = Thread.currentThread();
	}
}
/**
 * �������ʱ������Ҳ�����Լ�ʵ��ThreadFactory
 *
 */
class TestThreadFactory implements ThreadFactory{
	
	public static int index=0;

	@Override
	public Thread newThread(Runnable r) {
		//��������ڴ����̵߳�ʱ����һЩ�߼�
		synchronized (ThreadPoolExecutorTest.class) {
			Log.info("���ǵ�"+index+++"�߳�");
		}
		return new Thread();
	}
}
/**
 * 
 * ��ʹ��ThreadPoolExecutor�̳߳ص�ʱ����Ҫָ��һ��ʵ����BlockingQueue�ӿڵ�����ȴ����С�
 * ��ThreadPoolExecutor�̳߳ص�API�ĵ��У�һ���Ƽ������ֵȴ����У������ǣ�SynchronousQueue,
 * LinkedBlockingQueue��ArrayBlockingQueue����ͨ���۲�BlockingQueue�ӿڵ�ʵ�����,
 * �����Է��֣��ܹ�ֱ��ʹ�õĵȴ����л��У�PriorityBlockingQueue
 * LinkedBlockingDeque��LinkedTransferQueue��
 *
 */

/**
 * SynchronousQueue��
 *
 */
class SynchronousQueueTest{
	
	public void queue() throws InterruptedException{
		SynchronousQueue<Object> queue = new SynchronousQueue<Object>();
		
		// ��Ҫʹ��add����Ϊ��������ڲ�û���κ����������Ի��׳��쳣��IllegalStateException��
		// queue.add(new Object());
		// �����̻߳������ﱻ������ֱ�������������߳�ȡ���������
		queue.put(new Object());
	}
}
/**
 * ArrayBlockingQueue��
 *һ��������֧�ֵ��н��������С��˶��а� FIFO���Ƚ��ȳ���ԭ���Ԫ�ؽ�������
 *��Ԫ�ز��뵽���е�β�������л�ȡ�������ǴӶ���ͷ����ʼ���Ԫ�ء�
 *����һ�����͵ġ��н绺���������̶���С�����������б��������߲����Ԫ�غ�ʹ������ȡ��Ԫ�ء�
 *һ�������������Ļ��������Ͳ�������������������ͼ�����������з���Ԫ�ػᵼ�²�����������
 *��ͼ�ӿն�������ȡԪ�ؽ���������������
 */
class ArrayBlockingQueueTest{
	
	public void arrayQueue() throws InterruptedException{
		// ���Ǵ�����һ��ArrayBlockingQueue���������ö��пռ�Ϊ2
		ArrayBlockingQueue<Object> arrayQueue = new ArrayBlockingQueue<Object>(2);
		// �����һ������
		arrayQueue.put(new Object());
		// ����ڶ�������
		arrayQueue.put(new Object());
		// �������������ʱ����������߳̾ͻᱻ����(�ȴ�ǰ��Ķ��г�ȥ)��
		arrayQueue.put(new Object());
		// �벻Ҫʹ��add��������SynchronousQueue��add����һ�������Ƕ�ʹ����AbstractQueue�е�addʵ��
	}
}
/**
 * LinkedBlockingQueue:
 * LinkedBlockingQueue��������ThreadPoolExecutor�̳߳��г�Ӧ�õĵȴ����С�
 * ������ָ������Ҳ���Բ�ָ�����������������С����������������ԣ������һ��ǽ������������޶��еķ��루ʵ�����κ����������Ķ���/ջ�����������ģ������������Integer.MAX_VALUE����
 * LinkedBlockingQueue��ʵ���ǻ�������ṹ������������ArrayBlockingQueue���������顣��ʵ��ʹ�ù����У�������Ҫ���������ڲ�ʵ�֣�
 * �����ָ����LinkedBlockingQueue��������С��
 * ��ô����ӳ������ʹ�����Ծͺ�ArrayBlockingQueue������
 *
 */
class LinkedBlockingQueueTest{
	
	public void linkedBlockQueue() throws InterruptedException{
		LinkedBlockingQueue<Object> linkedQueue = new LinkedBlockingQueue<Object>(2);
		linkedQueue.put(new Object());
		// ����ڶ�������
		linkedQueue.put(new Object());
		// �������������ʱ����������߳̾ͻᱻ������(��������þ��Ǻ�ArrayBlockingQueue��һ����)
		linkedQueue.put(new Object());

		//=======================================

		// ��������ʹ�ã�
		LinkedBlockingQueue<Object> linkedQueue1 = new LinkedBlockingQueue<Object>();
		linkedQueue1.put(new Object());
		// ����ڶ�������
		linkedQueue1.put(new Object());
		// �����N������ʱ������������
		linkedQueue1.put(new Object());
	}
}
/**
 * LinkedBlockingDeque˫�˶���
 * LinkedBlockingDeque��һ�����������˫�˶��С�
 * LinkedBlockingQueue���ڲ��ṹ��������ֻ�ܴӶ���β������,�Ӷ���ͷ��ȡ��Ԫ�أ�
 * ����LinkedBlockingDeque�ȿ��Դ�β������/ȡ��Ԫ�أ������Դ�ͷ������Ԫ��/ȡ��Ԫ�ء�
 */
class LinkedBlockingDequeTest{
	
	public void linkedBlockingDeque(){
		PriorityBlockingQueue<TempObject> priorityQueue = new PriorityBlockingQueue<TempObject>();
		priorityQueue.put(new TempObject(-5));
		priorityQueue.put(new TempObject(5));
		priorityQueue.put(new TempObject(-1));
		priorityQueue.put(new TempObject(1));

		// ��һ��Ԫ����5
		// ʵ�����ڻ�û��ִ��priorityQueue.poll()����ʱ�򣬶����еĵڶ���Ԫ�ز�һ����1
		TempObject targetTempObject = priorityQueue.poll();
		System.out.println("tempObject.index = " + targetTempObject.getIndex());
		// �ڶ���Ԫ����1
		targetTempObject = priorityQueue.poll();
		System.out.println("tempObject.index = " + targetTempObject.getIndex());
		// ������Ԫ����-1
		targetTempObject = priorityQueue.poll();
		System.out.println("tempObject.index = " + targetTempObject.getIndex());
		// ���ĸ�Ԫ����-5
		targetTempObject = priorityQueue.poll();
		System.out.println("tempObject.index = " + targetTempObject.getIndex());
	}
}
/**
 * LinkedTransferQueueҲ��һ�����޶��У������˾���һ����еĲ��������⣨�Ƚ��ȳ�����������һ���������ԣ�LinkedTransferQueue������һ��������/�������߳̽��в������������߽�һ���µ�Ԫ�ز�����к��������߳̽���һֱ�ȴ���ֱ��ĳһ���������߳̽����Ԫ��ȡ�ߣ���֮��Ȼ��
 * @author Hotusm
 * ��������ӿ��Կ�:
 * @link http://blog.csdn.net/yinwenjie/article/details/50577325
 */
class LinkedTransferQueueTest{
	
	public void linkedtransferqueue(){
		
	}
}
class TempObject implements Comparable<TempObject>{
	
	private int index;
	
	public TempObject(int index){
		this.index=index;
	}
	//����Ǹ���  ��ô����С�ڸ����Ķ���  0���ǵ���  �����Ǵ���
	@Override
	public int compareTo(TempObject o) {
		return o.getIndex()-index;
	}
	public int getIndex() {
		return index;
	}
}
/**
 * �ܾ�����:
 * �������ⲿ���ǽ�������Ժ�һ������RejectedExecutionHandler��
 * ��ThreadPoolExecutor�̳߳��л���һ����Ҫ�Ľӿڣ�RejectedExecutionHandler��
 * ���ύ���̳߳ص�ĳһ���������޷�ֱ�ӱ��̳߳��С������̡߳�ֱ�Ӵ������޷�����ȴ����У�Ҳ�޷������µ��߳�ִ�У�
 * �ֻ����̳߳��Ѿ�����shutdown()����ֹͣ�˹������ֻ����̳߳ز��Ǵ��������Ĺ���״̬;
 * ��ʱ��ThreadPoolExecutor�̳߳ػ�ܾ������������
 * ����������ThreadPoolExecutor�̳߳�ʱ�����RejectedExecutionHandler�ӿڵ�ʵ�֣�
 * 
 * ��������ڹ��캯����û��ָ����������Ļ�  ��ô�ͻ�Ĭ�ϵİ����Ǵ���һ���ӿڵ�ʵ��
 * @see java.util.concurrent.ThreadPoolExecutor.AbortPolicy
 * 
 */
/**
 * ����������Լ��̳е�һ���쳣������
 * @author Hotusm
 *
 */
class RejectedExecutionHandlerImpl implements RejectedExecutionHandler{

	private AtomicInteger index=new AtomicInteger(0); 
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		Log.error("�߳�:"+r.toString()+"���ܾ�ִ�� �п��������µļ��ֿ���:"
				+ "���ύ���̳߳ص�ĳһ���������޷�ֱ�ӱ��̳߳��С������̡߳�ֱ�Ӵ������޷�����ȴ����У�Ҳ�޷������µ��߳�ִ��"
				+ "�ֻ����̳߳��Ѿ�����shutdown()����ֹͣ�˹������ֻ����̳߳ز��Ǵ��������Ĺ���״̬");
		Log.error("���ǵ�"+index.incrementAndGet()+"���ܾ����߳�");
	}
}
/**
 * Ĭ��ֻ������:
 *  1.CallerRunsPolicy:����ܾ�����������ֱ��������������run���������ǣ���ע�Ⲣ������ThreadPoolExecutor�̳߳��е��߳�������
 *  ����ֱ�ӵ����������ʵ�ֵ�run������
 *  @see java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy
 *  2.AbortPolicy
 *  ����������������񱻾ܾ���ᴴ��һ��RejectedExecutionException�쳣���׳�������������Ҳ��ThreadPoolExecutor�̳߳�Ĭ�ϵ�RejectedExecutionHandlerʵ�֣�
 *  @see java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy
 *  3.DiscardPolicy
 *  DiscardPolicy������������ĬĬ����������ܾ������񣬲����׳��쳣��Ҳ����ͨ��������ʽִ�����������κ�һ������������������κε���־��ʾ
 *  @see java.util.concurrent.ThreadPoolExecutor.DiscardPolicy
 *  4.DiscardOldestPolicy
 *  ���������������˼�������鵱ǰThreadPoolExecutor�̳߳صĵȴ����С������ö��е�poll()����������ǰ���ڵȴ�������ͷ�ĵȴ�����ǿ��ȡ����Ȼ������ͼ����ǰ���ܾ��������ύ���̳߳�ִ�У�
 *  @see java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy
 */

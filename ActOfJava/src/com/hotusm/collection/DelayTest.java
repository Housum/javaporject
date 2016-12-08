package com.hotusm.collection;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
/**
 * DelayQueue ���� @see PriorityQueue + @see BlockingQueue + @see Delayed
 * ��ɵ�һ����ʱ�Ķ���  
 *	
 */
public class DelayTest {
	
	/**
	 * DelayQueue������һϵ�е�Delayed����  ÿһ�� ��
	 * Delayed�����趨�����ڵ�ʱ��  ֻҪ���ڲ���Delayed��������
	 * �Ժ�  ���ܹ�take
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		DelayQueue<Delayed> delays=new DelayQueue<Delayed>();
		Node node = new DelayTest().new Node("1",10);
		node.getDelay(TimeUnit.SECONDS);
		delays.put(node);
		Delayed no = delays.take();
		System.out.println(no);
	}
	
	class Node implements Delayed{
		 
		private String str;
		private long time;
		
		public Node(String str,long time){
			this.str=str;
			this.time=time+System.currentTimeMillis();
		}
		
		@Override
		public int compareTo(Delayed o) {
			return -1;
		}

		/**
		 * TimeUnit.convert(long sourceDuration,
                    TimeUnit sourceUnit)
                  
                  		��sourceDuration��ΪsourceUnitת��Ϊthis���͵�ʱ�� 
           EG:TimeUnit.MILLISECONDS.convert(10L, TimeUnit.MINUTES) 
                                  ��10����ת��Ϊ����
		 */
		/**
		 * ֻ�з��ص�ֵʱ���������  ���ܹ��Ӷ����л�ȡ��Ԫ��
		 */
		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(time-now(), TimeUnit.SECONDS);
		}
		
	    final long now() {
	        return System.currentTimeMillis();
	    }
		@Override
		public String toString() {
			return str;
		}
	}
}

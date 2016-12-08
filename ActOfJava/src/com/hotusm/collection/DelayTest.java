package com.hotusm.collection;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
/**
 * DelayQueue 是由 @see PriorityQueue + @see BlockingQueue + @see Delayed
 * 组成的一个延时的队列  
 *	
 */
public class DelayTest {
	
	/**
	 * DelayQueue管理则一系列的Delayed对象  每一个 的
	 * Delayed对象都设定了延期的时间  只要在内部的Delayed对象逾期
	 * 以后  才能够take
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
                  
                  		将sourceDuration作为sourceUnit转化为this类型的时间 
           EG:TimeUnit.MILLISECONDS.convert(10L, TimeUnit.MINUTES) 
                                  将10分钟转化为毫秒
		 */
		/**
		 * 只有返回的值时负的情况下  才能够从队列中获取到元素
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

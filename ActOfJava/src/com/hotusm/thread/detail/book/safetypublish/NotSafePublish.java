package com.hotusm.thread.detail.book.safetypublish;
/**
 *
 *	使用这种方式发布的类不是安全的发布 
 *以为不同的线程看到的结果不一致的  
 *会出现<strong>可见性问题</strong>
 *可能会出现某一个线程看到还没有初始化的状态
 *
 */
public class NotSafePublish {

	private Object obj;
	
	private Bo second;
	
	public Bo getSecond() {
		return second;
	}


	public void setSecond(Bo second) {
		this.second = second;
	}


	public void init(){
		obj=new Object();
	}
	
	
}

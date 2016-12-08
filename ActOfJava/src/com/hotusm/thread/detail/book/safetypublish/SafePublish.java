package com.hotusm.thread.detail.book.safetypublish;
/**
 * 
 *安全发布的示例
 *	1.在静态初始化函数中初始化一个对象的引用
 *  2.将对象的引用保存在volatile类型的域或者AtomicReferance对象中
 *  3.将对象的引用保存到某个正确构造对象的final类型域中
 *  4.将对象的引用保存到由一个锁保护的域中
 */
public class SafePublish implements Cloneable{
	
	private static Object first;
	{
		first=new Object();
	}
	private volatile Object second;
	
	private final Object thirdly=new Object();
	
	private Object fourthly;
	
	private Bo bo;

	public Bo getBo() {
		return new Bo(bo);
	}

	public void setBo(Bo bo) {
		this.bo =new Bo(bo);
	}

	public static Object getFirst() {
		return first;
	}

	public static void setFirst(Object first) {
		SafePublish.first = first;
	}

	public Object getSecond() {
		Object second=this.second;
		return second;
	}

	public void setSecond(Object second) {
		this.second = second;
	}

	public Object getFourthly() {
		return fourthly;
	}

	public synchronized void setFourthly(Object fourthly) {
		this.fourthly = fourthly;
	}

	public synchronized Object getThirdly() {
		return thirdly;
	}
	
	
	
	
}

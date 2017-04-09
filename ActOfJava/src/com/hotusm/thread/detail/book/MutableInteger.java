package com.hotusm.thread.detail.book;

/**
 *失效数值:表示的是一个线程读的到数据可能已经过时的，是一个久的数据 
 *比如:A线程在读取一个值的时候  B线程正在设置这个值的时候  那么就有可能
 *A线程读的数据就不是最新的数据了 这个时候  我们就需要设置同步了  保证数据的
 *一致性  不能出现失效的数据 
 */
public class MutableInteger {
	
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
/**
 * 
 *在这个类中的方法进行同步  这样就不会导致出现失效的问题
 *注意的是:不能只对set方法增加同步  get也需要  这样才能进行保护
 * JDK中也有很多是这样实现的
 * @see java.util.Vector<E>
 * 
 */
class SynchronizedInteger{
	
	private int value;

	public synchronized int getValue() {
		return value;
	}

	public synchronized void setValue(int value) {
		this.value = value;
	}
	
}

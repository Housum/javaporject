package com.hotusm.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @since 2016-03-27
 * @author hotusm
 * 
 * 1.在JAVA SE5引入的AtomicInteger,AtomicLong,AtomicReference等
 * 特殊的原子性变量类，使用这些类的时候，不需要关心原子性问题和可视性问题
 * 2.因为不愿写代码.就试用一下API就是了.如果需要测试，必须写个并发的情况才能测试
 * 是否是线程安全
 * 3.除了特殊的情况下会使用，一般我们都使用synchronized关键字或者是Lock显式
 * 对象
 * 
 * 4从源码中我们可以看到原子操作是使用了乐观锁
 * @see java.util.concurrent.atomic.AtomicInteger.addAndGet(int)
 * 乐观锁和悲观锁的区别:
 * 悲观锁是一种独占锁，它假设的前提是“冲突一定会发生”，所以处理某段可能出现数据冲突的代码时，这个代码段就要被某个线程独占。而独占意味着“其它即将执行这段代码的其他线程”都将进入“阻塞”/“挂起”状态
 * 乐观锁假定“冲突不一定会出现”，如果出现冲突则进行重试，直到冲突消失。 由于乐观锁的假定条件，所以乐观锁不会独占资源，自然性能就会好于悲观锁。AtomicInteger是一个标准的乐观锁实现，sun.misc.Unsafe是JDK提供的乐观锁的支持。
 * 
 * @link http://blog.csdn.net/yinwenjie/article/details/50698751
 * 
 * 5.原子操作包括:
 * 	 大部分基本类型    数组以及引用类型
 */
public class AtomicTest {

	public static void main(String[] args) {
		//对这几个类使用的时候不需要关心并发问题
		AtomicInteger intt=new AtomicInteger(0);
		intt.addAndGet(10);
		System.out.println(intt.get());
		/**
		 * 这是对对象的用法
		 */
		AtomicReference<String> ar=new AtomicReference<String>();
		ar.getAndSet("hotusm");
		System.out.println(ar.get());
		
		
	}
}

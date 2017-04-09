package com.hotusm.thread;
/**
 * 
 * 
 * @author Hotusm
 * @since 2016-03-19
 * 原子性和易变性
 * 
 * 原子性:原子性可以应用于除long和double之外的所有基本类型上的简单操作(不可分操作).
 * 但是JVM会将64位的读取和写入当做是两个分离的32位操作.如果使用volatile修饰的话.
 * 可以使long和double的简操作时原子性的
 * 
 * 可视性:表示的是一个原子操作的域的改变只是储存在本地处理器的缓存中，导致对其他的
 * 任务是不可见的.这样就导致数据的不一致，如果域声明为volatile，那么每一次的写操作
 * 都会刷新到主内存中,每一次的读操作都会去主内存中读取数据.同理同步也是起到了相同的作用(
 * 使用synchronized也有相同的效果
 * )
 */
public class VolatileTest {
	
	private volatile String num;
	

}

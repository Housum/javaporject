package com.hotusm.thread.detail.book.safecontainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.junit.Test;

/**
 * 
 *演示一些线程安全的容器类
 *	Hashtable synchronizedList ConcurrentMap Vertor
 *	CopyOnWriteArrayList  CopyOnWriteArraySet
 *	队列: BlockingQueue  ConcurrentLinkedQueue
 *	只要是放在这些线程安全的类库中  那么数据对于其他的线程来说也是
 *  可见的
 *  2.通过Collections中很多的方法 能够将一般的容器转化为同步的容器
 *  下面的这几个测试就是例子
 */
public class ListContainer {

	@Test
	public void test(){
		Collections.synchronizedList(new ArrayList<String>());
	}
	public void test1(){
		Collections.synchronizedMap(new HashMap<String,Object>());
	}
}

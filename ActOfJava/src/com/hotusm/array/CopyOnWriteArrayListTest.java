package com.hotusm.array;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * http://blog.csdn.net/yinwenjie/article/details/51818352
 *  这是一个线程安全的List  在每一次的修改操作的时候 
 *  当进行容器中元素的修改操作时，它会首先将容器中的原有元素克隆到一个副本容器中，
 *  然后对副本容器中的元素进行修改操作。待这些操作完成后，再将副本中的元素集合重新
 *  会写到原有的容器中完成整个修改操作
 *
 */
public class CopyOnWriteArrayListTest {
	
	public static void main(String[] args) {
		
		List<Object> list=new CopyOnWriteArrayList<Object>();
	}
}

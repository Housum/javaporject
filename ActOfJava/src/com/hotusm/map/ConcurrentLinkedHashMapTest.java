package com.hotusm.map;

/**
 * 
 * LRU的全称是Least Recently Used（最近最少使用），它是一种选择算法，有的文章中也把LRU算法称为“缓存淘汰算法”
 *
 *我们可以看到几个关键点：

		整个队列有一个阀值用于限制能够存放于队列容器中的最大元素个数，这个阀值我们暂且称为maxCacheSize。

		当队列中的元素还没有达到这个maxCacheSize时，进入队列的元素将被放置在队列的最前面，队列会保持这种处理策略直到队列中的元素达到maxCacheSize为止。

		当队列中的某个元素被选择时（一般来说，队列允许开发人员在选择元素时传入一个Key，队列会依据这个Key进行元素选择），被命中的元素又会重新排列到队列的最前面。这样一来，队列最尾部的元素就是近期使用最少的一个元素。

		一旦当队列中的元素达到maxCacheSize后（不可能超过），新进入队列中的元素将会把队列最尾部的元素挤出队列，而它自己会排列到队列的最顶部。

	
 */
public class ConcurrentLinkedHashMapTest {
	
	public static void main(String[] args) {
		
		String s="";
		System.out.println(s);
	}
}

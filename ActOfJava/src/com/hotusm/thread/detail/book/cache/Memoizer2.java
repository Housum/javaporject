package com.hotusm.thread.detail.book.cache;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
/**
 * 
 * @author Hotusm
 *
 * @param <A>
 * @param <V>
 * 因为单纯的用ConcurrentHashMap的话  还是不能满足高性能  因为
 * 会出现当一个计算很久的操作的话  那么就会出现多个计算同时进行
 * 现在使用Future<V> 可以实现 计算在未来的  现在的 以往的计算结果
 */
public class Memoizer2<A,V> implements Computable<A, V>{
	
	private final Computable<A,V> c;
	private final Map<A,Future<V>> cache=new ConcurrentHashMap<A,Future<V>>();
	
	public Memoizer2(Computable<A,V> c) {
		this.c=c;
	}
	
	/**
	 * 表现出了很好的并发性  若结果已经计算出来了 那么就直接返回  如果新线程来的时候  
	 * 
	 */
	@Override
	public V compute(final A arg) {
		//将Future放在缓存中  这样的话  每次都到其中获取
		Future<V> result = cache.get(arg);
		//这里不是原子性的操作  所以还是有重复计算的问题  
		if(result==null){
			Callable<V> eval=new Callable<V>() {
				@Override
				public V call() throws Exception {
					//计算结果
					return c.compute(arg);
				}
			};
			
			FutureTask<V> ft=new FutureTask<V>(eval);
			result=ft;
			//这种方式就避免了上面所说的问题  这是一个原子的操作   
			cache.putIfAbsent(arg, ft);
			//cache.put(arg, ft);
			ft.run();
		}
		
		try {
			return result.get();
		} catch (Exception e) {
			e.printStackTrace();
			//如果出现错误的话  那么就缓存中的数据清理  保证不会对缓存出现污染
			cache.remove(arg);
		}
		return null;
	}

}

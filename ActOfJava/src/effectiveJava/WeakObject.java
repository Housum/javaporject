package effectiveJava;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

import org.junit.Test;
/**
 * 清除那些不需要的对象  
 * 
 * @author Hotusm
 *
 */
public class WeakObject {
	

	@Test
	public void testLinkedHashMap(){
		LinkedHashMap<String, Integer> map=new LinkedHashMap<String, Integer>();
		map.put("first", 1);
		map.put("second", 2);
		//在LinkedHashMap<K,V>中有一个removeEldestEntry()方法,我们可以
		//使用这个特性  将不使用的缓存进行清除  而不用使每一用的对象占用内存
	}
	
	/*
	 * 在这个map中存储的键是弱引用  对于那些没有引用的会马上的被当做垃圾回收
	 */
	public void testWeakHashMap(){
		WeakHashMap<String,Integer> map=new WeakHashMap<String,Integer>();
	}
}

package jvm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @author Hotusm  <br/>
 * @date 2017年3月19日   <br/>
 * @description
 * -verbose:gc 
 * -Xms20M(最小堆内存) 
 * -Xmx20M(最大堆内存)
 * -Xmn10M(分配给年轻代)
 * -XX:+PrintGCDetails 输出详细GC日志
 * -XX:SurvivorRatio=8 Eden区和survivor区的比例
 * -XX:-UseTLAB 是否使用本地线程分配缓冲
 * -XX:+HeapDumpOnOutOfMemoryError(在出现异常的情况下  将内存快照dump出来) 
 * -Xss128k 栈大小
 * -XX:MaxPermSize 方法区的最大值(JDK<=1.7 1.8将方法区移去，增加了metaspaces)
 * -XX:PermSize 方法区的大小
 */
public class OOMTest {
	
	public static void main(String[] args) {
//		List<Object> list=new ArrayList<>();
//		while(true){
//			list.add(new Object());
//		}
		
		//new OOMTest().testStackOverflow();
		//new OOMTest().testPermSizeOutOfMemoryError();
		new OOMTest().testDirectMemory();
	}
	private int index=0;
	public void testStackOverflow(){
		index++;
		testStackOverflow();
	}
	public void testPermSizeOutOfMemoryError(){
		
		while(true){
			Enhancer enhancer=new Enhancer();
			enhancer.setSuperclass(OOMTest.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				
				@Override
				public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
					return arg3.invoke(arg0, arg2);
				}
			});
			enhancer.create();
		}
	}
	/**
	 * 直接内存的分配也可导致内OOM
	 * @description <br/>
	 */
	public static void testDirectMemory(){
			while(true){
				getUnsafe().allocateMemory(1*8*1024*1024);
			}
	}
	
	public static sun.misc.Unsafe getUnsafe(){
		try {
			Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe"); //Internal reference  
			f.setAccessible(true);  
			sun.misc.Unsafe unsafe = (sun.misc.Unsafe) f.get(null);
			return unsafe;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

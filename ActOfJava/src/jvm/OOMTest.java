package jvm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @author Hotusm  <br/>
 * @date 2017��3��19��   <br/>
 * @description
 * -verbose:gc 
 * -Xms20M(��С���ڴ�) 
 * -Xmx20M(�����ڴ�)
 * -Xmn10M(����������)
 * -XX:+PrintGCDetails �����ϸGC��־
 * -XX:SurvivorRatio=8 Eden����survivor���ı���
 * -XX:-UseTLAB �Ƿ�ʹ�ñ����̷߳��仺��
 * -XX:+HeapDumpOnOutOfMemoryError(�ڳ����쳣�������  ���ڴ����dump����) 
 * -Xss128k ջ��С
 * -XX:MaxPermSize �����������ֵ(JDK<=1.7 1.8����������ȥ��������metaspaces)
 * -XX:PermSize �������Ĵ�С
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
	 * ֱ���ڴ�ķ���Ҳ�ɵ�����OOM
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

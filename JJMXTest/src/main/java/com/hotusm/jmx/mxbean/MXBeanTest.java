package com.hotusm.jmx.mxbean;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.List;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016年10月27日   <br/>
 * @description
 * @link  https://www.ibm.com/developerworks/cn/java/j-mxbeans/
 * 
 */
public class MXBeanTest {

	public static void main(String[] args) {
		
		/*监视线程的状态*/
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		System.out.println("thread count:"+threadMXBean.getThreadCount()+" ids:"+Arrays.asList(getAllThreads()));
		/*监视内存的状态*/
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		System.out.println("useage of memory:"+memoryMXBean.getHeapMemoryUsage());
		/*监视运行时的状态*/
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		System.out.println(runtimeMXBean.getInputArguments());
		/*监视多个垃圾回收器的状态*/
		List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
		
		System.out.println("GC Name:");
		for(GarbageCollectorMXBean gc:garbageCollectorMXBeans){
			
			System.out.println(gc.getName());
		}
	}
	
	public static long[] getAllThreads(){
		
		return ManagementFactory.getThreadMXBean().getAllThreadIds();
	}
	
	
}

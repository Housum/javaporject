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
 * @date 2016��10��27��   <br/>
 * @description
 * @link  https://www.ibm.com/developerworks/cn/java/j-mxbeans/
 * 
 */
public class MXBeanTest {

	public static void main(String[] args) {
		
		/*�����̵߳�״̬*/
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		System.out.println("thread count:"+threadMXBean.getThreadCount()+" ids:"+Arrays.asList(getAllThreads()));
		/*�����ڴ��״̬*/
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		System.out.println("useage of memory:"+memoryMXBean.getHeapMemoryUsage());
		/*��������ʱ��״̬*/
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		System.out.println(runtimeMXBean.getInputArguments());
		/*���Ӷ��������������״̬*/
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

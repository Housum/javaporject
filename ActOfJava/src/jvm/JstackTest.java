package jvm;

import java.io.IOException;
import java.util.Map;

/**
 * 
 * @author Hotusm
 * 在JDK1.5以后，增加了Thread.getAllStackTraces()这个方法,用来获取
 * 当前所以的线程的堆栈信息.
 */
public class JstackTest {

	public static void main(String[] args) {
		
		alwaysRun();
			
		for(Map.Entry<Thread, StackTraceElement[]> stackTrace:Thread.getAllStackTraces().entrySet()){
			Thread t=stackTrace.getKey();
			StackTraceElement[] stes=stackTrace.getValue();
			System.out.println("线程:"+t.getId());
			for(StackTraceElement ste:stes){
				System.out.println(ste);
			}
			
		}
		
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void alwaysRun(){
		
		while(true){
			System.out.println("--------");
		}
	}
	
}

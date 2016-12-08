package jvm;

import java.io.IOException;
import java.util.Map;

/**
 * 
 * @author Hotusm
 * ��JDK1.5�Ժ�������Thread.getAllStackTraces()�������,������ȡ
 * ��ǰ���Ե��̵߳Ķ�ջ��Ϣ.
 */
public class JstackTest {

	public static void main(String[] args) {
		
		alwaysRun();
			
		for(Map.Entry<Thread, StackTraceElement[]> stackTrace:Thread.getAllStackTraces().entrySet()){
			Thread t=stackTrace.getKey();
			StackTraceElement[] stes=stackTrace.getValue();
			System.out.println("�߳�:"+t.getId());
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

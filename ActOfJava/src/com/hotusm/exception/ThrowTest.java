package com.hotusm.exception;
/*
 * �����쳣:
 * �쳣�׳�����
 * */

public class ThrowTest {
	
	public static void main(String[] args) {
		try {
			test();
			//int i=1/0;
			/*
			 *�������쳣��ʱ��,��java�����Ķ���һ��
			 *��ʹ��new�ڶ��д���һ������ 
			 */
		} catch (Exception e) {
			String simpleName = e.getClass().getSimpleName();
			System.out.println(simpleName);
			//����쳣����ջ�켣
			//����ֱ��ͨ��getStackTrace��õ���ջ��Ϣ
			e.printStackTrace(System.out);
			//��ʾÿһ��ջ֡����Ϣ
			StackTraceElement[] stackTrace = e.getStackTrace();
			int l = stackTrace.length;
			System.out.println("��ʼ��ӡջ��Ϣ...");
			for(StackTraceElement st:stackTrace){
				System.out.println(st.toString());
			}
			System.out.println(l);
		}
		
		
	}
	public static void test(){
		test1();
	}
	public static void test1(){
		int i=10/0;
	}
}

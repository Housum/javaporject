package com.hotusm.thread.detail.start;
/**
 * 
 *��������ʾ���Ƕ���Դ�Ŀ���  ����ʱ���п����ж���߳̾���
 *�����Դ��Կ��  ������ֻ��һ����о  ����ֻ�ܹ�һ��Կ�׽���
 *����  �ڰγ�Կ���Ժ� �������̲߳��ܹ�����
 *
 *��JAVA��synchronized�ؼ��ֿ��Լ��غܶ�λ�á�
 *��������һ�����������ϼ�synchronized�ؼ��֡�Ҳ�����ڷ������м�synchronized�ؼ��֡�
 *��������static���м�synchronized�ؼ��֡����µĴ��붼����ȷ�ģ�
 *
 *synchronized�ؼ��ּ����ڷǾ�̬������ʱ�� 
 *�����ĺ����synchronized(this){}��������ͬ��������ӵ����������Ķ�����ж�����״̬��顣

 *synchronized�ؼ��ּ����ھ�̬������ʱ�� 
 *�����ĺ����synchronized(Class.class)�����������ơ�������ӵ�������������Ķ�����ж�
 *����״̬��飨�౾��Ҳ��һ������
 */
public class ThreadLock {
	
	private static final Object MUNTX=new Object();
	//������߳��ǲ���ȫ��  ʹ��������в���synchronized
	private static int num=0;
	public static void main(String[] args) {
		
		Thread a=new Thread(new Runnable() {
			
			@Override
			public void run() {
				//while(true){
					synchronized(MUNTX){
						Log.info("a:"+num++);
					//}
				}
			}
		});
		
		Thread b=new Thread(new Runnable() {
			
			@Override
			public void run() {
				//while(true){
					synchronized(MUNTX){
						Log.info("b:"+num++);
					//}
				}
			}
		});
		a.start();
		b.start();
	}
}

package com.hotusm.thread;

/**
 * 1.��̨�߳�:
 * ��̨�߳���ָ�ڳ������е�ʱ���ں�̨�ṩһ��ͨ�÷�����̣߳����������̲߳����ǳ����в��ɻ�ȱ�Ĳ���
 * ��˵�ǰ̨�̶߳�������ʱ��,��ʱ��̨�߳�Ҳ�����
 * 2.�̵߳����ȼ�:
 * �ڴ�����������,�̶߳�Ӧ����Ĭ�ϵ����ȼ�ִ�С���ͼ�����̵߳����ȼ���һ�ִ���
 * 
 * @author Hotusm
 * @since 2016-03-07
 */
public class DaemonTest extends Thread{

	int i;
	@Override
	public void run() {
		
		//��Ȼ����������������,�̻߳�һֱ��ִ��,������ʵ�ʵ����߳���,�������������߳�Ϊ
		//��̨�߳�,�����߳���ǰ̨�߳�,������ǰ̨�߳�ִ������Ժ󣬺�̨�߳���Ȼ��ֹͣ��.
		while(true){
			System.out.println(Thread.currentThread().toString());
			//ͨ�����õ�ǰ�̵߳����ȼ�,��ֵԽ���̵߳����ȼ�Խ��,ע��:��Ȼ���������ȼ�
			//���ǲ���ȷ��һ���ǰ���������ȼ�ִ�еģ����Ҳ���˵���ȼ��͵ľͱ�������,ֻ��
			//�������ȼ��͵�ִ�е�Ƶ�ʻ�Ƚϵ�һЩ
			Thread.currentThread().setPriority(10);
			System.out.println(i++);
		}
	}
	
	public static void main(String[] args) {
		DaemonTest dt=new DaemonTest();
		//�����setDaemon()ע�͵�,��ô�̻߳�һֱ��ִ��,û��ע�͵�����£����߳�ִ�����
		//�Ժ󣬺�̨�߳�Ҳ��Ӧ�Ľ���
		dt.setDaemon(true);
		dt.start();
		//�߳�Ĭ�ϵ����ȼ�����һ����,����Thread.currentThread().setPriority()
		//�������̵߳����ȼ�
		int priority2 = Thread.currentThread().getPriority();
		System.out.println("���̵߳����ȼ�:"+priority2);
		System.out.println(Thread.currentThread().toString());
	}
	
}

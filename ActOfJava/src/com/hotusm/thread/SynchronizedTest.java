package com.hotusm.thread;

import com.hotusm.thread.detail.start.Log;


/**
 * 
 * 
 * @author Hotusm
 * @since 2016-03-13
 * ��Դ����
 * 
 * ������ �ص�!!!
 * 
 */
public class SynchronizedTest {
	
	public static void main(String[] args) {
//		IntGen intGen=new IntGen();
//		new Provider(intGen);
//		new Provider(intGen);
//		new Provider(intGen);
//		new Provider(intGen);
//		new Provider(intGen);
		
		for(int i=0;i<2;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					/**
					 * ������ǲ��������synchronized �Ļ�  ��ô�ͻ��׳�java.lang.IllegalMonitorStateException
					 * ����쳣����ԭ�����˵��û�м������������  ����wait()  ����������Ǽ���synchronized�Ļ� ��ô��
					 * �ܹ���֤�ܹ��õ�������
					 */
					synchronized (this) {
						try {
							Log.info("�߳�������");
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();;
		}
	}
}
class IntGen{
	
	private int num=0;
	/**
	 * 
	 * �Է������м���,��ôÿһ��ֻ����һ���߳��ܹ�����
	 * �����Ͳ��������ͬ������
	 * @return
	 */
	public synchronized int next(){
		num++;
		return num;
	}
}
class Provider extends Thread{
	
	private IntGen intGen;

	public Provider(IntGen intGen) {
		super();
		this.intGen = intGen;
		start();
	}

	@Override
	public void run() {
		while(true){
			System.out.println(intGen.next());
		}
	}
	
}







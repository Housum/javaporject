package com.hotusm.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 
 * @author Hotusm
 * Lock����JAVA SE5����ʽ����Ļ������
 * Lock�Ĺ�����synchronizedһ���Ĺ���,���ǲ�һ���ĵط�����
 * ���뱻��ʽ�Ĵ������������ͷ�
 */
public class LockTest {
	
	public static void main(String[] args) {
		IntGen1 intGen=new IntGen1();
		new Provider1(intGen);
		new Provider1(intGen);
		new Provider1(intGen);
		new Provider1(intGen);
		new Provider1(intGen);
	}
}
class IntGen1{
	/*
	 * ������ʽ�Ĵ����������,������ʽ���������ͷ�
	 * 
	 * */
	private Lock lock =new ReentrantLock();
	private int num=0;
	
	public int next(){
		//��ʽ��������Դ
		lock.lock();
		try {
			num++;
			return num;
		} finally{
			//��ʽ���ͷ���Դ
			lock.unlock();
		}
		
	}
}
class Provider1 extends Thread{
	
	private IntGen1 intGen;

	public Provider1(IntGen1 intGen) {
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
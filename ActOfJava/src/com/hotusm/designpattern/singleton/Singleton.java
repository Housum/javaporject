package com.hotusm.designpattern.singleton;

import java.util.concurrent.TimeUnit;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016��10��10��   <br/>
 * @description  ���ںܶ����  ����ϵͳ��ֻ��Ҫһ��ʵ�� 
 * ���ʱ��Ϳ���ʹ�õ���ģʽ
 */
public class Singleton {
	
	private static Singleton PRODUCER;
	
	//�����캯������Ϊ˽�е�  �����������඼���ܹ�����  ֻ�ܹ�������з���
	private Singleton(){
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static Singleton getInstance(){
		
		//���������ͬ���Ļ�  ��ô�ڶ��߳��л���ֶ���߳�ͬʱ�Ľ��뵽null��  ������ظ�����������
		if(PRODUCER==null)
			synchronized (Singleton.class) {
				PRODUCER=new Singleton();
			}
		
		return PRODUCER;
	}
	
	public static void main(String[] args) {
		
		Singleton.getInstance();
		System.out.println("---------");
		Singleton.getInstance();
	}
}

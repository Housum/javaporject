package com.hotusm.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @since 2016-03-27
 * @author hotusm
 * 
 * 1.��JAVA SE5�����AtomicInteger,AtomicLong,AtomicReference��
 * �����ԭ���Ա����࣬ʹ����Щ���ʱ�򣬲���Ҫ����ԭ��������Ϳ���������
 * 2.��Ϊ��Ըд����.������һ��API������.�����Ҫ���ԣ�����д��������������ܲ���
 * �Ƿ����̰߳�ȫ
 * 3.�������������»�ʹ�ã�һ�����Ƕ�ʹ��synchronized�ؼ��ֻ�����Lock��ʽ
 * ����
 * 
 * 4��Դ�������ǿ��Կ���ԭ�Ӳ�����ʹ�����ֹ���
 * @see java.util.concurrent.atomic.AtomicInteger.addAndGet(int)
 * �ֹ����ͱ�����������:
 * ��������һ�ֶ�ռ�����������ǰ���ǡ���ͻһ���ᷢ���������Դ���ĳ�ο��ܳ������ݳ�ͻ�Ĵ���ʱ���������ξ�Ҫ��ĳ���̶߳�ռ������ռ��ζ�š���������ִ����δ���������̡߳��������롰������/������״̬
 * �ֹ����ٶ�����ͻ��һ������֡���������ֳ�ͻ��������ԣ�ֱ����ͻ��ʧ�� �����ֹ����ļٶ������������ֹ��������ռ��Դ����Ȼ���ܾͻ���ڱ�������AtomicInteger��һ����׼���ֹ���ʵ�֣�sun.misc.Unsafe��JDK�ṩ���ֹ�����֧�֡�
 * 
 * @link http://blog.csdn.net/yinwenjie/article/details/50698751
 * 
 * 5.ԭ�Ӳ�������:
 * 	 �󲿷ֻ�������    �����Լ���������
 */
public class AtomicTest {

	public static void main(String[] args) {
		//���⼸����ʹ�õ�ʱ����Ҫ���Ĳ�������
		AtomicInteger intt=new AtomicInteger(0);
		intt.addAndGet(10);
		System.out.println(intt.get());
		/**
		 * ���ǶԶ�����÷�
		 */
		AtomicReference<String> ar=new AtomicReference<String>();
		ar.getAndSet("hotusm");
		System.out.println(ar.get());
		
		
	}
}

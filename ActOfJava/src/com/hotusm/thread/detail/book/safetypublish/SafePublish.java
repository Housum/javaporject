package com.hotusm.thread.detail.book.safetypublish;
/**
 * 
 *��ȫ������ʾ��
 *	1.�ھ�̬��ʼ�������г�ʼ��һ�����������
 *  2.����������ñ�����volatile���͵������AtomicReferance������
 *  3.����������ñ��浽ĳ����ȷ��������final��������
 *  4.����������ñ��浽��һ��������������
 */
public class SafePublish implements Cloneable{
	
	private static Object first;
	{
		first=new Object();
	}
	private volatile Object second;
	
	private final Object thirdly=new Object();
	
	private Object fourthly;
	
	private Bo bo;

	public Bo getBo() {
		return new Bo(bo);
	}

	public void setBo(Bo bo) {
		this.bo =new Bo(bo);
	}

	public static Object getFirst() {
		return first;
	}

	public static void setFirst(Object first) {
		SafePublish.first = first;
	}

	public Object getSecond() {
		Object second=this.second;
		return second;
	}

	public void setSecond(Object second) {
		this.second = second;
	}

	public Object getFourthly() {
		return fourthly;
	}

	public synchronized void setFourthly(Object fourthly) {
		this.fourthly = fourthly;
	}

	public synchronized Object getThirdly() {
		return thirdly;
	}
	
	
	
	
}

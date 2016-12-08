package com.hotusm.second;

/**
 * 
 * @author Hotusm
 * 1.final��ʹ��:����,��������
 */
public class FinalTest {
	/*
	 * 1.����final��������˵,�������ֻ��ָ��һ�����������ָ��������
	 * ����,���ǲ����ܱ�֤���󲻱�,����ľ�������
	 * 
	 */
	private static T t=new T();
	private static final T tt=t;
	/*
	 * 5.���ڻ�������,��Ϊ���Ƕ��Ǵ����ڶ�ջ��,������ֵ
	 * �ǲ����
	 * */
	private final int i=10;
	
	/*
	 * 6.�������Ե�private��������ʽ��ָ��Ϊfinal��,
	 * ���������ǲ��ܹ���д���Ҽ̳�����.
	 * */
	private void say(){
		System.out.println("private method");
	}
	public static void main(String[] args) {
		System.out.println("tt:"+tt.i+" t:"+t.i);
		t.i=10;
		System.out.println("tt:"+tt.i+" t:"+t.i);
	}
}

class T{
	int i=0;
}
/*2.�հ�final,�ڶ����ʱ��û�н��и�ֵ,�����ڹ��캯���б�����г�ʼ��,
 * ��ᱨ��
 * */
class FinalTest1{
	
	private final String name;
	
	public FinalTest1() {
		//�ڹ������н��г�ʼ��
		name="default";
	}
	
	public String getName() {
		return name;
	}
	/*3.final����
	 * ���ڷ���ǰ�����final�������Ƿ�ֹ���าд,����������
	 * ���ǲ��ɼ���
	 * */
	public final void say(){
		System.out.println("final method");
	}
	
}
/*
 * 4.FinalTest1�е�say()��������B�����������˵
 * �ǲ��ɼ���
 * */
class D extends FinalTest1{
	
}
/*
 * 7.
 * ��ʹ��final���ε��಻�ܱ��̳�
 * ��final���ε���ķ���Ĭ�϶���final��
 * 
 * */
final class C{
	int i=10;
	float j=10f;
	//����Ĭ����final��
	 void say(){
		// alternative_hashing_threshold_default
	 }
}
//���ܻ�����final���ε���
//class DB extends C{
//	
//}
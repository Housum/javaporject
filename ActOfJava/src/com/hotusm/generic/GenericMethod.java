package com.hotusm.generic;

import java.util.List;


/**
 * 
 * @author Hotusm
 * @since 2016-03-06
 * 1.���ͷ�����ʹ��,���ͷ������Զ��Ĳ����ƶ�
 * 2.���ͷ����ͷ�����֮����û�к���ɵ���ϵ��,���㲻�Ƿ���
 * ��Ļ�,Ҳ���Զ��巺�ͷ����������ƶϵĺ�����˵
 * ���ڷ����Ĳ���,java�����еĽ��в���ʶ�𣬶�����
 * ���෺��һ����Ҫ���������ʾ��ָ������
 * 
 */
public abstract class GenericMethod {
	
	/**
	 * ���ͷ������Զ�ʶ���������,
	 * ������Ҫ�ͻ��˳���Ա֪���������¾���ʵ��
	 * 
	 * @param t
	 */
	public <T> void set(T t){
		System.out.println(t);
	}
	public <T,X> void set1(T t,X x){
		System.out.println();
	}
	//�����б���Ժ������ĺͷ��ͽ��н��
	public abstract <T> List<T> makeList(T...arg);
	
	public static void main(String[] args) {
		//new GenericMethod().set(1234);
	}
}
class G extends GenericMethod{

	@Override
	public <T> List<T> makeList(T... arg) {
		return null;
	}
}
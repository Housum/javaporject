package jvm;

import java.io.IOException;


/**
 * 
 * ƽʱ����˵ָ��ջ����JAVA�����ջ,��ȷ�е�˵��
 * JAVA�����ջ�еľֲ�������
 * 
 * @author Hotusm
 *
 */
public class VMStack {

	public void a(){
		b();
	}
	public void b(){
		a();
	}
	/**
	 * <code>
	 * 	Exception in thread "main" java.lang.StackOverflowError
	 * </code>
	 * 1.������δ����ֱ�ӱ���,����������ѭ����.�����ε�ԭ�����
	 * ÿһ������ִ�е�ʱ�򣬾ͻᴴ��һ��ջ.���߳������ջ��ȴ��������˵����
	 * ����ȵ�ʱ�򣬾ͻᱨ�����.
	 * 2.��JAVA��������Խ��ж�̬���ݵ�ʱ��,�޷����뵽�㹻���ڴ棬�ͻ��׳�
	 * OutofMemoryError
	 * @param args
	 */
	public static void main(String[] args) {
		new VMStack().a();
	}
}

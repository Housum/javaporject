package com.hotusm.second;
/**
 * 
 * @author Hotusm
 * 1.�������
 */
public class AbstractTest {
	
	public static void main(String[] args) {
		
	}
	
}

abstract class AA{
	/*  1.��Ϊһ������,���ǿ�Ƶ�������븲д
	 * ����,��ô����������Ϊabstract,ͬʱ���
	 * ĳһ�������abstract�ķ�ʽʱ,��ô�����Ҳ��������Ϊabstract
	 * 
	 * */
	abstract void say();
	void say1(){
		System.out.println("say1");
	}
}
/*2.
 * �ٶ��ڼ̳г����������,����Գ��󷽷����и�д
 * ��������Գ��󷽷����и�д,��ô����Ҳ���������ɳ���(abstract)
 * 
 * */
class BB extends AA{

	@Override
	void say() {
		System.out.println("say");
	}}

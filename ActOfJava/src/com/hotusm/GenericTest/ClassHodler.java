package com.hotusm.GenericTest;

/**
 * 
 * ����ʵ���˲��������͵�����(��һ��������Ϊ�������в���)
 * 
 * 1.ʹ�÷��ͳ��ж���,
 * ֻ��Ҫ����������<X>
 * @author Hotusm
 * @since 2016-03-06
 * 
 * @param <T>���ǳ�TΪ���Ͳ���
 */
public class ClassHodler<T>{
	
	//����ʹ�õ�����
	private T vo;

	public T getVo() {
		return vo;
	}
	public void setVo(T vo) {
		this.vo = vo;
	}
	@Override
	public String toString() {
		System.out.println("vo: "+vo);
		return super.toString();
	}
	
	public static void main(String[] args) {
		ClassHodler<String> gh=new ClassHodler<String>();
		gh.setVo("hotusm");
		gh.toString();
		
	}
}

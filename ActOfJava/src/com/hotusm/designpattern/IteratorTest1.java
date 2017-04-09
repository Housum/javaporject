package com.hotusm.designpattern;

/**
 * 
 * 
 * @author Hotusm
 * @since 2016-03-11
 * ���ģʽ->������ģʽ
 * 1.����ۼ�������������
 *  �����һ���ۼ��Ľӿ�û���ṩ�޸ľۼ�Ԫ�صķ����������Ľӿھ�����ν��խ�ӿڡ�
�����ۼ�����Ϊ�����Ӷ����ṩһ����ӿڣ���Ϊ���������ṩһ��խ�ӿڡ�����֮��
        �ۼ�������ڲ��ṹӦ���Ե����Ӷ����ʵ��������Ա�����Ӷ����ܹ��Ծۼ��������㹻����,
        �Ӷ����Խ��е������������ǣ��ۼ�����Ӧ�������������Ķ����ṩ��Щ��������Ϊ��������Ӧ
        �����������Ӷ��������Щ������������ֱ�Ӳٿؾۼ�����
 *       
 * ����JAVA�����У�ʵ��˫�ؽӿڵİ취���ǽ�����������Ƴɾۼ�����ڲ���Ա�ࡣ����������
 * ���󽫿�����ۼ�������ڲ���Աһ�����ʾۼ�������ڲ��ṹ���������һ��ʾ���Ե�ʵ�֣�˵
 * ������˫�ؽӿڵĽṹʱ��ô�������ģ��Լ�ʹ����˫�ؽӿڽṹ֮�������ģʽ��ʵ�ַ���������
 * ͬʱ��֤�ۼ�����ķ�װ�͵����ӹ��ܵ�ʵ�ֵķ�����������ʵ�ַ�����
 * 
 * �����ڵ������Ǿۼ����ڲ��࣬�����ӿ������ɷ��ʾۼ���Ԫ�أ����Ե����ӿ�������ʵ�ֵ�������
 * �����ƶԾۼ�Ԫ�صĵ����߼������ڵ��������ھۼ��Ľṹ֮�ڶ���ģ���������ĵ������ֽ�����
 * �������ӣ�Intrinsic Iterator����
 * 
 * 
 */
public class IteratorTest1 {
	
	public static void main(String[] args) {
		ConcreteAggregate<String> ca=new ConcreteAggregate<String>(new String[]{"111","222","333","444"});
		Iterator2<String> iterator2 = ca.iterator2();
		while(!iterator2.isDone()){
			System.out.println(iterator2.currentItem());
			iterator2.next();
		}
	}
}
/**
 * 
 * ���������
 * @author Hotusm
 *
 * @param <U>
 */
interface Iterator2<U>{
	
	public void first();
	public void next();
	public boolean isDone();
	public U currentItem();
	
}

/**
 * 
 * ����ۼ�
 * @author Hotusm
 *
 * @param <U>
 */
abstract class Aggregate1<U>{
	
	public abstract Iterator2<U> iterator2();
}

class ConcreteAggregate<U> extends Aggregate1<U>{
	
	private U[] us;
	

	public ConcreteAggregate(U[] us) {
		super();
		this.us = us;
	}

	@Override
	public Iterator2<U> iterator2() {
		
		return new ConcreteInterator2<U>();
	}
	
	
	private class ConcreteInterator2<K> implements Iterator2<U>{
		private int index=0;
		private int size=us.length;
		
		@Override
		public void first() {
			index=0;
		}

		@Override
		public void next() {
			if(index<size){
				index++;
			}
		}

		@Override
		public boolean isDone() {
			return index>=size;
		}

		@Override
		public U currentItem() {
			if(index<size){
				return us[index];

			}
			return null;
		}
		
		
	}
}




package com.hotusm.designpattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Hotusm
 * @since  2016-03-11
 * 
 *	���ģʽ->���������ģʽ(�α�ģʽ)
 *  ������ģʽ�ֽ��α�(Cursor)ģʽ���Ƕ������Ϊģʽ��
 *  ������ģʽ����˳��ط���һ���ۼ��е�Ԫ�ض����ر�¶�ۼ����ڲ�����
 *  (internal representation)��
 *  
 *  1.����ۼ�������������:
 *  �����һ���ۼ��Ľӿ��ṩ�˿��������޸ľۼ�Ԫ�صķ���������ӿھ�����ν�Ŀ�ӿڡ�
 *  ����ۼ�����Ϊ���ж����ṩͬһ���ӿڣ�Ҳ���ǿ�ӿڵĻ�����Ȼ�����������ģʽ��
 *  �����Ӷ����Ҫ�󡣵��ǣ��������ƻ��Ծۼ�����ķ�װ�������ṩ��ӿڵľۼ���������ۼ���
 *  �ۼ�����������ṩͬ���Ŀ�ӿڣ�����ͼ��ʾ��
 *  �ڡ����ھۼ��Լ�ʵ�ֵ����߼��������ⲿ�ṩ�ʵ��Ľӿڣ�ʹ�õ����ӿ��Դ��ⲿ���ƾۼ�Ԫ��
 *  �ĵ������̡�����һ�������������ƵĽ�����һ���α���ѣ����ֵ����ӽ����α�����ӣ�Cursor
 *   Iterator�������ڵ��������ھۼ��ṹ֮��ģ���������ĵ������ֽ������������ӣ�Extrinsic Iterator��
 *  �ۡ����ڿ�һ������ۼ������������ӵ�ʵ�֡�һ������ۼ�������ṩ�����Լ��ڲ�Ԫ�صĽӿڣ�����������������Traversing Method��,
 *  �Ӷ�ʹ���������ӿ���ͨ���ۼ��ı�������ʵ�ֵ������ܡ�
 *  
 *  
 */
public class IteratorTest {

	public static void main(String[] args) {
		Aggregate<String> aggregate=new ConcreteAggreage<String>(new String[]{"111","222","333","444"});
		Iterator1<String> inerator1 = aggregate.inerator1();
		while(!inerator1.isDone()){
			System.out.println(inerator1.currrentItem());
			inerator1.next();
		}
	}
	
	/**
	 * 
	 * ���������ģʽ�ĸ��ָ���
	 */
	public void testIterator(){
		/**
		 * List:�ۼ�(Aggregate)��ɫ,�˳����ɫ��������������(Iterator)����Ľӿ�
		 * ArrayList:����ۼ�(ConcreteAggregate)��ɫ��ʵ���˴���������(Iterator)����Ľӿڣ�����һ�����ʵľ��������ʵ��
		 * �ۼ����Ǿ���ľۼ�Ԫ�� 
		 * 
		 */
		
		List<String> list=new ArrayList<String>();
		/**
		 * ���������(Iterator)��ɫ���˳����ɫ���������Ԫ������Ľӿڡ�
		 * 
		 */
		Iterator<String> iterator = list.iterator();
	}
}
/**
 * 
 * ���������
 * @author Hotusm
 *
 * @param <U>
 */
interface Iterator1<U>{
	
	public void first();
	public void next();
	public boolean isDone();
	public U currrentItem();
}
/**
 * 
 * ����ĵ�����
 * @author Hotusm
 *
 * @param <U>
 */
class ConcreteIterator1<U> implements Iterator1<U>{
	
	private ConcreteAggreage<U> aggregate=null;
	private int size;
	private int index;
	
	public ConcreteIterator1(ConcreteAggreage<U> aggregate) {
		super();
		this.aggregate = aggregate;
		this.size=aggregate.size();
	}

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
	public U currrentItem() {
		return aggregate.getElement(index);
	}
}
/**
 * 
 * ����ۼ���
 * @author Hotusm
 *
 * @param <U>
 */
abstract class Aggregate<U>{
	
	public abstract Iterator1<U> inerator1();
}
/**
 * 
 *����ľۼ���
 * @author Hotusm
 *
 * @param <U>
 */
class ConcreteAggreage<U> extends Aggregate<U>{
	
	private U[] us=null;
	private int size=0;
	

	public ConcreteAggreage(U[] us) {
		super();
		this.us = us;
		size=us.length;
	}

	@Override
	public Iterator1<U> inerator1() {
		return new ConcreteIterator1<U>(this);
	}
	
	public U getElement(int index){
		if(index<0||index>=size){
			return null;
		}
		
		return us[index];
	}
	
	public int size(){
		return size;
	}
}













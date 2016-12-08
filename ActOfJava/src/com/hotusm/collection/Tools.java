package com.hotusm.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class Tools {
	
	@Test
	public void nCopy(){
		
		/**
		 * ����
		 */
		List<StringAddress> all = Collections.nCopies(4, new StringAddress("Hello "));
		List<StringAddress> list=new ArrayList<StringAddress>(all);
		System.out.println(list);
		
		/**
		 * fill���÷� ���ǽ��������������ݵ�ʵ��Ķ����ɵڶ�������ָ����ʵ��
		 */
		list.add(new StringAddress("1111"));
		Collections.fill(list, new StringAddress("Worlds"));
		System.out.println(list);
	}
	
	@Test
	public void testListRemove(){
		List<String> lists=new ArrayList<String>();
		
		/**
		 * ��ÿ�β���֮ǰ����֮ǰ�ļ��Ͻ���remove�������������ܱ�֤��������ظ���ֵ��
		 * ��Ȼ������ǰ���Ѿ�������11  ��������Ϊ�˱�ֵ֤��Ψһ��  ���ǾͿ��Դӵ�һ�ο�ʼ
		 * ���Ƚ���remove����  Ȼ���ڽ���add����
		 */
		lists.add("11");
		String single="11";
		lists.remove(single);
		lists.add(single);
		System.out.println(lists);
	}
}
class StringAddress{
	private String s;
	public StringAddress(String s){
		this.s=s;
	}
	@Override
	public String toString() {
		return super.toString()+" "+s;
	}
}

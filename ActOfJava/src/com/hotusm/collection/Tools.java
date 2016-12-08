package com.hotusm.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class Tools {
	
	@Test
	public void nCopy(){
		
		/**
		 * 拷贝
		 */
		List<StringAddress> all = Collections.nCopies(4, new StringAddress("Hello "));
		List<StringAddress> list=new ArrayList<StringAddress>(all);
		System.out.println(list);
		
		/**
		 * fill的用法 就是将容量所有有内容的实体的都换成第二个参数指定的实体
		 */
		list.add(new StringAddress("1111"));
		Collections.fill(list, new StringAddress("Worlds"));
		System.out.println(list);
	}
	
	@Test
	public void testListRemove(){
		List<String> lists=new ArrayList<String>();
		
		/**
		 * 在每次插入之前都对之前的集合进行remove操作，这样就能保证不会出现重复的值了
		 * 虽然我们在前面已经插入了11  后面我们为了保证值的唯一性  我们就可以从第一次开始
		 * 就先进行remove操作  然后在进行add操作
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

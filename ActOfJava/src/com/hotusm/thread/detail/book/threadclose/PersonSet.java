package com.hotusm.thread.detail.book.threadclose;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * 实例封闭  
 * 1.PersonSet的状态是由HashSet来管理的  而HashSet是非线程安全的
 * 但是由于mySet是私有的并且不会溢出
 * 实例封闭是构建线程安全类的一个最简单的方式,大部分的作用就是将非线程安全的类转化为
 * 线程安全的类(例如:ArrayList HashMap都不是线程安全的  但是在Collections.synchronizedList
 * Collections.synchronizedMap 通过转化成线程封闭的方式来实现线程的安全性
 * )
 * 
 */
public class PersonSet {

	private final Set<Person> mySet=new HashSet<Person>();
	
	public void addPerson(Person person){
		synchronized (this) {
			mySet.add(person);
		}
	}
	
	public boolean containPerson(Person person){
		synchronized (this) {
			return mySet.contains(person);
		}
	}
	
	public static void main(String[] args) {
	
		Map<String,Object> maps=new HashMap<String,Object>();
		Collections.synchronizedMap(maps);
	}
}

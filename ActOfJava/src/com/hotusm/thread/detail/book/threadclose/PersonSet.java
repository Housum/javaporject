package com.hotusm.thread.detail.book.threadclose;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * ʵ�����  
 * 1.PersonSet��״̬����HashSet�������  ��HashSet�Ƿ��̰߳�ȫ��
 * ��������mySet��˽�еĲ��Ҳ������
 * ʵ������ǹ����̰߳�ȫ���һ����򵥵ķ�ʽ,�󲿷ֵ����þ��ǽ����̰߳�ȫ����ת��Ϊ
 * �̰߳�ȫ����(����:ArrayList HashMap�������̰߳�ȫ��  ������Collections.synchronizedList
 * Collections.synchronizedMap ͨ��ת�����̷߳�յķ�ʽ��ʵ���̵߳İ�ȫ��
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

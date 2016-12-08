package com.hotusm.thread.detail.book.scale;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 1.��������ʱ��Խ��(��Ϊ����ζ�������̶߳�����ʱ��Խ��) ��ô�������Ծ�Խ��  
 * 2.��������Խ��(�����ζ��Խ����߳�������) ��ô�����Ծ�Խ��
 * 
 */
public class AttributeStore {

	private Map<String,String> attributes=new HashMap<String,String>();
	private final Set<String> users;
	private final Set<String> queries;
	
//	final Lock lock=new ReentrantLock();
//	final Condition userLock  = lock.newCondition();
//	final Condition queryLock  = lock.newCondition();
	private final Object USER_LOCK=new Object();
	private final Object QUERY_LOCK=new Object();
	
	public AttributeStore(Set<String> users,Set<String> queries) {
		this.users=new HashSet<String>(users);
		this.queries=new HashSet<String>(queries);
	}
	
	/**
	 * ������Ҫ��֪ʶ�ڻ�ȡ�����ݵ�ʱ��  Ϊ���̰߳�ȫ  
	 * ��Ҫ��attributes���м���   ������������ȴ�������ķ�������
	 * ������  ��ζ���߳�ռ�е���ʱ��Խ�� �����Ի��ܵ�Ӱ��
	 * @param userName
	 * @param regexp
	 * @return
	 */
	@Deprecated
	public synchronized boolean userLocationMathches(String userName,String regexp){
		String location=attributes.get(userName);
		if(location==null)
			return false;
		else
			return Pattern.matches(regexp, location);
	}
	
	public boolean userLocationMathches2(String userName,String regexp){
		String location=null;
		/**
		 * �Գ�����з���  ���ǿ��Կ�����  ��ʵ��Ҫ�����ľ�ֻ����һ����
		 * �������ַ�ʽ  ��ô�߳�ռ������ʱ��ͻ���ļ���
		 */
		synchronized (attributes) {
			location=attributes.get(userName);
		}
		if(location==null)
			return false;
		else
			return Pattern.matches(regexp, location);
	}
	
	//-------------------start--------------------------
	/**
	 * ������Ǹ������Ƕ�����set���ϵ���ɾ
	 * ʹ�õ���������  ��������������û���κι�ϵ  ����
	 * ����ͨ������������ϸ�� ��չ������
	 */
	public synchronized void addUser(String userName){
		users.add(userName);
	}
	public synchronized void removeUser(String userName){
		users.remove(userName);
	}
	public synchronized void addQuery(String query){
		queries.add(query);
	}
	public synchronized void removeQuery(String query){
		queries.remove(query);
	}
	//---------------------------------------
	/**
	 * ϸ����������  �����������ϵ����ֿ�
	 * ���������������Բ����Ľ���  ����ֻ�ܴ��е�ִ��
	 * ����һ�ַ�������ֱ��ί�и�һ���̰߳�ȫ��Set,��������ʾ��ʹ��
	 * �� (��Ϊÿ��set�������Լ�����)���̰߳�ȫ��setͬʱÿһ��������ִ��ͬ��
	 * 
	 */
	public void addUser2(String userName){
		synchronized (USER_LOCK) {
			users.add(userName);
		}
		
	}
	public void removeUser2(String userName){
		synchronized (USER_LOCK) {
			users.remove(userName);
		}
	}
	public void addQuery2(String query){
		synchronized (QUERY_LOCK) {
			queries.add(query);
		}
	}
	public void removeQuery2(String query){
		synchronized (QUERY_LOCK) {
			queries.remove(query);
		}
		
	}
	
}

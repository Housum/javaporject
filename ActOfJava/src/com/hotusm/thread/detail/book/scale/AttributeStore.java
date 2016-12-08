package com.hotusm.thread.detail.book.scale;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 1.持有锁的时间越长(因为这意味着其他线程堵塞的时间越长) 那么可伸缩性就越差  
 * 2.锁的粒度越粗(这就意味着越多的线程争夺锁) 那么伸缩性就越差
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
	 * 我们需要的知识在获取的数据的时候  为了线程安全  
	 * 需要对attributes进行加锁   但是现在我们却对整个的方法加了
	 * 内置锁  意味着线程占有的锁时间越长 伸缩性会受到影响
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
		 * 对程序进行分析  我们可以看出来  其实需要加锁的就只有这一部分
		 * 采用这种方式  那么线程占用锁的时间就会大大的减少
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
	 * 下面的是个方法是对两个set集合的增删
	 * 使用的是内置锁  但是这两个集合没有任何关系  所以
	 * 可以通过将锁的粒度细化 扩展伸缩性
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
	 * 细化锁的粒度  将这两个集合的锁分开
	 * 这样两个操作可以并发的进行  上面只能串行的执行
	 * 还有一种方案就是直接委托给一个线程安全的Set,而不是显示的使用
	 * 锁 (以为每个set都会有自己的锁)，线程安全的set同时每一个操作上执行同步
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

package com.hotusm.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

/**
 * 
 * @author Hotusm
 * 1.集合测试
 */
public class CollectionTest {
	
	
	@Test
	public void set(){
		Set<String> set=new HashSet<String>();
		boolean b1 = set.add("a");
		boolean b2 = set.add("a");
		System.out.println(b1+" "+b2);
	}
	//--------------Arrays.asList的用法----------------
	@Test
	public void testArrays(){

		/**
		 * @see AbstractList<E>
		 * Arrays.asList返回的是Arrays.ArrayList并不是我们
		 * 所使用的那个ArrayList
		 */
		//虽然这里是一个容器,但是在我们操作下面的时候
		//会出现UnsupportedOperationException
		//因为底层是作为数字来进行处理的  如果这个时候我们操作一些
		//容器的CUD时候会出现上面的异常
		List<Integer> nums = Arrays.asList(1,2,3);
		nums.add(4);
		//output:java.lang.UnsupportedOperationException
		
	}
	
	//---------------测试ArrayList和LinkedList的性能------------
	@Test
	public void testLinkedList(){
		LinkedList<Integer> linkedList=new LinkedList<Integer>();
		Random r=new Random();
		long start=System.currentTimeMillis();
		for(int i=0;i<10000000;i++){
			linkedList.add(r.nextInt(100000));
		}
		long end=System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	@Test
	public void testArrayList(){
		
		ArrayList<Integer> aList=new ArrayList<Integer>();
		Random r=new Random();
		long start=System.currentTimeMillis();
		for(int i=0;i<10000000;i++){
			aList.add(r.nextInt(100000));
		}
		long end=System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	//-------------------Collections&Arrays---------------
	//这里发现一个非常有趣的事,发现静态内部类是不能使用Collections.sort()
	
	public static class Apple{
		private Integer size;
		public Apple(Integer size){
			this.size=size;
		}
		@Override
		public String toString() {
			return "Apple [size=" + size + "]";
		}
		
	}

	//the container's producer
	public List<Integer> produce(){
		List<Integer> lists=new ArrayList<Integer>();
		lists.add(1);
		lists.add(3);
		lists.add(2);
		lists.add(4);
		lists.add(5);
		lists.add(6);
		lists.add(7);
		
		return lists;
	}
	
	/*
	 * Collectoins.sort()对容器持有的对象进行按照自然排序进行
	 * 重新排序
	 * */
	@Test
	public void CollectionsSort(){
		List<Integer> lists=produce();
		
		List<Integer> subList = lists.subList(0, 3);
		System.out.println(subList);
		//对容器内的对象按照自然排序
		Collections.sort(subList);
		System.out.println(subList);
		/*output :
				  [1, 3, 2]
				  [1, 2, 3]
		 * */

	}
	
	/*
	 *Collections.shuffle()
	 *对集合持有的对象进行重新的重洗
	 */
	@Test
	public void CollectionsSort1(){
		
		List<Integer> lists=produce();
		Collections.shuffle(lists);
		System.out.println(lists);
		/*
		 *   output:
		 *   [7, 3, 5, 4, 6, 2, 1]
		 *
		*/
		
	}
	//------------------ListIterator-------------------
	/**
	 * ListIterator是Iterator的子类，和父类不同点:
	 * 1.父类只能单向的移动
	 * 2.子类可以双向移动,但是只能在List类的访问
	 */
	@Test
	public void listIterator(){
		List<Integer> lists=produce();
		ListIterator<Integer> listIterator = lists.listIterator();
		Integer previous = listIterator.next();
		System.out.println(previous);
		
		listIterator.add(1);
		listIterator.hasNext();
		listIterator.hasPrevious();
		listIterator.next();
		listIterator.nextIndex();
		listIterator.previous();
		listIterator.previousIndex();
		
	}
	//----------------LinkedList------------------------
	/*
	 * 采用链表的方式来进行储存  对于数据的插入操作非常性能比较高
	 * 并且还增加了栈  队列  双端队列的方法
	 * 
	 * */
	@Test
	public void linkedList(){
		LinkedList<Integer> lists=new LinkedList<Integer>();
		lists.add(1);
		lists.add(3);
		lists.add(2);
		lists.add(4);
		lists.add(5);
		lists.add(6);
		lists.add(7);
		
		//增加一个持有对象
		lists.addFirst(0);
		lists.addLast(8);
		lists.add(9, 9);
		
		System.out.println(lists);
		
		/*移除第一个参数  如果表头为空的时候,出抛出异常(这三个都是移除并返回
		      第一个参数)
		**/
		lists.remove();
		lists.removeFirst();
		//和前面两个不同的是  表头不会抛出异常  只会返回null
		lists.poll();
		
		/*
		 *获取第一个参数
		 * 
		 */
		lists.getFirst();
		lists.element();
		//同理  这个方法不会抛出异常
		lists.peek();
		
		/*
		 *移除并返回最后一个参数
		 * 
		 */
		lists.removeLast();
		
		
		
	}
	//-----------------Set---------------------
	/*
	 *持有对象不能重复
	 */
	//------------------Map--------------------
	/*
	 *采用键值对的形式保存持有对象
	 */
	//------------------Queue-------------------
	/*
	 * 采用对垒的形式保存持有对象FIFO
	 * 
	 * */
	@Test
	public void queue(){
		//LinkedList继承了Queue
		Queue<Integer> queue=new LinkedList<Integer>();
		queue.add(1);
		queue.add(3);
		queue.add(2);
		queue.add(4);
		queue.add(5);
		queue.add(6);
		queue.add(7);
		
		System.out.println(queue);
		
		//在允许的情况下  将持有对象插入到队列的最前面
		queue.offer(8);
		System.out.println(queue);
	}
	//---------------PriorityQueue--------------------
	/*
	 * 是队列最典型的例子   先进先出声明的是下一个元素是等待最久的元素
	 * 1.但是  优先级队列表示的是 下一个弹出的元素是最需要的元素 (但是对于一般的
	 * 元素还是先进先出)
	 * 2.对于offer插入的对象时  这个对象会在队列中被排序  默认是按照自然顺序来进行排序
	 * 
	 * */
	
	
	
}



//----------------根据LinkedList的特性编写栈------------------
/*
 * 使用LinkedList的特性
 * 设计栈
 * */
class Stack<E>{
	
	private final LinkedList<E> linkedList=new LinkedList<E>();
	public void push(E e){
		linkedList.push(e);
	}
	//弹出栈顶元素
	public E pop(){
		return linkedList.removeFirst();
	}
	//返回栈顶元素,但是不会弹出,元素还是存在栈中
	public E peek(){
		return linkedList.getFirst();
	}
	public boolean empty(){
		return linkedList.size()==0;
	}
	@Override
	public String toString() {
		return linkedList.toString();
	}
	
	public static void main(String[] args) {
		Stack<String> stack=new Stack<String>();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		String str = stack.peek();
		System.out.println(str);
		System.out.println(stack);

	}
}
/**
 * 
 * @author Hotusm
 * 使用TreeSet计算输入单词的原因字母以及出现的数量
 * Set和List最大的区别就是List能够允许重复,但是Set不允许
 */
class UniqueWord{
	private static final Set<Character> sets=new TreeSet<Character>();
	//存放出现的那些元音字母(区分大小写)
	private final Set<Character> inputs=new TreeSet<Character>();
	//元音字母出现的次数
	private int count=0;
	//存放每一个元音字母出现的次数(不区分大小写)
	private Map<Character,Integer> map=new HashMap<Character, Integer>();
	static{
		Collections.addAll(sets, 'A','E','I','O','U');
	}
	
	public void count(String word){
		String Upword=word.toUpperCase();
		/*
		 * 计算逻辑
		 * */
		for(int i=0;i<Upword.length();i++){
			char w = Upword.charAt(i);
			if(sets.contains(w)){
				count++;
				inputs.add(word.charAt(i));
				
				if(map.keySet().contains(w)){
					int count=map.get(w);
					map.put(w, count+1);
				}else{
					map.put(w, 1);
				};
			}
		}
		
	}
	public int getCount() {
		return count;
	}

	public Map<Character, Integer> getMap() {
		return map;
	}

	public Set<Character> getInputs() {
		return inputs;
	}

	public static void main(String[] args) {
		UniqueWord uWord=new UniqueWord();
		uWord.count("abcdefsdgAAdsabgegoooooooooohdgf");
		System.out.println(uWord.getMap());
		int hashCode = uWord.hashCode();
		System.out.println(hashCode);
	}
}
//---------Queue----------
/**
 * 
 * @author Hotusm
 * 使用队列实现简单的生产者和消费者模式
 */
class QueueTest{
	
	public static void main(String[] args) {
		Storage s=new Storage();
		s.produce(new Command("test"));
//		Command cmd=new Command("-start");
//		s.produce(cmd);
//		System.out.println(s.custom().operation());
		new Thread(new Producer(s)).start();
		new Thread(new Customer(s)).start();
	}
}
/*生产者
 * 
 * */
class Producer implements Runnable{
	private final Storage storage;
	
	public Producer(Storage storage) {
		this.storage=storage;
	}
	@Override
	public void run() {
		int i=0;
		while(true){
			
//			if(storage.size()>1000){
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
			System.out.println("生产者-"+storage.size());
			Command cmd=new Command("生产"+i++);
			storage.produce(cmd);

		}
	}
	
}
/*
 * 消费者
 * */
class Customer implements Runnable{

	private final Storage storage;
	
	public Customer(Storage storag) {
		this.storage=storag;
	}
	@Override
	public void run() {
		
		while(true){
			Command custom = storage.custom();
			if(custom!=null){
				System.out.println("消费者-"+storage.size());
				custom.operation();
			}
			
		}
	}
	
}
/*测试使用类*/
class Command{
	private String cmd;
	
	public Command(String cmd) {
		System.out.println("生产者调用");
		this.cmd=cmd;
	}
	public String operation(){
		System.out.println("消费者调用");
		return cmd;
	}
}
class Storage{
	private final Queue<Command> queue;
	public Storage() {
		queue=new LinkedList<Command>();
	}
	public void produce(Command cmd){
		queue.offer(cmd);
	}
	public Command custom(){
		return queue.poll();
	}
	public int size(){
		return queue.size();
	}
}
//-------PriorityQueue--------- 
/*
 * 1.PriorityQueue是根据优先级排列队列的,
 * 最简单的示例就是:Integer,Character,String等等内置的
 * 2.对于那些自定义的类需要包含额外的功能或者是实现自己的
 * Comparator
 * */
class PriorityQueueTest{
	
	public static void main(String[] args) {
		PriorityQueue<String> strs=new PriorityQueue<String>();
		String strings="HOW ARE YOU";
		Collections.addAll(strs, strings.split(""));
		//根据字母的自然顺序排列队列,注意:优先级最高的在最前面,即最先出队列
		while(strs.size()>0){
			System.out.print(strs.poll()+":");
		}
		//output=> : :A:E:H:O:O:R:U:W:Y:
	}
	

}



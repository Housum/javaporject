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
 * 1.���ϲ���
 */
public class CollectionTest {
	
	
	@Test
	public void set(){
		Set<String> set=new HashSet<String>();
		boolean b1 = set.add("a");
		boolean b2 = set.add("a");
		System.out.println(b1+" "+b2);
	}
	//--------------Arrays.asList���÷�----------------
	@Test
	public void testArrays(){

		/**
		 * @see AbstractList<E>
		 * Arrays.asList���ص���Arrays.ArrayList����������
		 * ��ʹ�õ��Ǹ�ArrayList
		 */
		//��Ȼ������һ������,���������ǲ��������ʱ��
		//�����UnsupportedOperationException
		//��Ϊ�ײ�����Ϊ���������д����  ������ʱ�����ǲ���һЩ
		//������CUDʱ������������쳣
		List<Integer> nums = Arrays.asList(1,2,3);
		nums.add(4);
		//output:java.lang.UnsupportedOperationException
		
	}
	
	//---------------����ArrayList��LinkedList������------------
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
	//���﷢��һ���ǳ���Ȥ����,���־�̬�ڲ����ǲ���ʹ��Collections.sort()
	
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
	 * Collectoins.sort()���������еĶ�����а�����Ȼ�������
	 * ��������
	 * */
	@Test
	public void CollectionsSort(){
		List<Integer> lists=produce();
		
		List<Integer> subList = lists.subList(0, 3);
		System.out.println(subList);
		//�������ڵĶ�������Ȼ����
		Collections.sort(subList);
		System.out.println(subList);
		/*output :
				  [1, 3, 2]
				  [1, 2, 3]
		 * */

	}
	
	/*
	 *Collections.shuffle()
	 *�Լ��ϳ��еĶ���������µ���ϴ
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
	 * ListIterator��Iterator�����࣬�͸��಻ͬ��:
	 * 1.����ֻ�ܵ�����ƶ�
	 * 2.�������˫���ƶ�,����ֻ����List��ķ���
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
	 * ��������ķ�ʽ�����д���  �������ݵĲ�������ǳ����ܱȽϸ�
	 * ���һ�������ջ  ����  ˫�˶��еķ���
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
		
		//����һ�����ж���
		lists.addFirst(0);
		lists.addLast(8);
		lists.add(9, 9);
		
		System.out.println(lists);
		
		/*�Ƴ���һ������  �����ͷΪ�յ�ʱ��,���׳��쳣(�����������Ƴ�������
		      ��һ������)
		**/
		lists.remove();
		lists.removeFirst();
		//��ǰ��������ͬ����  ��ͷ�����׳��쳣  ֻ�᷵��null
		lists.poll();
		
		/*
		 *��ȡ��һ������
		 * 
		 */
		lists.getFirst();
		lists.element();
		//ͬ��  ������������׳��쳣
		lists.peek();
		
		/*
		 *�Ƴ����������һ������
		 * 
		 */
		lists.removeLast();
		
		
		
	}
	//-----------------Set---------------------
	/*
	 *���ж������ظ�
	 */
	//------------------Map--------------------
	/*
	 *���ü�ֵ�Ե���ʽ������ж���
	 */
	//------------------Queue-------------------
	/*
	 * ���ö��ݵ���ʽ������ж���FIFO
	 * 
	 * */
	@Test
	public void queue(){
		//LinkedList�̳���Queue
		Queue<Integer> queue=new LinkedList<Integer>();
		queue.add(1);
		queue.add(3);
		queue.add(2);
		queue.add(4);
		queue.add(5);
		queue.add(6);
		queue.add(7);
		
		System.out.println(queue);
		
		//������������  �����ж�����뵽���е���ǰ��
		queue.offer(8);
		System.out.println(queue);
	}
	//---------------PriorityQueue--------------------
	/*
	 * �Ƕ�������͵�����   �Ƚ��ȳ�����������һ��Ԫ���ǵȴ���õ�Ԫ��
	 * 1.����  ���ȼ����б�ʾ���� ��һ��������Ԫ��������Ҫ��Ԫ�� (���Ƕ���һ���
	 * Ԫ�ػ����Ƚ��ȳ�)
	 * 2.����offer����Ķ���ʱ  ���������ڶ����б�����  Ĭ���ǰ�����Ȼ˳������������
	 * 
	 * */
	
	
	
}



//----------------����LinkedList�����Ա�дջ------------------
/*
 * ʹ��LinkedList������
 * ���ջ
 * */
class Stack<E>{
	
	private final LinkedList<E> linkedList=new LinkedList<E>();
	public void push(E e){
		linkedList.push(e);
	}
	//����ջ��Ԫ��
	public E pop(){
		return linkedList.removeFirst();
	}
	//����ջ��Ԫ��,���ǲ��ᵯ��,Ԫ�ػ��Ǵ���ջ��
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
 * ʹ��TreeSet�������뵥�ʵ�ԭ����ĸ�Լ����ֵ�����
 * Set��List�����������List�ܹ������ظ�,����Set������
 */
class UniqueWord{
	private static final Set<Character> sets=new TreeSet<Character>();
	//��ų��ֵ���ЩԪ����ĸ(���ִ�Сд)
	private final Set<Character> inputs=new TreeSet<Character>();
	//Ԫ����ĸ���ֵĴ���
	private int count=0;
	//���ÿһ��Ԫ����ĸ���ֵĴ���(�����ִ�Сд)
	private Map<Character,Integer> map=new HashMap<Character, Integer>();
	static{
		Collections.addAll(sets, 'A','E','I','O','U');
	}
	
	public void count(String word){
		String Upword=word.toUpperCase();
		/*
		 * �����߼�
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
 * ʹ�ö���ʵ�ּ򵥵������ߺ�������ģʽ
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
/*������
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
			System.out.println("������-"+storage.size());
			Command cmd=new Command("����"+i++);
			storage.produce(cmd);

		}
	}
	
}
/*
 * ������
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
				System.out.println("������-"+storage.size());
				custom.operation();
			}
			
		}
	}
	
}
/*����ʹ����*/
class Command{
	private String cmd;
	
	public Command(String cmd) {
		System.out.println("�����ߵ���");
		this.cmd=cmd;
	}
	public String operation(){
		System.out.println("�����ߵ���");
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
 * 1.PriorityQueue�Ǹ������ȼ����ж��е�,
 * ��򵥵�ʾ������:Integer,Character,String�ȵ����õ�
 * 2.������Щ�Զ��������Ҫ��������Ĺ��ܻ�����ʵ���Լ���
 * Comparator
 * */
class PriorityQueueTest{
	
	public static void main(String[] args) {
		PriorityQueue<String> strs=new PriorityQueue<String>();
		String strings="HOW ARE YOU";
		Collections.addAll(strs, strings.split(""));
		//������ĸ����Ȼ˳�����ж���,ע��:���ȼ���ߵ�����ǰ��,�����ȳ�����
		while(strs.size()>0){
			System.out.print(strs.poll()+":");
		}
		//output=> : :A:E:H:O:O:R:U:W:Y:
	}
	

}



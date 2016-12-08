package com.hotusm.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Test {
	
	public static final String NUMBERS="0123456789";

		
	public void number(String str){
		int sum=0;
		for(int i=0;i<str.length();i++){
			if(NUMBERS.contains(str.subSequence(i, i+1))){
				sum++;
			};
		}
		System.out.println(sum);
	}
	public boolean leapYear(int age){
		if(age%400==0){return true;}
		if(age%100!=0&&age%4==0){return true;}
		return false;
	}
	
	
	public static int count(int start,int end){
		int sum=0;
		if(start>=end){return sum;}
		
		for(int i=start;i<=end;i++){
			if(i%4==3&i%8==5&i%12==7){
				System.out.println(i);
				sum++;
			}
		}
		
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		//System.out.println(1/2);
		//new Thread(() -> System.out.println("这是一个java8的小例子,可以使用lambda表达式")).start();
//		Map<String,Object> maps=new HashMap<String,Object>();
//		maps.put("1", "123");
//		//被修饰  不能进行修改
//		Map<String, Object> unMap = Collections.unmodifiableMap(maps);
//		unMap.put("2", "1111");
//		
//		int i=6/5;
////		System.out.println(i);
////		Test2 t2 = new Test.Test2();
//		String url="http://www.baidu.com/%E6%9C%A8";
//		try {
//			System.out.println(URLDecoder.decode(url, "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		boolean flag = new Test().testFinally();
//		System.out.println(flag);
//		double random = Math.random();
		
		
//		Calendar calendar=Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
//		int week = calendar.get(Calendar.DAY_OF_WEEK);
//		//System.out.println(week);
//		
//		List<String> list=new ArrayList<String>();
//		Map<String,Integer> maps=new HashMap<>();
//		
//		Integer integer = maps.get("1");
//		System.out.println(integer);
//		
//		List<Node> nodes=new LinkedList<>();
//		nodes.add(new Node(1));
//		nodes.add(new Node(3));
//		nodes.add(new Node(6));
//		nodes.add(new Node(2));
//		
//		Collections.sort(nodes, new Comparator<Node>(){
//
//			@Override
//			public int compare(Node o1, Node o2) {
//					
//				if(o1.getNode()>o2.getNode()){
//					return 1;
//				}else{
//					return -1;
//				}
//			
//			}});
//		
//		System.out.println(nodes);
//		
//		Node temp=new Node(1);
//		Method[] methods = temp.getClass().getMethods();
//		temp.getNode();
//		
//		
		
		//((Test)null).count(0, 100);
//		
//		List<String> list=new ArrayList<String>();
//		list.add("111");
//		list.add("222");
//		list.add("333");
//		new Test().clear(list);
//		System.out.println(list);
//		
//		ArrayList<String> classNames=new ArrayList<String>();
//		File file=new File("D://t.txt");
//		FileReader fileReader=new FileReader(file);
//		BufferedReader bufferedReader=new BufferedReader(fileReader);
//		
//		String readContent=null;
//		
//		while((readContent=bufferedReader.readLine())!=null){
//			
//			classNames.add(readContent.trim());
//		}
//		
//		for(String str:classNames){
//			
//			System.out.println(str);
//		}
//		
//		fileReader.close();
//		bufferedReader.close();
		
//		long start = System.currentTimeMillis();
//		Map<Integer,Object> maps=new HashMap<Integer,Object>(/*2048*/);
//		for(int i=0;i<30000;i++){
//			maps.put(i, i+"");
//		}
//		long end=System.currentTimeMillis()-start;
//		System.out.println(end);
//		maps.get(254);
		
		long maxValue = Long.MAX_VALUE;
		
		System.out.println(maxValue);
		
	}
	
	public void clear(List<String> list){
		list.clear();
	}
	
	public void swap(Object one,Object other){
		
		Object temp=one;
		one=other;
		other=temp;
	}
	
	private static class Node{
		
		private Integer index;

		public Node(Integer index) {
			super();
			this.index = index;
		}
		
		public Integer getNode() {

			return this.index;
		}
	
		@Override
		public String toString() {
			return this.index+"";
		}
	}
	public void tt(){
		System.out.println(new Random().nextFloat());
	}
	
	//这个程序将会返回false
	public boolean testFinally(){
		try {
			
			return true;
		}finally{
			System.out.println("finally");
			return false;
		}
		
	}
	
	private static class Operation{
		
		private long start;
		private long result;
		
		private int step;
		
		public void result(){
			
			
		}
		
	}
	
}

package com.hotusm.java8.lambda;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.hotusm.thread.detail.book.threadclose.Person;

public class JDKProvideFunction {

	public static void main(String[] args) {
		
		Predicate<String> predicate=(s)->s.length()>0;
		
		System.out.println(predicate.test("foo"));
		
		Predicate<Boolean> predicate2=Objects::nonNull;
		
		System.out.println(predicate2.test(null));
		
		//提供了一个抽象的接口  将前面的类型转化为后面的类型
		Function<Integer,String> func=(a)->String.valueOf(a);
		
		//返回某种类型
		Supplier<Person> supplier=Person::new;
		Person person = supplier.get();
		System.out.println(person);
		
		//Stream 
		//Filter 过滤
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
		
		stringCollection
		.stream()
		//排序  如果没有指定Comparator的话
		.sorted()
		//这里是指定了Comparator实现的
		.sorted((a,b)->a.compareTo(b))
		.map(String::toUpperCase)
		.filter((s)->s.startsWith("a"))
		.forEach((s)->{
			System.out.println("content:"+s);
		});
		
		//Any Match 
		boolean anyMatch = stringCollection
		.stream()
		.anyMatch((s)->s.startsWith("a"));
		System.out.println(anyMatch);
		
		//All Match
		boolean allMatch = stringCollection
				.stream()
				.allMatch((s)->s.startsWith("a"));
		
		
		//None Match
		boolean noneMatch = stringCollection
				.stream()
				.noneMatch((s)->s.startsWith("z"));
		
		//Count
		long count = stringCollection
				.stream()
				.filter((s)->s.contains("a"))
				.count();
		
		//并行的Stream

		List<String> values=new ArrayList<String>();
		
//		for(int i=0;i<1000000;i++){
//			values.add(UUID.randomUUID().toString());
//		}
//		
//		long t0=System.nanoTime();
//		long count2 = values
//		.stream()
//		.sorted()
//		.count();
//		long t1=System.nanoTime();
//		System.out.println(TimeUnit.NANOSECONDS.toMillis(t1-t0));
//		
//		long t2=System.nanoTime();
//		long count3 = values
//		//并行的stream
//		.parallelStream()
//		.sorted()
//		.count();
//		long t3=System.nanoTime();
//		System.out.println(TimeUnit.NANOSECONDS.toMillis(t3-t2));
		
		Map<Integer,String> map=new HashMap<Integer,String>();
		
		//如果不存在的话 那么就存入
		map.putIfAbsent(1,"H");
		map.putIfAbsent(1,"A");
		
		//和上面的用法刚好相反
		map.getOrDefault(2, "none");
		//Map 的foreach用法
		map.forEach((key,value)->{
			System.out.println("key:"+key+"  value:"+value);
		});
		
		System.out.println(map);
		
		//只有在键值对都符合的情况下 才能够remove操作
		map.remove(1, "HH");
		
		//对Map的元素做合并操作
		map.merge(1, "ABC", (value,newValue)->{
			return value+newValue;
		});
		
		System.out.println(map.get(1));
	
		
		//Clock 时钟
		Clock clock = Clock.systemDefaultZone();
		System.out.println(clock);
		System.out.println(TimeUnit.MILLISECONDS.toDays(clock.millis()));
		
		Instant instant = clock.instant();
		Date date = Date.from(instant);
		System.out.println(date);
	}
}

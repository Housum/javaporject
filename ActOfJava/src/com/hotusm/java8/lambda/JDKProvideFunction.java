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
		
		//�ṩ��һ������Ľӿ�  ��ǰ�������ת��Ϊ���������
		Function<Integer,String> func=(a)->String.valueOf(a);
		
		//����ĳ������
		Supplier<Person> supplier=Person::new;
		Person person = supplier.get();
		System.out.println(person);
		
		//Stream 
		//Filter ����
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
		//����  ���û��ָ��Comparator�Ļ�
		.sorted()
		//������ָ����Comparatorʵ�ֵ�
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
		
		//���е�Stream

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
//		//���е�stream
//		.parallelStream()
//		.sorted()
//		.count();
//		long t3=System.nanoTime();
//		System.out.println(TimeUnit.NANOSECONDS.toMillis(t3-t2));
		
		Map<Integer,String> map=new HashMap<Integer,String>();
		
		//��������ڵĻ� ��ô�ʹ���
		map.putIfAbsent(1,"H");
		map.putIfAbsent(1,"A");
		
		//��������÷��պ��෴
		map.getOrDefault(2, "none");
		//Map ��foreach�÷�
		map.forEach((key,value)->{
			System.out.println("key:"+key+"  value:"+value);
		});
		
		System.out.println(map);
		
		//ֻ���ڼ�ֵ�Զ����ϵ������ ���ܹ�remove����
		map.remove(1, "HH");
		
		//��Map��Ԫ�����ϲ�����
		map.merge(1, "ABC", (value,newValue)->{
			return value+newValue;
		});
		
		System.out.println(map.get(1));
	
		
		//Clock ʱ��
		Clock clock = Clock.systemDefaultZone();
		System.out.println(clock);
		System.out.println(TimeUnit.MILLISECONDS.toDays(clock.millis()));
		
		Instant instant = clock.instant();
		Date date = Date.from(instant);
		System.out.println(date);
	}
}

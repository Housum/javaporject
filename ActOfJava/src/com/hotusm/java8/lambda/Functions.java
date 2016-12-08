package com.hotusm.java8.lambda;

public class Functions {

	public static void main(String[] args) {

		Converter<String,Integer> converter=(from)->Integer.valueOf(from);
		System.out.println(converter.convert("3"));
		
		//方法与构造函数引用
		//静态方法
		Converter<String,Integer> converter1=Integer::valueOf;
		System.out.println(converter1.convert("3"));
		
		//对象的方法
		String something="1a23a";
		Converter<String,Integer> converter2=something::lastIndexOf;
		
		System.out.println(converter2.convert("a"));
		
		//对于多个参数 JVM能够进行类型推导  这里是以为我们的TestMultiple只有一个
		TestMultiple mul=something::indexOf;
		
		//构造函数使用::
		PersonFactory<Person> factory=Person::new;
		
		Person person = factory.create("hotusm", "lu");
		System.out.println(person);
		
	}
	
	
	private static class Person{
		
		private String firstName;
		private String lastName;
		public Person() {
			super();
		}
		public Person(String firstName, String lastName) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
		}
		@Override
		public String toString() {
			return "Person [firstName=" + firstName + ", lastName=" + lastName + "]";
		}
		
	}
	
	private static interface PersonFactory<P extends Person>{
		
		public P create(String firstName,String lastName);
	}
}

interface TestMultiple{

	void indexOf(int i,int j/* 如果这里没有注释 那么就会报错 不能够进行推导了,int k*/);
}



/**
 *
 *  每一个lambda表达式都对应一个<strong>类型</strong>，通常是接口类型。而“函数式接口”是指仅仅只包含一个抽象方法的接口，每一个该类型的lambda表达式都会被匹配到这个抽象方法。因为 默认方法 不算抽象方法，所以你也可以给你的函数式接口添加默认方法。
 *	我们可以将lambda表达式当作任意只包含一个抽象方法的接口类型，确保你的接口一定达到这个要求，你只需要给你的接口添加 @FunctionalInterface 注解，编译器如果发现你标注了这个注解的接口有多于一个抽象方法的时候会报错的。
 *
 */
//需要注意如果@FunctionalInterface如果没有指定，上面的代码也是对的。
@FunctionalInterface
interface Converter<F,T>{
	
	T convert(F from);
}




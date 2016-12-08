package com.hotusm.java8.lambda;

public class Functions {

	public static void main(String[] args) {

		Converter<String,Integer> converter=(from)->Integer.valueOf(from);
		System.out.println(converter.convert("3"));
		
		//�����빹�캯������
		//��̬����
		Converter<String,Integer> converter1=Integer::valueOf;
		System.out.println(converter1.convert("3"));
		
		//����ķ���
		String something="1a23a";
		Converter<String,Integer> converter2=something::lastIndexOf;
		
		System.out.println(converter2.convert("a"));
		
		//���ڶ������ JVM�ܹ����������Ƶ�  ��������Ϊ���ǵ�TestMultipleֻ��һ��
		TestMultiple mul=something::indexOf;
		
		//���캯��ʹ��::
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

	void indexOf(int i,int j/* �������û��ע�� ��ô�ͻᱨ�� ���ܹ������Ƶ���,int k*/);
}



/**
 *
 *  ÿһ��lambda���ʽ����Ӧһ��<strong>����</strong>��ͨ���ǽӿ����͡���������ʽ�ӿڡ���ָ����ֻ����һ�����󷽷��Ľӿڣ�ÿһ�������͵�lambda���ʽ���ᱻƥ�䵽������󷽷�����Ϊ Ĭ�Ϸ��� ������󷽷���������Ҳ���Ը���ĺ���ʽ�ӿ����Ĭ�Ϸ�����
 *	���ǿ��Խ�lambda���ʽ��������ֻ����һ�����󷽷��Ľӿ����ͣ�ȷ����Ľӿ�һ���ﵽ���Ҫ����ֻ��Ҫ����Ľӿ���� @FunctionalInterface ע�⣬����������������ע�����ע��Ľӿ��ж���һ�����󷽷���ʱ��ᱨ��ġ�
 *
 */
//��Ҫע�����@FunctionalInterface���û��ָ��������Ĵ���Ҳ�ǶԵġ�
@FunctionalInterface
interface Converter<F,T>{
	
	T convert(F from);
}




package com.hotusm.regular;

import java.io.File;

import org.junit.Test;

import com.hotusm.annotation.Date;

/**
 *1.  ?��ʾ������ 0������1��
 *2.  \d��ʾ��������(-?\\d��ʾ����ǰ�� �п�����һ������)
 *3.  +����һ�����߶��
 *4.  ����Ҫ����\��ʱ����Ҫʹ��\����ת��   �������Ҫ����һ��\dӦ��:\\d
 *    ���Ƕ���������û���������Ҫ  ʹ��\\������ת�� ��:+����������������ĺ���
 *    ���� ����ʹ��\\+������ת��   ��ô���ھ�����ͨ��+
 *5.  |������ߵ���˼  [abc]��a|b|c����ͬ����˼
 *6.  ()�������Ž����ʽ�����Ч��  glad��good ����������ʾ  'g(la|dd)d'
 *7.  \W�ǵ����ַ�   		\w����һ�������ַ�[a-zA-Z0-9]
 *8.  ���ֱ����һ���ַ�����:a���ǵ���a�ַ�
 *9.  [x-y] ��ʾһ������   :[A-Z]��д��ĸ  [a-zA-Z]
 *10.  . ����������ַ�(�����Ҫʹ����ͨ����ʽ   ��������:\\.)
 *11.  && ���ҵ���˼[a-z&&[hij]]��ʾ����a��z�κ��ַ����Һ�hij����һ��
 *12.  ^ ��ʾһ�еĿ�ʼ   ���磺/^A/��ƥ��"an A,"�еġ�A��,��ƥ��"An A."����ǰ��ġ�A��.
 *13. \s  �հ׷�  (�ո�,tab,����,��ҳ�ͻس�)
 *14. \S  �ǿհ׷�[^\s]
 *15  *   ��λ��߶��ƥ��ǰ����ַ����߱��ʽ��zo*����ƥ��  z(o����0��) zo(o����һ��) zoo(o���ֶ���)
 *		      �ȼ���{0,}
 *16.  X{n}ǡ��n��X. X{n,}����n��X.  X{n,m}����n�����m��X
 *17.  (?i)������Сд����  (?-i)�رմ�Сд����
 */
public class First {
	
	@Test
	public void first(){
		System.out.println("123".matches("-?\\d+"));
		System.out.println("-123".matches("-?\\d+"));
		System.out.println("+123".matches("-?\\d+"));
		//ʹ��()������߼�������  
		System.out.println("+123".matches("(-|\\+)?\\d+"));
		//a�����ʾ��������ַ�
		System.out.println("abcd".matches("a\\w+"));
		System.out.println("1".matches("\\W"));
		//��д��ĸ��ͷ .��β
		System.out.println("Asgt5��".matches("[A-Z].+\\."));
		//����
		System.out.println("295113757@163.com".matches(".+@\\w+\\.\\w+"));
		
	}
	
	@Date(value=20160601)
	@Test
	public void second(){
		//(?i)�������Դ�Сд
		System.out.println("ABC".matches("(?i)aBc"));
		
		
	}
	
	@Test
	public void test(){
		System.out.println("/em/orderinfo/".matches("^/em/orderinfo/"));
	}
}

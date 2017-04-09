package com.hotusm.bit;

import java.util.Random;

import org.junit.Test;

/**
 * 1.��Ϊʹ�õĺ��� ���Բ�֪�� λ�������ȼ�����͵�
 * @link http://baike.baidu.com/link?url=mXCDZyM2jhEvmBuUTTA6ZI45pB7mKqBFh3_MWZoz1b-7zcmGs_-RLgalD9fyEZd9QwN4yH9Zn-M8DWIEgvgf5q
 * λ������ʵ���ǽ�����������������  ���ڶ�����λ��������ǰ����0
 * ����:6������λ110  11�Ķ�����1011 ��ô�������������ʱ��  ��Ϊ0110 ��1011֮�������
 * ����and����:
 * 		0110
 * 		1011
 *���          0010
 *���Ƕ�������ͬ��λ�����в�(&)����
 *
 */
public class BitOperation {

	
	@Test
	public void orTest(){
		int temp=new Random().nextInt(100);
		//��Ϊ1�Ķ����ƾ���1ǰ�涼Ϊ0  ��������ʵ���������Ϊ���1(��Ϊ����һ�����������
		//��0����1 �����㶼Ϊ1)  Ȼ���ټ�һ  ��ô���������ζ�Ϊż���� 
		System.out.println((temp|1)-1);
	}
	@Test
	public void andTest(){
		int temp=new Random().nextInt(100);
		//�ͻ�������� 1�Ķ�����ǰ�涼��0  ����&1�Ľ����ȡ����һ�����Ķ��������һλ
		System.out.println(temp&1);
	}
	
	/**
	 * ������  
	 *  ��ʾ���Ƕ�����ͬ������  ��ͬ��Ϊ1  ����Ϊ0
	 *  �������������:
	 *  	6  0110
	 *      11 1011
	 *���:  13 1101
	 */
	@Test
	public void xorTest(){
		
		int encode=6^11;
		int decode=encode^11;
		
		System.out.println("encode:"+encode+" decode:"+decode);

		int a=1,b=2;
		
		a=a^b;
		b=a^b;
		a=a^b;
		
		
		System.out.println("a:"+a+" b:"+b);
	}
	
	/**
	 * ÿһλ��ȡ��
	 */
	@Test
	public void notTest(){
		System.out.println(~100);
	}
	
	/**
	 * ���� ���ǽ������������ƶ� ĩβ���0  
	 * ����:
	 * 	2>>2 ���ǽ�������10��߼�����0 ��Ϊ1000
	 * 
	 */
	@Test
	public void shlTest(){
		System.out.println(2<<2);
	}
	/**
	 * ����  
	 *  ��������෴  �ǽ����Ķ����������ƶ�  ���ǽ�ȡλ��
	 *  ���� 15>>1  1111���111
	 */
	@Test
	public void shrTest(){
		System.out.println(15>>1);
	}
	@Test
	public void test(){
		System.out.println(0x7fffffff);
	}
	
	public void BitOperation(){
		
	}
	
	public static void main(String[] args) {
		new BitOperation().BitOperation();
	}
}

package com.hotusm.io.learn.bit;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;

/**
 * SequenceInputStream ���������ϳ�һ����
 * ���췽��ժҪ 
	SequenceInputStream(Enumeration<? extends InputStream> e) 
	          ͨ����ס��������ʼ���´����� SequenceInputStream���ò�����������������ʱ����Ϊ InputStream ����� Enumeration �Ͳ����� 
	SequenceInputStream(InputStream s1, InputStream s2) 
	          ͨ����ס��������������ʼ���´����� SequenceInputStream������˳���ȡ�������������ȶ�ȡ s1��Ȼ���ȡ s2�������ṩ�Ӵ� SequenceInputStream ��ȡ���ֽڡ� 
 */
public class SequenceInputStreamTest {

	public static void main(String[] args) throws Exception {
		testSequence();
	}

	public static void testSequence() throws Exception {
		InputStream inStream1 = SequenceInputStreamTest.class.getClassLoader().getResourceAsStream("f1.txt");
		InputStream inStream2 = SequenceInputStreamTest.class.getClassLoader().getResourceAsStream("f2.txt");
		/**
		 * �ڶ��ֹ��췽ʽ
		 */
		// Vector<InputStream> vector=new Vector<>();
		// vector.add(inStream1);
		// vector.add(inStream2);
		//
		// Enumeration<InputStream> elements = vector.elements();
		// SequenceInputStream stream = new SequenceInputStream(elements);
		SequenceInputStream stream = new SequenceInputStream(inStream1, inStream2);

		BufferedInputStream bStream = new BufferedInputStream(stream);
		int c;
		while ((c = bStream.read()) != -1) {
			System.out.print((char) c);
		}
	}

}

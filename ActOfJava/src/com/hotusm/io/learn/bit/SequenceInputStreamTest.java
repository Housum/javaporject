package com.hotusm.io.learn.bit;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;

/**
 * SequenceInputStream 将多个流组合成一个流
 * 构造方法摘要 
	SequenceInputStream(Enumeration<? extends InputStream> e) 
	          通过记住参数来初始化新创建的 SequenceInputStream，该参数必须是生成运行时类型为 InputStream 对象的 Enumeration 型参数。 
	SequenceInputStream(InputStream s1, InputStream s2) 
	          通过记住这两个参数来初始化新创建的 SequenceInputStream（将按顺序读取这两个参数，先读取 s1，然后读取 s2），以提供从此 SequenceInputStream 读取的字节。 
 */
public class SequenceInputStreamTest {

	public static void main(String[] args) throws Exception {
		testSequence();
	}

	public static void testSequence() throws Exception {
		InputStream inStream1 = SequenceInputStreamTest.class.getClassLoader().getResourceAsStream("f1.txt");
		InputStream inStream2 = SequenceInputStreamTest.class.getClassLoader().getResourceAsStream("f2.txt");
		/**
		 * 第二种构造方式
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

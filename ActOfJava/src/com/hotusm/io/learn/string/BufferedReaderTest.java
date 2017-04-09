package com.hotusm.io.learn.string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * ����ַ�������
 * �ܽ�������Reader���л���
 */
public class BufferedReaderTest {

	public static void main(String[] args) throws Exception {
		testBuffedReader();
	}

	public static void testBuffedReader() throws Exception {
		InputStreamReader inputStreamReader = new InputStreamReader(
				InputStreamReaderTest.class.getClassLoader().getResourceAsStream("f1.txt"));
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line = bufferedReader.readLine();
		System.out.println(line);
	}

}

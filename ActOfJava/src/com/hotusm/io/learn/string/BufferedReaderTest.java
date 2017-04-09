package com.hotusm.io.learn.string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 输出字符流缓冲
 * 能将其他的Reader进行缓冲
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

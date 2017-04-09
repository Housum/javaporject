package com.hotusm.io.learn.string;

import java.io.StringReader;

/**
 * StringReader  基于字符的字符流
 * 1.read(char[] buff,...)读取到字符数组中
 * 2.mark(readAheadLimit) 标记位置  之后可以通过reset()重新到这个位置
 * 3.reset() 
 */
public class StringReaderTest {

	public static void main(String[] args) {
		testStringReaderRead();
		System.out.println();
		testStringReaderMark();
	}

	public static void testStringReaderRead() {
		try(StringReader reader=new StringReader("abcdefg");) {

			char[] buff=new char[2];
			while(reader.read(buff)!=-1){
				System.out.print(new String(buff));
				buff=new char[2];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输出：abab
	 * @description <br/>
	 */
	public static void testStringReaderMark(){
		try(StringReader reader=new StringReader("abcdefg");) {
			char[] buff=new char[2];
			reader.mark(0);
			reader.read(buff);
			System.out.print(new String(buff));
			reader.reset();
			
			buff=new char[2];
			reader.read(buff);
			System.out.print(new String(buff));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

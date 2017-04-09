package com.hotusm.io.learn.string;

import java.io.StringReader;

/**
 * StringReader  �����ַ����ַ���
 * 1.read(char[] buff,...)��ȡ���ַ�������
 * 2.mark(readAheadLimit) ���λ��  ֮�����ͨ��reset()���µ����λ��
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
	 * �����abab
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

package com.hotusm.io.learn.string;

import java.io.CharArrayReader;

/**
 *  此类实现一个可用作字符输入流的字符缓冲区。 
 */
public class CharArrayReaderTest {
	
	public static void main(String[] args)throws Exception{
		testCharArrayReader();
	}
	
	/**
	 * 这里会死循环  因为mark了开头 
	 */
	public static void testCharArrayReader() throws Exception{
		CharArrayReader cReader=new CharArrayReader("hello ".toCharArray());
		if(cReader.markSupported()){
			cReader.mark(0);
		}
		int c;
		while((c=cReader.read())!=-1){
			System.out.println((char)c);
			if(c=='o'){
				cReader.reset();
			}
		}
	}
	
}

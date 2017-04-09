package com.hotusm.io.book;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class InputStreamTest {
	
	/**
	 * 
	 * InputStram read()读取一个字节  
	 * 会进行堵塞
	 */
	@Test
	public void readVoid() throws IOException{
		InputStream is=new ByteArrayInputStream("a b c".getBytes());
		for(;;){
			int b = is.read();
			if(b==-1){
				break;
			}
			System.out.println((char)b);
		}
	}
}

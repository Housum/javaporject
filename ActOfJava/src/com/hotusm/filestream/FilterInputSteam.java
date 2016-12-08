package com.hotusm.filestream;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;

import org.junit.Test;

/**
 * 这些类都是对InputSteam启动装饰类的作用的(包装类)
 * 
 *1.DataInputSteam 对基本类型以及String类型的支持
 *2.BufferedInputStream  对输入流进行缓存   不用每次都去取
 *3.LineNumberInputStream  跟踪输入流的行号  
 */
public class FilterInputSteam {

	@Test
	public void dataInputStream(){
		
		InputStream in=new StringBufferInputStream("1111");
		DataInputStream ds= new DataInputStream(in);
		
		try {
			String c = ds.readLine();
			System.out.println(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void bufferedinputstream(){
	}
}

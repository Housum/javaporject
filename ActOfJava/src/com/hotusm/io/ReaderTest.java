package com.hotusm.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Test;

/**
 *
 * Reader和Writer是面向字符的操作
 * 
 *1.FileReader  对应FileInputStram
 *2.StringReader  对应StringInputStream
 *3.CharArrayReader  
 *4.PipedReader    对应管道
 *
 *-------------------
 *适配:InputStreamReader对直接流的适配(进行策略的匹配)
 */
public class ReaderTest{
	
	@Test
	public void filereader(){
		
		try {
			Reader reader=new FileReader(new File("D://Constants.java"));
			char buffer[]=new char[2014];
			reader.read(buffer);
			System.out.println(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void inputstreamreader(){
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(new File(""))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

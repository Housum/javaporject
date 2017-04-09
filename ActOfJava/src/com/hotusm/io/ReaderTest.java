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
 * Reader��Writer�������ַ��Ĳ���
 * 
 *1.FileReader  ��ӦFileInputStram
 *2.StringReader  ��ӦStringInputStream
 *3.CharArrayReader  
 *4.PipedReader    ��Ӧ�ܵ�
 *
 *-------------------
 *����:InputStreamReader��ֱ����������(���в��Ե�ƥ��)
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

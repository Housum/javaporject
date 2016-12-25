package com.hotusm.guavaex.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.Test;

import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;

import junit.framework.TestCase;
/**
 * 	ByteStreams								CharStreams
	byte[] toByteArray(InputStream)			String toString(Readable)
	N/A										List<String> readLines(Readable)
	long copy(InputStream, OutputStream)	long copy(Readable, Appendable)
	void readFully(InputStream, byte[])		N/A
	void skipFully(InputStream, long)		void skipFully(Reader, long)
	OutputStream nullOutputStream()			Writer nullWriter()
 */
public class Utility extends TestCase{

	
	@Override
	protected void setUp() throws Exception {
		
	}
	@Test
	public void test_ByteStream_byte() throws FileNotFoundException, IOException{
		
		//1.
		byte[] b = ByteStreams.toByteArray(new FileInputStream("C://file.xml"));
		System.out.println(new String(b,Charset.defaultCharset()));
		
		byte[] newByte=new byte[1024];
		ByteStreams.readFully(new FileInputStream("C://file.xml"), newByte);
	}
	
	@Test
	public void test_ByteStream_other() throws FileNotFoundException, IOException{
		
		//���ƵĻ���NIO�Ĳ���
		ByteStreams.copy(new FileInputStream("C://file.xml"), new FileOutputStream("C://file1.xml"));
		
		//skip n��byte
		ByteStreams.skipFully(new FileInputStream("C://ckcore.txt"), 200);
		
		//�������һ���յ������  ��ֹ���ֿ�ָ��
		ByteStreams.nullOutputStream();
		
	}
	
	/*
	 * CharStream ������ַ��� 
	 */
	@Test
	public void test_CharStream_read() throws FileNotFoundException, IOException{
		
		String content = CharStreams.toString(new FileReader("C://ckcore.txt"));
		System.out.println(content.substring(1000));
		
		List<String> lines = CharStreams.readLines(new FileReader("C://ckcore.txt"));
		System.out.println("length:"+lines.size()+" content:"+lines);
		
		//CharStreams.skipFully(reader, n);;
		
		CharStreams.nullWriter();
		
	}
	
	@Test
	public void test_charStream_copy() throws FileNotFoundException, IOException{
		
		CharStreams.copy(new FileReader("C://ckcore.txt"), new FileWriter("C://ckcore1.txt"));
	}
	
	
	
	
}

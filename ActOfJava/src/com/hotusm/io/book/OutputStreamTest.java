package com.hotusm.io.book;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;

import org.junit.Test;

public class OutputStreamTest {
	
	/**
	 * OutputStream write(int b) 将一个0到255的整数作为一个字节输入
	 */
	@Test
	public void writeInt() throws IOException{
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		os.write(33);
		System.out.println(new String(os.toByteArray()));
	}
	
	@Test
	public void writeArray(){
		byte[] b=new byte[8];
		for(int i=33;i<33+8;i++){
			b[i-33]=(byte)i;
		}
		/**
		 * JDK1.7的用法
		 * 只要实现了Closeable 实现close方法  
		 */
		try(ByteArrayOutputStream os=new ByteArrayOutputStream()) {
			os.write(b);
			System.out.println(new String(os.toByteArray()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void tryFinally(){
		
		try(Node node=new Node()){
		} catch (Exception e) {
		}
	}
	
	class Node implements Closeable{

		@Override
		public void close() throws IOException {
			System.out.println("关闭");
		}
	}
}

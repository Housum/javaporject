package com.hotusm.io;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

import org.junit.Test;
/**
 * 
 * InputStream 和OutputStream是面向字节的操作
 * 
 * 1.ByteArrayInputStrean
 * 2.StringBufferInputStream
 * 3.FileInputStream
 * 4.PipedInputStram   管道流
 * 5.SequenceInputSteam   序列流  可以将多个流转化成一个InputStram
 * 
 */

public class InputStreamTest{
	
	@Test
	public void testByteArray() throws IOException{
		ByteArrayInputStream  bain=new ByteArrayInputStream("Hello".getBytes());
		byte[] b=new byte[bain.available()];
		try {
			bain.read(b);
			System.out.println(new String(b));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将二个或者多个流和并为一个流  典型做法就是将多个文件合并成一个文件
	 * @throws IOException
	 */
	@Test
	public void testSequence(){
		 //创建一个合并流的对象  
	       SequenceInputStream sis = null;  
	       //创建输出流。  
	       BufferedOutputStream bos = null;
	       
	       
	       Vector<InputStream> vector = new Vector<InputStream>();  
           try {
			   vector.addElement(new FileInputStream("D:\text1.txt"));
			   vector.addElement(new FileInputStream("D:\text2.txt"));  
	           vector.addElement(new FileInputStream("D:\text3.txt"));  
	           Enumeration<InputStream> e = vector.elements();
	           sis = new SequenceInputStream(e);  
	           
	           bos = new BufferedOutputStream(  
	                   new FileOutputStream("D:\text4.txt"));  
	           //读写数据  
	           byte[] buf = new byte[1024];  
	           int len = 0;  
	           while ((len = sis.read(buf)) != -1) {  
	               bos.write(buf, 0, len);  
	               bos.flush();  
	           }
		} catch (Exception e1) {
			if(sis!=null){
				try {
					sis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			}
			
			e1.printStackTrace();
		}  
          
	}

}

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
 * InputStream ��OutputStream�������ֽڵĲ���
 * 
 * 1.ByteArrayInputStrean
 * 2.StringBufferInputStream
 * 3.FileInputStream
 * 4.PipedInputStram   �ܵ���
 * 5.SequenceInputSteam   ������  ���Խ������ת����һ��InputStram
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
	 * ���������߶�����Ͳ�Ϊһ����  �����������ǽ�����ļ��ϲ���һ���ļ�
	 * @throws IOException
	 */
	@Test
	public void testSequence(){
		 //����һ���ϲ����Ķ���  
	       SequenceInputStream sis = null;  
	       //�����������  
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
	           //��д����  
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

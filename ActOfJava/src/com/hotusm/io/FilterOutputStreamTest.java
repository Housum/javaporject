package com.hotusm.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

/**
 *
 * 1.DataOutputStream
 * 2.BufferedOutputStream
 * 3.PrintStream  ��Ҫ������������ĸ�ʽ
 */
public class FilterOutputStreamTest {

	@Test
	public void printstream(){
		OutputStream os=null;
		try {
			os= new FileOutputStream(new File("D://Constants.java"));
			PrintStream ps=new PrintStream(os);
			//���ļ����뵽D://Constants.java��ȥ
			ps.println("1111");
			ps.append("22222");
		} catch (FileNotFoundException e) {
			
		}
		
		//�ײ���ͨ��������out  ���뵽����̨
		PrintStream out = System.out;
		out.print("111");
	}
}

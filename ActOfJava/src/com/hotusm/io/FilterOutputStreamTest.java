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
 * 3.PrintStream  主要控制数据输入的格式
 */
public class FilterOutputStreamTest {

	@Test
	public void printstream(){
		OutputStream os=null;
		try {
			os= new FileOutputStream(new File("D://Constants.java"));
			PrintStream ps=new PrintStream(os);
			//将文件输入到D://Constants.java中去
			ps.println("1111");
			ps.append("22222");
		} catch (FileNotFoundException e) {
			
		}
		
		//底层是通过设置了out  输入到控制台
		PrintStream out = System.out;
		out.print("111");
	}
}

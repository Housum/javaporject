package com.hotusm.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.junit.Test;

/**
 *1.FileWriter 
 *2.StringWriter
 *3.CharArrayWriter
 *4.PipedWriter
 *5.PrintWriter  兼容两种模式的类  具有两种形式的参数  Writer和OutputStream
 *6.OutputStreamWriter可以对字节流进行适配,构造函数接收一个输出流
 */
public class WriterTest {
	@Test
	public void filewriter(){
		
		File file=new File("D://append.txt");
		boolean isExist=file.exists();
		try {
			FileWriter fw=new FileWriter(file);
			BufferedWriter rw=new BufferedWriter(fw);
			if(isExist){
				fw.append("1111");
				fw.append("2222");
			}else{
				fw.write("new");
				fw.write("new");
			}
			
			rw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//PrintWriter对两种模式都很兼容
	@Test
	public void  printwriter(){
		try {
			OutputStream out=new FileOutputStream(new File(""));
			PrintWriter pw=new PrintWriter(out);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

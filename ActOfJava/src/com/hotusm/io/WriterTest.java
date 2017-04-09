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
 *5.PrintWriter  ��������ģʽ����  ����������ʽ�Ĳ���  Writer��OutputStream
 *6.OutputStreamWriter���Զ��ֽ�����������,���캯������һ�������
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
	
	//PrintWriter������ģʽ���ܼ���
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

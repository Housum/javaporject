package com.hotusm.filestream;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;

import org.junit.Test;

/**
 * ��Щ�඼�Ƕ�InputSteam����װ��������õ�(��װ��)
 * 
 *1.DataInputSteam �Ի��������Լ�String���͵�֧��
 *2.BufferedInputStream  �����������л���   ����ÿ�ζ�ȥȡ
 *3.LineNumberInputStream  �������������к�  
 */
public class FilterInputSteam {

	@Test
	public void dataInputStream(){
		
		InputStream in=new StringBufferInputStream("1111");
		DataInputStream ds= new DataInputStream(in);
		
		try {
			String c = ds.readLine();
			System.out.println(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void bufferedinputstream(){
	}
}

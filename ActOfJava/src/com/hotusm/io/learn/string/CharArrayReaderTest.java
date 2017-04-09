package com.hotusm.io.learn.string;

import java.io.CharArrayReader;

/**
 *  ����ʵ��һ���������ַ����������ַ��������� 
 */
public class CharArrayReaderTest {
	
	public static void main(String[] args)throws Exception{
		testCharArrayReader();
	}
	
	/**
	 * �������ѭ��  ��Ϊmark�˿�ͷ 
	 */
	public static void testCharArrayReader() throws Exception{
		CharArrayReader cReader=new CharArrayReader("hello ".toCharArray());
		if(cReader.markSupported()){
			cReader.mark(0);
		}
		int c;
		while((c=cReader.read())!=-1){
			System.out.println((char)c);
			if(c=='o'){
				cReader.reset();
			}
		}
	}
	
}

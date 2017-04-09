package com.hotusm.io.learn.string;

import java.io.InputStreamReader;

/**
 * InputStreamReader��InputStream�ֽ�������װ�γ�
 * �ַ��� �����ַ��Ĳ���
 * 1.InputStreamReader(InputStream in) ���캯������һ��ֱ����
 * 2.read(char[] buff,...) ��һ�������ǽ�Ҫ��ȡ���ַ���Ŀ�ĵ�
 * 3.ready() �Ƿ��Ѿ�׼�������ڶ�ȡ
 * 4.FileReader �̳��� InputStreamReader
 * 5.mark()�������׳��쳣
 */
public class InputStreamReaderTest {
	
	public static void main(String[] args) {
		testInputStreamRead();
	}
	
	public static void testInputStreamRead(){
		try (InputStreamReader inputStreamReader=new InputStreamReader(InputStreamReaderTest.class.getClassLoader().getResourceAsStream("f1.txt"))){
			char[] buff=new char[2];
			//���뵽buff�ַ�����
			while((inputStreamReader.read(buff))!=-1){
				System.out.print(new String(buff));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

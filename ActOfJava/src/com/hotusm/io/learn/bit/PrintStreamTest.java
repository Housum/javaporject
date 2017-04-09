package com.hotusm.io.learn.bit;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * PrintStream Ϊ�������������˹��ܣ�ʹ�����ܹ�����ش�ӡ��������ֵ��ʾ��ʽ��
 * 1.�����ṩ��������ܡ��������������ͬ��PrintStream ��Զ�����׳� IOException��
 * �쳣��������ÿ�ͨ�� checkError �������Ե��ڲ���־��
 * 2.���⣬Ϊ���Զ�ˢ�£����Դ���һ�� PrintStream������ζ�ſ���д�� byte ����֮���Զ����� flush ������
 * �ɵ�������һ�� println ��������д��һ�����з����ֽ� ('\n')�� 
 * 3.PrintStream ��ӡ�������ַ���ʹ��ƽ̨��Ĭ���ַ�����ת��Ϊ�ֽڡ�
 * 4.����Ҫд���ַ�������д���ֽڵ�����£�Ӧ��ʹ�� PrintWriter �ࡣע��
 * PrintStream���Զ�ˢ�µ� ����PrintWriter��Ҫ������ʽ������ 
 * @see com.hotusm.io.learn.string.PrintWriterTest 
 */
public class PrintStreamTest {
	
	public static void main(String[] args) throws Exception{
		testPrintStream();
	}

	public static void testPrintStream() throws Exception{
		PrintStream printStream=new PrintStream(new FileOutputStream("src/f3.txt"));
		printStream.println("1234");
		printStream.print("4567");
		if(printStream.checkError()){
			printStream.close();
			throw new RuntimeException();
		}
		printStream.close();
	}
}

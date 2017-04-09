package com.hotusm.io.learn.string;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * �� PrintStream �಻ͬ������������Զ�ˢ�£���ֻ���ڵ��� println��printf 
 * �� format ������һ������ʱ�ſ�����ɴ˲�����
 * ������ÿ������������з�ʱ����ɡ���Щ����ʹ��ƽ̨���е��зָ�����������ǻ��з��� 
 * 
 * PrintWriter(File file) 
          ʹ��ָ���ļ������������Զ���ˢ�µ��� PrintWriter�� 
	PrintWriter(File file, String csn) 
	          ��������ָ���ļ����ַ����Ҳ����Զ�ˢ���µ��� PrintWriter�� 
	PrintWriter(OutputStream out) 
	          �������е� OutputStream ���������Զ���ˢ�µ��� PrintWriter�� 
	PrintWriter(OutputStream out, boolean autoFlush) 
	          ͨ�����е� OutputStream �����µ� PrintWriter�� 
	PrintWriter(String fileName) 
	          ��������ָ���ļ������Ҳ����Զ���ˢ�µ��� PrintWriter�� 
	PrintWriter(String fileName, String csn) 
	          ��������ָ���ļ����ƺ��ַ����Ҳ����Զ���ˢ�µ��� PrintWriter�� 
	PrintWriter(Writer out) 
                     ���������Զ���ˢ�µ��� PrintWriter�� 
	PrintWriter(Writer out, boolean autoFlush) 
                      ������ PrintWriter�� 
 */
public class PrintWriterTest {

	public static void main(String[] args) throws Exception{
		testPrintWriterNotFlush();
		testPrintWriterAutoFlush();
	}
	public static void testPrintWriterNotFlush() throws Exception{
		PrintWriter printWriter=new PrintWriter(new FileWriter("src/notFlush.txt"));
		printWriter.println("hotusm");
		printWriter.println("hello123");
//		printWriter.flush();
		printWriter.close();
		System.out.println();
	}
	public static void testPrintWriterAutoFlush() throws Exception{
		PrintWriter printWriter=new PrintWriter(new FileWriter("src/autoFlush.txt",true));
		printWriter.println("hotusm");
		printWriter.println("hello123");
//		printWriter.flush();
		printWriter.close();
		System.out.println();
	}
}

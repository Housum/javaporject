package com.hotusm.io.learn.string;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * 与 PrintStream 类不同，如果启用了自动刷新，则只有在调用 println、printf 
 * 或 format 的其中一个方法时才可能完成此操作，
 * 而不是每当正好输出换行符时才完成。这些方法使用平台自有的行分隔符概念，而不是换行符。 
 * 
 * PrintWriter(File file) 
          使用指定文件创建不具有自动行刷新的新 PrintWriter。 
	PrintWriter(File file, String csn) 
	          创建具有指定文件和字符集且不带自动刷行新的新 PrintWriter。 
	PrintWriter(OutputStream out) 
	          根据现有的 OutputStream 创建不带自动行刷新的新 PrintWriter。 
	PrintWriter(OutputStream out, boolean autoFlush) 
	          通过现有的 OutputStream 创建新的 PrintWriter。 
	PrintWriter(String fileName) 
	          创建具有指定文件名称且不带自动行刷新的新 PrintWriter。 
	PrintWriter(String fileName, String csn) 
	          创建具有指定文件名称和字符集且不带自动行刷新的新 PrintWriter。 
	PrintWriter(Writer out) 
                     创建不带自动行刷新的新 PrintWriter。 
	PrintWriter(Writer out, boolean autoFlush) 
                      创建新 PrintWriter。 
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

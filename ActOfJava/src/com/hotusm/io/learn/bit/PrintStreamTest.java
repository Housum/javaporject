package com.hotusm.io.learn.bit;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * PrintStream 为其他输出流添加了功能，使它们能够方便地打印各种数据值表示形式。
 * 1.它还提供其他两项功能。与其他输出流不同，PrintStream 永远不会抛出 IOException；
 * 异常情况仅设置可通过 checkError 方法测试的内部标志。
 * 2.另外，为了自动刷新，可以创建一个 PrintStream；这意味着可在写入 byte 数组之后自动调用 flush 方法，
 * 可调用其中一个 println 方法，或写入一个换行符或字节 ('\n')。 
 * 3.PrintStream 打印的所有字符都使用平台的默认字符编码转换为字节。
 * 4.在需要写入字符而不是写入字节的情况下，应该使用 PrintWriter 类。注意
 * PrintStream是自动刷新的 但是PrintWriter需要进行显式的设置 
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

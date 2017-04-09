package com.hotusm.io.learn.string;

import java.io.InputStreamReader;

/**
 * InputStreamReader对InputStream字节流进行装饰成
 * 字符流 便于字符的操作
 * 1.InputStreamReader(InputStream in) 构造函数传入一个直接流
 * 2.read(char[] buff,...) 第一个参数是将要读取到字符的目的地
 * 3.ready() 是否已经准备好用于读取
 * 4.FileReader 继承自 InputStreamReader
 * 5.mark()方法会抛出异常
 */
public class InputStreamReaderTest {
	
	public static void main(String[] args) {
		testInputStreamRead();
	}
	
	public static void testInputStreamRead(){
		try (InputStreamReader inputStreamReader=new InputStreamReader(InputStreamReaderTest.class.getClassLoader().getResourceAsStream("f1.txt"))){
			char[] buff=new char[2];
			//读入到buff字符流中
			while((inputStreamReader.read(buff))!=-1){
				System.out.print(new String(buff));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

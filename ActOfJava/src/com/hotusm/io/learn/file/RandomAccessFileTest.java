package com.hotusm.io.learn.file;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * 此类的实例支持对随机访问文件的读取和写入。随机访问文件的行为类似存储在文件系统中的一个大型 byte 数组
 * 。存在指向该隐含数组的光标或索引，称为文件指针；输入操作从文件指针开始读取字节，并随着对字节的读取而前
 * 移此文件指针。如果随机访问文件以读取/写入模式创建，则输出操作也可用；输出操作从文件指针开始写入字节，
 * 并随着对字节的写入而前移此文件指针。写入隐含数组的当前末尾之后的输出操作导致该数组扩展。该文件指针可
 * 以通过 getFilePointer 方法读取，并通过 seek 方法设置。 
 * 通常，如果此类中的所有读取例程在读取所需数量的字节之前已到达文件末尾，则抛出 EOFException（是一种 IOException）。如果由于某些原因无法读取任何字节，而不是在读取所需数量的字节之前已到达文件末尾，则抛出 IOException，而不是 EOFException。需要特别指出的是，如果流已被关闭，则可能抛出 IOException。 
 * 
 * public RandomAccessFile(File file,String mode)
 *  "r" 以只读方式打开。调用结果对象的任何 write 方法都将导致抛出 IOException。  
	"rw" 打开以便读取和写入。如果该文件尚不存在，则尝试创建该文件。  
	"rws" 打开以便读取和写入，对于 "rw"，还要求对文件的内容或元数据的每个更新都同步写入到底层存储设备。  
	"rwd"   打开以便读取和写入，对于 "rw"，还要求对文件内容的每个更新都同步写入到底层存储设备。  

 */
public class RandomAccessFileTest {
	
	public static void main(String[] args) throws Exception{
		testRandomAccessFile();
	}
	
	public static void testRandomAccessFile() throws Exception{
		RandomAccessFile accessFile=new RandomAccessFile(new File("src/f1.txt"), "r");
		/*
		 * 直接跳到了下标为3的字符 
		 * 文件内容:
		 * ABCDEFG
		 * */
		accessFile.seek(3);
		
		char c=(char)accessFile.read();
		System.out.println(c);
	}

	
}

package com.hotusm.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import org.junit.Test;

/**
 *RandomAccessFile是和其他的体系不一样的类  
 *1.并不继承InputStream和OutputStream,但是却有这两个的作用
 *可以向前或者后面移动  或者指定移动位置
 *2.并且可以通过rw执行模式
 *
 */
public class RandomAccessFileTest {
	
	@Test
	public void randomaccessfile(){
		try {
			RandomAccessFile ras=new RandomAccessFile(new File("D://randomaccess.txt"),"rw");
			ras.writeDouble(100D);
			ras.writeUTF("Hello");
			//将位置向后面移动1000
			ras.seek(1000);
			ras.writeUTF("Hello");
			ras.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void append(){
		File file=new File("D://nio.txt");
		if(file.exists()){
			try {
				RandomAccessFile raf=new RandomAccessFile(file, "rw");
				raf.seek(raf.length());
				raf.writeUTF("append");
				raf.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

	}
}

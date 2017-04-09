package com.hotusm.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import org.junit.Test;

/**
 *RandomAccessFile�Ǻ���������ϵ��һ������  
 *1.�����̳�InputStream��OutputStream,����ȴ��������������
 *������ǰ���ߺ����ƶ�  ����ָ���ƶ�λ��
 *2.���ҿ���ͨ��rwִ��ģʽ
 *
 */
public class RandomAccessFileTest {
	
	@Test
	public void randomaccessfile(){
		try {
			RandomAccessFile ras=new RandomAccessFile(new File("D://randomaccess.txt"),"rw");
			ras.writeDouble(100D);
			ras.writeUTF("Hello");
			//��λ��������ƶ�1000
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

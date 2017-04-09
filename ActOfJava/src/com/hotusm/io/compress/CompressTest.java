package com.hotusm.io.compress;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

/**
 * 
 *对文件进行压缩和解压缩 
 */
public class CompressTest {
	
	@Test
	public void gzip(){
		try {
			BufferedOutputStream bos=new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("D://test.gz")));
			for(int i=0;i<10;i++){
				bos.write("hello  gzip".getBytes());
			}
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void zip(){
		try {
			FileOutputStream fos=new FileOutputStream("D://test.zip");
			CheckedOutputStream cos=new CheckedOutputStream(fos, new Adler32());
			ZipOutputStream zos=new ZipOutputStream(cos);
			BufferedOutputStream bos=new BufferedOutputStream(zos);
			zos.setComment("this is test");
			//可以进行
			zos.putNextEntry(new ZipEntry("test.txt"));
			bos.write("this is text.txt's content".getBytes());
			bos.flush();
			
			zos.putNextEntry(new ZipEntry("test1.html"));
			bos.write("<html><title>test</title><body><h1>hello<h1></body></html>".getBytes());
			bos.flush();
			
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

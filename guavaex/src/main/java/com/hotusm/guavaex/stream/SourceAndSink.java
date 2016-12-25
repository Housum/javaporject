package com.hotusm.guavaex.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.TreeTraverser;
import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import junit.framework.TestCase;
/**
 * 
 * 对于所有可读的 称之为源(source) 所有可写的称为汇(sink)
 * 
 		字节			字符
	读	ByteSource	CharSource
	写	ByteSink	CharSink
 */
public class SourceAndSink extends TestCase{
	
	String fileName="";

	@Override
	protected void setUp() throws Exception {
		fileName="C://ckcore.txt";
	}
	
	@Test
	public void test_ByteSource() throws Exception{
		
		ByteSource bs = Files.asByteSource(new File(fileName));
		bs.openStream();
		
		//将资源作为流  && openStream 将根据 Source或者是Sink返回 InputStream 或者OutputStream
		ByteSource bs1 = Resources.asByteSource(new URL("http://ifeve.com/google-guava-io/"));
		
		//① 
		ByteStreams.copy(bs1.openStream(), Files.asByteSink(new File("C://test.html"),FileWriteMode.APPEND).openStream());
		//②
		bs1.copyTo(Files.asByteSink(new File("C://test.html"),FileWriteMode.APPEND));
		
	}
	
	@Test
	public void test_ByteSink() throws FileNotFoundException, IOException{
		
		Files.write(ByteStreams.toByteArray(new FileInputStream("C://test.html")), new File("C://test1.html"));
	}
	
	@Test
	public void test_close() throws IOException{
		ByteSource bs = Files.asByteSource(new File(fileName));
		InputStream os = bs.openStream();
		os.close();
	}
	
	@Test
	public void test_Files(){
		
		//查看某个文件夹的字文件夹
		TreeTraverser<File> tree = Files.fileTreeTraverser();
		Iterator<File> iterator = tree.children(new File("C://")).iterator();
		
		while(iterator.hasNext()){
			
			System.out.println(iterator.next().getName());
		}
		
		//规范文件路径，并不总是与文件系统一致
		String simplifyPath = Files.simplifyPath("C:\\tmp\\logs");
		System.out.println(simplifyPath);
		
	}
}

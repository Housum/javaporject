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
 * �������пɶ��� ��֮ΪԴ(source) ���п�д�ĳ�Ϊ��(sink)
 * 
 		�ֽ�			�ַ�
	��	ByteSource	CharSource
	д	ByteSink	CharSink
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
		
		//����Դ��Ϊ��  && openStream ������ Source������Sink���� InputStream ����OutputStream
		ByteSource bs1 = Resources.asByteSource(new URL("http://ifeve.com/google-guava-io/"));
		
		//�� 
		ByteStreams.copy(bs1.openStream(), Files.asByteSink(new File("C://test.html"),FileWriteMode.APPEND).openStream());
		//��
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
		
		//�鿴ĳ���ļ��е����ļ���
		TreeTraverser<File> tree = Files.fileTreeTraverser();
		Iterator<File> iterator = tree.children(new File("C://")).iterator();
		
		while(iterator.hasNext()){
			
			System.out.println(iterator.next().getName());
		}
		
		//�淶�ļ�·���������������ļ�ϵͳһ��
		String simplifyPath = Files.simplifyPath("C:\\tmp\\logs");
		System.out.println(simplifyPath);
		
	}
}

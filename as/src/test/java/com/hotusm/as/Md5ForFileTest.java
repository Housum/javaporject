package com.hotusm.as;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;

import junit.framework.TestCase;

public class Md5ForFileTest extends TestCase{

	private File file=null;
	
	@Override
	protected void setUp() throws Exception {
		file=new File("D://jeesite//as//src//test//resources//md5");
	}
	@Test
	public void test_md5_file() throws IOException{
		
		String projectPath=getClass().getClassLoader().getResource("").toString();
		
		byte[] oldF=Files.hash(file, Hashing.sha1()).asBytes();
		
		CharSink cs = Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND);
		Writer w = cs.openBufferedStream();
		w.write("www");
		w.flush();
		w.close();
		
		byte[] newF=Files.hash(file, Hashing.sha1()).asBytes();
		
		
		assertEquals(oldF, newF);
	}
	
	@Test
	public void test_5_second_auto_save(){
		
		ScheduledExecutorService schedule = Executors.newScheduledThreadPool(4);
		
		Thread thread = new Thread(){
			public void run() {
				long oldMd5=md5Old(file);
				//String newMd5 = md5ForFile(file);
			};
		};
		
		schedule.scheduleAtFixedRate(thread, 0, 5, TimeUnit.SECONDS);
		
		synchronized (this) {
			
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	long md5ForFile(File file){
		try {
			return Files.hash(file, Hashing.sha1()).asLong();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	long md5Old(File file){
		Preconditions.checkArgument(file!=null);
		String absolutePath = file.getAbsolutePath();
		System.out.println(absolutePath);
		if(absolutePath.indexOf("\\")!=-1){
			
			File f=new File(absolutePath+"-$md");
			if(!f.exists()){
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return -1;
			}else{
				CharSource cs = Files.asCharSource(f, Charsets.UTF_8);
				
				try {
					BufferedReader buff = cs.openBufferedStream();
					return Long.valueOf(buff.readLine().trim());
				} catch (IOException e) {
					return -1;
				}
			}
		}else{
			return -1;
		}
		
	}
	
	public void test_md5Old(){
		md5Old(file);
	}
	
}

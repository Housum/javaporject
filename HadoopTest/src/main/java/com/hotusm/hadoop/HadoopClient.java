package com.hotusm.hadoop;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class HadoopClient{

	public static void main(String[] args) throws Exception {
		InputStream in = new BufferedInputStream(new FileInputStream("C://111.txt"));
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(""),conf);
		OutputStream out = fs.create(new Path(""));
		IOUtils.copyBytes(in, out, 4096,true);//4096ÊÇ4k×Ö½Ú
		System.out.println("success");
		
		
	}
}

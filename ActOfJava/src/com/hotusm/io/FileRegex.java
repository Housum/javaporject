package com.hotusm.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class FileRegex {
	
	public static void main(String[] args) {
		File file=new File("D://");
		String[] list = file.list(new DirDilter("[a-zA-Z]+"));
		for(String s:list){
			System.out.println(s);
		}
		
		
	}
}
//对文件进行筛选
class DirDilter implements FilenameFilter{
	
	private Pattern pattern;
	
	public DirDilter(String regex){
		this.pattern=Pattern.compile(regex);
	}

	@Override
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();
	}
}
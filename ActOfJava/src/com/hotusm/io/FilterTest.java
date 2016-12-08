package com.hotusm.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class FilterTest {
	
	public static void main(String[] args) {
		File path=new File("D:");
		String[] list=path.list(new DirFilter("D.*\\.pom"));
		//File path=new File("D:/jeesite-master");
		show(path,"");
		//System.out.println("文件数量:"+count);
	}
	
	private static int count=0;
	//使用递归将文件按层级排列出来
	public static void show(File file,String sa){
		String[] list=file.list();
		for(String s:list){
			count++;
			File path1=new File(file.getAbsolutePath()+"/"+s);
			System.out.println(sa+s);
			if(path1.isDirectory()){
				show(path1,"="+sa);
			}
		}
	}
}
class DirFilter implements FilenameFilter{
	
	private Pattern pattern;
	
	public DirFilter(String regex) {
		pattern=Pattern.compile(regex);
	}

	@Override
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();
	}
}

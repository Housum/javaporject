package com.hotusm.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordCount {

	public void count(String...files){
		ExecutorService es=Executors.newCachedThreadPool();
		Map<String,Integer> map=new HashMap<String,Integer>();
		for(int i=0;i<files.length;i++){
			
			try {
				map.put(files[i], es.submit(new RunnableImpl(files[i])).get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		for(Map.Entry<String, Integer> entity:map.entrySet()){
			System.out.println(entity.getKey()+" "+entity.getValue());
		}
		
	}
	
	public static void main(String[] args) {
		new WordCount().count("C:/111.txt","C:/ckcore.txt","C:/vcredist_x86.log");
	}
}
class RunnableImpl implements Callable<Integer>{
	private String file;
	
	public RunnableImpl(String file){
		this.file=file;
	}
	@Override
	public Integer call() throws Exception {
		int count=0;
		try {
			Reader r=new FileReader(new File(file));
			BufferedReader reader=new BufferedReader(r);
			String line = null;
			while((line=reader.readLine())!=null){
				String[] words = line.split(" ");
				for(String word:words){
					if(!word.equals("")){
						count++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
}

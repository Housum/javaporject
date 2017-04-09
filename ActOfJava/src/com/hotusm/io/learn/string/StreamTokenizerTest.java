package com.hotusm.io.learn.string;

import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * 类获取输入流并将其解析为“标记”，
 */
public class StreamTokenizerTest {

	public static void main(String[] args) {
		try {
			testCountWord();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void testCountWord() throws Exception{
		//这里的a 不进行统计
		StringReader reader=new StringReader("i'm a boy");
		StreamTokenizer streamTokenizer=new StreamTokenizer(reader);
		
		int wordCount=0;
		while(streamTokenizer.nextToken()!=StreamTokenizer.TT_EOF){
			wordCount++;
		}
		System.out.println("wordCount:"+wordCount);
		
	}
}

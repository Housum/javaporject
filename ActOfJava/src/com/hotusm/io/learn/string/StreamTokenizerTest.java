package com.hotusm.io.learn.string;

import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * ���ȡ���������������Ϊ����ǡ���
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
		//�����a ������ͳ��
		StringReader reader=new StringReader("i'm a boy");
		StreamTokenizer streamTokenizer=new StreamTokenizer(reader);
		
		int wordCount=0;
		while(streamTokenizer.nextToken()!=StreamTokenizer.TT_EOF){
			wordCount++;
		}
		System.out.println("wordCount:"+wordCount);
		
	}
}

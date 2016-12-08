package com.hotusm.filestream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.junit.Test;
/**
 *1.BufferedReader 
 *
 */
public class FilterReaderTest {
	
	@Test
	public void filterreader(){
		try {
			Reader reader=new FileReader(new File("D://Constants.java"));
			BufferedReader br=new BufferedReader(reader);
			//------------
//			char buffer[]=new char[1024];
//			int skip=0;
//			do{
//				skip=br.read(buffer);
//				System.out.println(skip);
//			}while(skip!=-1);
//			System.out.println(buffer);
			//-------------
			String s;
			do{
				s = br.readLine();
				if(null!=s){
					System.out.println(s);
				}
			}while(s!=null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

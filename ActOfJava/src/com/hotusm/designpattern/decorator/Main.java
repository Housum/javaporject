package com.hotusm.designpattern.decorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.junit.Test;

public class Main {
	
	@Test
	public void custom(){
		
		Beverage beverage=new Mocha(new Mocha(new Espresso()));
		
		BigDecimal bd = beverage.cost();
		
		System.out.println("º€«Æ:"+bd.toString());
		System.out.println("√Ë ˆ:"+beverage.getDesc());
	}
	
	@Test
	public void testIO(){
		int c;
		
		try {
			InputStream inputStream=
					new LowerCaseInputStream(
							new BufferedInputStream(
									new FileInputStream("C://file.xml")));
			
			while((c=inputStream.read())!=-1){
				
				System.out.print((char)c);
			}
			
			inputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

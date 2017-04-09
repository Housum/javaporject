package com.hotusm.io;

import java.io.IOException;

import org.junit.Test;
/**
 *±ê×¼IO 
 *
 */
public class StandardIOTest{
	
	@Test
	public void standard(){
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

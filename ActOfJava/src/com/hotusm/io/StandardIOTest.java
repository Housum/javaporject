package com.hotusm.io;

import java.io.IOException;

import org.junit.Test;
/**
 *��׼IO 
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

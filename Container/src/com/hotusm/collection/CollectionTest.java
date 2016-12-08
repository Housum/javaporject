package com.hotusm.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
/**
 * 
 * @author Hotusm
 *Collection≤‚ ‘
 */
public class CollectionTest {
	
	@Test
	public void testSet(){
		Set<String> s=new HashSet<>();
		s.add("1");
		s.add("1");
	}
	
	@Test
	public void testArrays(){
		List<String> list=Arrays.asList("1","2","2");
		for(String s:list){
			System.out.println(s);
		}
	}
	
}

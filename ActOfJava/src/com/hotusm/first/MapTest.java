package com.hotusm.first;

import java.util.AbstractSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.junit.Test;

/**
 * 
 * @author Hotusm
 * 1.map
 * @param <K>
 */
public class MapTest<K> {
	/*
	 * ≤‚ ‘TreeMap∫ÕHashMap–‘ƒ‹
//	 * 
//	 * */
//	public static void main(String[] args) {
//		HashMap<String,String> hMap=new HashMap<String,String>();
//		long start=System.currentTimeMillis();
//		for(int i=0;i<1000000;i++){
//			hMap.put("i", "i"+i);
//		}
//		long end=System.currentTimeMillis();
//		System.out.println(end-start);
//		
//		TreeMap<String,String> tMap=new TreeMap<String,String>();
//		long start1=System.currentTimeMillis();
//		for(int i=0;i<1000000;i++){
//			tMap.put("i"+i, "i"+i);
//		}
//		long end1=System.currentTimeMillis();
//		System.out.println(end1-start1);
//		
//		long fStart=System.currentTimeMillis();
//		hMap.get("i10343");
//		long fEnd=System.currentTimeMillis();
//		System.out.println(fStart-fEnd);
//		long fStart1=System.currentTimeMillis();
//		tMap.get("i10343");
//		long fEnd1=System.currentTimeMillis();
//		System.out.println(fStart1-fEnd1);
//	}
	

}
class AbstractMap1{
	
	 Set<String> keySet = new AbstractSet<String>() {

		@Override
		public Iterator<String> iterator() {
			
			return new Iterator<String>(){

				@Override
				public boolean hasNext() {
					return false;
				}

				@Override
				public String next() {
					return null;
				}

				@Override
				public void remove() {
					
				}
				
			};
		}

		@Override
		public int size() {
			return 0;
		}
	};

}
class MapTest1{
	
	private static Logger logger=Logger.getLogger("MapTest1");
	public static void main(String[] args) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("1", 1);
		map.put("2", 1);
		Object object = map.get("1");
		System.out.println("hashCode:"+object.hashCode());
		Object object2 = map.get("2");
		System.out.println("hashCode:"+object2.hashCode());
		System.out.println(map.hashCode());
		logger.info("sss");
	}
}

package com.hotusm.test.comparator;
/**
 *
 * Comparable interface
 */
public class Entity implements Comparable{

	@Override
	public int compareTo(Object o) {
		if(o==null){
			return 1;
		}
		if(this==o){
			return 0;
		}
		
		return this.hashCode()-o.hashCode();
	}

}

package com.hotusm.map;

import java.util.Hashtable;

public class HashTableTest {
	
	public static void main(String[] args) {
		Hashtable hashTable=new Hashtable();
		hashTable.put("1", 1);
		hashTable.put("1", 2);
		
		Object object = hashTable.get("1");
		System.out.println(object);
	}
}

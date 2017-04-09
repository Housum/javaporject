package com.hotusm.algorithm.datastructure.stack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		test();
	}

	public static void test() {

		long t1 = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			List<Object> arrList = new ArrayList<>();
		}
		long t2 = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			List<Object> arrList = new LinkedList<>();
		}
		long t3 = System.nanoTime();
		long tc1 = t2 - t1;
		long tc2 = t3 - t2;
		System.out.println(tc1 + " " + tc2);
	}
}

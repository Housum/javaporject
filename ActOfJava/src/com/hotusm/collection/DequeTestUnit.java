package com.hotusm.collection;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;

public class DequeTestUnit {
	
	@Test
	public void DequeTest(){
		Deque<Node> deques=new ArrayDeque<Node>();
		deques.add(new Node("A"));
		deques.add(new Node("B"));
		System.out.println(deques.pollFirst());
		System.out.println(deques.pollFirst());
	}
}

package com.hotusm.thread.detail.book.monitor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VisualComponent {
	
	/**
	 * @see  java.util.concurrent.CopyOnWriteArrayList.set(int, E)
	 * CopyOnWriteArrayList是一个线程安全的list  并且实现是采用栈的形式
	 */
	private final List<KeyListener> keyListeners
	=new CopyOnWriteArrayList<KeyListener>();
	
	
}

package com.hotusm.thread.detail.book.monitor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VisualComponent {
	
	/**
	 * @see  java.util.concurrent.CopyOnWriteArrayList.set(int, E)
	 * CopyOnWriteArrayList��һ���̰߳�ȫ��list  ����ʵ���ǲ���ջ����ʽ
	 */
	private final List<KeyListener> keyListeners
	=new CopyOnWriteArrayList<KeyListener>();
	
	
}

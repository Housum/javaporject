package com.hotusm.designpattern.composite;

import java.util.Iterator;

@SuppressWarnings("rawtypes")
public class NullIterator implements Iterator{

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public Object next() {
		return null;
	}
}

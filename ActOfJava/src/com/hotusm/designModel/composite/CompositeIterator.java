package com.hotusm.designModel.composite;

import java.util.Iterator;
import java.util.Stack;

@SuppressWarnings("rawtypes")
public class CompositeIterator implements Iterator{
	
	private Stack stack=new Stack();

	public CompositeIterator(Iterator iterator){
		stack.push(iterator);
	}
	@Override
	public boolean hasNext() {
		if(stack.empty()){
			return false;
		}else{
			Iterator iterator=(Iterator) stack.peek();
			if(!iterator.hasNext()){
				stack.pop();
				return hasNext();
			}else{
				return true;
			}
		}
	}

	@Override
	public Object next() {
		if(hasNext()){
			Iterator iterator=(Iterator) stack.peek();
			MenuCompont compont= (MenuCompont) iterator.next();
			if(compont instanceof Menu){
				stack.push(compont.createIterator());
			}
			return compont;
		}
		return null;
	}

}

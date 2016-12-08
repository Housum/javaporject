package com.hotusm.designModel.composite;

import java.util.Iterator;

/**
 * 
 * @author Hotusm  <br/>
 * @date 2016年10月17日   <br/>
 * @description 组合模式  
 * 1.允许你将对象组成树形结构。组合能让客户已一致的方式处理个别对象和对象组合
 * 
 * 2.我们称包含其他组件的组件叫做组合对象,而称没有包含其他组件的组件为叶节点对象
 * 
 * 3.为了保持透明性(第客户端调用来说  对改变的东西是没有任何感应的),所以叶节点对象和组合对象都
 * 继承了同一个接口
 * 
 * @see  javax.swing.JComponent
 */
public abstract class MenuCompont {

	public void add(MenuCompont compont){
		throw new UnsupportedOperationException();
	}
	public void remove(MenuCompont compont){
		throw new UnsupportedOperationException();
	}
	public MenuCompont getChild(int i){
		throw new UnsupportedOperationException();
	}
	
	public String getName(){
		throw new UnsupportedOperationException();
	};
	public double getPrice(){
		throw new UnsupportedOperationException();
	}
	public boolean isVegetarian(){
		throw new UnsupportedOperationException();
	}
	
	public void print(){
		throw new UnsupportedOperationException();
	}
	public abstract Iterator createIterator();
	
}

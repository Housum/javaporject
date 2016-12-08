package com.hotusm.designModel.composite;

import java.util.Iterator;

/**
 * 
 * @author Hotusm  <br/>
 * @date 2016��10��17��   <br/>
 * @description ���ģʽ  
 * 1.�����㽫����������νṹ��������ÿͻ���һ�µķ�ʽ����������Ͷ������
 * 
 * 2.���ǳư���������������������϶���,����û�а���������������ΪҶ�ڵ����
 * 
 * 3.Ϊ�˱���͸����(�ڿͻ��˵�����˵  �Ըı�Ķ�����û���κθ�Ӧ��),����Ҷ�ڵ�������϶���
 * �̳���ͬһ���ӿ�
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

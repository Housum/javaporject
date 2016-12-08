package com.hotusm.designModel.template;

import java.awt.Graphics;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
	
	public MyFrame(String title){
		super(title);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,300);
		this.setVisible(true);
	}
	/**
	 * 这个方法是父类模板的一个钩子
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		String msg="I rule!!!";
		g.drawString(msg, 100, 100);
	}
	
	public static void main(String[] args) {
		
		new MyFrame("Template Hook");
	}
}

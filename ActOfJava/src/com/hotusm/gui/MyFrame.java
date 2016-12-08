package com.hotusm.gui;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame{
	
	
	public static void main(String[] args) {
		JButton button=new JButton("button1");
		button.setBounds(0, 0, 200, 40);
		JFrame frame=new JFrame("Tree");
		frame.setSize(400, 400);
		frame.add(button);
		
		frame.setVisible(true);
		
	}
}

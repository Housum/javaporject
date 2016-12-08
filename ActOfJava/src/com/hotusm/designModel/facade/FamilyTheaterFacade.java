package com.hotusm.designModel.facade;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016��10��15��   <br/>
 * @description  ���ģʽ  ������ںܶ���� ��ϳ�һ����;  
 * 1�����ʱ��  �ͻ�����Ҫ���úܶ����  
 * ����ʹ�����ģʽ�Ļ�  ��ô�ͺܼ򵥵�ֻ��Ҫ����һ������ 
 * 2.�ڶ�����������Ļ�  ���ܹ����ͻ�����ϵͳ(��������������)���н���Ĳ��� 
 */
public class FamilyTheaterFacade {

	private CdPlayer cdPlayer;
	private DVDPlayer dvdPlayer;
	private Tuner tuner;
	
	public FamilyTheaterFacade(CdPlayer cdPlayer,DVDPlayer dvdPlayer,Tuner tuner){
		
		this.cdPlayer=cdPlayer;
		this.dvdPlayer=dvdPlayer;
		this.tuner=tuner;
	}
	
	public void watchMovie(){
		this.tuner.play();
		this.dvdPlayer.play();
		this.cdPlayer.play();
	}
	
	public static void main(String[] args) {
		
		
	}
}

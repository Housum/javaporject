package com.hotusm.designModel.facade;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016年10月15日   <br/>
 * @description  外观模式  如果存在很多的类 组合成一个用途  
 * 1、这个时候  客户就需要调用很多的类  
 * 但是使用外观模式的话  那么就很简单的只需要调用一个方法 
 * 2.第二点就是这样的话  就能够将客户和子系统(就是其中诸多的类)进行解耦的操作 
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

package com.hotusm.designpattern.command;

public class SimpleRemoteContorl {

	private Command command;
	
	public SimpleRemoteContorl(Command command){
		
		 this.command=command;
	}
	
	public void buttonWasPressed(){
		
		this.command.execute();
	}
	
	public void undo(){
		
		this.command.undo();
	}
	
	public void setCommand(Command command) {
		this.command = command;
	}
	
	public static void main(String[] args) {
		
		//接受者知道如何进行必要的的工作 实现这个请求，任何类都可以当接受者
		Light light=new SimpleLight();   //接受者
		
		//命令
		Command command=new LightOnCommand(light);  //在这里创建一个命令  然后将接收者传给它
		Command commandOff=new LightOffCommand(light);
		
		//这个调用者持有一个命令对象 并在某个时间点调用命令对象的execute()方法,将请求付诸实行
		SimpleRemoteContorl contorl=new SimpleRemoteContorl(command);   //将命令传递给调用者
		
		
		contorl.buttonWasPressed();  //模拟
		
		//同时命令对象也可以支持取消操作
		contorl.undo();
		
		contorl.setCommand(commandOff); //可以重新的设置命令 
//		contorl.buttonWasPressed();
	}
	
	private static class SimpleLight implements Light{

		@Override
		public void on() {
			
			System.out.println("On!!!");
		}

		@Override
		public void off() {
			
			System.out.println("Off!!!");
		}
		
	}

}

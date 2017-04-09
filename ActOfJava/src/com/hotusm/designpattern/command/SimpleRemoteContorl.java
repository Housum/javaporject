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
		
		//������֪����ν��б�Ҫ�ĵĹ��� ʵ����������κ��඼���Ե�������
		Light light=new SimpleLight();   //������
		
		//����
		Command command=new LightOnCommand(light);  //�����ﴴ��һ������  Ȼ�󽫽����ߴ�����
		Command commandOff=new LightOffCommand(light);
		
		//��������߳���һ��������� ����ĳ��ʱ��������������execute()����,��������ʵ��
		SimpleRemoteContorl contorl=new SimpleRemoteContorl(command);   //������ݸ�������
		
		
		contorl.buttonWasPressed();  //ģ��
		
		//ͬʱ�������Ҳ����֧��ȡ������
		contorl.undo();
		
		contorl.setCommand(commandOff); //�������µ��������� 
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

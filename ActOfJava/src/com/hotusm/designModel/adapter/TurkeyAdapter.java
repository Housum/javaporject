package com.hotusm.designModel.adapter;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016年10月15日   <br/>
 * @description  适配器模式  
 * @see org.springframework.web.servlet.HandlerAdapter  springmvc 中典型的使用场景
 * 
 */
public class TurkeyAdapter implements Duck{
	

	private  TurKey turKey;
	
	public TurkeyAdapter(TurKey turKey) {
		
		this.turKey=turKey;
	}
	
	@Override
	public void quack() {
		
		this.turKey.gobble();
	}

	@Override
	public void fly() {
		
		this.turKey.fly();
		this.turKey.fly();
		this.turKey.fly();
	}
	
	public static void main(String[] args) {
		
		TurKey turKey=new WildTurKey();
		Duck duck=new MallardDuck();
		turKey.gobble();
		turKey.fly();
		
		System.out.println("------------------");
		duck.fly();
		duck.quack();
		System.out.println("------------------");
		Duck turKeyAdapter=new TurkeyAdapter(turKey);
		
		turKeyAdapter.fly();
		turKeyAdapter.quack();
		
	}

}

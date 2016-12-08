package com.hotusm.interfaceT;
/**
 * 
 * @author Hotusm
 * 使用静态工厂方法的好处
 */
public class InterfaceTest {
	public static void main(String[] args) {
		PeopleProvider p=new PeopleProvider();
		People p1 = p.getInstace();
		p1.eat(new Meat());
	}
}
class PeopleProvider{
	
	public People getInstace(){
		return new PeopleImpl();
	}
	
	//对于客户端程来说,并不知道内部实现,因为这是一个private类型的类型
	//这也是面向接口编程的一个思想
	
	private class PeopleImpl implements People{

		@Override
		public void eat(Object object) {
			if(!(object instanceof Meat)){
				say("他是食肉的");
			}else{
				say("吃的是肉");
			}
		}

		@Override
		public void say(String language) {
				System.out.println(language);
			}
		}
}

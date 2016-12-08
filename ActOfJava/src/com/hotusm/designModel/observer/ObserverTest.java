package com.hotusm.designModel.observer;

public class ObserverTest {

	public static void main(String[] args) {
		
		Multicast event=new Multicast();
		
		Observer older=new Older();
		Observer older1=new Older();
		Observer older2=new Older();
		Observer older3=new Older();
		
		event.registerOberver(older);
		event.registerOberver(older1);
		event.registerOberver(older2);
		event.registerOberver(older3);
		
		event.hasNews("台风来了");
		System.out.println("-");
		event.remove(older1);
		
		event.hasNews("台风走了");
		
		
		
	}
}

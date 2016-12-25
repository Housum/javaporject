package junit;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext atx=new ClassPathXmlApplicationContext("spring-context.xml");
		atx.start();
		
		try {
			System.out.println("server start... press any key to quit");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

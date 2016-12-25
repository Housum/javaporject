package com.hotusm.dubbo.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hotusm.dubbo.model.entity.User;

public class DemoServiceImpl implements DemoService {

	public String sayHello() {
		
		return "hello dubbo";
	}

	public List<?> getAllUser() {
		List<User> list=new ArrayList<User>();
		Random r=new Random();
		for(int i=0;i<10;i++){
			list.add(new User(""+r.nextInt(100)));
		}
		
		return list;
	}

}

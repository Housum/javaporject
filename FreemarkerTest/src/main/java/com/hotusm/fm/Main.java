package com.hotusm.fm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
	
	public static void main(String[] args) {
//		Map<String,Object> root=new HashMap<String, Object>();
//		List<Menu> menus=new ArrayList<Menu>();
//		
//		menus.add(new Menu("1", "基础设置", "0", "0,", 1, "/1/img"));
//			menus.add(new Menu("2", "菜单管理", "1", "0,1,", 2, "/2/img"));
//				menus.add(new Menu("3", "功能菜单", "2", "0,1,2,", 3, "/3/img"));
//			menus.add(new Menu("4", "角色管理", "1", "0,1,", 4, "/4/img"));
//			menus.add(new Menu("5", "业务员管理", "1", "0,1,", 5, "/1/img"));
//		menus.add(new Menu("6", "交易设置", "0", "0,", 6, "/6/img"));
//			menus.add(new Menu("7", "开/闭市管理", "6", "0,6,", 7, "/7/img"));
//			menus.add(new Menu("8", "交易节设置", "6", "0,6,", 8, "/8/img"));
//			menus.add(new Menu("9", "交易节管理", "0", "0,6", 9, "/9/img"));
//		root.put("menus", menus);
		
		Map<String,Object> root=new HashMap<String, Object>();
		
		
		Map<String,Object> values=new HashMap<String, Object>();
		
		Map<String,Object> seconds=new HashMap<String, Object>();
		seconds.put("key", "value");
		
		
		//User user=new User();
		//user.setName("hotusm");
		
		//values.put("user", user);
		
		
		root.put("values", values);
		values.put("seconds", seconds);
		
		//FreeMarkerUtil.print("01.ftl", root);
		root.put("attribute", "hotusm");
		
		FreeMarkerUtil.print("ftl.xml", root);
		
		
	}
}

package com.hotusm.fm;

import java.util.HashMap;
import java.util.Map;

public class Test07 {
	
	public static void main(String[] args) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("content", new CustomTag());
		FreeMarkerUtil.print("07.ftl", map);
	}
}

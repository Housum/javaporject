package com.thinkgem.jeesite.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.support.json.JSONUtils;

public class JsonTest {
	
	public static void main(String[] args) {
		List list=new ArrayList();
		list.add("1");
		list.add("1");
		list.add("1");
		System.out.println(JSONUtils.toJSONString(list));
	}
}

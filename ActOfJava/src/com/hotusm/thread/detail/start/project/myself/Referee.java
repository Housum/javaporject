package com.hotusm.thread.detail.start.project.myself;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ����
 * 	
 */
public class Referee {
	
	private String name;
	/**
	 * ������
	 */
	private Map<Player,Result> result=new ConcurrentHashMap<Player, Result>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<Player, Result> getResult() {
		return result;
	}
}

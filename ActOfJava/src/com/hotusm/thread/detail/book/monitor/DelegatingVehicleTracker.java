package com.hotusm.thread.detail.book.monitor;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 基于委托的车辆追踪
 * 1.注意  这里使用的是Point  Point是一个不可变对象  所以是线程安全的  但是如果我们
 * 使用的是MutablePoint的话  那么那不是线程安全的  因为但会的引用可以进行修改对象
 * @see com.hotusm.thread.detail.book.monitor.MonitorVehicleTracker
 * 
 */
public class DelegatingVehicleTracker {
	
	private final ConcurrentHashMap<String, Point> locations;
	private final Map<String,Point> unmodifiableMap;
	
	public DelegatingVehicleTracker(Map<String,Point> points){
		locations=new ConcurrentHashMap<String,Point>(points);
		unmodifiableMap=Collections.unmodifiableMap(locations);
	}
	
	public Map<String,Point> getLocations(){
		//这里直接将引用返回  但是不会出现问题 因为locations是ConcurrentHashMap的   所以是
		//线程安全的   不会出现可见性问题
		return locations;
	}
	public void setLocations(String id,int x,int y){
		if(locations.replace(id, new Point(x,y))==null){
			throw new IllegalArgumentException("invaild vehicle name:"+id);
		}
	}
	
}

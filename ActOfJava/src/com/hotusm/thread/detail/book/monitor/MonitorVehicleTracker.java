package com.hotusm.thread.detail.book.monitor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 *虽然MutablePoint不是线程安全的一个类  但是我们通过JAVA监视器模式
 *将其封装到了其中 
 */
public class MonitorVehicleTracker {
	
	private final Map<String,MutablePoint> locations;
	
	private final Object mutex=new Object();
	
	public MonitorVehicleTracker(Map<String,MutablePoint> locations) {
		this.locations=deepCopy(locations);
	}
	
	public Map<String,MutablePoint> getLocations(){
		synchronized (mutex) {
			return deepCopy(this.locations);
		}
	}
	
	public MutablePoint getLocation(String id){
		synchronized (mutex) {
			MutablePoint loc=locations.get(id);
			return loc==null?null:new MutablePoint(loc);
		}
	}
	
	public void setLocation(String id,int x,int y){
		synchronized (mutex) {
			MutablePoint loc=locations.get(id);
			if(loc==null){
				throw new IllegalArgumentException();
			}
			loc.x=x;
			loc.y=y;
		}
	}
	
	/**
	 * 不能使用拷贝构造函数的原因就是说
	 * 如果那样的话  只是对象的引用  而不是对象的本身
	 * @param m
	 * @return
	 */
	public static Map<String,MutablePoint> deepCopy(Map<String,MutablePoint> m){
		Map<String,MutablePoint> result=new HashMap<String,MutablePoint>();
		for(String id:m.keySet()){
			result.put(id, new MutablePoint(m.get(id)));
		}
		
		return Collections.unmodifiableMap(result);
	}
}

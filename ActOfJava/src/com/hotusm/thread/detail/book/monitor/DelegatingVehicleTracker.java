package com.hotusm.thread.detail.book.monitor;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * ����ί�еĳ���׷��
 * 1.ע��  ����ʹ�õ���Point  Point��һ�����ɱ����  �������̰߳�ȫ��  �����������
 * ʹ�õ���MutablePoint�Ļ�  ��ô�ǲ����̰߳�ȫ��  ��Ϊ��������ÿ��Խ����޸Ķ���
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
		//����ֱ�ӽ����÷���  ���ǲ���������� ��Ϊlocations��ConcurrentHashMap��   ������
		//�̰߳�ȫ��   ������ֿɼ�������
		return locations;
	}
	public void setLocations(String id,int x,int y){
		if(locations.replace(id, new Point(x,y))==null){
			throw new IllegalArgumentException("invaild vehicle name:"+id);
		}
	}
	
}

package com.hotusm.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotusm.crawl.excel.annotation.Sheet;

public class Store {
	
	List<Customer> list=new ArrayList<Customer>();
	Map<Double,String> map=new HashMap<Double,String>();
	private static int num=0;
	
	public void addSale(String customerName,double amount){
		Customer customer = new Customer(num++,customerName);
		list.add(new Customer(num++,customerName));
		map.put(amount,customer.getName());
	}
	public String nameOfBestCustomer(){
		double max=0L;
		for(Map.Entry<Double, String> enty:map.entrySet()){
			double tmp=enty.getKey();
			if(tmp>max){
				max=tmp;
			}
		}
		return map.get(max);
	}
}

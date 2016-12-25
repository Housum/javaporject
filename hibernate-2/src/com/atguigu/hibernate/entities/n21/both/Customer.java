package com.atguigu.hibernate.entities.n21.both;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private Integer customerId;
	private String customerName;
	
	/*
	 * 1. 澹版槑闆嗗悎绫诲瀷鏃� 闇�娇鐢ㄦ帴鍙ｇ被鍨� 鍥犱负 hibernate 鍦ㄨ幏鍙�
	 * 闆嗗悎绫诲瀷鏃� 杩斿洖鐨勬槸 Hibernate 鍐呯疆鐨勯泦鍚堢被鍨� 鑰屼笉鏄�JavaSE 涓�釜鏍囧噯鐨�
	 * 闆嗗悎瀹炵幇. 
	 * 2. 闇�鎶婇泦鍚堣繘琛屽垵濮嬪寲, 鍙互闃叉鍙戠敓绌烘寚閽堝紓甯�
	 */
	private Set<Order> orders = new HashSet<>();

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	

}

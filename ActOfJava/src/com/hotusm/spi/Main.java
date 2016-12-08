package com.hotusm.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

import com.hotusm.spi.api.BusinessInterface;

public class Main {
	
	public static void main(String[] args) {
		ServiceLoader<BusinessInterface> interfaces = ServiceLoader.load(BusinessInterface.class);  
		// ����д��ԭ���ǣ�������һ��ָ������ӿڵĶ������ʵ��
		Iterator<BusinessInterface> iinterface= interfaces.iterator();  
		if (iinterface.hasNext()) {  
		    BusinessInterface interfaceItem = iinterface.next();
		    interfaceItem.say();
		}
	}
}

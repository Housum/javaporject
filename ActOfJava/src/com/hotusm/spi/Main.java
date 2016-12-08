package com.hotusm.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

import com.hotusm.spi.api.BusinessInterface;

public class Main {
	
	public static void main(String[] args) {
		ServiceLoader<BusinessInterface> interfaces = ServiceLoader.load(BusinessInterface.class);  
		// 这样写的原因是，您可以一次指定这个接口的多个具体实现
		Iterator<BusinessInterface> iinterface= interfaces.iterator();  
		if (iinterface.hasNext()) {  
		    BusinessInterface interfaceItem = iinterface.next();
		    interfaceItem.say();
		}
	}
}

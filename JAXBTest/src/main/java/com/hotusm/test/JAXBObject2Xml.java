package com.hotusm.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.hotusm.entity.Customer;

/**
 * ������ת��ΪXml
 * @author Hotusm
 *
 */
public class JAXBObject2Xml {
	
	public static void main(String[] args) {
		Customer customer = new Customer();
	      customer.setId(100);
	      customer.setName("benson");
	      customer.setAge(23);
	 
	      try {
	    	  
	    	//�����ɵ�xml�����file.xml
	        File file = new File("C:\\file.xml");
	        JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
	        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
	        // output pretty printed
	        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 
	        jaxbMarshaller.marshal(customer, file);
	        jaxbMarshaller.marshal(customer, System.out);
	 
	          } catch (JAXBException e) {
	        	  e.printStackTrace();
	          }
	}
}

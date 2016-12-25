package com.hotusm.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.hotusm.entity.Customer;
import com.hotusm.entity.DemoEntity;

/**
 * 将xml结构生成对应的对象
 * 
 * @author Hotusm
 *
 */
public class JAXBXXml2Object {
	
	public static void main(String[] args) {
		
		try {
			 File file = new File("C:\\file.xml");
		     JAXBContext jaxbContext = JAXBContext.newInstance(DemoEntity.class);
		     Unmarshaller un = jaxbContext.createUnmarshaller();
		     DemoEntity de = (DemoEntity) un.unmarshal(file);
		     System.out.println("id:"+de.getId()+" code:"+de.getCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

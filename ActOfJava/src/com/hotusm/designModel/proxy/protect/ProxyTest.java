package com.hotusm.designModel.proxy.protect;

import com.hotusm.designModel.proxy.protect.handler.NotOwnerInvocationHandler;
import com.hotusm.designModel.proxy.protect.handler.OwnerInvocationHandler;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016��10��18��   <br/>
 * @description ��������  Ϊ�˱���ĳһЩ�ķ���
 * 
 */
public class ProxyTest {

	public static void main(String[] args) {
		IPersonBean personBean=new PersonBean();
		
		IPersonBean pb =new OwnerInvocationHandler(personBean).getOwnerProxy();
			
		pb.setHotOrNotRating(1);
		
		IPersonBean pb1=new NotOwnerInvocationHandler(personBean).getOwnerProxy();
		pb1.setName("111");
		
	}

}

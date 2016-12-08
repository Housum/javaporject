package com.hotusm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * 
 * @author Hotusm
 * JDK ��̬����
 * 
 * JDK������ص���Ǳ���ʵ��һ���ӿ�
 * @see org.apache.ibatis.datasource.pooled.PooledConnection
 */
public class P1 implements P2{

	public static void main(String[] args) {
		P2 p2 = (P2) JDKProxy.getInstance(new P1());
		p2.say();
		// print :com.hotusm.proxy.P1@d98c113
		//��������Կ�������һ���������
		System.out.println(p2);
	}

	//������ִ�����������ʱ��,��ʵ������ֱ�ӵ����������,���Ǵ�����
	//����InvocationHandler�е�invoke(Object proxy, Method method,Object[] args)
	//����
	
	@Override
	public int say() {
		System.out.println("hello proxy");
		return 100;
	}
}
class JDKProxy{
	
	public static Object getInstance(final Object o){
		/**
		 * ��Ҫ������������:
		 * 1.�������
		 * 2.���������Ҫʵ�ֵĽӿ�(������Ķ���Ľӿ�)
		 * 3.������
		 */
		Object object = Proxy.newProxyInstance(P1.class.getClassLoader(), o.getClass().getInterfaces(), 
				//��������ʵ����
				new InvocationHandler(){

					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						Object result = method.invoke(o, args);
						System.out.println("result: "+result);
						return result;
					}
			
		});
		return object;
	}
}
//ʵ��JDK�ṩ�����ǵĴ�������

class MyInvocationHandler implements InvocationHandler{
	
	private Object object;
	
	public MyInvocationHandler(Object object){
		this.object=object;
	}
		
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//���÷���ǰ
		Object result = method.invoke(this.object, args);
		//���÷���֮��
		
		System.out.println("result: "+result);
		return result;
	}
	
}

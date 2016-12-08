package com.hotusm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * 
 * @author Hotusm
 * JDK 动态代理
 * 
 * JDK代理的特点就是必须实现一个接口
 * @see org.apache.ibatis.datasource.pooled.PooledConnection
 */
public class P1 implements P2{

	public static void main(String[] args) {
		P2 p2 = (P2) JDKProxy.getInstance(new P1());
		p2.say();
		// print :com.hotusm.proxy.P1@d98c113
		//从这里可以看出这是一个代理对象
		System.out.println(p2);
	}

	//在我们执行这个方法的时候,其实并不是直接调用这个方法,而是代理类
	//调用InvocationHandler中的invoke(Object proxy, Method method,Object[] args)
	//方法
	
	@Override
	public int say() {
		System.out.println("hello proxy");
		return 100;
	}
}
class JDKProxy{
	
	public static Object getInstance(final Object o){
		/**
		 * 主要传入三个参数:
		 * 1.类加载器
		 * 2.这个代理需要实现的接口(被代理的对象的接口)
		 * 3.拦截器
		 */
		Object object = Proxy.newProxyInstance(P1.class.getClassLoader(), o.getClass().getInterfaces(), 
				//采用匿名实现类
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
//实现JDK提供给我们的代理处理器

class MyInvocationHandler implements InvocationHandler{
	
	private Object object;
	
	public MyInvocationHandler(Object object){
		this.object=object;
	}
		
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//调用方法前
		Object result = method.invoke(this.object, args);
		//调用方法之后
		
		System.out.println("result: "+result);
		return result;
	}
	
}

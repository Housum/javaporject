package com.hotusm.classloader;

import java.io.InputStream;

import org.junit.Test;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016Äê10ÔÂ29ÈÕ   <br/>
 * @description
 */
public class ClassLoaderTest {
	
	@Test
	public void test() throws ClassNotFoundException{
		
		PathClassLoader classLoader=new PathClassLoader("D:/jeesite/ActOfJava/bin");
		Class<?> loadClass = classLoader.findClass("com.hotusm.classloader.ClassLoaderTest");
		System.out.println(loadClass);
	}
	
	@Test
	public void testClassPath(){
		
		ClassLoader classLoader=new ClassLoader() {
			
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
					if(!name.startsWith("com.hotusm"))
						return super.loadClass(name);
					
					
					String fileName=name.substring(name.lastIndexOf(".")+1)+".class";
					try(InputStream is=getClass().getResourceAsStream(fileName)) {
						
						if(is==null){
							super.loadClass(name);
						}
						byte[] b=new byte[is.available()];
						
						is.read(b);
						
						return defineClass(name, b, 0, b.length);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
			}
		};
		
		try {
			Object obj = classLoader.loadClass("com.hotusm.classloader.ClassLoaderTest").newInstance();
			//Method method = obj.getClass().getMethod("testMethod");
			//method.invoke(obj);
			System.out.println(obj instanceof com.hotusm.classloader.ClassLoaderTest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void testMethod(){
		System.out.println("method");
	}
}

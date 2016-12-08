package com.hotusm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016��10��28��   <br/>
 * @description �������
 * http://www.cnblogs.com/sunniest/p/4574080.html
 * 
 * JVM�������������״��νṹ
 * 		�����������(bootstrap class loader)
 * 		��չ�������(extensions class loader)
 * 		ϵͳ�������(system class loader)
 * 		�Զ����������(custom class loader)
 * 
 */
public class PathClassLoader extends ClassLoader{
	
	private String classPath;
	private String packageNames="com.hotusm";
	
	private Map<String,Class<?>> cache=new ConcurrentHashMap<>();
	
	public PathClassLoader(String classPath){
		this.classPath=classPath;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		
		if(name.startsWith(packageNames)){
			return cacheClass(name);
		}else{
			return super.findClass(name);
		}
		
	}
	
	private byte[] getData(String className){
		String path=classPath+File.separatorChar+className.replace('.', File.separatorChar)
				+".class";
		try(InputStream is=new FileInputStream(path)) {
			
			ByteArrayOutputStream stream=new ByteArrayOutputStream();
			byte[] buffer=new byte[2014];
			int num=0;
			while((num=is.read(buffer))!=-1){
				stream.write(buffer,0,num);
			}
			return stream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Class<?> cacheClass(final String name) throws ClassNotFoundException{
		Class<?> clazz = cache.get(name);
			synchronized (name) {
				if(clazz==null){
					byte[] classData = getData(name);
					if(classData==null){
						throw new ClassNotFoundException();
					}else{
						clazz = defineClass(name, classData, 0, classData.length);
						cache.put(name, clazz);
					}
				}
			}
		return clazz;
	}
}

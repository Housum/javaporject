package com.hotusm.jmx.test;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

public class StandardAgent {
	private MBeanServer mBeanServer = null;  
    public StandardAgent(){  
    	//创建一个Factory
        mBeanServer = MBeanServerFactory.newMBeanServer();  
    }  
      
    public MBeanServer getMBeanServer(){  
        return mBeanServer;  
    }  
      
    public ObjectName createObjectName(String name){  
        
    	ObjectName objectName = null;  
        try{ 
        	//穿件一个ObjectName,使用全限定名
            objectName = new ObjectName(name);  
        }catch(Exception e){ 
        	System.out.println("create error");
        }  
        return objectName;  
    }  
    private void createStandardBean(ObjectName objectName, String managedResourceClassName){  
        try{
        	//创建 MBean，并将其注册到 MBeanServer
            mBeanServer.createMBean(managedResourceClassName, objectName);  
        }catch(Exception e){}  
    }  
      
    public static  void main(String[] args){  
        StandardAgent agent = new StandardAgent();  
        MBeanServer mBeanServer = agent.getMBeanServer();  
        String domain = mBeanServer.getDefaultDomain();  
        String managedResourceClassName = "com.hotusm.jmx.entity.Car";  
        ObjectName objectName = agent.createObjectName(domain + ":type=" + managedResourceClassName);  
        System.out.println("objectName: " + objectName);  
        agent.createStandardBean(objectName, managedResourceClassName);  
          
        try{
        	//代表了MBean的一个属性
            Attribute colorAttribute = new Attribute("Color", "blue");  
            //设置属性
            mBeanServer.setAttribute(objectName, colorAttribute);  
            System.out.println(mBeanServer.getAttribute(objectName, "Color"));  
            //代理方法
            mBeanServer.invoke(objectName, "drive", null, null);  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
}

package com.hotusm.jmx.test;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

public class StandardAgent {
	private MBeanServer mBeanServer = null;  
    public StandardAgent(){  
    	//����һ��Factory
        mBeanServer = MBeanServerFactory.newMBeanServer();  
    }  
      
    public MBeanServer getMBeanServer(){  
        return mBeanServer;  
    }  
      
    public ObjectName createObjectName(String name){  
        
    	ObjectName objectName = null;  
        try{ 
        	//����һ��ObjectName,ʹ��ȫ�޶���
            objectName = new ObjectName(name);  
        }catch(Exception e){ 
        	System.out.println("create error");
        }  
        return objectName;  
    }  
    private void createStandardBean(ObjectName objectName, String managedResourceClassName){  
        try{
        	//���� MBean��������ע�ᵽ MBeanServer
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
        	//������MBean��һ������
            Attribute colorAttribute = new Attribute("Color", "blue");  
            //��������
            mBeanServer.setAttribute(objectName, colorAttribute);  
            System.out.println(mBeanServer.getAttribute(objectName, "Color"));  
            //������
            mBeanServer.invoke(objectName, "drive", null, null);  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
}

package com.hotusm.third.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

public class RemoteUnicastMain {
	
	private static final Log LOGGER = LogFactory.getLog(RemoteUnicastMain.class);
	
	static {
        BasicConfigurator.configure();
    }
	
    public static void main(String[] args) {
		try {
			/**
			 * Locate registry������������RMI����ע���������RMI����λ�òֿ⡣
	         * ��Ҫ��������ά��һ�������������ṩRMI������������λ�á���
	         * ÿһ�������RMI�����ṩ�ߣ����ὲ�Լ���Stubע�ᵽLocate registry�У��Ա�ʾ�Լ��������ṩ����
	         * 
	         * �����ַ�ʽ���Թ���Locate registry��һ����ͨ������ϵͳ������������ע���
	         * ��һ�����ڴ�����ʹ��LocateRegistry�ࡣ
	         * 
	         * LocateRegistry������һ��createRegistry��������������̨������ϴ���һ��������RMIע���
			 * 
			 */
			LocateRegistry.createRegistry(1099);
			 // ��������LocateRegistryע�ᣨ��/�ذ󶨣�RMI Serverʵ�֡�
			RemoteUnicastServiceImpl remote=new RemoteUnicastServiceImpl();
			 // ͨ��java ���ַ����������Խ������RMI Serverʵ�ְ�һ������·����ע�ᵽLocateRegistry��
			//Naming.rebind("rmi://127.0.0.1/fetchDtos", remote);
		      /*
	         * �ڡ��Ѿ�ӵ��ĳ���ɷ��ʵ�Զ��RMIע���������¡�
	         * ���������������Զ��ע���ע��RMI Server��
	         * ��ȻԶ��RMIע����JVM-classpath��һ��Ҫ�����Server��Stub����
	         *  
	         * ������������һ��JVM�ϵ�RMIע���������ͬһ̨�����Ҳ���ܲ���ͬһ̨�������
	         * Naming.rebind("rmi://192.168.61.1:1099/queryAllUserinfo", remoteService);
	         * */
		} catch (Exception e) {
			LOGGER.error("register RMI error", e);
			e.printStackTrace();
		}
	}
}

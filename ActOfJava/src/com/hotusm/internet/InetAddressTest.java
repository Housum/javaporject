package com.hotusm.internet;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.junit.Test;

public class InetAddressTest{
	
	/**
	 * InetAddress�������IP��������
	 */
	@Test
	public void getByName(){
		try {
			InetAddress address=InetAddress.getByName("mobiledev.yonyou.com");
			System.out.println(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void localHost() throws UnknownHostException{
		InetAddress inetAddress = InetAddress.getLocalHost();
		String hostName = inetAddress.getHostName();
		String hostAddress = inetAddress.getHostAddress();
		System.out.println("hostName:"+hostName+" hostAddress:"+hostAddress+" inetAddress:"+inetAddress);
	}
	
	/**
	 * 
	 * NetworkInterface���������·�ӿ�
	 */
	@Test
	public void networkInterface() throws Exception{
		InetAddress inetAddress = InetAddress.getLocalHost();
		NetworkInterface networkInterface=NetworkInterface.getByInetAddress(inetAddress);
		System.out.println(networkInterface);
	}
	
	/**
	 * ��ѯ���ص���������ӿ�
	 */
	@Test
	public void allNetworkInterface() throws SocketException{
		Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
		while(networks.hasMoreElements()){
			System.out.println(networks.nextElement());
		}
	}
}

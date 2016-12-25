package com.hotusm.third.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
/**
 * 
 *Test for JAVA RMI
 *@link http://blog.csdn.net/yinwenjie/article/details/49120813
 *��ע��RemoteUnicastServiceImpl�Ķ��壬���̳���UnicastRemoteObject��
 *һ����˵RMI Server��ʵ�ֿ��Լ̳����ָ��ࣺUnicastRemoteObject��Activatable����ƪ���¾ͻὲ��Activatable����
 *
 */
public interface RemoteServiceInterface extends Remote{
	/**
	 * 
	 * @return
	 * @throws RemoteException  must throw this exception
	 * 
	 */
	public List<DTO> fetchDtos() throws RemoteException;
}

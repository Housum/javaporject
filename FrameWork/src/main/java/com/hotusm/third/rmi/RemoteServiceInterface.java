package com.hotusm.third.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
/**
 * 
 *Test for JAVA RMI
 *@link http://blog.csdn.net/yinwenjie/article/details/49120813
 *请注意RemoteUnicastServiceImpl的定义，它继承了UnicastRemoteObject。
 *一般来说RMI Server的实现可以继承两种父类：UnicastRemoteObject和Activatable（下篇文章就会讲到Activatable）。
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

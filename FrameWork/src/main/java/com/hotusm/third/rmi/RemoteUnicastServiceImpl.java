package com.hotusm.third.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RemoteUnicastServiceImpl extends UnicastRemoteObject implements RemoteServiceInterface {
	
	private static final long serialVersionUID = 1L;
	private static List<DTO> lists=new ArrayList<DTO>();
	
	/**
	 * initalize capacity
	 * 
	 */
	static{
		lists.add(new DTO("1","h1"));
		lists.add(new DTO("2","h2"));
		lists.add(new DTO("3","h3"));
		lists.add(new DTO("4","h4"));
		lists.add(new DTO("5","h5"));
	}
	/**
	 * 
	 * @throws RemoteException
	 * attention must have constructor
	 * 
	 */
	protected RemoteUnicastServiceImpl() throws RemoteException {
		super();
	}
	
	public List<DTO> fetchDtos() throws RemoteException{
		return lists;
	}

}

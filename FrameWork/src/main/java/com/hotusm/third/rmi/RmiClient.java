package com.hotusm.third.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class RmiClient {
	
	public static void main(String[] args) {
		 try {
			RemoteServiceInterface remote = (RemoteServiceInterface)Naming.lookup("rmi://10.1.193.144:1099/fetchDtos");
			List<DTO> fetchDtos = remote.fetchDtos();
			System.out.println(fetchDtos);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}

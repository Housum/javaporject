package com.hotusm.Serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableTest {
	
	public static void main(String[] args) throws IOException {
		NeedSerializable needSerializable=new NeedSerializable();
		needSerializable.setAddress("±±¾©");
		needSerializable.setUsername("hotusm");
		needSerializable.setPassword("1234");
		ObjectOutput objectOutput=new ObjectOutputStream(new FileOutputStream("test"));
		objectOutput.writeObject(needSerializable);
		
		ObjectInput input=new ObjectInputStream(new FileInputStream("test"));
		try {
			NeedSerializable readObject = (NeedSerializable) input.readObject();
			
			System.out.println(readObject.getPassword()+" "+readObject.getUsername()+" "+readObject.getAddress());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static class Father{

		private String address;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
		
		
	}
	
	public static class NeedSerializable extends Father implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public String username;
		public transient String password;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
	}
}

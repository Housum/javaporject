package serial.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;



/**
 * 
 * ��������л��Լ������л�
 * 1.transient�ؼ������ε��򲻲μ����л�
 * 2.serialVersionUID��������˵��֤�����Ƿ���Խ��з����л�
 * �����ͬ�Ļ� ��ô�ǲ����Խ������л�.�ٻ���������ǿ����Զ���serialVersionUID
 * �ﵽֻ��Ȩ�޵��˲������л����ǵ���Դ��ͬʱ������ͬ����ŵ�������.�����л��ķ����Լ��ֶ�
 * ����Ҳ����Ӱ�쵽���ķ����л�
 * 
 */
import serial.entity.Customer;
import serial.entity.Customer.Sex;

public class Main {

	public static void main(String[] args) {
		new Main().serializeObject();
	}
	
	
	//������������л�
	public void serializeObject(){
		ObjectOutputStream out=null;
		try {
			out=new ObjectOutputStream(new FileOutputStream(new File("D://customer.data")));
			Customer c=new Customer();
			c.setAge(30);
			c.setCustomeCost(100F);
			c.setName("Hotusm");
			c.setSex(Customer.Sex.MAN);
			Customer.CATEGORY="mayor";
			out.writeObject(c);
			Customer.serializeStaticState(out);
			System.out.println("���л�����ɹ�...");
		}catch (Exception e) {
			System.out.println("���л��������...");
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	//��һ�����з����л�һ������
	public void deserializeObject(){
		ObjectInputStream in=null;
		try {
			in=new ObjectInputStream(new FileInputStream(new File("D://customer.data")));
			Customer ob = (Customer) in.readObject();
			Customer.deserializeStaticState(in);
			System.out.println(ob.getCustomeCost());
			Sex sex = ob.getSex();
			
			System.out.println(ob.getCATEGORY());
			System.out.println(sex);
			System.out.println("�����л��ɹ� "+ob.getClass().getName());
		} catch(Exception e) {
			System.out.println("�����л����ִ���");
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

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
 * 对象的序列化以及反序列化
 * 1.transient关键字修饰的域不参加序列化
 * 2.serialVersionUID的作用是说验证数据是否可以进行反序列化
 * 如果不同的话 那么是不可以进行序列化.①基于这个我们可以自定义serialVersionUID
 * 达到只有权限的人才能序列化我们的资源②同时具有相同的序号的两个类.反序列化的方法以及字段
 * 增加也不会影响到它的反序列化
 * 
 */
import serial.entity.Customer;
import serial.entity.Customer.Sex;

public class Main {

	public static void main(String[] args) {
		new Main().serializeObject();
	}
	
	
	//将对象进行序列化
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
			System.out.println("序列化对象成功...");
		}catch (Exception e) {
			System.out.println("序列化对象出错...");
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
	//从一个流中反序列化一个对象
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
			System.out.println("发序列化成功 "+ob.getClass().getName());
		} catch(Exception e) {
			System.out.println("发序列化出现错误");
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

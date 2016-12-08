package serial.entity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Customer implements Serializable{

	private static final long serialVersionUID = 1253153223890085616L;
	
	private String name;
	private Integer age;
	private Sex sex;
	/**
	 * 标记了这个的话  在序列化的时候  这个字段不会别序列化 
	 */
	private transient String VIPCode;
	
	private transient String describe;
	//对于static字段的  不能够进行序列化  需要自己添加 serializeStaticState
	//deserializeStaticState进行序列化和反序列化
	public static String CATEGORY="simple";
	
	public enum Sex{
		MAN,WOAMN
	}
	
	private double customeCost;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getDescribe() {
		if(this.age>30){
			describe="old";
		}else{
			describe="young";
		}
		return describe;
	}
	public double getCustomeCost() {
		return customeCost;
	}

	public void setCustomeCost(double customeCost) {
		this.customeCost = customeCost;
	}
	
	public static String getCATEGORY() {
		return CATEGORY;
	}

	/**
	 * 反序列化的时候，默认会为对象创建这样的一个方法，进行反序列化，
	 * 我们可以在反序列的时候在这里进行一些操作
	 * 
	 * @return
	 */
	/*
	public Object readResolve(){
		System.out.println("正在反序列化对象...");
		return new Customer();
	}*/
	
	public String getVIPCode() {
		return VIPCode;
	}

	public void setVIPCode(String vIPCode) {
		VIPCode = vIPCode;
	}
	/**
	 *在序列化的时候   ObjectOutputStream的时候  会检查继承了Serializable接口的对象.如果
	 *实现了自己的writeObject或者readObject,那么会跳过正常的序列化并调用对象实现的方法
	 *在这里面也可以调用默认的方法进行默认的序列化操作
	 *
	 * @param oos
	 */
	private void writeObject(ObjectOutputStream oos){
		System.out.println(111);
		try {
			oos.defaultWriteObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void readObject(ObjectInputStream ois){
		System.out.println(222);
		try {
			ois.defaultReadObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	//-----------------------------------
	//对于静态的字段并不能够进行序列化  需要我们自己操作
	//记住  这个是必须自己手动调用的  方法名称可以自定义  而且存储的位置和取出的位置是一样的
	public static void serializeStaticState(ObjectOutputStream oos){
		try {
			oos.writeUTF(CATEGORY);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void deserializeStaticState(ObjectInputStream ois){
		try {
			CATEGORY = ois.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

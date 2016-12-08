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
	 * ���������Ļ�  �����л���ʱ��  ����ֶβ�������л� 
	 */
	private transient String VIPCode;
	
	private transient String describe;
	//����static�ֶε�  ���ܹ��������л�  ��Ҫ�Լ���� serializeStaticState
	//deserializeStaticState�������л��ͷ����л�
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
	 * �����л���ʱ��Ĭ�ϻ�Ϊ���󴴽�������һ�����������з����л���
	 * ���ǿ����ڷ����е�ʱ�����������һЩ����
	 * 
	 * @return
	 */
	/*
	public Object readResolve(){
		System.out.println("���ڷ����л�����...");
		return new Customer();
	}*/
	
	public String getVIPCode() {
		return VIPCode;
	}

	public void setVIPCode(String vIPCode) {
		VIPCode = vIPCode;
	}
	/**
	 *�����л���ʱ��   ObjectOutputStream��ʱ��  ����̳���Serializable�ӿڵĶ���.���
	 *ʵ�����Լ���writeObject����readObject,��ô���������������л������ö���ʵ�ֵķ���
	 *��������Ҳ���Ե���Ĭ�ϵķ�������Ĭ�ϵ����л�����
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
	//���ھ�̬���ֶβ����ܹ��������л�  ��Ҫ�����Լ�����
	//��ס  ����Ǳ����Լ��ֶ����õ�  �������ƿ����Զ���  ���Ҵ洢��λ�ú�ȡ����λ����һ����
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

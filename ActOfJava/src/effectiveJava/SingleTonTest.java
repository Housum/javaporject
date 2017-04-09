package effectiveJava;

import java.io.Serializable;

/**
 * ʹ��˽�й���������ö������ǿ��singleton����
 * @author Hotusm
 *
 *���ѡ�񷽰�:
 *@see ElvisInstance
 */

public class SingleTonTest {
	
	public static void main(String[] args) {
		ElvisInstance instance = ElvisInstance.INSTANCE;
		instance.dance();
	}
}
/**
 * 
 * @author Hotusm
 * ��һ�ֲ��õ��ǹ�����ķ��������ҽ����캯������Ϊ˽�е�
 */
class Elvis{
	public static final Elvis INSTANCE=new Elvis();
	private Elvis(){}
	public void dance(){
		
	}
}

/**
 * 1.�ڶ����ǲ��ù�����ķ�ʽ
 * �Ƽ�ʹ�õڶ��ַ�ʽ
 * @author Hotusm
 * 2.���ڵ���������˵�������̳�Serializable�ǲ��ܹ�ʵ�����л���
 * ������Ҫ��ʵ�����Ķ�������Ϊtransient�������ṩһ��readResolve�ķ���
 * ���û������Ĳ�������ô�ڷ����л� ��ʱ��ᴴ��һ���µĶ��󣬵��¡���ð��
 * ������һ������
 */
class Elvis1 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static final transient Elvis1 INSTANCE=new Elvis1();
	private Elvis1(){}
	public Elvis1 getInstance(){
		return INSTANCE;
	}
	/**
	 * �����е�ʱ�������������
	 * @return
	 */
	private Object readResolve(){
		return INSTANCE;
	}
	

	
}
/**
 *�����ǵ����ֵ���ʵ�ֵķ���
 *�����˵�Ԫ�ص�ö�����ͣ����ַ�ʽ���Ժܺõı�����ʵ���������Ҷ������л���˵�Ǻ��кô���
 * 
 */
enum ElvisInstance{
	INSTANCE;
	public void dance(){}
}







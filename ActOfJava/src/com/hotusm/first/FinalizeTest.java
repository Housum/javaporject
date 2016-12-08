package com.hotusm.first;

/***
 * 
 * @author Hotusm
 * 1.����gc
 * 2.���Ծ�̬�����͹��캯����ִ��˳��
 */
public class FinalizeTest {
	
	static int i;
	static boolean flag=true;

	static{
		System.out.println("static block");
	}
	
	public FinalizeTest() {
		System.out.println("initialization");
	}
	@Override
	protected void finalize() throws Throwable {
		
		flag=false;
		System.out.println("��ʼ�����:"+i++);
		super.finalize();
	}
	/***
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/*1.����gc,������������֮��,���ǽ�����������(�������finalize()����)*/
//		while(flag){
//			new FinalizeTest();
//		}
		
		/*2.���Ծ�̬�����͹��ź�����ִ��˳��*/
		//new FinalizeTest();
	}
	
}

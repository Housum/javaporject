package com.hotusm.first;
/**
 * 
 * @author Hotusm
 * 1.���ʿ���Ȩ�޲���:
 * ��public ��protected �۰����ʿ���Ȩ��(package,û�йؼ��ֻ���friendly) ��private
 */
public class VisitControl {
	/*2.Ĭ�ϵķ���Ȩ��
	 * ֻ��ͬһ�����ڵ�����з���Ȩ��
	 * */
	int i;
	/*3.protected 
	 * ��ͬ�İ����Ǿ��з���Ȩ�޵�,�����ڲ�ͬ����ʱ����û��
	 * ����Ȩ�޵�.
	 * 1.��private������������̳е�ʱ���ܹ��̳�protected��
	 * ���ǲ��ܼ̳�private��.
	 * 2.��Ĭ�ϵķ���Ȩ�޲�ͬ������Ĭ�Ϸ���Ȩ�޵�ֻ��ͬһ����������Է���,
	 * ���Ƕ��ڲ�ͬ���������Ͳ��ܹ�ֱ�ӷ�����,��ô�ڲ�ͬ�����������Ͳ��ܹ�
	 * �̳�һЩ��������ԣ�����protectedȴ��������
	 * 3.protected��õĳ����Ƕ���ĳ������˵,���ĳЩ����������������,
	 * ��ϣ�����ڵ�������˵�ǿɼ���
	 * */
	protected int j;
	/*4.private 
	 * ֻ�б�����ڲ����з���Ȩ��
	 * */
	private int k;
	/*5.public
	 * ���е��඼���з��ʵ�Ȩ��
	 * */
	public int m;
	
	public void testPublic(){
		System.out.println("testPublic...");
	}
	void testControll(){
		System.out.println("Ĭ�ϵķ���Ȩ��...");
	}
	protected void testProtected(){
		System.out.println("testProtected...");
	}
	private void testPrivate(){
		System.out.println("testPrivate...");
	}
	public static void main(String[] args) {
		char[] aa=new char[50];
		for(int i=65;i<97;i++){
			aa[i-65]=(char)i;
		}
		AAA a=new AAA(aa);
		System.out.println(a.charAt(0));
	}

}
/*
 *�����������˵��private�޸��ǵ�ֻ��
 *�ڲ��ܹ�����,ע��ڶ������캯��,��ȻA�Ǵ�������
 *����,������Ϊ�Ǳ���,�����ܹ�����private 
 * */
class AAA implements CharSequence{
	private final char[] value;
	
	public AAA(){
		value=new char[0];
	}
	/*
	 *ע������ 
	 */
	public AAA(AAA a) {
		this.value=a.value;
	}
	public AAA(char[] values){
		this.value=values;
	}
	
	@Override
	public int length() {
		return value.length;
	}

	@Override
	public char charAt(int index) {
		if(index<0||index>=value.length){
			return ' ';
		}
		return value[index];
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return null;
	}
	
	
}

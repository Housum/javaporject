package com.hotusm.first;
/**
 * 
 * @author Hotusm
 * 1.访问控制权限测试:
 * ①public ②protected ③包访问控制权限(package,没有关键字或者friendly) ④private
 */
public class VisitControl {
	/*2.默认的访问权限
	 * 只有同一个包内的类具有访问权限
	 * */
	int i;
	/*3.protected 
	 * 相同的包内是具有访问权限的,但是在不同包的时候是没有
	 * 访问权限的.
	 * 1.和private区别在于子类继承的时候能够继承protected的
	 * 但是不能继承private的.
	 * 2.和默认的访问权限不同点在于默认访问权限的只有同一个包下面可以访问,
	 * 但是对于不同包下面的类就不能够直接访问了,那么在不同包下面的子类就不能够
	 * 继承一些不许的属性，但是protected却可以做到
	 * 3.protected最常用的场景是对于某个类来说,想把某些事物对外界隐藏起来,
	 * 但希望对于导出类来说是可见的
	 * */
	protected int j;
	/*4.private 
	 * 只有本类的内部具有访问权限
	 * */
	private int k;
	/*5.public
	 * 所有的类都具有访问的权限
	 * */
	public int m;
	
	public void testPublic(){
		System.out.println("testPublic...");
	}
	void testControll(){
		System.out.println("默认的访问权限...");
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
 *这个例子用来说明private修改是的只在
 *内部能够访问,注意第二个构造函数,虽然A是传进来的
 *参数,但是因为是本类,所以能够访问private 
 * */
class AAA implements CharSequence{
	private final char[] value;
	
	public AAA(){
		value=new char[0];
	}
	/*
	 *注意这里 
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

package com.hotusm.GenericTest;

/**
 * 
 * @author Hotusm
 * 使用泛型实现元组,元组:仅一次方法调用可以返回对个对象(
 * 将一组对象直接打包储存于其中的一个单一对象
 * )
 * 
 * @since 2016-03-06
 * 
 */
public class TwoTuple<A,B>{
	
	public final A first;
	public final B second;
	public TwoTuple(A first, B second) {
		super();
		this.first = first;
		this.second = second;
	}
	
	@Override
	public String toString() {
		return "("+first+" "+second+")";
	}
	
	public static void main(String[] args) {
		TwoTuple<String,Integer> s=new TwoTuple<String, Integer>("one two three", 1234);
		s.toString();
	}
}
/**
 * 三个元组的形式,直接继承了二元祖,直接在原有的泛型基础上添加一个泛型
 * @author Hotusm
 *
 * @param <A>
 * @param <B>
 * @param <C>
 */
class ThreeTuple<A,B,C> extends TwoTuple<A, B>{
	public final C three;
	public ThreeTuple(A first, B second,C three){
		super(first, second);
		this.three=three;	
	}
}
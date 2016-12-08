package com.hotusm.GenericTest;

/**
 * 
 * @author Hotusm
 * ʹ�÷���ʵ��Ԫ��,Ԫ��:��һ�η������ÿ��Է��ضԸ�����(
 * ��һ�����ֱ�Ӵ�����������е�һ����һ����
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
 * ����Ԫ�����ʽ,ֱ�Ӽ̳��˶�Ԫ��,ֱ����ԭ�еķ��ͻ��������һ������
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
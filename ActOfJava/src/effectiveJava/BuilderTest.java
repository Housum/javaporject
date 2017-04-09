package effectiveJava;
/**
 * ��������ʹ��
 * 
 * 
 * ��һ����Ĺ��캯���ж��������ʱ��.���ʱ��������������
 * 1.�ܶ������ʱ���п��ܻ���ֿͻ��˳���Ա��������˳��������������ʱ��ͻ�
 * ��������Ԥ�ϵĴ���
 * 2.����Щ����Ĳ�����һ�����ǲ���Ҫ�ģ����ʱ�����ǵ�һ�������������ص�������ģʽ
 * ��������ģʽ������һ��������ģ����кܶ�Ĳ�����ʱ�򣬻��Ǻ��ѱ�д��
 * 3.����ǰ����������ǿ���ʹ�ù������ķ�ʽ:
 * ����ľ��ǹ�������ʽ,��.���ַ�ʽ���Խ������������ŵ�,��.ͬʱҲ�����ڳ�ʼ����ʱ����в�����֤
 * �ۺ͹��캯�����,�������Ĳ����ǿɱ�ģ����Ҵ����ķ�ʽҲ�Ƚϼ򵥣�������ִ���
 * 
 * 
 * @author Hotusm
 *
 */
public class BuilderTest {

	public static void main(String[] args) {
		RealClass rc=new RealClass.Builder(10, 10).calories(1).carbohydrate(1).build();
		int count = rc.count();
		System.out.println(count);
	}
}

class RealClass{
	
	private final int servingSize;
	private final int serving;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;
	
	
	public static class Builder{
		private final int servingSize;
		private final int serving;
		
		private int calories=0;
		private int fat=0;
		private int sodium=0;
		private int carbohydrate=0;
		
		public Builder(int servingSize,int serving){
			this.servingSize=servingSize;
			this.serving=serving;
		}
		public Builder calories(int val){
			this.calories=val;
			return this;
		}
		public Builder fat(int val){
			this.fat=val;
			return this;
		}
		public Builder sodium(int val){
			this.sodium=val;
			return this;
		}
		public Builder carbohydrate(int val){
			this.carbohydrate=val;
			return this;
		}
		
		public RealClass build(){
			
			return new RealClass(this);
		}
	}
	
	private RealClass(Builder builder){
		//�ڶ������ж�����builder�жԲ������м���
		if(this.calories<=0){
			throw new IllegalStateException("��������");
		}
		this.servingSize=builder.servingSize;
		this.serving=builder.serving;
		this.calories=builder.calories;
		this.fat=builder.fat;
		this.sodium=builder.sodium;
		this.carbohydrate=builder.carbohydrate;
		
	}
	
	public int count(){
		
		return servingSize+serving+calories+fat+sodium+carbohydrate;
	}
}










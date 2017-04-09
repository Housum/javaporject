package effectiveJava;
/**
 * 构建器的使用
 * 
 * 
 * 当一个类的构造函数有多个参数的时候.这个时候最大的隐患在于
 * 1.很多参数的时候，有可能会出现客户端程序员将参数的顺序给混淆掉，这个时候就会
 * 出现难以预料的错误
 * 2.当这些构造的参数有一部分是不需要的，这个时候，我们的一般做法是利用重叠构造器模式
 * 但是这种模式还是有一定的问题的，当有很多的参数的时候，还是很难编写的
 * 3.基于前面的问题我们可以使用构建器的方式:
 * 下面的就是构建器方式,①.这种方式可以结合上面的两种优点,②.同时也可以在初始化的时候进行参数验证
 * ③和构造函数相比,构建器的参数是可变的，并且创建的方式也比较简单，不会出现错误
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
		//在对象域中而不是builder中对参数进行检验
		if(this.calories<=0){
			throw new IllegalStateException("参数错误");
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










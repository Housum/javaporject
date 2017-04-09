package effectiveJava;

import java.io.Serializable;

/**
 * 使用私有构造器或者枚举类型强化singleton属性
 * @author Hotusm
 *
 *最佳选择方案:
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
 * 第一种采用的是公共域的方法，并且将构造函数设置为私有的
 */
class Elvis{
	public static final Elvis INSTANCE=new Elvis();
	private Elvis(){}
	public void dance(){
		
	}
}

/**
 * 1.第二种是采用共有域的方式
 * 推荐使用第二种方式
 * @author Hotusm
 * 2.对于单例的类来说，仅仅继承Serializable是不能够实现序列化的
 * 我们需要将实例化的对象设置为transient，并且提供一个readResolve的方法
 * 如果没有上面的操作。那么在反序列化 的时候会创建一个新的对象，导致‘假冒’
 * 下面是一个例子
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
	 * 反序列的时候会调用这个方法
	 * @return
	 */
	private Object readResolve(){
		return INSTANCE;
	}
	

	
}
/**
 *下面是第三种单例实现的方法
 *采用了单元素的枚举类型，这种方式可以很好的避免多次实例化，并且对于序列化来说是很有好处的
 * 
 */
enum ElvisInstance{
	INSTANCE;
	public void dance(){}
}







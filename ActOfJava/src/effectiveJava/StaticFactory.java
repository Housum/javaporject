package effectiveJava;
/**
 * 
 * 
 * @author Hotusm
 * 创建实例的时候,首先想到的是
 * 采用静态工厂的方法,而不是使用构造器的方式
 * 1.@see com.hotusm.interfaceT.InterfaceTest
 * 上面的就是一个例子,这个采用了静态方法的思想,这样返回的是一个接口类型，对于客户端程序员来说
 * 并不知道具体的实现是什么,这样的好处在于，客户端程序员不用关心接口的具体实现,而静态工厂方法会
 * 自动的进行适配，返回不用的示例
 * 2.第二个使用到静态工厂方法的是服务提供者框架
 * 
 */
public class StaticFactory {
	
}

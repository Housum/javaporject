package hotusm.test;

public class BasicTest {
	
	@SuppressWarnings("rawtypes")
	public static void loader(){
		Class clazz=String.class;
		try {
			clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	//@SuppressWarnings("rawtypes")  SuppressWarnings不能再静态初始化之前
	static{
		Class clazz=String.class;
	}
}

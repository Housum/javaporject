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
	
	//@SuppressWarnings("rawtypes")  SuppressWarnings�����پ�̬��ʼ��֮ǰ
	static{
		Class clazz=String.class;
	}
}

package effectiveJava;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

import org.junit.Test;
/**
 * �����Щ����Ҫ�Ķ���  
 * 
 * @author Hotusm
 *
 */
public class WeakObject {
	

	@Test
	public void testLinkedHashMap(){
		LinkedHashMap<String, Integer> map=new LinkedHashMap<String, Integer>();
		map.put("first", 1);
		map.put("second", 2);
		//��LinkedHashMap<K,V>����һ��removeEldestEntry()����,���ǿ���
		//ʹ���������  ����ʹ�õĻ���������  ������ʹÿһ�õĶ���ռ���ڴ�
	}
	
	/*
	 * �����map�д洢�ļ���������  ������Щû�����õĻ����ϵı�������������
	 */
	public void testWeakHashMap(){
		WeakHashMap<String,Integer> map=new WeakHashMap<String,Integer>();
	}
}

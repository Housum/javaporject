package com.hotusm;

import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

import org.junit.Test;

/**
 * 
 *Javassist��һ���ֽ��빤��  
 *���Զ�̬���޸�����ֽ���
 *
 */
public class Main {
	
	/**
	 * ��̬�ĸı�һ����������ֽ���
	 */
	@Test
	public void testJavassist() throws Exception{
		//ClassPool��Ӧ�ľ���������ʱ�ĳ�   ά���˶����һ����ϣ��
		ClassPool pool = ClassPool.getDefault();
		//get��ȡһ����  �ͷ����ʹ�������Ƶ�
		//ctClass ������  �ͷ����Class����  ���Ƕ���һЩ����  ���ӷ���   �ֶεȵ�  ct->compile time
		CtClass cc = pool.get("com.hotusm.Entity");
		//��Entity�ĸ����ΪSuperclass
		cc.setSuperclass(pool.get("com.hotusm.Superclass"));
		//���ӷ���  
		CtMethod method = CtNewMethod.make("public void say(){System.out.println(\"hello Javassist\");}", cc);
		cc.addMethod(method);
		//1.��CtClassת��Ϊ���ļ� ��д�뵽����
		//cc.writeFile();
		//2.��ȡ����ֽ���
		//byte[] bytecode = cc.toBytecode();
		//3.��Ҫ��ǰ���߳�������Ctclass��������ļ�  ���ҷ���һ������
		Class<?> c1 = cc.toClass();
		
		//���÷���ķ�ʽ  ����ʹ�����ǸղŶ�̬����ķ���  
		Method m1 = c1.getMethod("say");
		Object object = c1.newInstance();
		m1.invoke(object);
		
	}
	/**
	 * ��̬�Ĵ���һ������������µĽӿ�
	 * @throws Exception 
	 */
	@Test
	public void make() throws Exception{
		ClassPool pool = ClassPool.getDefault();
		//����һ����  ����ָ������������    ��������ӿ��Ժܼ򵥵Ŀ�����  �������������ֿ���
		CtClass cc = pool.makeClass("com.hotusm.Make");
		//Class<?> c1 = cc.toClass();
		//Package p = c1.getPackage();
		//System.out.println(p.getName());
		//����һ������   �������Ǳ�����������еĲ���  �����ﶼ������
		CtMethod method = CtNewMethod.make("public void say(){System.out.println(\"hello Javassist\");}", cc);
		cc.addMethod(method);
		//����һ���ֶ�
		CtClass type = pool.get("java.lang.String");//�ֶε�����
		CtField field=new CtField(type, "name", cc);
		cc.addField(field);
		
		//����һ���ӿ�
		pool.makeInterface("com.hotusm.InterfaceVo");
		//��������  ��Ϊ���ﴴ���ķ������ǳ����     
		//CtNewMethod.abstractMethod(Void.class, "hello", null, null, cc);
		
	}
	
	/**
	 * use variable in Javaassist 
	 * @throws Exception 
	 * 
	 * 
	 *$0, $1, $2, ...	this and actual parameters
	 *$args				An array of parameters. The type of $args is Object[].
	 *$$				All actual parameters.For example, m($$) is equivalent to m($1,$2,...)
	 *$cflow(...)		cflow variable
	 *$r				The result type. It is used in a cast expression.
	 *$w				The wrapper type. It is used in a cast expression.
	 *$_				The resulting value
	 *$sig				An array of java.lang.Class objects representing the formal parameter types
	 *$type				A java.lang.Class object representing the formal result type.
	 *$class			A java.lang.Class object representing the class currently edited.
	@link http://wsmajunfeng.iteye.com/blog/1912983
	 */
	@Test
	public void variable() throws Exception{
		//ClassPool��Ӧ�ľ���������ʱ�ĳ�   ά���˶����һ����ϣ��
		ClassPool pool = ClassPool.getDefault();
		//get��ȡһ����  �ͷ����ʹ�������Ƶ�
		//ctClass ������  �ͷ����Class����  ���Ƕ���һЩ����  ���ӷ���   �ֶεȵ�  ct->compile time
		CtClass cc = pool.get("com.hotusm.Entity");
		//��Entity�ĸ����ΪSuperclass
		cc.setSuperclass(pool.get("com.hotusm.Superclass"));
		//���ӷ���  
		CtMethod method = CtNewMethod.make("public void say(String str){System.out.println(\"hello Javassist\");}", cc);
		/**
		 * use $x represent stack variable 
		 * 
		 */
		method.insertBefore("System.out.println($1);");
		cc.addMethod(method);
		
		//1.��CtClassת��Ϊ���ļ� ��д�뵽����
		//cc.writeFile();
		//2.��ȡ����ֽ���
		//byte[] bytecode = cc.toBytecode();
		//3.��Ҫ��ǰ���߳�������Ctclass��������ļ�  ���ҷ���һ������
		Class<?> c1 = cc.toClass();
		
		//���÷���ķ�ʽ  ����ʹ�����ǸղŶ�̬����ķ���  
		Method m1 = c1.getMethod("say",new Class[]{String.class});
		Object object = c1.newInstance();
		m1.invoke(object,"hello ");
		
	}
	
}

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
 *Javassist是一个字节码工具  
 *可以动态的修改类的字节码
 *
 */
public class Main {
	
	/**
	 * 动态的改变一个已有类的字节码
	 */
	@Test
	public void testJavassist() throws Exception{
		//ClassPool对应的就是类运行时的池   维护了对象的一个哈希表
		ClassPool pool = ClassPool.getDefault();
		//get获取一个类  和反射的使用是相似的
		//ctClass 代表类  和反射的Class相似  但是多了一些功能  增加方法   字段等等  ct->compile time
		CtClass cc = pool.get("com.hotusm.Entity");
		//将Entity的父类改为Superclass
		cc.setSuperclass(pool.get("com.hotusm.Superclass"));
		//增加方法  
		CtMethod method = CtNewMethod.make("public void say(){System.out.println(\"hello Javassist\");}", cc);
		cc.addMethod(method);
		//1.将CtClass转化为类文件 并写入到磁盘
		//cc.writeFile();
		//2.获取类的字节码
		//byte[] bytecode = cc.toBytecode();
		//3.会要求当前的线程来加载Ctclass代表的类文件  并且返回一个对象
		Class<?> c1 = cc.toClass();
		
		//采用反射的方式  可以使用我们刚才动态定义的方法  
		Method m1 = c1.getMethod("say");
		Object object = c1.newInstance();
		m1.invoke(object);
		
	}
	/**
	 * 动态的创建一个新类或者是新的接口
	 * @throws Exception 
	 */
	@Test
	public void make() throws Exception{
		ClassPool pool = ClassPool.getDefault();
		//创建一个类  可以指定完整的类名    下面的例子可以很简单的看出来  将报名和类名分开了
		CtClass cc = pool.makeClass("com.hotusm.Make");
		//Class<?> c1 = cc.toClass();
		//Package p = c1.getPackage();
		//System.out.println(p.getName());
		//新增一个方法   其中我们编码中设计类中的操作  在这里都可以做
		CtMethod method = CtNewMethod.make("public void say(){System.out.println(\"hello Javassist\");}", cc);
		cc.addMethod(method);
		//新增一个字段
		CtClass type = pool.get("java.lang.String");//字段的类型
		CtField field=new CtField(type, "name", cc);
		cc.addField(field);
		
		//创建一个接口
		pool.makeInterface("com.hotusm.InterfaceVo");
		//创建方法  因为这里创建的方法都是抽象的     
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
		//ClassPool对应的就是类运行时的池   维护了对象的一个哈希表
		ClassPool pool = ClassPool.getDefault();
		//get获取一个类  和反射的使用是相似的
		//ctClass 代表类  和反射的Class相似  但是多了一些功能  增加方法   字段等等  ct->compile time
		CtClass cc = pool.get("com.hotusm.Entity");
		//将Entity的父类改为Superclass
		cc.setSuperclass(pool.get("com.hotusm.Superclass"));
		//增加方法  
		CtMethod method = CtNewMethod.make("public void say(String str){System.out.println(\"hello Javassist\");}", cc);
		/**
		 * use $x represent stack variable 
		 * 
		 */
		method.insertBefore("System.out.println($1);");
		cc.addMethod(method);
		
		//1.将CtClass转化为类文件 并写入到磁盘
		//cc.writeFile();
		//2.获取类的字节码
		//byte[] bytecode = cc.toBytecode();
		//3.会要求当前的线程来加载Ctclass代表的类文件  并且返回一个对象
		Class<?> c1 = cc.toClass();
		
		//采用反射的方式  可以使用我们刚才动态定义的方法  
		Method m1 = c1.getMethod("say",new Class[]{String.class});
		Object object = c1.newInstance();
		m1.invoke(object,"hello ");
		
	}
	
}

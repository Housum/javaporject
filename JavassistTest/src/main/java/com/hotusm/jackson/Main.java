
package com.hotusm.jackson;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hotusm.jackson.annotation.IgnoreProperty;
import com.hotusm.jackson.annotation.ObjectMapperBuilder;

public class Main {
	
	
	/**
	 * 循环引用
	 * 相互引用的时候  会出现这个问题
	 * @param args
	 */
	@Test
	public void cycleTest() {
		User user=new User();
		user.setName("123");
		Article a1=new Article();
		a1.setTitle("t1");
		a1.setUser(user);
		Article a2=new Article();
		a2.setTitle("t2");
		a2.setUser(user);
		Article a3=new Article();
		a3.setTitle("t3");
		a3.setUser(user);
		List<Article> as=new ArrayList<Article>();
		as.add(a1);
		as.add(a2);
		as.add(a3);
		user.setArticles(as);
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			String str = objectMapper.writeValueAsString(user);
			System.out.println(str);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 使用@JsonIgnoreProperties注解  动态的
	 * 过滤掉一些属性  可以通过这种方式来解决循环引用的
	 * 问题
	 */
	@Test
	public void cycleTest1(){
		User user=new User();
		user.setName("123");
		Article a1=new Article();
		a1.setTitle("t1");
		a1.setUser(user);
		Article a2=new Article();
		a2.setTitle("t2");
		a2.setUser(user);
		Article a3=new Article();
		a3.setTitle("t3");
		a3.setUser(user);
		List<Article> as=new ArrayList<Article>();
		as.add(a1);
		as.add(a2);
		as.add(a3);
		user.setArticles(as);
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.addMixInAnnotations(Article.class, ArticleFilter.class);
		try {
			String str = objectMapper.writeValueAsString(user);
			System.out.println(str);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	//动态的创建一个接口  其实就相当于ArticleFilter 
	//这样就不用我们每次都去创建一个了
	@Test
	public void makeClass() throws CannotCompileException{
		ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeInterface("ProxyMixInAnnotation" + System.currentTimeMillis());
        //ClassFile对应的就是JAVA的.class文件
        ClassFile classFile = cc.getClassFile();
        //常量池
        ConstPool cp = classFile.getConstPool();
        AnnotationsAttribute attr = new AnnotationsAttribute(cp,
                AnnotationsAttribute.visibleTag);
        Annotation jsonIgnorePropertiesAnnotation = new Annotation(
                JsonIgnoreProperties.class.getName(), cp);
        BooleanMemberValue ignoreUnknownMemberValue = new BooleanMemberValue(false, cp);
        //
        ArrayMemberValue arrayMemberValue = new ArrayMemberValue(cp);
        Collection<MemberValue> memberValues = new HashSet<MemberValue>();
        StringMemberValue memberValue = new StringMemberValue(cp);// 将name值设入注解内
        memberValue.setValue("user");
        memberValues.add(memberValue);
        arrayMemberValue.setValue(memberValues.toArray(new MemberValue[]{}));
        jsonIgnorePropertiesAnnotation.addMemberValue("value", arrayMemberValue);
        jsonIgnorePropertiesAnnotation.addMemberValue("ignoreUnknown", ignoreUnknownMemberValue);
        attr.addAnnotation(jsonIgnorePropertiesAnnotation);
        classFile.addAttribute(attr); 
        //这个类和我们手动创建的作用是一样的
        Class clazz = cc.toClass();
        
        
        User user=new User();
		user.setName("123");
		Article a1=new Article();
		a1.setTitle("t1");
		a1.setUser(user);
		Article a2=new Article();
		a2.setTitle("t2");
		a2.setUser(user);
		Article a3=new Article();
		a3.setTitle("t3");
		a3.setUser(user);
		List<Article> as=new ArrayList<Article>();
		as.add(a1);
		as.add(a2);
		as.add(a3);
		user.setArticles(as);
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.addMixInAnnotations(Article.class,clazz);
		try {
			String str = objectMapper.writeValueAsString(user);
			System.out.println(str);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 采用注解的形式类进行动态的在方法上面忽略指定类的属性
	 */
	@Test
	@IgnoreProperty(pojo=Article.class,value={"user"})
	public void testJacksonAnnotation(){
		 User user=new User();
			user.setName("hotusm");
			Article a1=new Article();
			a1.setTitle("t1");
			a1.setUser(user);
			Article a2=new Article();
			a2.setTitle("t2");
			a2.setUser(user);
			Article a3=new Article();
			a3.setTitle("t3");
			a3.setUser(user);
			List<Article> as=new ArrayList<Article>();
			as.add(a1);
			as.add(a2);
			as.add(a3);
			user.setArticles(as);
			ObjectMapper objectMapper;
			try {
				objectMapper = new ObjectMapperBuilder().build(Main.class.getMethod("testJacksonAnnotation"));
				String str = objectMapper.writeValueAsString(user);
				System.out.println(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	@Test
	public void testJscksonFeature(){
		List<Demo> demos=new ArrayList<Demo>();
		demos.add(new Demo("d1","ddd1"));
		demos.add(new Demo("d2","ddd2"));
		demos.add(new Demo("d3","ddd3"));
		ObjectMapper objectMapper=new ObjectMapper();
		//设置json数据的格式化
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		try {
			String str = objectMapper.writeValueAsString(demos);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

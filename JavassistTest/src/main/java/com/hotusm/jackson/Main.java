
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
	 * ѭ������
	 * �໥���õ�ʱ��  ������������
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
	 * ʹ��@JsonIgnorePropertiesע��  ��̬��
	 * ���˵�һЩ����  ����ͨ�����ַ�ʽ�����ѭ�����õ�
	 * ����
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
	//��̬�Ĵ���һ���ӿ�  ��ʵ���൱��ArticleFilter 
	//�����Ͳ�������ÿ�ζ�ȥ����һ����
	@Test
	public void makeClass() throws CannotCompileException{
		ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeInterface("ProxyMixInAnnotation" + System.currentTimeMillis());
        //ClassFile��Ӧ�ľ���JAVA��.class�ļ�
        ClassFile classFile = cc.getClassFile();
        //������
        ConstPool cp = classFile.getConstPool();
        AnnotationsAttribute attr = new AnnotationsAttribute(cp,
                AnnotationsAttribute.visibleTag);
        Annotation jsonIgnorePropertiesAnnotation = new Annotation(
                JsonIgnoreProperties.class.getName(), cp);
        BooleanMemberValue ignoreUnknownMemberValue = new BooleanMemberValue(false, cp);
        //
        ArrayMemberValue arrayMemberValue = new ArrayMemberValue(cp);
        Collection<MemberValue> memberValues = new HashSet<MemberValue>();
        StringMemberValue memberValue = new StringMemberValue(cp);// ��nameֵ����ע����
        memberValue.setValue("user");
        memberValues.add(memberValue);
        arrayMemberValue.setValue(memberValues.toArray(new MemberValue[]{}));
        jsonIgnorePropertiesAnnotation.addMemberValue("value", arrayMemberValue);
        jsonIgnorePropertiesAnnotation.addMemberValue("ignoreUnknown", ignoreUnknownMemberValue);
        attr.addAnnotation(jsonIgnorePropertiesAnnotation);
        classFile.addAttribute(attr); 
        //�����������ֶ�������������һ����
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
	 * ����ע�����ʽ����ж�̬���ڷ����������ָ���������
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
		//����json���ݵĸ�ʽ��
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		try {
			String str = objectMapper.writeValueAsString(demos);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

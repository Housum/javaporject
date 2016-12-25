package com.hotusm.utils;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper extends ObjectMapper {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -8996664778361944956L;
		private static JsonMapper jsonMapper; 
		
		public static JsonMapper getInstance(){
			if(jsonMapper==null){
				jsonMapper=new JsonMapper().enableSimple();
			}
			return jsonMapper;
		}
		/**
		 * ��������ĸ�ʽ
		 * ��������
		 * ���������ŵ��ֶ�����
		 * @return
		 */
		public JsonMapper enableSimple(){
			this.configure(Feature.ALLOW_SINGLE_QUOTES, true);
			this.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			return this;
		}
		/**
		 * ������ת��Ϊjson�ַ���
		 * @param object
		 * @return
		 */
		public String toJson(Object object){
			try {
				return this.writeValueAsString(object);
			} catch (Exception e) {
				return null;
			}
		}
		/**
		 * ��json�ַ���ת��Ϊָ���Ķ���
		 * @param jsonString
		 * @param clazz
		 * @return
		 */
		public <T> T fromJson(String jsonString,Class<T> clazz){
			if(StringUtils.isBlank(jsonString)){
				return null;
			}
			try {
				return this.readValue(jsonString, clazz);
			}catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			
		}
		/**
		 * �߼����͵�ת�� Collection Map
		 * @param jsonString
		 * @param javaType
		 * @return
		 */
		public <T> T fromJson(String jsonString,JavaType javaType){
			if(StringUtils.isEmpty(jsonString)){
				return null;
			}
			try {
				return this.readValue(jsonString, javaType);
			}catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		/**
		 * �����ͽ��з����л� ���JavaType
		 * @param collectionClass
		 * @param elementClass
		 * @return
		 */
		public static JavaType getCollectionType(Class<?> collectionClass,Class<?> elementClass){
			return JsonMapper.getInstance().getTypeFactory().constructParametricType(collectionClass, elementClass);
		}
		/**
		 * ת������Ϊjson�ַ���
		 * @param object
		 * @return
		 */
		public static String toJsonString(Object object){
			
			return JsonMapper.getInstance().toJson(object);
		}
		
		public static Object fromJsonString(String jsonString,Class<?> clazz){
			return JsonMapper.getInstance().fromJson(jsonString, clazz);
		}
}

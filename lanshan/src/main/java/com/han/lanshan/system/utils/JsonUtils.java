package com.han.lanshan.system.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Json工具类
 * 
 * @author caomei
 *
 */
public class JsonUtils {
	
	private final static ObjectMapper mapper = new ObjectMapper();
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	
	static {
		// 为 null 的不转换
		//this.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// 支持 属性不对应
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 为bean 为null时不报异常
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		// 键 支持 不带 双引号 ""
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		// 设置日期格式
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}
	
	private JsonUtils() {
		throw new IllegalAccessError("工具类不能实例化");
	}

	

	/**
	 * 将对象转转化成Json字符串
	 * 
	 * @param o
	 * @return
	 */
	public static String writeValueAsString(Object o) {
		String str = null;
		try {
			str = mapper.writeValueAsString(o);
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return str;
	}

	/**
	 * 将对象字符串(不是List格式),转化成对象.
	 * 
	 * @param content
	 * @param clazz
	 * @return
	 */

	public static <T> T readValue(String content, Class<T> clazz) {
		T t = null;
		try {
			t = mapper.readValue(content, clazz);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return t;
	}

	/**
	 * 将List对象字符串,转化成List对象.
	 * 
	 * @param content 字符串内容
	 * @param clazz   对象类型 例如 User.class
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> readValues(String content, Class<T> clazz) {
		return (List<T>) readValues(content, ArrayList.class, clazz);
	}

	/**
	 * 将List对象字符串,转化成List对象.
	 * 
	 * @param content         字符串内容
	 * @param collectionClass 集合类型,例如 ArrayList.class
	 * @param clazz           对象类型 例如 User.class
	 * @return
	 */
	public static Object readValues(String content, Class collectionClass, Class clazz) {
		Object o = null;

		try {
			o = mapper.readValue(content, getCollectionType(collectionClass, clazz));
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return o;
	}

	private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

}

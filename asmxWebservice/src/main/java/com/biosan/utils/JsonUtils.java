package com.biosan.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json数据的处理工具类
 * 
 * @author qibill
 */
public class JsonUtils {

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	//对MAPPER进行设置
/*	static {
		//对象属性为NULL 不序列化 //对象属性为NULL 不序列化 
		MAPPER.setSerializationInclusion(Include.NON_EMPTY);
	}*/

	/**
	 * 将对象转换成json字符串。
	 * <p>Title: pojoToJson</p>
	 * <p>Description: </p>
	 * @param data
	 * @return
	 */
	public static String objectToJson(Object data) {
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 将json结果集转化为对象
	 * 
	 * @param jsonData json数据
	 * @param clazz 对象中的object类型
	 * @return
	 */
	public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
		try {
			T t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成pojo对象list
	 * <p>Title: jsonToList</p>
	 * <p>Description: </p>
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list = MAPPER.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		byte[] bs = {91,123,34,115,97,109,112,108,101,66,97,115,101,73,100,34,58,34,53,53,53,34,44,34,109,111,116,104,101,114,78,97,109,101,34,58,110,117,108,108,44,34,98,105,114,116,104,68,97,116,101,34,58,110,117,108,108,44,34,109,111,116,104,101,114,80,104,111,110,101,78,117,109,34,58,34,49,51,53,51,48,50,53,54,53,55,48,34,44,34,115,97,109,112,108,101,83,101,120,34,58,110,117,108,108,44,34,103,101,115,116,97,116,105,111,110,97,108,87,101,101,107,115,34,58,34,49,55,43,50,34,44,34,98,111,100,121,87,101,105,103,104,116,34,58,34,34,44,34,98,101,100,78,117,109,34,58,110,117,108,108,44,34,104,111,115,112,116,105,97,108,78,117,109,34,58,110,117,108,108,44,34,99,104,101,109,111,116,104,101,114,97,112,121,66,108,111,111,100,68,97,116,101,34,58,110,117,108,108,44,34,99,114,101,97,116,101,84,105,109,101,34,58,34,50,48,49,56,45,48,52,45,50,55,32,49,51,58,53,54,58,53,57,46,48,34,44,34,97,110,116,105,98,105,111,116,105,99,115,85,115,101,34,58,110,117,108,108,44,34,115,97,109,112,108,101,78,97,109,101,34,58,34,-27,-80,-113,-25,-70,-94,34,44,34,105,100,67,97,114,100,34,58,34,52,50,49,48,50,51,49,57,57,49,49,48,50,53,56,53,53,48,34,44,34,98,108,111,111,100,67,111,100,101,34,58,110,117,108,108,44,34,98,111,100,121,72,101,105,103,104,116,34,58,34,34,44,34,100,101,108,105,118,101,114,121,77,111,100,101,34,58,110,117,108,108,44,34,98,108,111,111,100,78,97,109,101,34,58,110,117,108,108,125,93};
		String s = new String(bs);
		System.out.println(s);
		s = s.substring(1, s.length() - 1);
		System.out.println(s);
		

	}
}

package com.qibill.utils;

import java.lang.reflect.Method;

public class XMLUtil {

	/**
	 * 添加对应属性的xml标签。
	 * @param field 属性名
	 * @param clazz 调用方法的类
	 * @param object 调用方法的对象 
	 * @return xml类型的字符串String
	 * 
	 * @author qibill
	 * 2018年5月28日上午11:46:06
	 */
	public static String addElement(String field, Class<?> clazz, Object object) {
		Method method;
		try {
			method = clazz.getDeclaredMethod("get" + StringUtil.toUpperCaseFirstOne(field));
			object = method.invoke(object);
		} catch (Exception e) {
			System.out.println(field + "无法添加信息");
			e.printStackTrace();
		}
		return object == null ? "<" + field + " />" : "<" + field + ">" + object + "</" + field + ">";
	}
}

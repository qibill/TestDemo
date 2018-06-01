package com.biosan.utils;

import java.lang.reflect.Method;

public class XMLUtil {

	/**
	 * 添加对应属性的xml标签。
	 * 
	 * @param field 属性名
	 * @param object 调用方法的对象 
	 * @return xml类型的字符串String
	 * 
	 * @author qibill
	 * 2018年5月28日上午11:46:06
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> String addElement(String field, T t) {
		Method method;
		Object object;
		Class<T> clazz =  (Class<T>) t.getClass();
		//判断 field 是否正确
		try {
			clazz.getDeclaredField(field);
		} catch (Exception e1) {
			System.out.println("没有指定" + field + "的字段");
			e1.printStackTrace();
			return "";
		}
		//调用get方法
		try {
			method = clazz.getDeclaredMethod("get" + StringUtil.toUpperCaseFirstOne(field));
			object = method.invoke(t);
		} catch (Exception e) {
			System.out.println(field + "无法添加信息");
			e.printStackTrace();
			return "";
		}
		return object == null ? "<" + field + " />" : "<" + field + ">" + object + "</" + field + ">";
	}
}

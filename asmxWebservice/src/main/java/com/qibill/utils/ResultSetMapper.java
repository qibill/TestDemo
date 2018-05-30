package com.qibill.utils;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;

public class ResultSetMapper<T> {

	/**
	 * 把ResultSet的结果放到java对象中
	 * @param rs ResultSet
	 * @param clazz 映射的java对象类
	 * @return 映射的java对象的list集合
	 * 
	 * @author qibill
	 * 2018年5月30日上午9:54:20
	 */
	public static <T> ArrayList<T> putResult(ResultSet rs, Class<T> clazz) {
		ArrayList<T> arrayList = new ArrayList<T>();
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			//获取总列数
			int count = metaData.getColumnCount();
			while (rs.next()) {
				//创建对象实例
				T newInstance = clazz.newInstance();
				for (int i = 1; i <= count; i++) {
					
					//给对象的某个属性赋值
					String name = metaData.getColumnName(i).toLowerCase();
					name = toJavaField(name);// 改变列名格式成java命名格式
					Class<?> type = clazz.getDeclaredField(name).getType();// 获取字段类型
					Method method = clazz.getMethod("set" + StringUtil.toUpperCaseFirstOne(name), type);
					
					//判断读取数据的类型
					if (type.isAssignableFrom(String.class)) {
						method.invoke(newInstance, rs.getString(i));
					} else if (type.isAssignableFrom(int.class)
							|| type.isAssignableFrom(Integer.class)) {
						method.invoke(newInstance, rs.getInt(i));
					} else if (type.isAssignableFrom(Boolean.class)
							|| type.isAssignableFrom(boolean.class)) {
						method.invoke(newInstance, rs.getBoolean(i));
					} else if (type.isAssignableFrom(Date.class)) {
						method.invoke(newInstance, rs.getDate(i));
					} else if (type.isAssignableFrom(byte[].class)) {
						method.invoke(newInstance, rs.getBlob(i));
					}
				}
				arrayList.add(newInstance);
			}
			return arrayList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 数据库命名格式转java命名格式
	 * 
	 * @param str 数据库字段名
	 * @return java字段名
	 */
	public static String toJavaField(String str) {

		String[] split = str.split("_");
		StringBuilder builder = new StringBuilder();
		builder.append(split[0]);// 拼接第一个字符

		// 如果数组不止一个单词
		if (split.length > 1) {
			for (int i = 1; i < split.length; i++) {
				// 去掉下划线，首字母变为大写
				String string = split[i];;
				builder.append(StringUtil.toUpperCaseFirstOne(string));
			}
		}

		return builder.toString();
	}
}

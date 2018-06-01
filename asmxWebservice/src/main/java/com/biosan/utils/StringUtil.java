package com.biosan.utils;

public class StringUtil {

	/**
	 * 将字符串的首字母大写
	 * 
	 * @param string
	 * @return
	 * 
	 * @author qibill
	 * 2018年5月28日上午10:27:09
	 */
	public static String toUpperCaseFirstOne(String string) {
		if (Character.isUpperCase(string.charAt(0)))
			return string;
		else
			return (new StringBuilder()).append(Character.toUpperCase(string.charAt(0)))
					.append(string.substring(1)).toString();
	}
}

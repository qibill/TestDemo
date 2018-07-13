package net.biosan.net.utils;

public class CheckUtil {

	public static String CheckPregRealcycle(String preg_realcycle){
		//错误返回
		String defaultPreg_realcycle = "-/-";
		//null值，空值
		if (preg_realcycle == null || preg_realcycle.trim().isEmpty()) {
			return defaultPreg_realcycle;
		}
		//无"/" 多个"/"
		if (!preg_realcycle.contains("/") || preg_realcycle.indexOf("/") != preg_realcycle.lastIndexOf("/")) {
			return defaultPreg_realcycle;
		}
		String[] split = preg_realcycle.split("/");
		int preg_week = 0;
		int preg_day = 0;
		//无法转换
		try {
			preg_week = Integer.parseInt(split[0].trim());
			preg_day = Integer.parseInt(split[1].trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return defaultPreg_realcycle;
		}
		//取值范围不对
		if (preg_week < 15 || preg_week >50 || preg_day < 0 || preg_day > 6) {
			return defaultPreg_realcycle;
		}
		return preg_week + "/" + preg_day;
		
	};
}

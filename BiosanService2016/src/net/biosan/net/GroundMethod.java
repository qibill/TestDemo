package net.biosan.net;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 快速录入公共方法类
 * 
 * @author JohnYe
 * @date 2009-02-26
 * 
 */
public class GroundMethod {

	static DateFormat df = new SimpleDateFormat("yyMMddHHmmss");

	static DecimalFormat deci = new DecimalFormat("##.##");

	/**
	 * 取出一个字符串HASHMAP中的KEY的对象LIST
	 * 
	 * @param set
	 * @param matchStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getXmlObjectKeyList(Set<String> set,
			String matchStr, String matchChar) {
		List<String> result = new ArrayList<String>();
		if (set != null && set.isEmpty() == false) {
			List<String> list = new ArrayList<String>();
			Iterator<String> iterator = set.iterator();
			// int listLength = 0;
			String sp = "";
			while (iterator.hasNext()) {
				String s = iterator.next();
				if (s.contains(matchStr) && s.contains(matchChar)) {
					sp = s.substring(0, s.indexOf(matchChar) + 1);
					list.add(sp);
				}
			}
			Set someSet = new HashSet(list);

			// 将Set中的集合，放到一个临时的链表中(tempList)
			Iterator<String> iterator2 = someSet.iterator();
			while (iterator2.hasNext()) {
				result.add(iterator2.next());
			}
		}
		return result;
	}

	@SuppressWarnings("unused")
	private static int digit(long number, int position) {
		return (int) (number / Math.pow(10, position)) % 10;
	}

	/**
	 * 计算流程KEY
	 * 
	 * @param operateId
	 * @param customerTypeId
	 * @param lastuid
	 * @return
	 */
	// public static String getKeySerialNumber(int processTypeId, String
	// lastuid) {
	// DecimalFormat df3 = new DecimalFormat("000");
	// DecimalFormat df9 = new DecimalFormat("000000000");
	// if (lastuid == null || "".equals(lastuid)) {
	// return df3.format(processTypeId) + "800800";
	// }
	// long newuid = Long.parseLong(lastuid) + 1;
	// return df9.format(newuid);
	// }
	/**
	 * work grid key
	 */
	public static String getKey(long patientid, long seq, String definition) {
		return definition + String.valueOf(patientid) + String.valueOf(seq);
	}

	/**
	 * 计算PATIENTGUID
	 * 
	 * @param type
	 * @param seq
	 * @return
	 */
	public static String getGuidSerialNumber(String type, String seq) {
		DecimalFormat df8 = new DecimalFormat("00000000");
		return "8" + type + df8.format(Long.parseLong(seq));
	}

	/**
	 * 计算EMPLOYEEGUID
	 * 
	 * @param type
	 * @param seq
	 * @return
	 */
	public static String getEmployeeGuidSerialNumber(String type, String seq) {
		DecimalFormat df8 = new DecimalFormat("0000");
		return "9" + type + df8.format(Long.parseLong(seq));
	}

	/**
	 * 计算EMPLOYEECODE
	 * 
	 * @param depCode
	 * @param seq
	 * @return
	 */
	public static String getEmployeeCodeSerialNumber(String depCode, String seq) {
		DecimalFormat df8 = new DecimalFormat("000");
		return depCode + df8.format(Long.parseLong(seq));
	}

	/**
	 * 计算DepCODE
	 * 
	 * @param areaCode
	 * @param seq
	 * @return
	 */
	public static String getDepartmentCodeSerialNumber(String areaCode,
			String seq) {
		DecimalFormat df8 = new DecimalFormat("000");
		return areaCode + df8.format(Long.parseLong(seq));
	}

	/**
	 * 计算AreaCODE
	 * 
	 * @param areaCode
	 * @param seq
	 * @return
	 */
	public static String getAreaCodeSerialNumber(String seq) {
		DecimalFormat df8 = new DecimalFormat("00");
		return df8.format(Long.parseLong(seq));
	}

	/**
	 * 计算账户的GUID
	 * 
	 * @param type
	 * @param uid
	 * @return
	 */
	public static String getAccountSerialNumber(String type, String guid) {
		return "6" + type + guid;
	}

	/**
	 * 计算病历卡号的GUID
	 * 
	 * @param operateId
	 * @param customerTypeId
	 * @param lastuid
	 * @return
	 */
	public static String getIllHistorySerialNumber(String guid) {

		return "7" + guid;
	}

	/**
	 * 生成批条形码
	 * 
	 * @return
	 */
	public static String getBatchBarCodeSerialNumber(String type, String seq) {
		DecimalFormat df3 = new DecimalFormat("000");
		DecimalFormat df8 = new DecimalFormat("00000000");
		return "b" + type + df3.format(000) + df8.format(Long.parseLong(seq));
	}

	/**
	 * 生成条形码
	 * 
	 * @return
	 */
	public static String getBarCodeSerialNumber(String seq) {
		DecimalFormat df4 = new DecimalFormat("0000");
		DecimalFormat df8 = new DecimalFormat("00000000");
		return "s" + df4.format(0000) + df8.format(Long.parseLong(seq));
	}

	/**
	 * 计算平均值
	 * 
	 * @param dataMap
	 * @return
	 */
	public static float computeX(HashMap<String, Float> dataMap) {
		float result = 0l;
		Iterator<String> dataKey = dataMap.keySet().iterator();
		while (dataKey.hasNext()) {
			result += dataMap.get(dataKey.next());
		}
		if (dataMap.size() > 0)
			result = result / dataMap.size();
		return Float.parseFloat(deci.format(result));
	}

	/**
	 * 计算SD
	 * 
	 * @param dataMap
	 * @param x
	 * @return
	 */
	public static float computeSD(HashMap<String, Float> dataMap, float x) {
		float result = 0l;
		Iterator<String> dataKey = dataMap.keySet().iterator();
		while (dataKey.hasNext()) {
			String key = dataKey.next();
			result += (x - dataMap.get(key)) * (x - dataMap.get(key));
		}
		if (dataMap.size() > 0)
			result = result / dataMap.size();
		return Float.parseFloat(deci.format(result));
	}

	/**
	 * java.util.date to java.sql.date
	 * 
	 * @param dateString
	 * @param nowDate
	 * @return
	 */
	public static java.sql.Date changeDateType(String dateString, Date nowDate) {
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String temp = f.format(nowDate);
		java.sql.Date sd = null;
		try {
			if (dateString.equals("") == false)
				temp = dateString;
			Date d = f.parse(temp);
			sd = new java.sql.Date(d.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sd;
	}

	/**
	 * 转换timestamp
	 * 
	 */
	public static java.sql.Timestamp changeTimestampType(String dateString,
			Date nowDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		Long temp = new Date().getTime();

		if (dateString.equals("") == false) {
			try {
				temp = f.parse(dateString).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Timestamp ts = new Timestamp(temp);
		return ts;
	}

	public static java.sql.Timestamp changeLongTimestampType(String dateString,
			Date nowDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		Long temp = new Date().getTime();

		if (dateString.equals("") == false) {
			try {
				temp = f.parse(dateString).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Timestamp ts = new Timestamp(temp);
		return ts;
	}

	/**
	 * 生成pdf
	 */
	/*
	 * public static String reportInfo(String xml) {
	 * 
	 * xml = xml == null ? "" : xml; SimpleDateFormat dsTime = new
	 * SimpleDateFormat("HHmmss"); String nowTime = dsTime.format(new
	 * Date(System.currentTimeMillis()));
	 * 
	 * File xslStream = new File(
	 * "D:/workspace341/MountainBeanTestII21/WEB-INF/pdfcg/temp.xsl");
	 * 
	 * String pdf = "F:/bbb/temp" + nowTime + ".pdf"; OutputStream out =null;
	 * try { out = new FileOutputStream(pdf); FopFactory fopFactory =
	 * FopFactory.newInstance(); FOUserAgent foUserAgent =
	 * fopFactory.newFOUserAgent(); fopFactory
	 * .setUserConfig("D:/workspace341/MountainBeanTestII21/WEB-INF/pdfcg/fop.xconf"
	 * ); Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
	 * TransformerFactory factory = TransformerFactory.newInstance();
	 * Transformer transformer = factory.newTransformer(new StreamSource(
	 * xslStream));
	 * 
	 * InputStream in = new ByteArrayInputStream(xml.getBytes()); Source src =
	 * new StreamSource(in);
	 * 
	 * Result result = new SAXResult(fop.getDefaultHandler());
	 * transformer.transform(src, result);
	 * 
	 * out.close(); System.out.println("success"); } catch (Exception e) {
	 * e.printStackTrace(); } return pdf; }
	 */

	public static void report(String xml) {

	}

	/**
	 * @param h
	 * @return 实现对map按照key排序
	 */
	@SuppressWarnings("unchecked")
	public static Map.Entry[] getSortedHashtableByKey(Map h) {

		Set set = h.entrySet();

		Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set
				.size()]);

		Arrays.sort(entries, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Object key1 = ((Map.Entry) arg0).getKey();
				Object key2 = ((Map.Entry) arg1).getKey();
				return ((Comparable) key1).compareTo(key2);
			}

		});

		return entries;
	}

}

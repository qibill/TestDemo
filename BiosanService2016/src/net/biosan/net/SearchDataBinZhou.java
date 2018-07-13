package net.biosan.net;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.biosan.utils.BiosanUtils;

/**
 * @author SXZ（通用版本、用于查询实验结果、结论）PS:滨州为第一个点
 * @date 2014 2014-1-9 下午02:54:26
 */
public class SearchDataBinZhou {

	/**
	 * 通过时间范围搜索barcode
	 * 
	 * @return barcode字符串数组
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public String[] searchBarcodes(String starttime, String endtime) {
		String[] str = null; // barcode数组
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1, d2;
			long interval = 0;
			d1 = sf.parse(starttime); // 读取文本框中输入的字符串，并将其转化为日期格式
			d2 = sf.parse(endtime);
			if (d2.getTime() > d1.getTime()) // 判断得到的两个日期的毫秒数大小
			{
				interval = d2.getTime() - d1.getTime(); // 得到两个日期的毫秒差
			} else {
				str = new String[1];
				str[0] = "开始时间大于结束时间";
				return str;
			}
			interval = (long) (interval / (1000 * 60 * 60 * 24));
			if (interval > 30) {
				str = new String[1];
				str[0] = "间隔时间大于30天";
				return str;
			}

			// 去视图中查找barcode
			String sql = "select barcodestring from ground_vw_sample_search_list ls where pdfdate "
					+ ">= to_date(?, 'yyyy-MM-dd HH24:mi:ss') and pdfdate <= to_date(?, 'yyyy-MM-dd HH24:mi:ss') "
					+ "and barcodestring is not null ";
			sql += " and (pdfaddress is not null or isverify = 1)";
			Object[] params = { starttime, endtime };
			List<String> idList = null;
			idList = BiosanUtils.getListColumn(sql, params);
			if (idList.size() != 0) {
				str = new String[idList.size()];
				for (int i = 0; i < idList.size(); i++) {
					str[i] = idList.get(i);
				}
			} else {
				str = new String[1];
				str[0] = "此时间范围没有数据";
				return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 根据barcode搜索用户基本信息并返回信息（xml格式）
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public String searchInfo(String barcode) {
		Document document = DocumentHelper.createDocument();
		String xml = ""; // 返回的xml字符串
		Map<String, Object> dataMap = null;
		try {
			//				String dataColumn = "admissionnum,collectnum, operatname, illhisguid, samplestring, samplebarcode, patientname, motherage, preg_realcycle, pre_num, born_num, sex, weight, "
			//					+ "apgar, suckledate, istosix, ill_thyroid, eatthyroiddrug, domicile linkman, mobile, telephone, address, postalcode, city, "
			//					+ "area, hos, attribute samplequality, nativeplace, experimentitem, remark";
			String dataColumn = "ohp_result, phe_result, tsh_result, g6pd_result, msms_result, phe_reason, tsh_reason, ohp_reason, "
					+ "g6pd_reason, msms_reason, target_msms, target_phe, target_tsh, target_17_a_ohp target_ohp, target_g6pd, "
					+ "barcodestring, area_ul area, experimentitem, sex, pregcarenum, mobile, patientname";
			dataColumn += ", to_char(collectedate, 'yyyy-MM-dd') collectdate, to_char(birthday, 'yyyy-MM-dd') birthday";
			String sql = "select " + dataColumn
					+ " from ground_vw_sample_search_list where barcodestring = ?";
			Object[] param = { barcode };
			dataMap = BiosanUtils.getMap(sql, param);
			if (dataMap != null) {
				Element root = document.addElement("request");
				Element dataElement = root.addElement("data");
				Set<String> keySet = dataMap.keySet();
				Iterator<String> it = keySet.iterator();
				while (it.hasNext()) {
					String key = it.next();
					if ("mobile".equals(key.toLowerCase())) {
						Element e = dataElement.addElement(key);
						e.setText("NULL");
						continue;
					}
					String value = "";
					if (dataMap.get(key.toUpperCase()) != null)
						value = dataMap.get(key.toUpperCase()).toString();
					Element e = dataElement.addElement(key);
					if ("".equals(value)) {
						value = "NULL";
					}
					e.setText(value);
				}
			}
			xml = document.asXML();
		} catch (Exception e) {
			e.printStackTrace();
			BiosanUtils.log4j("SearchDataBinZhou").error(e + "searchInfo方法运行出现异常！");
		}
		return xml;
	}

	/**
	 * 根据collectnum搜索用户基本信息并返回信息（xml格式）
	 */
	@SuppressWarnings( { "unchecked" })
	public String searchInfoByCollectnum(String collectnum) {
		Document document = DocumentHelper.createDocument();
		String xml = ""; // 返回的xml字符串
		Map<String, Object> dataMap = null;
		try {
			//String dataColumn = "admissionnum,collectnum, operatname, illhisguid, samplestring, samplebarcode, patientname, motherage, preg_realcycle, pre_num, born_num, sex, weight, "
			//+ "apgar, suckledate, istosix, ill_thyroid, eatthyroiddrug, domicile linkman, mobile, telephone, address, postalcode, city, "
			//+ "area, hos, attribute samplequality, nativeplace, experimentitem, remark";
			String dataColumn = "replace(replace(experimentitem,'labitem_'),'17_a_OHP','OHP') as experimentitem, pdfaddress, isverify, " +
					"ohp_result, phe_result, tsh_result, g6pd_result, msms_result, phe_reason, tsh_reason, ohp_reason, " +
					"g6pd_reason, msms_reason, target_msms, target_phe, target_tsh, target_17_a_ohp target_ohp, target_g6pd, " +
					"barcodestring, area_ul area, experimentitem, sex, pregcarenum, mobile, patientname";
			dataColumn += ", to_char(collectedate, 'yyyy-MM-dd') collectdate, to_char(birthday, 'yyyy-MM-dd') birthday";
			String sql = "select " + dataColumn
					+ " from ground_vw_sample_search_list where collectnum = ?";
			Object[] param = { collectnum };
			dataMap = BiosanUtils.getMap(sql, param);
			
			Element root = document.addElement("request");
			Element dataElement = root.addElement("data");
			
			//判断是否有值
			if (dataMap == null) {
				return document.asXML();
			}
			
			for (String key : dataMap.keySet()) {
				System.err.println(key + "============" + dataMap.get(key));
			}
			
			//判断是否做完实现
			String experimentitem = (String)dataMap.get("EXPERIMENTITEM");
			String[] experimentitems = experimentitem.split(",");
			for (String string : experimentitems) {
				if (string.trim().equals("MSMS") ) {
					//MSMS报告未出
					if (dataMap.get("ISVERIFY") == null) {
						return document.asXML();
					}
				} else {
					//四项中有报告未出
					if (dataMap.get("PDFADDRESS") == null) {
						return document.asXML();
					}
				}
			}
			
			
			Set<String> keySet = dataMap.keySet();
			Iterator<String> it = keySet.iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = "";
				if ("mobile".equals(key.toLowerCase())) {
					Element e = dataElement.addElement(key);
					e.setText("NULL");
					continue;
				}
				
				//不传送的部分
				if ("experimentitem".equals(key.toLowerCase())||
						"pdfaddress".equals(key.toLowerCase())||
						"isverify".equals(key.toLowerCase())) {
					continue;
				}
				if (dataMap.get(key.toUpperCase()) != null)
					value = dataMap.get(key.toUpperCase()).toString();
				Element e = dataElement.addElement(key);
				if ("".equals(value)) {
					value = "NULL";
				}
				e.setText(value);
			}
			xml = document.asXML();
		} catch (Exception e) {
			e.printStackTrace();
			BiosanUtils.log4j("SearchDataBinZhou").error(e + "searchInfo方法运行出现异常！");
		}
		return xml;
	}

	/**
	 * 根据时间段搜索barcode，搜索用户基本信息并返回信息（xml格式）
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public String searchInfoAll(String starttime, String endtime) {
		String xml = ""; // 返回的xml字符串
		String[] barcodeStr = searchBarcodes(starttime, endtime);
		//    	String[] barcodeStr = {"130000214571", "130000214571", "130000214571", "130000214571", "130000214571"};
		Document document = DocumentHelper.createDocument();
		Map<String, Object> dataMap = null;
		Element root = document.addElement("request");
		for (String barcode : barcodeStr) {
			if ("此时间范围没有数据".equals(barcode) || "间隔时间大于30天".equals(barcode)
					|| "此时间范围没有数据".equals(barcode))
				continue;
			try {
				//				String dataColumn = "admissionnum,collectnum, operatname, illhisguid, samplestring, samplebarcode, patientname, motherage, preg_realcycle, pre_num, born_num, sex, weight, "
				//					+ "apgar, suckledate, istosix, ill_thyroid, eatthyroiddrug, domicile linkman, mobile, telephone, address, postalcode, city, "
				//					+ "area, hos, attribute samplequality, nativeplace, experimentitem, remark";
				String dataColumn = "ohp_result, phe_result, tsh_result, g6pd_result, msms_result, phe_reason, tsh_reason, ohp_reason, "
						+ "g6pd_reason, msms_reason, target_msms, target_phe, target_tsh, target_17_a_ohp target_ohp, target_g6pd, "
						+ "barcodestring, area_ul area, experimentitem, sex, pregcarenum, mobile, patientname";
				dataColumn += ", to_char(collectedate, 'yyyy-MM-dd') collectdate, to_char(birthday, 'yyyy-MM-dd') birthday";
				String sql = "select " + dataColumn
						+ " from ground_vw_sample_search_list where barcodestring = ?";
				Object[] param = { barcode };
				dataMap = BiosanUtils.getMap(sql, param);
				if (dataMap != null) {
					Element dataElement = root.addElement("data");
					Set<String> keySet = dataMap.keySet();
					Iterator<String> it = keySet.iterator();
					while (it.hasNext()) {
						String key = it.next();
						if ("mobile".equals(key.toLowerCase())) {
							Element e = dataElement.addElement(key);
							e.setText("NULL");
							continue;
						}
						String value = "";
						if (dataMap.get(key.toUpperCase()) != null)
							value = dataMap.get(key.toUpperCase()).toString();
						Element e = dataElement.addElement(key);
						if ("".equals(value)) {
							value = "NULL";
						}
						e.setText(value);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				BiosanUtils.log4j("SearchDataBinZhou").error(e + "searchInfoAll方法运行出现异常！");
			}
		}
		xml = document.asXML();
		return xml;
	}

	/**
	 * 根据phoneNum搜索用户基本信息并返回信息（xml格式）
	 */
	public String searchInfoByPhoneNum(String mobile) {
		Document document = DocumentHelper.createDocument();
		String xml = ""; // 返回的xml字符串
		Map<String, Object> dataMap = null;
		try {
			String dataColumn = "ohp_result, phe_result, tsh_result, g6pd_result, msms_result, phe_reason, tsh_reason, ohp_reason, "
					+ "g6pd_reason, msms_reason, target_msms, target_phe, target_tsh, target_17_a_ohp target_ohp, target_g6pd, "
					+ "barcodestring, area_ul area, experimentitem, sex, pregcarenum, decrypt(mobile, 'Bio-San#17') as mobile, patientname"
					+ ", to_char(collectedate, 'yyyy-MM-dd') collectdate, to_char(birthday, 'yyyy-MM-dd') birthday";
			String sql = "select " + dataColumn
					+ " from ground_vw_sample_search_list where mobile = encrypt(?, 'Bio-San#17')";
			Object[] param = { mobile };
			dataMap = BiosanUtils.getMap(sql, param);
			if (dataMap != null) {
				Element root = document.addElement("request");
				Element dataElement = root.addElement("data");
				Set<String> keySet = dataMap.keySet();
				Iterator<String> it = keySet.iterator();
				while (it.hasNext()) {
					String key = it.next();
					if ("mobile".equals(key.toLowerCase())) {
						Element e = dataElement.addElement(key);
						e.setText("NULL");
						continue;
					}
					String value = "";
					if (dataMap.get(key.toUpperCase()) != null)
						value = dataMap.get(key.toUpperCase()).toString();
					Element e = dataElement.addElement(key);
					if ("".equals(value)) {
						value = "NULL";
					}
					e.setText(value);
				}
			}
			xml = document.asXML();
		} catch (Exception e) {
			e.printStackTrace();
			BiosanUtils.log4j("SearchDataBinZhou").error(e + "searchInfo方法运行出现异常！");
		}
		return xml;
	}

	/**
	 * 合并两个字符串数组
	 * 
	 * @param a 数组a
	 * @param b 数组b
	 * @return 合并后的数组c
	 */
	public static String[] concat(String[] a, String[] b) {
		String[] c = new String[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}

	public static void main(String[] args) {
		SearchDataBinZhou sd = new SearchDataBinZhou();
		//    	String[] ss = sd.searchBarcodes("2013-7-21", "2013-8-1");
		//    	System.out.println(ss);
		//    	for (String s: ss) {
		//    		System.out.println(s);
		//    	}
		//    	String xml = sd.searchInfo("130000214571");
		String xml = sd.searchInfoByPhoneNum("13864497209");
		System.out.println(xml);

		//    	String xmlall = sd.searchInfoAll("2013-7-21", "2013-7-21");
		//    	System.out.println(xmlall);
	}

}

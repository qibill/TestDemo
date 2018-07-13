package net.biosan.net;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import net.marginland.mland.common.utils.file.FileUtil;
import net.marginland.mland.common.utils.xml.XmlDocument3;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import com.biosan.UploadService;
import com.biosan.utils.BiosanUtils;

import cpdetector.io.ASCIIDetector;
import cpdetector.io.CodepageDetectorProxy;
import cpdetector.io.JChardetFacade;
import cpdetector.io.ParsingDetector;
import cpdetector.io.UnicodeDetector;

/**
 * 妇保接口（上传xml到中间表）
 * @author SXZ
 * @date 2015 2015-11-11 下午02:05:05
 */
public class ImportDataForFB

{
	private Logger log = Logger.getLogger(ImportDataForFB.class);
	private FileUtil fileUtil = new FileUtil();
	private BasicDataSource dataSource = new BasicDataSource();
	private BasicDataSource qualitydataSource = new BasicDataSource();
	private String must = "";
	private String departmentid = "";
	private String employeeid = "";
	private String business = "";
	private String type = "";
	private String dir = "";
	private String encode = "utf-8";
	private ArrayList<String> keylist = new ArrayList<String>();
	private String employeeguid = "";
	private String senddepartment = "";
	private String localsenddepartment = "";

	public BasicDataSource loadinit() {

		int maxactive = 30;
		int maxidle = 20;
		long maxwait = 10000;

		Properties p = new Properties();
		try {
			log.info("连接数据库！");
			p.load(UploadService.class.getResourceAsStream("/dbcp.properties"));
			must = p.getProperty("must");
			dir = p.getProperty("dir");
			encode = p.getProperty("encode");
			senddepartment = p.getProperty("departmentid");
			localsenddepartment = senddepartment;
			business = p.getProperty("business");
			type = p.getProperty("type");
			dataSource.setDriverClassName(p.getProperty("driverClass"));
			dataSource.setUrl(p.getProperty("jdbcUrl"));
			dataSource.setUsername(p.getProperty("user"));
			dataSource.setPassword(p.getProperty("password"));
			dataSource.setMaxActive(maxactive);
			dataSource.setMaxIdle(maxidle);
			dataSource.setMaxWait(maxwait);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataSource;

	}

	public void importdata() {
		loadinitkeylist();
		loadinit();
		readFile();
	}

	public void loadinitkeylist() {
		keylist.add("samplestring"); 
		keylist.add("collectenum");  // 采血编号
		keylist.add("barcode");
		keylist.add("patientname");
		keylist.add("reserved0");  // 母亲年龄
		keylist.add("preg_realcycle");
		keylist.add("pre_num");
		keylist.add("born_num");
		keylist.add("sex");
		keylist.add("weight");
		keylist.add("apgar");
		keylist.add("birthday");
		keylist.add("reserved1");  // 开奶日期
		keylist.add("collectedate");
		keylist.add("operatedate");
		keylist.add("domicile");
		keylist.add("mobile");
		keylist.add("telephone");
		keylist.add("address");
		keylist.add("reserved6");  // 婴儿籍贯
		keylist.add("household");  // 母亲户籍
		keylist.add("expensetype");  // 费用类型
		keylist.add("reserved3");  // 免费券号
		keylist.add("labitemname");
		keylist.add("remark");
		
		keylist.add("istosix");
		keylist.add("ill_thyroid");
		keylist.add("reserved2");
		keylist.add("cardtype");
		keylist.add("attribute");
		keylist.add("isfree");
		
//		keylist.add("area_ul");
//		keylist.add("hos");
	}

	@SuppressWarnings("unchecked")
	private void readFile() {
		log.info("解析文件！");
		if (!(dir != null && dir.equals("") == false)) {
			log.info("文件路径为空！");
			return;
		}

		if (fileUtil.isDirectoryExist(dir) == false) {
			log.info("文件路径不存在！");
			return;
		}

		ArrayList<String> fileNameList = fileUtil.getFileNamesInDirectory(dir);
		if (!(fileNameList != null && fileNameList.isEmpty() == false)) {
			log.info("没有要读的文件！");
			return;
		}

		String fileName = "";
		for (int i = 0; i < fileNameList.size(); i++) {
			try {
				fileName = fileNameList.get(i);
				if (!(fileName != null && fileName.equals("") == false))
					continue;

				if (fileName.endsWith(".xml") == false)
					continue;

				String doc = "";
				byte[] fileBytes = fileUtil.readAsBytes(dir + fileName);
				if (!(fileBytes != null && fileBytes.length > 0))
					return;

				log.info("文件编码格式为：" + encode);
				
				// 转字符串
				try {
					doc = new String(fileBytes, encode);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				doc = doc.replaceAll("null", "").trim();

				System.out.println("dom==" + doc.trim());
				// workgrid2.
				XmlDocument3 xmlDocument = XmlDocument3.getInstance(null, doc);
				if (xmlDocument != null) {
					log.info("生成xmlDocument");
					ArrayList<ImportBean> datalist = new ArrayList<ImportBean>();
					int m = 0;
					m = xmlDocument.getChildLength("request.data");
					for (int j = 0; j < m; j++) {
						String keyvalue = "";
						ImportBean importbean = new ImportBean();
						Class c = importbean.getClass();
						boolean flag = true;
						if (m == 1) {
							// 判断不能为空
							if (must.equals("") == false) {
								for (int x = 0; x < must.split(",").length; x++) {
									keyvalue = xmlDocument.get("request.data."
											+ must.split(",")[x]);
									keyvalue = keyvalue == null ? "" : keyvalue
											.trim();
									if (keyvalue.equals("")) {
										flag = false;
										break;
									}
								}
							}
							if (flag) {
								for (String key : keylist) {
									keyvalue = xmlDocument.get("request.data."
											+ key);
									keyvalue = keyvalue == null ? "" : keyvalue
											.trim();
									
									if (key.equals("collectenum")) {
										if (keyvalue.equals("") == false) {
											if (keyvalue.length() > 8) {
												Map<String, Object> hos = BiosanUtils
														.getMap("select d.code||'|'||d.departmentname as hos,a.code||'|'||a.areaname as area "
																+ " from ground_tb_department d "
																+ " left join ground_tb_area a on a.areaid=d.areaid "
																+ "where d.departmenttype='hos' and rownum=1"
																+ " and d.code='"
																+ keyvalue.substring(0, 6)
																+ "'");
												String hosStr = "";
												String areaStr = "";
												if (hos != null) {
													hosStr = hos.get("hos").toString();
													areaStr = hos.get("area").toString();
												} 
												Field f = c.getDeclaredField("hos");
												f.set(importbean, hosStr);
												
												f = c.getDeclaredField("area_ul");
												f.set(importbean, areaStr);
											}

										}
									}
//									if (key.equals("labitemname")) {
//										if (keyvalue.equals("") == false) {
//											if (keyvalue.equals("4项")) {
//												keyvalue = "labitem_PHE,labitem_TSH,labitem_17_a_OHP,labitem_G6PD";
//											}
//											if (keyvalue.equals("29项")) {
//												keyvalue = "labitem_TSH,labitem_17_a_OHP,labitem_G6PD,labitem_MSMS";
//											}
//										}
//									}
									
									try {
										Field f = c.getDeclaredField(key);
										f.set(importbean, keyvalue);
									} catch (SecurityException e) {
										e.printStackTrace();
									} catch (NoSuchFieldException e) {
										e.printStackTrace();
									} catch (IllegalArgumentException e) {
										e.printStackTrace();
									} catch (IllegalAccessException e) {
										e.printStackTrace();
									}
								}
								employeeguid = importbean.getHos().split("\\|")[0];
								datalist.add(importbean);
							} else
								continue;
						} else {
							// 判断不能为空
							if (must.equals("") == false) {
								for (int x = 0; x < must.split(",").length; x++) {
									keyvalue = xmlDocument.get("request.data["
											+ j + "]." + must.split(",")[x]);
									keyvalue = keyvalue == null ? "" : keyvalue
											.trim();
									if (keyvalue.equals("")) {
										flag = false;
										break;
									}
								}
							}
							if (flag) {
								for (String key : keylist) {
									keyvalue = xmlDocument.get("request.data["
											+ j + "]." + key);
									keyvalue = keyvalue == null ? "" : keyvalue
											.trim();
									if (key.equals("collectenum")) {
										if (keyvalue.equals("") == false) {
											if (keyvalue.length() > 8) {
												Map<String, Object> hos = BiosanUtils
														.getMap("select d.code||'|'||d.departmentname as hos,a.code||'|'||a.areaname as area "
																+ " from ground_tb_department d "
																+ " left join ground_tb_area a on a.areaid=d.areaid "
																+ "where d.departmenttype='hos' and rownum=1"
																+ " and d.code='"
																+ keyvalue.substring(0, 10)
																+ "'");
												String hosStr = "";
												String areaStr = "";
												if (hos != null) {
													hosStr = hos.get("hos").toString();
													areaStr = hos.get("area").toString();
												} 
												Field f = c.getDeclaredField("hos");
												f.set(importbean, hosStr);
												
												f = c.getDeclaredField("area_ul");
												f.set(importbean, areaStr);
											}

										}
									}
//									if (key.equals("labitemname")) {
//										if (keyvalue.equals("") == false) {
//											if (keyvalue.equals("4项")) {
//												keyvalue = "labitem_PHE,labitem_TSH,labitem_17_a_OHP,labitem_G6PD";
//											}
//											if (keyvalue.equals("29项")) {
//												keyvalue = "labitem_TSH,labitem_17_a_OHP,labitem_G6PD,labitem_MSMS";
//											}
//										}
//									}
									
									try {
										Field f = c.getDeclaredField(key);
										f.set(importbean, keyvalue);
									} catch (SecurityException e) {
										log.info(e);
										e.printStackTrace();
									} catch (NoSuchFieldException e) {
										log.info(e);
										e.printStackTrace();
									} catch (IllegalArgumentException e) {
										log.info(e);
										e.printStackTrace();
									} catch (IllegalAccessException e) {
										log.info(e);
										e.printStackTrace();
									}
								}
								employeeguid = importbean.getHos().split("\\|")[0];
								datalist.add(importbean);
							} else
								continue;
						}
					}
					log.info("datalist size " + datalist.size());
					if (datalist.size() != 0) {
						insertData(dir + fileName, datalist);
					}
				} else
					continue;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * 使用BiosanUtils
	 * @param renamefile
	 * @param datalist
	 */
	private void insertData(String renamefile, ArrayList<ImportBean> datalist) {
		log.info("写数据库！");
		String insert_sql = "insert into ground_tb_interface_fubao(interfaceid, samplestring, collectnum, patientname, mobile, hos, area, addtime, details, birthday, operatorid, barcode, collectedate, labitemname) "
				+ "values(GROUND_SEQ_INTERFACE_FUBAO.NEXTVAL, ?, ?, ?, ?, ?, ?, sysdate, ?, to_date(?, 'yyyy-MM-dd hh24:mi:ss'), ?, ?, to_date(?, 'yyyy-MM-dd hh24:mi:ss'), ?)";
		String select_exists = "select count(*) from ground_tb_interface_fubao where samplestring = ?";
		boolean flag = true;
		String mothername = "";
		String failmothername = "";
		try {
			log.info("总共数据： " + datalist.size());
			for (ImportBean ib : datalist) {
				try {
        			String count = "0";
        			String hos = ib.getHos();
        			String area = ib.getArea_ul();
        			if (!"".equals(ib.getSamplestring())) {
        				Object[] exists_param = { ib.getSamplestring() };
        				count = BiosanUtils.getSingle(select_exists, exists_param).toString();
        			}
        			if ("0".equals(count)) {
        				Object[] params = { ib.getSamplestring(), ib.getCollectenum(), ib.getPatientname(), ib.getMobile(), hos, area, 
        						BiosanUtils.ConvertToJson(ib).toString(), ib.getBirthday(), "", ib.getBarcode(), ib.getCollectedate(), ib.getLabitemname() };
            			int isSuccess = BiosanUtils.executeUpdate(insert_sql, params);
            			if (isSuccess == 0) {
            				flag = false;
            				failmothername += "," + ib.getPatientname();
            			}
        			} else {
        				flag = false;
        				mothername += "," + ib.getPatientname();
        			}
				} catch (Exception e) {
					e.printStackTrace();
					log.info("处理失败！samplestring" + ib.getSamplestring());
				}
			}
			String resultMsg = "";
    		if ("".equals(mothername) ==  false) {
    			mothername = mothername.substring(1);
    			resultMsg += "，有重复数据上传，母亲姓名为：（" + mothername + "）";
    		}
    		if ("".equals(failmothername) ==  false) {
    			failmothername = failmothername.substring(1);
    			resultMsg += "，插入失败，母亲姓名为：（" + failmothername + "）";
    		}
    		if ("".equals(resultMsg) ==  false) {
    			resultMsg = resultMsg.substring(1);
    		}
			log.info("提交！");
			log.info("写库完成！");
			String backstring = "_bak";
			if (flag == false) {
				log.info("写库结果：" + resultMsg);
				backstring = "_error";
    		} else {
    			log.info("写库结果：无错误！");
    			backstring = "_bak";
    		}
			// 重命名文件
			String newfile = renamefile + backstring;
			fileUtil.renameFile(renamefile, newfile);
		} catch (Exception e1) {
			e1.printStackTrace();
			log.info("" + e1);
		}
	}

}

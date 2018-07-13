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
 * 福建接口
 * 
 * @author qibill
 *
 */
public class ImportDataFJ

{
	private Logger log = Logger.getLogger(ImportDataFJ.class);
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
			// InputStream inputStream = null;
			p.load(UploadService.class.getResourceAsStream("/dbcp.properties"));
			must = p.getProperty("must");
			dir = p.getProperty("dir");
			encode = p.getProperty("encode");
			senddepartment = p.getProperty("departmentid");
			localsenddepartment = senddepartment;
			business = p.getProperty("business");
			type = p.getProperty("type");
			// log.info(must);
			dataSource.setDriverClassName(p.getProperty("driverClass"));
			dataSource.setUrl(p.getProperty("jdbcUrltemp"));
			dataSource.setUsername(p.getProperty("usertemp"));
			dataSource.setPassword(p.getProperty("passwordtemp"));
			dataSource.setMaxActive(maxactive);
			dataSource.setMaxIdle(maxidle);
			dataSource.setMaxWait(maxwait);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		keylist.add("barcode");
		keylist.add("collectenum");
		keylist.add("patientname");
		keylist.add("birthday");
		keylist.add("mobile");
		keylist.add("telephone");
		keylist.add("sex");
		keylist.add("preg_realcycle");
		keylist.add("childname");
		keylist.add("address");
		keylist.add("weight");
		keylist.add("bednum");
		keylist.add("contacter");
		keylist.add("admissionnum");
		keylist.add("experimentitem");
		keylist.add("collectedate");
		keylist.add("operdate");
		keylist.add("hos");
		keylist.add("area");
		keylist.add("household");
		keylist.add("housetype");
		keylist.add("remark");
		keylist.add("bron_type");
		keylist.add("istosix");
		keylist.add("antibiotic");
		keylist.add("apgar");
		keylist.add("collector");
		keylist.add("postcode");
		keylist.add("reserved1");
		keylist.add("operatedate");
		// keylist.add("paramer2");
		// keylist.add("paramer3");
		// keylist.add("paramer4");
		// keylist.add("paramer5");
		// keylist.add("paramer6");

	}

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
				// doc = fileUtil.readAsText(dir + fileName);

				byte[] fileBytes = fileUtil.readAsBytes(dir + fileName);
				if (!(fileBytes != null && fileBytes.length > 0))
					return;

				// 去除windows utf-8 BOM
				// if (fileBytes != null && fileBytes.length > 3) {
				// if (fileBytes[0] == -17)
				// fileBytes[0] = 32;
				// if (fileBytes[1] == -69)
				// fileBytes[1] = 32;
				// if (fileBytes[2] == -65)
				// fileBytes[2] = 32;
				// }
				
				// 判断文件的编码格式
//				encode = getFileEncode(dir + fileName);
				log.info("文件编码格式为：" + encode);
				
				// 转字符串
				try {
					doc = new String(fileBytes, encode);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					// return;
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
						log.info("into data..");
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
							log.info(flag);
							if (flag) {
								String hos = "";
								for (String key : keylist) {
									keyvalue = xmlDocument.get("request.data."
											+ key);
									keyvalue = keyvalue == null ? "" : keyvalue
											.trim();
									
									// 把 医院保存下来用于查询地区
									if ("hos".equals(key) && !"".equals(keyvalue))
										hos = keyvalue.split("\\|")[1];
									// 地区为空时的操作（根据医院去数据库取地区）
									if ("area".equals(key) && "".equals(keyvalue)) {
										String select_area = "select a.code||'|'||a.areaname area_ul from ground_tb_area a where a.areaid in " +
												"(select t.areaid from ground_tb_department t where t.departmentname = ?) and rownum < 2";
										log.info("" + hos);
										Object[] hos_param = { hos };
										if (BiosanUtils.getSingle(select_area, hos_param) != null && !"".equals(BiosanUtils.getSingle(select_area, hos_param).toString()))
											keyvalue = BiosanUtils.getSingle(select_area, hos_param).toString();
									}
									try {
										Field f = c.getDeclaredField(key);
										f.set(importbean, keyvalue);
									} catch (SecurityException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (NoSuchFieldException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IllegalArgumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								employeeguid = importbean.getHos().split("\\|")[0];
								datalist.add(importbean);
								// System.out.println(importbean.getPatientname());
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
							log.info(flag);
							if (flag) {
								String hos = "";
								log.info("keylist" + keylist.size());
								for (String key : keylist) {
									keyvalue = xmlDocument.get("request.data["
											+ j + "]." + key);
									keyvalue = keyvalue == null ? "" : keyvalue
											.trim();
									
									log.info("key" + key);
									log.info("keyvalue" + keyvalue);
									
									// 把 医院保存下来用于查询地区
									if ("hos".equals(key) && !"".equals(keyvalue))
										hos = keyvalue.split("\\|")[1];
									// 地区为空时的操作（根据医院去数据库取地区）
									if ("area".equals(key) && "".equals(keyvalue)) {
										String select_area = "select a.code||'|'||a.areaname area_ul from ground_tb_area a where a.areaid in " +
												"(select t.areaid from ground_tb_department t where t.departmentname = ?) and rownum < 2";
										log.info("" + hos);
										Object[] hos_param = { hos };
										if (BiosanUtils.getSingle(select_area, hos_param) != null && !"".equals(BiosanUtils.getSingle(select_area, hos_param).toString()))
											keyvalue = BiosanUtils.getSingle(select_area, hos_param).toString();
									}
									try {
										Field f = c.getDeclaredField(key);
										f.set(importbean, keyvalue);
									} catch (SecurityException e) {
										// TODO Auto-generated catch block
										log.info(e);
										e.printStackTrace();
									} catch (NoSuchFieldException e) {
										// TODO Auto-generated catch block
										log.info(e);
										e.printStackTrace();
									} catch (IllegalArgumentException e) {
										// TODO Auto-generated catch block
										log.info(e);
										e.printStackTrace();
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										log.info(e);
										e.printStackTrace();
									}
								}
								log.info("data over");
								employeeguid = importbean.getHos().split("\\|")[0];
								datalist.add(importbean);
							} else
								continue;

						}
					}
					log.info("datalist size " + datalist.size());
					if (datalist.size() != 0) {
						if (type.equals("0"))
							insertData(dir + fileName, datalist);
						else if (type.equals("1"))
							importsampledata(dir + fileName, datalist);
					}
				} else
					continue;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void insertData(String renamefile, ArrayList<ImportBean> datalist) {
		log.info("写数据库！");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		PreparedStatement pssql = null;
		PreparedStatement ps = null;
		// 判断存在
		String select_sql = "select id from ground_tb_importtemp where  patientname=? and BARCODESTRING=?";
		// 插入数据
		String insert_sql = "insert into ground_tb_importtemp(ID,SAMPLESTRING,BARCODESTRING,PATIENTNAME,BIRTHDAY,"
				+ " MOBILE,TELEPHONE,SEX,PREG_REALCYCLE,CHILDNAME,"
				+ " ADDRESS,WEIGHT,ADMISSIONNUM,EXPERIMENTITEM,COLLECTEDATE,"
				+ " AREA_UL,HOS,HOUSEHOLD,HOUSETYPE,REMARK,"
				+ " PARAMER1,PARAMER2,PARAMER3,PARAMER4,PARAMER5,"
				+ " PARAMER6,istosix,antibiotic,apgar,IMPORTDATE,PREGCARENUM,doc,postcode,bednum,bron_type,OPERATEDATE) values(GROUND_SEQ_IMPORTTEMP.nextval,?,?,?,?,"
				+ " ?,?,?,?,?,"
				+ " ?,?,?,?,?,"
				+ " ?,?,?,?,?,"
				+ " ?,?,?,?,?,"
				+ " ?,?,?,?,sysdate,?,?,?,?,?,?)";
		// 修改数据
		String update_sql = "update ground_tb_importtemp set SAMPLESTRING=?,BIRTHDAY=?,MOBILE=?,TELEPHONE=?,SEX=?,"
				+ " PREG_REALCYCLE=?,CHILDNAME=?,ADDRESS=?,WEIGHT=?,ADMISSIONNUM=?,"
				+ " EXPERIMENTITEM=?,COLLECTEDATE=?,AREA_UL=?,HOS=?,HOUSEHOLD=?,"
				+ " HOUSETYPE=?,REMARK=?,PARAMER1=?,PARAMER2=?,PARAMER3=?,"
				+ " PARAMER4=?,PARAMER5=?,PARAMER6=?,IMPORTDATE=sysdate,istosix=?,"
				+ " antibiotic=?,apgar=?,PREGCARENUM=?,doc=?,postcode=?,bednum=?,bron_type=?,OPERATEDATE=? where patientname=? and BARCODESTRING=?";

		try {
			log.info("取连接！");
			connection = dataSource.getConnection();
			log.info("取到连接！");
			log.info("总共数据： " + datalist.size());
			int j = 0;
			for (ImportBean importbean : datalist) {
				try {
					connection.setAutoCommit(false);
					String id = "";
					statement = connection.prepareStatement(select_sql);
					statement.setString(1, importbean.getPatientname());
					statement.setString(2, importbean.getBarcode());
					resultSet = statement.executeQuery();
					while (resultSet.next()) {
						id = resultSet.getString("id");
					}
					resultSet.close();
					statement.close();
					if (id.equals("")) {
						pssql = connection.prepareStatement(insert_sql);
						pssql.setString(1, importbean.getSamplestring());
						pssql.setString(2, importbean.getBarcode());
						pssql.setString(3, importbean.getPatientname());
						pssql.setString(4, importbean.getBirthday());
						pssql.setString(5, importbean.getMobile());
						pssql.setString(6, importbean.getTelephone());
						pssql.setString(7, importbean.getSex());
						pssql.setString(8, importbean.getPreg_realcycle());
						pssql.setString(9, importbean.getChildname());
						pssql.setString(10, importbean.getAddress());
						pssql.setString(11, importbean.getWeight());
						pssql.setString(12, importbean.getAdmissionnum());
						String exp = importbean.getExperimentitem();
						exp = "labitem_" + exp;
						exp = exp.replaceAll(",", ",labitem_");
						pssql.setString(13, exp);
						pssql.setString(14, importbean.getCollectedate());
						pssql.setString(15, importbean.getArea());
						pssql.setString(16, importbean.getHos());
						pssql.setString(17, importbean.getHousehold());
						pssql.setString(18, importbean.getHousetype());
						pssql.setString(19, importbean.getRemark());
						pssql.setString(20, importbean.getParamer1());
						pssql.setString(21, importbean.getParamer2());
						pssql.setString(22, importbean.getParamer3());
						pssql.setString(23, importbean.getParamer4());
						pssql.setString(24, importbean.getParamer5());
						pssql.setString(25, importbean.getParamer6());
						pssql.setString(26, importbean.getIstosix());
						pssql.setString(27, importbean.getAntibiotic());
						pssql.setString(28, importbean.getApgar());
						pssql.setString(29, importbean.getReserved1());
						pssql.setString(30, importbean.getCollector());
						pssql.setString(31, importbean.getPostcode());
						pssql.setString(32, importbean.getBednum());
						pssql.setString(33, importbean.getBron_type());
						pssql.setString(34, importbean.getOperatedate());
						pssql.execute();
						pssql.close();
					} else {
						ps = connection.prepareStatement(update_sql);
						ps.setString(1, importbean.getSamplestring());
						ps.setString(2, importbean.getBirthday());
						ps.setString(3, importbean.getMobile());
						ps.setString(4, importbean.getTelephone());
						ps.setString(5, importbean.getSex());
						ps.setString(6, importbean.getPreg_realcycle());
						ps.setString(7, importbean.getChildname());
						ps.setString(8, importbean.getAddress());
						ps.setString(9, importbean.getWeight());
						ps.setString(10, importbean.getAdmissionnum());
						String exp = importbean.getExperimentitem();
						exp = "labitem_" + exp;
						exp = exp.replaceAll(",", ",labitem_");
						ps.setString(11, exp);
						ps.setString(12, importbean.getCollectedate());
						ps.setString(13, importbean.getArea());
						ps.setString(14, importbean.getHos());
						ps.setString(15, importbean.getHousehold());
						ps.setString(16, importbean.getHousetype());
						ps.setString(17, importbean.getRemark());
						ps.setString(18, importbean.getParamer1());
						ps.setString(19, importbean.getParamer2());
						ps.setString(20, importbean.getParamer3());
						ps.setString(21, importbean.getParamer4());
						ps.setString(22, importbean.getParamer5());
						ps.setString(23, importbean.getParamer6());
						ps.setString(24, importbean.getIstosix());
						ps.setString(25, importbean.getAntibiotic());
						ps.setString(26, importbean.getApgar());
						ps.setString(27, importbean.getReserved1());
						ps.setString(28, importbean.getCollector());
						ps.setString(29, importbean.getPostcode());
						ps.setString(30, importbean.getBednum());
						ps.setString(31, importbean.getBron_type());
						ps.setString(32, importbean.getOperatedate());
						ps.setString(33, importbean.getPatientname());
						ps.setString(34, importbean.getBarcode());
						ps.execute();
						ps.close();
					}
					connection.commit();
				} catch (Exception e) {
					e.printStackTrace();
					log
							.info("处理失败！samplestring"
									+ importbean.getSamplestring());
				}
				j++;
			}
			log.info("提交！");
			log.info("处理数据： " + j);
			connection.close();
			resultSet = null;
			statement = null;
			log.info("写库完成！");
			// 重命名文件
			String newfile = renamefile + "_bak";
			fileUtil.renameFile(renamefile, newfile);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			e1.printStackTrace();
		}

	}

	// 样本导入
	public void importsampledata(String renamefile,
			ArrayList<ImportBean> datalist) {
		log.info("样本开始导入！");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		PreparedStatement ps = null;
		try {
			log.info("取连接！");
			connection = dataSource.getConnection();
			if (connection == null) {
				return;
			}
			log.info("取到连接！");
			senddepartment = localsenddepartment;
			Date nowDate = new Date();
			connection.setAutoCommit(false);
			long batchBarcodeId = 0l;
			// long operDepartmentId = 0;

			String sql_select_employee = "select em.departmentid,em.employeeid,de.deliverydid from ground_tb_employee em "
					+ "left join ground_tb_department de on em.departmentid=de.departmentid where em.employeeguid=?";
			statement = connection.prepareStatement(sql_select_employee);
			statement.setString(1, employeeguid);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				departmentid = resultSet.getString("departmentid");
				employeeid = resultSet.getString("employeeid");
				if (senddepartment.equals(""))
					senddepartment = departmentid;
				// senddepartment = resultSet.getString("deliverydid");
			}
			resultSet.close();
			statement.close();

			// 计算批条形码 取得条形码id
			String sql_select_barcodeid = "select ground_seq_barcode.nextval as barcodeid from dual";
			statement = connection.prepareStatement(sql_select_barcodeid);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				batchBarcodeId = resultSet.getInt("barcodeid");
			}
			resultSet.close();
			statement.close();
			resultSet = null;
			statement = null;
			// 生成条形码
			String batchBarcode = GroundMethod.getBatchBarCodeSerialNumber(
					String.valueOf(ConstantInterface.BATCH_TYPE_EXPERIMENT),
					String.valueOf(batchBarcodeId));

			// 插入条形码
			String sql_insert_barcode = "insert into ground_tb_barcode(barcodeid,barcodestring,state) values(?,?,1)";
			statement = connection.prepareStatement(sql_insert_barcode);
			statement.setLong(1, batchBarcodeId);
			statement.setString(2, batchBarcode);
			statement.execute();
			statement.close();
			statement = null;

			// 新建样本批号
			String sql_insert_batch = "insert into ground_tb_sample_batch(batchid,barcodeid,createdate,operatorid,state,batchtypeid,batchnum,senddate) values(ground_seq_sample_batch.nextval,?,?,?,1,?,?,sysdate)";
			statement = connection.prepareStatement(sql_insert_batch);
			statement.setLong(1, batchBarcodeId);
			statement.setDate(2, GroundMethod.changeDateType("", nowDate));
			statement.setString(3, employeeid);
			statement.setInt(4, ConstantInterface.BATCH_TYPE_EXPERIMENT);
			statement.setString(5, batchBarcode);
			statement.execute();
			statement.close();
			statement = null;

			// 取得批号
			long batchId = 0l;
			String sql_select_batchid = "select ground_seq_sample_batch.currval as batchid from dual";
			statement = connection.prepareStatement(sql_select_batchid);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				batchId = resultSet.getLong("batchid");
			}
			resultSet.close();
			statement.close();
			statement = null;

			// 写批流动日志表,状态写成1
			// String sql_insert_batch_log = "insert into ground_tb_sa_ba_log("
			// + "logid,batchid,rdepartmentid,roperatorid,ddepartmentid,"
			// + "doperatorid,operatorid,operatedate,state,remark) values("
			// + "ground_seq_sample_log.nextval,?,?,?,?," + "?,?,?,1,?)";
			// statement = connection.prepareStatement(sql_insert_batch_log);
			// statement.setLong(1, batchId);
			// statement.setString(2, departmentid);
			// statement.setString(3, employeeid);
			// statement.setString(4, departmentid);
			// statement.setString(5, employeeid);
			// statement.setString(6, employeeid);
			// statement.setTimestamp(7, GroundMethod.changeTimestampType("",
			// nowDate));
			// statement.setString(8, "");
			// statement.execute();
			// statement.close();

			// 新建样本和样本批映射
			String sql_sample_batch_mapping = "insert into ground_tb_sa_ba_mapping(id,batchid,sampleid,remark,state,operatorid,departmentid,senddate,key,flag,SDEPARTMENTID,SENDTYPE"
					+ ") values(ground_seq_sa_ba_mapping.nextval,?,?,?,1,?,?,?,?,1,?,2)";
			ps = connection.prepareStatement(sql_sample_batch_mapping);
			ps.clearBatch();
			for (ImportBean importbean : datalist) {
				// 判断条码
				System.out.println(importbean.getBarcode());
				int sampleid = 0;
				String sQuery = "select sampleid from ground_tp_create_data where samplebarcode=?";
				statement = connection.prepareStatement(sQuery);
				statement.setString(1, importbean.getBarcode());
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					sampleid = resultSet.getInt("sampleid");
				}
				resultSet.close();
				statement.close();
				resultSet = null;
				statement = null;

				if (sampleid != 0) {
					log.info(importbean.getBarcode() + "重复！");
					continue;
				}

				// long collertoDepartmentId = 0;
				// String sql_select_department =
				// "select de.departmentid,de.employeeid,de.areaid from ground_vw_dep_emp de"
				// + " where de.depcode = ? ";
				// statement =
				// connection.prepareStatement(sql_select_department);
				// statement.setString(1, importbean.getHos().split("|")[0]);
				// resultSet = statement.executeQuery();
				// if (resultSet.next()) {
				// collertoDepartmentId = resultSet.getLong("departmentid");
				// }
				// resultSet.close();
				// statement.close();
				// resultSet = null;
				// statement = null;

				HashMap<String, String> map = new HashMap<String, String>();

				String item = importbean.getExperimentitem();
				item = "labitem_" + item;
				item = item.replaceAll(",", ",labitem_");

				long childId = 0;
				long patientId = 0;
				int accountId = 0;
				String workKey = "";

				/**
				 * 新增婴儿的实体
				 */
				String select_patient = "select ground_seq_patient.nextval as id from dual";
				statement = connection.prepareStatement(select_patient);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					childId = resultSet.getLong("id");
					patientId = childId;
				}
				resultSet.close();
				statement.close();
				resultSet = null;
				statement = null;

				/**
				 * 病人的GUID
				 */
				String patientGuid = GroundMethod
						.getGuidSerialNumber(
								String
										.valueOf(ConstantInterface.DEFAULT_PATIENT_ACCOUNT_TYPE),
								String.valueOf(patientId));
				/**
				 * 生成新的账号
				 */
				String accountGuid = GroundMethod
						.getAccountSerialNumber(
								String
										.valueOf(ConstantInterface.DEFAULT_PATIENT_ACCOUNT_TYPE),
								patientGuid);

				accountId = 0;
				String select_account_id = "select ground_seq_account.nextval as id from dual";
				statement = connection.prepareStatement(select_account_id);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					accountId = resultSet.getInt("id");
				}
				resultSet.close();
				statement.close();
				resultSet = null;
				statement = null;

				String sql_insert_account = "insert into ground_tb_account(accountid,accountguid,balance,state) values(?,?,0,1)";
				statement = connection.prepareStatement(sql_insert_account);
				statement.setInt(1, accountId);
				statement.setString(2, accountGuid);
				statement.executeUpdate();
				statement.close();
				statement = null;

				String sql_insert_pa = "insert into GROUND_TB_PATIENT(patientid,patientguid,ILLHISGUID,"
						+ " RACE,BARCODE,IDENTITYCARD,PATIENTNAME,BIRTHDAY,PASSPORTNUM,OFFICERNUM,PREGNANCYNUM,PREGCARENUM,"
						+ " PERMITNUM,BEDNUM,mobile,telephone,DOMICILE,ADDRESS,POSTALCODE,WORKTYPE,WORKPOSTALCODE,WORKAROUND,WORKUNITS,state,"
						+ "LANGUAGE,OPERATEDATE,BUSINESS,RELATEID,patienttypeid,accountid,operatorid,MOIDFIER,remark,childbirthday,weight,sex,housetype,household) "
						+ " values(?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,1,"
						+ "?,?,1,1,2,?,?,?,?,?,?,?,?,?)";

				statement = connection.prepareStatement(sql_insert_pa);
				statement.setLong(1, childId);
				statement.setString(2, patientGuid);
				statement.setString(3, "");
				statement.setString(4, "");
				statement.setString(5, "");
				statement.setString(6, "");
				statement.setString(7, importbean.getPatientname());
				statement.setDate(8, GroundMethod.changeDateType(importbean
						.getBirthday(), nowDate));
				statement.setString(9, "");
				statement.setString(10, "");
				statement.setString(11, "");
				statement.setString(12, "");
				statement.setString(13, "");
				statement.setString(14, importbean.getBednum());
				statement.setString(15, importbean.getMobile());// 移动电话
				statement.setString(16, importbean.getTelephone());// 电话
				statement.setString(17, importbean.getContacter());
				statement.setString(18, importbean.getAddress());// 地址
				statement.setString(19, "");
				statement.setString(20, "");
				statement.setString(21, "");
				statement.setString(22, "");
				statement.setString(23, "");
				statement.setString(24, importbean.getChildname());
				statement.setDate(25, GroundMethod.changeDateType("", nowDate));
				statement.setInt(26, accountId);
				statement.setString(27, employeeid);
				statement.setString(28, employeeid);
				statement.setString(29, "");
				statement.setDate(30, GroundMethod.changeDateType(importbean
						.getBirthday(), nowDate));// 小孩生日
				statement.setString(31, importbean.getWeight());// 小孩体重
				statement.setString(32, importbean.getSex());// 小孩性别

				String hostype = importbean.getHousetype();
				hostype = hostype == null ? "" : hostype;
				if (hostype.equals(""))
					hostype = "1";
				else if (hostype.equals("城市"))
					hostype = "1";
				else if (hostype.equals("农村"))
					hostype = "2";

				String hoshold = importbean.getHousehold();
				hoshold = hoshold == null ? "" : hoshold;
				if (hoshold.equals(""))
					hoshold = "1";
				else if (hoshold.equals("省内"))
					hoshold = "1";
				else if (hoshold.equals("省外"))
					hoshold = "2";
				statement.setString(33, hostype);// 户口类型
				statement.setString(34, hoshold);// 户籍地
				statement.execute();
				statement.close();
				statement = null;

				/*
				 * 生成KEY
				 */
				long seq = 0l;
				String select_seq_key = "select ground_seq_workgrid_key.nextval as workkey from dual";
				statement = connection.prepareStatement(select_seq_key);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					seq = resultSet.getLong("workkey");
				}
				resultSet.close();
				statement.close();
				statement = null;
				resultSet = null;

				workKey = GroundMethod.getKey(patientId, seq,
						ConstantInterface.PROCESS_FETUS_TYPE);

				String insert_key_mapping = "insert into ground_tb_key_mapping ("
						+ "id,WORKKEY,WORKVALUE,VALUETYPE,PROCESSTYPE,version,state,OPERATEDATE,operatorid)values("
						+ "ground_seq_key_mapping.nextval,?,?,?,2,1,?,?,?)";
				statement = connection.prepareStatement(insert_key_mapping);
				statement.setString(1, workKey);
				statement.setLong(2, childId);
				statement.setInt(3, 1);
				statement.setLong(4, 9);// 初始状态为9
				statement.setTimestamp(5, GroundMethod.changeTimestampType("",
						nowDate));
				statement.setString(6, employeeid);
				statement.execute();
				statement.close();
				statement = null;

				long barcodeId = 0;

				// 判断操作人部门是否存在
				if (departmentid.equals("")) {
					System.out.println("部门不存在!");
				} else {
					// 取条形码序列
					String select_sampel_barcod_seq = "select ground_seq_barcode.nextval as id from dual";
					statement = connection
							.prepareStatement(select_sampel_barcod_seq);
					resultSet = statement.executeQuery();
					while (resultSet.next()) {
						barcodeId = resultSet.getLong("id");
					}
					resultSet.close();
					statement.close();
					statement = null;
					resultSet = null;

					// 插入条形码
					sql_insert_barcode = "insert into ground_tb_barcode(barcodeid,barcodestring,remark,state) values(?,?,?,?)";
					statement = connection.prepareStatement(sql_insert_barcode);
					statement.setLong(1, barcodeId);
					statement.setString(2, importbean.getBarcode());
					statement.setString(3, "");
					statement.setInt(4, 1);
					statement.execute();
					statement.close();
					statement = null;

					long sampleId = 0;
					// 插入样本
					String sql_select_sampleid = "select ground_seq_sample.nextval as sampleid from dual";
					statement = connection
							.prepareStatement(sql_select_sampleid);
					resultSet = statement.executeQuery();
					while (resultSet.next()) {
						sampleId = resultSet.getInt("sampleid");
					}
					resultSet.close();
					statement.close();
					statement = null;
					resultSet = null;

					// 插入样本表
					String sql_insert_sample = "insert into ground_tb_sample (sampleid,patientid,collectedate,"
							+ " barcodeid,state,operateorid,operatedate,modifier,business,relateid,"
							+ " SAMPLETYPEID,SAMPLEPASS,COLLECTORID,samplestring,cardtypeid,attribute,realcollectdate) "
							+ " values(ground_seq_sample.currval,?,?,"
							+ " ?,1,?,?,?,1,0," + "?,1,?,?,?,?,?)";
					statement = connection.prepareStatement(sql_insert_sample);
					statement.setLong(1, childId);
					statement.setDate(2, GroundMethod.changeDateType(importbean
							.getCollectedate(), new Date()));// 采样时间
					statement.setLong(3, barcodeId);
					statement.setString(4, employeeid);
					statement.setTimestamp(5, GroundMethod.changeTimestampType(
							"", new Date()));
					statement.setString(6, employeeid);
					statement.setInt(7, ConstantInterface.SAMPLE_DEFAULT);
					statement.setLong(8, Long.valueOf(departmentid));// 采样人idcollertoDepartmentId
					statement.setString(9, importbean.getSamplestring());// labString
					statement.setLong(10, 1);
					statement.setString(11, "合格");
					statement.setDate(12, GroundMethod.changeDateType(
							importbean.getCollectedate(), nowDate));// 采样时间
					statement.execute();
					statement.close();
					statement = null;

					// 样本状态表
					String insert_sds = "insert into ground_tb_sam_dep_business(id,sampleid,departmentid,state,remark,operatedate,business,flag) "
							+ " values(ground_seq_sam_dep_state.nextval,?,?,?,?,sysdate,?,?)";
					statement = connection.prepareStatement(insert_sds);
					statement.setLong(1, sampleId);
					statement.setString(2, senddepartment);
					statement.setInt(3, 1);
					statement.setString(4, "newborn");
					statement.setString(5, business);// 1：录入状态 2：待提交 3：待验收,8:加样
					statement.setInt(6, 2);
					statement.execute();
					statement.close();
					statement = null;

					insert_key_mapping = "insert into ground_tb_key_mapping ("
							+ "id,WORKKEY,WORKVALUE,VALUETYPE,PROCESSTYPE,version,state,OPERATEDATE,operatorid)values("
							+ "ground_seq_key_mapping.nextval,?,?,?,2,1,?,?,?)";
					statement = connection.prepareStatement(insert_key_mapping);
					statement.setString(1, workKey);
					statement.setLong(2, sampleId);
					statement.setInt(3, 2);
					statement.setLong(4, 9);
					statement.setTimestamp(5, GroundMethod.changeTimestampType(
							"", nowDate));
					statement.setString(6, employeeid);
					statement.execute();
					statement.close();
					statement = null;

					ps.setLong(1, batchId);
					ps.setLong(2, sampleId);
					ps.setString(3, "");
					ps.setString(4, employeeid);
					ps.setString(5, departmentid);
					ps.setDate(6, GroundMethod.changeDateType("", nowDate));
					ps.setString(7, workKey);
					ps.setString(8, senddepartment);
					ps.addBatch();

					map.put("patientid", String.valueOf(patientId));
					map.put("sampleid", String.valueOf(sampleId));
					map.put("samplebarcode", importbean.getBarcode());
					map.put("experimentitem", item);
					map.put("Infant_weight", importbean.getWeight());
					map.put("admissionnum", importbean.getAdmissionnum());
					map.put("preg_realcycle", importbean.getPreg_realcycle());
					map.put("area_ul", importbean.getArea());
					map.put("hos", importbean.getHos());
					map.put("collectnum", importbean.getCollectenum());
					map.put("bron_type", importbean.getBron_type());

					String insert = "insert into ground_tp_create_data(id,key,state) values(GROUND_SEQ_TP_CREATE_DATA.nextval,?,1)";
					statement = connection.prepareStatement(insert);
					statement.setString(1, workKey);
					statement.execute();
					statement.close();
					statement = null;

					/**
					 * 查询标准名跟列名的对应关系
					 */
					HashMap<String, String> smap = new HashMap<String, String>();
					String select_cs = "select cs.standardname,cs.createname from ground_tp_creat_stand cs";
					statement = connection.prepareStatement(select_cs);
					resultSet = statement.executeQuery();
					String ss = "";
					while (resultSet.next()) {
						ss = resultSet.getString("createname");// 列名
						smap.put(resultSet.getString("standardname"), ss);// 标准名,列名
					}
					resultSet.close();
					statement.close();
					statement = null;
					resultSet = null;

					// 插入、修改其他 数据到新增筛查数据表
					Set<String> s = map.keySet();
					Iterator<String> it = s.iterator();
					String update_create = "update ground_tp_create_data set ";
					String condition = " where key='" + workKey + "'";
					String createnameq = "";
					int a = 1;
					while (it.hasNext()) {
						String kk = it.next();// 标准名
						createnameq = smap.get(kk);
						if (createnameq != null) {
							if (a == 1) {
								update_create += createnameq + "='"
										+ map.get(kk) + "'";
								a++;
							} else {
								update_create += "," + createnameq + "='"
										+ map.get(kk) + "'";
							}
						}

					}
					statement = connection.prepareStatement(update_create
							+ condition);
					statement.execute();
					statement.close();
					statement = null;

				}
			}

			if (ps != null) {
				ps.executeBatch();
				ps.close();
			}
			log.info("提交！");
			connection.commit();
			log.info("写库完成！");

			connection.close();
			resultSet = null;
			statement = null;
			// 重命名文件
			fileUtil.renameFile(renamefile, renamefile + "_bak");

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			try {
				if (statement != null)
					statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			e1.printStackTrace();
		}

	}
	
	/**
	 * 利用第三方开源包cpdetector获取文件编码格式
	 * @param path 要判断文件编码格式的源文件的路径
	 * @return 编码格式
	 */
	public static String getFileEncode(String path) {
		/*
		 * detector是探测器，它把探测任务交给具体的探测实现类的实例完成。
		 * cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法 加进来，如ParsingDetector、
		 * JChardetFacade、ASCIIDetector、UnicodeDetector。
		 * detector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的
		 * 字符集编码。使用需要用到三个第三方JAR包：antlr.jar、chardet.jar和cpdetector.jar
		 */
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		/*
		 * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于
		 * 指示是否显示探测过程的详细信息，为false不显示。
		 */
		detector.add(new ParsingDetector(false));
		/*
		 * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
		 * 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以
		 * 再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
		 */
		detector.add(JChardetFacade.getInstance());// 用到antlr.jar、chardet.jar
		// ASCIIDetector用于ASCII编码测定
		detector.add(ASCIIDetector.getInstance());
		// UnicodeDetector用于Unicode家族编码的测定
		detector.add(UnicodeDetector.getInstance());
		java.nio.charset.Charset charset = null;
		File f = new File(path);
		try {
			charset = detector.detectCodepage(f.toURI().toURL());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (charset != null)
			return charset.name();
		else
			return null;
	}
}

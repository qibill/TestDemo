package net.biosan.net.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import net.biosan.net.ConstantInterface;
import net.biosan.net.GroundMethod;
import net.biosan.net.ImportBean;
import net.marginland.mland.common.utils.file.FileUtil;
import net.marginland.mland.common.utils.xml.XmlDocument3;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import com.biosan.UploadService;
import com.biosan.utils.BiosanUtils;

/**
 * 
 * @author qibill
 */
public abstract class ImportData {

	private static final Logger logger = Logger.getLogger(ImportData.class);
	
	private FileUtil fileUtil = new FileUtil();
	private BasicDataSource dataSource = new BasicDataSource();
	private String must = "";
	private String departmentid = "";
	private String employeeid = "";
	private String business = "";
	private String type = "";
	private String dir = "";
	//编码
	private String encode = "utf-8";
	
	private List<String> keylist = new ArrayList<String>();
	
	private String employeeguid = "";
	private String senddepartment = "";
	private String localsenddepartment = "";
	
	public void importdata() {
		loadinitkeylist();
		editKeylist();
		loadinit();
		readFile();
	}
	
	/**
	 * 加载 keylist
	 */
	private void loadinitkeylist() {
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
	}
	
	/**
	 * 读取配置文件，创建数据库
	 */
	private void loadinit() {

		int maxactive = 30;
		int maxidle = 20;
		long maxwait = 10000;

		Properties p = new Properties();
		try {
			logger.info("连接数据库！");
			// InputStream inputStream = null;
			p.load(UploadService.class.getResourceAsStream("/dbcp.properties"));
			must = p.getProperty("must");
			dir = p.getProperty("dir");
			encode = p.getProperty("encode");
			employeeid = p.getProperty("employeeid");
			senddepartment = p.getProperty("departmentid");
			localsenddepartment = senddepartment;
			business = p.getProperty("business");
			type = p.getProperty("type");
			// logger.info(must);
			dataSource.setDriverClassName(p.getProperty("driverClass"));
			dataSource.setUrl(p.getProperty("jdbcUrl"));
			dataSource.setUsername(p.getProperty("user"));
			dataSource.setPassword(BiosanUtils.decrypt(p.getProperty("password"), "Bio-San#17"));
			dataSource.setMaxActive(maxactive);
			dataSource.setMaxIdle(maxidle);
			dataSource.setMaxWait(maxwait);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 读取上传文件
	 */
	private void readFile() {
		logger.info("解析文件！");
		if (!(dir != null && dir.equals("") == false)) {
			logger.info("文件路径为空！");
			return;
		}

		if (fileUtil.isDirectoryExist(dir) == false) {
			logger.info("文件路径不存在！");
			return;
		}

		ArrayList<String> fileNameList = fileUtil.getFileNamesInDirectory(dir);
		if (!(fileNameList != null && fileNameList.isEmpty() == false)) {
			logger.info("没有要读的文件！");
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

				
				// 判断文件的编码格式
				logger.info("文件编码格式为：" + encode);
				
				// 转字符串
				try {
					doc = new String(fileBytes, encode);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					// return;
				}
				doc = doc.replaceAll("null", "").trim();

				logger.info("dom==" + doc.trim());
				// workgrid2.
				
				XmlDocument3 xmlDocument = XmlDocument3.getInstance(null, doc);
				
				if (xmlDocument == null) {
					continue;
				} 
				
				logger.info("生成xmlDocument");
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
								try {
									Field f = c.getDeclaredField(key);
									f.set(importbean, keyvalue);
								} catch (SecurityException e) {
									logger.info(e);
									e.printStackTrace();
								} catch (NoSuchFieldException e) {
									logger.info(e);
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									logger.info(e);
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									logger.info(e);
									e.printStackTrace();
								}
							}
							employeeguid = importbean.getHos().split("\\|")[0];
							datalist.add(importbean);
						} else
							continue;
						
					}
				}
				logger.info("datalist size " + datalist.size());
				if (datalist.size() != 0) {
					if (type.equals("0"))
						insertData(dir + fileName, datalist);
					else if (type.equals("1"))//走新筛流程
						importsampledata(dir + fileName, datalist);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void insertData(String renamefile, ArrayList<ImportBean> datalist) {
		logger.info("写数据库！");
		// 判断存在
		String select_exists = "select count(*) from ground_tb_interface where patientname = ? and hos = ? and birthday = to_date(?, 'yyyy-MM-dd hh24:mi:ss')";
		// 插入数据
		String insert_sql = "insert into ground_tb_interface(interfaceid, samplestring, collectnum, patientname, mobile, hos, area, addtime, details, birthday, operatorid) "
				+ "values(GROUND_SEQ_INTERFACE.NEXTVAL, ?, ?, ?, ?, ?, ?, sysdate, ?, to_date(?, 'yyyy-MM-dd hh24:mi'), ?)";
		// 修改数据
		String update_sql = "update ground_tb_interface set samplestring = ?, collectnum = ?, mobile = ?, area = ?, addtime = sysdate, details = ?, operatorid = ? " 
				+ " where patientname = ? and hos = ? and birthday = to_date(?, 'yyyy-MM-dd hh24:mi:ss')";
		try {
			logger.info("总共数据： " + datalist.size());
			int j = 0;
			for (ImportBean importbean : datalist) {
				try {
					Object[] select_sql_params = { importbean.getPatientname(), importbean.getHos(), importbean.getBirthday() };
					String count = BiosanUtils.getSingle(select_exists, select_sql_params).toString();
					// 处理实验项目
					String exp = importbean.getExperimentitem();
					exp = "labitem_" + exp;
					exp = exp.replaceAll(",", ",labitem_");
					importbean.setExperimentitem(exp);  // 重新设置实验项目
					if ("0".equals(count)) {
						Object[] insert_sql_params = { importbean.getSamplestring(), importbean.getCollectenum(), importbean.getPatientname(), 
								importbean.getMobile(), importbean.getHos(), importbean.getArea(), BiosanUtils.ConvertToJson(importbean).toString(), 
								importbean.getBirthday(), "" };
						int insert_success = BiosanUtils.executeUpdate(insert_sql, insert_sql_params);
					} else {
						Object[] update_sql_params = { importbean.getSamplestring(), importbean.getCollectenum(), importbean.getMobile(), 
								importbean.getArea(), BiosanUtils.ConvertToJson(importbean).toString(), "", importbean.getPatientname(), 
								importbean.getHos(), importbean.getBirthday() };
						int update_success = BiosanUtils.executeUpdate(update_sql, update_sql_params);
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("处理失败！samplestring" + importbean.getSamplestring());
				}
				j++;
			}
			logger.info("提交！");
			logger.info("处理数据： " + j);
			logger.info("写库完成！");
			// 重命名文件
			String newfile = renamefile + "_bak";
			fileUtil.renameFile(renamefile, newfile);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	// 样本导入  走新筛流程  配置文件type=1
	public void importsampledata(String renamefile, ArrayList<ImportBean> datalist) {
		logger.info("样本开始导入！");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		PreparedStatement ps = null;
		try {
			// 重命名文件
			fileUtil.renameFile(renamefile, renamefile + "_bak");
			logger.info("取连接！");
			connection = dataSource.getConnection();
			if (connection == null) {
				return;
			}
			logger.info("取到连接！");
			senddepartment = localsenddepartment;
			Date nowDate = new Date();
			connection.setAutoCommit(false);
			long batchBarcodeId = 0l;
			// long operDepartmentId = 0;

			String sql_select_employee = "select " +
					"em.departmentid,em.employeeid,de.deliverydid " +
					"from ground_tb_employee em " + 
					"left join ground_tb_department de on em.departmentid=de.departmentid " +
					"where em.employeeguid=?";
			statement = connection.prepareStatement(sql_select_employee);
			logger.debug("employeeguid=======" + employeeguid);
			statement.setString(1, employeeguid);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				departmentid = resultSet.getString("departmentid");
				employeeid = resultSet.getString("employeeid");
				logger.debug("departmentid==========" + departmentid);
				logger.debug("employeeid==========" + employeeid);
				logger.debug("senddepartment==========" + senddepartment);
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
			// + "ground_seq_sample_logger.nextval,?,?,?,?," + "?,?,?,1,?)";
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
				logger.debug(importbean.toString());
				// 搜索录入人
				logger.debug("employeeguid==========" + employeeguid);
				employeeguid = importbean.getHos().split("\\|")[0];
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
					
				// 判断条码
				//System.out.println(importbean.getBarcode());
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
					logger.info(importbean.getBarcode() + "重复！");
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
				
				String encrypt = "0";  // 是否加密（针对电话号码）
				if (BiosanUtils.getFileNode("/dbcp.properties", "encrypt") != null) {
					logger.info("into...");
					encrypt = BiosanUtils.getFileNode("/dbcp.properties", "encrypt");
					logger.info("encrypt value is..." + encrypt);
					if ("".equals(encrypt) == false) {
					    
						encrypt = "1";
					}
				}
				logger.info("final encrypt is..." + encrypt);
				
				if ("0".equals(encrypt)) {  // 不加密
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
					statement.setString(3, importbean.getIllhisguid());
					statement.setString(4, "");
					statement.setString(5, "");
					statement.setString(6, "");
					statement.setString(7, importbean.getPatientname());
					statement.setDate(8, GroundMethod.changeDateType(importbean.getBirthday(), nowDate));
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
					statement.setDate(30, GroundMethod.changeDateType(importbean.getBirthday(), nowDate));// 小孩生日
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
				} else {  // 加密
					String sql_insert_pa = "insert into GROUND_TB_PATIENT(patientid,patientguid,ILLHISGUID,"
						+ " RACE,BARCODE,IDENTITYCARD,PATIENTNAME,BIRTHDAY,PASSPORTNUM,OFFICERNUM,PREGNANCYNUM,PREGCARENUM,"
						+ " PERMITNUM,BEDNUM,mobile,telephone,DOMICILE,ADDRESS,POSTALCODE,WORKTYPE,WORKPOSTALCODE,WORKAROUND,WORKUNITS,state,"
						+ "LANGUAGE,OPERATEDATE,BUSINESS,RELATEID,patienttypeid,accountid,operatorid,MOIDFIER,remark,childbirthday,weight,sex,housetype,household) "
						+ " values(?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,"
						+ "?,?,ENCRYPT('" + importbean.getMobile() + "', 'Bio-San#17'),ENCRYPT('" + importbean.getTelephone() + "', 'Bio-San#17'),?,?,?,?,?,?,?,1,"
						+ "?,?,1,1,2,?,?,?,?,?,?,?,?,?)";

					statement = connection.prepareStatement(sql_insert_pa);
					statement.setLong(1, childId);
					statement.setString(2, patientGuid);
					statement.setString(3, importbean.getIllhisguid());
					statement.setString(4, "");
					statement.setString(5, "");
					statement.setString(6, "");
					statement.setString(7, importbean.getPatientname());
					statement.setDate(8, GroundMethod.changeDateType(importbean.getBirthday(), nowDate));
					statement.setString(9, "");
					statement.setString(10, "");
					statement.setString(11, "");
					statement.setString(12, "");
					statement.setString(13, "");
					statement.setString(14, importbean.getBednum());
	//				statement.setString(15, importbean.getMobile());// 移动电话
	//				statement.setString(16, importbean.getTelephone());// 电话
					statement.setString(15, importbean.getContacter());
					statement.setString(16, importbean.getAddress());// 地址
					statement.setString(17, "");
					statement.setString(18, "");
					statement.setString(19, "");
					statement.setString(20, "");
					statement.setString(21, "");
					statement.setString(22, importbean.getChildname());
					statement.setDate(23, GroundMethod.changeDateType("", nowDate));
					statement.setInt(24, accountId);
					statement.setString(25, employeeid);
					statement.setString(26, employeeid);
					statement.setString(27, "");
					statement.setDate(28, GroundMethod.changeDateType(importbean.getBirthday(), nowDate));// 小孩生日
					statement.setString(29, importbean.getWeight());// 小孩体重
					statement.setString(30, importbean.getSex());// 小孩性别
	
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
					statement.setString(31, hostype);// 户口类型
					statement.setString(32, hoshold);// 户籍地
					statement.execute();
					statement.close();
					statement = null;
				}

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
					logger.info("部门不存在!");
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

					
					logger.debug("employeeid=========" + employeeid);
					logger.debug("departmentid=========" + departmentid);
					
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
					logger.info("obj area_ul====" + importbean.getArea());
					map.put("hos", importbean.getHos());
					map.put("collectnum", importbean.getCollectenum());
					map.put("bron_type", importbean.getBron_type());
					//收费情况
					map.put("reserved7", importbean.getIsfree());

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
							if ("area_ul".equals(createnameq))
								logger.debug("update cd====" + map.get(kk));
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
					logger.info(update_create + condition);
					statement.execute();
					statement.close();
					statement = null;

				}
			}
			logger.info("into...ps");
			if (ps != null) {
				int[] i = ps.executeBatch();
				for (int j : i) {
					logger.debug(j);
				}
				ps.close();
			}
			logger.info(sql_sample_batch_mapping);
			logger.info("提交！");
			connection.commit();
			logger.info("写库完成！");

			connection.close();
			resultSet = null;
			statement = null;

		} catch (Exception e1) {
			try {
				if (statement != null)
					statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.info(e);
			}
			e1.printStackTrace();
			logger.info(e1);
		}

	}

	/**
	 * 为子类提供的重载方法，在对默认Keylist进行增删
	 *
	 * @author:qibill
	 */
	public void editKeylist(){
	}
	
	public FileUtil getFileUtil() {
		return fileUtil;
	}

	public void setFileUtil(FileUtil fileUtil) {
		this.fileUtil = fileUtil;
	}

	public BasicDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getMust() {
		return must;
	}

	public void setMust(String must) {
		this.must = must;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public List<String> getKeylist() {
		return keylist;
	}

	public void setKeylist(List<String> keylist) {
		this.keylist = keylist;
	}

	public String getEmployeeguid() {
		return employeeguid;
	}

	public void setEmployeeguid(String employeeguid) {
		this.employeeguid = employeeguid;
	}

	public String getSenddepartment() {
		return senddepartment;
	}

	public void setSenddepartment(String senddepartment) {
		this.senddepartment = senddepartment;
	}

	public String getLocalsenddepartment() {
		return localsenddepartment;
	}

	public void setLocalsenddepartment(String localsenddepartment) {
		this.localsenddepartment = localsenddepartment;
	}
	
}

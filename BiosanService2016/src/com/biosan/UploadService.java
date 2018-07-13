package com.biosan;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.biosan.net.ImportDataHN;
import net.biosan.net.SearchDataBinZhou;
import net.biosan.net.service.ClientIpService;
import net.biosan.net.service.impl.ClientIpServiceImpl;

import org.apache.log4j.Logger;
import org.codehaus.xfire.transport.http.XFireServletController;

import com.biosan.utils.BiosanUtils;

/**
 * webservice方法
 * @author SXZ
 * @date 2014 2014-12-17 上午09:38:30
 */
public class UploadService {
	
	private static Logger log = Logger.getLogger(UploadService.class);
	
	private ClientIpService clientIpService;

	
	public UploadService() {
		super();
		clientIpService = new ClientIpServiceImpl();
	}

	public String getMappingFileName() {
		// 获取随机数发生器
		// (Random)configMap.get(ConstantInterface.TAG_CONTEXT_SYSTEM_RANDOMGENERATOR);
		Random random = new Random();
		// 获取当前时间
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddhhmmss"); // 格式化当前系统日期
		String dateTime = dateFm.format(new java.util.Date());

		// 产生3位随机数
		String randomString = "";

		DecimalFormat numberFormat = (DecimalFormat) NumberFormat.getInstance();
		numberFormat.applyPattern("000");
		randomString = numberFormat.format(random.nextInt(999));
		// 组成文件名，格式：yyyyMMddhhmmssXXX
		dateTime += randomString;
		return dateTime;
	}

	/**
	 * 上传普通文件
	 * @param bs 文件字节数组
	 * @param fileName 文件名
	 * @param loginName 用户名
	 * @param password 密码
	 * @return 上传信息
	 */
	public String uploadFile(byte[] bs, String fileName, String loginName, String password) {
		// 访问日志
//    	String loginfo = "IP:" + getClientIpXfire() + ";" + "function:uploadFile()";
//    	String insert_sql = "insert into GROUND_TB_ERROR_LOG(id, loginfo, type) values(ground_seq_error_log.nextval, ?, ?)";
//    	Object[] insert_params = { loginfo, "ip" };
//    	BiosanUtils.executeUpdate(insert_sql, insert_params);
		
		clientIpService.getClientIpXfire("function:uploadFile()");
		
		String newname = fileName;
		
		//判断是否是xml文件
		if (fileName.indexOf("xml") != -1)
			newname = fileName.substring(0, fileName.indexOf(".")) + getMappingFileName() + ".xml";
		
		FileOutputStream out = null;
		try {
			//认证用户名和密码
			String realuser = BiosanUtils.getFileNode("/dbcp.properties", "realuser").toString();
	        String realpsw = BiosanUtils.getFileNode("/dbcp.properties", "realpsw").toString();
	        if (!loginName.equals(realuser) || !password.equals(realpsw))
	        	return "认证失败！";
			
			Properties p = new Properties();
			p.load(UploadService.class.getResourceAsStream("/dbcp.properties"));
			String newFile = p.getProperty("dir") + newname;
			log.info("上传文件=======" + newFile);
			out = new FileOutputStream(newFile);
			try {
				out.write(bs);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "上传失败";
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
//		ImportDataFJ im = new ImportDataFJ();	 // 福建接口
//		ImportData im = new ImportData();
		ImportDataHN im = new ImportDataHN();
//		ImportDataJN imjn = new ImportDataJN();  // 济宁接口
//		ImportDataForFB imfb = new ImportDataForFB(); 
		im.importdata();
		return "上传成功";
	}
	
	/**
	 * 登陆验证
	 * @param loginName
	 * @param password
	 * @return
	 */
	public String checkLogin(String loginName, String password) {
		// 访问日志
    	String loginfo = "IP:" + getClientIpXfire() + ";" + "function:checkLogin()";
    	String insert_sql = "insert into GROUND_TB_ERROR_LOG(id, loginfo, type) values(ground_seq_error_log.nextval, ?, ?)";
    	Object[] insert_params = { loginfo, "ip" };
    	BiosanUtils.executeUpdate(insert_sql, insert_params);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");  // 格式化当前系统日期
		String returnString = "";  // 返回串
		try {
			String sql = "select count(*) from ground_tb_employee em where NLS_UPPER(em.employeeguid) = ? and em.passwd = ?";
	        String psw = password;
	        if (psw.length() <= 20) {
	        	psw = BiosanUtils.Md5(password);
	        }
			Object[] params = { loginName.toUpperCase(), psw };
			String count = BiosanUtils.getSingle(sql, params).toString();
			if ("0".equals(count) ==  false) {
				String dateTime = sdf.format(new java.util.Date());
				String loginpsw = loginName + "|" + password;
				String pswSql = "select encrypt('" + loginpsw + "', 'Bio-San#17" + dateTime + "') from dual";
				returnString = BiosanUtils.getSingle(pswSql).toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	public String[] searchBarcode(String starttime, String endtime) throws IOException {
		// 访问日志
/*    	String loginfo = "IP:" + getClientIpXfire() + ";" + "function:searchBarcode(" + starttime + ", " + endtime + ")";
    	String insert_sql = "insert into GROUND_TB_ERROR_LOG(id, loginfo, type) values(ground_seq_error_log.nextval, ?, ?)";
    	Object[] insert_params = { loginfo, "ip" };
    	BiosanUtils.executeUpdate(insert_sql, insert_params);*/
		
    	SearchDataBinZhou sd = new SearchDataBinZhou();
    	String[] str = sd.searchBarcodes(starttime, endtime);
    	return str;
    }
	
    public String searchInfo(String barcode) {
    	// 访问日志
    	String loginfo = "IP:" + getClientIpXfire() + ";" + "function:searchInfo(" + barcode + ")";
    	String insert_sql = "insert into GROUND_TB_ERROR_LOG(id, loginfo, type) values(ground_seq_error_log.nextval, ?, ?)";
    	Object[] insert_params = { loginfo, "ip" };
    	BiosanUtils.executeUpdate(insert_sql, insert_params);
    	
    	SearchDataBinZhou sd = new SearchDataBinZhou();
    	String xml = sd.searchInfo(barcode);
    	return xml;
    }
    
    public String searchInfoByCollectnum(String collectnum) {
    	// 访问日志
/*    	String loginfo = "IP:" + getClientIpXfire() + ";" + "function:searchInfo(" + collectnum + ")";
    	String insert_sql = "insert into GROUND_TB_ERROR_LOG(id, loginfo, type) values(ground_seq_error_log.nextval, ?, ?)";
    	Object[] insert_params = { loginfo, "ip" };
    	BiosanUtils.executeUpdate(insert_sql, insert_params);*/
    	
    	SearchDataBinZhou sd = new SearchDataBinZhou();
    	String xml = sd.searchInfoByCollectnum(collectnum);
    	return xml;
    }
    
    public String searchInfo(String barcode, String loginName, String password) {
    	// 访问日志
    	String loginfo = "IP:" + getClientIpXfire() + ";" + "function:searchInfo(" + barcode + ")";
    	String insert_sql = "insert into GROUND_TB_ERROR_LOG(id, loginfo, type) values(ground_seq_error_log.nextval, ?, ?)";
    	Object[] insert_params = { loginfo, "ip" };
    	BiosanUtils.executeUpdate(insert_sql, insert_params);

    	try {
    		String realuser = BiosanUtils.getFileNode("/dbcp.properties", "realuser").toString();
            String realpsw = BiosanUtils.getFileNode("/dbcp.properties", "realpsw").toString();
            if (!loginName.equals(realuser) || !password.equals(realpsw))
            	return "认证失败！";
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	SearchDataBinZhou sd = new SearchDataBinZhou();
    	String xml = sd.searchInfo(barcode);
    	return xml;
    }
    
    public String searchInfoAll(String starttime, String endtime) {
    	// 访问日志
    	String loginfo = "IP:" + getClientIpXfire() + ";" + "function:searchInfoAll(" + starttime + ", " + endtime + ")";
    	String insert_sql = "insert into GROUND_TB_ERROR_LOG(id, loginfo, type) values(ground_seq_error_log.nextval, ?, ?)";
    	Object[] insert_params = { loginfo, "ip" };
    	BiosanUtils.executeUpdate(insert_sql, insert_params);
    	
    	SearchDataBinZhou sd = new SearchDataBinZhou();
    	String xml = sd.searchInfoAll(starttime, endtime);
    	return xml;
    }
    
    /** 
     * 获取客户端IP地址 
     * 适用于xfire发布的webservice 
     * @return 
     */ 
    public static String getClientIpXfire() { 
        String ip = ""; 
        try { 
            HttpServletRequest request = XFireServletController.getRequest(); 
            ip = request.getRemoteAddr(); 
        } catch (Exception e) { 
            System.out.println("无法获取HttpServletRequest."); 
            e.printStackTrace(); 
        } 
        return ip; 
    }

}

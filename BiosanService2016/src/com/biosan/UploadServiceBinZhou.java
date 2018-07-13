package com.biosan;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Random;

import com.biosan.utils.BiosanUtils;

import net.biosan.net.ImportData;
import net.biosan.net.SearchDataBinZhou;

public class UploadServiceBinZhou {

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
        String newname = fileName;
        if (fileName.indexOf("xml") != -1)
            newname = fileName.substring(0, fileName.indexOf(".")) + getMappingFileName() + ".xml";
        FileOutputStream out = null;
		try {
			Properties p = new Properties();
			p.load(UploadServiceBinZhou.class.getResourceAsStream("/dbcp.properties"));
			String newFile = p.getProperty("dir") + newname;
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
        ImportData im = new ImportData();
        im.importdata();
        return "上传成功";
    }

    public String[] SearchBarcode(String starttime, String endtime) throws IOException {
    	SearchDataBinZhou sd = new SearchDataBinZhou();
    	String[] str = sd.searchBarcodes(starttime, endtime);
    	return str;
    }
    
    /**
     * 获取新筛结果数据
     * @param username 用户名
     * @param password 密码
     * @param barcode 条码号
     * @return
     */
    public String searchInfo(String username, String password, String barcode) {
    	String xml = "";
    	try {
			String realuser = BiosanUtils.getFileNode("/dbcp.properties", "interface_user").toString();
			String realpsw = BiosanUtils.getFileNode("/dbcp.properties", "interface_psw").toString();
			if (!username.equals(realuser) || !password.equals(realpsw))
				return "认证失败！";
			SearchDataBinZhou sd = new SearchDataBinZhou();
			xml = sd.searchInfo(barcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return xml;
    }
    
    public String searchInfoAll(String starttime, String endtime) {
    	SearchDataBinZhou sd = new SearchDataBinZhou();
    	String xml = sd.searchInfoAll(starttime, endtime);
    	return xml;
    }
}

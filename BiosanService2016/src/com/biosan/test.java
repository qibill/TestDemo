package com.biosan;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import org.codehaus.xfire.client.Client;

import com.biosan.utils.BiosanUtils;

public class test {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {

//		String realuser = BiosanUtils.getFileNode("/dbcp.properties", "interface_user").toString();
//		System.out.println();
//		UploadServiceShandong up = new UploadServiceShandong();
//		UploadServiceBinZhou bz = new UploadServiceBinZhou();
//		UploadServiceJiNing ujn = new UploadServiceJiNing();
//		UploadServiceDataCenter dc = new UploadServiceDataCenter();
//
		UploadService us = new UploadService();
		File file = new File("D:\\uploaddata\\23.xml");
		FileInputStream fs = new FileInputStream(file);
		byte[] bs1 = new byte[fs.available()];
		fs.read(bs1);
		fs.close();
		System.out.println(us.uploadFile(bs1, "23.xml", "hd", "ujH8ol81Ce6gKChzYhhJ8g=="));
//		String[] searchBarcode = us.searchBarcode("2018-08-16 00:00:00", "2018-08-19 00:00:00");3993
/*		String searchInfoByCollectnum = us.searchInfoByCollectnum("3196");
		System.out.println(searchInfoByCollectnum);*/
/*		String info = us.searchInfoByCollectnum("20120204");
		System.out.println(info);*/
		
//		System.out.println(BiosanUtils.decrypt(us.checkLogin("biosan", "biosan#17"), "Bio-San#17"));
//		System.out.println(us.checkLogin("biosan", "biosan#17"));
//		System.out.println(BiosanUtils.decrypt("Tu5vgoxp41o=", "Bio-San#17"));
		
//		System.out.println(us.uploadQuality(bs1, "20140910030111_Quality20140910030315665.xml", "", ""));
//		System.out.println(us.uploadFile(bs1, "pku.xml", "", ""));
//		System.out.println(ujn.searchInfoAll("2014-04-24 16:54:00", "2014-04-24 16:54:56"));
//		System.out.println(us.searchInfo("20141210006"));
		
//		System.out.println(dc.uploadFile(null, "上传结束", "", ""));
		
		
//		SearchDataBinZhou s=new SearchDataBinZhou();
//		System.out.println(s.searchInfo("BZ000013017"));
//		ExcellUtils eu = new ExcellUtils();
//		List<Map<String, Object>> list = eu.ReadExcell("");
		
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date d1, d2;
//		long interval = 0;
//		String starttime = "2013-12-12 12:12:12";
//		if (starttime.indexOf(":") != -1) {  // 存在时分秒
//			System.out.println("aa");
//		}
	}
}

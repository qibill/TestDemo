package com.biosan;

import net.biosan.net.SearchDataBinZhou;

import com.biosan.utils.BiosanUtils;

public class SearchInfoSerivce {
	
    /**
     * 获取新筛结果数据
     * @param username 用户名
     * @param password 密码
     * @param Phone 电话号码
     * @return
     */
    public String searchInfo(String username, String password, String mobile) {
    	String xml = "调用成功";
    	try {
//			String realuser = BiosanUtils.getFileNode("/dbcp.properties", "interface_user").toString();
//			String realpsw = BiosanUtils.getFileNode("/dbcp.properties", "interface_psw").toString();
			if (!username.equals("BGI") || !password.equals("BGI"))
				return "认证失败！";
			
			SearchDataBinZhou sd = new SearchDataBinZhou();
			xml = sd.searchInfoByPhoneNum(mobile);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return xml;
    }
    
}

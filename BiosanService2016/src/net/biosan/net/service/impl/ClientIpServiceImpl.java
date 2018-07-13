package net.biosan.net.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.xfire.transport.http.XFireServletController;

import com.biosan.utils.BiosanUtils;

import net.biosan.net.service.ClientIpService;

public class ClientIpServiceImpl implements ClientIpService {

	private static final Logger logger = Logger.getLogger(ClientIpServiceImpl.class);
	
	public String getClientIpXfire(String loginfo) {
		loginfo = "IP:" + getClientIpXfire() + ";" + loginfo;
		
    	String insert_sql = "insert into GROUND_TB_ERROR_LOG(id, loginfo, type) values(ground_seq_error_log.nextval, ?, ?)";
    	Object[] insert_params = { loginfo, "ip" };
    	BiosanUtils.executeUpdate(insert_sql, insert_params);
    	
		return "";
	}

	public String getClientIpXfire() {
		String ip = "";
		try {
			HttpServletRequest request = XFireServletController.getRequest();
			ip = request.getRemoteAddr();
		} catch (Exception e) {
			logger.error("无法获取HttpServletRequest.");
			e.printStackTrace();
			return "";
		}
		return ip;
	}
}

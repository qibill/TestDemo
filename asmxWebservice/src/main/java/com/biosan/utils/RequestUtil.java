package com.biosan.utils;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.newtouch.pojo.PatientDetailInfo;

public class RequestUtil {

	private static final Logger logger = Logger.getLogger(RequestUtil.class);

	public static BiosanResult getPatientDetailInfoRequest(String reponse) {
		String reponsebody = reponse.substring(reponse.lastIndexOf("<body>") + 6,
				reponse.lastIndexOf("</body>"));
		BiosanResult biosanResult = getErrorRequest(reponsebody);
		if (biosanResult.getStatus() != null) {
			return biosanResult;
		}
		String dataTable = reponsebody.substring(reponsebody.indexOf("<DataTable>") + 11,
				reponsebody.indexOf("</DataTable>"));
		JAXBContext jc;
		PatientDetailInfo patientDetailInfo = new PatientDetailInfo();
		try {
			jc = JAXBContext.newInstance(PatientDetailInfo.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			patientDetailInfo = (PatientDetailInfo) unmarshaller
					.unmarshal(new StringReader(dataTable));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		biosanResult.setStatus(1);
		biosanResult.setData(patientDetailInfo);
		return biosanResult;
	}

	public static BiosanResult getTSSCRequest(String reponse) {
		logger.debug("处理响应");
		String reponsebody = reponse.substring(reponse.lastIndexOf("<body>") + 6,
				reponse.lastIndexOf("</body>"));
		BiosanResult biosanResult = getErrorRequest(reponsebody);
		if (biosanResult.getStatus() != null) {
			return biosanResult;
		}
		logger.info("发送成功");
		biosanResult.setStatus(1);
		return biosanResult;
	}

	/**
	 * @Title: getErrorRequest
	 * @Description: 取错误信息
	 * @param reponsebody
	 * @return BiosanResult 成功返回null，失败返回BiosanResult类型
	 */
	private static BiosanResult getErrorRequest(String reponsebody) {
		BiosanResult biosanResult = new BiosanResult();
		// 小写
		reponsebody = reponsebody.toLowerCase();
		String result = reponsebody.substring(reponsebody.lastIndexOf("<result>") + 8,
				reponsebody.lastIndexOf("</result>"));
		logger.debug(result);
		// 失败-系统级
		if ("err".equals(result)) {
			logger.debug(reponsebody.lastIndexOf("<errmsg>"));
			if (reponsebody.lastIndexOf("<errmsg>") > 0) {
				logger.error(reponsebody.substring(reponsebody.lastIndexOf("<errmsg>") + 8,
						reponsebody.lastIndexOf("</errmsg>")));
			}
			biosanResult.setStatus(2);
			logger.error("系统异常");
			return biosanResult;
		}
		logger.debug(reponsebody.lastIndexOf("<flag>"));
		String falg = reponsebody.substring(reponsebody.lastIndexOf("<flag>") + 6,
				reponsebody.lastIndexOf("</flag>"));
		logger.debug(falg);
		// 失败
		if ("0".equals(falg)) {
			biosanResult.setStatus(2);
			biosanResult.setMsg("返回值为空");
			logger.info("返回值为空");
			return biosanResult;
		}
		return biosanResult;
	}
}

package com.biosan.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://webservice.biosan.com/")
public interface BiosanWebSerivce {

	/**
	 * @param CardNo
	 * @return
	 * @author qibill 2018年5月31日上午10:16:00
	 */
	@WebMethod(action = "http://webservice.biosan.com/getPatientDetailInfo")
	String getPatientDetailInfo(
			@WebParam(name = "CardNo", targetNamespace = "http://webservice.biosan.com/") String CardNo);

	/**
	 * @param sampleid 样品ID
	 * @param czqf 操作编码（1 新增 3 撤销）
	 * @author qibill 2018年5月31日上午10:17:04
	 */
	@WebMethod(action = "http://webservice.biosan.com/sendPlatFormTSSCService")
	String sendPlatFormTSSCService(
			@WebParam(name = "sampleid", targetNamespace = "http://webservice.biosan.com/") Integer sampleid,
			@WebParam(name = "czqf", targetNamespace = "http://webservice.biosan.com/") Integer czqf);
}

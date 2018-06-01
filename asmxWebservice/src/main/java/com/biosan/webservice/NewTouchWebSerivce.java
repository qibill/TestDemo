package com.biosan.webservice;

import javax.jws.WebService;

@WebService
public interface NewTouchWebSerivce {
	
	/**
	 * @param CardNo
	 * @return
	 * @author qibill 2018年5月31日上午10:16:00
	 */
	String getPatientDetailInfo(String CardNo);
	
	/**
	 * @param sampleid 样品ID
	 * @param czqf 操作编码（1 新增 3 撤销）
	 * @author qibill 2018年5月31日上午10:17:04
	 */
	void sendPlatFormTSSCService(int sampleid, String czqf);
}

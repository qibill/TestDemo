package com.biosan.webservice;

import org.springframework.beans.factory.annotation.Autowired;

import com.biosan.utils.JsonUtils;
import com.biosan.utils.RequestUtil;
import com.newtouch.pojo.PatientDetailInfoRequest;

public class BiosanWebSerivceImpl implements BiosanWebSerivce{

    @Autowired
	private NewTouchService newtouchservice;
/*    @Autowired
	private TSSCService tSSCSrevice;*/
	
	@Override
	public String getPatientDetailInfo(String CardNo) {

		PatientDetailInfoRequest request = new PatientDetailInfoRequest(CardNo);
		String patientDetailInfo = newtouchservice.patientDetailInfo(request.toXml());
		return JsonUtils.objectToJson(RequestUtil.getPatientDetailInfoRequest(patientDetailInfo));
	}

/*	@Override
	public String sendPlatFormTSSCService(Integer sampleid, Integer czqf) {
		PlatFormTSSCServiceRequest request = tSSCSrevice.creator(sampleid, czqf);
		String platForm_TSSC_Service = newtouchservice.PlatForm_TSSC_Service(request.toXml());
		return platForm_TSSC_Service;
	}*/

}

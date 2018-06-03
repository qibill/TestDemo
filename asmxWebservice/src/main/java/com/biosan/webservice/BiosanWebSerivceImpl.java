package com.biosan.webservice;

import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.pojo.PatientDetailInfoRequest;
import com.newtouch.pojo.PlatFormTSSCServiceRequest;

public class BiosanWebSerivceImpl implements BiosanWebSerivce{

    @Autowired
	private NewTouchService newtouchservice;
    @Autowired
	private TSSCService tSSCSrevice;
	
	@Override
	public String getPatientDetailInfo(String CardNo) {

		PatientDetailInfoRequest request = new PatientDetailInfoRequest(CardNo);
		String patientDetailInfo = newtouchservice.patientDetailInfo(request.toXml());
		return patientDetailInfo;
	}

	@Override
	public String sendPlatFormTSSCService(int sampleid, String czqf) {
		PlatFormTSSCServiceRequest request = tSSCSrevice.creatPlatFormTSSCServiceRequest(sampleid, czqf);
		String platForm_TSSC_Service = newtouchservice.PlatForm_TSSC_Service(request.toXml());
		return platForm_TSSC_Service;
	}

}

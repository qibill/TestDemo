package com.biosan.webservice;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biosan.utils.RequestUtil;
import com.newtouch.webservice.patientbasicinfo.PatientBasicInfoSoap;
import com.newtouch.webservice.testreportrelease.TestReportReleaseSoap;


@Service
public class CXFWebservice implements NewTouchService {
	
    private static final Logger logger = Logger.getLogger(CXFWebservice.class);

	@Autowired
	private PatientBasicInfoSoap patientBasicInfo;
	@Autowired
	private TestReportReleaseSoap testReportRelease;

	@Override
    public String patientDetailInfo(String request) {
        String patientDetailInfo = patientBasicInfo.patientDetailInfo(request);
		return patientDetailInfo;
    }

	@Override
    public String platFormTSSCService(String request) {
        String platFormTSSCService = testReportRelease.platFormTSSCService(request);
        logger.info(platFormTSSCService);
        return platFormTSSCService;
    }

}

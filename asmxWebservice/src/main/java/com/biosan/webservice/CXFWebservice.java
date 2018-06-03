package com.biosan.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.webservice.PatientBasicInfoSoap;
import com.newtouch.webservice.TestReportReleaseSoap;

@Service
public class CXFWebservice implements NewTouchService {

    private PatientBasicInfoSoap patientBasicInfo;
    private TestReportReleaseSoap testReportRelease;

    @Override
    public String patientDetailInfo(String request) {
        String patientDetailInfo = patientBasicInfo.patientDetailInfo(request);
        return patientDetailInfo;
    }

    @Override
    public String PlatForm_TSSC_Service(String request) {
        String platForm_TSSC_Service = testReportRelease
                .PlatForm_TSSC_Service(request);
        return platForm_TSSC_Service;
    }

}

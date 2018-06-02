package com.biosan.webservice;

import com.newtouch.webservice.NewTouchWebServiceSoap;

public class CXFWebservice implements NewTouchService{

    private NewTouchWebServiceSoap newTouchWebServiceSoap;

    @Override
    public String patientDetailInfo(String request) {
        String patientDetailInfo = newTouchWebServiceSoap.patientDetailInfo(request);
        return patientDetailInfo;
    }

    @Override
    public String PlatForm_TSSC_Service(String request) {
        String platForm_TSSC_Service = newTouchWebServiceSoap.PlatForm_TSSC_Service(request);
        return platForm_TSSC_Service;
    }


    
}

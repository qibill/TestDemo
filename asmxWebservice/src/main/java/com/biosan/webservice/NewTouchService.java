package com.biosan.webservice;

public interface NewTouchService {

    /**
     * 根据卡号获取病人基本信息
     * 
     * @param request PatientDetailInfoRequest.toxml()
     * @return
     * 
     * @author qibill
     * 2018年6月7日上午8:59:29
     */
    String patientDetailInfo(String request);

    /**
     * 发送病人唐氏筛查信息
     * 
     * @param request PlatFormTSSCServiceRequest.toxml()
     * @return
     * 
     * @author qibill
     * 2018年6月7日上午9:01:15
     */
    String platFormTSSCService(String request);
}

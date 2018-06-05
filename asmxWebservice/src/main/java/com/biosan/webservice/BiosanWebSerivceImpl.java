package com.biosan.webservice;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.biosan.utils.BiosanResult;
import com.biosan.utils.JsonUtils;
import com.biosan.utils.RequestUtil;
import com.newtouch.pojo.PatientDetailInfoRequest;

public class BiosanWebSerivceImpl implements BiosanWebSerivce{
    
    private static final Logger logger = Logger.getLogger(BiosanWebSerivceImpl.class);
    
    @Autowired
	private NewTouchService newtouchservice;
/*    @Autowired
	private TSSCService tSSCSrevice;*/
	
	@Override
	public String getPatientDetailInfo(String CardNo) {
	    logger.info("开始查找就诊卡号为" + CardNo + "病人基本信息(唐筛)");
		PatientDetailInfoRequest request = new PatientDetailInfoRequest(CardNo);
		String patientDetailInfo = newtouchservice.patientDetailInfo(request.toXml());
		BiosanResult biosanResult = RequestUtil.getPatientDetailInfoRequest(patientDetailInfo);
		if (biosanResult.getStatus() == 1) {
		    logger.info("查询成功");
        }else{
            logger.info("查询失败，失败信息：" +  biosanResult.getMsg());
        };
		return JsonUtils.objectToJson(RequestUtil.getPatientDetailInfoRequest(patientDetailInfo));
	}

/*	@Override
	public String sendPlatFormTSSCService(Integer sampleid, Integer czqf) {
		PlatFormTSSCServiceRequest request = tSSCSrevice.creator(sampleid, czqf);
		String platForm_TSSC_Service = newtouchservice.PlatForm_TSSC_Service(request.toXml());
		return platForm_TSSC_Service;
	}*/

}

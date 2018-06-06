package com.biosan.webservice;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.biosan.pojo.Newtouchtsscresult;
import com.biosan.utils.BiosanResult;
import com.biosan.utils.JsonUtils;
import com.biosan.utils.RequestUtil;
import com.newtouch.pojo.PatientDetailInfoRequest;

public class BiosanWebSerivceImpl implements BiosanWebSerivce {

	private static final Logger logger = Logger.getLogger(BiosanWebSerivceImpl.class);

	@Autowired
	private NewTouchService newtouchservice;
	@Autowired
	private TSSCService tSSCSrevice;
	@Autowired
	private NewtouchtsscresultService newtouchtsscresultService;

	
	@Override
	public String getPatientDetailInfo(String CardNo) {
		logger.info("开始查找就诊卡号为" + CardNo + "病人基本信息(唐筛)");
		PatientDetailInfoRequest request = new PatientDetailInfoRequest(CardNo);
		String patientDetailInfo = newtouchservice.patientDetailInfo(request.toXml());
		BiosanResult biosanResult = RequestUtil.getPatientDetailInfoRequest(patientDetailInfo);
		if (biosanResult.getStatus() == 1) {
			logger.info("查询成功");
		} else {
			logger.info("查询失败，失败信息：" + biosanResult.getMsg());
		}
		;
		return JsonUtils.objectToJson(RequestUtil.getPatientDetailInfoRequest(patientDetailInfo));
	}

	@Override
	public String sendPlatFormTSSCService(String sampleidList) {
		BiosanResult result = new BiosanResult();
		Map<String, String> map= new LinkedHashMap<>();
		String[] sampleids = sampleidList.split(",");
		for (String sampleid : sampleids) {
			if (sampleid != null) {
				Newtouchtsscresult newtouchtsscresult = newtouchtsscresultService.selectBySampleid(Integer.valueOf(sampleid)).get(0);
				result = tSSCSrevice.sendPlatFormTSSCService(newtouchtsscresult);
				map.put(sampleid, result.getStatus() == 1 ? "发送成功":"发送失败");
			}
			
		}
		
		return map.toString();
	}

}

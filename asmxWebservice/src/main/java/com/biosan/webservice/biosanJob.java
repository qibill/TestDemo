package com.biosan.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biosan.pojo.Newtouchtsscresult;
import com.biosan.utils.BiosanResult;
import com.biosan.utils.RequestUtil;
import com.newtouch.pojo.PlatFormTSSCServiceRequest;

/**
 * 定时任务类
 * 
 * @author qibill
 */
@Component
public class biosanJob {

	@Autowired
	private TSSCService tSSCSrevice;
	@Autowired
	private NewTouchService newtouchservice;
	@Autowired
	private NewtouchtsscresultService newtouchtsscresultService;

	private Integer days = 1;

	public void execute() {
		BiosanResult result = new BiosanResult();
		List<Newtouchtsscresult> list = newtouchtsscresultService.selectDaysSampleid(days);
		for (Newtouchtsscresult newtouchtsscresult : list) {
			// 未发过 表newtouchtsscresult没有数据
			if (newtouchtsscresult.getTsscresult() == null) {
				result = sendPlatFormTSSCService(newtouchtsscresult.getSampleid(), 1);
				newtouchtsscresult.setTsscresult(result.getStatus());
				newtouchtsscresultService.insert(newtouchtsscresult);
			} else {
				// 表newtouchtsscresult有数据
				// 更新  pdfdate报告日期
				// 成功发送过
				if (newtouchtsscresult.getTsscresult() == 1) {
					result = sendPlatFormTSSCService(newtouchtsscresult.getSampleid(), 3);
					if (result.getStatus() == 1) {
						result = sendPlatFormTSSCService(newtouchtsscresult.getSampleid(), 1);
					}
					newtouchtsscresult.setTsscresult(result.getStatus());
					newtouchtsscresultService.updateByBean(newtouchtsscresult);
				} else {
					// 发送失败的 pdfdate报告日期在一天之内
					result = sendPlatFormTSSCService(newtouchtsscresult.getSampleid(), 1);
					newtouchtsscresult.setTsscresult(result.getStatus());
					newtouchtsscresultService.updateByBean(newtouchtsscresult);
				}
			}
		}
		// 曾经发送失败的  pdfdate报告日期 包括在一天之前
		List<Newtouchtsscresult> Errlist = newtouchtsscresultService.selectErr();
		for (Newtouchtsscresult newtouchtsscresult : Errlist) {
			result = sendPlatFormTSSCService(newtouchtsscresult.getSampleid(), 1);
			newtouchtsscresult.setTsscresult(result.getStatus());
			newtouchtsscresultService.updateByBean(newtouchtsscresult);
		}
	}

	public BiosanResult sendPlatFormTSSCService(int sampleid, Integer czqf) {
		
		PlatFormTSSCServiceRequest request = tSSCSrevice.creator(sampleid, czqf);
		String tSSCReponse = newtouchservice.PlatForm_TSSC_Service(request.toXml());
		// 运行时有异常
		if (tSSCReponse == null) {
			return BiosanResult.isErr();
		}
		return RequestUtil.getTSSCRequest(tSSCReponse);
	}
}

package com.biosan.webservice;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.biosan.pojo.Newtouchtsscresult;
import com.biosan.utils.BiosanResult;

/**
 * 定时任务类
 * 
 * @author qibill
 */
@Component
public class biosanJob {

	private static final Logger logger = Logger.getLogger(biosanJob.class);

	@Autowired
	private TSSCService tSSCSrevice;
	@Autowired
	private NewTouchService newtouchservice;
	@Autowired
	private NewtouchtsscresultService newtouchtsscresultService;
	@Value("${DAYS}")
	private Integer DAYS;



	public void execute() {
		logger.info("开始发送病人唐氏筛查信息");
		BiosanResult result = new BiosanResult();
		List<Newtouchtsscresult> list = newtouchtsscresultService.selectNewSampleid(DAYS);
		logger.info("有" + list.size() + "个新报告信息");
		//发送新生成的报告
		for (Newtouchtsscresult newtouchtsscresult : list) {
			tSSCSrevice.sendPlatFormTSSCService(newtouchtsscresult);
		}
		// 曾经发送失败的 pdfdate报告日期 包括在一天之前
		List<Newtouchtsscresult> Errlist = newtouchtsscresultService.selectErr();
		logger.info("有" + Errlist.size() + "个发送失败的报告信息");
		for (Newtouchtsscresult newtouchtsscresult : Errlist) {
			tSSCSrevice.sendPlatFormTSSCService(newtouchtsscresult);
		}
	}
}

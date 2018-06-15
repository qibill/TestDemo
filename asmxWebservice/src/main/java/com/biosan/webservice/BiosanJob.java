package com.biosan.webservice;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.biosan.pojo.Newtouchtsscresult;

/**
 * 定时任务类
 * 
 * @author qibill
 */
@Component
public class BiosanJob {

	private static final Logger logger = Logger.getLogger(BiosanJob.class);

	@Autowired
	private TSSCService tSSCSrevice;
	@Autowired
	private NewtouchtsscresultService newtouchtsscresultService;
	@Value("${DAYS}")
	private String DAYS;



	public void execute() {
		logger.info("开始发送病人唐氏筛查信息");
		Integer days = Integer.valueOf(DAYS);
		//发送新生成的报告
		List<Newtouchtsscresult> list = newtouchtsscresultService.selectNewSampleid(days);
		logger.info("有" + list.size() + "个新报告信息");
		for (Newtouchtsscresult newtouchtsscresult : list) {
			tSSCSrevice.sendPlatFormTSSCService(newtouchtsscresult);
		}
		// 发送曾经发送失败的 
		List<Newtouchtsscresult> errlist = newtouchtsscresultService.selectErr();
		logger.info("有" + errlist.size() + "个发送失败的报告信息");
		for (Newtouchtsscresult newtouchtsscresult : errlist) {
			tSSCSrevice.sendPlatFormTSSCService(newtouchtsscresult);
		}
	}
}

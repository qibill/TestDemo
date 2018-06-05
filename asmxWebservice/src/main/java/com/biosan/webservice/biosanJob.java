package com.biosan.webservice;

import java.util.List;

import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(biosanJob.class);

    @Autowired
    private TSSCService tSSCSrevice;

    @Autowired
    private NewTouchService newtouchservice;

    @Autowired
    private NewtouchtsscresultService newtouchtsscresultService;

    private Integer days = 1;

    public void execute() {
        logger.info("开始发送病人唐氏筛查信息");
        BiosanResult result = new BiosanResult();
        List<Newtouchtsscresult> list = newtouchtsscresultService.selectDaysSampleid(days);
        logger.info("有" + list.size() + "个新报告信息");
        for (Newtouchtsscresult newtouchtsscresult : list) {
            // 未发过 表newtouchtsscresult没有数据
            if (newtouchtsscresult.getTsscresult() == null) {
                result = sendPlatFormTSSCService(newtouchtsscresult.getSampleid(), 1);
                newtouchtsscresult.setTsscresult(result.getStatus());
                newtouchtsscresultService.insert(newtouchtsscresult);
            }
            else {
                // 表newtouchtsscresult有数据
                // 更新 pdfdate报告日期
                // 成功发送过
                logger.info("样品ID为" + newtouchtsscresult.getSampleid() + "重新风险计算了，发送撤销命令");
                if (newtouchtsscresult.getTsscresult() == 1) {
                    result = sendPlatFormTSSCService(newtouchtsscresult.getSampleid(), 3);
                    if (result.getStatus() == 1) {
                        result = sendPlatFormTSSCService(newtouchtsscresult.getSampleid(), 1);
                    }
                    newtouchtsscresult.setTsscresult(result.getStatus());
                    newtouchtsscresultService.updateByBean(newtouchtsscresult);
                }
                else {
                    // 发送失败的 pdfdate报告日期在一天之内
                    result = sendPlatFormTSSCService(newtouchtsscresult.getSampleid(), 1);

                    newtouchtsscresult.setTsscresult(result.getStatus());
                    newtouchtsscresultService.updateByBean(newtouchtsscresult);
                }
            }
        }
        // 曾经发送失败的 pdfdate报告日期 包括在一天之前
        List<Newtouchtsscresult> Errlist = newtouchtsscresultService.selectErr();
        logger.info("有" + list.size() + "发送失败的报告信息");
        for (Newtouchtsscresult newtouchtsscresult : Errlist) {
            result = sendPlatFormTSSCService(newtouchtsscresult.getSampleid(), 1);
            newtouchtsscresult.setTsscresult(result.getStatus());
            newtouchtsscresultService.updateByBean(newtouchtsscresult);
        }
    }

    public BiosanResult sendPlatFormTSSCService(int sampleid, Integer czqf) {
        String report = czqf == 1 ? "报告信息" : "撤销命令";
        logger.info("样品ID为" + sampleid + "开始发送" + report);
        PlatFormTSSCServiceRequest request = tSSCSrevice.creator(sampleid, czqf);
        String tSSCReponse = newtouchservice.PlatForm_TSSC_Service(request.toXml());
        // 运行时有异常
        if (tSSCReponse == null) {
            logger.info("样品ID为" + sampleid + report + "发送失败");
            return BiosanResult.isErr();
        }
        BiosanResult biosanResult = RequestUtil.getTSSCRequest(tSSCReponse);
        String result = biosanResult.getStatus() == 1 ? "成功" : "失败";
        logger.info("样品ID为" + sampleid + report + "发送" + result);
        return biosanResult;
    }

}

package com.biosan.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biosan.utils.BiosanResult;
import com.biosan.utils.RequestUtil;
import com.newtouch.pojo.PlatFormTSSCServiceRequest;

/**
 * 定时任务类
 * 
 * @author qibill
 *
 */
@Component
public class biosanJob {

    @Autowired
    private SampleresultService sampleresultService;
    @Autowired
    private TSSCService tSSCSrevice;
    @Autowired
    private NewTouchService newtouchservice;
    
    private Integer days;
    
    public void execute() {  
        List<Integer> sampleids = sampleresultService.selectDaysSampleid(days); 
        for (Integer sampleid : sampleids) {
            BiosanResult result = sendPlatFormTSSCService(sampleid, 1);
            //TODO 建立新表 sql
        }
    } 
    
    public BiosanResult sendPlatFormTSSCService(int sampleid, Integer czqf) {
        PlatFormTSSCServiceRequest request = tSSCSrevice.creatPlatFormTSSCServiceRequest(sampleid, czqf);
        String tSSCReponse = newtouchservice.PlatForm_TSSC_Service(request.toXml());
        return RequestUtil.getTSSCRequest(tSSCReponse);
    }
}

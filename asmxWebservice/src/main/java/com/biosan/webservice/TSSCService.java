package com.biosan.webservice;

import com.biosan.pojo.Newtouchtsscresult;
import com.biosan.utils.BiosanResult;
import com.newtouch.pojo.PlatFormTSSCServiceRequest;

public interface TSSCService {

    /**
     * 根据 创建PlatFormTSSCServiceRequest
     * 
     * @param sampleid 样品ID
     * @param czqf 操作编码（1 新增 3 撤销）
     * @return
     * @author qibill 2018年5月31日上午10:05:55
     */
    PlatFormTSSCServiceRequest creator(Integer sampleid, Integer czqf);
    
    /**
     * 根据 发送记录 发送报告
     * 
     * @param newtouchtsscresult
     * @return
     * 
     * @author qibill
     * 2018年6月7日上午9:14:11
     */
    public BiosanResult sendPlatFormTSSCService(Newtouchtsscresult newtouchtsscresult);
}

package com.biosan.webservice;

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
    PlatFormTSSCServiceRequest creatPlatFormTSSCServiceRequest(int sampleid, String czqf);
}

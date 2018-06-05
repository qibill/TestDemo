package com.biosan.utils;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.newtouch.pojo.PatientDetailInfo;

public class RequestUtil {

    public static BiosanResult getPatientDetailInfoRequest(String reponse) {
        String reponsebody = reponse.substring(reponse.lastIndexOf("<body>") + 6, reponse.lastIndexOf("</body>"));
        BiosanResult biosanResult = getErrorRequest(reponsebody);
        if (biosanResult != null) {
            return biosanResult;
        }
        biosanResult = new BiosanResult();
        String dataTable = reponsebody.substring(reponsebody.indexOf("<DataTable>") + 11, reponsebody.indexOf("</DataTable>"));
        JAXBContext jc;
        PatientDetailInfo patientDetailInfo = new PatientDetailInfo();
        try {
            jc = JAXBContext.newInstance(PatientDetailInfo.class);
            Unmarshaller unmarshaller=jc.createUnmarshaller();
            patientDetailInfo = (PatientDetailInfo) unmarshaller.unmarshal(new StringReader(dataTable));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        biosanResult.setStatus(1);
        biosanResult.setData(patientDetailInfo);
        return biosanResult;
    }
    
    public static BiosanResult getTSSCRequest(String reponse) {
        String reponsebody = reponse.substring(reponse.lastIndexOf("<body>") + 6, reponse.lastIndexOf("</body>"));
        BiosanResult biosanResult = getErrorRequest(reponsebody);
        if (biosanResult != null) {
            return biosanResult;
        }
        biosanResult = new BiosanResult();
        biosanResult.setStatus(1);
        return biosanResult;
    }
    /**   
     * @Title: getErrorRequest   
     * @Description: 取错误信息
     * @param reponsebody
     * @return  BiosanResult 成功返回null，失败返回BiosanResult类型
     */
    private static BiosanResult getErrorRequest(String reponsebody) {
        BiosanResult biosanResult = new BiosanResult();
        //小写
        reponsebody = reponsebody.toLowerCase();
        String result = reponsebody.substring(reponsebody.lastIndexOf("<result>") + 8, reponsebody.lastIndexOf("</result>"));
        //失败-系统级
        if ("err".equals(result)) {
            String errMsg = reponsebody.substring(reponsebody.lastIndexOf("<errMsg>") + 8, reponsebody.lastIndexOf("</errMsg>"));
            biosanResult.setStatus(2);
            biosanResult.setMsg(errMsg);
            return biosanResult;
        }
        String Flag = reponsebody.substring(reponsebody.lastIndexOf("<flag>") + 6, reponsebody.lastIndexOf("</flag>")); 
        //失败
        if ("0".equals(Flag)) {
            String Msg = reponsebody.substring(reponsebody.lastIndexOf("<msg>") + 5, reponsebody.lastIndexOf("</msg>"));
            biosanResult.setStatus(2);
            biosanResult.setMsg(Msg);
            return biosanResult;
        }
        

        return null;
    }
}

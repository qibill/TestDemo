package com.qibill;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.qibill.pojo.PatientDetailInfo;

public class GetReponseTest {

	@Test
	public void xmltoString() {
		String reponse ="<root>" + 
				"<head>" + 
				"<parameter>" + 
				"<hashKey>16a839ed-b5f1-XXXX-b9a3-XXXXXXX72e4</hashKey>" + 
				"<CardNo>135403000800151</CardNo>" + 
				"</parameter>" + 
				"</head>" + 
				"<body>" + 
				"<result>OK</result>" + 
				"<DataTable>" + 
				"<row>" + 
				"<Patid>1000000108</Patid>" + 
				"<OutPatiNo>YL00800151</OutPatiNo>" + 
				"<PatName>测试151</PatName>" + 
				"<SexName>女</SexName>" + 
				"<LastMense>2017-12-01</LastMense>" + 
				"<Age>8</Age>" + 
				"<IdCardNo>310103XXXXXXXX5610</IdCardNo>" + 
				"<Tel />" + 
				"<Mobile>13111111111</Mobile>" + 
				"<Height />" + 
				"<Weight />" + 
				"<ChildHistory />" + 
				"<MedicalHistory />" + 
				"<Flag>1</Flag>" + 
				"<Msg />" + 
				"</row>" + 
				"</DataTable>" + 
				"</body>" + 
				"</root>";
		String reponsebody = reponse.substring(reponse.indexOf("<body>") + 6, reponse.indexOf("</body>"));
		String result = reponsebody.substring(reponsebody.indexOf("<result>") + 8, reponsebody.indexOf("</result>"));
		//失败-系统级
		if ("err".equals(result)) {
			String errMsg = reponsebody.substring(reponsebody.indexOf("<errMsg>") + 8, reponsebody.indexOf("</errMsg>"));
			System.out.println(errMsg); 
		}
		
		String Flag = reponsebody.substring(reponsebody.indexOf("<Flag>") + 6, reponsebody.indexOf("</Flag>"));		
		//失败
		if ("0".equals(Flag)) {
			String Msg = reponsebody.substring(reponsebody.indexOf("<Msg>") + 5, reponsebody.indexOf("</Msg>"));
			System.out.println(Msg);
		}
		
		String dataTable = reponsebody.substring(reponsebody.indexOf("<DataTable>") + 11, reponsebody.indexOf("</DataTable>"));
		System.out.println(dataTable);
		JAXBContext jc;
		PatientDetailInfo patientDetailInfo = new PatientDetailInfo();
		try {
			jc = JAXBContext.newInstance(PatientDetailInfo.class);
			Unmarshaller unmarshaller=jc.createUnmarshaller();
			patientDetailInfo = (com.qibill.pojo.PatientDetailInfo) unmarshaller.unmarshal(new StringReader(dataTable));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println(patientDetailInfo.toString());
	}
}

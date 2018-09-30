package com.qibill.test;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.biosan.utils.JsonUtil;
import com.biosan.webservice.TSSCServiceImpl;
import com.newtouch.pojo.PatientDetailInfo;
import com.newtouch.pojo.PatientDetailInfoRequest;
import com.newtouch.pojo.PlatFormTSSCServiceRequest;

public class XMLTest {

	@Test
	public void xmlTOString() throws Exception {
/*	    TSSCServiceImpl serivce = new TSSCServiceImpl();
		PlatFormTSSCServiceRequest request = serivce.creator(74734, 1);
		
		System.out.println(request.toXml());*/
		PatientDetailInfoRequest request = new PatientDetailInfoRequest();
		request.setCardNo("331081117");
		System.out.println(request.toXml());
	}
	
	@Test
	public void xmlToObject() throws JAXBException {
		JAXBContext jc;
		PatientDetailInfo patientDetail = new PatientDetailInfo();
		String dataTable = "<row>" + 
				"        <Patid>1000000108</Patid>" + 
				"        <OutPatiNo>YL00800151</OutPatiNo>" + 
				"        <PatName>测试151</PatName>" + 
				"        <SexName>女</SexName>" + 
				"        <LastMense>2017-12-01</LastMense>" + 
				"        <Age>8</Age>" + 
				"        <IdCardNo>310103XXXXXXXX5610</IdCardNo>" + 
				"        <Tel />" + 
				"        <Mobile>13111111111</Mobile>" + 
				"        <Height />" + 
				"        <Weight />" + 
				"        <ChildHistory />" + 
				"        <MedicalHistory />" + 
				"        <Flag>1</Flag>" + 
				"        <Msg />" + 
				"      </row>" + 
				"";
		jc = JAXBContext.newInstance(PatientDetailInfo.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		patientDetail = (PatientDetailInfo) unmarshaller
				.unmarshal(new StringReader(dataTable));
		System.out.println(JsonUtil.objectToJson(patientDetail));
	}
}

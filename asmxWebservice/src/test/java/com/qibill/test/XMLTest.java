package com.qibill.test;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.biosan.pojo.PatientDetail;
import com.biosan.webservice.TSSCServiceImpl;
import com.newtouch.pojo.PatientDetailInfo;
import com.newtouch.pojo.PlatFormTSSCServiceRequest;

public class XMLTest {

	@Test
	public void xmlTOString() throws Exception {
	    TSSCServiceImpl serivce = new TSSCServiceImpl();
		PlatFormTSSCServiceRequest request = serivce.creator(74734, 1);
		
		System.out.println(request.toXml());
/*		Class clazz = contentItem.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
			
		}*/
	}
	
	@Test
	public void xmlToObject() throws JAXBException {
		JAXBContext jc;
		PatientDetail patientDetail = new PatientDetail();
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
		jc = JAXBContext.newInstance(PatientDetail.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		patientDetail = (PatientDetail) unmarshaller
				.unmarshal(new StringReader(dataTable));
		System.out.println(patientDetail.toString());
	}
}

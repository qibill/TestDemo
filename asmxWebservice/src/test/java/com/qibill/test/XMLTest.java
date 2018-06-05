package com.qibill.test;

import org.junit.Test;

import com.biosan.webservice.TSSCServiceImpl;
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
}

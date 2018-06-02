package com.qibill.test;

import org.junit.Test;

import com.biosan.webservice.BiosanWebSerivceImpl;
import com.newtouch.pojo.PlatFormTSSCServiceRequest;

public class XMLTest {

	@Test
	public void xmlTOString() throws Exception {
		BiosanWebSerivceImpl serivce = new BiosanWebSerivceImpl();
		PlatFormTSSCServiceRequest request = serivce.creatPlatFormTSSCServiceRequest(74734, "1");
		
		System.out.println(request.toXml());
/*		Class clazz = contentItem.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
			
		}*/
		
	}
}

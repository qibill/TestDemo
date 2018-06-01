package com.qibill;

import org.junit.Test;

import com.NewTouch.pojo.PlatFormTSSCServiceRequest;
import com.biosan.webservice.impl.NewTouchWebSerivceImpl;

public class XMLTest {

	@Test
	public void xmlTOString() throws Exception {
		NewTouchWebSerivceImpl serivce = new NewTouchWebSerivceImpl();
		PlatFormTSSCServiceRequest request = serivce.creatPlatFormTSSCServiceRequest(74734, "1");
		
		System.out.println(request.toXml());
/*		Class clazz = contentItem.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
			
		}*/
		
	}
}

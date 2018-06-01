package com.qibill;

import org.junit.Test;

import com.NewTouch.pojo.PlatFormTSSCServiceRequest;
import com.biosan.webservice.NewTouchSerivce;

public class XMLTest {

	@Test
	public void xmlTOString() throws Exception {
		NewTouchSerivce serivce = new NewTouchSerivce();
		PlatFormTSSCServiceRequest request = serivce.creatPlatFormTSSCServiceRequest(74795, "1");
		
		System.out.println(request.toXml());
/*		Class clazz = contentItem.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
			
		}*/
		
	}
}

package com.qibill;

import org.junit.Test;

import com.qibill.pojo.ContentBody;

public class XMLTest {

	@Test
	public void xmlTOString() throws Exception {
		ContentBody object = new ContentBody();

		
		System.out.println(object.toXml());
/*		Class clazz = contentItem.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
			
		}*/
		
	}
}

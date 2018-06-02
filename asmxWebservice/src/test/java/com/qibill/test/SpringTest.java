package com.qibill.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.biosan.webservice.BiosanWebSerivce;

public class SpringTest {

	@Test
	public void dataSourceTest() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		BiosanWebSerivce bean = (BiosanWebSerivce) context.getBean("newTouchWebSerivce");
		bean.sendPlatFormTSSCService(74734, "1");
		System.out.println(bean.toString());
	}

}

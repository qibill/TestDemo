package com.biosan.quartz;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuartzTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("quartz.xml");
		context.start();
	}
}
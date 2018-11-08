package com.qibill.test;

import org.junit.Test;

import com.biosan.utils.JsonUtil;
import com.newtouch.pojo.PatientDetailInfo;

public class JsonTest {

	@Test
	public void test1() {
		PatientDetailInfo info = new PatientDetailInfo();
		info.setIdCardNo("safsdfsfas");
		System.out.println(JsonUtil.objectToJson(info));

	}
	
}

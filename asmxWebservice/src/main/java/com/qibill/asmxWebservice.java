package com.qibill;

import java.util.Vector;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class asmxWebservice {
	private String url = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";// 提供接口的地址
	private String soapaction = "http://WebXml.com.cn/"; // 域名，这是在server定义的

	public void GetAdmOrgUnitByUpdateTime() {
		Service service = new Service();
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName(soapaction, "getSupportCity")); // 设置要调用哪个方法
			call.addParameter(new QName(soapaction, "byProvinceName"), // 设置要传递的参数
					org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(new QName(soapaction, "getSupportCity"), String [].class); // 要返回的数据类型（自定义类型）

//			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// （标准的类型）

			call.setUseSOAPAction(true);
			call.setSOAPActionURI(soapaction + "getSupportCity");

			String [] invoke = (String []) call.invoke(new Object[] {"浙江"});// 调用方法并传递参数
			for (String string : invoke) {
				System.out.println("result is " + string);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String args[]) {
		asmxWebservice serivce = new asmxWebservice();
		serivce.GetAdmOrgUnitByUpdateTime();
	}
}

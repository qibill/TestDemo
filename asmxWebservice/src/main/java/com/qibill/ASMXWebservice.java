package com.qibill;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class ASMXWebservice {
	private String url = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";// 提供接口的地址
	private String soapaction = "http://WebXml.com.cn/"; // 域名，这是在server定义的

	public void GetAdmOrgUnitByUpdateTime() {
		Service service = new Service();
		String Method = "getSupportCity";
		Object[] objects = {"浙江"};
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);//设置目标服务端口的端点地址
			call.setOperationName(new QName(soapaction, Method)); // 设置要调用哪个方法
			call.addParameter(new QName(soapaction, "byProvinceName"), // 设置要传递的参数
					org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(new QName(soapaction, Method), String [].class); // 要返回的数据类型（自定义类型）

//			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// （标准的类型）

			call.setUseSOAPAction(true);
			call.setSOAPActionURI(soapaction + Method);

			String [] invoke = (String []) call.invoke(objects);// 调用方法并传递参数
			for (String string : invoke) {
				System.out.println("result is " + string);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ASMXWebservice serivce = new ASMXWebservice();
		serivce.GetAdmOrgUnitByUpdateTime();
	}
}

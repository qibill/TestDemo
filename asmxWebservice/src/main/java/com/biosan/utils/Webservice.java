package com.biosan.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/**
 * Webservice的工具实现类
 * 
 * @author qibill
 */
public class Webservice {

	/**
	 * 使用 Axis 调用Webservice
	 * 
	 * @param url			URL地址
	 * @param soapaction	名称空间
	 * @param Method		方法名
	 * @param parameter		参数Map,key为参数名,value为参数值，目前只支持String。
	 * @param clazz			返回的类
	 * @author qibill 2018年5月29日下午2:25:34
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> T doAxisWebservice(String url, String soapaction, String Method,
			Map<String, Object> parameter, Class<T> clazz) {
		Service service = new Service();
		List<Object> objects = new ArrayList<>();
		try {
			Call call = (Call) service.createCall();
			// 设置目标服务端口的端点地址
			call.setTargetEndpointAddress(url);
			// 设置要调用哪个方法
			call.setOperationName(new QName(soapaction, Method)); 
			// 设置要传递的参数
			for (String parameterName  : parameter.keySet()) {
				call.addParameter(new QName(soapaction, parameterName), 
						org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
				objects.add(parameter.get(parameterName));
			}
			// 要返回的数据类型（自定义类型）
			call.setReturnType(new QName(soapaction, Method), clazz); 

			// call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// （标准的类型）

			call.setUseSOAPAction(true);
			call.setSOAPActionURI(soapaction + Method);
			// 调用方法并传递参数
			return (T)call.invoke(objects.toArray());
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}

package com.weather;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2018-06-02T22:54:27.966+08:00
 * Generated source version: 2.7.11
 * 
 */
@WebService(targetNamespace = "http://WebXml.com.cn/", name = "WeatherWebServiceHttpGet")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface WeatherWebServiceHttpGet {

    @WebResult(name = "ArrayOfString", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    @WebMethod
    public ArrayOfString getWeatherbyCityName(
        @WebParam(partName = "theCityName", name = "theCityName", targetNamespace = "http://WebXml.com.cn/")
        java.lang.String theCityName
    );

    @WebResult(name = "DataSet", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    @WebMethod
    public DataSet getSupportDataSet();

    @WebResult(name = "ArrayOfString", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    @WebMethod
    public ArrayOfString getWeatherbyCityNamePro(
        @WebParam(partName = "theCityName", name = "theCityName", targetNamespace = "http://WebXml.com.cn/")
        java.lang.String theCityName,
        @WebParam(partName = "theUserID", name = "theUserID", targetNamespace = "http://WebXml.com.cn/")
        java.lang.String theUserID
    );

    @WebResult(name = "ArrayOfString", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    @WebMethod
    public ArrayOfString getSupportCity(
        @WebParam(partName = "byProvinceName", name = "byProvinceName", targetNamespace = "http://WebXml.com.cn/")
        java.lang.String byProvinceName
    );

    @WebResult(name = "ArrayOfString", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    @WebMethod
    public ArrayOfString getSupportProvince();
}

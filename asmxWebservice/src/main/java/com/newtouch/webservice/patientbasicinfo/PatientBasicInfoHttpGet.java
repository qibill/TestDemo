package com.newtouch.webservice.patientbasicinfo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2018-06-06T15:02:48.170+08:00
 * Generated source version: 2.7.11
 * 
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "PatientBasicInfoHttpGet")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface PatientBasicInfoHttpGet {

    /**
     * 病人基本信息
     */
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    @WebMethod
    public java.lang.String patientBasicInfo(
        @WebParam(partName = "xml", name = "xml", targetNamespace = "http://tempuri.org/")
        java.lang.String xml
    );

    /**
     * 病人基本信息(对外)
     */
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    @WebMethod
    public java.lang.String patientBasicInfoOut(
        @WebParam(partName = "xml", name = "xml", targetNamespace = "http://tempuri.org/")
        java.lang.String xml
    );

    /**
     * 病人基本信息(唐筛)
     */
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    @WebMethod
    public java.lang.String patientDetailInfo(
        @WebParam(partName = "xml", name = "xml", targetNamespace = "http://tempuri.org/")
        java.lang.String xml
    );
}

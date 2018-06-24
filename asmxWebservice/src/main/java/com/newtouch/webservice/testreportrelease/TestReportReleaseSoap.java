package com.newtouch.webservice.testreportrelease;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2018-06-06T15:00:58.385+08:00
 * Generated source version: 2.7.11
 * 
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "TestReportReleaseSoap")
@XmlSeeAlso({ObjectFactory.class})
public interface TestReportReleaseSoap {

    /**
     * 检验报告发布
     */
    @WebResult(name = "testReportReleaseResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "testReportRelease", targetNamespace = "http://tempuri.org/", className = "com.newtouch.webservice.testreportrelease.TestReportRelease")
    @WebMethod(action = "http://tempuri.org/testReportRelease")
    @ResponseWrapper(localName = "testReportReleaseResponse", targetNamespace = "http://tempuri.org/", className = "com.newtouch.webservice.testreportrelease.TestReportReleaseResponse")
    public java.lang.String testReportRelease(
        @WebParam(name = "xml", targetNamespace = "http://tempuri.org/")
        java.lang.String xml
    );

    /**
     * 心电图相关接口
     */
    @WebResult(name = "PlatForm_ECG_ServiceResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "PlatForm_ECG_Service", targetNamespace = "http://tempuri.org/", className = "com.newtouch.webservice.testreportrelease.PlatFormECGService")
    @WebMethod(operationName = "PlatForm_ECG_Service", action = "http://tempuri.org/PlatForm_ECG_Service")
    @ResponseWrapper(localName = "PlatForm_ECG_ServiceResponse", targetNamespace = "http://tempuri.org/", className = "com.newtouch.webservice.testreportrelease.PlatFormECGServiceResponse")
    public java.lang.String platFormECGService(
        @WebParam(name = "xml", targetNamespace = "http://tempuri.org/")
        java.lang.String xml
    );

    /**
     * 唐氏筛查相关接口
     */
    @WebResult(name = "PlatForm_TSSC_ServiceResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "PlatForm_TSSC_Service", targetNamespace = "http://tempuri.org/", className = "com.newtouch.webservice.testreportrelease.PlatFormTSSCService")
    @WebMethod(operationName = "PlatForm_TSSC_Service", action = "http://tempuri.org/PlatForm_TSSC_Service")
    @ResponseWrapper(localName = "PlatForm_TSSC_ServiceResponse", targetNamespace = "http://tempuri.org/", className = "com.newtouch.webservice.testreportrelease.PlatFormTSSCServiceResponse")
    public java.lang.String platFormTSSCService(
        @WebParam(name = "xml", targetNamespace = "http://tempuri.org/")
        java.lang.String xml
    );
}
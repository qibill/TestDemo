package com.newtouch.webservice.patientbasicinfo;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2018-06-06T15:02:48.181+08:00
 * Generated source version: 2.7.11
 * 
 */
@WebServiceClient(name = "PatientBasicInfo", 
                  wsdlLocation = "file:/D:/git/TestDemo/asmxWebservice/src/main/resources/PatientBasicInfo.wsdl",
                  targetNamespace = "http://tempuri.org/") 
public class PatientBasicInfo_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempuri.org/", "PatientBasicInfo");
    public final static QName PatientBasicInfoSoap12 = new QName("http://tempuri.org/", "PatientBasicInfoSoap12");
    public final static QName PatientBasicInfoSoap = new QName("http://tempuri.org/", "PatientBasicInfoSoap");
    public final static QName PatientBasicInfoHttpGet = new QName("http://tempuri.org/", "PatientBasicInfoHttpGet");
    public final static QName PatientBasicInfoHttpPost = new QName("http://tempuri.org/", "PatientBasicInfoHttpPost");
    static {
        URL url = null;
        try {
            url = new URL("file:/D:/git/TestDemo/asmxWebservice/src/main/resources/PatientBasicInfo.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(PatientBasicInfo_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/D:/git/TestDemo/asmxWebservice/src/main/resources/PatientBasicInfo.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public PatientBasicInfo_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PatientBasicInfo_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PatientBasicInfo_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public PatientBasicInfo_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public PatientBasicInfo_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public PatientBasicInfo_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns PatientBasicInfoSoap
     */
    @WebEndpoint(name = "PatientBasicInfoSoap12")
    public PatientBasicInfoSoap getPatientBasicInfoSoap12() {
        return super.getPort(PatientBasicInfoSoap12, PatientBasicInfoSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PatientBasicInfoSoap
     */
    @WebEndpoint(name = "PatientBasicInfoSoap12")
    public PatientBasicInfoSoap getPatientBasicInfoSoap12(WebServiceFeature... features) {
        return super.getPort(PatientBasicInfoSoap12, PatientBasicInfoSoap.class, features);
    }
    /**
     *
     * @return
     *     returns PatientBasicInfoSoap
     */
    @WebEndpoint(name = "PatientBasicInfoSoap")
    public PatientBasicInfoSoap getPatientBasicInfoSoap() {
        return super.getPort(PatientBasicInfoSoap, PatientBasicInfoSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PatientBasicInfoSoap
     */
    @WebEndpoint(name = "PatientBasicInfoSoap")
    public PatientBasicInfoSoap getPatientBasicInfoSoap(WebServiceFeature... features) {
        return super.getPort(PatientBasicInfoSoap, PatientBasicInfoSoap.class, features);
    }
    /**
     *
     * @return
     *     returns PatientBasicInfoHttpGet
     */
    @WebEndpoint(name = "PatientBasicInfoHttpGet")
    public PatientBasicInfoHttpGet getPatientBasicInfoHttpGet() {
        return super.getPort(PatientBasicInfoHttpGet, PatientBasicInfoHttpGet.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PatientBasicInfoHttpGet
     */
    @WebEndpoint(name = "PatientBasicInfoHttpGet")
    public PatientBasicInfoHttpGet getPatientBasicInfoHttpGet(WebServiceFeature... features) {
        return super.getPort(PatientBasicInfoHttpGet, PatientBasicInfoHttpGet.class, features);
    }
    /**
     *
     * @return
     *     returns PatientBasicInfoHttpPost
     */
    @WebEndpoint(name = "PatientBasicInfoHttpPost")
    public PatientBasicInfoHttpPost getPatientBasicInfoHttpPost() {
        return super.getPort(PatientBasicInfoHttpPost, PatientBasicInfoHttpPost.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PatientBasicInfoHttpPost
     */
    @WebEndpoint(name = "PatientBasicInfoHttpPost")
    public PatientBasicInfoHttpPost getPatientBasicInfoHttpPost(WebServiceFeature... features) {
        return super.getPort(PatientBasicInfoHttpPost, PatientBasicInfoHttpPost.class, features);
    }

}
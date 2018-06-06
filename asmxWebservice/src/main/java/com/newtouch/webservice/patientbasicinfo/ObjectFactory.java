
package com.newtouch.webservice.patientbasicinfo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.newtouch.webservice.patientbasicinfo package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _String_QNAME = new QName("http://tempuri.org/", "string");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.newtouch.webservice.patientbasicinfo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PatientBasicInfoOut }
     * 
     */
    public PatientBasicInfoOut createPatientBasicInfoOut() {
        return new PatientBasicInfoOut();
    }

    /**
     * Create an instance of {@link PatientBasicInfoResponse }
     * 
     */
    public PatientBasicInfoResponse createPatientBasicInfoResponse() {
        return new PatientBasicInfoResponse();
    }

    /**
     * Create an instance of {@link PatientBasicInfo }
     * 
     */
    public PatientBasicInfo createPatientBasicInfo() {
        return new PatientBasicInfo();
    }

    /**
     * Create an instance of {@link PatientBasicInfoOutResponse }
     * 
     */
    public PatientBasicInfoOutResponse createPatientBasicInfoOutResponse() {
        return new PatientBasicInfoOutResponse();
    }

    /**
     * Create an instance of {@link PatientDetailInfoResponse }
     * 
     */
    public PatientDetailInfoResponse createPatientDetailInfoResponse() {
        return new PatientDetailInfoResponse();
    }

    /**
     * Create an instance of {@link PatientDetailInfo }
     * 
     */
    public PatientDetailInfo createPatientDetailInfo() {
        return new PatientDetailInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

}

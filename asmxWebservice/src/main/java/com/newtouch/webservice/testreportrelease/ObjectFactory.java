
package com.newtouch.webservice.testreportrelease;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.newtouch.webservice.testreportrelease package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.newtouch.webservice.testreportrelease
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TestReportRelease }
     * 
     */
    public TestReportRelease createTestReportRelease() {
        return new TestReportRelease();
    }

    /**
     * Create an instance of {@link PlatFormECGService }
     * 
     */
    public PlatFormECGService createPlatFormECGService() {
        return new PlatFormECGService();
    }

    /**
     * Create an instance of {@link PlatFormECGServiceResponse }
     * 
     */
    public PlatFormECGServiceResponse createPlatFormECGServiceResponse() {
        return new PlatFormECGServiceResponse();
    }

    /**
     * Create an instance of {@link TestReportReleaseResponse }
     * 
     */
    public TestReportReleaseResponse createTestReportReleaseResponse() {
        return new TestReportReleaseResponse();
    }

    /**
     * Create an instance of {@link PlatFormTSSCServiceResponse }
     * 
     */
    public PlatFormTSSCServiceResponse createPlatFormTSSCServiceResponse() {
        return new PlatFormTSSCServiceResponse();
    }

    /**
     * Create an instance of {@link PlatFormTSSCService }
     * 
     */
    public PlatFormTSSCService createPlatFormTSSCService() {
        return new PlatFormTSSCService();
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

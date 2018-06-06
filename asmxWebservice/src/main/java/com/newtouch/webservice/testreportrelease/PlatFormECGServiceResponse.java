
package com.newtouch.webservice.testreportrelease;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PlatForm_ECG_ServiceResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "platFormECGServiceResult"
})
@XmlRootElement(name = "PlatForm_ECG_ServiceResponse")
public class PlatFormECGServiceResponse {

    @XmlElement(name = "PlatForm_ECG_ServiceResult")
    protected String platFormECGServiceResult;

    /**
     * 获取platFormECGServiceResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlatFormECGServiceResult() {
        return platFormECGServiceResult;
    }

    /**
     * 设置platFormECGServiceResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlatFormECGServiceResult(String value) {
        this.platFormECGServiceResult = value;
    }

}
